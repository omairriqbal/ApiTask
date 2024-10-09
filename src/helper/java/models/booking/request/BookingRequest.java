
package helper.java.models.booking.request;

import com.google.gson.annotations.SerializedName;


public class BookingRequest {

    @SerializedName("additionalneeds")
    private String mAdditionalneeds;
    @SerializedName("bookingdates")
    private Bookingdates mBookingdates;
    @SerializedName("depositpaid")
    private Boolean mDepositpaid;
    @SerializedName("firstname")
    private String mFirstname;
    @SerializedName("lastname")
    private String mLastname;
    @SerializedName("totalprice")
    private Double mTotalprice;

    public String getAdditionalneeds() {
        return mAdditionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        mAdditionalneeds = additionalneeds;
    }

    public Bookingdates getBookingdates() {
        return mBookingdates;
    }

    public void setBookingdates(Bookingdates bookingdates) {
        mBookingdates = bookingdates;
    }

    public Boolean getDepositpaid() {
        return mDepositpaid;
    }

    public void setDepositpaid(Boolean depositpaid) {
        mDepositpaid = depositpaid;
    }

    public String getFirstname() {
        return mFirstname;
    }

    public void setFirstname(String firstname) {
        mFirstname = firstname;
    }

    public String getLastname() {
        return mLastname;
    }

    public void setLastname(String lastname) {
        mLastname = lastname;
    }

    public Double getTotalprice() {
        return mTotalprice;
    }

    public void setTotalprice(Double totalprice) {
        mTotalprice = totalprice;
    }

}
