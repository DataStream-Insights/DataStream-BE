package com.wnsud9771.dto.filter.management.search;

import com.wnsud9771.dto.filter.FiltervalueDTO;
import com.wnsud9771.dto.filter.OperationDTO;

import lombok.Data;

@Data
public class SearchFilterSetDTO {
	private String andor;

	private FiltervalueDTO filtervalue;
	private OperationDTO operation;
	private String item_alias;
}
