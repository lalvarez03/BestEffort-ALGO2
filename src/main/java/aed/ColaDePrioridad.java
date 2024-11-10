package aed;

public interface ColaDePrioridad<T> {
    public boolean vacia();

    public void apilar(T e);

    public T desapilarMax();

    public T getMax();

}
