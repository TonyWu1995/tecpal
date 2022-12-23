package com.tecpal.tecpalinterview.service;

import com.tecpal.tecpalinterview.entity.BookEntity;
import com.tecpal.tecpalinterview.repo.BookRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BookServiceTest {

    private BookService bookService;

    @Autowired
    BookRepo repo;

    @BeforeEach
    void init() {
        bookService = new BookService(repo);
    }

    @AfterEach
    void clean() {
        repo.deleteAll();
    }


    @Test
    void success_findById() {
        BookEntity entity = repo.save(BookEntity.builder()
                .title("book title")
                .description("des")
                .author("author")
                .publishDate(LocalDate.now())
                .build());

        Optional<BookEntity> bookOpt = bookService.findById(entity.getId());

        assertThat(bookOpt).isNotEmpty();
        assertThat(bookOpt)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(bookOpt);
    }

    @Test
    void failed_findById() {
        Optional<BookEntity> bookOpt = bookService.findById(1L);
        assertThat(bookOpt).isEmpty();
    }

    @Test
    void success_findAll() {
        BookEntity entity1 = repo.save(BookEntity.builder()
                .title("book title")
                .description("des")
                .author("author")
                .publishDate(LocalDate.now())
                .build());
        BookEntity entity2 = repo.save(BookEntity.builder()
                .title("book title")
                .description("des")
                .author("author")
                .publishDate(LocalDate.now())
                .build());

        List<BookEntity> res = bookService.findAll();

        assertThat(res).hasSize(2);
    }

    @Test
    void failed_findAll() {
        List<BookEntity> res = bookService.findAll();
        assertThat(res).hasSize(0);
    }

    @Test
    void success_delete() {
        BookEntity entity = repo.save(BookEntity.builder()
                .title("book title")
                .description("des")
                .author("author")
                .publishDate(LocalDate.now())
                .build());
        boolean result = bookService.delete(entity.getId());
        assertThat(result).isTrue();
    }

    @Test
    void failed_delete() {
        boolean result = bookService.delete(1L);
        assertThat(result).isFalse();
    }

    @Test
    void success_create(){
        BookEntity entity = repo.save(BookEntity.builder()
                .title("book title")
                .description("des")
                .author("author")
                .publishDate(LocalDate.now())
                .build());

        Long id = bookService.create(entity);
        assertThat(id).isGreaterThan(0L);

        assertThat(bookService.findById(id)).isNotEmpty();
    }
}