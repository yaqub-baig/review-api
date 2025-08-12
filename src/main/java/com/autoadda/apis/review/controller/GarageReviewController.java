package com.autoadda.apis.review.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autoadda.apis.review.request.GarageReviewRequest;
import com.autoadda.apis.review.response.GarageReviewResponse;
import com.autoadda.apis.review.service.GarageReviewService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class GarageReviewController {

	private final GarageReviewService reviewService;

	// Submit a review
	@PostMapping
	public ResponseEntity<GarageReviewResponse> createReview(@RequestBody @Valid GarageReviewRequest request) {
		GarageReviewResponse response = reviewService.submitReview(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	// 🔍 Get all reviews by garage ID
	@GetMapping("/garage/{garageId}")
	public ResponseEntity<List<GarageReviewResponse>> getReviewsByGarage(@PathVariable Integer garageId) {
		return ResponseEntity.ok(reviewService.getReviewsByGarage(garageId));
	}

	// ⭐ Get average rating for a garage
	@GetMapping("/garage/{garageId}/rating")
	public ResponseEntity<Double> getAverageRating(@PathVariable Integer garageId) {
		return ResponseEntity.ok(reviewService.getAverageRating(garageId));
	}

	// 🔍 Get review by appointment
	@GetMapping("/appointment/{appointmentId}")
	public ResponseEntity<GarageReviewResponse> getReviewByAppointment(@PathVariable Integer appointmentId) {
		return reviewService.getReviewByAppointment(appointmentId).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
}
