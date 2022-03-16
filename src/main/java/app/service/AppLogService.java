package app.service;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import app.log.model.AppLog;

import javax.ws.rs.Produces;

@Path("/applogs")
@RegisterRestClient
@Produces(MediaType.APPLICATION_JSON)
public interface AppLogService {
    @GET
    List<AppLog> allAppLogs();

}