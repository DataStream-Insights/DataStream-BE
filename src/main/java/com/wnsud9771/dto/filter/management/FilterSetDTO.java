package com.wnsud9771.dto.filter.management;

import com.wnsud9771.dto.filter.FiltervalueDTO;
import com.wnsud9771.dto.filter.OperationDTO;
import com.wnsud9771.dto.format.FormatItemDTO;

import lombok.Data;

@Data
public class FilterSetDTO {
	private String andor;
	
	private FiltervalueDTO filtervalue;
	private OperationDTO operation;
	private Long responseItemid;
}
