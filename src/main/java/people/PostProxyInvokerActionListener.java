package people;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.lang.reflect.Method;

/**
 * Created by Oleksandra_Dmytrenko on 11/16/2016.
 */
public class PostProxyInvokerActionListener implements ApplicationListener<ContextRefreshedEvent>{

    @Autowired
    ConfigurableListableBeanFactory listableBeanFactory;

    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        final ApplicationContext context = contextRefreshedEvent.getApplicationContext();
        final String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanName : beanDefinitionNames) {
            // нельзя брать бин из контеста, только из фабрики, поскольку в конетексте он после прокси и имеет другое
            // имя. Примером Person будет Proxy$11. Наш бин мы там не найдем.
            final BeanDefinition beanDefinition = listableBeanFactory.getBeanDefinition(beanName);
            final String originalClassName = beanDefinition.getBeanClassName();
            try {
                //Достаем Proxy$11 класс, который содержит те же методы и те же аннотации, как и проксированный класс
                final Class<?> originalClass = Class.forName(originalClassName);
                final Method[] methods = originalClass.getMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(PostProxy.class)){
                        final Object bean = context.getBean(beanName);
//                        находим и вызываем тот же метод у проксированного класса
                        final Method curMethod = bean.getClass().getMethod(method.getName(), method.getParameterTypes());
                        curMethod.invoke(bean);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
