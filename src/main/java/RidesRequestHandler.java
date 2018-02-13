import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import dynamo.RidesDao;
import models.ClientRequest;


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

    /*
     * add user info and request a ride
     */
    private int addInfo (ClientRequest request)
    {
//        ridesDao.insert();
        return 0;
    }

    /*
     * delete user info
     */
    private int deleteInfo (ClientRequest request)
    {
//        ridesDao.delete();
        return 0;
    }

    /*
     * edit user's ride request
     */
    private int editInfo (ClientRequest request)
    {
//        ridesDao.update();
        return 0;
    }

    /*
     * cancel a ride
     */
    private int cancelInfo (ClientRequest request)
    {
        //cancel something
        return 0;
    }

    /*
     * get info about a user
     */
    private int retrieveInfo (ClientRequest request) {
//        ridesDao.read();
        return 0;
    }

}
