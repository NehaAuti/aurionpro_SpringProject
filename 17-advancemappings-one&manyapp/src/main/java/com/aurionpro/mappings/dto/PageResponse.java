package com.aurionpro.mappings.dto;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PageResponse<Student> {
	
	private int totalPages;
	private Long totalElements;
	private int size;
	private List<Student> content;
	private boolean isLastPage;

}
