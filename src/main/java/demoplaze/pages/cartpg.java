package demoplaze.pages;

import demoplaze.drivers.intialDriver;
import demoplaze.utils.propertyReader;
import org.openqa.selenium.By;

public class cartpg {
    intialDriver driver;
    public cartpg(intialDriver driver) {
        this.driver=driver;
    }
    private String cart="/view_cart";
    private By proceedButton=By.xpath("//div/a[text()='Proceed To Checkout']");
    private By dynamicproductname(String product ){
        return By.xpath("//h4/a[text()='"+product+"']");
    }
    private By dynamicproductPrice(String product ){
        return By.xpath("(//h4/a[text()='"+product+"']/following::td[@class='cart_price']//p)[1]");
    }
    private By dynamicQuantity(String product){
       return By.xpath("(//h4 /a[text()='"+product+"']/following::td[@class='cart_quantity']/button)[1]");
    }
    private By dynamicTotalPrice(String product){
        return By.xpath("(//h4 /a[text()='"+product+"']/following::td[@class='cart_total']/p)[1]");
    }
    private  By dynamicRemoveProduct(String product){
        return By.xpath("(//h4/a[text()='"+product+"']/following::td[@class='cart_delete']/a)[1]");
   }
    public cartpg navicationtocart(){
        driver.browserAction().navigate(propertyReader.getproperty("BaseUrl")+cart);
        return this;
    }
    public cartpg checkprodcutandprice(String prodcut, String price,String quantity,String totalPrice){
        String actualproduct= driver.ElementAction().getText(dynamicproductname(prodcut));
        String actualproductprice= driver.ElementAction().getText(dynamicproductPrice(prodcut));
        String actualQunantity=driver.ElementAction().getText(dynamicQuantity(prodcut));
        String actualTotalprice=driver.ElementAction().getText(dynamicTotalPrice(prodcut));
        driver.softAssertions().equals(actualproduct,prodcut,"product name not equals");
        driver.softAssertions().equals(actualproductprice,price,"pricing is not equals");
        driver.softAssertions().equals(actualQunantity,quantity,"quantity is not equals");
        driver.softAssertions().equals(actualTotalprice,totalPrice,"total is not equals");
        return this;
    }
    public proccedPg procedtocheckout(){
        driver.ElementAction().click(proceedButton);
        return new proccedPg(driver);
    }
    public cartpg removeProduct(String product){
        driver.ElementAction().click(dynamicRemoveProduct(product));
        return this;
    }

}
