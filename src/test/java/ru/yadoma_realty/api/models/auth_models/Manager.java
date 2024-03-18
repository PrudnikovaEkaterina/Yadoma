package ru.yadoma_realty.api.models.auth_models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Manager(
        Integer id,
        String phone,
        @JsonProperty("phone_verified_at")
        Date phoneVerifiedAt,
        String name,
        @JsonProperty("avatar_path")
        String avatarPath,
        Integer role,
        @JsonProperty("manager_id")
        Integer managerId,
        @JsonProperty("referral_code")
        String referralCode,
        String email,
        @JsonProperty("gar_object_id")
        Long garObjectId,
        @JsonProperty("created_at")
        Date createdAt,
        @JsonProperty("updated_at")
        Date updatedAt,
        @JsonProperty("avatar_url")
        String avatarUrl,
        Object manager
) {
}
