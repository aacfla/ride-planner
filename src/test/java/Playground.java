import dynamo.RidesDao;

/**
 * For testing random things
 */
public class Playground {

    public static void main(String[] args) {
        RidesDao dao = new RidesDao();
        //Insert format:
        //Email, Name, Year, PhoneNumber, Church, Attendance, Driver, Num of Seats, Notes
        dao.insert("okayyy@gmail.com", "Jeffolas", "Freshman", "123-456-7890", "CCAC", true, true, 3, "Praise God");
        dao.read("okaasdy@gmail.com");
        dao.readAll();
//        dao.delete("okay@gmail.com");
    }
}
