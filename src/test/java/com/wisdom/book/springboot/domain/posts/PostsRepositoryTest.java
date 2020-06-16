package com.wisdom.book.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postPostsRepository;

    // Junit에서 단위 테스트가 끝날때 마다 수행되는 메소드를 지정한다.
    // 배포 전 전체 테스트 수행 시 테스트간 데이터 침범을 막기 위해 사용한다.
    @After
    public void cleanup(){
        postPostsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        // 테이블 posts에 insert/update쿼리를 실행한다.
        // id값이 있다면 update, 없다면 insert 쿼리가 실행된다.
        postPostsRepository.save(Posts.builder().title(title).content(content).author("ifyouwonder90@naver.com").build());

        //when
        //테이블 posts에 있는 모든 데이터를 조회 해오는 메소드이다.
        List<Posts> postsList = postPostsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }

    @Test
    public void BaseTimeEntity_등록(){

        // given
        LocalDateTime now = LocalDateTime.of(2020, 6, 12, 0, 0, 0);
        postPostsRepository.save(Posts.builder().title("title").content("content").author("author").build());

        // when
        List<Posts> postsList = postPostsRepository.findAll();

        // then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>> createDate="+posts.getCreatedDate()+", modifiedDate="+posts.getModifiedDate());


        assertThat(posts.getModifiedDate()).isAfter(now);
        assertThat(posts.getCreatedDate()).isAfter(now);

    }
}
