package demoplaze.pages;

import demoplaze.drivers.intialDriver;
import demoplaze.utils.propertyReader;
import org.openqa.selenium.By;

public class proccedPg  {
    intialDriver driver;
    public proccedPg(intialDriver driver) {
        this.driver=driver;
    }
    private By totalPriceProduct=By.xpath("//h4/b/following::p[@class='cart_total_price']");
    private By messageArea=By.tagName("textarea");
    private By placeOrderButton=By.xpath("//div/a[text()='Place Order']");
    private By firstAndlastNmae=By.xpath("//ul[@id='address_delivery']/li[@class='address_firstname address_lastname']");
    private By company=By.xpath("(//ul[@id='address_delivery']/li[@class='address_address1 address_address2'])[1]");
    private By address1=By.xpath("(//ul[@id='address_delivery']/li[@class='address_address1 address_address2'])[2]");
    private By address2=By.xpath("(//ul[@id='address_delivery']/li[@class='address_address1 address_address2'])[3]");
    private By cityStateZip=By.xpath("//ul[@id='address_delivery']/li[@class='address_city address_state_name address_postcode']");
    private By country=By.xpath("//ul[@id='address_delivery']/li[@class='address_country_name']");
    private By Phone=By.xpath("//ul[@id='address_delivery']/li[@class='address_phone']");

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

    public void navigationtoprocceding(){
        driver.browserAction().navigate(propertyReader.getproperty("BaseUrl")+"/checkout");
    }
    public paymentpg placeOrder(String Message){
        driver.ElementAction().sendText(messageArea,Message) .click(placeOrderButton);
        return new paymentpg(driver);

    }

    public proccedPg checkprodcutandprice(String prodcut, String price, String quantity, String totalPrice,String totalpriceProduct){
        String actualproduct= driver.ElementAction().getText(dynamicproductname(prodcut));
        String actualproductprice= driver.ElementAction().getText(dynamicproductPrice(prodcut));
        String actualQunantity=driver.ElementAction().getText(dynamicQuantity(prodcut));
        String actualTotalprice=driver.ElementAction().getText(dynamicTotalPrice(prodcut));
        String actualTotalpriceproduct=driver.ElementAction().getText(totalPriceProduct);
        driver.softAssertions().equals(actualproduct,prodcut,"product name not equals");
        driver.softAssertions().equals(actualproductprice,price,"pricing is not equals");
        driver.softAssertions().equals(actualQunantity,quantity,"quantity is not equals");
        driver.softAssertions().equals(actualTotalprice,totalPrice,"total is not equals");
        driver.softAssertions().equals(actualTotalpriceproduct,totalpriceProduct,"totalpriceProdcut is not equals");
        return this;
    }
    public proccedPg checkAddressDetails(String gender,String fname,String lName, String companyname, String address1Name, String address2Name, String city,String State,String zipName, String countryName , String phoneNumber){
       String firstAndlastNmaeAddress=  driver.ElementAction().getText(firstAndlastNmae);
       String comppanyAddress=driver.ElementAction().getText(company);
       String Address1=driver.ElementAction().getText(address1);
       String Address2=driver.ElementAction().getText(address2);
       String cityStateZipAddress=driver.ElementAction().getText(cityStateZip);
       String countryAddress=driver.ElementAction().getText( country);
       String phoneAddress=driver.ElementAction().getText(Phone);
        driver.softAssertions().equals(firstAndlastNmaeAddress,(gender+". "+fname+" "+lName)," name not equals");
        driver.softAssertions().equals(comppanyAddress,companyname,"company is not equals");
        driver.softAssertions().equals(Address1,address1Name,"address1 is not equals");
        driver.softAssertions().equals(Address2,address2Name,"address2 is not equals");
        driver.softAssertions().equals(cityStateZipAddress,(city+" "+State+" "+zipName),"cityStatezipName is not equals");
        driver.softAssertions().equals(countryAddress,countryName,"countryName is not equals");
        driver.softAssertions().equals(phoneAddress,phoneNumber,"cityStatezipName is not equals");

        return this;

    }


}
