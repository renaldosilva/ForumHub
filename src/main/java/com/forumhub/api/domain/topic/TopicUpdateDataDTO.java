package com.forumhub.api.domain.topic;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

public record TopicUpdateDataDTO(
        @NotNull
        Long id,

        String title,

        String message,

        @NotNull
        @JsonAlias("author_id")
        Long authorId,

        @JsonAlias("course_id")
        Long courseId
) {
}
