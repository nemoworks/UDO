package info.nemoworks.udo.model;

public class Link {

    public Link(String name, String collectionName, String linkType) {
        this.name = name;
        this.collectionName = collectionName;
        this.linkType = linkType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    private String name;
    private String collectionName;
    private String linkType;
}
