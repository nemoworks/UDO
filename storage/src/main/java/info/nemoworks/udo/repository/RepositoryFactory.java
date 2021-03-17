package info.nemoworks.udo.repository;

import info.nemoworks.udo.repository.h2.impl.MixH2Repository;
import info.nemoworks.udo.repository.nitrite.NitriteSchemaRepository;

public class RepositoryFactory {
//    static final String Nitrite = "Nitrite";
//    static final String H2 = "H2";
    public UdoSchemaRepository getRepository(DBType dbType){
        switch (dbType){
            case NitruteRepository:
                return new NitriteSchemaRepository();
            case H2:
                return null;
        }
        return null;
    }
}
