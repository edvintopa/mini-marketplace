package com.example.minimarketplace.model.communication.request.user;

import java.util.List;

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-05-18
 */
public class SetInterestRequest {
    private List<String> interests;

    public SetInterestRequest(List<String> interests) {
        this.interests = interests;
    }

    public List<String> getInterests() {
        return interests;
    }
}
