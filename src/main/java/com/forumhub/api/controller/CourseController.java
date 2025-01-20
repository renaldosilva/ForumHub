package com.forumhub.api.controller;

import com.forumhub.api.domain.course.CourseDetailDataDTO;
import com.forumhub.api.domain.course.CourseRegistrationDataDTO;
import com.forumhub.api.domain.course.CourseService;
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
@RequestMapping("/cursos")
public class CourseController {

    @Autowired
    private CourseService service;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid CourseRegistrationDataDTO data, UriComponentsBuilder uriComponentsBuilder) {
        CourseDetailDataDTO courseDetails = this.service.register(data);
        URI uri = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(courseDetails.id()).toUri();
        return ResponseEntity.created(uri).body(courseDetails);
    }

    @GetMapping
    public ResponseEntity<Page<CourseDetailDataDTO>> listAll(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        Page page = this.service.listAll(pageable);
        return ResponseEntity.ok(page);
    }
}
