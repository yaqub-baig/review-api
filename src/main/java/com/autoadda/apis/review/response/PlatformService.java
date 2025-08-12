package com.autoadda.apis.review.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlatformService {
	private Integer id;

	private String service;

	private String description;

	private Integer categoryId;

	private Date insertTms;

}
