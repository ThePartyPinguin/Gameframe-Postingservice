package postingservice.model.dto.response;

import org.springframework.hateoas.ResourceSupport;

public class Response extends ResourceSupport {

    private int responseCode;
    private String responseMessage;

    public Response() {
    }

    public Response(int responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
