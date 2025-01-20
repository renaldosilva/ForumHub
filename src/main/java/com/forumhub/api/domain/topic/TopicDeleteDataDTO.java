package com.forumhub.api.domain.topic;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

public record TopicDeleteDataDTO(
        @NotNull
        @JsonAlias("topic_id")
        Long topicId,

        @NotNull
        @JsonAlias("author_id")
        Long authorId
) {
}
