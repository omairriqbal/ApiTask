
package helper.java.models.booking.response;

import com.google.gson.annotations.SerializedName;
import helper.java.models.booking.request.Bookingdates;


public class BookingResponse {

    @SerializedName("booking")
    private CustomerBooking mBooking;
    @SerializedName("bookingid")
    private Integer mBookingid;

    public CustomerBooking getBooking() {
        return mBooking;
    }

    public void setBooking(CustomerBooking booking) {
        mBooking = booking;
    }

    public Integer getBookingid() {
        return mBookingid;
    }

    public void setBookingid(Integer bookingid) {
        mBookingid = bookingid;
    }

    public class CustomerBooking {

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
        private Long mTotalprice;

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

        public Long getTotalprice() {
            return mTotalprice;
        }

        public void setTotalprice(Long totalprice) {
            mTotalprice = totalprice;
        }

    }
}
