package com.forumhub.api.domain.topic;

import com.forumhub.api.domain.course.CourseDetailDataDTO;
import com.forumhub.api.domain.user.UserDetailDataDTO;

public record TopicDetailDataDTO(
        Long id,
        String title,
        String message,
        StatusEnum status,
        UserDetailDataDTO author,
        CourseDetailDataDTO course
) {
    public TopicDetailDataDTO(Topic topic) {
        this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getStatus(),
                new UserDetailDataDTO(topic.getAuthor()), new CourseDetailDataDTO(topic.getCourse()));
    }
}
