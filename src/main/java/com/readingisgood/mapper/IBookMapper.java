package com.readingisgood.mapper;


import com.readingisgood.dto.BookDTO;
import com.readingisgood.entity.Book;
import org.mapstruct.Mapper;

@Mapper(implementationName = "BookMapperImpl", componentModel = "spring")
public interface IBookMapper {
    Book toBook(BookDTO orderDTO);

    BookDTO toBookDTO(Book book);
}
