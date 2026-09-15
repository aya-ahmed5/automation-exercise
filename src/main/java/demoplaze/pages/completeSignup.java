package demoplaze.pages;

import demoplaze.drivers.intialDriver;
import demoplaze.utils.propertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class completeSignup {
    private  intialDriver driver;
    public completeSignup(intialDriver driver) {
        this.driver=driver;
    }
    By name=By.cssSelector("input[data-qa='name']");
    By email=By.cssSelector("input[data-qa='email']");
    By password=By.cssSelector("input[data-qa='password']");
    By day=By.id("days");
    By month=By.id("months");
    By year=By.id("years");
    By newslatter=By.id("newsletter");
    By optain=By.id("optin");
    By firstName=By.cssSelector("input[data-qa='first_name']");
    By lastName=By.cssSelector("input[data-qa='last_name']");
    By company=By.cssSelector("input[data-qa='company']");
    By address=By.cssSelector("input[data-qa='address']");
    By address2=By.cssSelector("input[data-qa='address2']");
    By country=By.id("country");
    By state=By.cssSelector("input[data-qa='state']");
    By city=By.cssSelector("input[data-qa='city']");
    By zipcode=By.cssSelector("input[data-qa='zipcode']");
    By mobile_number=By.cssSelector("input[data-qa='mobile_number']");
    By createAccount=By.cssSelector("button[data-qa='create-account']");
    By validcreatemessage=By.cssSelector("h2 b");
    By coutine=By.cssSelector("a[data-qa='continue-button']");

    @Step("move to complete sign up pg")
    public completeSignup navigateCompleSignup(){
        driver.browserAction().navigate(propertyReader.getproperty("BaseUrl"+"/signup"));
        return this;
    }
    //locators

    @Step("choose gender {name}")
    private completeSignup choosegender(String name){
         By radioButton=By.cssSelector("span>input[value='"+name+"']");
         driver.ElementAction().click(radioButton);
         return this;
    }
    @Step("enter all signup fields")
    public completeSignup completesignupfields(String gender, String password, String day, String month, String year, String firstName, String lastName, String company,
                                       String address, String address2, String country, String state, String city, String zipcode, String mobile_number){
    choosegender(gender);
    driver.ElementAction().sendText(this.password,password);
    driver.ElementAction().dropdown(this.day,day);
    driver.ElementAction().dropdown(this.month,month);
    driver.ElementAction().dropdown(this.year,year);
    driver.ElementAction().click(newslatter);
    driver.ElementAction().click(optain);
    driver.ElementAction().sendText(this.firstName,firstName);
    driver.ElementAction().sendText(this.lastName,lastName);
    driver.ElementAction().sendText(this.company,company);
    driver.ElementAction().sendText(this.address,address);
    driver.ElementAction().sendText(this.address2,address2);
    driver.ElementAction().dropdown(this.country,country);
    driver.ElementAction().sendText(this.state,state);
    driver.ElementAction().sendText(this.city,city);
    driver.ElementAction().sendText(this.zipcode,zipcode);
    driver.ElementAction().sendText(this.mobile_number,mobile_number);
    driver.ElementAction().click(createAccount);
    return this;
    }
    @Step("click on countine button")
    public componentNavbar clickingcountine(String ecpected){
        driver.ElementAction().click(coutine);
        return new componentNavbar(driver);
    }

    @Step("validation account is created")
    public completeSignup checkCreateAccountMessage(String ecpected){
        String actual=driver.ElementAction().getText(validcreatemessage);
        driver.hardAssertions().equals(actual,ecpected,"account created message are not same");
        return this;
    }

    //validation
}
