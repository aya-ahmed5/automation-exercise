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
public class addToCartTest extends baseTest {


   @Test(description = "This test case is to add product to cart")
   public void addproducttocarttest(){
       new productpg(driver).productNavigation().addproducttocart(jsonReader.getjson("product1.productname")).checkproductaddedtocart(jsonReader.getjson("message.addcart"));
   }
   @Test(description = "This test case is to search product")
   public void searchofproductTc() {
       new productpg(driver).productNavigation().searchProduct().checlproductPrice(jsonReader.getjson("product1.productname"),jsonReader.getjson("product1.price"));

   }
    @Override
    protected String getjsonfile() {
        return "/product";
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

