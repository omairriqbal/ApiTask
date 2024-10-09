package helper;

import BookMe.Bookings;

import helper.java.models.booking.request.BookingRequest;
import helper.java.utils.ConfigManager;
import helper.java.utils.TestUtils;
import io.restassured.response.Response;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Random;

import static io.restassured.RestAssured.*;

public class TestCaseUtils {


    public String makeApiRequest(BookingRequest bookingModel, String End_Point) throws Exception {

        ConfigManager configManager = new ConfigManager();
        String BaseUrl = configManager.configManagerValue("baseURL");
        baseURI = BaseUrl;
        // Sending Post Request
        Response apiResponse;
        apiResponse = given().
                header("Content-Type", "application/json").
                body(bookingModel).
                baseUri(BaseUrl).
                basePath(End_Point).
                when().
                post().
                then().
                statusCode(200).
                assertThat().
                log().
                all().
                extract().response();
        System.out.println("apiResponse.asString() ->"+apiResponse.asString());

        return apiResponse.asString();
    }


}
