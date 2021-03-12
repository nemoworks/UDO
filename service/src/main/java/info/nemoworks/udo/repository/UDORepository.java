package info.nemoworks.udo.repository;

import info.nemoworks.udo.exception.UDOPersistException;
import info.nemoworks.udo.model.UDO;
import info.nemoworks.udo.model.UDOSchema;

public interface UDORepository {

    public void saveUDO(UDO udo) throws UDOPersistException;

    public UDO findUDO(String oid);

    public UDOSchema findSchema(UDO udo);

    public void saveSchema(UDOSchema schema) throws UDOPersistException;

}
