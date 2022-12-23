package com.tecpal.tecpalinterview.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "book_table")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String title;

    private String description;

    private String author;

    private LocalDate publishDate;


    public static BookEntity of(String title,
                                String description,
                                String author) {
        return BookEntity.builder()
                .title(title)
                .description(description)
                .author(author)
                .publishDate(LocalDate.now())
                .build();
    }
}
