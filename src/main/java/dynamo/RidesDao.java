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




}