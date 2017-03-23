package daolayer;

/**
 * Created by 20_ok on 21.03.2017.
 */
public abstract class DAO<K>{

    protected abstract boolean create(K entity);
    public abstract int checkField(String field, String checkQuery);
}
