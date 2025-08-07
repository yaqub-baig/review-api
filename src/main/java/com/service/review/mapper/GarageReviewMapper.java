package com.service.review.mapper;

import org.mapstruct.Mapper;

import com.service.review.entity.GarageReview;
import com.service.review.request.GarageReviewRequest;
import com.service.review.response.GarageReviewResponse;

@Mapper(componentModel = "spring")
public interface GarageReviewMapper {

    GarageReview toEntity(GarageReviewRequest request);

    GarageReviewResponse toDto(GarageReview review);
}

