package world.core;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import world.core.*;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
@TestMethodOrder(OrderAnnotation.class)
public class LanguageResourceTest {
  @Test
  @Order(2)
  void testRetrieveAll() {
    Response result =
        given()
          .when().get("/languages")
          .then()
            .statusCode(200)
            .body(
                containsString("English"),
                containsString("Chinese")
            )
            .extract()
            .response();

    List<Language> languages = result.jsonPath().getList("$");
    assertThat(languages, not(empty()));
    assertThat(languages, hasSize(2));
  }

  @Test
  @Order(3)
  void testGetLanguage() {
    Language language =
        given()
            .when().get("/languages/{code}", "EN")
            .then()
              .statusCode(200)
              .extract()
              .as(Language.class);

    assertThat(language.code, equalTo("EN"));
    assertThat(language.lang, equalTo("English"));



  }

  @Test
  @Order(1)
  @Transactional
  void testCreateLanguage() {
    Language lang = new Language();
    //lang.id=3L;
    lang.code="fr";
    lang.lang="French";


    Locale e=new Locale();
    //e.id=1L;
    e.code="fh";
    e.locale="french locale";
    e.lang=lang;
    //lang.locales.add(e);

    lang.addLocale(e);
    lang.persist();

    given()
        .contentType(ContentType.JSON)
        .body(lang)
        .when().post("/languages")
        .then()
        .statusCode(400);
        // .statusCode(200); 

  }
/*
  @Test
  @Order(3)
  void testCreateAccount() throws Exception {
    Account newAccount = new Account();
    newAccount.accountNumber = 324324L;
    newAccount.customerNumber = 112244L;
    newAccount.customerName = "Sandy Holmes";
    newAccount.balance = new BigDecimal("154.55");

    Account returnedAccount =
        given()
          .contentType(ContentType.JSON)
          .body(newAccount)
          .when().post("/accounts")
          .then()
            .statusCode(201)
            .extract()
            .as(Account.class);

    assertThat(returnedAccount, notNullValue());
    newAccount.id = returnedAccount.id;
    assertThat(returnedAccount, equalTo(newAccount));

    Response result =
        given()
            .when().get("/accounts")
            .then()
            .statusCode(200)
            .body(
                containsString("Debbie Hall"),
                containsString("David Tennant"),
                containsString("Alex Kingston"),
                containsString("Sandy Holmes")
            )
            .extract()
            .response();

    List<Account> accounts = result.jsonPath().getList("$");
    assertThat(accounts, not(empty()));
    assertThat(accounts, hasSize(9));
  }

  @Test
  @Order(4)
  void testCloseAccount() {
    given()
        .when().delete("/accounts/{accountNumber}", 5465)
        .then()
        .statusCode(204);

    Account account =
        given()
            .when().get("/accounts/{accountNumber}", 5465)
            .then()
            .statusCode(200)
            .extract()
            .as(Account.class);

    assertThat(account.accountNumber, equalTo(5465L));
    assertThat(account.customerName, equalTo("Alex Trebek"));
    assertThat(account.customerNumber, equalTo(776868L));
    assertThat(account.balance, equalTo(new BigDecimal("0.00")));
    assertThat(account.accountStatus, equalTo(AccountStatus.CLOSED));
  }

  @Test
  @Order(5)
  void testDeposit() {
    Account account =
        given()
            .when().get("/accounts/{accountNumber}", 123456789)
            .then()
            .statusCode(200)
            .extract()
            .as(Account.class);

    BigDecimal deposit = new BigDecimal("154.98");
    BigDecimal balance = account.balance.add(deposit);

    account =
        given()
            .contentType(ContentType.JSON)
            .body(deposit.toString())
            .when().put("/accounts/{accountNumber}/deposit", 123456789)
            .then()
            .statusCode(200)
            .extract()
            .as(Account.class);

    assertThat(account.accountNumber, equalTo(123456789L));
    assertThat(account.customerName, equalTo("Debbie Hall"));
    assertThat(account.customerNumber, equalTo(12345L));
    assertThat(account.accountStatus, equalTo(AccountStatus.OPEN));
    assertThat(account.balance, equalTo(balance));

    account =
        given()
            .when().get("/accounts/{accountNumber}", 123456789)
            .then()
            .statusCode(200)
            .extract()
            .as(Account.class);

    assertThat(account.accountNumber, equalTo(123456789L));
    assertThat(account.customerName, equalTo("Debbie Hall"));
    assertThat(account.customerNumber, equalTo(12345L));
    assertThat(account.accountStatus, equalTo(AccountStatus.OPEN));
    assertThat(account.balance, equalTo(balance));
  }

  @Test
  @Order(6)
  void testWithdrawal() {
    Account account =
        given()
            .when().get("/accounts/{accountNumber}", 78790)
            .then()
            .statusCode(200)
            .extract()
            .as(Account.class);

    BigDecimal withdrawal = new BigDecimal("23.82");
    BigDecimal balance = account.balance.subtract(withdrawal);

    account =
        given()
            .contentType(ContentType.JSON)
            .body(withdrawal.toString())
            .when().put("/accounts/{accountNumber}/withdrawal", 78790)
            .then()
            .statusCode(200)
            .extract()
            .as(Account.class);

    assertThat(account.accountNumber, equalTo(78790L));
    assertThat(account.customerName, equalTo("Vanna White"));
    assertThat(account.customerNumber, equalTo(444222L));
    assertThat(account.accountStatus, equalTo(AccountStatus.OPEN));
    assertThat(account.balance, equalTo(balance));

    account =
        given()
            .when().get("/accounts/{accountNumber}", 78790)
            .then()
            .statusCode(200)
            .extract()
            .as(Account.class);

    assertThat(account.accountNumber, equalTo(78790L));
    assertThat(account.customerName, equalTo("Vanna White"));
    assertThat(account.customerNumber, equalTo(444222L));
    assertThat(account.accountStatus, equalTo(AccountStatus.OPEN));
    assertThat(account.balance, equalTo(balance));
  }

  @Test
  void testGetAccountFailure() {
     given()
        .when().get("/accounts/{accountNumber}", 11)
        .then()
        .statusCode(404);
  }

  @Test
  void testCreateAccountFailure() {
    Account newAccount = new Account();
    newAccount.id = 12L;
    newAccount.accountNumber = 90909L;
    newAccount.customerNumber = 888898L;
    newAccount.customerName = "Barry Mines";
    newAccount.balance = new BigDecimal("878.32");

    given()
        .contentType(ContentType.JSON)
        .body(newAccount)
        .when().post("/accounts")
        .then()
        .statusCode(400);
  }
  */
}
