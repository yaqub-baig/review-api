package com.autoadda.apis.review.response;

import java.time.LocalDate;

import com.autoadda.apis.review.util.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentServices {

	private Integer id;

	private Status status;

	private Integer appointmentId;

	private LocalDate insert_tms;

	private PlatformService service;

	private GarageTechnician technician;

}
