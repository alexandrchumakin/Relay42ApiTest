package http;

import common.Configurations;
import common.StringExtension;
import http.models.Visitor;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.UUID;

public class HttpReq {
    public Visitor emulateVisiting(String apiIdentifier){
        String partnerType = Configurations.getValueByKey("partnerType");
        String shopNumber = Configurations.getValueByKey("shopNumber");
        String reqUrl = String.format("site-%1$s/segments/stream/%2$s?partnerType=%3$s", shopNumber, apiIdentifier, partnerType);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(StringExtension.generateUrl(reqUrl));
        httpGet.addHeader(HttpHeaders.AUTHORIZATION, StringExtension.baseEncode());
        httpGet.addHeader(HttpHeaders.ACCEPT, "*/*");
        try (CloseableHttpResponse response1 = client.execute(httpGet)) {
            String userId = emulateVisit();
            final HttpEntity entity = response1.getEntity();
            if (entity != null) {
                try (InputStream inputStream = entity.getContent()) {
                    byte[] inputData = new byte[1024];
                    readInputStreamWithTimeout(inputStream, inputData);
                    String[] arrayOfChars = Arrays.stream(Arrays.toString(inputData).replace("[", "").replace("]", "").split(",")).map(String::trim).toArray(String[]::new);
                    response1.close();
                    System.out.println("Stream is closed.");
                    return new Visitor(StringExtension.charsToString(arrayOfChars), userId);
                } catch (Exception ex) {
                    System.out.println("Cannot get stream entity" + StringExtension.formatMessage(ex));
                }
            }
        } catch (Exception ex) {
            System.out.println(StringExtension.formatMessage(ex));
        }
        return new Visitor("", "");
    }

    private String emulateVisit(){
        UUID randomId = UUID.randomUUID();
        String unixTime = Long.toString(System.currentTimeMillis());
        String reqUrl = String.format("https://t.svtrd.com/t-1233?i=%1$s&e=true&et=Tomato&cb=%2$s", randomId, unixTime);
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.getInputStream();
            System.out.println(String.format("Emulate a visit to the product page is finished with userId '%1$s'.", randomId));
        } catch (Exception ex) {
            System.out.println("Cannot emulate a bid: " + StringExtension.formatMessage(ex));
        }
        return randomId.toString();
    }

    private static void readInputStreamWithTimeout(InputStream is, byte[] b) throws IOException  {
        int bufferOffset = 0;
        int timeout = Integer.parseInt(Configurations.getValueByKey("streamTimeout")) * 1000;
        long maxTimeMillis = System.currentTimeMillis() + timeout;
        while (System.currentTimeMillis() < maxTimeMillis && bufferOffset < b.length) {
            int readLength = java.lang.Math.min(is.available(), b.length - bufferOffset);
            int readResult = is.read(b, bufferOffset, readLength);
            if (readResult == -1) break;
            bufferOffset += readResult;
        }
    }
}
