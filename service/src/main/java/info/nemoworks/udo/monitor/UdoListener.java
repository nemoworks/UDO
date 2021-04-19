package info.nemoworks.udo.monitor;

import info.nemoworks.udo.model.Udo;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

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
