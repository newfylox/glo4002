package ca.ulaval.glo4002.common;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.net.URI;
import java.net.URISyntaxException;

public class POSTRequestSender {

    private static final String APPLICATION_TYPE = "application/json";
    private static final String SERVER_URL = "http://localhost:8080/";
    private static final int RESPONSE_OK = 200;

    private WebResource webResource;

    public POSTRequestSender() {
        Client client = Client.create();
        webResource = client.resource(SERVER_URL);
    }

    protected POSTRequestSender(final WebResource ressource) {
        webResource = ressource;
    }

    public String sendPostRequest(final String resource, final String messageToSend) {
        changeWebResource(resource);
        ClientResponse response = webResource.type(APPLICATION_TYPE).post(ClientResponse.class, messageToSend);

        if (response.getStatus() != RESPONSE_OK) {
            throw new HTTPException("Failed: HTTP error code: " + response.getStatus());
        }

        return response.toString();
    }

    private void changeWebResource(String resource) {
        try {
            URI resourceURL = new URI(String.format("%s/%s", SERVER_URL, resource));
            webResource.uri(resourceURL);
        } catch (URISyntaxException e) {
            // It is currently unrecoverable because the URLs are hard-coded. If
            // we can't create it here, then nobody can.
            throw new RuntimeException("There was an unrecoverable error trying to create a new resource URL");
        }
    }
}