package ru.yadoma_realty.api.models.auth_models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LoginResponse(
        @JsonProperty("access_token")
        String accessToken,
        @JsonProperty("token_type")
        String tokenType,
        @JsonProperty("access_expires_in")
        Integer accessExpiresIn,
        @JsonProperty("refresh_token")
        String refreshToken,
        @JsonProperty("refresh_expires_in")
        Integer refreshExpiresIn,
        User user) {
}
