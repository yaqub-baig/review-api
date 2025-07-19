package com.autoadda.apis.review.mapper;

import org.mapstruct.Mapper;

import com.autoadda.apis.review.entity.GarageReview;
import com.autoadda.apis.review.request.GarageReviewRequest;
import com.autoadda.apis.review.response.GarageReviewResponse;

@Mapper(componentModel = "spring")
public interface GarageReviewMapper {

    GarageReview toEntity(GarageReviewRequest request);

    GarageReviewResponse toDto(GarageReview review);
}

