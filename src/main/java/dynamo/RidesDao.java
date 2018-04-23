package dynamo;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

import java.util.List;


/**
 * Data accessor object for rides DynamoDB
 */
public class RidesDao {

    private AmazonDynamoDB dbClient;
    private DynamoDBMapper mapper;

    public RidesDao() {
        dbClient = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_WEST_1).build();
        mapper = new DynamoDBMapper(dbClient);
    }

    public void insert(String email, String name, String year, String phoneNumber, String church,
                       boolean attendance, boolean driver, Integer numSeats, String notes) {
        RidesInfo rider = new RidesInfo();
        rider.setEmail(email);
        rider.setName(name);
        rider.setYear(year);
        rider.setPhoneNumber(phoneNumber);
        rider.setChurch(church);
        rider.setCanAttend(attendance);
        rider.setDriver(driver);
        rider.setNumSeats(numSeats);
        rider.setNotes(notes);
        rider.setTimestamp(System.currentTimeMillis());
        mapper.save(rider);

        System.err.print("Rider saved: ");
        System.err.println(email);
    }

    public RidesInfo read(String email) {
        RidesInfo riderRetrieved = mapper.load(RidesInfo.class, email);
        System.err.print("Rider retrieved: ");
        System.err.println(riderRetrieved);

        return riderRetrieved;
    }



    /**
     * Updates an existing entry in the table.
     * @param email
     * @param update Required fields do not need to be filled in
     * @return 0 : no errors
     *         1 : entry does not exist in the table
     */
    public int update(String email, RidesInfo update) {

        RidesInfo prev = read(email);
        if (prev == null) {
            return 1;
        }

        // Insert missing values from previous version
        if (update.getName() == null) {
            update.setName(prev.getName());
        }
        if (update.getYear() == null) {
            update.setYear(prev.getYear());
        }
        if (update.getPhoneNumber() == null) {
            update.setPhoneNumber(prev.getPhoneNumber());
        }
        if (update.getChurch() == null) {
            update.setChurch(prev.getChurch());
        }
        if (update.getCanAttend() == null) {
            update.setCanAttend(prev.getCanAttend());
        }
        if (update.getDriver() == null) {
            update.setDriver(prev.getDriver());
        }
        if (update.getNumSeats() == null) {
            update.setNumSeats(prev.getNumSeats());
        }
        if (update.getNotes() == null) {
            update.setNotes(prev.getNotes());
        }
        update.setTimestamp(System.currentTimeMillis());

        mapper.save(update);
        System.err.print("Rider updated: ");
        System.err.println(email);

        return 0;

    }

    public List<RidesInfo> readAll() {

        List<RidesInfo> riders = mapper.scan(RidesInfo.class, new DynamoDBScanExpression()); // scan table for all entries
        for (RidesInfo rider : riders) {
            System.err.println(rider);
        }

        return riders;
    }

    public void delete(String email) {

        RidesInfo rider = mapper.load(RidesInfo.class, email);
        mapper.delete(rider);
        System.err.println("Deleted rider: ");
        System.err.println(email);
        
    }

}
