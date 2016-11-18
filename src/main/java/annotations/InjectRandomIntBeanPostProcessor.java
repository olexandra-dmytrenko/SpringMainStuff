package annotations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Random;

/**
 * Created by Oleksandra_Dmytrenko on 11/11/2016.
 * Set up bean before it gets into container
 */
@Slf4j
public class InjectRandomIntBeanPostProcessor implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object bean, String s) throws BeansException {
        log.info("InjectRandomIntBeanPostProcessor.postProcessBeforeInitialization starts" );
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            InjectRandomInt annotation = field.getAnnotation(InjectRandomInt.class);
            if (annotation != null) {
                int min = annotation.min();
                int max = annotation.max();
                Random random = new Random();
                field.setAccessible(true);
                final int randomValue = min + random.nextInt(max - min);
                ReflectionUtils.setField(field, bean, randomValue);
//            field.set(bean, min + random.nextInt(max - min));
            }
        }
        log.info("InjectRandomIntBeanPostProcessor.postProcessBeforeInitialization ends" );
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String s) throws BeansException {
        return bean;
    }
}
