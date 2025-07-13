package it.unicam.cs.FilieraAgricola.Command;

import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;

import java.util.List;

public abstract class Command<T> {

    protected User user;
    protected T item;


    public Command (User user, T item){
        this.item = item;
        this.user = user;
    }

    public abstract List<UserRole> getNeededAuthorization();

    public abstract boolean hasCallerNeededAuthorization();

    public abstract void execute();
}
