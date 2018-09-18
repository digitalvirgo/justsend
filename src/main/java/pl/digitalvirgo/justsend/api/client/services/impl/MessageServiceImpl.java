package pl.digitalvirgo.justsend.api.client.services.impl;

import pl.digitalvirgo.justsend.api.client.model.JSResponse;
import pl.digitalvirgo.justsend.api.client.model.Message;
import pl.digitalvirgo.justsend.api.client.model.VoiceMessage;
import pl.digitalvirgo.justsend.api.client.services.impl.enums.BulkVariant;
import pl.digitalvirgo.justsend.api.client.services.impl.services.MessageService;
import pl.digitalvirgo.justsend.api.client.services.impl.services.exception.JustsendApiClientException;
import pl.digitalvirgo.justsend.api.client.services.impl.services.methods.MessageMethods;
import pl.digitalvirgo.justsend.api.client.services.impl.utils.JSONSerializer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class MessageServiceImpl extends BaseService implements MessageService {

    /**
     * @param appKey Klucz api
     */

    public MessageServiceImpl(String appKey) {
        super(appKey);
    }

    @Override
    public Long sendMessage(Message message) throws JustsendApiClientException {

        String url = createURL(MessageMethods.SEND_MESSAGE.getPath());
        String json = JSONSerializer.serialize(message);

        try {

            JSResponse jsResponse = justsendHttpClient.doPost(url, json);
            return processResponse(jsResponse, Long.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }

    }

    @Override
    public Long sendMessage(String to, String from, String message, BulkVariant bulkVariant) throws JustsendApiClientException {

        try {
            message = URLEncoder.encode(message, "UTF-8");
            from = URLEncoder.encode(from, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new JustsendApiClientException("Conversion message to HTML failed: " + e.getMessage());
        }

        String url = createURL(MessageMethods.SEND_MESSAGE_GET.getPath(), "to", to, "from", from, "message", message, "bulkVariant", bulkVariant.toString());

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, Long.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }

    }

    @Override
    public Long sendMessageECO(String to, String message) throws JustsendApiClientException {

        try {
            message = URLEncoder.encode(message, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new JustsendApiClientException("Conversion message to HTML failed: " + e.getMessage());
        }

        String url = createURL(MessageMethods.SEND_MESSAGE_ECO.getPath(), "to", to, "message", message);

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, Long.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public Long sendVoiceMessage(VoiceMessage voiceMessage) throws JustsendApiClientException {

        String url = createURL("/v2/message/sendVoice");

        try {

            JSResponse jsResponse = justsendHttpClient.doPost(url, voiceMessage);
            return processResponse(jsResponse, Long.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }
}
