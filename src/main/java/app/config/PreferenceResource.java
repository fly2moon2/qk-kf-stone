package app.config;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.net.URI;
//import java.math.BigDecimal;
import java.util.List;

@Path("/preferences")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PreferenceResource {

/*   @ConfigProperty(name="app.lang.default")
  String langDefault; */

  @GET
  public List<Preference> allPreferences() {
    return Preference.listAll();
  }

  @GET
  @Path("/{code}")
  public Preference getPreference(@PathParam("code") String code) {
    PreferenceCode prefCde = PreferenceCode.findByCode(code);
    Preference pref = null;

    if (prefCde == null) {
      throw new WebApplicationException("Preference code of " + code + " is not found.", 404);
    } else {
      pref = Preference.findByCode(prefCde);
      if (pref == null) {
        throw new WebApplicationException("Preference with code of " + code + " is not found.", 404);
      }
    }

    return pref;
  }

/*   @GET
  @Path("/{code}")
  public PreferenceCode getPreference(@PathParam("code") String code) {
    PreferenceCode prefCde = PreferenceCode.findByCode(code);

    if (prefCde == null) {
      throw new WebApplicationException("Preference code of " + code + " is not found.", 404);
    }

    return prefCde;
  } */

/*   @POST
  @Transactional
  public Response createLanguage(Language lang) {
    if (lang.id != null) {
      throw new WebApplicationException("Id was invalidly set on request.", 400);
    }

    lang.persist();
    return Response.status(201).entity(lang).build();
  } */

/*   @POST
  @Transactional
  public Response createLanguageSample2(Language lang) {
      lang.persist();
      return Response.created(URI.create("/persons/" + lang.id)).build();
  }

  @PUT
  @Path("/{code}")
  @Transactional
  public Language update(@PathParam("code") String code, Language lang) {
      Language entity = Language.findByCode(code);
      if(entity == null) {
          throw new NotFoundException();
      }

      // map all fields from the person parameter to the existing entity
      entity.lang = lang.code;

      return entity;
  }

  @DELETE
  @Path("/{code}")
  @Transactional
  public void delete(@PathParam("code") String code) {
    Language entity = Language.findByCode(code);
    if(entity == null) {
        throw new NotFoundException();
    }
      entity.delete();
  } */
/*
  @GET
  @Path("/search/{name}")
  public Person search(@PathParam("name") String name) {
      return Person.findByName(name);
  }
*/
  @GET
  @Path("/count")
  public Long count() {
      return Preference.count();
  }
/*
  @PUT
  @Path("{langCde}/withdrawal")
  @Transactional
  public Language update(@PathParam("accountNumber") Long accountNumber, String amount) {
    Account entity = Account.findByAccountNumber(accountNumber);

    if (entity == null) {
      throw new WebApplicationException("Account with " + accountNumber + " does not exist.", 404);
    }

    if (entity.accountStatus.equals(AccountStatus.OVERDRAWN)) {
      throw new WebApplicationException("Account is overdrawn, no further withdrawals permitted", 409);
    }

    entity.withdrawFunds(new BigDecimal(amount));

    return entity;
  }

  @PUT
  @Path("{accountNumber}/deposit")
  @Transactional
  public Account deposit(@PathParam("accountNumber") Long accountNumber, String amount) {
    Account entity = Account.findByAccountNumber(accountNumber);

    if (entity == null) {
      throw new WebApplicationException("Account with " + accountNumber + " does not exist.", 404);
    }

    entity.addFunds(new BigDecimal(amount));
    return entity;
  }
*/
/*
  @DELETE
  @Path("{langCde}")
  @Transactional
  public Response closeAccount(@PathParam("accountNumber") Long accountNumber) {
    Account account = Account.findByAccountNumber(accountNumber);

    if (account == null) {
      throw new WebApplicationException("Account with " + accountNumber + " does not exist.", 404);
    }

    account.close();
    return Response.noContent().build();
  }
*/

  @Provider
  public static class ErrorMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {

      int code = 500;
      if (exception instanceof WebApplicationException) {
        code = ((WebApplicationException) exception).getResponse().getStatus();
      }

      JsonObjectBuilder entityBuilder = Json.createObjectBuilder()
          .add("exceptionType", exception.getClass().getName())
          .add("code", code);

      if (exception.getMessage() != null) {
        entityBuilder.add("error", exception.getMessage());
      }

      return Response.status(code)
          .entity(entityBuilder.build())
          .build();
    }
  }
}
