package info.nemoworks.udo.service;

import info.nemoworks.udo.repository.DBType;
import info.nemoworks.udo.repository.RepositoryFactory;
import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.repository.UdoSchemaRepository;
import org.springframework.stereotype.Service;

import info.nemoworks.udo.model.UdoSchema;

import java.util.List;

@Service
public class UdoSchemaService {

    private UdoSchemaRepository udoSchemaRepository;
    private static final RepositoryFactory repositoryFactory = new RepositoryFactory();

    public UdoSchemaService(DBType dbType) {
        this.udoSchemaRepository = repositoryFactory.getRepository(dbType);
    }

    public void insertSchema(UdoSchema schema) throws UdoPersistException {
        udoSchemaRepository.createSchema(schema);
    }

    public UdoSchema findSchemaById(String udoi) {
        return udoSchemaRepository.findSchema(udoi);
    }

    public List<UdoSchema> findUdoSchemaList(){
        return udoSchemaRepository.findSchemaList();
    }

    // // 关于使用ObjectFilter来作为查询条件还需要查文档及测试
    // public void deleteSchema(ObjectFilter filter) {
    // // schemaRepository.remove(filter);
    // }

    // public void updateSchema(ObjectFilter filter, UDOSchema update) {
    // // schemaRepository.update(filter, update);
    // }
}
