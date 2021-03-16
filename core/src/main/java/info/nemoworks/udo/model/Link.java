package info.nemoworks.udo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Link {
    private String name;
    private String collectionName;
    private String linkType;
}
