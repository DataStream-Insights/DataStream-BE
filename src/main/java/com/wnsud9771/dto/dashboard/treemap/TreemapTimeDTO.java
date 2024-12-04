package com.wnsud9771.dto.dashboard.treemap;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
@Data
public class TreemapTimeDTO {
	private List<TreemapDTO> treemaps;
	private LocalDateTime timestamp;
}
