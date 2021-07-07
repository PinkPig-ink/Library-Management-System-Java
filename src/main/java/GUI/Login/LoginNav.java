package GUI.Login;

import GUI.Information.library;
import GUI.Librarian.Librarian;
import Tables.Account_pwd;
import com.kkang.view.MyWindows;
import jdbc.DruidUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LoginNav {
    private String current; //身份
    private String account;
    private String pwd;

    public LoginNav(String current, String account, String pwd) {
        this.current = current;
        this.account = account;
        this.pwd = pwd;
        //在对应表中验证
        login(account,pwd,current);
    }

    private void login(String account, String pwd , String identity) {
        if (identity.equals("")) {
            JOptionPane.showMessageDialog(null, "请先选择您的身份", "抱歉",JOptionPane.WARNING_MESSAGE);
            return;
        }
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DruidUtil.getDataSource());
        String sql = "SELECT * FROM "+identity;
        List<Account_pwd> account_pwdList = jdbcTemplate.query(sql, new RowMapper<Account_pwd>() {
            @Override
            public Account_pwd mapRow(ResultSet rs, int i) throws SQLException {
                Account_pwd account_pwd = new Account_pwd();
                account_pwd.setAccount(rs.getString("账号"));
                account_pwd.setPwd(rs.getString("密码"));
                return account_pwd;
            }
        });
        //验证
        boolean check = false;
        for (Account_pwd account_pwd : account_pwdList) {
            if(account.equals(account_pwd.getAccount())) {
                if (pwd.equals(account_pwd.getPwd())) {
                    System.out.println("登陆成功");
                    check = true;
                    break;
                }
            }
        }
        if (check) {
            JOptionPane.showMessageDialog(null, "用户身份："+identity+", 登陆成功" , "恭喜",JOptionPane.WARNING_MESSAGE);
            if (identity.equals("图书管理员")){
                new Librarian().gui();
            }else if(identity.equals("用户")){
                new library(account).setVisible(true);
            }else if (identity.equals("用户信息管理员")){
                new MyWindows();
            }
        }else {
            if (account.equals("")||pwd.equals("")) {
                JOptionPane.showMessageDialog(null, "请输入账号或密码", "抱歉",JOptionPane.WARNING_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(null, "账号或密码错误", "抱歉",JOptionPane.WARNING_MESSAGE);
        }
    }
}
