package Tables;
//用户表
public class User {
    private String account;
    private String pwd;
    private String name;
    private String phone;
    private String account_identification;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "account='" + account + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", account_identification='" + account_identification + '\'' +
                '}';
    }

    public String getAccount_identification() {
        return account_identification;
    }

    public void setAccount_identification(String account_identification) {
        this.account_identification = account_identification;
    }
}
