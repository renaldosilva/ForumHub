package com.forumhub.api.domain.user;

import com.forumhub.api.domain.profile.Profile;

public record UserDetailDataDTO(Long id, String name, String email, Profile profile) {

    public UserDetailDataDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getProfile());
    }
}
