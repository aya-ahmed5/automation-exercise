package demoTest.uiTest;

import demoplaze.api.userManageAPI;
import demoplaze.drivers.UiTest;
import demoplaze.drivers.intialDriver;
import demoplaze.pages.completeSignup;
import demoplaze.pages.componentNavbar;
import demoplaze.pages.signup;
import demoplaze.utils.TimeManager;
import demoplaze.utils.jsonReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
@UiTest
public class registerTest extends baseTest {
;

 @Test
 public void registerTestcase(){
     String timestamp=TimeManager.millisTime();
     new componentNavbar(driver).signup().signupfields(jsonReader.getjson("name"),jsonReader.getjson("email")+timestamp+"@example.com");
             new completeSignup(driver)
             .completesignupfields(
             jsonReader.getjson("gender"),jsonReader.getjson("password"),
             jsonReader.getjson("day"),jsonReader.getjson("month"),jsonReader.getjson("year"),jsonReader.getjson("firstName"),jsonReader.getjson("lastName"),
             jsonReader.getjson("company"), jsonReader.getjson("address"), jsonReader.getjson("address2"), jsonReader.getjson("country"),jsonReader.getjson("state"),jsonReader.getjson("city"),
             jsonReader.getjson("zipcode"),jsonReader.getjson("mobile_number")
     ).checkCreateAccountMessage(jsonReader.getjson("message"));

 }

@Test
public void registerTestcaseWithApi(){
    String timestamp=TimeManager.millisTime();
     new userManageAPI().CreateAccount(jsonReader.getjson("name"),
            jsonReader.getjson("email")+timestamp+"@example.com",
             jsonReader.getjson("password"),jsonReader.getjson("gender"),
             jsonReader.getjson("day"),jsonReader.getjson("month"),jsonReader.getjson("year"),jsonReader.getjson("firstName"),jsonReader.getjson("lastName"),
             jsonReader.getjson("company"), jsonReader.getjson("address"), jsonReader.getjson("address2"), jsonReader.getjson("country"),jsonReader.getjson("zipcode"),jsonReader.getjson("state"),jsonReader.getjson("city")
            ,jsonReader.getjson("mobile_number")).checkAccountApiCreated("User created!");
             new signup(driver).signupandlogin().signupfields(jsonReader.getjson("name"),jsonReader.getjson("email")+timestamp+"@example.com").checksignupmessagedisplyed("Email Address already exist!");
}



    @Override
    protected String getjsonfile() {
        return "/register";
    }
    @BeforeMethod
    public void test() {
        driver = new intialDriver();
        jsonResder = new jsonReader(getjsonfile());
        new componentNavbar(driver).navifgationtonavbarPg();

    }

    @AfterMethod
    public void teardown(){
        driver.quitDriver();
    }
}
