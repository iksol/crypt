package com.crypt.app.crypt.response;

import org.springframework.http.HttpStatus;

public class UserResponse {
    private HttpStatus status;
    private String response;

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
