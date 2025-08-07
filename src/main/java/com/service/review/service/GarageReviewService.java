package com.service.review.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.service.review.client.AppointmentFeignClient;
import com.service.review.entity.GarageReview;
import com.service.review.mapper.GarageReviewMapper;
import com.service.review.repository.GarageReviewRepository;
import com.service.review.request.GarageReviewRequest;
import com.service.review.response.AppointmentDetails;
import com.service.review.response.AppointmentResponse;
import com.service.review.response.GarageReviewResponse;
import com.service.review.util.Status;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GarageReviewService {

	private final GarageReviewRepository reviewRepository;
	private final AppointmentFeignClient appointmentFeignClient;
	private final GarageReviewMapper reviewMapper;

	public GarageReviewResponse submitReview(GarageReviewRequest request) {
		AppointmentResponse appointmentResponse = appointmentFeignClient.getAppointmentById(request.getAppointmentId());

		if (appointmentResponse == null || appointmentResponse.getAppointment() == null) {
			throw new IllegalArgumentException("Invalid appointment ID");
		}

		AppointmentDetails appointmentDetails = appointmentResponse.getAppointment();
		if (appointmentDetails.getStatus() != Status.COMPLETED) {
			throw new IllegalArgumentException("Appointment must be completed to submit a review");
		}
		if (!appointmentDetails.getGarage().getId().equals(request.getGarageId())) {
			throw new IllegalArgumentException("Garage ID mismatch with appointment");
		}
		if (!appointmentDetails.getCustomer().getId().equals(request.getCustomerId())) {
			throw new IllegalArgumentException("Customer ID mismatch with appointment");
		}
		if (request.getRating() < 1 || request.getRating() > 5) {
			throw new IllegalArgumentException("Rating must be between 1 and 5");
		}
		GarageReview review = GarageReview.builder().garageId(request.getGarageId())
				.appointmentId(request.getAppointmentId()).customerId(request.getCustomerId())
				.rating(request.getRating()).comments(request.getComments()).createdAt(LocalDateTime.now()).build();

		reviewRepository.save(review);
		return reviewMapper.toDto(review);
	}

	public Optional<GarageReviewResponse> getReviewByAppointment(Integer appointmentId) {
		return reviewRepository.findByAppointmentId(appointmentId).map(reviewMapper::toDto);
	}

	public List<GarageReviewResponse> getReviewsByGarage(Integer garageId) {
		return reviewRepository.findByGarageId(garageId).stream().map(reviewMapper::toDto).collect(Collectors.toList());
	}

	public Double getAverageRating(Integer garageId) {
		return Optional.ofNullable(reviewRepository.findAverageRatingByGarageId(garageId)).orElse(0.0);
	}
}
