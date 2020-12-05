package tokyo.northside.omegat;

public class OnlineDictionaryService {
    private String name;
    private String endpointUrl;
    private String driver;

    public OnlineDictionaryService(final String name, final String endpointUrl, final String driver) {
        this.name = name;
        this.endpointUrl = endpointUrl;
        this.driver = driver;
    }

    public OnlineDictionaryService() { }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEndpointUrl() {
        return endpointUrl;
    }

    public void setEndpointUrl(final String endpointUrl) {
        this.endpointUrl = endpointUrl;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(final String driver) {
        this.driver = driver;
    }
}
