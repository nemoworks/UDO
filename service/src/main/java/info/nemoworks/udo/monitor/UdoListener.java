package info.nemoworks.udo.monitor;

import info.nemoworks.udo.model.Udo;
import io.micrometer.core.instrument.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class UdoListener implements ApplicationListener<UdoEvent> {

    final UdoMeterRegistry udoMeterRegistry;

    public UdoListener(UdoMeterRegistry udoMeterRegistry){
        this.udoMeterRegistry = udoMeterRegistry;
    }

    @Override
    public void onApplicationEvent(UdoEvent event) {
        System.out.println("事件触发：" + event.getUdo());
        Udo udo = event.getUdo();
        udoMeterRegistry.updateUdoMeter(udo);
    }
}
