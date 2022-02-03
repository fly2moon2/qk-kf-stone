package world.core;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
//import java.math.BigDecimal;
import java.util.List;

@Path("/languages")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LanguageResource {

  @GET
  public List<Language> allLanguages() {
    return Language.listAll();
  }

  @GET
  @Path("/{code}")
  public Language getLanguage(@PathParam("code") String code) {
    Language language = Language.findByCode(code);

    if (language == null) {
      throw new WebApplicationException("Language code of " + code + " is not found.", 404);
    }

    return language;
  }

  @POST
  @Transactional
  public Response createLanguage(Language language) {
    if (language.id != null) {
      throw new WebApplicationException("Id was invalidly set on request.", 400);
    }

    language.persist();
    return Response.status(201).entity(language).build();
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
