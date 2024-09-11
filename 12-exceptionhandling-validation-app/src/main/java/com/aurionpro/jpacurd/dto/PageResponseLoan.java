package com.aurionpro.jpacurd.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PageResponseLoan<Loan> {
    
    private int totalPages;
    private Long totalElements;
    private int size;
    private List<Loan> content;
    private boolean isLastPage;
}