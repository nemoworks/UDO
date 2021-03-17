package info.nemoworks.udo.repository.nitrite;

import info.nemoworks.udo.model.UdoSchema;
import info.nemoworks.udo.repository.UdoSchemaRepository;
import org.dizitart.no2.FindOptions;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteCollection;
import org.dizitart.no2.filters.Filters;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import java.util.List;

public class NitriteSchemaRepository implements UdoSchemaRepository {

    private static final Nitrite db = Nitrite.builder().openOrCreate();

    //private NitriteCollection collection;

    private ObjectRepository<UdoSchema> udoSchemaRepository;

    public NitriteSchemaRepository(){
        udoSchemaRepository = db.getRepository(UdoSchema.class);
        //collection = db.getCollection("UdoSchema");
    }


    @Override
    public List<UdoSchema> findSchemaList() {
        return udoSchemaRepository.find().toList();
        //return null;
    }

    @Override
    public UdoSchema findSchema(String udoi) {
        return udoSchemaRepository.find(ObjectFilters.eq("udoi",udoi)).firstOrDefault();
    }

    @Override
    public UdoSchema createSchema(UdoSchema udoSchema) {
        return (UdoSchema) udoSchemaRepository.insert(udoSchema);
        //return null;
    }

    @Override
    public void deleteSchema(String udoi) {
        udoSchemaRepository.remove(ObjectFilters.eq("udoi",udoi));
    }
}
