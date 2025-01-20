package com.forumhub.api.domain.user;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRegistrationDataDTO(
        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String password,

        @NotNull
        @JsonAlias("profile_id")
        Long profileId
) {
}
