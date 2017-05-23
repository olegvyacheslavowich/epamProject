package entity;

/**
 * Created by 20_ok on 15.05.2017.
 */
public class Order {

    private int id;
    private Client client;
    private Voucher voucher;
    private Account account;
    private Money money;

    public Order() {
    }

    public Order(Client client, Voucher voucher, Account account, Money money) {
        this.client = client;
        this.voucher = voucher;
        this.account = account;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }
}
