package com.ExtremelyOkayLending.service;

import com.amazonaws.auth.AWS4Signer;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.http.AWSRequestSigningApacheInterceptor;

import com.ExtremelyOkayLending.models.LendorItem;
import com.ExtremelyOkayLending.models.SearchItemRequest;
import com.ExtremelyOkayLending.models.UpdateAvailabilityStatusRequest;
import com.ExtremelyOkayLending.models.UpdateVerificationStatusRequest;
import lombok.NoArgsConstructor;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequestInterceptor;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@NoArgsConstructor
public class ElasticsearchItemStoreAdapter {

    private static final String SERVICE_NAME = "es";
    private static final String REGION = "us-east-2";
    private static final String ES_ENDPOINT =
            "https://search-es-item-store-2-2qxcbj74s2d52rp6mggb6psxyu.us-east-2.es.amazonaws.com";
    private static final String ES_INDEX = "items-index";

    private static final String ITEM_ID = "item_id";
    private static final String USER_ID = "user_id";
    private static final String CATEGORY = "category";
    private static final String LOCATION_X = "location_x";
    private static final String LOCATION_Y = "location_y";
    private static final String VERIFIED = "verified";
    private static final String AVAILABLE = "available";
    private static final String RETURN_DATE = "return_date";
    private static final String SKU = "sku";

    private static final double EARTH_RADIUS = 3960.0;
    private static final double DEGREES_TO_RADIANS = 3.1415 / 180.0;
    private static final double RADIANS_TO_DEGREES = 180.0 / 3.1415;

    private static final AWSCredentialsProvider CREDENTIALS_PROVIDER = new DefaultAWSCredentialsProviderChain();

    private static final RestHighLevelClient restHighLevelClient = provideElasticsearchClient();

    public SearchResponse searchItemsIndex(final SearchItemRequest searchItemRequest) {
        final Long itemId = searchItemRequest.getItemId();
        final String userId = searchItemRequest.getUserId();
        final Integer category = searchItemRequest.getCategory();
        final Integer proximity = searchItemRequest.getProximity();
        final Double locationX = searchItemRequest.getLocationX();
        final Double locationY = searchItemRequest.getLocationY();
        final Boolean verified = searchItemRequest.getVerified();
        final Boolean available = searchItemRequest.getAvailable();
        final Long returnDate = searchItemRequest.getReturnDate();
        final String sku = searchItemRequest.getSku();

        final BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (itemId != null) {
            boolQueryBuilder.must(QueryBuilders.matchQuery(ITEM_ID, itemId));
        }
        if (userId != null) {
            boolQueryBuilder.must(QueryBuilders.matchQuery(USER_ID, userId));
        }
        if (category != null) {
            boolQueryBuilder.must(QueryBuilders.matchQuery(CATEGORY, category));
        }
        if (proximity != null && locationX != null && locationY != null) {
            double x = convertMilesToLongitudeDegrees(locationY, proximity);
            double y = convertMilesToLatitudeDegrees(proximity);

            boolQueryBuilder.must(QueryBuilders.rangeQuery(LOCATION_X).gt(locationX - x));
            boolQueryBuilder.must(QueryBuilders.rangeQuery(LOCATION_X).lt(locationX + x));

            boolQueryBuilder.must(QueryBuilders.rangeQuery(LOCATION_Y).gt(locationY - y));
            boolQueryBuilder.must(QueryBuilders.rangeQuery(LOCATION_Y).lt(locationY + y));
        }
        if (verified != null) {
            boolQueryBuilder.must(QueryBuilders.matchQuery(VERIFIED, verified));
        }
        if (available != null) {
            boolQueryBuilder.must(QueryBuilders.matchQuery(AVAILABLE, available));
        }
        if (returnDate != null) {
            boolQueryBuilder.must(QueryBuilders.matchQuery(RETURN_DATE, returnDate));
        }
        if (sku != null) {
            boolQueryBuilder.must(QueryBuilders.matchQuery(SKU, sku));
        }

        final SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().query(boolQueryBuilder);

        final SearchRequest searchRequest = new SearchRequest();
        searchRequest.source(searchSourceBuilder);
        try {
            return restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            System.out.println("Search request failed to be called against ES cluster: " + e);
            return null;
        }
    }

