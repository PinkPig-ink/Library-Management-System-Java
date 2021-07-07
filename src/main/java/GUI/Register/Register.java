package GUI.Register;

import Tables.User;
import jdbc.DruidUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Register extends JFrame {
    JLabel login_JLabel, question_JLabel, pwd_JLabel, confirm_pwd_JLabel, input_answer_JLabel;
    JTextField account_jTextField, pwd_jTextField, confirm_pwd_JTextField, question_answer_JTextField;
    JButton register_JButton;
    JComboBox<String> questions_select_List;
    String[] questionsList = {"我最感兴趣的事情？", "最想去哪里旅游？", "家里人都叫你什么小名？", "初中班主任的名字？"};

    public Register() {
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
        this.setTitle("读者用户注册-图书管理系统");
        this.setBounds(120, 120, 377, 228);
//        按钮初始化
        this.register_JButton = new JButton("注册");
        this.login_JLabel = new JLabel("账   号：  ");
        this.pwd_JLabel = new JLabel("密   码：   ");
        this.confirm_pwd_JLabel = new JLabel("确 认 密 码:");
        this.confirm_pwd_JTextField = new JPasswordField(25);
        this.account_jTextField = new JTextField(25);
        this.pwd_jTextField = new JPasswordField(25);
        this.question_JLabel = new JLabel("密  保  问  题：");
        this.input_answer_JLabel = new JLabel("问 题 答 案：");
        this.question_answer_JTextField = new JTextField(25);
        this.questions_select_List = new JComboBox<>();
        for (String s : this.questionsList) {
            this.questions_select_List.addItem(s);
        }

//        加入组件
        this.add(login_JLabel);
        this.add(account_jTextField);
        this.add(pwd_JLabel);
        this.add(pwd_jTextField);
        this.add(confirm_pwd_JLabel);
        this.add(confirm_pwd_JTextField);
        this.add(question_JLabel);
        this.add(questions_select_List);
        this.add(new JLabel("                      "));
        this.add(input_answer_JLabel);
        this.add(question_answer_JTextField);
        this.add(register_JButton);


        //添加监听事件
        this.register_JButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String account = account_jTextField.getText();
                String pwd = pwd_jTextField.getText();
                String confirm_pwd = confirm_pwd_JTextField.getText();
                String question = (String) questions_select_List.getSelectedItem();
                String answer = question_answer_JTextField.getText();
                if (verification(account, pwd, confirm_pwd,answer)) {
                    //验证成功
                    //添加账号信息到用户表和密保表
                   if (create_account(account,pwd,question,answer)) {
                       JOptionPane.showMessageDialog(null, "注册成功", "恭喜",JOptionPane.PLAIN_MESSAGE);
                   }
                }
            }
        });
        this.setResizable(false);
        this.setVisible(true);
//        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private boolean verification(String account, String pwd, String confirm_pwd,String answer) {
        //注册验证

//        验证用户表中是否已存在该账号，存在不能注册
        if (isExisted(account)) {
            JOptionPane.showMessageDialog(null, "账号已存在", "提示",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (account.contains(" ")) {
            //账号不能含有空格
            JOptionPane.showMessageDialog(null, "账号不允许空格", "提示",JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (account.length() < 3) {
            JOptionPane.showMessageDialog(null, "账号长度最少为3位", "提示",JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (!pwd.equals(confirm_pwd)) {
            JOptionPane.showMessageDialog(null, "两次输入的密码不一致", "提示",JOptionPane.WARNING_MESSAGE);
            return false;
        }else if(answer.equals("")) {
            JOptionPane.showMessageDialog(null, "密保不能为空", "提示",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean create_account(String account , String pwd, String question, String answer) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DruidUtil.getDataSource());
        String sql = "INSERT INTO `mydb`.`用户`(`账号`, `密码`, `姓名`, `联系电话`, `账号标识`) VALUES (?, ?, NULL, NULL, NULL); ";
        String sql2 = "INSERT INTO `mydb`.`密保`(`账号`, `密保问题`, `密保`, `身份`) VALUES (?, ?, ?, '用户');";
        return (jdbcTemplate.update(sql,account,pwd) +jdbcTemplate.update(sql2,account,question,answer)) >1;
    }
    private boolean isExisted(String account) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DruidUtil.getDataSource());
        String sql = "SELECT `用户`.*FROM `用户`";
        List<User> userList = jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int i) throws SQLException {
                User user = new User();
                user.setAccount(rs.getString("账号"));
                user.setPwd(rs.getString("密码"));
                user.setName(rs.getString("姓名"));
                user.setPhone(rs.getString("联系电话"));
                user.setAccount_identification(rs.getString("账号标识"));
                return user;
            }
        });
        for (User user : userList) {
            if (account.equals(user.getAccount())) {
                return true;
            }
        }
        return false;
    }

}
