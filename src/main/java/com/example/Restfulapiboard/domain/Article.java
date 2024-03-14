package com.example.Restfulapiboard.domain;

import javax.persistence.*;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@ToString(callSuper = true)
@SQLDelete(sql = "UPDATE article SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
@Entity
public class Article extends AuditingFields{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private Boolean deleted = Boolean.FALSE;

    private Integer commentsCount = 0;

    protected Article() {}

    private Article(Member member, String title, String content) {
        this.member = member;
        this.title = title;
        this.content = content;
    }

    public static Article of(Member member, String title, String content) {
        return new Article(member, title, content);
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public boolean isAuthor(Long memberId) {
        if (memberId == null) {
            return false;
        }
        return member.getId().equals(memberId);
    }

}
