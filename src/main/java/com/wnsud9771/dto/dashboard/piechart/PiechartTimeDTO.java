package com.wnsud9771.dto.dashboard.piechart;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PiechartTimeDTO {
	private PiechartDTO piechartDTO;
	private LocalDateTime timestamp;
}
