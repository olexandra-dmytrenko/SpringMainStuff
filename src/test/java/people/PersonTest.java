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
@ContextConfiguration(locations = { "classpath:spring-test-config.xml" })
public class PersonTest extends AbstractTestNGSpringContextTests{

@Autowired
Person person;

    @org.testng.annotations.BeforeMethod
    public void setUp() throws Exception {
    }

    @Test()
    public void setNameTest(){
        Assert.assertNotNull(person.getName());
        person.setName("Vasia");
        Assert.assertEquals(person.getName(), "Vasia");
    }

    @Test()
    public void getNameTest(){
        Assert.assertNotEquals(0, person.getAge());
        Assert.assertNotNull(person.getName());
    }

    @org.testng.annotations.AfterMethod
    public void tearDown() throws Exception {

    }

}