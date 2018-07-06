package pl.avantis.justsend.api.client.services.impl.http.utils;

import org.testng.annotations.Test;

public class PrinterTest {

    @Test
    public void testGetNiceFormat() {
        //given
        String input= "{\"responseCode\":\"OK\",\"errorId\":0,\"message\":\"Successful\",\"data\":{\"name\":\"Name-1243583452_2018-07-06-10-46-56\",\"from\":\"sender68775\",\"message\":\"Test message\",\"sendDate\":\"2018-07-06T10:46:55+02:00\",\"bulkVariant\":\"ECO\",\"personalizedMessage\":false,\"language\":null,\"groupSmsContent\":null,\"groupIds\":[2393],\"id\":18239,\"estimatedEndDate\":\"2018-07-06T10:47:15+02:00\",\"costInPoints\":3,\"shortName\":\"Name-1243583452\",\"bulkStatus\":\"TO_SEND\",\"bulkSize\":1,\"autoSend\":null,\"voiceDuration\":null}}";

        //when
        String niceFormat = Printer.getNiceFormat(input);


        //then
    }
}