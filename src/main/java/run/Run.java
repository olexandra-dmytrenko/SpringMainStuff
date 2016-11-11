package run;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import people.Person;

/**
 * Created by Oleksandra_Dmytrenko on 11/10/2016.
 */
@Slf4j
public class Run {
//    final static Logger log = LoggerFactory.getLogger(Run.class);

    public static void main(String[] args) {
        log.info("Initialize Spring Context");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/context.xml");

        log.info("Get Person bean");
        final Person person1 = context.getBean(Person.class);
        log.info("Speak {}", person1.sayQuote());
    }
}
