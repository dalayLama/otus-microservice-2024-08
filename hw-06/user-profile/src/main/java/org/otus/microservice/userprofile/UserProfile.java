package org.otus.microservice.userprofile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("users_profiles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserProfile {

    @Id
    private Long id;

    @Column("nickname")
    private String nickname;

}
