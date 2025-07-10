package it.unicam.cs.FilieraAgricola.Event;

import it.unicam.cs.FilieraAgricola.CheckStrategy.BookEventStrategyCheck;
import it.unicam.cs.FilieraAgricola.CheckStrategy.CreateEventCheckStrategy;
import it.unicam.cs.FilieraAgricola.CheckStrategy.DeleteEventCheckStrategy;
import it.unicam.cs.FilieraAgricola.CheckStrategy.UpdateEventCheckStrategy;
import it.unicam.cs.FilieraAgricola.Command.*;
import it.unicam.cs.FilieraAgricola.Repository.EventRepository;
import it.unicam.cs.FilieraAgricola.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventManager {

    @Autowired
    private CreateEventCheckStrategy createEventCheckStrategy;

    @Autowired
    private BookEventStrategyCheck bookEventStrategyCheck;

    @Autowired
    private DeleteEventCheckStrategy deleteEventCheckStrategy;

    @Autowired
    private UpdateEventCheckStrategy updateEventCheckStrategy;

    @Autowired
    private EventLoaderFactory eventLoaderFactory;

    @Autowired
    private EventRepository eventRepository;


    public void createEventRequest(User user, Event event){

        if (!this.createEventCheckStrategy.validate(user, event))
            throw new IllegalArgumentException("Event non valid for creation");

        Command<Event> createEventCommand = new CreateEventCommand(user, event, this.eventLoaderFactory);

        CommandInvoker invoker = new CommandInvoker();

        invoker.setCommand(createEventCommand);
        invoker.invoke();
    }


    public void bookEventRequest (User user, Event event){

        if (!this.bookEventStrategyCheck.validate(user, event))
            throw new IllegalArgumentException("The event is not on the book");

        Command<Event> bookEventCommand = new BookEventCommand(user, event, this.eventRepository);

        CommandInvoker invoker = new CommandInvoker();

        invoker.setCommand(bookEventCommand);
        invoker.invoke();
    }

    public void deleteEventRequest(User user, Event event){
        if(!this.deleteEventCheckStrategy.validate(user, event)){
            throw new IllegalArgumentException("Event non valid for deletion");
        }

        Command<Event> deleteEventCommand = new DeleteEventCommand(user, event, this.eventLoaderFactory);
        CommandInvoker invoker = new CommandInvoker();
        invoker.setCommand(deleteEventCommand);
        invoker.invoke();
    }

    public void updateEventRequest(User user, Event event){
        if (!this.updateEventCheckStrategy.validate(user, event)){
            throw new IllegalArgumentException("Event non valid for update");
        }

        Command<Event> updateEventCommand = new UpdateEventCommand(user, event, this.eventLoaderFactory);
        CommandInvoker invoker = new CommandInvoker();
        invoker.setCommand(updateEventCommand);
        invoker.invoke();
    }
}
