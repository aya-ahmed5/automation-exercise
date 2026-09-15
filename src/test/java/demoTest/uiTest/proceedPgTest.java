package demoTest.uiTest;

import demoplaze.api.userManageAPI;
import demoplaze.drivers.UiTest;
import demoplaze.drivers.intialDriver;
import demoplaze.pages.cartpg;
import demoplaze.pages.componentNavbar;
import demoplaze.pages.productpg;
import demoplaze.pages.signup;
import demoplaze.utils.TimeManager;
import demoplaze.utils.jsonReader;
import org.testng.annotations.*;
@UiTest
public class proceedPgTest extends baseTest {
    String timestamp= TimeManager.millisTime();

    @Test
    public void regist() {
        new userManageAPI().CreateAccount(jsonReader.getjson("name"),
                jsonReader.getjson("email") + timestamp + "@example.com",
                jsonReader.getjson("password"), jsonReader.getjson("gender"),
                jsonReader.getjson("day"), jsonReader.getjson("month"), jsonReader.getjson("year"), jsonReader.getjson("firstName"), jsonReader.getjson("lastName"),
                jsonReader.getjson("company"), jsonReader.getjson("address"), jsonReader.getjson("address2"), jsonReader.getjson("country"), jsonReader.getjson("zipcode"), jsonReader.getjson("state"), jsonReader.getjson("city")
                , jsonReader.getjson("mobile_number")).checkAccountApiCreated("User created!");
    }
    @Test(dependsOnMethods = {"regist"})
    public void login() {
        new signup(driver).signupandlogin().logifields(jsonReader.getjson("email") + timestamp + "@example.com", jsonReader.getjson("password"));
    }
    @Test(dependsOnMethods = {"login","regist"})
    public void addToCart() {
        new productpg(driver).productNavigation().addproducttocart(jsonReader.getjson("productname"));
    }
    @Test(dependsOnMethods = {"addToCart","login","regist"})
    public void proceed() {
              new cartpg(driver).navicationtocart().procedtocheckout().checkAddressDetails(jsonReader.getjson("gender"),jsonReader.getjson("firstName"),jsonReader.getjson("lastName"),jsonReader.getjson("company"),jsonReader.getjson("address"), jsonReader.getjson("address2"),jsonReader.getjson("city"),jsonReader.getjson("state"),jsonReader.getjson("zipcode"),jsonReader.getjson("country"),jsonReader.getjson("mobile_number")).placeOrder(jsonReader.getjson("proccedmessage"));
    }
    @Test(dependsOnMethods = {"addToCart","login","regist","proceed"})
    public void deleteAccount() {
        new userManageAPI().deleteAccount(jsonReader.getjson("email")+ timestamp+"@example.com",jsonReader.getjson("password")).checkAccountApiDeleted("Account deleted!");
    }

    @Override
    protected String getjsonfile() {
        return "/procced";
    }
    @BeforeClass
    public void test() {
        driver = new intialDriver();
        jsonResder = new jsonReader(getjsonfile());
        new componentNavbar(driver).navifgationtonavbarPg();

    }

    @AfterClass
    public void teardown(){
        driver.quitDriver();
    }
}
