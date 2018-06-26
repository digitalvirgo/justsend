package pl.justsend.api.client.services;

public enum OrderEnum {
    ASC("0"), DESC("1");

    private String order;

    OrderEnum(String order) {
        this.order = order;
    }

    public String getOrder() {
        return order;
    }
}
