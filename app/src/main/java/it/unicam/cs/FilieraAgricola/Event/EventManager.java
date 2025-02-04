package it.unicam.cs.FilieraAgricola.Event;

import it.unicam.cs.FilieraAgricola.CheckStrategy.CheckStrategy;
import it.unicam.cs.FilieraAgricola.Command.Command;
import it.unicam.cs.FilieraAgricola.Command.CommandInvoker;
import it.unicam.cs.FilieraAgricola.Command.LoadProductCommand;
import it.unicam.cs.FilieraAgricola.Product.Product;
import it.unicam.cs.FilieraAgricola.User.User;
import org.springframework.stereotype.Service;

@Service
public class EventManager {

    private EventManager (){}

    public void createEventRequest( User user , Event event){
        CheckStrategy createEventStrategy = new CreateEventCheckStrategy(event);

        if (!createEventStrategy.validate())
            throw new IllegalArgumentException("Event non valid for creation");

        Command<Event> createEventCommand = new CreateEventCommand(user, event);

        CommandInvoker invoker = new CommandInvoker();

        invoker.setCommand(createEventCommand);
        invoker.invoke();
    }

    public void bookEventRequest (User user, Event event){

        CheckStrategy bookEventStrategy =  new BookEventStrategyChech (event) ;

        if (!bookEventStrategy.validate())
            throw new IllegalArgumentException("The event is not on the book");

        Command<Event> createEventCommand = CreateEventCommand (user, event);

        CommandInvoker invoker = new CommandInvoker();

        invoker.setCommand(createEventCommand);
        invoker.invoke();
    }
}
