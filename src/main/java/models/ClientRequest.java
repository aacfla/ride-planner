package models;

public class ClientRequest {

    public enum requestType {
        ADD_RIDE_REQUEST,
        DELETE_RIDE_REQUEST,
        EDIT_RIDE_REQUEST,
        CANCEL_RIDE_REQUEST,
        RETRIEVE_RIDE_REQUEST;
    }

    private requestType type;
    private String email;
    private String name;
    private int year;
    private String phoneNumber;
    private String church;
    private boolean driver;
    private int numSeats;
    private String notes;
    private boolean canAttend;
    private long timestamp;

    public void setRequestType(requestType type) { this.type = type; }
    public requestType getRequestType() { return type; }

    public void setEmail(String email) { this.email = email; }
    public String getEmail() { return email; }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public void setYear(int year) { this.year = year; }
    public int getYear() { return year; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getPhoneNumber() { return phoneNumber; }

    public void setChurch(String church) { this.church = church; }
    public String getChurch() { return church; }

    public void setDriver(boolean driver) { this.driver = driver; }
    public boolean getDriver() { return driver; }

    public void setNumSeats(int numSeats) { this.numSeats = numSeats; }
    public int getNumSeats() { return numSeats; }

    public void setNotes(String notes) { this.notes = notes; }
    public String getNotes() { return notes; }

    public void setCanAttend(boolean canAttend) { this.canAttend = canAttend; }
    public boolean getCanAttend() { return canAttend; }

    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
    public long getTimestamp() { return timestamp; }

}
