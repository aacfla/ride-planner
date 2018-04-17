package dynamo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;

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
    
public void insertTest(String email, String name, int year, String phoneNumber, String church, boolean attendance) {
		
		dbClient = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_WEST_1).build();
        dynamoDB = new DynamoDB(dbClient);
        
		Table table = dynamoDB.getTable("RideTable");

		final Map<String, Object> infoMap = new HashMap<String, Object>();
		infoMap.put("Name", name);
		infoMap.put("Church", church);
		infoMap.put("Year", year);
		infoMap.put("Email", email);
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
	public void updateTest(String email,RidesInfo object) {// use email & rideInfo object as parameter
		Table table = dynamoDB.getTable("RideTable");
		//These values are default since they don't necessarily have to be filled out at insertion
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
				.withUpdateExpression("set info.Email = :email, set info.Name = :name, set info.Church = :church,"
				+ "set info.Year = :year, set info.PhoneNumber = :phoneNumber, set info.Attendance = :attendance,"
				+ "set info.Driver = :driver, set info.NumSeats = :numSeats, set info.Notes = :notes")
				.withValueMap(new ValueMap().withString(":email", object.getEmail())
				.withString(":name", object.getName()).withString(":church", object.getChurch())
				.withInt(":year", object.getYear()).withString(":phoneNumber", object.getPhoneNumber())
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
    
    
    
    // Test Helper Function 
	public String getEmailFromDynamo(String email) {
		HashMap<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put(":email", email);
		Table table = dynamoDB.getTable("RideTableTest");
		
		QuerySpec querySpec = new QuerySpec().withKeyConditionExpression("Email = : email")
	    .withValueMap(valueMap);
        ItemCollection<QueryOutcome> items = null;
        Iterator<Item> iterator = null;
        Item item = null;

        try {
            items = table.query(querySpec);

            iterator = items.iterator();
            while (iterator.hasNext()) {
                item = iterator.next();
                return item.getString("Email");
            }

        }
        catch (Exception e) {
            System.err.println("Unable to find " + email);
            System.err.println(e.getMessage());
        }
		return "";
	}
	
	public String getNameFromDynamo(String email) {

		Table table = dynamoDB.getTable("RideTable");
		return "";
	}
	public String getYearFromDynamo(String email) {

		Table table = dynamoDB.getTable("RideTable");
		return "";
	}
	public String getPhoneFromDynamo(String email) {

		Table table = dynamoDB.getTable("RideTable");
		return "";
	}
	public String getChurchFromDynamo(String email) {

		Table table = dynamoDB.getTable("RideTable");
		return "";
	}
	public String getDriverFromDynamo(String email) {

		Table table = dynamoDB.getTable("RideTable");
		return "";
	}
	public String getNumSeatsFromDynamo(String email) {

		Table table = dynamoDB.getTable("RideTable");
		return "";
	}
	public String getNotesFromDynamo(String email) {

		Table table = dynamoDB.getTable("RideTable");
		return "";
	}
	public String getAttendFromDynamo(String email) {

		Table table = dynamoDB.getTable("RideTable");
		return "";
	}
	public Long getTimeStampFromDynamo(String email) {

		Table table = dynamoDB.getTable("RideTable");
		return (long) 0;
	}
	
    @Test
    void testInsert() {
    	RidesDaoTest object = new RidesDaoTest();
    	//object.insertTest("Email", "Ryan", 2021, "7605555555", "LightHouse", false);
    	assert(object.getEmailFromDynamo("Email") == "Email");

    }
    

}
