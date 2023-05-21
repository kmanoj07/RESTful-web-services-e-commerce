package com.kumarmanoj.ecommerce.dto.userDTO;

public class SigninResponseDTO {
    private String status;
    private String token;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public SigninResponseDTO(String status, String token) {
        this.status = status;
        this.token = token;
    }
}
