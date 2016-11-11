package people;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Oleksandra_Dmytrenko on 11/10/2016.
 */
@Test
@ContextConfiguration(locations = {"classpath:spring-test-config-autowired.xml"})
public class PersonAutowiredTest extends AbstractTestNGSpringContextTests {

    @Autowired
    Person person;


    @Test(priority = 2)
    public void setNameTest() {
        Assert.assertNull(person.getName());
        person.setName("Vasia");
        Assert.assertEquals( "Vasia", person.getName());
    }

    @Test(priority = 1)
    public void getNameTest() {
        Assert.assertEquals(0, person.getAge());
        Assert.assertNull(person.getName());
    }
}