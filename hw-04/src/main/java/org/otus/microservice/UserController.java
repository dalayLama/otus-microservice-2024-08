package org.otus.microservice;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{id}")
    public UserDto getUser(@PathVariable long id) {
        return userService.findById(id);
    }

    @PostMapping("/users")
    public UserDto createUser(@RequestBody UserDataRequest request) {
        return userService.create(request.login());
    }

    @PutMapping("/users/{id}")
    public void updateUser(@PathVariable long id, @RequestBody UserDataRequest request) {
        userService.update(id, request.login());
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.delete(id);
    }

}
