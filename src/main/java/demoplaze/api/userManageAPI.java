package demoplaze.api;

import demoplaze.utils.logger.logs;
import demoplaze.validations.hardAssertions;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class userManageAPI {
    hardAssertions hardAssertion;
    Response response;
    String createAccountEndpoint="/createAccount";
    String deleteAccountEndpoint="/deleteAccount";
    public userManageAPI(){
      this.hardAssertion=  new hardAssertions();
    }
    //Creta accout
    @Step("craete account using api")
    public userManageAPI CreateAccount(String name, String email, String password, String title , String birth_date, String birth_month, String birth_year, String firstname, String lastname, String company, String address1, String address2, String country, String zipcode, String state, String city, String mobile_number){
        Map<String,String>addData=new HashMap<>();
        addData.put("name", name);
        addData.put("email", email);
        addData.put("password", password);
        addData.put("title", title);
        addData.put("birth_date", birth_date);
        addData.put("birth_month", birth_month);
        addData.put("birth_year", birth_year);
        addData.put("firstname", firstname);
        addData.put("lastname", lastname);
        addData.put("company", company);
        addData.put("address1", address1);
        addData.put("address2", address2);
        addData.put("country", country);
        addData.put("zipcode", zipcode);
        addData.put("state", state);
        addData.put("city", city);
        addData.put("mobile_number", mobile_number);
       response= RestAssured.given().spec(builder.requestSpecification(addData)).post(createAccountEndpoint);
        logs.infoMethod(response.asPrettyString());
        return this;
    }

    @Step("craete account with minimal data api")
    public userManageAPI CreateAccountwithminimal (String name, String email, String password,  String firstname, String lastname ) {
        Map<String, String> addData = new HashMap<>();
        addData.put("name", name);
        addData.put("email", email);
        addData.put("password", password);
        addData.put("title", "title");
        addData.put("birth_date", "birth_date");
        addData.put("birth_month", "birth_month");
        addData.put("birth_year", "birth_year");
        addData.put("firstname", firstname);
        addData.put("lastname", lastname);
        addData.put("company", "company");
        addData.put("address1", "address1");
        addData.put("address2", "address2");
        addData.put("country", "country");
        addData.put("zipcode", "zipcode");
        addData.put("state", "state");
        addData.put("city", "city");
        addData.put("mobile_number", "mobile_number");
        response=RestAssured.given().spec(builder.requestSpecification(addData)).post(createAccountEndpoint);
        logs.infoMethod(response.asPrettyString());
        return this;
    }
@Step("check aacount created by api")
    public userManageAPI checkAccountApiCreated(String expected){
        hardAssertion.equals(response.jsonPath().get("message"),expected,"account using api meaasge is not equals ");

return this;
}
@Step("delete account using api")
    public userManageAPI deleteAccount(String email , String password){
        Map<String,String>deleteData=new HashMap<>();
        deleteData.put("email",email);
        deleteData.put("password",password);

     response= RestAssured.given().spec(builder.requestSpecification(deleteData)).delete(deleteAccountEndpoint);
     return this;
}
    @Step("check aacount deleted by api")
    public userManageAPI checkAccountApiDeleted(String expected){
        hardAssertion.equals(response.jsonPath().get("message"),expected,"account using api meaasge is not equals ");
        return this;
    }
}
