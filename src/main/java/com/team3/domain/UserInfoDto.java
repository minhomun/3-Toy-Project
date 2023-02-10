package com.team3.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Builder
@Data
public class UserInfoDto{
    private String id;
    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password;
    @NotBlank(message = "이름을 입력해주세요.")
    private String name;
    @NotEmpty(message = "휴대폰 번호를 입력해주세요.")
    private String phoneNumber;

    //@Enumerated(EnumType.STRING)
    private String role = Role.ROLE_USER.getDescription();

    public UserInfoDto(String id, String password, String name, String phoneNumber, String role) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.role = Role.ROLE_USER.getDescription();
    }
}
