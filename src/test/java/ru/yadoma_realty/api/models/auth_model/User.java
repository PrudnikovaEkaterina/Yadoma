package ru.yadoma_realty.api.models.auth_model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public record User(
        Integer id,
        String phone,
        @JsonProperty("phone_verified_at")
        Date phoneVerifiedAt,
        String name,
        @JsonProperty("avatar_path")
        String avatarPath,
        Integer role,
        @JsonProperty("manager_id")
        String managerId,
        @JsonProperty("referral_code")
        String referralCode,
        String email,
        @JsonProperty("gar_object_id")
        Long garObjectId,
        @JsonProperty("created_at")
        Date createdAt,
        @JsonProperty("updated_at")
        Date updatedAt,
        @JsonProperty("favorites_flats_count")
        Integer favoritesFlatsCount,
        @JsonProperty("favorites_buildings_count")
        Integer favoritesBuildingsCount,
        @JsonProperty("recommendations_flats_count")
        Integer recommendationsFlatsCount,
        @JsonProperty("recommendations_buildings_count")
        Integer recommendationsBuildingsCount,
        @JsonProperty("avatar_url")
        String avatarUrl,
        Manager manager
) {
}
