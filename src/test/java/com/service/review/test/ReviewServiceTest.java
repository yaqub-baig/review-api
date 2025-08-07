package com.service.review.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.service.review.client.AppointmentFeignClient;
import com.service.review.entity.GarageReview;
import com.service.review.mapper.GarageReviewMapper;
import com.service.review.repository.GarageReviewRepository;
import com.service.review.request.GarageReviewRequest;
import com.service.review.response.AppointmentDetails;
import com.service.review.response.AppointmentResponse;
import com.service.review.response.Customer;
import com.service.review.response.Garage;
import com.service.review.response.GarageReviewResponse;
import com.service.review.service.GarageReviewService;
import com.service.review.util.Status;

@SpringBootTest
class ReviewServiceTest {

	@InjectMocks
	private GarageReviewService garageReviewService;

	@Mock
	private GarageReviewRepository reviewRepository;

	@Mock
	private AppointmentFeignClient appointmentFeignClient;

	@Mock
	private GarageReviewMapper reviewMapper;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testSubmitReview_Success() {
		GarageReviewRequest request = new GarageReviewRequest();
		request.setGarageId(1);
		request.setAppointmentId(100);
		request.setCustomerId(200);
		request.setRating(5);
		request.setComments("Great service!");

		AppointmentDetails appointmentDetails = new AppointmentDetails();
		appointmentDetails.setStatus(Status.COMPLETED);
		Garage garage = Garage.builder().id(1).garageName("Test Garage").contactNumber("1234567890").build();
		Customer customer = Customer.builder().id(200).companyName("Name").emailId("a@A.com").build();
		appointmentDetails.setGarage(garage);
		appointmentDetails.setCustomer(customer);

		AppointmentResponse appointmentResponse = new AppointmentResponse();
		appointmentResponse.setAppointment(appointmentDetails);

		GarageReview savedReview = GarageReview.builder().garageId(1).appointmentId(100).customerId(200).rating(5)
				.comments("Great service!").createdAt(LocalDateTime.now()).build();

		GarageReviewResponse responseDto = new GarageReviewResponse();
		responseDto.setRating(5);
		responseDto.setComments("Great service!");

		when(appointmentFeignClient.getAppointmentById(100)).thenReturn(appointmentResponse);
		when(reviewRepository.save(any(GarageReview.class))).thenReturn(savedReview);
		when(reviewMapper.toDto(any(GarageReview.class))).thenReturn(responseDto);

		GarageReviewResponse response = garageReviewService.submitReview(request);

		assertNotNull(response);
		assertEquals(5, response.getRating());
		verify(reviewRepository).save(any(GarageReview.class));
	}

	@Test
	void testGetReviewByAppointment() {
		GarageReview review = GarageReview.builder().appointmentId(100).rating(4).comments("Nice").build();

		GarageReviewResponse dto = new GarageReviewResponse();
		dto.setRating(4);
		dto.setComments("Nice");

		when(reviewRepository.findByAppointmentId(100)).thenReturn(Optional.of(review));
		when(reviewMapper.toDto(review)).thenReturn(dto);

		Optional<GarageReviewResponse> result = garageReviewService.getReviewByAppointment(100);
		assertTrue(result.isPresent());
		assertEquals(4, result.get().getRating());
	}

	@Test
	void testGetReviewsByGarage() {
		GarageReview review1 = GarageReview.builder().garageId(1).rating(4).comments("Good").build();
		GarageReview review2 = GarageReview.builder().garageId(1).rating(5).comments("Excellent").build();

		GarageReviewResponse dto1 = new GarageReviewResponse();
		dto1.setRating(4);
		dto1.setComments("Good");

		GarageReviewResponse dto2 = new GarageReviewResponse();
		dto2.setRating(5);
		dto2.setComments("Excellent");

		when(reviewRepository.findByGarageId(1)).thenReturn(Arrays.asList(review1, review2));
		when(reviewMapper.toDto(review1)).thenReturn(dto1);
		when(reviewMapper.toDto(review2)).thenReturn(dto2);

		var results = garageReviewService.getReviewsByGarage(1);
		assertEquals(2, results.size());
	}

	@Test
	void testGetAverageRating() {
		when(reviewRepository.findAverageRatingByGarageId(1)).thenReturn(4.5);
		Double avg = garageReviewService.getAverageRating(1);
		assertEquals(4.5, avg);
	}

	@Test
	void testGetAverageRating_NullCase() {
		when(reviewRepository.findAverageRatingByGarageId(1)).thenReturn(null);
		Double avg = garageReviewService.getAverageRating(1);
		assertEquals(0.0, avg);
	}
}
