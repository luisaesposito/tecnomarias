package br.uff.tecnomarias.rest.dto;

import java.time.LocalDateTime;

public class ErrorResponse {
    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public static class Builder {
        private LocalDateTime timestamp;
        private Integer status;
        private String error;
        private String message;
        private String path;

        public Builder() {
            timestamp = LocalDateTime.now();
        }

        public Builder statusCode(int statusCode) {
            this.status = statusCode;
            return this;
        }

        public Builder error(String error) {
            this.error = error;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public ErrorResponse build() {
            var response = new ErrorResponse();
            response.status = this.status;
            response.error = this.error;
            response.message = this.message;
            response.path = this.path;
            return response;
        }
    }

    public ErrorResponse() {
        // desserializacao
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
