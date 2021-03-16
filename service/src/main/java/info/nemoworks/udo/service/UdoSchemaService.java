package info.nemoworks.udo.service;

import info.nemoworks.udo.repository.DBType;
import info.nemoworks.udo.repository.RepositoryFactory;
import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.repository.UdoRepository;
import org.springframework.stereotype.Service;

import info.nemoworks.udo.model.UdoSchema;

@Service
public class UdoSchemaService {

    private UdoRepository udoRepository;
    private static final RepositoryFactory repositoryFactory = new RepositoryFactory();

  //  private UDONitriteRepository udoNitriteRepository;

    public UdoSchemaService(DBType dbType) {
        // schemaRepository = UDOSchemaRepository.getSchemaRepository();
       // udoNitriteRepository = new UDONitriteRepository();
        this.udoRepository = repositoryFactory.getRepository(dbType);
    }

    public void insertSchema(UdoSchema schema) throws UdoPersistException {
        // schemaRepository.insert(schema);
        //udoNitriteRepository.saveSchema(schema);
        udoRepository.saveSchema(schema);
    }

    public UdoSchema findSchemaById(String udoi) {
        //return udoNitriteRepository.findSchemaByI(udoi);
        return null;
    }

    // // 关于使用ObjectFilter来作为查询条件还需要查文档及测试
    // public void deleteSchema(ObjectFilter filter) {
    // // schemaRepository.remove(filter);
    // }

    // public void updateSchema(ObjectFilter filter, UDOSchema update) {
    // // schemaRepository.update(filter, update);
    // }
}
