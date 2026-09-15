package demoplaze.api;
import demoplaze.utils.propertyReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class builder {
    static String  baseUri= propertyReader.getproperty("baseapi");
    public  static RequestSpecification requestSpecification(Map<String,?>addParameter){
      return  new RequestSpecBuilder().setBaseUri(baseUri).setContentType(ContentType.URLENC).addFormParams(addParameter).build();

    }
}
