package ru.yadoma_realty.api.models.auth_model;

public record LoginRequest(
        String password,
        String phone) {

    public LoginRequest (String userPhone) {
        this(null, userPhone);
    }

}
