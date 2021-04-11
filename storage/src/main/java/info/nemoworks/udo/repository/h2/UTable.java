package info.nemoworks.udo.repository.h2;

import javax.persistence.*;
import java.util.List;

/*
Table描述Tuples组合构成的一张表
 */

@javax.persistence.Entity
public class UTable {
    @Id
    @GeneratedValue
    private int pkey;

    private String tableName;

    @OneToMany(targetEntity = UTuple.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<UTuple> UTuples;

    public UTable(List<UTuple> UTuples, String tableName) {
        this.UTuples = UTuples;
        this.tableName = tableName;
    }

    public void setUTuples(List<UTuple> UTuples) {
        this.UTuples = UTuples;
    }

    public List<UTuple> getUTuples() {
        return UTuples;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
