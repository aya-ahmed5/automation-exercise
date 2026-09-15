package demoplaze.pages;

import demoplaze.drivers.intialDriver;
import demoplaze.utils.propertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class componentNavbar {
   private final intialDriver driver;
    public componentNavbar(intialDriver driver){
        this.driver=driver;
    }
    //locator
    private final   By home= By.cssSelector("li>[href='/']");
    private final By products=By.cssSelector("li>[href='/products']");
    private final By cart=By.cssSelector("li>[href='/view_cart']");
    private final By signup=By.cssSelector("li>[href='/login']");
    private final By contactus=By.cssSelector("li>[href='/contact_us']");
    private final By logout=By.cssSelector("li>[href='/logout']");
    private final By deleteAccount=By.cssSelector("li>[href='/delete_account']");
    private final By video=By.cssSelector("li>[href='https://www.youtube.com/c/AutomationExercise']");
    private final By automationText=By.cssSelector("h1>span");
    private final By loginName=By.cssSelector("li>a b");

    //action

    public componentNavbar navifgationtonavbarPg(){
        driver.browserAction().navigate(propertyReader.getproperty("BaseUrl"));

        return this;
    }
    @Step("move to home pg")
    public componentNavbar homepg(){
        driver.ElementAction().click(home);
        return this;
    }
    @Step("move to product pg")
    public productpg productpg(){
        driver.ElementAction().click(products);
        return new productpg(driver);
    }
    @Step("move to cart pg")
    public cartpg cartpg(){
        driver.ElementAction().click(cart);
        return new cartpg(driver);
    }
    @Step("move to signup/login pg")
    public signup signup(){
        driver.ElementAction().click(signup);
       return new signup(driver);
    }
    @Step("move to contact us pg")
    public contactus contactus(){
        driver.ElementAction().click(contactus);
        return new contactus(driver);
    }
    @Step("move to logout pg")
    public logoutpg logout(){
        driver.ElementAction().click(logout);
        return new logoutpg(driver);
    }
    @Step("move to deleteAccount pg")
    public deleteAccount delete(){
        driver.ElementAction().click(deleteAccount);
        return new deleteAccount(driver);
    }
    @Step("move to video pg")
    public videototrialpg videototrial(){
        driver.ElementAction().click(video);
        return new videototrialpg(driver);
    }
//validation
 @Step("check automation test is displyed")
    public componentNavbar checkAutomationTestDisplyed(){
        driver.hardAssertions().assertisElementVisible(automationText);
        return this;
 }
    @Step("check automation test is displyed")
    public componentNavbar checkloginName(String expected ){
       String name= driver.ElementAction().getText(loginName);
        driver.hardAssertions().equals(name,expected,"error to find login name");
        return this;
    }

}
