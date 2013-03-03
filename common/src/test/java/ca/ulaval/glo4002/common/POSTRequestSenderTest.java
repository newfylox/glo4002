package ca.ulaval.glo4002.common;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

public class POSTRequestSenderTest {

    private static final String A_MESSAGE = "message";
    private static final int AN_HTTP_ERROR_CODE = 500;

    @Mock
    private WebResource resource;

    @InjectMocks
    private POSTRequestSender postRequestSender;

    @Before
    public void initPOSTRequestSender() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = HTTPException.class)
    public void throwsHTTPExceptionWhenResponseIsNotOk() {
        ClientResponse clientResponse = mock(ClientResponse.class);
        Builder builder = mock(Builder.class);
        doReturn(builder).when(resource).type(anyString());
        doReturn(clientResponse).when(builder).post(ClientResponse.class, A_MESSAGE);
        doReturn(AN_HTTP_ERROR_CODE).when(clientResponse).getStatus();

        postRequestSender.sendRequest(anyString(), A_MESSAGE);
    }
}