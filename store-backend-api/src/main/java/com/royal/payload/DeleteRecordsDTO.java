package com.royal.payload;
/**
 *
 *@author Isaachome
 */

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class DeleteRecordsDTO {
	
	private List<Long> ids = new ArrayList<>();

}
