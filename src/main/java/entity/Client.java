package entity;

import java.sql.Date;

/**
 * Created by 20_ok on 15.05.2017.
 */
public class Client {

    private int id;
    private String fullName;
    private String paper;
    private long documentNum;
    private String phone;
    private Date birthday;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPaper() {
        return paper;
    }

    public void setPaper(String paper) {
        this.paper = paper;
    }

    public long getDocumentNum() {
        return documentNum;
    }

    public void setDocumentNum(int documentNum) {
        this.documentNum = documentNum;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
