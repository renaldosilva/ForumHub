package com.forumhub.api.controller;

import com.forumhub.api.domain.user.AuthenticationDataDTO;
import com.forumhub.api.domain.user.User;
import com.forumhub.api.infra.security.TokenDataJWTDTO;
import com.forumhub.api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthenticationDataDTO dados) {
        var autenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.password());
        var authentication = authenticationManager.authenticate(autenticationToken);

        var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenDataJWTDTO(tokenJWT));
    }
}
