package DomainLayer.Users;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RefereeTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void approveRegistration() {
    }

    @Test
    public void updatePersonalDetails() {
        Referee r = new Referee("r1", "rr", "Ziv Adler","ziv@adler" , "Kavan");

        assertTrue("does nothing good. !", r.userFullName.equals("Ziv Adler"));

        r.setUserFullName("Tali");
        assertFalse(" UC 10.1 is good ! - name has been updated.", r.userFullName.equals("Ziv Adler"));
        assertTrue("UC 10.1 is good !",r.userFullName.equals("Tali"));

        assertTrue(" nothing happened.", r.getQualification().equals("Kavan"));
        r.setQualification("Nothing");
        assertFalse(" UC 10.1 is good ! - qualification has been updated.", r.getQualification().equals("Kavan"));
        assertTrue("UC 10.1 is good !",r.getQualification().equals("Nothing"));

    }

    @Test
    public void addGame() {
    }

   /* @Test
    public void watchGamesList() {
        Referee r = new Referee("r1", "rr", "Ziv Adler", "ziv@adler" ,"Kavan");
        ArrayList<Team> teams=new ArrayList<>();

        Team a= new Team("Maccabi Tel Aviv");
        Team b= new Team("Maccabi Haifa");
        Team c= new Team("Beitar");
        Team d= new Team("Hapoel Beer Sheva");
        teams.add(a);
        teams.add(b);
        teams.add(c);
        teams.add(d);

        Date d1 = new Date(120, 4, 9, 21, 10);
        Date d2 = new Date(120, 4, 11, 21, 30);
        Date d3 = new Date(120, 5, 18, 14, 20);

        Date d4 = new Date();
        d4.setYear(120);
        d4.setMonth(4);
        d4.setDate(9);
        d4.setHours(21);

        //Date d4 = new Date(2020, 5, 24);
        // Date d5 = new Date(2020, 7, 29);

        Game g1 = new Game(a, b);
        Game g2 = new Game(a, c);
        Game g3 = new Game(b, d);

        g1.setGameDate(d1);
        g1.setGameHour(d1);
        g2.setGameDate(d2);
        g2.setGameHour(d2);
        g3.setGameDate(d3);
        g3.setGameHour(d3);

        r.addGame(g1);
        r.addGame(g3);
        r.addGame(g2);

        //assertTrue();
        try {
            r.watchGamesList();
            java.lang.System.out.println("success");
        }catch (Exception e){
            java.lang.System.out.println("wrong");
        }
    }*/
}