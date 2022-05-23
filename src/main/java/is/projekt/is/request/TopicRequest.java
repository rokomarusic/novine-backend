package is.projekt.is.request;

import javax.validation.constraints.NotBlank;

public class TopicRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String shortName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
