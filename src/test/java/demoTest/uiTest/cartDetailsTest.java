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
public class cartDetailsTest extends baseTest {
  @Test
  public void checkcartDetails(){

      new productpg(driver).productNavigation().addproducttocart(jsonReader.getjson("product.productname")).clickviewcart().checkprodcutandprice(jsonReader.getjson("product.productname"),jsonReader.getjson("product.price"),jsonReader.getjson("product.quantity"),jsonReader.getjson("product.total"));

  }
    @Override
    protected String getjsonfile() {
        return "/cart";
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
