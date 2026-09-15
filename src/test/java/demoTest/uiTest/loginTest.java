package demoTest.uiTest;

import demoplaze.api.userManageAPI;
import demoplaze.drivers.UiTest;
import demoplaze.drivers.intialDriver;
import demoplaze.pages.componentNavbar;
import demoplaze.pages.signup;
import demoplaze.utils.TimeManager;
import demoplaze.utils.jsonReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
@UiTest
public class loginTest extends baseTest {
String timestamp= TimeManager.millisTime();


@Test
public void loginTestcs(){
    new userManageAPI().CreateAccountwithminimal(jsonReader.getjson("name"),(jsonReader.getjson("email")+timestamp+"@gmail.com"),jsonReader.getjson("password"),jsonReader.getjson("firstname"),jsonReader.getjson("lastname")).checkAccountApiCreated("User created!");
    new signup(driver).signupandlogin().logifields(jsonReader.getjson("email")+ timestamp+"@gmail.com",jsonReader.getjson("password")).checktextvisable();
    new userManageAPI().deleteAccount(jsonReader.getjson("email")+ timestamp+"@gmail.com",jsonReader.getjson("password")).checkAccountApiDeleted("Account deleted!");
}
@Test
public void invalidemaillogin(){
    new userManageAPI().CreateAccountwithminimal(jsonReader.getjson("name"),(jsonReader.getjson("email")+timestamp+"@gmail.com"),jsonReader.getjson("password"),jsonReader.getjson("firstname"),jsonReader.getjson("lastname")).checkAccountApiCreated("User created!");
    new signup(driver).signupandlogin().logifields(jsonReader.getjson("email")+"@gmail.com",jsonReader.getjson("password")).checkloginmessagedisplyed(jsonReader.getjson("message"));
    new userManageAPI().deleteAccount(jsonReader.getjson("email")+ timestamp+"@gmail.com",jsonReader.getjson("password")).checkAccountApiDeleted("Account deleted!");

}
@Test
    public void invalidpasswordlogin(){
        new userManageAPI().CreateAccountwithminimal(jsonReader.getjson("name"),(jsonReader.getjson("email")+timestamp+"@gmail.com"),jsonReader.getjson("password"),jsonReader.getjson("firstname"),jsonReader.getjson("lastname")).checkAccountApiCreated("User created!");
        new signup(driver).signupandlogin().logifields(jsonReader.getjson("email")+timestamp+"@gmail.com",jsonReader.getjson("password")+"1").checkloginmessagedisplyed(jsonReader.getjson("message"));
    new userManageAPI().deleteAccount(jsonReader.getjson("email")+ timestamp+"@gmail.com",jsonReader.getjson("password")).checkAccountApiDeleted("Account deleted!");

    }

    @Override
    protected String getjsonfile() {
        return "login";
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
