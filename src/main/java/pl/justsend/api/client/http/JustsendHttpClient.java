package pl.justsend.api.client.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import pl.justsend.api.client.http.utils.JSONSerializer;
import pl.justsend.api.client.model.JSResponse;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: posiadacz
 * Date: 21.03.18
 * Time: 16:09
 */
public class JustsendHttpClient {

    private static final Logger logger = Logger.getLogger(JustsendHttpClient.class);

    private final String USER_AGENT = "JustSendApiClient/1.0";
    private final String CONTENT_TYPE = "application/json";


    public JustsendHttpClient() {
    }

    public JSResponse doGet(String url) throws IOException, JustsendApiClientException {

        HttpClient client = HttpClientBuilder.create().build();

        HttpGet request = new HttpGet(url);
        request.addHeader("User-Agent", USER_AGENT);
        request.addHeader("Content-Type", CONTENT_TYPE);

        logger.info("Sending GET request to JUSTSEND_API_URL : " + url);
        HttpResponse response = client.execute(request);

        return processResponse(response);

    }

    public JSResponse doPost(String url, String data) throws IOException, JustsendApiClientException {

        HttpClient client = HttpClientBuilder.create().build();

        HttpPost request = new HttpPost(url);
        request.addHeader("User-Agent", USER_AGENT);
        request.addHeader("Content-Type", CONTENT_TYPE);

        StringEntity params = new StringEntity(data);
        request.setEntity(params);

        logger.info("Sending POST request to JUSTSEND_API_URL : " + url + " with content: \n" + data);
        HttpResponse response = client.execute(request);

        return processResponse(response);

    }

    public JSResponse doPut(String url, String data) throws IOException, JustsendApiClientException {

        HttpClient client = HttpClientBuilder.create().build();

        HttpPut request = new HttpPut(url);
        request.addHeader("User-Agent", USER_AGENT);
        request.addHeader("Content-Type", CONTENT_TYPE);

        StringEntity params = new StringEntity(data);
        request.setEntity(params);

        logger.info("Sending POST request to JUSTSEND_API_URL : " + url + " with content: \n" + data);
        HttpResponse response = client.execute(request);

        return processResponse(response);

    }

    private JSResponse processResponse(HttpResponse response) throws JustsendApiClientException, IOException {

        logger.info("processResponse : " + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuilder result = new StringBuilder();
        String line;

        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        if (response.getStatusLine().getStatusCode() == 200) {

            logger.info(result.toString());

            JSResponse jsResponse = JSONSerializer.deserialize(result.toString(), JSResponse.class);
            JSONObject jsonObject = new JSONObject(result.toString());

            if (!jsonObject.isNull("data")) {

                logger.info("Object: " + jsonObject.get("data").getClass());

                Object o = jsonObject.get("data");

                if (o instanceof JSONObject) {
                    jsResponse.setAdditionalData(jsonObject.getJSONObject("data").toString());
                } else if (o instanceof Integer) {
                    jsResponse.setAdditionalData(jsonObject.getInt("data") + "");
                } else if (o instanceof String) {
                    jsResponse.setAdditionalData(jsonObject.getString("data") + "");
                } else if (o instanceof Long) {
                    jsResponse.setAdditionalData(jsonObject.getLong("data") + "");
                } else if (o instanceof JSONArray) {
                    jsResponse.setAdditionalData(jsonObject.getJSONArray("data") + "");
                } else {
                    throw new JustsendApiClientException("Class " + o.getClass() + " not supported by response parser");
                }

            }
         
            return jsResponse;

        } else {
            logger.error(result.toString());
            throw new JustsendApiClientException("Connection failed. Response code: " + response.getStatusLine().getStatusCode());
        }

    }

}
