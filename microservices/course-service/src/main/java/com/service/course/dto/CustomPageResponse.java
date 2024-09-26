package com.service.course.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomPageResponse<T>
{
    private  int pageNumber;
    private int pageSize;
    private long totalElements;
    private  int totalPages;
    private boolean isLast;
    private List<T> content;
}
