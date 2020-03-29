package com.jojodu.book.springboot.domain.posts;

import com.jojodu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor  //기본생성자 자동 추가
@Entity //테이블과 링크될 클래스임을 나타냄, 절대 setter 메소드를 만들지 않는다
public class Posts extends BaseTimeEntity {

    @Id //해당 테이블의 PK필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK생성 규칙
    private Long id;

    @Column(length = 500, nullable = false) //굳이 선언하지 않더라고 모든 클래스의 필드는 컬럼이됨.
    private String title;

    @Column(columnDefinition = "TEXT" , nullable = false)
    private String content;

    private String author;

    @Builder    //빌더 패턴 클래스 생성
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
