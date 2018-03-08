import dynamo.RidesDao;
import dynamo.RidesDaoTest;

/**
 * For testing random things
 */
public class Playground {

    public static void main(String[] args) {
        RidesDao dao = new RidesDao();
        dao.insert("Email", "Ryan", 2021, "7605555555", "Lighthouse", false);
        //dao.insert();
        System.out.println("hello");
    }
}
