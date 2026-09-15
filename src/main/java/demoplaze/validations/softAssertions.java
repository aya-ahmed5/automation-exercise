package demoplaze.validations;

import demoplaze.utils.logger.logs;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

public class softAssertions extends baseAssertions{
    private  static boolean used=false;
 private static  SoftAssert softassertion=new SoftAssert();
 public softAssertions(){

 }
    public softAssertions(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void assertTrueMethod(boolean value, String message) {
        used=true;
        Assert.assertTrue(value,message);

    }

    @Override
    protected void assertFalseMethod(boolean value, String message) {
        used=true;
        Assert.assertFalse(value, message);

    }

    @Override
    protected void assertEqualMethod(String actual, String expected, String message) {
        used=true;
        Assert.assertEquals(actual,expected,message);

    }
    public static void assertAll(){
       if(!used)return;
        try{
            softassertion.assertAll();
        }
        catch(AssertionError e){


            logs.errorMethod("error soft assertions",e.getMessage());
            throw  e;
        }
      finally{
            //resrt soft assertions
         softassertion= new SoftAssert();
        }
    }

}
