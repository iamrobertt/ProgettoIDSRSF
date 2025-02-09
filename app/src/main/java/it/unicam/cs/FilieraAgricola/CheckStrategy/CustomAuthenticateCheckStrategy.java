package it.unicam.cs.FilieraAgricola.CheckStrategy;

public interface CustomAuthenticateCheckStrategy<T, E> {
    boolean validate(T item, E customItem);
}
