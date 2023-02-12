package com.heloay.heloay.dto;

import com.heloay.heloay.domain.Movie;
import com.heloay.heloay.domain.Review;
import com.heloay.heloay.domain.UserAccount;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Data
@NoArgsConstructor
public class SignUpDto {

    private Long id;

    @Size(min = 3, max = 25)
    @NotEmpty(message = "사용자 ID는 필수항목 입니다.")
    private String username;

    @NotEmpty(message = "비밀번호는 필수항목입니다.")

    private String password;
    @NotEmpty(message = "사용자 이름은 필수항목 입니다.")

    private String name;
    @NotEmpty(message = "이메일은 필수 입니다.")
    @Email
    private String email;
    private String role;

    @Builder
    public SignUpDto(String username, String password, String name, String email, String role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public UserAccount toEntity(){
        return UserAccount.builder()
                .username(username)
                .password(new BCryptPasswordEncoder().encode(password))
                .name(name)
                .email(email)
                .role(role)
                .build();
    }
}
