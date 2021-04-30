package info.nemoworks.udo.service;

import info.nemoworks.udo.Publisher;
import info.nemoworks.udo.monitor.UdoSubscribeEvent;
import info.nemoworks.udo.repository.h2.exception.UdroPersistException;

import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.repository.UdoRepository;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UdoService {


    private final UdoRepository udoRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final Publisher publisher;

    public UdoService(UdoRepository udoRepository, ApplicationEventPublisher applicationEventPublisher, Publisher publisher) {
        this.udoRepository = udoRepository;
        this.applicationEventPublisher = applicationEventPublisher;
        this.publisher = publisher;
    }

    public UdoRepository getUdoRepository() {
        return udoRepository;
    }

    public Udo saveUdo(Udo doc) throws UdoPersistException, UdroPersistException {
        System.out.println(doc.toJSON());
        return udoRepository.saveUdo(doc, doc.getSchemaId());
    }

    public Udo findUdoById(String udoi, String schemaId) throws UdoPersistException, UdroPersistException {
//        schemaId = schemaId.substring(0, 1).toLowerCase() + schemaId.substring(1);
        Udo doc = udoRepository.findUdo(udoi, schemaId);
        if (doc == null) {
            throw new UdoPersistException("Doc " + udoi + " does not exist.\n" + schemaId + udoi);
        }
        return doc;
    }

    public List<Udo> findAllUdos(String schemaId) {
        return udoRepository.findAllUdos(schemaId);
    }

    public List<Udo> deleteUdoById(String udoi, String schemaId) throws UdoPersistException, UdroPersistException {
        Udo doc = udoRepository.findUdo(udoi, schemaId);
        if (doc == null) {
            throw new UdoPersistException("Doc " + udoi + " does not exist.");
        }
        udoRepository.deleteUdo(udoi, schemaId);
        return udoRepository.findAllUdos(schemaId);
    }

    public void updateUdoFromGateway(Udo udo, String udoi) throws UdoPersistException, UdroPersistException {
        Udo doc = udoRepository.findUdo(udoi, udo.getSchemaId());
        if (doc == null) {
            throw new UdoPersistException("Doc " + udoi + " does not exist.");
        }
        this.pushListener(udo);
        udoRepository.updateUdo(udo, udoi, udo.getSchemaId());
    }

    public Udo updateUdo(Udo udo, String udoi) throws UdoPersistException, UdroPersistException {
        Udo doc = udoRepository.findUdo(udoi, udo.getSchemaId());
        if (doc == null) {
            throw new UdoPersistException("Doc " + udoi + " does not exist.");
        }
        //publish update message to mqtt
        try {
            publisher.publishUdo(udo);
        } catch (MqttException e) {
            e.printStackTrace();
        }
        return udoRepository.updateUdo(udo, udoi, udo.getSchemaId());
    }


    public void pushListener(Udo udo) {
        applicationEventPublisher.publishEvent(new UdoSubscribeEvent(this, udo));
    }
}