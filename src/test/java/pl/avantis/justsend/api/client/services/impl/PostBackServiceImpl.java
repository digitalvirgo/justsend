package pl.avantis.justsend.api.client.services.impl;

import pl.avantis.justsend.api.client.model.JSResponse;
import pl.avantis.justsend.api.client.pojo.PostBackFileDTO;
import pl.avantis.justsend.api.client.pojo.PostBackRecipientDTO;
import pl.avantis.justsend.api.client.pojo.PostBackResponseDTO;
import pl.avantis.justsend.api.client.services.impl.services.exception.JustsendApiClientException;

import java.io.IOException;

public class PostBackServiceImpl extends BaseService {


    private final String pageUrl;

    public PostBackServiceImpl(String pageUrl) {
        super("");
        this.pageUrl = pageUrl;
    }

    PostBackFileDTO getPostBackFile(final Long id) throws JustsendApiClientException {
        String url = new StringBuilder(pageUrl).append("/postBack/getPostBackFile/").append(id).toString();

        try {
            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, PostBackFileDTO.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    PostBackRecipientDTO getPostBackRecipient(final Long id) throws JustsendApiClientException {
        String url = new StringBuilder(pageUrl).append("/postBack/getPostBackRecipient/").append(id).toString();

        try {
            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, PostBackRecipientDTO.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    PostBackResponseDTO getPostBackResponse(final Long id) throws JustsendApiClientException {
        String url = new StringBuilder(pageUrl).append("/postBack/getPostBackResponse/").append(id).toString();

        try {
            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, PostBackResponseDTO.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }
}
