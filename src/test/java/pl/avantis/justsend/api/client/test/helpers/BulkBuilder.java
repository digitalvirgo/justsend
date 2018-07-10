package pl.avantis.justsend.api.client.test.helpers;

import pl.avantis.justsend.api.client.model.Bulk;
import pl.avantis.justsend.api.client.model.LanguageMessage;
import pl.avantis.justsend.api.client.services.impl.enums.BulkVariant;

import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;
import static pl.avantis.justsend.api.client.services.impl.enums.BulkVariant.ECO;

public final class BulkBuilder {

    private static Random random = new Random();

    private Long groupId;
    private String name;
    private List<String> to;
    private String from;
    private String message;
    private String sendDate;
    private BulkVariant bulkVariant;
    private boolean personalizedMessage = false;
    private LanguageMessage language;

    public static BulkBuilder aBulk() {
        return new BulkBuilder();
    }

    public static BulkBuilder bulkWithDefaultFieldsSet() {
        BulkBuilder bulkBuilder = new BulkBuilder();
        bulkBuilder.withName("Name" + random.nextInt(10000));
        bulkBuilder.withMessage("Api jar test");
        bulkBuilder.withBulkVariant(ECO);
        bulkBuilder.withFrom("Bulk" + random.nextInt(1000));
        bulkBuilder.withTo(asList("514746368"));
        bulkBuilder.withLanguage(LanguageMessage.POLISH);
        bulkBuilder.withSendDate("2018-06-20T16:54:67-00:00");
        return bulkBuilder;
    }

    public BulkBuilder withGroupId(Long groupId) {
        this.groupId = groupId;
        return this;
    }

    public BulkBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public BulkBuilder withTo(List<String> to) {
        this.to = to;
        return this;
    }

    public BulkBuilder withFrom(String from) {
        this.from = from;
        return this;
    }

    public BulkBuilder withMessage(String message) {
        this.message = message;
        return this;
    }

    public BulkBuilder withSendDate(String sendDate) {
        this.sendDate = sendDate;
        return this;
    }

    public BulkBuilder withBulkVariant(BulkVariant bulkVariant) {
        this.bulkVariant = bulkVariant;
        return this;
    }

    public BulkBuilder withPersonalizedMessage(boolean personalizedMessage) {
        this.personalizedMessage = personalizedMessage;
        return this;
    }

    public BulkBuilder withLanguage(LanguageMessage language) {
        this.language = language;
        return this;
    }

    public Bulk build() {
        Bulk bulk = new Bulk();
        bulk.setGroupId(groupId);
        bulk.setName(name);
        bulk.setTo(to);
        bulk.setFrom(from);
        bulk.setMessage(message);
        bulk.setSendDate(sendDate);
        bulk.setBulkVariant(bulkVariant);
        bulk.setPersonalizedMessage(personalizedMessage);
        bulk.setLanguage(language);
        return bulk;
    }
}
