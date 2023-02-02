package com.heloay.heloay.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    public Member(Long id, String username) {
        this.id = id;
        this.username = username;
    }

}
