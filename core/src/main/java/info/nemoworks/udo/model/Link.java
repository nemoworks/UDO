package info.nemoworks.udo.model;

public class Link {

    private String name;
    private String linkType;

    public Link(String name, String linkType) {
        this.name = name;
        this.linkType = linkType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

}
