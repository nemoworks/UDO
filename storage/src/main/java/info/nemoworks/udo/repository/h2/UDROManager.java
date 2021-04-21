package info.nemoworks.udo.repository.h2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.nemoworks.udo.model.Udo;

@Service
public class UDROManager {
    @Autowired
    private UDRORepository udroRepository;

    public UDROManager(UDRORepository udroRepository) {
        this.udroRepository = udroRepository;
    }

    public List<UDRO> findAll() {
        return udroRepository.findAll();
    }

    public UDRO findByName(String name) throws UDROPersistException {
        UDRO UDRO = udroRepository.findByTableName(name);
        if (UDRO == null) {
            throw new UDROPersistException("UDRO " + name + " does not exist.");
        }
        return UDRO;
    }

    public void deleteByName(String name) throws UDROPersistException {
        UDRO UDRO = udroRepository.findByTableName(name);
        if (UDRO == null) {
            throw new UDROPersistException("UDRO " + name + " does not exist.");
        }
        udroRepository.deleteByTableName(name);
    }

    public UDRO saveUdo(Udo udo) throws UDROPersistException {
        // JSONObject obj = udo.getContent();
        String firstTableName = udo.getUdoi();
        String secondTableName = udo.getSchemaId();
        Translate translate = new Translate(udo.getContent());
        translate.startTrans();
        UDRO udro = new UDRO(translate.getUTuples(), firstTableName, secondTableName);
        if (udroRepository.findByTableName(udro.getTableName()) != null) {
            throw new UDROPersistException("UDRO " + udro.getTableName() + " already exists.");
        }
        System.out.println("save UDRO: " + udro);
        return udroRepository.save(udro);
    }

    public UDRO updateUdo(Udo udo) throws UDROPersistException {
        // JSONObject obj = udo.getContent();
        String firstTableName = udo.getUdoi();
        String secondTableName = udo.getSchemaId();
        Translate translate = new Translate(udo.getContent());
        translate.startTrans();
        UDRO udro = new UDRO(translate.getUTuples(), firstTableName, secondTableName);
        if (udroRepository.findByTableName(udro.getTableName()) == null) {
            throw new UDROPersistException("UDRO " + udro.getTableName() + " does not exist.");
        }
        udroRepository.deleteByTableName(firstTableName + "_" + secondTableName);
        return udroRepository.save(udro);
    }
}
