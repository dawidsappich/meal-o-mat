package de.sappich.mealomat.utils;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class ApplicationResponse {

    private Boolean isSuccess;
    private ApplicationCode code;
    private String message;
    private LocalDateTime time;

    private ApplicationResponse() {
    }

    private ApplicationResponse(Builder builder) {
        this.isSuccess = builder.isSuccess;
        this.code = builder.code;
        this.message = builder.message;
        this.time = builder.time;
    }


    public static class Builder {
        private Boolean isSuccess;
        private ApplicationCode code;
        private String message;
        private LocalDateTime time;

        public ApplicationResponse build() {
            return new ApplicationResponse(this);
        }

        public Builder setIsSuccess(Boolean isSuccess) {
            this.isSuccess = isSuccess;
            return this;
        }

        public Builder setCode(ApplicationCode code) {
            this.code = code;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setTime(LocalDateTime time) {
            this.time = time;
            return this;
        }

    }

    public static ApplicationResponse createResponse(String message, ApplicationCode code) {
        ApplicationResponse.Builder builder = new ApplicationResponse.Builder();
        boolean isSuccess = false;

        if (code.equals(ApplicationCode.OK)) {
            isSuccess = true;
        }

        return builder.setMessage(message)
                .setIsSuccess(isSuccess)
                .setCode(code)
                .setTime(LocalDateTime.now())
                .build();
    }


}
