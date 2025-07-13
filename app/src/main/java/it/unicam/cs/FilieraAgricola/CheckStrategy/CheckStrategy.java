package it.unicam.cs.FilieraAgricola.CheckStrategy;


import it.unicam.cs.FilieraAgricola.User.User;

public interface CheckStrategy<T> {

    boolean validate(User user, T item);
}
