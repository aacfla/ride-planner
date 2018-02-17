package dynamo;

import java.util.Arrays;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;

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
    	/*
        dbClient = AmazonDynamoDBClientBuilder.standard()
                        .withRegion(Regions.US_WEST_1)
                        .build();
        dynamoDB = new DynamoDB(dbClient);     
        */
        dbClient = AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.US_EAST_2)
                .build();
        dynamoDB = new DynamoDB(dbClient);   
    }

    
    
    
    
    // Create Table 
    public void createTable() {
    	
        dbClient = AmazonDynamoDBClientBuilder.standard()   // Client
                .withRegion(Regions.US_EAST_2)
                .build();
        dynamoDB = new DynamoDB(dbClient);   
        String tableName = "RideTable2"; //Name for Table

        try {
            System.out.println("Attempting to create table; please wait...");
            Table table = dynamoDB.createTable(tableName,
                Arrays.asList(new KeySchemaElement("Email", KeyType.HASH), // Partition
                                                                          // key
                    new KeySchemaElement("Name", KeyType.RANGE)), // Sort key
                Arrays.asList(new AttributeDefinition("Email", ScalarAttributeType.S),
                    new AttributeDefinition("Name", ScalarAttributeType.S)),
                new ProvisionedThroughput(10L, 10L));
            table.waitForActive();
            System.out.println("Success.  Table status: " + table.getDescription().getTableStatus());

        }
        catch (Exception e) {
            System.err.println("Unable to create table: ");
            System.err.println(e.getMessage());
        }
    }
    
    
    //Delete Table
    public void deleteTable(String tableName) {
        Table table = dynamoDB.getTable(tableName);
        try {
            System.out.println("Issuing DeleteTable request for " + tableName);
            table.delete();

            System.out.println("Waiting for " + tableName + " to be deleted...this may take a while...");

            table.waitForDelete();
        }
        catch (Exception e) {
            System.err.println("DeleteTable request failed for " + tableName);
            System.err.println(e.getMessage());
        }
    }
    
    
    // Todo: Implement insert, update, read, readAll, delete
    public void insert(String email, String name, int year, String phoneNumber, String church, boolean attendance)
    {
    }

    public void update(String email)
    {
    }

    public void read(String email)
    {
    }

    public void readAll()
    {
    }

    public void delete(String email)
    {
    }

}