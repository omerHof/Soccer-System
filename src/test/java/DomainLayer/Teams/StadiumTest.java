package DomainLayer.Teams;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StadiumTest {
    Stadium stadium;

    @Before
    public void setUp() throws Exception {
        stadium = new Stadium(1000,50000,50);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getCapacity() {
        try{
            assertEquals("not equals",50000,stadium.getCapacity());
        }catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void setCapacity() {
        try{
            stadium.setCapacity(10000);
            assertEquals("not equals",10000,stadium.getCapacity());
        }catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void getPrice() {
        try{
            double price=50;
            assertTrue("not equals",price==stadium.getPrice());
        }catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void setPrice() {
        try{
            stadium.setPrice(60);
            assertTrue("not equals",60==stadium.getPrice());
        }catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void getName() {
        try{
            stadium.setName("camp nou");
            assertEquals("not equals","camp nou",stadium.getName());
        }catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void setName() {
        try{
            stadium.setName("camp nou");
            assertEquals("not equals","camp nou",stadium.getName());
        }catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void getWorth() {
        try{
            assertTrue("not equals",1000==stadium.getWorth());
        }catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void setWorth() {
        try{
            stadium.setWorth(5000);
            assertTrue("not equals",5000==stadium.getWorth());
        }catch (Exception e) {
            System.out.println("error");
        }
    }
}