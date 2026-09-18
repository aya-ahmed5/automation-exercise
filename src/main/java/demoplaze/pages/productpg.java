package demoplaze.pages;

import demoplaze.drivers.intialDriver;
import demoplaze.utils.propertyReader;
import org.openqa.selenium.By;

public class productpg {
    intialDriver driver;

    public productpg(intialDriver driver) {
        this.driver = driver;
    }

    private By searchBox = By.cssSelector("input[id='search_product']");
    private By textmessage = By.xpath("(//div[@class='modal-body']/p[@class='text-center'])[1]");
    private By viewcart = By.xpath("(//div[@class='modal-body']/p[@class='text-center'])[2]/a");
    private By countineShping = By.xpath("//button[text()='Continue Shopping']");
    private By serchButton = By.cssSelector("button[id='submit_search']");


    //locators
    private By productlocatorname(String productname) {
       // return By.xpath("//div[@class='product-overlay']/div/p[text()='" + productname + "']/following-sibling::a");
       return By.xpath("//div[@class='features_items']//div[@class='productinfo text-center']/p[text()='" + productname + "']/following-sibling::a");
    }

    private By productNameforhover(String productname) {
        return By.xpath("//div[@class='features_items']//div[@class='productinfo text-center'] /p[text()='" + productname + "']");
       // return By.xpath("//div[@class='productinfo text-center'] /p[text()='" + productname + "']");
    }
    private By productPrice(String productname) {
        return By.xpath("//div[@class='productinfo text-center']/p[text()='" + productname +"']/preceding-sibling::h2");
    }

    private By viewproductlocator(String productname) {
        return By.xpath("//div[@class='productinfo text-center'] /p[text()='"+productname+"']/following::div[@class='choose'][1]");
    }
  //methods
  public productpg productNavigation() {
      driver.browserAction().navigate(propertyReader.getproperty("BaseUrl") + "/products");
      return this;
  }

    public productpg addproducttocart(String productname) {
        driver.ElementAction().hover(productNameforhover(productname));
       // driver.ElementAction().isElementVisible(productlocatorname(productname));
        driver.ElementAction().click(productlocatorname(productname));
        return this;
    }
    public cartpg clickviewcart() {
        driver.ElementAction().isElementVisible(viewcart);
        driver.ElementAction().click(viewcart);
        return new cartpg(driver);
    }
    public productpg clickcountineShping() {
        driver.ElementAction().click(countineShping);
        return this;
    }
    public productpg searchProduct(){
        driver.ElementAction().sendText(searchBox,"Blue Top").click(serchButton);
        return this;

    }
    public viewProductPg viewproductPg(String productname){
        driver.ElementAction().click(viewproductlocator(productname));
        return new viewProductPg(driver);

    }

    //validation check product added to cart
    public productpg checkproductaddedtocart(String expected) {
        String actual = driver.ElementAction().getText(textmessage);
        driver.hardAssertions().equals(actual, expected, "product added to cart message is not equal");
        return this;

    }
    public productpg checlproductPrice(String productname, String expectedPrice){
        String price= driver.ElementAction().getText(productPrice(productname));
        String name= driver.ElementAction().getText(productNameforhover(productname));
        driver.softAssertions().equals(price,expectedPrice,"product price is not equal");
        driver.softAssertions().equals(name,productname,"product name is not equal");
      return this;
    }

}