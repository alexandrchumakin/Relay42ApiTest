package web.pages.models;

public class Audience {
    private String name;
    private String apiIdentifier;

    public Audience(String name, String apiIdentifier){
        this.name = name;
        this.apiIdentifier = apiIdentifier;
    }

    public String getName() {
        return name;
    }

    public String getApiIdentifier() {
        return apiIdentifier;
    }
}
