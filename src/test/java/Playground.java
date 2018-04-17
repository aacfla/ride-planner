import dynamo.RidesDao;
import dynamo.RidesDaoTest;
import dynamo.RidesInfo;

/**
 * For testing random things
 */
public class Playground {

    public static void main(String[] args) {
        RidesDao dao = new RidesDao();
        //dao.insert("Jason@gmail.com", "Jason", 1923, "7605555555", "LightHouse", false);
        
        RidesInfo jasonUpdate = new RidesInfo();
        jasonUpdate.setEmail("Jason@gmail.com");
        jasonUpdate.setCanAttend(false);
        jasonUpdate.setName("Jeff");
        jasonUpdate.setNotes("Notes Go Here");
        jasonUpdate.setYear(1924);
        jasonUpdate.setPhoneNumber("7605555555");
        jasonUpdate.setChurch("Lighthouse");
        dao.update("Jason@gmail.com", jasonUpdate);
        //dao.insert("Email", "Sean",2021, "7605555555", "Lighthouse", false);
        
    }
}
