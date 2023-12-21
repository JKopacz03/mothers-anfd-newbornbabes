package com.kopacz.mothersandnewbornbabes.mother;

import com.kopacz.mothersandnewbornbabes.kid.Kid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MotherService {

    private final MotherRepository motherRepository;

    public List<String> getMothersWithChildWeightsOver4000(){
        List<Mother> mothers = motherRepository.findAll();

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

    public List<Mother> getMothersWithTwins(){
        List<Mother> mothers = motherRepository.findAll();

        List<Mother> mothersWithTwins = new ArrayList<>();

        mothers.stream()
                .forEach(mother -> {
                    List<Kid> listOfKids = mother.getKids();
                    List<Kid> listOfKidsWithTwins = listOfKids.stream()
                            .collect(Collectors.groupingBy(Kid::getBirthday))
                            .entrySet()
                            .stream()
                            .filter(localDateListEntry -> localDateListEntry.getValue().size() > 1)
                            .flatMap(localDateListEntry -> localDateListEntry.getValue().stream())
                            .toList();
                    if(!listOfKidsWithTwins.isEmpty()){
                        mothersWithTwins.add(mother);
                    }
                });

        return mothersWithTwins;
    }

}
