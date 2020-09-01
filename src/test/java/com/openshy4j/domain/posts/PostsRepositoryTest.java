package com.openshy4j.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {

        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                            .title(title)
                            .content(content)
                            .author("hanguk0726@gmail.com")
                            .build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }

    @Test
    public void BaseTimeEntity_등록() {

        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        postsRepository.save(Posts.builder()
                            .title("title4")
                            .content("content")
                            .author("author")
                            .build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);

        System.out.println(">>>>>>> createDate="+posts.
                getCreatedDate()+", modifiedDate="+posts.getModifiedDate());

        System.out.println("post is "+posts);

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);

    }

    @Test
    public void posts_롬복테스트() {
        String title = "테스트 게시글";
        String content = "테스트 본문";

        Long id = postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("hanguk0726@gmail.com")
                .build()).getId();

        System.out.println("id is" + id);

    }
}
