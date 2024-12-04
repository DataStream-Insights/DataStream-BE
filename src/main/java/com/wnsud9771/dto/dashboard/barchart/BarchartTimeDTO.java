package com.wnsud9771.dto.dashboard.barchart;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class BarchartTimeDTO {
	private List<BarchartDTO> barchartDTOs;
	private LocalDateTime timestamp;
}
