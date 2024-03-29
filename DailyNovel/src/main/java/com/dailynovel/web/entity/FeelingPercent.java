package com.dailynovel.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FeelingPercent {
	
	private String name;
	private Integer total;
	private Integer frequency;
	private Integer percent;
}