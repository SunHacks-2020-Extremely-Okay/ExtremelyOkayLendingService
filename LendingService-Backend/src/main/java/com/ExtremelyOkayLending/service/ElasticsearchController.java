package com.ExtremelyOkayLending.service;

import java.util.ArrayList;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;

import example.mapper.ElasticsearchResponseToItemListMapper;
import example.models.LendorItem;
import example.models.SearchItemRequest;
import example.models.UpdateAvailabilityStatusRequest;
import example.models.UpdateVerificationStatusRequest;

@RestController
public class ElasticsearchController {
    @GetMapping("/items")
    public ArrayList<LendorItem> getItems(
            @RequestParam(required = false, value = "item_id") Long itemId,
            @RequestParam(required = false, value = "user_id") String userId,
            @RequestParam(required = false, value = "category") Integer category,
            @RequestParam(required = false, value = "proximity", defaultValue = "5") Integer proximity,
            @RequestParam(required = false, value = "location_x", defaultValue = "-111.9401533") Double locationX,
            @RequestParam(required = false, value = "location_y", defaultValue = "33.4226584") Double locationY,
            @RequestParam(required = false, value = "verified") Boolean verified,
            @RequestParam(required = false, value = "available") Boolean available,
            @RequestParam(required = false, value = "return_date") Long returnDate,
            @RequestParam(required = false, value = "sku") String sku
    ) {
        SearchItemRequest searchItemRequest = new SearchItemRequest(
                itemId,
                userId,
                category,
                proximity,
                locationX,
                locationY,
                verified,
                available,
                returnDate,
                sku
        );

        ElasticsearchItemStoreAdapter esAdapter = new ElasticsearchItemStoreAdapter();
        SearchResponse searchResponse = esAdapter.searchItemsIndex(searchItemRequest);

        if (searchResponse != null && searchResponse.getHits().totalHits > 0L) {
            ElasticsearchResponseToItemListMapper mapper = new ElasticsearchResponseToItemListMapper();

            return mapper.map(searchResponse);
        } else {
            return null;
        }
    }

    @PutMapping("/item")
    public int putItem(@RequestBody LendorItem lendorItem) {
        ElasticsearchItemStoreAdapter esAdapter = new ElasticsearchItemStoreAdapter();
        IndexResponse indexResponse = esAdapter.insertNewItem(lendorItem);

        if (indexResponse != null && indexResponse.status() != null) {
            return indexResponse.status().getStatus();
        } else {
            return 500;
        }
    }

    @PutMapping("/item/verify")
    public int verifyItem(@RequestBody UpdateVerificationStatusRequest updateVerificationStatusRequest) {
        ElasticsearchItemStoreAdapter esAdapter = new ElasticsearchItemStoreAdapter();
        UpdateResponse updateResponse = esAdapter.updateVerificationStatus(updateVerificationStatusRequest);

        if (updateResponse != null && updateResponse.status() != null) {
            return updateResponse.status().getStatus();
        } else {
            return 500;
        }
    }

    @PutMapping("/item/availability")
    public int changeItemAvailability(@RequestBody UpdateAvailabilityStatusRequest updateAvailabilityStatusRequest) {
        ElasticsearchItemStoreAdapter esAdapter = new ElasticsearchItemStoreAdapter();
        UpdateResponse updateResponse = esAdapter.updateAvailabilityStatus(updateAvailabilityStatusRequest);

        if (updateResponse != null && updateResponse.status() != null) {
            return updateResponse.status().getStatus();
        } else {
            return 500;
        }
    }
}
