package helper.java.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DbQueryExecutor {

    public void ByPassOTP() throws IOException {
        ConfigManager config = new ConfigManager();
        String currentURL = null;
        currentURL = config.configManagerValue("baseURL");
        String connectionURL = "";
        String tableName = "";


       /* if (currentURL.equals("http://XXX.XXX.XXX.XXX:XXX")) {
            connectionURL = "";
            tableName = "";
        } else if (currentURL.equals("")) {
            connectionURL = "";
            tableName = "";
        } else if (currentURL.equals("")) {
            connectionURL = "";
            tableName = "";
        } else if (currentURL.equals("")) {
            connectionURL = "";
            tableName = "";
        } else if (currentURL.equals("")) {
            connectionURL = "";
            tableName = "";
        } else {
            try {
                throw new Exception(" No Proper Environment Found For Database Connection");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
*/

        try {
            Connection conn = DriverManager.getConnection(connectionURL, "XXXXXXX_XXXX", "XXXXXXXXXXXX");
            Statement stmt = conn.createStatement();
            int rs;

            String query = "UPDATE "+tableName+" SET chk_status = 0 Where telephone = '"+config.configManagerValue("MSISDN")+"';";

            rs = stmt.executeUpdate(query);
            System.out.println("Following Number Of Rows Update : --> "+ rs);
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }


}