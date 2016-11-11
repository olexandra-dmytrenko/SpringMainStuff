package people;

/**
 * Created by Oleksandra_Dmytrenko on 11/10/2016.
 */

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Setter
@ToString
@Component
public class Person implements Quoter {

    @InjectRandomInt(min = 5, max=80)
    int age;
    private String name;

    public String sayQuote() {
        final String words = "My name is " + name+ ". I'm " + age;
        log.info("{} quotes: {}.", name, words);
        return words;
    }
}
