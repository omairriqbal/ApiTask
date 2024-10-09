
package helper.java.models.booking.request;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;


public class Bookingdates {

    @SerializedName("checkin")
    private String mCheckin;
    @SerializedName("checkout")
    private String mCheckout;

    public String getCheckin() {
        return mCheckin;
    }

    public void setCheckin(String checkin) {
        mCheckin = checkin;
    }

    public String getCheckout() {
        return mCheckout;
    }

    public void setCheckout(String checkout) {
        mCheckout = checkout;
    }

}
