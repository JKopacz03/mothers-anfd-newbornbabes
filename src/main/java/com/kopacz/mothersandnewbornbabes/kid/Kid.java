package com.kopacz.mothersandnewbornbabes.kid;

import com.kopacz.mothersandnewbornbabes.mother.Mother;
import jakarta.persistence.*;
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
    private Integer weight;
    private Double height;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mother")
    private Mother mother;

    @Override
    public String toString() {
        return "Kid{" +
                "id=" + id +
                ", sex=" + sex +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", weight=" + weight +
                ", height=" + height +
                ", mother=" + mother.getName() +
                '}';
    }
}
