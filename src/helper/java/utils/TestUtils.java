package helper.java.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.spec.X509EncodedKeySpec;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class TestUtils {


    public static final String RSA_ALGORITHM = "RSA";
    public static  String Date = "2022-08-09 13:00:00"; //2022-08-10 12:00:00

    public String getDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String generateRandomNumber(){

        ThreadLocalRandom random = ThreadLocalRandom.current();
        String num = String.valueOf(random.nextLong(10_000_000_000L, 100_000_000_000L));
        return num;
    }


    //Encryption Method
    // Changed PKCS7Padding to PKCS5Padding as cyper in java does not support PKCS7
    public static String Encrypt(String text, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] keyBytes = new byte[16];
        byte[] b = key.getBytes(StandardCharsets.UTF_8);
        int len = b.length;
        if (len > keyBytes.length)
            len = keyBytes.length;
        System.arraycopy(b, 0, keyBytes, 0, len);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        byte[] results = new byte[text.length()];
        try {
            results = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            System.out.println("Error in Decryption" + e);
            throw e;
        }

        return Base64.getEncoder().encodeToString(results);
    }


    //Decryption Method
    public static String Decrypt(String text, String key) throws Exception {

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] keyBytes = new byte[16];

        byte[] b = key.getBytes(StandardCharsets.UTF_8);
        int len = b.length;
        if (len > keyBytes.length)
            len = keyBytes.length;
        System.arraycopy(b, 0, keyBytes, 0, len);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] results = new byte[text.length()];
        try {
            //results = cipher.doFinal(android.util.Base64.decode(text, 0));
            results = cipher.doFinal(Base64.getDecoder().decode(text));

        } catch (Exception e) {
            System.out.println("Erron in Decryption ====>" + e);
        }
        System.out.println("Data ====> " + new String(results, StandardCharsets.UTF_8));
        //DataHandler.terminology = "";
        return new String(results, StandardCharsets.UTF_8); // it returns the result as a String
    }


    public static String generateIMEI() {
        int pos;
        int[] str = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int sum = 0;
        int final_digit;
        int t;
        int len_offset;
        int len = 15;
        String imei = "";

        String[] rbi = new String[]{"01", "10", "30", "33", "35", "44", "45", "49", "50", "51", "52", "53", "54", "86", "91", "98", "99"};
        String[] arr = rbi[(int) Math.floor(Math.random() * rbi.length)].split("");
        str[0] = Integer.parseInt(arr[0]);
        str[1] = Integer.parseInt(arr[1]);
        pos = 2;

        while (pos < len - 1) {
            str[pos++] = (int) (Math.floor(Math.random() * 10) % 10);
        }

        len_offset = (len + 1) % 2;
        for (pos = 0; pos < len - 1; pos++) {
            if ((pos + len_offset) % 2 != 0) {
                t = str[pos] * 2;
                if (t > 9) {
                    t -= 9;
                }
                sum += t;
            } else {
                sum += str[pos];
            }
        }

        final_digit = (10 - (sum % 10)) % 10;
        str[len - 1] = final_digit;

        for (int d : str) {
            imei += String.valueOf(d);
        }

        return imei;
    }

    public static String RSAEncode(String publicKey, String data) {
        try {
            publicKey = publicKey.replace("\n", "");

            byte[] pks = Base64.getDecoder().decode(publicKey);
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(pks);
            KeyFactory kf = KeyFactory.getInstance(RSA_ALGORITHM);
            PublicKey pk;
            pk = kf.generatePublic(x509EncodedKeySpec);
            Cipher cp = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cp.init(Cipher.ENCRYPT_MODE, pk);
            return Base64.getEncoder().encodeToString(cp.doFinal(data.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {

            //LogUtil.error("SecurityUtil",e);
            return "";
        }
    }


    public static String GenerateRandomUdidAndImei()
    {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //System.out.println(uuid+"|"+generateIMEI());
        return (uuid+"|"+generateIMEI());
    }

    public static String getMd5(Object input)
    {
        try {

            //Object mapper to convert input json object to string
            ObjectMapper objMap = new ObjectMapper();
            String jsonToString = objMap.writeValueAsString(input);

            // Creating string for md5 processing
            String md5ConvertibleString = jsonToString+"XqwTIcLvfW78yuhP";

            //String x_lemon_sign = TestUtils.getMd5(spaceRemoved);

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(md5ConvertibleString.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException | JsonProcessingException e) {
            throw new RuntimeException("*** Not able to make Md5 *** --->> "+e);
        }
    }

    public static boolean IsValuePresent(Response obj, String objPath, String objValue) throws ParseException {

        ResponseBody body = obj.getBody();
        try {
            return body.jsonPath().get(objPath).equals(objValue);
        }
        catch (Exception Ex)
        {
            throw new RuntimeException ("*** Not able to Parse/Get Value from response body"+ Ex);
        }
    }

    public static boolean ContiansValue(Response obj, String objPath, String objValue)
    {

        ResponseBody body = obj.getBody();
        try {

            return body.jsonPath().get(objPath).toString().contains(objValue);
        }
        catch (Exception Ex)
        {
            throw new RuntimeException ("*** Not able to Parse/Get Value from response body"+ Ex);
        }

    }

    public static boolean IsParamValueNull(Response obj, String objPath) throws ParseException {

        ResponseBody body = obj.getBody();
        try {
            List<String> wholePath = new ArrayList<>();
            wholePath = Arrays.asList(objPath.split("\\."));
            int pathSize = wholePath.size();

            String actualPath = wholePath.get(pathSize-1);

            if(body.jsonPath().get().toString().contains(actualPath))
            {
                return body.jsonPath().get(objPath) == null;
            }
            else {
                throw new RuntimeException("*** Path Does Not Exist");
            }
        }
        catch (Exception Ex)
        {
            throw new RuntimeException ("*** Not able to Parse/Get Value from response body"+ Ex);
        }

    }

    public static boolean IsParamValueNotNull(Response obj, String objPath) {

        ResponseBody body = obj.getBody();
        try {
            List<String> wholePath = new ArrayList<>();
            wholePath = Arrays.asList(objPath.split("\\."));
            int pathSize = wholePath.size();

            String actualPath = wholePath.get(pathSize-1);

            /*if(body.jsonPath().get().toString().contains(actualPath))
            {
                if (body.jsonPath().get(objPath) != null)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else {
                throw new RuntimeException("*** Path Does Not Exist");
            }*/

            // Cleaned code
            if(body.jsonPath().get().toString().contains(actualPath)) {
                return body.jsonPath().get(objPath) != null;
            } else {
                throw new RuntimeException("*** Path Does Not Exist");
            }
        }
        catch (Exception Ex)
        {
            throw new RuntimeException ("*** Not able to Parse/Get Value from response body"+ Ex);
        }

    }

    public static HashMap<String, String> parseStringXML(InputStream file) throws Exception {
        HashMap<String, String> stringHashMap = new HashMap<>();

        // Get document builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Build document
        Document document = builder.parse(file);

        //normalize the xml strutuctre and its very important
        document.getDocumentElement().normalize();

        //Get all the elements
        NodeList nodeList = document.getElementsByTagName("string");
        for (int iter = 0; iter < nodeList.getLength(); iter++) {
            Node node = nodeList.item(iter);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                stringHashMap.put(element.getAttribute("name"), element.getTextContent());
            }
        }
        return stringHashMap;
    }


}
