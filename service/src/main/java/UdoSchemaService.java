import info.nemoworks.udo.exception.UdoPersistException;
import org.springframework.stereotype.Service;

import info.nemoworks.udo.model.UdoSchema;
import info.nemoworks.udo.repository.nitrite.UDONitriteRepository;

@Service
public class UdoSchemaService {

    // private ObjectRepository<UDOSchema> schemaRepository;

    // public ObjectRepository<UDOSchema> getSchemaRepository() {
    // return schemaRepository;
    // }

    private UDONitriteRepository udoNitriteRepository;

    public UdoSchemaService() {
        // schemaRepository = UDOSchemaRepository.getSchemaRepository();
        udoNitriteRepository = new UDONitriteRepository();
    }

    public void insertSchema(UdoSchema schema) throws UdoPersistException {
        // schemaRepository.insert(schema);
        udoNitriteRepository.saveSchema(schema);
    }

    public UdoSchema findSchemaByI(String udoi) {
        return udoNitriteRepository.findSchemaByI(udoi);
    }

    // // 关于使用ObjectFilter来作为查询条件还需要查文档及测试
    // public void deleteSchema(ObjectFilter filter) {
    // // schemaRepository.remove(filter);
    // }

    // public void updateSchema(ObjectFilter filter, UDOSchema update) {
    // // schemaRepository.update(filter, update);
    // }
}
