package dynamo;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;


/**
 * Data accessor object for rides DynamoDB
 */
public class RidesDao {

    private AmazonDynamoDB dbClient;
    private DynamoDB dynamoDB;
    private DynamoDBMapper mapper;



    // Needs to deal with the credentials...
    public RidesDao() {
        dbClient = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_WEST_1).build();
        dynamoDB = new DynamoDB(dbClient);
        mapper = new DynamoDBMapper(dbClient);
    }

    public void insert(String email, String name, int year, String phoneNumber, String church,
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
        mapper.save(rider);
    }

    // Note that every parameter is optional except for email
    public void update(String email,
                       String name, Integer year, String phoneNumber, String church, Boolean attendance) {}

    public void read(String email) {
        RidesInfo riderRetrieved = mapper.load(RidesInfo.class, email);
        System.out.println("Rider retrieved:");
        System.out.println(riderRetrieved);

    }

    public void readAll() {}

    public void delete(String email) {}

}
