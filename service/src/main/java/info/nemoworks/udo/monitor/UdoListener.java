package info.nemoworks.udo.monitor;

import info.nemoworks.udo.model.Udo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class UdoListener implements ApplicationListener<UdoEvent> {

    final UdoMeterRegistry udoMeterRegistry;

    private static final Logger logger = LoggerFactory.getLogger(UdoListener.class);


    public UdoListener(UdoMeterRegistry udoMeterRegistry){
        this.udoMeterRegistry = udoMeterRegistry;
    }

    @Override
    public void onApplicationEvent(UdoEvent event) {
        logger.info("trigger updateUdoEvent：" + event.getUdo());
        Udo udo = event.getUdo();
        udoMeterRegistry.updateUdoMeter(udo);
    }
}
