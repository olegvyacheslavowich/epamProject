package entity;

import java.sql.Date;

/**
 * Created by 20_ok on 14.05.2017.
 */
public class CreditCard {

    private int id;
    private CardType cardType;
    private String ownerName;
    private long number;
    private int cvvNumber;
    private Date date;

    public CreditCard() {
    }

    public CreditCard(CardType cardType, String ownerName, long number, int cvvNumber, Date date) {
        this.cardType = cardType;
        this.ownerName = ownerName;
        this.number = number;
        this.cvvNumber = cvvNumber;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public int getCvvNumber() {
        return cvvNumber;
    }

    public void setCvvNumber(int cvvNumber) {
        this.cvvNumber = cvvNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
