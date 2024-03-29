package com.dailynovel.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Feeling {
	
	private Integer id;
	private String name;
	private String description;
	private int frequency;
	private String img;
}