package org.otus.microservice.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jdbc.core.JdbcAggregateOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.data.relational.core.query.Criteria.where;
import static org.springframework.data.relational.core.query.Query.query;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final JdbcAggregateOperations jdbcAggregateTemplate;

    @PostMapping("/sign-in")
    public ResponseEntity<String> signIn(@RequestBody UserRequest userRequest) {
        User user = findUserByCredentials(userRequest.login(), userRequest.password()).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(generateToken(user));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<User> singUp(@RequestBody UserRequest userRequest) {
        User newUser = new User(null, userRequest.login(), userRequest.password());
        User savedUser = jdbcAggregateTemplate.save(newUser);
        return ResponseEntity.ok(savedUser);
    }

    private String generateToken(User user) {
        return JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("login", user.getLogin())
                .sign(Algorithm.HMAC256("otus"));
    }

    private Optional<User> findUserByCredentials(String login, String password) {
        return jdbcAggregateTemplate.findOne(
                query(where("login").is(login).and("password").is(password)),
                User.class
        );
    }

}
