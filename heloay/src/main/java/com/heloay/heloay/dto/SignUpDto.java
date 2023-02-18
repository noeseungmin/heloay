package com.heloay.heloay.dto;

import com.heloay.heloay.domain.UserAccount;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class SignUpDto {

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
