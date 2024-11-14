package org.otus.microservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserJpaRepository repository;

    @Transactional
    public UserDto create(String login) {
        UserEntity newUser = new UserEntity(null, login);
        UserEntity savedUser = repository.saveAndFlush(newUser);
        return new UserDto(savedUser.getId(), savedUser.getLogin());
    }

    @Transactional
    public void update(Long id, String login) {
        UserEntity user = repository.findById(id).orElseThrow();
        user.setLogin(login);
        repository.saveAndFlush(user);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public UserDto findById(Long id) {
        return repository.findById(id).map(u -> new UserDto(u.getId(), u.getLogin())).orElseThrow();
    }

}
