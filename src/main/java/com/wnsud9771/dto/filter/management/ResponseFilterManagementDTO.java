package com.wnsud9771.dto.filter.management;

import java.util.List;

import lombok.Data;

@Data
public class ResponseFilterManagementDTO {
	private String filtername;
	private String filter;
	
	private List<FilterSetListDTO> filterSetLists;
}
