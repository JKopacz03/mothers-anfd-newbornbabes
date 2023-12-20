package com.kopacz.mothersandnewbornbabes.kid;

import com.kopacz.mothersandnewbornbabes.DayOfTheWeekWithBirthdays;
import com.kopacz.mothersandnewbornbabes.kid.dto.HighestKidResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KidService {

    private final KidRepository kidRepository;

    public void saveKid(Kid kid){
        kidRepository.save(kid);
    }

    public HighestKidResponse getHighestBoy(){
        List<Kid> kids = kidRepository.findAll();

        Kid kid = kids.stream()
                .filter(kid1 -> kid1.getSex() == 's')
                .max(Comparator.comparing(Kid::getHeight))
                .orElseThrow();

        return HighestKidResponse.builder()
                .name(kid.getName())
                .height(kid.getHeight())
                .build();
    }

    public HighestKidResponse getHighestGirl(){
        List<Kid> kids = kidRepository.findAll();

        Kid kid = kids.stream()
                .filter(kid1 -> kid1.getSex() == 'c')
                .max(Comparator.comparing(Kid::getHeight))
                .orElseThrow();

        return HighestKidResponse.builder()
                .name(kid.getName())
                .height(kid.getHeight())
                .build();
    }

    public DayOfTheWeekWithBirthdays getDayOfTheWeekWithMostBirths(){
        List<Kid> kids = kidRepository.findAll();

        List<DayOfWeek> dayOfWeek = kids.stream()
                .map(kid -> kid.getBirthday().getDayOfWeek())
                .toList();

        List<DayOfTheWeekWithBirthdays> dayOfTheWeekWithBirthdays =  new ArrayList<>();
        dayOfTheWeekWithBirthdays.add(new DayOfTheWeekWithBirthdays(DayOfWeek.MONDAY, Collections.frequency(dayOfWeek, DayOfWeek.MONDAY)));
        dayOfTheWeekWithBirthdays.add(new DayOfTheWeekWithBirthdays(DayOfWeek.TUESDAY, Collections.frequency(dayOfWeek, DayOfWeek.TUESDAY)));
        dayOfTheWeekWithBirthdays.add(new DayOfTheWeekWithBirthdays(DayOfWeek.WEDNESDAY, Collections.frequency(dayOfWeek, DayOfWeek.WEDNESDAY)));
        dayOfTheWeekWithBirthdays.add(new DayOfTheWeekWithBirthdays(DayOfWeek.THURSDAY, Collections.frequency(dayOfWeek, DayOfWeek.THURSDAY)));
        dayOfTheWeekWithBirthdays.add(new DayOfTheWeekWithBirthdays(DayOfWeek.FRIDAY, Collections.frequency(dayOfWeek, DayOfWeek.FRIDAY)));
        dayOfTheWeekWithBirthdays.add(new DayOfTheWeekWithBirthdays(DayOfWeek.SATURDAY, Collections.frequency(dayOfWeek, DayOfWeek.SATURDAY)));
        dayOfTheWeekWithBirthdays.add(new DayOfTheWeekWithBirthdays(DayOfWeek.SUNDAY, Collections.frequency(dayOfWeek, DayOfWeek.SUNDAY)));

        DayOfTheWeekWithBirthdays dayOfTheWeekWithMostBirths = dayOfTheWeekWithBirthdays.stream()
                .max(Comparator.comparing(DayOfTheWeekWithBirthdays::getAmountOfBirths))
                .get();

        return dayOfTheWeekWithMostBirths;
    }

}
