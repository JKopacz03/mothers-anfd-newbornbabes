package com.kopacz.mothersandnewbornbabes.kid.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class HighestKidResponse {
    private String name;
    private Double height;
}
