package info.nemoworks.udo.model;

public class Link {

    private String name;
    private String linkType;
    private String collection;

    public Link(String name, String linkType, String collection) {
        this.name = name;
        this.linkType = linkType;
        this.collection = collection;
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

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getCollection() {
        return collection;
    }
}
