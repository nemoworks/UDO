package info.nemoworks.udo.monitor;

import info.nemoworks.udo.model.Udo;
import org.springframework.context.ApplicationEvent;

public class UdoEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    private Udo udo ;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public UdoEvent(Object source,Udo udo) {
        super(source);
        this.udo = udo;
    }

    public Udo getUdo() {
        return udo;
    }

    public void setUdo(Udo udo) {
        this.udo = udo;
    }
}
