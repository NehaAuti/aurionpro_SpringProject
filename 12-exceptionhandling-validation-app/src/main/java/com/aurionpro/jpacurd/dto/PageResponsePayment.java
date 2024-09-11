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

public class PageResponsePayment <T> {
    
    private int totalPages;
    private Long totalElements;
    private int size;
    private List<T> content;
    private boolean isLastPage;
}