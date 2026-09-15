package demoTest.uiTest;

import demoplaze.api.userManageAPI;
import demoplaze.drivers.UiTest;
import demoplaze.drivers.intialDriver;
import demoplaze.pages.*;
import demoplaze.utils.TimeManager;
import demoplaze.utils.jsonReader;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
@UiTest
public class dawnloadInvoiceTest extends baseTest {
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
        new cartpg(driver).navicationtocart().procedtocheckout().checkAddressDetails(jsonReader.getjson("gender"),jsonReader.getjson("firstName"),jsonReader.getjson("lastName"),jsonReader.getjson("company"),jsonReader.getjson("address"), jsonReader.getjson("address2"),jsonReader.getjson("city"),jsonReader.getjson("state"),jsonReader.getjson("zipcode"),jsonReader.getjson("country"),jsonReader.getjson("mobile_number"));
    }
    @Test(dependsOnMethods = {"addToCart","login","regist","proceed"})
    public void payment() {
        new proccedPg(driver).placeOrder(jsonReader.getjson("hint")).enterPaymentDetails(jsonReader.getjson("cardname"),jsonReader.getjson("cardnumber"),jsonReader.getjson("cvc"),jsonReader.getjson("expirymonth"),jsonReader.getjson("expiryyear")).verifysuccesmessagee(jsonReader.getjson("messagePlaceOrder"));
    }
    @Test(dependsOnMethods = {"addToCart","login","regist","proceed","payment"})
    public void downloadInvoice() {
        new paymentpg(driver).navigationtodownloadPg().clickonDownloadInvoice().checkInvoiceDownloaded(jsonReader.getjson("filename"));
    }
    @Test(dependsOnMethods = {"addToCart","login","regist","proceed","payment","downloadInvoice"})
    public void deleteAccount() {
        new userManageAPI().deleteAccount(jsonReader.getjson("email")+ timestamp+"@example.com",jsonReader.getjson("password")).checkAccountApiDeleted("Account deleted!");
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




    @Override
    protected String getjsonfile() {
        return "/payment";
    }
}
