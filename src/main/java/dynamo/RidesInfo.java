package dynamo;

public class RidesInfo {

    private String email;
    private String name;
    private int year;
    private String phoneNumber;

    private String church;
    private Boolean driver;
    private Integer numSeats;
    private String notes;
    private Boolean canAttend;
    private Long timestamp;

    public void setEmail (String email) { this.email = email; }
    public String getEmail() { return email; }

    public void setName (String name) { this.name = name; }
    public String getName() { return name; }

    public void setYear (int year) { this.year = year; }
    public int getYear() { return year; }

    public void setPhoneNumber (String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getPhoneNumber() { return phoneNumber; }

    public void setChurch (String church) { this.church = church; }
    public String getChurch() { return church; }

    public void setDriver (Boolean driver) { this.driver = driver; }
    public Boolean getDriver() { return driver; }

    public void setNumSeats (Integer numSeats) { this.numSeats = numSeats; }
    public Integer getNumSeats() { return numSeats; }

    public void setNotes (String notes) { this.notes = notes; }
    public String getNotes() { return notes; }

    public void setCanAttend (Boolean canAttend) { this.canAttend = canAttend; }
    public Boolean getCanAttend() { return canAttend; }

    public void setTimestamp (Long timeStamp) { this.timestamp = timeStamp; }
    public Long getTimestamp() { return timestamp; }
    
}
