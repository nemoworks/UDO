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

    @Autowired
    private UdoSchemaRepository udoSchemaRepository;

    public UdoSchemaService(UdoSchemaRepository udoSchemaRepository ) {
        this.udoSchemaRepository = udoSchemaRepository;
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
}
