package com.asaif.course_service.mapper;

import com.asaif.course_service.dto.RatingDto;
import com.asaif.course_service.model.Rating;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface RatingMapper {
     Rating dtoToRating(RatingDto ratingDto);
     RatingDto ratingToDto(Rating rating);
     List<RatingDto> ratingsToDtos(List<Rating> ratings);
        List<Rating> dtosToRatings(List<RatingDto> ratingDtos);
}
