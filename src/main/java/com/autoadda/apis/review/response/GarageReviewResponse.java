package com.autoadda.apis.review.response;

import java.time.LocalDateTime;

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
public class GarageReviewResponse {
	private Integer id;
	private Integer garageId;
	private Integer appointmentId;
	private Integer customerId;
	private String comments;
	private int rating;
	private LocalDateTime createdAt;
}