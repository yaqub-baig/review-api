package com.autoadda.apis.review.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GarageReviewRequest {
    private Integer garageId;
    private Integer customerId;
    private Integer appointmentId;
    private String comments;
    private int rating;
}
