package com.wnsud9771.dto.dashboard.priceboard;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PriceboardDTO {
	private Long averageValue;
	private Long minValue;
	private Long maxValue;
	private LocalDateTime timestamp;
}
