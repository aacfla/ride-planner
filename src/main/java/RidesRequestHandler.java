import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import dynamo.RidesDao;
import models.ClientRequest;


public class RidesRequestHandler implements RequestHandler<ClientRequest, String> {

    private RidesDao ridesDao = new RidesDao();

    @Override
    public String handleRequest(ClientRequest request, Context context) {

        switch(request.getRequestType()) {
            case ADD:
                addInfo(request);
                break;
            case DELETE:
                deleteInfo(request);
                break;
            case EDIT:
                editInfo(request);
                break;
            case CANCEL:
                cancelInfo(request);
                break;
            case RETRIEVE:
                retrieveInfo(request);
                break;
            default:
                // panic
        }

        return "";
    }

    private int addInfo (ClientRequest request)
    {
        ridesDao.insert(request);
        return 0;
    }

    private int deleteInfo (ClientRequest request)
    {
        ridesDao.delete(request);
        return 0;
    }

    private int editInfo (ClientRequest request)
    {
        ridesDao.update(request);
        return 0;
    }

    private int cancelInfo (ClientRequest request)
    {
        //cancel something
        return 0;
    }

    private int retrieveInfo (ClientRequest request) {
        ridesDao.read(request);
        return 0;
    }

}
