package ca.ulaval.glo4002.common;

import java.net.URI;
import java.net.URISyntaxException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public abstract class HTTPRequestSender {

    protected static final String APPLICATION_TYPE = "application/json";
    protected String serverURL = "http://localhost";
    protected static final int RESPONSE_OK = 200;

    protected int port;

    protected WebResource webResource;

    public HTTPRequestSender(int port) {
        serverURL = String.format("%s%s", serverURL, "/");
        Client client = Client.create();
        webResource = client.resource(serverURL);
    }

    protected HTTPRequestSender(final WebResource ressource) {
        webResource = ressource;
    }

    protected void changeWebResource(String resource) {
        try {
            URI resourceURL = new URI(String.format("%s/%s", serverURL, resource));
            webResource.uri(resourceURL);
        } catch (URISyntaxException e) {
            // It is currently unrecoverable because the URLs are hard-coded. If
            // we can't create it here, then nobody can.
            throw new RuntimeException("There was an unrecoverable error trying to create a new resource URL");
        }
    }
}
