package run;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import people.Quoter;

/**
 * Created by Oleksandra_Dmytrenko on 11/10/2016.
 * Inspired by Eugen Borisov https://www.youtube.com/watch?v=BmBr5diz8WA
 */
@Slf4j
public class Run {
    //    final static Logger log = LoggerFactory.getLogger(Run.class);

//    @Autowired
//    private Person person;

    public static void main(String[] args) throws InterruptedException {
        log.info("Initialize Spring Context");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/context.xml");

        log.info("Get Person bean");
        final Quoter person1 = context.getBean(Quoter.class);
//        final Quoter person1 = context.getBean(Quoter.class);

//        Run run = context.getBean(Run.class);
      /*  for(int i = 0; i < 10; ++i) {
            Thread.sleep(100);
            *//* чтоб выводить время выполнения метода создана аннотация @MeasureTime. Она активируется через проперти
            MeasureTimeCotroller.enabled. Поскольку оно имплементит MBean MeasureTimeCotrollerMBean, значение этой
            проперти можно менять в Java VisualVM, которая находится по пути jdk\bin\jvisualvm.exe. Надо запустить
            аппликейшин, зайти в Java VisualVM в Local найти приложение и во вкладке MBean выбрать timeManagement ->
            controller, куда передать значение true для измерения времени или наоборот. MBean - плагин, который можно
            установить через Tools.*//*
            log.info("Speak {} time {}", i, person1.sayQuote());
        }*/
    }
}
