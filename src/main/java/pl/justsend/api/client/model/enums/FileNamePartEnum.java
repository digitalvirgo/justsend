package pl.justsend.api.client.model.enums;

public enum FileNamePartEnum {
    FILE_NAME_PREFIX(0), MASTER_USER_ID(1), FROM_DATE(2), TO_DATE(3), FILE_ID(4), CREATE_DATE(5), FILE_EXTENSION(6);

    private int partNumber;

    FileNamePartEnum(int partNumber) {
        this.partNumber = partNumber;
    }

    public int getPartNumber() {
        return partNumber;
    }
}
