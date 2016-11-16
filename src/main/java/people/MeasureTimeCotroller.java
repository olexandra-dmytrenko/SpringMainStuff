package people;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Oleksandra_Dmytrenko on 11/15/2016.
 */
@Setter
@Getter
/**
 * Чтоб выводить время выполнения метода создана аннотация @MeasureTime. Она активируется через проперти
 MeasureTimeCotroller.enabled. Поскольку оно имплементит MBean MeasureTimeCotrollerMBean, значение этой
 проперти можно менять в Java VisualVM, которая находится по пути jdk\bin\jvisualvm.exe. Надо запустить
 аппликейшин, зайти в Java VisualVM в Local найти приложение и во вкладке MBean выбрать timeManagement ->
 controller, куда передать значение true для измерения времени или наоборот. MBean - плагин, который можно
 установить через Tools.
 */

public class MeasureTimeCotroller implements MeasureTimeCotrollerMBean {
    /**
     * output measured time of method implementation
     */
    boolean enabled = true;
}
