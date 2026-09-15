package demoTest.api;

import demoTest.uiTest.baseTest;
import demoplaze.api.userManageAPI;
import demoplaze.utils.TimeManager;
import demoplaze.utils.jsonReader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class registerwithapiTest extends baseTest {
    String timestamp= TimeManager.millisTime();

    @Test
    public void registerTestcaseWithApi(){
        new userManageAPI().CreateAccount(jsonReader.getjson("name"),
                jsonReader.getjson("email")+timestamp+"@example.com",
                jsonReader.getjson("password"),jsonReader.getjson("gender"),
                jsonReader.getjson("day"),jsonReader.getjson("month"),jsonReader.getjson("year"),jsonReader.getjson("firstName"),jsonReader.getjson("lastName"),
                jsonReader.getjson("company"), jsonReader.getjson("address"), jsonReader.getjson("address2"), jsonReader.getjson("country"),jsonReader.getjson("zipcode"),jsonReader.getjson("state"),jsonReader.getjson("city")
                ,jsonReader.getjson("mobile_number")).checkAccountApiCreated("User created!");
    }

    @Override
    protected String getjsonfile() {
        return "/register";
    }
    @BeforeMethod
    public void test() {
        jsonResder = new jsonReader(getjsonfile());

    }


}



