package com.example.Restfulapiboard;

import com.example.Restfulapiboard.domain.Article;
import com.example.Restfulapiboard.domain.Comment;
import com.example.Restfulapiboard.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final EntityManager em;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void init(){
        for (int i = 1; i <= 3; i++) {
            Member member = Member.of("member" + i, "{noop}123");
            em.persist(member);

            //글 생성
            for (int j = 1; j <= 500; j++) {
                Article article = Article.of(member, "Test title" + j, "Content is...");
                article.setCreatedBy(member.getUsername());
                em.persist(article);

                //댓글 생성
                for(int k = 1; k <= 5; k++){
                    Comment comment = Comment.of("Comment is...", member, article);
                    comment.setCreatedBy(member.getUsername());
                    em.persist(comment);
                }
            }
        }
    }
}
