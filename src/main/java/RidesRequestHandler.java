import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.models.AddInfoRequest;

public class RidesRequestHandler implements RequestHandler<AddInfoRequest, String> {

    @Override
    public String handleRequest(AddInfoRequest request, Context context) {
        return "foo";
    }

}
