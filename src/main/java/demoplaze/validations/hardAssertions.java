package demoplaze.validations;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class hardAssertions extends baseAssertions{
    public hardAssertions(){
        super();
    }
    public hardAssertions(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void assertTrueMethod(boolean value, String message) {
        Assert.assertTrue(value,message);
    }

    @Override
    protected void assertFalseMethod(boolean value, String message) {
     Assert.assertFalse(value, message);
    }

    @Override
    protected void assertEqualMethod(String actual, String expected, String message) {
    Assert.assertEquals(actual,expected,message);
    }
}
