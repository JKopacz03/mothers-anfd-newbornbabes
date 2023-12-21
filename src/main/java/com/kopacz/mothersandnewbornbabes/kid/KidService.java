package com.kopacz.mothersandnewbornbabes.kid;

import com.kopacz.mothersandnewbornbabes.kid.dto.DayOfTheWeekWithBirthdaysResponse;
import com.kopacz.mothersandnewbornbabes.kid.dto.GirlsWithSameNameLikeMotherResponse;
import com.kopacz.mothersandnewbornbabes.kid.dto.HighestKidResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.*;

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

    public DayOfTheWeekWithBirthdaysResponse getDayOfTheWeekWithMostBirths(){
        List<Kid> kids = kidRepository.findAll();

        List<DayOfWeek> dayOfWeek = kids.stream()
                .map(kid -> kid.getBirthday().getDayOfWeek())
                .toList();

        List<DayOfTheWeekWithBirthdaysResponse> dayOfTheWeekWithBirthdayResponses =  new ArrayList<>();
        dayOfTheWeekWithBirthdayResponses.add(new DayOfTheWeekWithBirthdaysResponse(DayOfWeek.MONDAY, Collections.frequency(dayOfWeek, DayOfWeek.MONDAY)));
        dayOfTheWeekWithBirthdayResponses.add(new DayOfTheWeekWithBirthdaysResponse(DayOfWeek.TUESDAY, Collections.frequency(dayOfWeek, DayOfWeek.TUESDAY)));
        dayOfTheWeekWithBirthdayResponses.add(new DayOfTheWeekWithBirthdaysResponse(DayOfWeek.WEDNESDAY, Collections.frequency(dayOfWeek, DayOfWeek.WEDNESDAY)));
        dayOfTheWeekWithBirthdayResponses.add(new DayOfTheWeekWithBirthdaysResponse(DayOfWeek.THURSDAY, Collections.frequency(dayOfWeek, DayOfWeek.THURSDAY)));
        dayOfTheWeekWithBirthdayResponses.add(new DayOfTheWeekWithBirthdaysResponse(DayOfWeek.FRIDAY, Collections.frequency(dayOfWeek, DayOfWeek.FRIDAY)));
        dayOfTheWeekWithBirthdayResponses.add(new DayOfTheWeekWithBirthdaysResponse(DayOfWeek.SATURDAY, Collections.frequency(dayOfWeek, DayOfWeek.SATURDAY)));
        dayOfTheWeekWithBirthdayResponses.add(new DayOfTheWeekWithBirthdaysResponse(DayOfWeek.SUNDAY, Collections.frequency(dayOfWeek, DayOfWeek.SUNDAY)));

        DayOfTheWeekWithBirthdaysResponse dayOfTheWeekWithMostBirths = dayOfTheWeekWithBirthdayResponses.stream()
                .max(Comparator.comparing(DayOfTheWeekWithBirthdaysResponse::getAmountOfBirths))
                .get();

        return dayOfTheWeekWithMostBirths;
    }

    public List<GirlsWithSameNameLikeMotherResponse> getGirlsWithSameNameLikeMother(){
        List<Kid> kids = kidRepository.findAll();

        List<GirlsWithSameNameLikeMotherResponse> girlsWithSameNameLikeMother = kids.stream()
                .filter(kid1 -> kid1.getSex() == 'c')
                .filter(kid1 -> kid1.getMother().getName().equalsIgnoreCase(kid1.getName()))
                .map(kid -> new GirlsWithSameNameLikeMotherResponse(kid.getName(), kid.getBirthday()))
                .toList();

        return girlsWithSameNameLikeMother;
    }

}
