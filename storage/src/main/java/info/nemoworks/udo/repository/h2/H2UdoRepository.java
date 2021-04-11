package info.nemoworks.udo.repository.h2;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import info.nemoworks.udo.exception.TablePersistException;
import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.repository.UdoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Tuple;
import java.util.List;

public class H2UdoRepository implements UdoRepository {
    @Autowired
    private TableService tableService;

    @Override
    public Udo saveUdo(Udo udo, String schemaId) throws UdoPersistException, TablePersistException {
        UTable table = tableService.saveUdoAsTable(udo);
        List<UTuple> tuples = table.getUTuples();
        Translate translate = new Translate(udo.getContent());
        String jStr = JSON.toJSONString(translate.backTranslate(tuples));
        return JSONObject.parseObject(jStr, udo.getClass());
    }

    @Override
    public Udo findUdo(String udoi, String schemaId) {
        return null;
    }

    @Override
    public List<Udo> findAllUdos(String schemaId) {
        return null;
    }

    @Override
    public void deleteUdo(String udoi, String schemaId) {

    }

    @Override
    public Udo updateUdo(Udo udo, String udoi, String schemaId) {
        return null;
    }
}
