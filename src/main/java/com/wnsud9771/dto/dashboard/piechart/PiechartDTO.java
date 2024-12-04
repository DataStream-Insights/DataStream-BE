package com.wnsud9771.dto.dashboard.piechart;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PiechartDTO {
	private Long success;
	private Long failure;
	private LocalDateTime timestamp;
}
