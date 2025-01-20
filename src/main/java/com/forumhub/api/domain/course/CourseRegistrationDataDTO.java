package com.forumhub.api.domain.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseRegistrationDataDTO(
        @NotBlank
        String name,

        @NotNull
        CategoryEnum category
) {
}
