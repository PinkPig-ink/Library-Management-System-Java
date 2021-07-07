package GUI.Information;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

//提示信息窗口类
public class prompt extends JFrame {

    JPanel contentPane;
    JLabel lbMessage;
    JButton btY,btN;

    // 定义数据库需要的全局变量
    PreparedStatement ps = null;
    Connection ct = null;
    ResultSet rs = null;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
//		prompt frame = new prompt("什么都没有",3,"123");
//		frame.setVisible(true);
    }

    /**
     * Create the frame.
     */
    public prompt(String s,int t,String id) {
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 352, 200);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //提示信息标签
        lbMessage = new JLabel(s);
        if(t==1)
            lbMessage.setForeground(Color.GREEN);
        else
            lbMessage.setForeground(Color.RED);
        lbMessage.setFont(new Font("宋体", Font.BOLD, 20));
        lbMessage.setHorizontalAlignment(SwingConstants.CENTER);
        lbMessage.setBounds(38, 0, 265, 89);
        contentPane.add(lbMessage);

        //确定按钮，点击后关闭提示窗口
        btY = new JButton("确   定");
        btY.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btY.setBounds(48, 99, 97, 23);
        contentPane.add(btY);

        //关闭按钮，点击后关闭提示窗口
        btN = new JButton("关   闭");
        btN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btN.setBounds(203, 99, 97, 23);
        contentPane.add(btN);



        if(t==3){//注销确定界面
            btY.setText("确 认 注 销");
            btN.setText("取   消");
            btY.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String driver = "com.mysql.jdbc.Driver"; // 数据库连接描述符。
                    String sourceURL = "jdbc:mysql://localhost/mydb?serverTimezone=UTC&useSSL=false&characterEncoding=utf-8&user=root&password=root";

                    Connection conn = null;

                    try {
                        Class.forName(driver);
                        ct = DriverManager.getConnection(sourceURL, "root", "root");

                        ps = ct.prepareStatement("delete from 用户 where 账号=?");
                        ps.setString(1, id);
                        ps.execute();

                        dispose();
                        prompt frame = new prompt("注销成功！",4,id);
                        frame.setVisible(true);
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
        }
        else if(t==4) {//注销后退出
            btY.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            btN.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
        }
    }
}
