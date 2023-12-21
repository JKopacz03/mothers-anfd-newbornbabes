package com.kopacz.mothersandnewbornbabes;

import com.kopacz.mothersandnewbornbabes.kid.Kid;
import com.kopacz.mothersandnewbornbabes.kid.KidRepository;
import com.kopacz.mothersandnewbornbabes.mother.Mother;
import com.kopacz.mothersandnewbornbabes.mother.MotherRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.stream.Stream;

import static java.lang.Long.valueOf;

@Component
@RequiredArgsConstructor
public class DataBaseInit {

    private final MotherRepository motherRepository;
    private final KidRepository kidRepository;

    @PostConstruct
    private void postConstruct() throws IOException {
        Path pathOfMothers = Path.of("D:\\Pobrane\\mamy.txt");
        try (Stream<String> lines = Files.lines(pathOfMothers)) {
            lines.forEach(
                    s -> {
                        String[] mothersParameters = s.split(" ");
                        motherRepository.save(new Mother(
                                valueOf(mothersParameters[0]),
                                mothersParameters[1],
                                Integer.valueOf(mothersParameters[2])));
                    }
            );
        } catch (IOException exception) {
            throw exception;
        }

        Path pathOfKids = Path.of("D:\\Pobrane\\noworodki.txt");
        try (Stream<String> lines = Files.lines(pathOfKids)) {
            lines.forEach(
                    s -> {
                        String[] kidsParameters = s.split(" ");
                        kidRepository.save(new Kid(
                                valueOf(kidsParameters[0]),
                                kidsParameters[1].charAt(0),
                                kidsParameters[2],
                                LocalDate.parse(kidsParameters[3]),
                                Integer.valueOf(kidsParameters[4]),
                                Double.valueOf(kidsParameters[5]),
                                motherRepository.findById(valueOf(kidsParameters[6])).get()
                        ));


                    }
            );
        } catch (IOException exception) {
            throw exception;
        }
    }
}
