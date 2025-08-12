package com.autoadda.apis.review.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.autoadda.apis.review.entity.GarageReview;

@Repository
public interface GarageReviewRepository extends JpaRepository<GarageReview, Integer> {

	List<GarageReview> findByGarageId(Integer garageId);

	Optional<GarageReview> findByAppointmentId(Integer appointmentId);

	@Query("SELECT AVG(r.rating) FROM GarageReview r WHERE r.garageId = :garageId")
	Double findAverageRatingByGarageId(@Param("garageId") Integer garageId);
}
