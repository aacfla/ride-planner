import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import dynamo.RidesDao;
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

        return "";
    }

    /**
     * Add user info and request a ride
     */
    private int addInfo (ClientRequest request)
    {
        return 0;
    }

    /**
     * Delete user row based on primary key
     */
    private int deleteInfo (ClientRequest request)
    {
        return 0;
    }

    /**
     * Edit user's ride request
     */
    private int editInfo (ClientRequest request)
    {
        return 0;
    }

    /**
     * Cancel a ride for the week, but keep user info
     */
    private int cancelInfo (ClientRequest request)
    {
        return 0;
    }

    /**
     * Get info about a user
     */
    private int retrieveInfo (ClientRequest request) {
        return 0;
    }

}