    public IndexResponse insertNewItem(final LendorItem lendorItem) {
        Random random = new Random();
        // Create the document as a hash map
        Map<String, Object> document = new HashMap<>();
        long itemId = Math.abs(random.nextLong());

        /* This is not a perfect solution since we could have overlapping numbers, but for now it's fine */
        document.put(ITEM_ID, itemId);
        document.put(USER_ID, lendorItem.getUserId());
        document.put(CATEGORY, lendorItem.getCategory());
        document.put(LOCATION_X, lendorItem.getLocationX());
        document.put(LOCATION_Y, lendorItem.getLocationY());
        document.put(VERIFIED, false);
        document.put(AVAILABLE, true);
        document.put(RETURN_DATE, 0);
        document.put(SKU, lendorItem.getSku());

        // Form the indexing request, send it, and print the response
        final IndexRequest indexRequest = new IndexRequest(ES_INDEX);
        indexRequest.id(Long.toString(itemId));
        indexRequest.source(document);

        try {
            return restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            System.out.println("Index request failed to be called against ES cluster: " + e);
            return null;
        }
    }

    public UpdateResponse updateVerificationStatus(UpdateVerificationStatusRequest updateVerificationStatusRequest) {
        final Long itemId = updateVerificationStatusRequest.getItemId();
        final Boolean verified = updateVerificationStatusRequest.getVerified();

        final Map<String, Object> document = new HashMap<>();
        document.put(VERIFIED, verified);

        UpdateRequest updateRequest = new UpdateRequest(ES_INDEX, itemId.toString()).doc(document);

        try {
            return restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            System.out.println("Update (verification) request failed to be called against ES cluster: " + e);
            return null;
        }
    }

    public UpdateResponse updateAvailabilityStatus(UpdateAvailabilityStatusRequest updateAvailabilityStatusRequest) {
        final Long itemId = updateAvailabilityStatusRequest.getItemId();
        final Boolean available = updateAvailabilityStatusRequest.getAvailable();
        final Long returnDateTimeStamp = updateAvailabilityStatusRequest.getReturnDate();

        final Map<String, Object> document = new HashMap<>();
        document.put(AVAILABLE, available);

        if (!available) {
            if (returnDateTimeStamp != null) {
                document.put(RETURN_DATE, returnDateTimeStamp);
            } else {
                System.out.println("Return date cannot be null if someone is trying to check out item");
                return null;
            }
        }

        UpdateRequest updateRequest = new UpdateRequest(ES_INDEX, itemId.toString()).doc(document);

        try {
            return restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            System.out.println("Update (availability) request failed to be called against ES cluster: " + e);
            return null;
        }
    }

    // Adds the interceptor to the ES REST client
    private static RestHighLevelClient provideElasticsearchClient() {
        AWS4Signer signer = new AWS4Signer();
        signer.setServiceName(SERVICE_NAME);
        signer.setRegionName(REGION);
        HttpRequestInterceptor interceptor =
                new AWSRequestSigningApacheInterceptor(SERVICE_NAME, signer, CREDENTIALS_PROVIDER);
        return new RestHighLevelClient(RestClient.builder(HttpHost.create(ES_ENDPOINT))
                                                 .setHttpClientConfigCallback(hacb -> hacb.addInterceptorLast(
                                                         interceptor)));
    }

    private double convertMilesToLongitudeDegrees(double latitude,
            int miles) {
        double radians = EARTH_RADIUS * Math.cos(latitude * DEGREES_TO_RADIANS);
        return (miles / radians) * RADIANS_TO_DEGREES;
    }

    private double convertMilesToLatitudeDegrees(int miles) {
        return (miles / EARTH_RADIUS) * RADIANS_TO_DEGREES;
    }
}
