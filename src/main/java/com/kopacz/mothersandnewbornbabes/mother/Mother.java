package com.kopacz.mothersandnewbornbabes.mother;

import com.kopacz.mothersandnewbornbabes.kid.Kid;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Mother {
    @Id
    private Long id;
    private String name;
    private Integer age;
    @OneToMany(mappedBy = "mother")
    private List<Kid> kids;

    public Mother(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}

