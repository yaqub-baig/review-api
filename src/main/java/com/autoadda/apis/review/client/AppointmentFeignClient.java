package com.autoadda.apis.review.client;

import com.autoadda.apis.review.response.AppointmentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "appointmentClient", url = "${appointment.service.url}")
public interface AppointmentFeignClient {
    @GetMapping("/appointments/{id}")
    AppointmentResponse getAppointmentById(@PathVariable("id") Integer id);
}
