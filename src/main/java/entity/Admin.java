package entity;

/**
 * Created by 20_ok on 19.05.2017.
 */
public class Admin {

    private int id;
    private Account account;

    public Admin() {
    }

    public Admin(Account account) {
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}


