package demoTest.uiTest;

import demoplaze.drivers.UiTest;
import demoplaze.drivers.intialDriver;
import demoplaze.pages.componentNavbar;
import demoplaze.pages.productpg;
import demoplaze.utils.jsonReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
@UiTest
public class viewProductTest extends baseTest {


  @Test
  public void viewproducttest() {
      new productpg(driver).productNavigation().viewproductPg(jsonReader.getjson("product.name")).checkprductandprice(jsonReader.getjson("product.name"),jsonReader.getjson("product.price"));
  }
  @Test
  public void addreviewtest() {
      new productpg(driver).productNavigation().viewproductPg(jsonReader.getjson("product.name")).addReview(jsonReader.getjson("review.name"), jsonReader.getjson("review.email"), jsonReader.getjson("review.text")).CheckreviewSuccessMessage(jsonReader.getjson("review.message"));
  }
    @Override
    protected String getjsonfile() {
        return "/viewProduct";
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
