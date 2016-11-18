package annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Oleksandra_Dmytrenko on 11/11/2016.
 */
@Retention(value = RetentionPolicy.RUNTIME) // so that we could access the annotation through reflection
public @interface InjectRandomInt {

    int min();
    int max();
}
