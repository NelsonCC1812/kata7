package model.db.schema;

public interface Loader<T> {
    public boolean next() throws Exception;

    public T get() throws Exception;
}
