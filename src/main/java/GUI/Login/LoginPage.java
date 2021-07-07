package GUI.Login;

import GUI.Forget.ForgetPwd;
import GUI.Register.Register;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class LoginPage extends JFrame {
    JLabel login_JLabel, register_JLabel;
    JTextField account_jTextField, pwd_jTextField;
    JButton login_JButton,register_JButton ,forget_jButton;
    JRadioButton user_radio, user_administrator_radio, librarian_radio;
    ButtonGroup identity = new ButtonGroup();
    public LoginPage() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
//        页面初始化
        this.setLayout(new FlowLayout());
        this.setTitle("登陆页面-图书管理系统");
        this.setBounds(100, 100, 377, 453);
//        按钮初始化
        this.login_JButton = new JButton("登陆");
        this.register_JButton = new JButton("注册");
        this.login_JLabel = new JLabel("账   号：  ");
        this.register_JLabel = new JLabel("密   码：   ");
        this.account_jTextField = new JTextField(25);
        this.pwd_jTextField = new JPasswordField(25);
        this.forget_jButton = new JButton("忘记密码？");
        this.user_radio = new JRadioButton("用户      ");
        this.user_administrator_radio = new JRadioButton("用户信息管理员       ");
        this.librarian_radio = new JRadioButton("图书管理员      ");
        this.identity.add(user_radio);
        this.identity.add(user_administrator_radio);
        this.identity.add(librarian_radio);
//        加入组件
        this.add(login_JLabel);
        this.add(account_jTextField);
        this.add(register_JLabel);
        this.add(pwd_jTextField);
        this.add(user_radio);
        this.add(user_administrator_radio);
        this.add(librarian_radio);
        this.add(login_JButton);
        this.add(register_JButton);
        this.add(forget_jButton);

        //添加监听事件
        this.forget_jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //忘记密码按钮的事件
                new ForgetPwd();
            }
        });
        this.login_JButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //登陆按钮的事件
                new LoginNav(getSelected(identity),account_jTextField.getText(),pwd_jTextField.getText());
            }
        });
        this.register_JButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //注册页面
                new Register();
            }
        });
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private String getSelected(ButtonGroup identity) {
        Enumeration<AbstractButton> elements = identity.getElements();
        //获取单选框内容
        while (elements.hasMoreElements()) {
            AbstractButton btn = elements.nextElement();
            if (btn.isSelected()) {
                return btn.getText().trim(); //去除空格
            }
        }
        return "";
    }
    public static void main(String[] args) {
        new LoginPage();
    }
}
