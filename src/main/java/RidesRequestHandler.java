import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import dynamo.RidesDao;
import dynamo.RidesInfo;
import models.ClientRequest;

/**
 * Handles queries to access the rides database
 */
public class RidesRequestHandler implements RequestHandler<ClientRequest, String> {

    private RidesDao ridesDao = new RidesDao();

    @Override
    public String handleRequest(ClientRequest request, Context context) {

        switch(request.getRequestType()) {
            case ADD_RIDE_REQUEST:
                addInfo(request);
                break;
            case DELETE_RIDE_REQUEST:
                deleteInfo(request);
                break;
            case EDIT_RIDE_REQUEST:
                editInfo(request);
                break;
            case CANCEL_RIDE_REQUEST:
                cancelInfo(request);
                break;
            case RETRIEVE_RIDE_REQUEST:
                retrieveInfo(request);
                break;
            default:
                // panic
        }

        return "End of handleRequest";
    }

    /**
     * Add user info and request a ride
     */
    private int addInfo (ClientRequest request)
    {
        if(request.getEmail() == null) {
            System.err.print("Needs rider's email (primary key)");
            return 1;
        }
        RidesInfo addToRider = new RidesInfo();
        addToRider.setEmail(request.getEmail());
        addToRider.setName(request.getName());
        addToRider.setYear(request.getYear());
        addToRider.setPhoneNumber(request.getPhoneNumber());
        addToRider.setChurch(request.getChurch());
        addToRider.setDriver(request.getDriver());
        addToRider.setNumSeats(request.getNumSeats());
        addToRider.setNotes(request.getNotes());
            // Request a ride
        addToRider.setCanAttend(true);
        ridesDao.update(request.getEmail(), addToRider);
        System.err.print("Add info completed for " + request.getName() + ".");
        return 0;
    }

    /**
     * Delete user row based on primary key
     */
    private int deleteInfo (ClientRequest request)
    {
        ridesDao.delete(request.getEmail());
        System.err.print(request.getName() + " has been deleted.");
        return 0;
    }

    /**
     * Edit user's ride request
     */
    private int editInfo (ClientRequest request)
    {
        RidesInfo editRider = new RidesInfo();
        editRider.setEmail(request.getEmail());
        editRider.setChurch(request.getChurch());
        ridesDao.update(request.getEmail(), editRider);
        return 0;
    }

    /**
     * Cancel a ride for the week, but keep user info
     */
    private int cancelInfo (ClientRequest request)
    {
        RidesInfo cancelRide = new RidesInfo();
        cancelRide.setEmail(request.getEmail());
        cancelRide.setCanAttend(false);
        ridesDao.update(request.getEmail(), cancelRide);
        return 0;
    }

    /**
     * Get info about a user
     */
    private int retrieveInfo (ClientRequest request) {
        ridesDao.read(request.getEmail());
        return 0;
    }

}
