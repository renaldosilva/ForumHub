package com.forumhub.api.domain.topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    @Query("SELECT topic FROM Topic topic WHERE topic.status != :status")
    Page<Topic> findAllAvaliable(Pageable pageable, StatusEnum status);
}
