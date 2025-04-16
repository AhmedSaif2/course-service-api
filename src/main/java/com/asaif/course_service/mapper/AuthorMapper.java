package com.asaif.course_service.mapper;

import org.mapstruct.Mapper;
import com.asaif.course_service.dto.AuthorDto;
import com.asaif.course_service.model.Author;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
     AuthorDto authorToDto(Author author);
     Author dtoToAuthor(AuthorDto authorDto);
     List<AuthorDto> authorsToDtos(List<Author> authors);
}
