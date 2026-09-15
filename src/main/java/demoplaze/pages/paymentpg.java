package demoplaze.pages;

import demoplaze.drivers.intialDriver;
import demoplaze.utils.propertyReader;
import org.openqa.selenium.By;

public class paymentpg {
    intialDriver driver;

    public paymentpg(intialDriver driver) {
        this.driver = driver;
    }

    By cardname = By.cssSelector("input[name='name_on_card']");
    By cardnumber = By.cssSelector("input[name='card_number']");
    By cvc = By.cssSelector("input[name='cvc']");
    By expmonth = By.cssSelector("input[name='expiry_month']");
    By expyear = By.cssSelector("input[name='expiry_year']");
    By payandconfirm = By.cssSelector("button[id='submit']");
    By successmessage=By.cssSelector("h2>b");
    By downloadInvice=By.xpath("//a[text()='Download Invoice']");

    public paymentpg enterPaymentDetails(String name, String number, String cvc, String month, String year) {
        driver.ElementAction().sendText(cardname, name);
        driver.ElementAction().sendText(cardnumber, number);
        driver.ElementAction().sendText(this.cvc, cvc);
        driver.ElementAction().sendText(expmonth, month);
        driver.ElementAction().sendText(expyear, year);
        driver.ElementAction().click(payandconfirm);
        return this;
    }
    public paymentpg navigationtoPaymentPg(){
        driver.browserAction().navigate(propertyReader.getproperty("BaseUrl")+"/payment");
        return this;
    }
    public paymentpg navigationtodownloadPg(){
        driver.browserAction().navigate(propertyReader.getproperty("BaseUrl")+"/payment_done/500");
        return this;
    }
    public paymentpg clickonDownloadInvoice(){
        driver.ElementAction().click(downloadInvice);
        return this;
    }

    public paymentpg verifysuccesmessagee(String expected){
        String actual=driver.ElementAction().getText(successmessage);
        driver.hardAssertions().equals(actual,expected,"success message is not equals");
        return this;
    }
    public paymentpg checkInvoiceDownloaded(String expectedFileName) {
        driver.hardAssertions().assertFile(expectedFileName);
        return this;

    }
}