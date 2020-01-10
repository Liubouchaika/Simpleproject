package domain;

public class Account {
    private long id;
    private long account;
    private long userId;

    public Account(long id, long account, long userId) {
        this.id = id;
        this.account = account;
        this.userId = userId;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccount() {
        return this.account;
    }

    public void setAccount(long account) {
        this.account = account;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}



