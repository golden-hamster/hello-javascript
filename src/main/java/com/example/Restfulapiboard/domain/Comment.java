package com.example.Restfulapiboard.domain;

import javax.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString(callSuper = true)
@Entity
public class Comment extends AuditingFields{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    protected Comment() {}

    private Comment(String content, Member member, Article article) {
        this.content = content;
        this.member = member;
        this.article = article;
    }

    public static Comment of(String content, Member member, Article article) {
        return new Comment(content, member, article);
    }
}
