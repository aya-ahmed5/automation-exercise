package demoplaze.pages;

import demoplaze.drivers.intialDriver;
import org.openqa.selenium.By;

public class viewProductPg {
    intialDriver driver;
    public viewProductPg(intialDriver driver) {
        this.driver=driver;
    }
    By cartButton=By.cssSelector("div[class='product-information'] button");
    By reviewName=By.cssSelector("form[id='review-form'] input[id='name']");
    By reviewEmail=By.cssSelector("form[id='review-form'] input[id='email']");
    By reviewText=By.cssSelector("textarea[name='review']");
    By reviewSubmitButton=By.id("button-review");
    By successReviewMessage=By.cssSelector("div[id='review-section'] span");
    public By productNameDynamicLocator(String productname){
        return By.xpath("//div[@class='product-information'] /h2[text()='"+productname+"']");
    }

    public By priceDynamicLocator(String productname){
        return By.xpath("//div[@class='product-information'] /h2[text()='"+productname+"']/following::span[2]");
    }
    public viewProductPg addProductToCart(){
        driver.ElementAction().click(cartButton);
        return this;
    }
    public viewProductPg addReview(String name, String email, String review){
        driver.ElementAction().sendText(reviewName,name);
        driver.ElementAction().sendText(reviewEmail,email);
        driver.ElementAction().sendText(reviewText,review);
        driver.ElementAction().click(reviewSubmitButton);
        return this;
    }
    //assertions
    public viewProductPg checkprductandprice(String productname, String price){
        String actualproductname=driver.ElementAction().getText(productNameDynamicLocator(productname));
        String actualprice=driver.ElementAction().getText(priceDynamicLocator(productname));
        driver.softAssertions().equals(actualproductname,productname,"product name is not equal");
        driver.softAssertions().equals(actualprice,price,"product price is not equal");
        return this;
    }
    public viewProductPg CheckreviewSuccessMessage(String expected){
        String actual=driver.ElementAction().getText(successReviewMessage);
        driver.hardAssertions().equals(actual,expected,"review success message is not equal");
        return this;
    }


}
