package info.nemoworks.udo.repository.h2.model;

import javax.persistence.*;
import java.util.List;

/*
Table描述Tuples组合构成的一张表
 */

@javax.persistence.Entity
public class UDRO {
    @Id
    @GeneratedValue
    private int pkey;

    private String firstTableName;
    private String secondTableName;
    private String tableName;

    @OneToMany(targetEntity = UTuple.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<UTuple> UTuples;

    public UDRO() {

    }

    public UDRO(List<UTuple> UTuples, String firstTableName, String secondTableName) {
        this.UTuples = UTuples;
        this.firstTableName = firstTableName;
        this.secondTableName = secondTableName;
        this.tableName = firstTableName + "_" + secondTableName;
    }

    public void setUTuples(List<UTuple> UTuples) {
        this.UTuples = UTuples;
    }

    public List<UTuple> getUTuples() {
        return UTuples;
    }

    public String getFirstTableName() {
        return firstTableName;
    }

    public String getSecondTableName() {
        return secondTableName;
    }

    public void setFirstTableName(String firstTableName) {
        this.firstTableName = firstTableName;
    }

    public void setSecondTableName(String secondTableName) {
        this.secondTableName = secondTableName;
    }

    public String getTableName() {
        return tableName;
    }

    //    public String getTableName() {
//        return tableName;
//    }
//
//    public void setTableName(String tableName) {
//        this.tableName = tableName;
//    }
}
