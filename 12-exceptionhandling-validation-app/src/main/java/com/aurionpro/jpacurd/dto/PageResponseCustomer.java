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
public class PageResponseCustomer<Customer> {
    
    private int totalPages;
    private Long totalElements;
    private int size;
    private List<Customer> content;
    private boolean isLastPage;
}