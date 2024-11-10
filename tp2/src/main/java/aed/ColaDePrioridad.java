package aed;

public interface ColaDePrioridad<T> {
    public boolean vacia();

    public void apilar(T e);

    public T desapilar(int i);

    public T getMax();

}
