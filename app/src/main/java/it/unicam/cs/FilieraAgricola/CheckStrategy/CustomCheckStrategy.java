package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.User.User;

public interface CustomCheckStrategy<T, E> {

    boolean validate(User user, T item, E customItem);

}
