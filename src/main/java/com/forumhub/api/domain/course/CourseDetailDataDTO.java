package com.forumhub.api.domain.course;

public record CourseDetailDataDTO(
        Long id,
        String name,
        CategoryEnum category
) {
    public CourseDetailDataDTO(Course course) {
        this(course.getId(), course.getName(), course.getCategory());
    }
}
