package app.service;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import world.core.Language;

import javax.ws.rs.Produces;

@Path("/languages")
@RegisterRestClient
@Produces(MediaType.APPLICATION_JSON)
public interface LanguageService {
    @GET
    List<Language> allLanguages();

}