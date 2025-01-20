package com.forumhub.api.controller;

import com.forumhub.api.domain.topic.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicController {

    @Autowired
    private TopicService service;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid TopicRegistrationDataDTO data, UriComponentsBuilder uriComponentsBuilder) {
        TopicDetailDataDTO topicDetail = this.service.register(data);
        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topicDetail.id()).toUri();
        return ResponseEntity.created(uri).body(topicDetail);
    }

    @GetMapping
    public ResponseEntity<Page<TopicDetailDataDTO>> listAll(@PageableDefault(size = 10, sort = {"title"}) Pageable pageable) {
        Page page = this.service.listAll(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity listById(@PathVariable Long id) {
        TopicDetailDataDTO topicDetail = this.service.findById(id);
        return ResponseEntity.ok(topicDetail);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid TopicUpdateDataDTO data) {
        TopicDetailDataDTO topicDetail = this.service.update(data);
        return ResponseEntity.ok(topicDetail);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity delete(@RequestBody @Valid TopicDeleteDataDTO data) {
        this.service.delete(data);
        return ResponseEntity.noContent().build();
    }
}

