package pl.avantis.justsend.api.client.services.impl.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.avantis.justsend.api.client.model.JSResponse;
import pl.avantis.justsend.api.client.services.impl.services.exception.JustsendApiClientException;
import pl.avantis.justsend.api.client.services.impl.utils.JSONSerializer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.apache.http.util.TextUtils.isBlank;
import static pl.avantis.justsend.api.client.services.impl.utils.JSONSerializer.serialize;
import static pl.avantis.justsend.api.client.services.impl.utils.Printer.getNiceFormat;

public class JustsendHttpClient {

    private static final Logger logger = LoggerFactory.getLogger(JustsendHttpClient.class);

    private final String USER_AGENT = "JustSendApiClient/1.0";
    private final String CONTENT_TYPE = "application/json";
    private final String APP_KEY_HEADER_NAME = "App-Key";
    private String appKey;


    public JustsendHttpClient(String appKey) {
        this.appKey = appKey;
    }

    public JSResponse doGet(String url, String headerAppKey) throws IOException, JustsendApiClientException {

        HttpClient client = HttpClientBuilder.create().build();

        HttpGet request = new HttpGet(url);
        request.addHeader(APP_KEY_HEADER_NAME, headerAppKey);
        request.addHeader("User-Agent", USER_AGENT);
        request.addHeader("Content-Type", CONTENT_TYPE);

        logger.info("Sending GET request to JUSTSEND_API_URL:\n " + url);
        HttpResponse response = client.execute(request);

        return processResponse(response);

    }

    public JSResponse doGet(String url) throws IOException, JustsendApiClientException {

        HttpClient client = HttpClientBuilder.create().build();

        HttpGet request = new HttpGet(url);
        addHeaderFields(request);

        logger.info("Sending GET request to JUSTSEND_API_URL:\n " + url);
        HttpResponse response = client.execute(request);

        return processResponse(response);

    }

    public JSResponse doPost(String url, Object data) throws IOException, JustsendApiClientException {
        return doPost(url, serialize(data));
    }

    public JSResponse doPost(String url, String data) throws IOException, JustsendApiClientException {

        HttpClient client = HttpClientBuilder.create().build();

        HttpPost request = new HttpPost(url);
        addHeaderFields(request);

        if (data != null) {
            StringEntity params = new StringEntity(data);
            request.setEntity(params);
        }

        logger.info(format("Sending POST request to JUSTSEND_API_URL:\n %s\n with content: %s.", url, data == null ? "" : data));
        HttpResponse response = client.execute(request);

        return processResponse(response);

    }

    public JSResponse doMultiPartPost(String url, String fileInputName, File file, String textInputName, String text) throws IOException, JustsendApiClientException {

        HttpClient client = HttpClientBuilder.create().build();


        HttpPost request = new HttpPost(url);
        request.addHeader(APP_KEY_HEADER_NAME, appKey);
        request.addHeader("User-Agent", USER_AGENT);

        String name = "";
        if (file != null) {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

            FileBody fileBody = new FileBody(file);
            builder.addPart(fileInputName, fileBody);
            if (!isBlank(text)) {
                builder.addTextBody(textInputName, text);
            }

            HttpEntity entity = builder.build();
            request.setEntity(entity);
            name = file.getName();
        }

        logger.info("Sending POST request to JUSTSEND_API_URL:\n " + url + "\n with file: " + name);

        HttpResponse response = client.execute(request);

        return processResponse(response);

    }

    public JSResponse doDelete(String url) throws IOException, JustsendApiClientException {

        HttpClient client = HttpClientBuilder.create().build();

        HttpDelete request = new HttpDelete(url);
        addHeaderFields(request);

        HttpResponse response = client.execute(request);

        return processResponse(response);
    }


    public JSResponse doPut(String url, Object data) throws IOException, JustsendApiClientException {
        return doPut(url, serialize(data));
    }

    public JSResponse doPut(String url, String data) throws IOException, JustsendApiClientException {

        HttpClient client = HttpClientBuilder.create().build();

        HttpPut request = new HttpPut(url);
        addHeaderFields(request);

        StringEntity params = new StringEntity(data);
        request.setEntity(params);

        logger.info("Sending POST request to JUSTSEND_API_URL:\n " + url + "\n with content: " + data);
        HttpResponse response = client.execute(request);

        return processResponse(response);

    }

    public JSResponse doPut(String url) throws IOException, JustsendApiClientException {

        HttpClient client = HttpClientBuilder.create().build();

        HttpPut request = new HttpPut(url);
        addHeaderFields(request);

        logger.info("Sending PUT request to JUSTSEND_API_URL:\n " + url);
        HttpResponse response = client.execute(request);

        return processResponse(response);

    }

    private void addHeaderFields(HttpRequestBase request) {
        request.addHeader(APP_KEY_HEADER_NAME, appKey);
        request.addHeader("User-Agent", USER_AGENT);
        request.addHeader("Content-Type", CONTENT_TYPE);
    }

    public String doGetByte(String url) throws IOException, JustsendApiClientException {
        HttpClient client = HttpClientBuilder.create().build();

        HttpGet request = new HttpGet(url);
        addHeaderFields(request);

        logger.info("Sending GET request to JUSTSEND_API_URL:\n " + url);
        HttpResponse response = client.execute(request);

        return processByteResponse(response);
    }

    private String processByteResponse(HttpResponse response) throws JustsendApiClientException, IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        String result = rd.lines().collect(Collectors.joining("\n"));

        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 200) {
            return result;
        } else {
            logger.error(getNiceFormat(result));
            throw new JustsendApiClientException("Connection failed. Response code: " + response.getStatusLine().getStatusCode());
        }
    }

    private JSResponse processResponse(HttpResponse response) throws JustsendApiClientException, IOException {


        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        String result = rd.lines().collect(Collectors.joining());

        logger.info(format("Response StatusCode = %s.", response.getStatusLine().getStatusCode()));
        logger.debug(format("Response content = %s.", getNiceFormat(result)));

        if (response.getStatusLine().getStatusCode() == 200) {

            JSResponse jsResponse = JSONSerializer.deserialize(result, JSResponse.class);
            JSONObject jsonObject = new JSONObject(result);

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
            logger.error(getNiceFormat(result));
            throw new JustsendApiClientException("Connection failed. Response code: " + response.getStatusLine().getStatusCode());
        }
    }
}
