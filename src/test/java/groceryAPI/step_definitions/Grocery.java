package groceryAPI.step_definitions;

import groceryAPI.utilities.ConfigurationReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;


public class Grocery {

    private static final String BASE_URL = ConfigurationReader.get("groceryAPIURI");

    RequestSpecification request;
    Response response;
    String baseURI_AllGrocery="";

    String requestHeader="";
    String authorizationKey="";
    String authorizationValue="";

    String param = "";
    String postURL = "";

    @Given("set GET allgrocery service api endpoint")
    public void setGETAllgroceryServiceApiEndpoint() {

        baseURI_AllGrocery = BASE_URL+"allGrocery/";

    }

    @When("set request header")
    public void setRequestHeader() {

        requestHeader = "ContentType.JSON";

    }

    @And("set authorization")
    public void setAuthorization() {

        authorizationKey ="x-api-key";
        authorizationValue ="6fec3ab0";

    }


    @And("body contains data")
    public void bodyContainsData() {

        System.out.println(response.body().asString());
        Assert.assertNotNull(response.body().asString());

    }

    @Given("set GET allgrocery service api endpoint with param {string}")
    public void setGETAllgroceryServiceApiEndpointWithParam(String name) {

        baseURI_AllGrocery = BASE_URL+"allGrocery/";
        param = name;

    }

    @And("body contains data related param")
    public void bodyContainsDataRelatedParam() {

        System.out.println(response.body().asString());
        Assert.assertTrue(response.body().asString().contains(param));

    }

    @Given("set GET wrong service api endpoint with param {string}")
    public void setGETWrongServiceApiEndpointWithParam(String name) {

        baseURI_AllGrocery = BASE_URL+"allGrocery";
        param = name;

    }

    @And("body contains -error- message")
    public void bodyContainsErrorMessage() {

        System.out.println(response.body().asString());
        Assert.assertTrue(response.body().asString().contains("error"));

    }

    @Given("set POST employee service api endpoint")
    public void setPOSTEmployeeServiceApiEndpoint() {

        postURL = baseURI+"add";

    }

    @And("send a POST HTTP request")
    public void sendAPOSTHTTPRequest() {

                response = given().accept(requestHeader)
                .header(authorizationKey, authorizationValue)
                .when().post(BASE_URL+"add");

                 response.prettyPrint();

    }

    @Then("Verify status code {int}")
    public void verifyStatusCode(int statusCode) {

        Assert.assertEquals(statusCode, response.getStatusCode());

    }

    @And("send a GET HTTP request")
    public void sendAGETHTTPRequest() {

                if(param.isEmpty()) {
                    response = given().accept(requestHeader)
                            .header(authorizationKey, authorizationValue)
                            .when().get(baseURI_AllGrocery);
                }else if(!param.isEmpty()){
                    response = given().accept(requestHeader)
                            .header(authorizationKey, authorizationValue)
                            .when().get(baseURI_AllGrocery+param);

                }

    }
}
