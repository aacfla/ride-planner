package dynamo;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;


/**
 * Data accessor object for rides DynamoDB
 */
public class RidesDao {

    private AmazonDynamoDB dbClient;
    private DynamoDBMapper mapper;


    // Needs to deal with the credentials...
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

        System.out.print("Rider saved: ");
        System.out.println(email);
    }

    // Note that every parameter is optional except for email
    public void update(String email, String name, String year, String phoneNumber, String church, Boolean attendance) {}

    public RidesInfo read(String email) {
        RidesInfo riderRetrieved = mapper.load(RidesInfo.class, email);
        System.out.print("Rider retrieved: ");
        System.out.println(riderRetrieved);

        return riderRetrieved;
    }

    public void readAll() {}

    public void delete(String email) {}

}
