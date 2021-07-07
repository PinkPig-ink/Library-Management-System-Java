package GUI.Forget;

import Tables.Secret;
import jdbc.DruidUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;

public class ForgetPwd extends JFrame {
    private JLabel account_JLabel;
    private JTextField account_jTextField;
    private JButton find_JButton;
    private JRadioButton user_radio, user_administrator_radio, librarian_radio;
    private ButtonGroup identity = new ButtonGroup();
    private String question;
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(DruidUtil.getDataSource());

    public ForgetPwd() {
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
        this.setTitle("忘记密码-图书管理系统");
        this.setBounds(120, 120, 377, 132);
//        按钮初始化
        this.account_JLabel = new JLabel("找回密码的账号：");
        this.account_jTextField = new JTextField(20);
        this.find_JButton = new JButton("验证密保");
        this.user_radio = new JRadioButton("用户      ");
        this.user_administrator_radio = new JRadioButton("用户信息管理员       ");
        this.librarian_radio = new JRadioButton("图书管理员      ");
        //加入到组单选
        this.identity.add(user_radio);
        this.identity.add(user_administrator_radio);
        this.identity.add(librarian_radio);

//        加入组件
        this.add(account_JLabel);
        this.add(account_jTextField);
        this.add(user_radio);
        this.add(user_administrator_radio);
        this.add(librarian_radio);
        this.add(find_JButton);

//        监听事件
        this.find_JButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verification();
            }
        });
        this.setResizable(false);
        this.setVisible(true);
//        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void verification() {
        //通过账号拿到对应的密保问题
        String current = account_jTextField.getText();
        String table = "";
        table = getSelected(identity);

        String sql = "SELECT `密保`.*FROM `密保`";
        //获取密保表数据导入list列表
        List<Secret> list = jdbcTemplate.query(sql, new RowMapper<Secret>() {
            @Override
            public Secret mapRow(ResultSet rs, int i) throws SQLException {
                Secret secret = new Secret();
                secret.setAccount(rs.getString("账号"));
                secret.setQuestion(rs.getString("密保问题"));
                secret.setAnswer(rs.getString("密保"));
                secret.setStatus(rs.getString("身份"));
                return secret;
            }
        });
//        for (Secret secret : list) {
//            System.out.println(secret);
//        }
        Boolean check = false;
        String newPwd = "";
        for (Secret secret : list) {
            if (current.equals(secret.getAccount()) && secret.getStatus().equals(table)) {
                //验证密保
//                String input = JOptionPane.showInputDialog(null, "密保问题：" + secret.getQuestion(), "验证密保", JOptionPane.PLAIN_MESSAGE);
                while (!JOptionPane.showInputDialog(null, "密保问题：" + secret.getQuestion(), "验证密保", JOptionPane.PLAIN_MESSAGE).equals(secret.getAnswer())) {
                    //进入重置密码程序
                    JOptionPane.showMessageDialog(null, "密保错误", "很抱歉", JOptionPane.ERROR_MESSAGE);
                }
                newPwd = JOptionPane.showInputDialog(null, "新的密码：", "重置密码", JOptionPane.PLAIN_MESSAGE);
                check = true;
            }
        }
        if (check) {
            changePwd(newPwd, table, current);
            JOptionPane.showMessageDialog(null, "改密成功，请牢记您的密码！");
        } else {
            JOptionPane.showMessageDialog(null, "账号不存在", "很抱歉", JOptionPane.ERROR_MESSAGE);
        }

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

    //改密码，传入新密码，用户身份表，改密码的账户
    private void changePwd(String newPwd, String table, String account) {
        String sql = null;
        if (table.equals("用户"))
            sql = "UPDATE 用户 SET 密码=? WHERE 账号=?";
        else if (table.equals("用户信息管理员"))
            sql = "UPDATE 用户信息管理员 SET 密码=? WHERE 账号=?";
        else if (table.equals("图书管理员"))
            sql = "UPDATE 图书管理员 SET 密码=? WHERE 账号=?";
        if (sql != null) {
            jdbcTemplate.update(sql, newPwd, account);
            System.out.println("修改成功");
        }
    }

}
