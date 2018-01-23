package dynamo;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.Item;

/**
 * Data accessor object for rides DynamoDB
 */
public class RidesDao {

    private AmazonDynamoDB dbClient;
    private DynamoDB dynamoDB;

    Table table = dynamoDB.getTable("Movies");   // Name of the database table
    
    
    // Needs to deal with the credentials...
    public RidesDao() {
        dbClient = AmazonDynamoDBClientBuilder.standard()
                        .withRegion(Regions.US_WEST_1)
                        .build();
        dynamoDB = new DynamoDB(dbClient);     
    }

    // Todo: Implement insert, update, read, readAll, delete
    public void insert() {
    	try {
            System.out.println("Adding a new item...");
            PutItemOutcome outcome = table.putItem(new Item().withPrimaryKey("year", year, "title", title).withMap("info", infoMap));

            System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());

        }
        catch (Exception e) {
            System.err.println("Unable to add item: " + year + " " + title);
            System.err.println(e.getMessage());
        }
    }


}