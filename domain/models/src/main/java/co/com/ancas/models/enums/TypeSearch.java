package co.com.ancas.models.enums;


public enum TypeSearch {

    HISTORICAL("HISTORICAL"),
    RETURNED("RETURNED"),
    EXPIRED("EXPIRED"),
    ACTIVE("ACTIVE");


    private String typeSearch;

    TypeSearch(String typeSearch) {
        this.typeSearch = typeSearch;
    }

    public String getTypeSearch() {
        return typeSearch;
    }
}
