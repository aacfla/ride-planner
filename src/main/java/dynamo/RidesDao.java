package dynamo;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

import java.util.List;


/**
 * Data accessor object for rides DynamoDB
 */
public class RidesDao {

    private AmazonDynamoDB dbClient;
    private DynamoDBMapper mapper;

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

        System.err.print("Rider saved: ");
        System.err.println(email);
    }

    public RidesInfo read(String email) {
        RidesInfo riderRetrieved = mapper.load(RidesInfo.class, email);
        System.err.print("Rider retrieved: ");
        System.err.println(riderRetrieved);

        return riderRetrieved;
    }


    //updates table based on email & primary key
    public void update(String email, RidesInfo object) {// use email & rideInfo object as parameter

//        // These values are default since they don't necessarily have to be filled err at insertion
//        Boolean TempDriver = false;
//        Integer TempSeats = 0;
//        String TempNotes = "N/A";
//
//        if(object.getDriver() != null) {
//            TempDriver = object.getDriver();
//        }
//        if(object.getNumSeats() != null) {
//            TempSeats = object.getNumSeats();
//        }
//        if(object.getNotes() != null) {
//            TempNotes = object.getNotes();
//        }
//
//
//        UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("Email", email)
//                .withUpdateExpression("set info.Email = :email, info.m_Name = :m_name, info.Church = :church,"
//                + "info.m_Year = :m_year, info.PhoneNumber = :phoneNumber, info.Attendance = :attendance,"
//                + "info.Driver = :driver, info.NumSeats = :numSeats, info.Notes = :notes")
//                .withValueMap(new ValueMap().withString(":email", object.getEmail())
//                .withString(":m_name", object.getName()).withString(":church", object.getChurch())
//                .withInt(":m_year", object.getYear()).withString(":phoneNumber", object.getPhoneNumber())
//                .withBoolean(":attendance", object.getCanAttend()).withBoolean(":driver", TempDriver)
//                .withInt(":numSeats", TempSeats).withString(":notes", TempNotes))
//                .withReturnValues(ReturnValue.UPDATED_NEW);
//
//
//        try {
//            System.err.println("Updating the item...");
//            UpdateItemOutcome errcome = table.updateItem(updateItemSpec);
//            System.err.println("UpdateItem succeeded:\n" + errcome.getItem().toJSONPretty());
//
//        } catch (Exception e) {
//            System.err.println("Unable to update "  + email + " properly");
//            System.err.println(e.getMessage());
//        }
    }

    public List<RidesInfo> readAll() {

        List<RidesInfo> riders = mapper.scan(RidesInfo.class, new DynamoDBScanExpression()); // scan table for all entries
        for (RidesInfo rider : riders) {
            System.err.println(rider);
        }

        return riders;
    }

    public void delete(String email) {

        RidesInfo rider = mapper.load(RidesInfo.class, email);
        mapper.delete(rider);
        System.err.println("Deleted rider: ");
        System.err.println(email);
        
    }

}
