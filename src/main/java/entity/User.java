package entity;


import java.sql.Date;

public class User {

    private int id;
    private String fullName;
    private String paper;
    private long documentNum;
    private String phone;
    private Date birthday;
    private String email;
    private Account account;

    public User(UserBuilder userBuilder) {
        this.id = userBuilder.id;
        this.fullName = userBuilder.fullName;
        this.paper = userBuilder.paper;
        this.documentNum = userBuilder.documentNum;
        this.phone = userBuilder.phone;
        this.birthday = userBuilder.birthday;
        this.email = userBuilder.email;
        this.account = userBuilder.account;
    }


    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPaper() {
        return paper;
    }

    public long getDocumentNum() {
        return documentNum;
    }

    public String getPhone() {
        return phone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public Account getAccount() {
        return account;
    }

    public static class UserBuilder {
        private int id;
        private String fullName;
        private String paper;
        private long documentNum;
        private String phone;
        private Date birthday;
        private String email;
        private Account account;

        public UserBuilder id(int id) {
            this.id = id;
            return this;
        }

        public UserBuilder account(Account account) {
            this.account = account;
            return this;
        }

        public UserBuilder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public UserBuilder paper(String paper) {
            this.paper = paper;
            return this;
        }

        public UserBuilder documentNum(long documentNum) {
            this.documentNum = documentNum;
            return this;
        }

        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder birthday(Date birthday) {
            this.birthday = birthday;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(this);
        }

    }
}
