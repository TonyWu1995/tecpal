package com.tecpal.tecpalinterview.api;

import com.tecpal.tecpalinterview.entity.BookEntity;
import com.tecpal.tecpalinterview.service.BookService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@ToString
@RequestMapping("/api/v1/")
public class BookApi {

    private final BookService bookService;

    public BookApi(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("book/{id}")
    public ResponseEntity<BookEntity> getBook(@PathVariable(value = "id") Long id) {
        return ResponseEntity.of(bookService.findById(id));
    }

    @GetMapping("books")
    public List<BookEntity> findAll() {
        return bookService.findAll();
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    static class CreateBookReq {

        private String title;

        private String description;

        private String author;

    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    static class CreateBookRes {

        boolean result;

        Long id;

    }

    @PostMapping("book")
    public ResponseEntity<CreateBookRes> create(@RequestBody CreateBookReq req) {
        log.info("create() req={}", req);
        long id = bookService.create(BookEntity.of(
                req.getTitle(),
                req.getDescription(),
                req.getAuthor()
        ));
        return ResponseEntity
                .ok(CreateBookRes.builder()
                        .id(id)
                        .result(true)
                        .build());
    }


    @DeleteMapping("book/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable(value = "id") Long id) {

        boolean result = bookService.delete(id);
        if (!result) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
