package com.jojodu.book.springboot.domain.posts;

import com.jojodu.book.springboot.web.dto.PostsResponseDto;
import com.jojodu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojodu.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){

        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" +id));
        posts.update(requestDto.getTitle(), requestDto.getContent());   //쿼리 없이 update 실행
        // JPA의 영속성 컨텍스트
        // 트랜잭션 안에서 데이터베스에서 데이터를 가져오면 데이터는 영속성 컨텍스트가 유지된 상태
        // 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영
        //즉, Entity 객체의 값만 변경하면 별도로 Update 쿼리를 날릴 필요가 없다는 것 = '더티 체킹'

        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당게시글이 없습니다. id="+ id ));

        return new PostsResponseDto(entity);
    }
}
