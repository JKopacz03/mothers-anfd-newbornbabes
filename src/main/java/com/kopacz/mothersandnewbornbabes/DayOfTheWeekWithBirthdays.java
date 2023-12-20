package com.kopacz.mothersandnewbornbabes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.DayOfWeek;

@Data
@AllArgsConstructor
public class DayOfTheWeekWithBirthdays {
    private DayOfWeek dayOfWeek;
    private Integer amountOfBirths;
}
