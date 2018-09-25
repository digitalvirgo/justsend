package pl.digitalvirgo.justsend.api.client.test.helpers.builder;

import pl.digitalvirgo.justsend.api.client.model.BulkGroupList;
import pl.digitalvirgo.justsend.api.client.model.LanguageMessage;
import pl.digitalvirgo.justsend.api.client.services.impl.enums.BulkVariant;

import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;
import static pl.digitalvirgo.justsend.api.client.services.impl.enums.BulkVariant.ECO;

public final class BulkGroupListBuilder {

    private static Random random = new Random();

    private List<Long> groupIds;
    private String name;
    private List<String> to;
    private String from;
    private String message;
    private String sendDate;
    private BulkVariant bulkVariant;
    private boolean personalizedMessage = false;
    private LanguageMessage language;

    private BulkGroupListBuilder() {
    }

    public static BulkGroupListBuilder aBulkGroupList() {
        return new BulkGroupListBuilder();
    }

    public static BulkGroupListBuilder bulkGroupListWithDefaultListSet() {
        BulkGroupListBuilder bulkGroupListBuilder = new BulkGroupListBuilder();
        bulkGroupListBuilder.withName("Name" + random.nextInt(10000));
        bulkGroupListBuilder.withMessage("Test message");
        bulkGroupListBuilder.withBulkVariant(ECO);
        bulkGroupListBuilder.withFrom("Bulk"+ random.nextInt(1000));
        bulkGroupListBuilder.withTo(asList("514746368"));
        bulkGroupListBuilder.withLanguage(LanguageMessage.POLISH);
        bulkGroupListBuilder.withSendDate("2018-06-20T16:54:67-00:00");
        return bulkGroupListBuilder;
    }

    public BulkGroupListBuilder withGroupIds(List<Long> groupIds) {
        this.groupIds = groupIds;
        return this;
    }

    public BulkGroupListBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public BulkGroupListBuilder withTo(List<String> to) {
        this.to = to;
        return this;
    }

    public BulkGroupListBuilder withFrom(String from) {
        this.from = from;
        return this;
    }

    public BulkGroupListBuilder withMessage(String message) {
        this.message = message;
        return this;
    }

    public BulkGroupListBuilder withSendDate(String sendDate) {
        this.sendDate = sendDate;
        return this;
    }

    public BulkGroupListBuilder withBulkVariant(BulkVariant bulkVariant) {
        this.bulkVariant = bulkVariant;
        return this;
    }

    public BulkGroupListBuilder withPersonalizedMessage(boolean personalizedMessage) {
        this.personalizedMessage = personalizedMessage;
        return this;
    }

    public BulkGroupListBuilder withLanguage(LanguageMessage language) {
        this.language = language;
        return this;
    }

    public BulkGroupList build() {
        BulkGroupList bulkGroupList = new BulkGroupList();
        bulkGroupList.setGroupIds(groupIds);
        bulkGroupList.setName(name);
        bulkGroupList.setTo(to);
        bulkGroupList.setFrom(from);
        bulkGroupList.setMessage(message);
        bulkGroupList.setSendDate(sendDate);
        bulkGroupList.setBulkVariant(bulkVariant);
        bulkGroupList.setPersonalizedMessage(personalizedMessage);
        bulkGroupList.setLanguage(language);
        return bulkGroupList;
    }
}
