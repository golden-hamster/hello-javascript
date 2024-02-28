package com.example.Restfulapiboard.domain;

import javax.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@ToString(callSuper = true)
@Entity
public class Article extends AuditingFields{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    protected Article() {}

    private Article(Member member, String title, String content) {
        this.member = member;
        this.title = title;
        this.content = content;
    }

    public static Article of(Member member, String title, String content) {
        return new Article(member, title, content);
    }

}
