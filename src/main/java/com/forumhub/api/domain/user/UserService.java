package com.forumhub.api.domain.user;

import com.forumhub.api.domain.profile.Profile;
import com.forumhub.api.domain.profile.ProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDetailDataDTO register(UserRegistrationDataDTO data) {
        Profile profile = this.profileRepository.findById(data.profileId())
                .orElseThrow(() -> new EntityNotFoundException("O perfil com id:%d não existe!".formatted(data.profileId())));

        String encryptedPassword = bCryptPasswordEncoder.encode(data.password());

        User user = new User(data, profile, encryptedPassword);
        this.userRepository.save(user);

        return new UserDetailDataDTO(user);
    }

    public Page<UserDetailDataDTO> listAll(Pageable pageable) {
        return this.userRepository.findAllByActiveTrue(pageable).map(UserDetailDataDTO::new);
    }

    public UserDetailDataDTO listById(Long id) {
        return new UserDetailDataDTO(this.userRepository.getReferenceById(id));
    }

    public void delete(Long id) {
        User user = this.userRepository.getReferenceById(id);
        user.setActive(false);
    }
}
