package helper.java.utils;


import com.fasterxml.jackson.databind.ObjectMapper;

import helper.java.helpers.ServiceHelper;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class APIHeaders  {
    Map<String, Object> headersFromYml;
    Map<String,Object> getHeaderListHashMap;
    ConfigManager configManager;

    public Map<String, Object> GetHeadersForLogin(String UserState) throws Exception {
        configManager = new ConfigManager();
        System.out.println("*** Creating Headers ***");
        System.out.println(" ");

        ServiceHelper serviceHelperObj = new ServiceHelper();
        //ServiceHelper serviceObj = new ServiceHelper();
        ObjectMapper objMap = new ObjectMapper();
        Yaml yaml = new Yaml();

        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("PostHeaders.yml");

         headersFromYml = yaml.load(inputStream);

        String deviceID = configManager.configManagerValue("imei");
        String deviceName = configManager.configManagerValue("deviceName");
        String androidVersion = configManager.configManagerValue("androidVersion");
        String appVersion = configManager.configManagerValue("appVersion");
        String client = configManager.configManagerValue("client");
        String language = configManager.configManagerValue("language");
        String isAndroid = configManager.configManagerValue("isAndroid");
        String androidUpdate = configManager.configManagerValue("buildVersion");

        String user_msisdn = TestUtils.Encrypt(configManager.configManagerValue("MSISDN"), configManager.configManagerValue("EncKey1Android"));
        headersFromYml.put(HeaderConstants.APIHeaders.NO, user_msisdn);
        headersFromYml.put(HeaderConstants.APIHeaders.DEVICE_NAME, deviceName);
        headersFromYml.put(HeaderConstants.APIHeaders.DEVICE_ID, deviceID);
        headersFromYml.put(HeaderConstants.APIHeaders.IMEI, deviceID);
        headersFromYml.put(HeaderConstants.APIHeaders.ANDROID_VERSION, androidVersion);
        headersFromYml.put(HeaderConstants.APIHeaders.App_VERSION, appVersion);
        headersFromYml.put(HeaderConstants.APIHeaders.ANDROID_UPDATE, androidUpdate);
        headersFromYml.put(HeaderConstants.APIHeaders.ISANDROID, isAndroid);
        headersFromYml.put(HeaderConstants.APIHeaders.Language, language);
        headersFromYml.put(HeaderConstants.APIHeaders.Client, client);

        /*if (UserState == "loggedIn") {
            headersFromYml.remove("USER");
            getHeaderList.put("x-auth-token",LoginObj.getInstance().loginSession);
            String user_id = TestUtils.Encrypt(configManager.configManagerValue("MSISDN"), configManager.configManagerValue("EncKey1Android"));
            headersFromYml.put("x-lemon-sign", serviceHelperObj.getSecureKey(requestBody));
            //headersFromYml.remove("x-auth-token");
        }*/
        return headersFromYml;
    }


}
