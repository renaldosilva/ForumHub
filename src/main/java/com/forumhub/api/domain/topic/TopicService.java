package com.forumhub.api.domain.topic;

import com.forumhub.api.domain.course.Course;
import com.forumhub.api.domain.course.CourseRepository;
import com.forumhub.api.domain.user.User;
import com.forumhub.api.domain.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    public TopicDetailDataDTO register(TopicRegistrationDataDTO data) {
        User author = validateAndGetUser(data.authorId());
        Course course = validateAndGetCourse(data.courseId());

        Topic topic = new Topic(data, author, course);
        this.topicRepository.save(topic);
        return new TopicDetailDataDTO(topic);
    }

    public Page<TopicDetailDataDTO> listAll(Pageable pageable) {
        return this.topicRepository.findAllAvaliable(pageable, StatusEnum.HIDDEN)
                .map(TopicDetailDataDTO::new);
    }

    public TopicDetailDataDTO findById(Long id) {
        return new TopicDetailDataDTO(this.topicRepository.getReferenceById(id));
    }

    public TopicDetailDataDTO update(TopicUpdateDataDTO data) {
        Topic topic = validateAndGetTopic(data.id());
        User author = validateAndGetUser(data.authorId());
        Course course = (data.courseId() != null) ? validateAndGetCourse(data.courseId()) : null;

        validateOwnership(topic, author.getId());

        topic.update(data, course);
        return new TopicDetailDataDTO(topic);
    }

    public void delete(TopicDeleteDataDTO data) {
        Topic topic = validateAndGetTopic(data.topicId());
        User author = validateAndGetUser(data.authorId());
        validateOwnership(topic, author.getId());

        topic.hidden();
    }

    private User validateAndGetUser(Long userId) {
        if (!userRepository.existsById(userId) || !userRepository.findAtivoById(userId)) {
            throw new EntityNotFoundException("O usuário com o id:%d não existe ou está inativo!".formatted(userId));
        }
        return userRepository.getReferenceById(userId);
    }

    private Course validateAndGetCourse(Long courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new EntityNotFoundException("O curso com o id:%d não existe!".formatted(courseId));
        }
        return courseRepository.getReferenceById(courseId);
    }

    private Topic validateAndGetTopic(Long topicId) {
        if (!topicRepository.existsById(topicId)) {
            throw new EntityNotFoundException("O Tópico com o id:%d não existe!".formatted(topicId));
        }
        return topicRepository.getReferenceById(topicId);
    }

    private void validateOwnership(Topic topic, Long actualAuthorId) {
        if (!Objects.equals(actualAuthorId, topic.getAuthor().getId())) {
            throw new IllegalArgumentException("Erro!!! O tópico com o id:%d não pertence ao usuário com o id:%d!"
                    .formatted(topic.getId(), actualAuthorId));
        }
    }
}
