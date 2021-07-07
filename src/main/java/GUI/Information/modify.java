package GUI.Information;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class modify extends JFrame {

    private JPanel contentPane,p1,p2;
    JTabbedPane tp;
    JLabel lbID,lbName,lbPhone,lbID_1,lbOld,lbNew,lbConfirm;
    JTextField tfName,tfPhone,tfOld,tfNew,tfConfirm;
    JButton btModify1,btModify2;

    // 定义数据库需要的全局变量
    PreparedStatement ps = null;
    Connection ct = null;
    ResultSet rs = null;
    private JButton btnNewButton;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
//		modify frame = new modify("1346889789",new JLabel(),new JLabel());
//		frame.setVisible(true);
    }

    /**
     * Create the frame.
     */
    public modify(String id,JLabel name,JLabel phone) {
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 350);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        tp = new JTabbedPane(JTabbedPane.TOP);
        tp.setBounds(10, 50, 416, 250);
        contentPane.add(tp);

        //基本信息
        p1 = new JPanel();
        tp.addTab("基本信息", null, p1, null);
        p1.setLayout(null);

        lbID = new JLabel("账 号：");
        lbID.setFont(new Font("宋体", Font.BOLD, 15));
        lbID.setBounds(10, 10, 80, 30);
        p1.add(lbID);

        lbName = new JLabel("姓 名：");
        lbName.setFont(new Font("宋体", Font.BOLD, 15));
        lbName.setBounds(10, 50, 80, 30);
        p1.add(lbName);

        lbPhone = new JLabel("联系电话：");
        lbPhone.setFont(new Font("宋体", Font.BOLD, 15));
        lbPhone.setBounds(10, 90, 80, 30);
        p1.add(lbPhone);

        lbID_1 = new JLabel(id);
        lbID_1.setFont(new Font("宋体", Font.BOLD, 15));
        lbID_1.setBounds(100, 10, 300, 30);
        p1.add(lbID_1);

        tfName = new JTextField();
        tfName.setFont(new Font("宋体", Font.BOLD, 15));
        tfName.setColumns(10);
        tfName.setBounds(100, 50, 300, 30);
        p1.add(tfName);

        tfPhone = new JTextField();
        tfPhone.setFont(new Font("宋体", Font.BOLD, 15));
        tfPhone.setColumns(10);
        tfPhone.setBounds(100, 90, 300, 30);
        p1.add(tfPhone);

        btModify1 = new JButton("确 认 修 改");
        btModify1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String driver = "com.mysql.jdbc.Driver"; // 数据库连接描述符。
                String sourceURL = "jdbc:mysql://localhost/mydb?serverTimezone=UTC&useSSL=false&characterEncoding=utf-8&user=root&password=root";

                Connection conn = null;

                // 读取基本信息
                try {
                    Class.forName(driver);
                    ct = DriverManager.getConnection(sourceURL, "root", "root");

                    ps = ct.prepareStatement("update 用户 set 姓名=?,联系电话=? where 账号=?");
                    ps.setString(1, tfName.getText());
                    ps.setString(2, tfPhone.getText());
                    ps.setString(3, id);
                    ps.execute();
                } catch (Exception e1) {
                    e1.printStackTrace();
                } finally {

                    try {
                        if (rs != null) {
                            rs.close();
                        }
                        if (ps != null) {
                            ps.close();
                        }
                        if (ct != null) {
                            ct.close();
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }

                name.setText(tfName.getText());
                phone.setText(tfPhone.getText());

                prompt frame = new prompt("修改成功！",1,id);
                frame.setVisible(true);
                dispose();
            }
        });
        btModify1.setFont(new Font("宋体", Font.BOLD, 15));
        btModify1.setBounds(133, 150, 150, 30);
        p1.add(btModify1);

        //密码
        p2 = new JPanel();
        tp.addTab("密码", null, p2, null);
        p2.setLayout(null);

        lbOld = new JLabel("旧密码");
        lbOld.setFont(new Font("宋体", Font.BOLD, 15));
        lbOld.setBounds(10, 10, 80, 30);
        p2.add(lbOld);

        lbNew = new JLabel("新密码");
        lbNew.setFont(new Font("宋体", Font.BOLD, 15));
        lbNew.setBounds(10, 50, 80, 30);
        p2.add(lbNew);

        lbConfirm = new JLabel("确认密码");
        lbConfirm.setFont(new Font("宋体", Font.BOLD, 15));
        lbConfirm.setBounds(10, 90, 80, 30);
        p2.add(lbConfirm);

        tfOld = new JTextField();
        tfOld.setFont(new Font("宋体", Font.BOLD, 15));
        tfOld.setColumns(10);
        tfOld.setBounds(100, 10, 300, 30);
        p2.add(tfOld);

        tfNew = new JTextField();
        tfNew.setFont(new Font("宋体", Font.BOLD, 15));
        tfNew.setColumns(10);
        tfNew.setBounds(100, 50, 300, 30);
        p2.add(tfNew);

        tfConfirm = new JTextField();
        tfConfirm.setFont(new Font("宋体", Font.BOLD, 15));
        tfConfirm.setColumns(10);
        tfConfirm.setBounds(100, 90, 300, 30);
        p2.add(tfConfirm);

        btModify2 = new JButton("确 认 修 改");
        btModify2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String driver = "com.mysql.jdbc.Driver"; // 数据库连接描述符。
                String sourceURL = "jdbc:mysql://localhost/mydb?serverTimezone=UTC&useSSL=false&characterEncoding=utf-8&user=root&password=root";

                Connection conn = null;

                try {
                    Class.forName(driver);
                    ct = DriverManager.getConnection(sourceURL, "root", "root");

                    ps = ct.prepareStatement("select 密码 from 用户 where 账号=?");
                    ps.setString(1, id);
                    rs=ps.executeQuery();

                    //检查密码是否正确
                    boolean right = false;
                    while(rs.next()) {
                        System.out.println(rs.getString(1));
                        if(rs.getString(1).equals(tfOld.getText())&&tfNew.getText().equals(tfConfirm.getText()))right=true;
                    }

                    if(right) {
                        ps = ct.prepareStatement("update 用户 set 密码=? where 账号=?");
                        ps.setString(1, tfNew.getText());
                        ps.setString(2, id);
                        ps.execute();

                        prompt frame = new prompt("修改密码成功！",1,id);
                        frame.setVisible(true);
                    }else {
                        prompt frame = new prompt("密码错误，请重新输入！",2,id);
                        frame.setVisible(true);
                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                } finally {

                    try {
                        if (rs != null) {
                            rs.close();
                        }
                        if (ps != null) {
                            ps.close();
                        }
                        if (ct != null) {
                            ct.close();
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        btModify2.setFont(new Font("宋体", Font.BOLD, 15));
        btModify2.setBounds(133, 150, 150, 30);
        p2.add(btModify2);

        btnNewButton = new JButton("\u6CE8 \u9500");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                prompt frame = new prompt("确定要注销此账号吗？",3,id);
                frame.setVisible(true);
            }
        });
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setBackground(Color.RED);
        btnNewButton.setBounds(360, 10, 66, 25);
        contentPane.add(btnNewButton);


        String driver = "com.mysql.jdbc.Driver"; // 数据库连接描述符。
        String sourceURL = "jdbc:mysql://localhost/mydb?serverTimezone=UTC&useSSL=false&characterEncoding=utf-8&user=root&password=root";

        Connection conn = null;

        // 读取基本信息
        try {
            Class.forName(driver);
            ct = DriverManager.getConnection(sourceURL, "root", "root");

            ps = ct.prepareStatement("select * from 用户 where 账号 = " + id);
            rs = ps.executeQuery();

            while (rs.next()) {
                tfName.setText(rs.getString(3));
                tfPhone.setText(rs.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (ct != null) {
                    ct.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
