package BookMe;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import helper.TestCaseUtils;

import helper.java.Listeners.TestListeners;
import helper.java.constants.EndPoints;
import helper.java.models.booking.request.BookingRequest;
import helper.java.models.booking.request.Bookingdates;
import helper.java.models.booking.response.BookingResponse;
import helper.java.utils.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Bookings {
    private HashMap<String, String> testData;
    private TestCaseUtils testCaseUtils;
    private ConfigManager configManager;
    private ObjectMapper objectMapper;
    private TestListeners testListeners;

    @BeforeMethod(alwaysRun = true)
    public void initializeTestcase() throws IOException {
        testCaseUtils = new TestCaseUtils();
        configManager = new ConfigManager();
        objectMapper = new ObjectMapper();
        testListeners = new TestListeners();
        RestAssured.filters(new ResponseLoggingFilter(), new ResponseLoggingFilter());


    }

    @Test(priority = 1, groups = "Bookings")
    public void BookingDetails() throws Exception {
        BookingRequest bookingRequest = createBookingRequest();

        String donationJson = objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(bookingRequest);
        String apiPaymentResponse = testCaseUtils.makeApiRequest(bookingRequest, EndPoints.BOOKINGS);

        BookingResponse bookingResponse = objectMapper.readValue(apiPaymentResponse, BookingResponse.class);

        // Extract booking details from the response
        BookingResponse.CustomerBooking bookingDetails = bookingResponse.getBooking();

        JsonNode responseNode = objectMapper.readTree(apiPaymentResponse);

        Assert.assertEquals(bookingDetails.getFirstname(),"Ali", "First Name match!");
        Assert.assertEquals(bookingDetails.getLastname(), "Imran","Last Name match!");

        testListeners.loggerPass(EndPoints.BOOKINGS, responseNode.toPrettyString());

    }

    private BookingRequest createBookingRequest() {
        BookingRequest bookingModel = new BookingRequest();
        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin(getDates(0));
        bookingdates.setCheckout(getDates(1));
        bookingModel.setFirstname("Ali");
        bookingModel.setLastname("Imran");
        bookingModel.setTotalprice(77.7);
        bookingModel.setDepositpaid(true);
        bookingModel.setBookingdates(bookingdates);
        bookingModel.setAdditionalneeds("Nothing");

        return bookingModel;
    }


private String getDates(int num){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Get the current date
    LocalDate currentDate = LocalDate.now();

    // Increment the date by 1 day
    LocalDate incrementedDate = currentDate.plusDays(num);
    String formattedCurrentDate = incrementedDate.format(formatter);
    return formattedCurrentDate;
}

}
