package info.nemoworks.udo.service;

import org.dizitart.no2.objects.ObjectFilter;
import org.springframework.stereotype.Service;

import info.nemoworks.udo.model.UDOSchema;

@Service
public class UDOSchemaService {

    // private ObjectRepository<UDOSchema> schemaRepository;

    // public ObjectRepository<UDOSchema> getSchemaRepository() {
    //     return schemaRepository;
    // }

    public UDOSchemaService() {
        // schemaRepository = UDOSchemaRepository.getSchemaRepository();
    }

    public void insertSchema(UDOSchema schema) {
        // schemaRepository.insert(schema);
    }


    // 关于使用ObjectFilter来作为查询条件还需要查文档及测试
    public void deleteSchema(ObjectFilter filter) {
        // schemaRepository.remove(filter);
    }

    public void updateSchema(ObjectFilter filter, UDOSchema update) {
        // schemaRepository.update(filter, update);
    }
}
