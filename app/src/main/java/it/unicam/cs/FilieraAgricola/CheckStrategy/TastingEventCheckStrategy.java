package it.unicam.cs.FilieraAgricola.CheckStrategy;
import it.unicam.cs.FilieraAgricola.Event.Event;
import it.unicam.cs.FilieraAgricola.Event.EventUtility;
import it.unicam.cs.FilieraAgricola.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class TastingEventCheckStrategy implements CheckStrategy <Event>{

    @Autowired
    private EventUtility eventUtility;

    @Override
    public boolean validate(User user, Event event) {

        if(!this.eventUtility.checkEventInfo(event)){
            return false;
        }
        if (this.eventUtility.checkExistEvent(event)) {
            return false;
        }

        if(this.eventUtility.isEventFull(event)){
            return false;
        }
        return false;
    }
}
