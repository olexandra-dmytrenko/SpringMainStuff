package people;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Oleksandra_Dmytrenko on 11/15/2016.
 */
@Slf4j
public class MeasureTimeBeanPostProcessor implements BeanPostProcessor {
    MeasureTimeCotroller timeCotroller = new MeasureTimeCotroller();
    private Map<String, Class> mapBeanWithInitialClass = new HashMap<String, Class>();

    public MeasureTimeBeanPostProcessor() throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
        //зарегать новый бин в МБин, который можно менять на лету
        final MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
        platformMBeanServer.registerMBean(timeCotroller, new ObjectName("timeManagement", "name", "controller"));
    }

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(MeasureTime.class)) {
            mapBeanWithInitialClass.put(beanName, beanClass);
        }
        return bean;
    }

    public Object postProcessAfterInitialization(final Object bean, String beanName) throws BeansException {
        timeCotroller.setEnabled(true);
        final Class beanClass = mapBeanWithInitialClass.get(beanName);
        if (beanClass != null) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new InvocationHandler() {
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if (timeCotroller.isEnabled()) {
                        log.info("Start measure time");
                        long startTime = System.nanoTime();
                        Object result = method.invoke(bean, args);
                        log.info("Time of implementation = {}s", (System.nanoTime() - startTime)/1000);
                        log.info("End measure time");
                        return result;
                    }
                    return method.invoke(bean, args);
                }
            });
        }
        return bean;
    }
}
