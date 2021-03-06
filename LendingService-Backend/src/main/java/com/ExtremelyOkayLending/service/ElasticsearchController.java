package com.ExtremelyOkayLending.service;

import java.util.ArrayList;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ExtremelyOkayLending.mapper.ElasticsearchResponseToItemListMapper;
import com.ExtremelyOkayLending.models.LendorItem;
import com.ExtremelyOkayLending.models.SearchItemRequest;
import com.ExtremelyOkayLending.models.UpdateAvailabilityStatusRequest;
import com.ExtremelyOkayLending.models.UpdateVerificationStatusRequest;

@RestController
public class ElasticsearchController {
    @CrossOrigin
    @GetMapping("/items")
    public ArrayList<LendorItem> getItems(
            @RequestParam(required = false) Integer itemId,
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) Integer category,
            @RequestParam(required = false, defaultValue = "5") Integer proximity,
            @RequestParam(required = false, defaultValue = "-111.9401533") Double locationX,
            @RequestParam(required = false, defaultValue = "33.4226584") Double locationY,
            @RequestParam(required = false) Boolean verified,
            @RequestParam(required = false) Boolean available,
            @RequestParam(required = false) Long returnDate,
            @RequestParam(required = false) String sku
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

        if (searchResponse != null && searchResponse.getHits().getTotalHits().value > 0L) {
            ElasticsearchResponseToItemListMapper mapper = new ElasticsearchResponseToItemListMapper();

            return mapper.map(searchResponse);
        } else {
            return null;
        }
    }

    @CrossOrigin
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

    @CrossOrigin
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

    @CrossOrigin
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
