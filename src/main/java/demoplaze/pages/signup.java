package demoplaze.pages;

import demoplaze.drivers.intialDriver;
import demoplaze.utils.propertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class signup {
    private  intialDriver driver;
    public signup(intialDriver driver) {
        this.driver=driver;
    }
//locator
    private final By loginEmail= By.cssSelector("input[data-qa='login-email']");
    private final By loginPassword= By.cssSelector("input[data-qa='login-password']");
    private final By loginButton= By.cssSelector("button[data-qa='login-button']");
    private final By signupName= By.cssSelector("input[data-qa='signup-name']");
    private final By signupEmail= By.cssSelector("input[data-qa='signup-email']");
    private final By signupButton= By.cssSelector("button[data-qa='signup-button']");
    private final By loginErrorMessage=By.cssSelector("div[class='login-form']>form p");
    private final By signupErrorMessage=By.cssSelector("div[class='signup-form']>form p");
    private final By validateText=By.cssSelector("div>h1>span");

    // actions
   @Step("navigation to signup/login pg")
    public signup signupandlogin(){
       driver.browserAction().navigate(propertyReader.getproperty("BaseUrl")+"/signup");
       return this;
   }
   @Step("enter login fields")
    public signup logifields(String email, String password){
       driver.ElementAction().sendText(loginEmail,email);
       driver.ElementAction().sendText(loginPassword,password);
       driver.ElementAction().click(loginButton);
       return this;
   }
    @Step("enter signup fields")
    public signup signupfields(String name, String email){
        driver.ElementAction().sendText(signupName,name);
        driver.ElementAction().sendText(signupEmail,email);
        driver.ElementAction().click(signupButton);
        return this;
    }
    // assertions


@Step("check error message login")
    public signup checkloginmessagedisplyed(String expected){
       String actual=driver.ElementAction().getText(loginErrorMessage);
       driver.hardAssertions().equals(actual,expected,"login error message is not equal");
       return this;
}
    @Step("check error message signup")
    public signup checksignupmessagedisplyed(String expected){
        String actual=driver.ElementAction().getText(signupErrorMessage);
        driver.hardAssertions().equals(actual,expected,"signup error message is not equal");
        return this;
    }
    @Step("check that text  is displyed ")
    public signup checktextvisable(){
        driver.hardAssertions().assertisElementVisible(validateText);
        return this;
    }


}
