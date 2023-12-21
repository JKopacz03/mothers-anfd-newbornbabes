package com.kopacz.mothersandnewbornbabes.kid.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class GirlsWithSameNameLikeMotherResponse {
    private String name;
    private LocalDate birthday;
}
