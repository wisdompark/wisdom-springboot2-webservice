package com.wisdom.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // 롬복의 어노테이션
@Entity // JPA의 어노테이션, 테이블과 링크 될 클래스 임을 나타낸다.
@NoArgsConstructor  // 롬복의 어노테이션
public class Posts extends BaseTimeEntity {

    @Id // 해당 테이블의 PK필드를 나타낸다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)  // 테이블의 컬럼을 나타내며 굳이 선어하지 않아도 된다.
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;

    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
