package it.unicam.cs.FilieraAgricola.Manager;

import it.unicam.cs.FilieraAgricola.CheckStrategy.BookEventStrategyCheck;
import it.unicam.cs.FilieraAgricola.CheckStrategy.CheckStrategy;
import it.unicam.cs.FilieraAgricola.CheckStrategy.CreateEventCheckStrategy;
import it.unicam.cs.FilieraAgricola.Command.BookEventCommand;
import it.unicam.cs.FilieraAgricola.Command.Command;
import it.unicam.cs.FilieraAgricola.Command.CommandInvoker;
import it.unicam.cs.FilieraAgricola.Command.CreateEventCommand;
import it.unicam.cs.FilieraAgricola.Event.Event;
import it.unicam.cs.FilieraAgricola.User.User;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventManager {

    @Autowired
    private CreateEventCheckStrategy createEventCheckStrategy;
    @Autowired
    private BookEventStrategyCheck bookEventStrategyCheck;

    public void createEventRequest( User user , Event event){

        if (!this.createEventCheckStrategy.validate(user, event))
            throw new IllegalArgumentException("Event non valid for creation");

        Command<Event> createEventCommand = new CreateEventCommand(user, event);

        CommandInvoker invoker = new CommandInvoker();

        invoker.setCommand(createEventCommand);
        invoker.invoke();
    }


    public void bookEventRequest (User user, Event event){

        if (!this.bookEventStrategyCheck.validate(user, event))
            throw new IllegalArgumentException("The event is not on the book");

        Command<Event> bookEventCommand = new BookEventCommand(user, event);

        CommandInvoker invoker = new CommandInvoker();

        invoker.setCommand(bookEventCommand);
        invoker.invoke();
    }
}
