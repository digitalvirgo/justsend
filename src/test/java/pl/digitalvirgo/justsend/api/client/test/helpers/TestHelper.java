package pl.digitalvirgo.justsend.api.client.test.helpers;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import pl.digitalvirgo.justsend.api.client.model.Bulk;
import pl.digitalvirgo.justsend.api.client.model.BulkResponse;
import pl.digitalvirgo.justsend.api.client.services.impl.Constants;
import pl.digitalvirgo.justsend.api.client.services.impl.enums.FileNamePartEnum;

import java.io.File;
import java.time.LocalDate;

import static java.lang.Long.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

public class TestHelper {

    public static final String APP_KEY = "JDJhJDEyJDN2c1NWQ2o1ZHh1U3M1WHpmYXpFN3VhRGZQSUlub3hwT3hIRzU1bkJ4MWpjbVZPaFAxcEdP";
    public static final String APP_KEY_FOR_PREFIX_PREFIX_TEST = "JDJhJDEyJFRCSC9qTFJIQzNmQmU0b0hqNDREY09qdmZ5ZktONkFTbi8uR0JQak9qQnVIbFlQZm96YUJl";
    public static final String APP_KEY_ADMINISTRATOR = "123456";
    public static final String PROD_URL = "justsend.pl";

    public static ReflectionToStringBuilder toString(Object object) {
        return new ReflectionToStringBuilder(object, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public static LocalDate daysBeforeNowLD(int minusDays) {
        return LocalDate.now().minusDays(minusDays);
    }

    public static String getFileNamePart(String fileName, FileNamePartEnum partNumber) {
        String[] split = fileName.split("_");
        return split[partNumber.getPartNumber()];
    }

    public File getFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        String file = classLoader.getResource(fileName).getFile();
        return new File(file);
    }

    public static void checkIfProdUrl(){
        if (Constants.JUSTSEND_API_URL.contains(PROD_URL)){
            throw new IllegalStateException("Production link can't be used in tests");
        }
    }

    public static Long getGroupID(String group) {
        String groupId = group.split(":")[1].trim().replace(",", "");
        return valueOf(groupId);
    }


    public static void compare(Bulk request, BulkResponse response) {
        assertThat(response.getName()).startsWith(request.getName());
        assertThat(response.getMessage()).isEqualTo(request.getMessage());
        assertThat(response.getBulkVariant()).isEqualTo(request.getBulkVariant());
        assertThat(response.getFrom()).isEqualTo(request.getFrom());
        assertThat(response.getGroupIds()).containsExactly(request.getGroupId());
    }
}
