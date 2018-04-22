package dynamo;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;


/**
 * Data accessor object for rides DynamoDB
 */
public class RidesDao {
	public String tableName = "RidesTable";
    private AmazonDynamoDB dbClient;
    private Table table;

    // Needs to deal with the credentials...
    public RidesDao() {
		dbClient = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_WEST_1).build();
        DynamoDB dynamoDB = new DynamoDB(dbClient);
        table = dynamoDB.getTable(tableName);
    }

	// inserts a new person into the table with an email & a name
	public void insert(String email, String name, int year, String phoneNumber, String church, boolean attendance) {

		final Map<String, Object> infoMap = new HashMap<>();
		infoMap.put("m_Name", name);
		infoMap.put("Church", church);
		infoMap.put("m_Year", year);
		infoMap.put("PhoneNumber", phoneNumber);
		infoMap.put("Attendance", attendance);
		infoMap.put("Driver", false);
		infoMap.put("NumSeats", 0);
		infoMap.put("Notes", " ");

		try {
			System.out.println("Adding a new item...");
			PutItemOutcome outcome = table
					.putItem(new Item().withPrimaryKey("Email", email).withMap("info", infoMap));

			System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());

		} catch (Exception e) {
			System.err.println("Unable to add " + email);
			System.err.println(e.getMessage());
		}
	}
	

	//updates table based on email & primary key
	public void update(String email, RidesInfo object) {// use email & rideInfo object as parameter

		// These values are default since they don't necessarily have to be filled out at insertion
		Boolean TempDriver = false;
		Integer TempSeats = 0;
		String TempNotes = "N/A";

		if(object.getDriver() != null) {
			TempDriver = object.getDriver();
		}
		if(object.getNumSeats() != null) {
			TempSeats = object.getNumSeats();
		}
		if(object.getNotes() != null) {
			TempNotes = object.getNotes();
		}
		

		UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("Email", email)
				.withUpdateExpression("set info.Email = :email, info.m_Name = :m_name, info.Church = :church,"
				+ "info.m_Year = :m_year, info.PhoneNumber = :phoneNumber, info.Attendance = :attendance,"
				+ "info.Driver = :driver, info.NumSeats = :numSeats, info.Notes = :notes")
				.withValueMap(new ValueMap().withString(":email", object.getEmail())
				.withString(":m_name", object.getName()).withString(":church", object.getChurch())
				.withInt(":m_year", object.getYear()).withString(":phoneNumber", object.getPhoneNumber())
				.withBoolean(":attendance", object.getCanAttend()).withBoolean(":driver", TempDriver)
				.withInt(":numSeats", TempSeats).withString(":notes", TempNotes))
				.withReturnValues(ReturnValue.UPDATED_NEW);
		
		
		try {
			System.out.println("Updating the item...");
			UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
			System.out.println("UpdateItem succeeded:\n" + outcome.getItem().toJSONPretty());

		} catch (Exception e) {
			System.err.println("Unable to update "  + email + " properly");
			System.err.println(e.getMessage());
		}
	}
	

    public void read(String email) {

		GetItemSpec spec = new GetItemSpec().withPrimaryKey("Email", email);

		try {
			System.out.println("Attempting to read the item...");
			Item outcome = table.getItem(spec);
			System.out.println("Read Item success!: " + outcome);

		} catch (Exception e) {
			System.err.println("Unable to read item: " + email);
			System.err.println(e.getMessage());
		}
    }

    public void readAll() {

		ScanRequest scanRequest = new ScanRequest().withTableName(tableName);

		ScanResult result = dbClient.scan(scanRequest);
		for (Map<String, AttributeValue> item : result.getItems()) {
			System.out.print(item);
		}
    }

   
    public void delete(String email) {

		DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
				.withPrimaryKey(new PrimaryKey("Email", email));

		// Conditional delete (we expect this to fail)

		try {
			System.out.println("Attempting a conditional delete...");
			table.deleteItem(deleteItemSpec);
			System.out.println("DeleteItem succeeded");
		} catch (Exception e) {
			System.err.println("Unable to delete item: " + email);
			System.err.println(e.getMessage());
		}
    }

}
