package com.kopacz.mothersandnewbornbabes.kid.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class HighestKidResponse {
    private String name;
    private Double height;
}
