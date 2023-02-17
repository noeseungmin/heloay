package com.heloay.heloay.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAccount {

    @Id
    @Column(unique = true)
    private String username;

    @OneToMany(mappedBy = "userAccount")
    private List<Movie> movies;

    @OneToMany(mappedBy = "userAccount")
    private List<Review> reviews;

    private String password;

    private String name;
    @Column(unique = true)
    private String email;
    private String role;

    @Builder
    public UserAccount(String username, String password, String name, String email, String role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
    }
}
