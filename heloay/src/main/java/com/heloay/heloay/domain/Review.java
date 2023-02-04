package com.heloay.heloay.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MoviesReview {

    @Id @GeneratedValue
    private Long id;

    @OneToMany()

    private String content;
    private Integer voleCount;
    private String score;

}
