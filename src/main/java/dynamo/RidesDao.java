package dynamo;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import models.ClientRequest;

/**
 * Data accessor object for rides DynamoDB
 */
public class RidesDao {

    private AmazonDynamoDB dbClient;
    private DynamoDB dynamoDB;

//    Table table = dynamoDB.getTable("Movies");   // Name of the database table
    
    
    // Needs to deal with the credentials...
    public RidesDao() {
        dbClient = AmazonDynamoDBClientBuilder.standard()
                        .withRegion(Regions.US_WEST_1)
                        .build();
        dynamoDB = new DynamoDB(dbClient);     
    }

    // Todo: Implement insert, update, read, readAll, delete
    public void insert(ClientRequest request, String email, String name, int year, String phoneNumber, String church, boolean attendance)
    {
    }

    public void update(ClientRequest request, String email)
    {
    }

    public void read(ClientRequest request, String email)
    {
    }

    public void readAll(ClientRequest request)
    {
    }

    public void delete(ClientRequest request, String email)
    {
    }

}