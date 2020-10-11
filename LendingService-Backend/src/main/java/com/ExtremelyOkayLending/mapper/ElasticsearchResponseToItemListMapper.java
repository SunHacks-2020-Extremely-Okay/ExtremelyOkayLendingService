package com.ExtremelyOkayLending.mapper;

import java.util.ArrayList;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;

import example.models.LendorItem;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ElasticsearchResponseToItemListMapper {
    private static final String ITEM_ID = "item_id";
    private static final String USER_ID = "user_id";
    private static final String CATEGORY = "category";
    private static final String LOCATION_X = "location_x";
    private static final String LOCATION_Y = "location_y";
    private static final String VERIFIED = "verified";
    private static final String AVAILABLE = "available";
    private static final String RETURN_DATE = "return_date";
    private static final String SKU = "sku";

    public ArrayList<LendorItem> map(SearchResponse searchResponse) {
        ArrayList<LendorItem> items = new ArrayList<>();

        SearchHit[] searchHits = searchResponse.getHits().getHits();
        for (SearchHit searchHit : searchHits) {
            final Map<String, Object> documentMap = searchHit.getSourceAsMap();

            final Long itemId = (Long) documentMap.get(ITEM_ID);
            final String userId = (String) documentMap.get(USER_ID);
            final Integer category = (Integer) documentMap.get(CATEGORY);
            final Double locationX = (Double) documentMap.get(LOCATION_X);
            final Double locationY = (Double) documentMap.get(LOCATION_Y);
            final Boolean verified = (Boolean) documentMap.get(VERIFIED);
            final Boolean available = (Boolean) documentMap.get(AVAILABLE);
            final Long returnDate = (Long) documentMap.get(RETURN_DATE);
            final String sku = (String) documentMap.get(SKU);

            LendorItem item = new LendorItem(
                    itemId,
                    userId,
                    category,
                    locationX,
                    locationY,
                    verified,
                    available,
                    returnDate,
                    sku
            );

            items.add(item);
        }

        return items;
    }
}
