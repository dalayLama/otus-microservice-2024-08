package org.otus.microservice.userprofile;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jdbc.core.JdbcAggregateOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final JdbcAggregateOperations jdbcAggregateOperations;

    @PutMapping("/profile")
    public ResponseEntity<Void> updateProfile(@AuthenticationPrincipal JwtPrincipal jwtPrincipal,
                                              @RequestBody UpdateProfileRequest request) {
        UserProfile userProfile = new UserProfile(jwtPrincipal.id(), request.nickname());
        boolean exists = jdbcAggregateOperations.existsById(jwtPrincipal.id(), UserProfile.class);
        if (exists) {
            jdbcAggregateOperations.update(userProfile);
        } else {
            jdbcAggregateOperations.insert(userProfile);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileDto> getUserProfile(@AuthenticationPrincipal JwtPrincipal principal) {
        UserProfile userProfile = jdbcAggregateOperations.findById(principal.id(), UserProfile.class);
        if (userProfile == null) {
            return ResponseEntity.ok(new UserProfileDto(principal));
        }
        return ResponseEntity.ok(new UserProfileDto(userProfile, principal));
    }

}
