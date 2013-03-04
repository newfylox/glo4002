package ca.ulaval.glo4002.common;

import java.net.URISyntaxException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class POSTRequestSender extends HTTPRequestSender {

    public POSTRequestSender(int port) {
        super(port);
    }

    protected POSTRequestSender(WebResource resource) {
        super(resource);
    }

    public String sendRequest(String resource, String messageToSend) throws URISyntaxException {
        // changeWebResource(resource);
        Client client = Client.create();
        webResource = client.resource(String.format("%s/%s", serverURL, resource));
        ClientResponse response = webResource.type(APPLICATION_TYPE).post(ClientResponse.class, messageToSend);

        if (response.getStatus() != RESPONSE_OK) {
            throw new HTTPException("Failed: HTTP error code: " + response.getStatus());
        }

        return response.getEntity(String.class);
    }
}