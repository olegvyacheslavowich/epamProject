package entity;

/**
 * Created by 20_ok on 14.05.2017.
 */
public class Money {

    private int id;
    private Account account;
    private CreditCard creditCard;
    private int money;

    public Money() {
    }

    public Money(Account account, CreditCard creditCard) {
        this.account = account;
        this.creditCard = creditCard;
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

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
