package info.nemoworks.udo.repository.h2.manager;

import java.util.List;

import info.nemoworks.udo.repository.h2.UdroRepository;
import info.nemoworks.udo.repository.h2.exception.UdroPersistException;
import info.nemoworks.udo.repository.h2.model.Udro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.nemoworks.udo.model.Udo;

@Service
public class UdroManager {
    @Autowired
    private UdroRepository udroRepository;

    public UdroManager(UdroRepository udroRepository) {
        this.udroRepository = udroRepository;
    }

    public List<Udro> findAll() {
        return udroRepository.findAll();
    }

    public Udro findByName(String name) throws UdroPersistException {
        Udro UDRO = udroRepository.findByTableName(name);
        if (UDRO == null) {
            throw new UdroPersistException("UDRO " + name + " does not exist.");
        }
        return UDRO;
    }

    public void deleteByName(String name) throws UdroPersistException {
        Udro UDRO = udroRepository.findByTableName(name);
        if (UDRO == null) {
            throw new UdroPersistException("UDRO " + name + " does not exist.");
        }
        udroRepository.deleteByTableName(name);
    }

    public Udro saveUdo(Udo udo) throws UdroPersistException {
        // JSONObject obj = udo.getContent();
        String firstTableName = udo.getUdoi();
        String secondTableName = udo.getSchemaId();
        Translate translate = new Translate(udo.getContent());
        translate.startTrans();
        Udro udro = new Udro(translate.getUTuples(), firstTableName, secondTableName);
        if (udroRepository.findByTableName(udro.getTableName()) != null) {
            throw new UdroPersistException("UDRO " + udro.getTableName() + " already exists.");
        }
        System.out.println("save UDRO: " + udro);
        return udroRepository.save(udro);
    }

    public Udro updateUdo(Udo udo) throws UdroPersistException {
        // JSONObject obj = udo.getContent();
        String firstTableName = udo.getUdoi();
        String secondTableName = udo.getSchemaId();
        Translate translate = new Translate(udo.getContent());
        translate.startTrans();
        Udro udro = new Udro(translate.getUTuples(), firstTableName, secondTableName);
        if (udroRepository.findByTableName(udro.getTableName()) == null) {
            throw new UdroPersistException("UDRO " + udro.getTableName() + " does not exist.");
        }
        udroRepository.deleteByTableName(firstTableName + "_" + secondTableName);
        return udroRepository.save(udro);
    }
}
