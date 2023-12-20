package com.kopacz.mothersandnewbornbabes.mother;

import com.kopacz.mothersandnewbornbabes.kid.Kid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MotherService {

    private final MotherRepository motherRepository;

    public void saveMother(Mother mother){
        motherRepository.save(mother);
    }

    public List<String> getMothersWithChildWeightsOver4000(){
        final List<Mother> mothers = motherRepository.findAll();

        List<Mother> mothersBelow25 = mothers.stream()
                .filter(mother -> mother.getAge() < 25)
                .toList();

        List<Mother> mothersWithKidsOver4000 = new ArrayList<>();

        mothersBelow25.stream().forEach(mother -> {
            List<Kid> listOfKids = mother.getKids();
            List<Kid> listOfKidsOver4000 = listOfKids.stream()
                    .filter(kid -> kid.getWeight() > 4000)
                    .toList();
            if(!listOfKidsOver4000.isEmpty()){
                mothersWithKidsOver4000.add(mother);
            }
        });

        return mothersWithKidsOver4000.stream()
                .map(Mother::getName)
                .toList();
    }

}
