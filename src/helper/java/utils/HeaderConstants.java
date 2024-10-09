package helper.java.utils;

public class HeaderConstants {
        public static class APIConfig {
            public static final long TIMEOUT_DEFAULT = 30L;

            //Pagination
            public static final int ITEM_PER_PAGE = 20;
            public static final long QUERY_DEBOUNCE = 500L;
            public static final long SUBMIT_DELAY = 250L;
        }

        public static class APIHeaders {
            public static final String AUTHORIZATION = "Authorization";
            public static final String ACCESS_TOKEN = "Access_Token";
            public static final String ISANDROID = "IsAndroid";
            public static final String USER = "USER";
            public static final String NO = "NO";
            public static final String Language = "Language";
            public static final String App_VERSION = "AppVersion";
            public static final String IMEI = "imei";
            public static final String DEVICE_NAME = "DeviceName";
            public static final String DEVICE_ID = "DeviceID";
            public static final String OS_VERSION = "OSVersion";
            public static final String ANDROID_VERSION = "AndroidVersion";
            public static final String ANDROID_UPDATE = "AndroidUpdate";
            public static final String SEGMENT = "Segment";
            public static final String REFRESH_TOKEN = "RefreshToken";
            public static final String Client = "Client";
            public static final String Build_Version = "BuildVersion";
        }
}
