package com.kopacz.mothersandnewbornbabes.kid;

import com.kopacz.mothersandnewbornbabes.mother.Mother;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Kid {
    @Id
    private Long id;
    private Character sex;
    private String name;
    private LocalDate birthday;
    private Double weight;
    private Double height;
    @ManyToOne
    @JoinColumn(name = "mother")
    private Mother mother;
}
