package ca.ulaval.glo4002.common;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.Client;

public class HTTPRequestSenderTest {

    private static final String INVALID_URL = "@!#!(%@*#$)(!)@#~~{}>>..";
    private static final String A_MESSAGE = "message";
    private static final int A_PORT = 8080;

    private Client client;

    private POSTRequestSender postRequestSender;

    @Before
    public void initHomeConnectionHandler() {
        client = mock(Client.class);
        postRequestSender = new POSTRequestSender(A_PORT, client);
    }

    @Test(expected = RuntimeException.class)
    public void throwsRuntimeExceptionWhenURLIsInvalid() {
        postRequestSender.sendRequest(INVALID_URL, A_MESSAGE);
    }
}
