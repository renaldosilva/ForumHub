package com.forumhub.api.controller;

import com.forumhub.api.domain.user.User;
import com.forumhub.api.domain.user.UserDetailDataDTO;
import com.forumhub.api.domain.user.UserRegistrationDataDTO;
import com.forumhub.api.domain.user.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    UserService service;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid UserRegistrationDataDTO data, UriComponentsBuilder uriComponentsBuilder) {
        UserDetailDataDTO userDetails = this.service.register(data);
        URI uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(userDetails.id()).toUri();
        return ResponseEntity.created(uri).body(userDetails);
    }

    @GetMapping
    public ResponseEntity<Page<UserDetailDataDTO>> listAll(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        Page<UserDetailDataDTO> page = this.service.listAll(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity listById(@PathVariable Long id) {
        UserDetailDataDTO user = this.service.listById(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
