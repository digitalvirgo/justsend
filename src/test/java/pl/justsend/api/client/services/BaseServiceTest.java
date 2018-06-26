package pl.justsend.api.client.services;

import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.justsend.api.client.model.JSResponse;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

import static org.testng.Assert.*;

public class BaseServiceTest {

    private BaseService baseService;

    @BeforeClass
    public void setUp() {
        baseService = new BaseService("") {};
    }

    @Test
    public void testAddParameters() throws JustsendApiClientException {
        String url = baseService.addParameters("url", "param1", "paramValue1", "param2", "paramValue2");
        Assertions.assertThat(url).isEqualTo("url?param1=paramValue1&param2=paramValue2");
    }

    @Test(expectedExceptions = {JustsendApiClientException.class}, expectedExceptionsMessageRegExp = "Incorrect parameters number, should be allays two multiplayer!")
    public void when_parameterDontHaveValue_then_throwException() throws JustsendApiClientException {
        String url = baseService.addParameters("url", "param1", "paramValue1", "param2");
    }
}