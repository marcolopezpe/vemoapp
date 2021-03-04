package pe.marcolopez.sistemas.vemoapp.controller.enums;

public enum ResponseEnum {

    ERROR(-1, "Error"),
    WARNING(0, "Warning"),
    SUCCESS(1, "Success");

    private Integer value;
    private String name;

    ResponseEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
