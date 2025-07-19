package com.autoadda.apis.review.mapper;

import com.autoadda.apis.review.entity.GarageReview;
import com.autoadda.apis.review.request.GarageReviewRequest;
import com.autoadda.apis.review.response.GarageReviewResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-18T20:41:12-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.0.v20250526-2018, environment: Java 23.0.2 (Eclipse Adoptium)"
)
@Component
public class GarageReviewMapperImpl implements GarageReviewMapper {

    @Override
    public GarageReview toEntity(GarageReviewRequest request) {
        if ( request == null ) {
            return null;
        }

        GarageReview.GarageReviewBuilder garageReview = GarageReview.builder();

        garageReview.appointmentId( request.getAppointmentId() );
        garageReview.comments( request.getComments() );
        garageReview.customerId( request.getCustomerId() );
        garageReview.garageId( request.getGarageId() );
        garageReview.rating( request.getRating() );

        return garageReview.build();
    }

    @Override
    public GarageReviewResponse toDto(GarageReview review) {
        if ( review == null ) {
            return null;
        }

        GarageReviewResponse.GarageReviewResponseBuilder garageReviewResponse = GarageReviewResponse.builder();

        garageReviewResponse.appointmentId( review.getAppointmentId() );
        garageReviewResponse.comments( review.getComments() );
        garageReviewResponse.createdAt( review.getCreatedAt() );
        garageReviewResponse.customerId( review.getCustomerId() );
        garageReviewResponse.garageId( review.getGarageId() );
        garageReviewResponse.id( review.getId() );
        garageReviewResponse.rating( review.getRating() );

        return garageReviewResponse.build();
    }
}
