import org.springframework.stereotype.Service;

import info.nemoworks.udo.model.UdoSchema;
import info.nemoworks.udo.repository.nitrite.UDONitriteRepository;

@Service
public class UdoSchemaService {

    // private ObjectRepository<UDOSchema> schemaRepository;

    // public ObjectRepository<UDOSchema> getSchemaRepository() {
    // return schemaRepository;
    // }

    public UdoSchemaService() {
        // schemaRepository = UDOSchemaRepository.getSchemaRepository();
    }

    public void insertSchema(UdoSchema schema) {
        // schemaRepository.insert(schema);
    }

    // // 关于使用ObjectFilter来作为查询条件还需要查文档及测试
    // public void deleteSchema(ObjectFilter filter) {
    // // schemaRepository.remove(filter);
    // }

    // public void updateSchema(ObjectFilter filter, UDOSchema update) {
    // // schemaRepository.update(filter, update);
    // }
}
