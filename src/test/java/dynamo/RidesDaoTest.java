package dynamo;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;

import java.util.Arrays;

public class RidesDaoTest {

    private AmazonDynamoDB dbClient;
    private DynamoDB dynamoDB;
    public String tableName = "RideTable";

    public RidesDaoTest() {
    		AmazonDynamoDB dbClient = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_WEST_1).build();
    		dynamoDB = new DynamoDB(dbClient);
    }
    
    public void createTable() {
        try {
            System.out.println("Attempting to create table; please wait...");
            Table table = dynamoDB.createTable(tableName, Arrays.asList(new KeySchemaElement("Email", KeyType.HASH)), // Partition
                    Arrays.asList(new AttributeDefinition("Email", ScalarAttributeType.S)),
                                  new ProvisionedThroughput(10L, 10L));
                                table.waitForActive();
            System.out.println("Success.  Table status: " + table.getDescription().getTableStatus());

        } catch (Exception e) {
            System.err.println("Unable to create table: ");
            System.err.println(e.getMessage());
        }
    }

    // Delete Table
    public void deleteTable() {
        Table table = dynamoDB.getTable(tableName);
        try {
            System.out.println("Issuing DeleteTable request for " + tableName);
            table.delete();

            System.out.println("Waiting for " + tableName + " to be deleted...this may take a while...");

            table.waitForDelete();
            System.out.println("Success! Table Deleted");
        } catch (Exception e) {
            System.err.println("Delete Table request failed for " + tableName);
            System.err.println(e.getMessage());
        }
    }

    

}
