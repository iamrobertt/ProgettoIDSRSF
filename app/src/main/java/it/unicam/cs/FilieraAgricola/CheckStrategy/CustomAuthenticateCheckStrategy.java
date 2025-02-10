package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.User.User;

public interface CustomAuthenticateCheckStrategy<T, E> {
    User validate(T item, E customItem);
}
