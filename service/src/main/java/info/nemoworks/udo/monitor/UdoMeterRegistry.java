package info.nemoworks.udo.monitor;

import info.nemoworks.udo.model.Meter;
import info.nemoworks.udo.model.Udo;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class UdoMeterRegistry {
    final MeterRegistry meterRegistry;

    private static final Logger logger = LoggerFactory.getLogger(UdoMeterRegistry.class);


    private static final Map<String, Map<String, Meter>> meterMap = new HashMap<>();

    public UdoMeterRegistry(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public void addUdoMeter(Udo udo){
        String schema = udo.getSchemaId();
        logger.info("register meter of "+udo.getSchemaId()+"_"+udo.getUdoi()+" in prometheus...");
        List<String> meterList = MeterCluster.getMeterMap().get(schema);
        meterMap.put(udo.getUdoi(),new HashMap<>());
        meterList.forEach(s -> {
            meterMap.get(udo.getUdoi()).put(s,new Meter(new AtomicInteger(0)));
            String name = schema+"_"+udo.getUdoi()+"_"+s;
            meterMap.get(udo.getUdoi()).get(s).value = meterRegistry.gauge(name,new AtomicInteger(0));
        });
    }

    public void updateUdoMeter(Udo udo){
        logger.info("update meter of "+udo.getSchemaId()+"_"+udo.getUdoi()+" in prometheus...");
        meterMap.get(udo.getUdoi()).forEach((key,value)->{
            meterMap.get(udo.getUdoi()).get(key).value.set(udo.getContent().getIntValue(key));
        });
    }

    public static Map<String, Map<String, Meter>> getMeterMap() {
        return meterMap;
    }

}
