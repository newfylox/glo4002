package ca.ulaval.glo4002.centralServer.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.centralServer.treatment.AlarmListTreatment;

@Path("/alarm/")
public class AlarmListResource {

    private static final int HTTP_OK = 200;

    private AlarmListTreatment alarmListTreatment = new AlarmListTreatment();

    @POST
    @Path("{userId}")
    public Response askForAlarmFromUser(@PathParam("userId") String userIdPassedByGetRequest) {
        String log = alarmListTreatment.retrieveLogFromUser(userIdPassedByGetRequest);
        return Response.status(HTTP_OK).entity(log).build();
    }
}
