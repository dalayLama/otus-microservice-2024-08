package org.otus.microservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Slf4j
@Service
public class UserService {

    private final UserJpaRepository repository;

    private final Random random = new Random();

    private final double sleepMinTime;

    private final double sleepMaxTime;

    public UserService(UserJpaRepository repository,
                       @Value("${sleepMinTime}") double sleepMinTime,
                       @Value("${sleepMaxTime}") double sleepMaxTime) {
        this.repository = repository;
        this.sleepMinTime = sleepMinTime;
        this.sleepMaxTime = sleepMaxTime;
    }

    @Transactional
    public synchronized UserDto create(String login) {
        sleep();
        UserEntity newUser = new UserEntity(null, login);
        UserEntity savedUser = repository.saveAndFlush(newUser);
        return new UserDto(savedUser.getId(), savedUser.getLogin());
    }

    @Transactional
    public synchronized void update(Long id, String login) {
        sleep();
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
        sleep();
        return repository.findById(id).map(u -> new UserDto(u.getId(), u.getLogin())).orElseThrow();
    }

    private void sleep() {
        try {
            int sleepTime = sleepTime();
            log.error("Sleeping for {} seconds", sleepTime);
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private int sleepTime() {
        return (int) ((random.nextDouble() * (sleepMaxTime - sleepMinTime) + sleepMinTime) * 1000);
    }

}
