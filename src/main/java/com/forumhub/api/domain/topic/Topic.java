package com.forumhub.api.domain.topic;

import com.forumhub.api.domain.course.Course;
import com.forumhub.api.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topics")
@Entity(name = "Topic")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String message;
    private LocalDateTime creationDate;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    public Topic(TopicRegistrationDataDTO data, User author, Course course) {
        this.title = data.title();
        this.message = data.message();
        this.creationDate = LocalDateTime.now();
        this.status = StatusEnum.ACTIVE;
        this.author = author;
        this.course = course;
    }

    public void update(TopicUpdateDataDTO data, Course course) {
        if (data.title() != null) {
            this.title = data.title();
        }

        if (data.message() != null) {
            this.message = data.message();
        }

        if (course != null) {
            this.course = course;
        }
    }

    public void hidden() {
        this.status = StatusEnum.HIDDEN;
    }
}
