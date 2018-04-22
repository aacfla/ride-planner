package dynamo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "RidesTableTest")
public class  RidesInfo {

    private String email;
    private String name;
    private String year;
    private String phoneNumber;

    private String church;
    private Boolean driver;
    private Integer numSeats;
    private String notes;
    private Boolean canAttend;
    private Long timestamp;

    @DynamoDBHashKey(attributeName = "Email")
    public String getEmail() { return email; }
    public void setEmail (String email) { this.email = email; }

    @DynamoDBAttribute(attributeName = "Name")
    public String getName() { return name; }
    public void setName (String name) { this.name = name; }

    @DynamoDBAttribute(attributeName = "Year")
    public String getYear() { return year; }
    public void setYear (String year) { this.year = year; }

    @DynamoDBAttribute(attributeName = "PhoneNumber")
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber (String phoneNumber) { this.phoneNumber = phoneNumber; }

    @DynamoDBAttribute(attributeName = "Church")
    public String getChurch() { return church; }
    public void setChurch (String church) { this.church = church; }

    @DynamoDBAttribute(attributeName = "Attendance")
    public Boolean getCanAttend() { return canAttend; }
    public void setCanAttend (Boolean canAttend) { this.canAttend = canAttend; }

    @DynamoDBAttribute(attributeName = "Driver")
    public Boolean getDriver() { return driver; }
    public void setDriver (Boolean driver) { this.driver = driver; }

    @DynamoDBAttribute(attributeName = "NumSeats")
    public Integer getNumSeats() { return numSeats; }
    public void setNumSeats (Integer numSeats) { this.numSeats = numSeats; }

    @DynamoDBAttribute(attributeName = "Notes")
    public String getNotes() { return notes; }
    public void setNotes (String notes) { this.notes = notes; }

    @DynamoDBAttribute(attributeName = "Timestamp")
    public Long getTimestamp() { return timestamp; }
    public void setTimestamp (Long timeStamp) { this.timestamp = timeStamp; }

    @Override
    public String toString() {

        return "Name = " + name + " | Email = " + email + " | Year = " + year + " | Phone Number = " + phoneNumber
                + " | Church = " + church + " | Attendance = " + canAttend + " | Notes for " + name + ": " + notes;
    }
}
