package com.forumhub.api.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationDataDTO (
        @NotBlank
        @Email
        String email,

        @NotBlank
        String password
){
}
