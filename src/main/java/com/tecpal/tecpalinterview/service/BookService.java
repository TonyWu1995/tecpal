package com.tecpal.tecpalinterview.service;

import com.tecpal.tecpalinterview.api.BookApi;
import com.tecpal.tecpalinterview.entity.BookEntity;
import com.tecpal.tecpalinterview.repo.BookRepo;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@ToString
public class BookService {

    private final BookRepo bookRepo;

    public BookService(final BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public Optional<BookEntity> findById(long id) {
        return bookRepo.findById(id);
    }

    public Long create(BookEntity bookEntity) {
        return bookRepo.save(bookEntity)
                .getId();
    }

    @Transactional
    public boolean delete(long id){
        if(bookRepo.existsById(id)){
            bookRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public List<BookEntity> findAll() {
        return bookRepo.findAll();
    }



}
