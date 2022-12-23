package com.tecpal.tecpalinterview.repo;

import com.tecpal.tecpalinterview.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<BookEntity, Long> {
}
