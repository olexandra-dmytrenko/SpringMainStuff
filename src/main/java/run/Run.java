package run;

import dao.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Oleksandra_Dmytrenko on 11/10/2016.
 * Enspired by https://www.youtube.com/watch?v=BmBr5diz8WA
 */
public class Run {
    final static Logger log = LoggerFactory.getLogger(Run.class);

    public static void main(String[] args) {
        log.info("Initialize Spring Context");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/context.xml");
        System.out.println(context.getBean(Person.class).toString());
    }
}
