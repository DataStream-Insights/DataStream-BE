package com.wnsud9771.dto.dashboard.priceboard;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PriceboardTimeDTO {
	private PriceboardDTO priceboardDTO;
	private LocalDateTime timestamp;
}
