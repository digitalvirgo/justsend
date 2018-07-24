package pl.avantis.justsend.api.client.services.impl;

import com.google.gson.reflect.TypeToken;
import pl.avantis.justsend.api.client.model.JSResponse;
import pl.avantis.justsend.api.client.services.impl.utils.JSONSerializer;
import pl.avantis.justsend.api.client.services.impl.services.BlackListService;
import pl.avantis.justsend.api.client.services.impl.services.exception.JustsendApiClientException;
import pl.avantis.justsend.api.client.services.impl.services.methods.BlackListMethods;

import java.io.IOException;
import java.util.List;

public class BlackListServiceImpl extends BaseService implements BlackListService {

    /**
     * @param appKey Klucz api
     */

    public BlackListServiceImpl(String appKey) {
        super(appKey);
    }

    @Override
    public String addNumbersToBlackList(List<String> numbers) throws JustsendApiClientException {

        String url = createURL(BlackListMethods.ADD_NUMBERS_TO_BLACKLIST.getPath());
        String json = JSONSerializer.serialize(numbers);

        try {

            JSResponse jsResponse = justsendHttpClient.doPut(url, json);
            return processResponse(jsResponse, String.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }

    }

    @Override
    public String removeNumbersFromBlackList(List<String> numbers) throws JustsendApiClientException {

        String url = createURL(BlackListMethods.REMOVE_NUMBERS_FROM_BLACKLIST.getPath());
        String json = JSONSerializer.serialize(numbers);

        try {

            JSResponse jsResponse = justsendHttpClient.doPut(url, json);
            return processResponse(jsResponse, String.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }

    }

    @Override
    public List<String> retrieveNumbers() throws JustsendApiClientException {

        String url = createURL(BlackListMethods.RETRIEVE_NUMBERS.getPath());

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, new TypeToken<List<String>>() {
            }.getType());

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }

    }

}
