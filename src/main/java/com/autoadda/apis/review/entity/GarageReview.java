package com.autoadda.apis.review.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "garage_reviews", uniqueConstraints = { @UniqueConstraint(columnNames = { "appointmentId" }) })
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GarageReview {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer garageId;

	private Integer customerId;

	private Integer appointmentId;

	@Column(length = 1000)
	private String comments;

	private int rating;

	private LocalDateTime createdAt;
}
