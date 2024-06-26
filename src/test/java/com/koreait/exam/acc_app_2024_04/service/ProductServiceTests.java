package com.koreait.exam.acc_app_2024_04.service;

import com.koreait.exam.acc_app_2024_04.app.member.entity.Member;
import com.koreait.exam.acc_app_2024_04.app.member.repository.MemberRepository;
import com.koreait.exam.acc_app_2024_04.app.product.entity.Product;
import com.koreait.exam.acc_app_2024_04.app.product.service.ProductService;
import com.koreait.exam.acc_app_2024_04.app.song.entity.Song;
import com.koreait.exam.acc_app_2024_04.app.song.service.SongService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ProductServiceTests {
    @Autowired
    private SongService songService;

    @Autowired
    private ProductService productService;

    @Test
    @DisplayName("상품 등록")
    void t1() {
        Song song = songService.findById(1).get();

        Product product = productService.create(song, "제목 1", 1_900);

        assertThat(product).isNotNull();
        assertThat(product.getSubject()).isEqualTo("제목 1");
        assertThat(product.getPrice()).isEqualTo(1_900);
    }

    @Test
    @DisplayName("상품 수정")
    void t2() {
        Product product1 = productService.findById(1).get();

        productService.modify(product1, "제목 1-1", 3_800);

        assertThat(product1).isNotNull();
        assertThat(product1.getSubject()).isEqualTo("제목 1-1");
        assertThat(product1.getPrice()).isEqualTo(3_800);
    }
}
