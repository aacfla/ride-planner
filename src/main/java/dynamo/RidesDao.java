package dynamo;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;


/**
 * Data accessor object for rides DynamoDB
 */
public class RidesDao {

    private AmazonDynamoDB dbClient;
    private DynamoDB dynamoDB;

    // Needs to deal with the credentials...
    public RidesDao() {
        dbClient = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_WEST_1).build();
        dynamoDB = new DynamoDB(dbClient);
    }

    public void insert(String email, String name, int year, String phoneNumber, String church, boolean attendance) {}

    // Note that every parameter is optional except for email
    public void update(String email,
                       String name, Integer year, String phoneNumber, String church, Boolean attendance) {}

    public void read(String email) {

    }

    public void readAll() {}

    public void delete(String email) {}

}
