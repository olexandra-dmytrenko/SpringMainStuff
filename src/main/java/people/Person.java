package people;

/**
 * Created by Oleksandra_Dmytrenko on 11/10/2016.
 */

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Getter
@Setter
@ToString
@Component
@MeasureTime
public class Person implements Quoter {

    @InjectRandomInt(min = 5, max = 80)
    int age;
    private String name;

    public Person() {
        log.info("Phase 1. Person constructor runs " + this.toString());
    }

    /* @PostConstruct - чтоб имела силу, в контексте прописываем
    <bean class="org.springframework.context.annotation.CommonAnnotationBeanPostProcessor" /> или
            <context:annotation-config />
    */
    @PostConstruct
    public void init() {
        log.info("Phase 2. init() method called " + this.toString());
    }

    public String sayQuote() {
        final String words = "My name is " + (name == null ? "null" : name) + ". I'm " + age;
        log.info("{} quotes: {}.", (name == null ? "null" : name), words);
        return words;
    }
}
