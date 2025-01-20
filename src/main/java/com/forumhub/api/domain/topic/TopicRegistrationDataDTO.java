package com.forumhub.api.domain.topic;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicRegistrationDataDTO(
        @NotBlank
        String title,

        @NotBlank
        String message,

        @NotNull
        @JsonAlias("author_id")
        Long authorId,

        @NotNull
        @JsonAlias("course_id")
        Long courseId
) {
}
