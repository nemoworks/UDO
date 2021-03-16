package info.nemoworks.udo.repository;

import info.nemoworks.udo.repository.DBType;
import info.nemoworks.udo.repository.UdoRepository;
import info.nemoworks.udo.repository.nitrite.UDONitriteRepository;

public class RepositoryFactory {
//    static final String Nitrite = "Nitrite";
//    static final String H2 = "H2";
    public UdoRepository getRepository(DBType dbType){
        switch (dbType){
            case NitruteRepository:
                return new UDONitriteRepository();
            case H2:
                return null;
        }
        return null;
    }
}
