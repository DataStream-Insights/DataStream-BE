package com.wnsud9771.dto.filter.management;

import lombok.Data;

@Data
public class ResponseFilterManagementDTO {
	private String filtername;
	private String filtermanage_id;
	
	private FilterSetListDTO filterSetList;
}
