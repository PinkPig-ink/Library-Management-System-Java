package GUI.Information;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class information extends JFrame {

    JPanel contentPane, panel, p1, p2, pB1, pB2;
    JTabbedPane tp;
    JLabel lbTitle, lbID, lbName, lbPhone, lbUsable, lb1, lb2, lb3, lb4, lbHistory, lbBID, lbBName, lbAuthor, lbType,
            lbB1, lbB2, lbB3, lbB4, lbBID_1, lbBName_1, lbAuthor_1, lbType_1, lbB1_1, lbB2_1, lbB3_1, lbB4_1;
    JButton btModify, btReturn, btContinue;

    Vector row1, col1, row2, col2;
    DefaultTableModel mm1, mm2;
    JTable table1, table2;
    JScrollPane sp1, sp2;

    // 定义数据库需要的全局变量
    PreparedStatement ps = null;
    Connection ct = null;
    ResultSet rs = null;


    public information(String id) {
        // 绝对位置布局
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 700, 650);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // 个人信息面板
        panel = new JPanel();
        panel.setBackground(Color.GRAY);
        panel.setBounds(10, 10, 666, 150);
        contentPane.add(panel);
        panel.setLayout(null);

        lbTitle = new JLabel("个     人     信     息");
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setFont(new Font("宋体", Font.BOLD, 20));
        lbTitle.setBounds(100, 10, 300, 30);
        panel.add(lbTitle);

        lbID = new JLabel("账 号：");
        lbID.setForeground(Color.WHITE);
        lbID.setFont(new Font("宋体", Font.ITALIC, 12));
        lbID.setBounds(5, 60, 45, 20);
        panel.add(lbID);

        lbName = new JLabel("姓 名：");
        lbName.setForeground(Color.WHITE);
        lbName.setFont(new Font("宋体", Font.ITALIC, 12));
        lbName.setBounds(255, 60, 45, 20);
        panel.add(lbName);

        lbPhone = new JLabel("联系电话：");
        lbPhone.setForeground(Color.WHITE);
        lbPhone.setFont(new Font("宋体", Font.ITALIC, 12));
        lbPhone.setBounds(5, 90, 60, 20);
        panel.add(lbPhone);

        lbUsable = new JLabel("是否可用：");
        lbUsable.setForeground(Color.WHITE);
        lbUsable.setFont(new Font("宋体", Font.ITALIC, 12));
        lbUsable.setBounds(255, 90, 60, 20);
        panel.add(lbUsable);

        lb1 = new JLabel("- -");
        lb1.setForeground(Color.WHITE);
        lb1.setFont(new Font("宋体", Font.ITALIC, 12));
        lb1.setBounds(55, 60, 80, 20);
        panel.add(lb1);

        lb2 = new JLabel("- -");
        lb2.setForeground(Color.WHITE);
        lb2.setFont(new Font("宋体", Font.ITALIC, 12));
        lb2.setBounds(305, 60, 80, 20);
        panel.add(lb2);

        lb3 = new JLabel("- -");
        lb3.setForeground(Color.WHITE);
        lb3.setFont(new Font("宋体", Font.ITALIC, 12));
        lb3.setBounds(70, 90, 80, 20);
        panel.add(lb3);

        lb4 = new JLabel("- -");
        lb4.setForeground(Color.WHITE);
        lb4.setFont(new Font("宋体", Font.ITALIC, 12));
        lb4.setBounds(320, 90, 80, 20);
        panel.add(lb4);

        btModify = new JButton("修   改");
        btModify.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modify frame = new modify(id, lb2, lb3);
                frame.setVisible(true);

                String driver = "com.mysql.jdbc.Driver"; // 数据库连接描述符。
                String sourceURL = "jdbc:mysql://localhost/mydb?serverTimezone=UTC&useSSL=false&characterEncoding=utf-8&user=root&password=root";

                Connection conn = null;

                try {
                    Class.forName(driver);
                    ct=DriverManager.getConnection(sourceURL,"root","root");

                    ps=ct.prepareStatement("select * from 用户 where 账号=?");
                    ps.setString(1, id);
                    rs=ps.executeQuery();

                    while(rs.next()){
                        lb2.setText(rs.getString(3));
                        lb3.setText(rs.getString(4));
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                } finally{

                    try {
                        if(rs!=null){
                            rs.close();
                        }
                        if(ps!=null){
                            ps.close();
                        }
                        if(ct!=null){
                            ct.close();
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        btModify.setBounds(546, 65, 110, 25);
        panel.add(btModify);

        // 设置列名字
        col1 = new Vector();
        col2 = new Vector();
        col1.add("编号");
        col1.add("名称");
        col1.add("借书时间");
        col1.add("截止时间");
        col1.add("状态");
        col2.add("编号");
        col2.add("名称");
        col2.add("借书时间");
        col2.add("还书时间");

        // 连接数据库并取数据
        row1 = new Vector();
        row2 = new Vector();
        String driver = "com.mysql.jdbc.Driver"; // 数据库连接描述符。
        String sourceURL = "jdbc:mysql://localhost/mydb?serverTimezone=UTC&useSSL=false&characterEncoding=utf-8&user=root&password=root";
        Connection conn = null;

        // 读取个人信息
        try {
            Class.forName(driver);
            ct = DriverManager.getConnection(sourceURL, "root", "root");

            ps = ct.prepareStatement("select * from 用户 where 账号 = " + id);
            rs = ps.executeQuery();

            while (rs.next()) {
                lb1.setText(id);
                lb2.setText(rs.getString(3));
                lb3.setText(rs.getString(4));
                lb4.setText(rs.getString(5));
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

        // 读取未还书籍
        try {
            Class.forName(driver);
            ct = DriverManager.getConnection(sourceURL, "root", "root");

            ps = ct.prepareStatement(
                    "select a.图书编号,图书名称,借书时间,还书时间,允许最长借阅时间 from 借阅情况 a join 图书 b on a.图书编号=b.图书编号 where 借阅账号 = " + id
                            + " and 还书时间 = '未还'");
            rs = ps.executeQuery();

            while (rs.next()) {
                // 计算当前状态，已借时间大于允许最长借阅时间就到期
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                Date fromDate = dateFormat.parse(rs.getString(3));// 借书时间
                Date nowDate = new Date();// 现在时间
                long from = fromDate.getTime();
                long now = nowDate.getTime();
                int days = (int) ((now - from) / (1000 * 60 * 60 * 24));
                String status = "未到期";
                if (days > rs.getInt(5))
                    status = "已到期";

                // 计算截止时间
                Calendar cd = Calendar.getInstance();
                cd.setTime(fromDate);
                cd.add(Calendar.DATE, rs.getInt(5));
                Date endDate = cd.getTime();// 截止时间
                String end = dateFormat.format(endDate);
//				String endDate = rightNow.add(Calendar.DAY_OF_YEAR,10);//日期加10天

                Vector hang1 = new Vector();
                hang1.add(rs.getString(1));
                hang1.add(rs.getString(2));
                hang1.add(rs.getString(3));
                hang1.add(end);
                hang1.add(status);
                row1.add(hang1);
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

        mm1 = new DefaultTableModel(row1, col1) {
            private static final long serialVersionUID = 1L;

            // 设置单元格不可编辑
            public boolean isCellEditable(int rowIndex, int ColIndex) {
                return false;
            }
        };
        table1 = new JTable(mm1);
        TableRowSorter<DefaultTableModel> sorter1 = new TableRowSorter<DefaultTableModel>(mm1);
        table1.setRowSorter(sorter1);
        table1.getTableHeader().setReorderingAllowed(false);// 不可拖动

        // 设置列的宽度
        TableColumn column1 = null;
        for (int i = 0; i < 5; i++) {
            column1 = table1.getColumnModel().getColumn(i);
            if (i == 0) {
                column1.setPreferredWidth(50);
            } else {
                column1.setPreferredWidth(100);
            }
        }

        // 添加table2中选中某行事件监听，在下面显示其详情信息
        table1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = table1.getSelectedRow();
                String driver = "com.mysql.jdbc.Driver"; // 数据库连接描述符。
                String sourceURL = "jdbc:mysql://localhost/mydb?serverTimezone=UTC&useSSL=false&characterEncoding=utf-8&user=root&password=root";

                Connection conn = null;

                try {
                    Class.forName(driver);
                    ct = DriverManager.getConnection(sourceURL, "root", "root");

                    ps = ct.prepareStatement("select * from 图书 where 图书编号=?");
                    ps.setString(1, (String) table1.getValueAt(row, 0));
                    rs = ps.executeQuery();

                    while (rs.next()) {
                        lbB1.setText(rs.getString(1));
                        lbB2.setText(rs.getString(2));
                        lbB3.setText(rs.getString(3));
                        lbB4.setText(rs.getString(5));
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

        // 读取已还书籍
        try {
            Class.forName(driver);
            ct = DriverManager.getConnection(sourceURL, "root", "root");

            ps = ct.prepareStatement("select a.图书编号,图书名称,借书时间,还书时间 from 借阅情况 a join 图书 b on a.图书编号=b.图书编号 where 借阅账号 = "
                    + id + " and 还书时间 regexp '^[0-9]{8}$'");
            rs = ps.executeQuery();

            while (rs.next()) {
                Vector hang2 = new Vector();
                hang2.add(rs.getString(1));
                hang2.add(rs.getString(2));
                hang2.add(rs.getString(3));
                hang2.add(rs.getString(4));
                row2.add(hang2);
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

        mm2 = new DefaultTableModel(row2, col2) {
            private static final long serialVersionUID = 1L;

            // 设置单元格不可编辑
            public boolean isCellEditable(int rowIndex, int ColIndex) {
                return false;
            }
        };
        table2 = new JTable(mm2);
        TableRowSorter<DefaultTableModel> sorter2 = new TableRowSorter<DefaultTableModel>(mm2);
        table2.setRowSorter(sorter2);
        table2.getTableHeader().setReorderingAllowed(false);// 不可拖动

        // 设置列的宽度
        TableColumn column2 = null;
        for (int i = 0; i < 4; i++) {
            column2 = table2.getColumnModel().getColumn(i);
            if (i == 0) {
                column2.setPreferredWidth(50);
            } else {
                column2.setPreferredWidth(100);
            }
        }

        // 添加table2中选中某行事件监听，在下面显示其详情信息
        table2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = table2.getSelectedRow();
                String driver = "com.mysql.jdbc.Driver"; // 数据库连接描述符。
                String sourceURL = "jdbc:mysql://localhost/mydb?serverTimezone=UTC&useSSL=false&characterEncoding=utf-8&user=root&password=root";
                Connection conn = null;

                try {
                    Class.forName(driver);
                    ct = DriverManager.getConnection(sourceURL, "root", "root");

                    ps = ct.prepareStatement("select * from 图书 where 图书编号=?");
                    ps.setString(1, (String) table2.getValueAt(row, 0));
                    rs = ps.executeQuery();

                    while (rs.next()) {
                        lbB1_1.setText(rs.getString(1));
                        lbB2_1.setText(rs.getString(2));
                        lbB3_1.setText(rs.getString(3));
                        lbB4_1.setText(rs.getString(5));
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

        // 借阅情况标签
        lbHistory = new JLabel("借   阅   情   况");
        lbHistory.setHorizontalAlignment(SwingConstants.CENTER);
        lbHistory.setFont(new Font("宋体", Font.BOLD, 15));
        lbHistory.setBounds(10, 195, 200, 25);
        contentPane.add(lbHistory);

        // 借阅情况
        tp = new JTabbedPane(JTabbedPane.TOP);
        tp.setBounds(10, 230, 666, 370);
        contentPane.add(tp);

        // 未还图书
        p1 = new JPanel();
        tp.addTab("未还图书", null, p1, null);
        p1.setLayout(null);

        sp1 = new JScrollPane(table1);
        sp1.setBounds(10, 10, 641, 251);
        p1.add(sp1);

        pB1 = new JPanel();
        pB1.setLayout(null);
        pB1.setBackground(Color.GRAY);
        pB1.setBounds(0, 270, 661, 70);
        p1.add(pB1);

        lbBID = new JLabel("编 号：");
        lbBID.setForeground(Color.WHITE);
        lbBID.setFont(new Font("宋体", Font.ITALIC, 12));
        lbBID.setBounds(5, 10, 45, 20);
        pB1.add(lbBID);

        lbBName = new JLabel("名 称：");
        lbBName.setForeground(Color.WHITE);
        lbBName.setFont(new Font("宋体", Font.ITALIC, 12));
        lbBName.setBounds(205, 10, 45, 20);
        pB1.add(lbBName);

        lbAuthor = new JLabel("作 者：");
        lbAuthor.setForeground(Color.WHITE);
        lbAuthor.setFont(new Font("宋体", Font.ITALIC, 12));
        lbAuthor.setBounds(5, 40, 45, 20);
        pB1.add(lbAuthor);

        lbType = new JLabel("类 型：");
        lbType.setForeground(Color.WHITE);
        lbType.setFont(new Font("宋体", Font.ITALIC, 12));
        lbType.setBounds(205, 40, 45, 20);
        pB1.add(lbType);

        lbB1 = new JLabel("- -");
        lbB1.setForeground(Color.WHITE);
        lbB1.setFont(new Font("宋体", Font.ITALIC, 12));
        lbB1.setBounds(55, 10, 80, 20);
        pB1.add(lbB1);

        lbB2 = new JLabel("- -");
        lbB2.setForeground(Color.WHITE);
        lbB2.setFont(new Font("宋体", Font.ITALIC, 12));
        lbB2.setBounds(255, 10, 140, 20);
        pB1.add(lbB2);

        lbB3 = new JLabel("- -");
        lbB3.setForeground(Color.WHITE);
        lbB3.setFont(new Font("宋体", Font.ITALIC, 12));
        lbB3.setBounds(55, 40, 140, 20);
        pB1.add(lbB3);

        lbB4 = new JLabel("- -");
        lbB4.setForeground(Color.WHITE);
        lbB4.setFont(new Font("宋体", Font.ITALIC, 12));
        lbB4.setBounds(255, 40, 80, 20);
        pB1.add(lbB4);

        btReturn = new JButton("还   书");
        btReturn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String driver = "com.mysql.jdbc.Driver"; // 数据库连接描述符。
                String sourceURL = "jdbc:mysql://localhost/mydb?serverTimezone=UTC&useSSL=false&characterEncoding=utf-8&user=root&password=root";
                Connection conn = null;

                try {
                    Class.forName(driver);
                    ct = DriverManager.getConnection(sourceURL, "root", "root");

                    ps = ct.prepareStatement("update 图书 set 图书库存量=图书库存量+1 where 图书编号=?");
                    ps.setString(1, lbB1.getText());
                    ps.execute();

                    ps = ct.prepareStatement("update 借阅情况 set 还书时间=? where 借阅账号=? and 图书编号=? and 借书时间=?");
                    SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
                    Date dtime = new Date();
                    String time = df.format(dtime);
                    ps.setString(1, time);
                    ps.setString(2, id);
                    ps.setString(3, lbB1.getText());
                    ps.setString(4, (String) table1.getValueAt(table1.getSelectedRow(), 2));
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

                mm1.removeRow(table1.getSelectedRow());//刷新未还图书

                //刷新已还图书
                try {
                    Class.forName(driver);
                    ct = DriverManager.getConnection(sourceURL, "root", "root");
                    ps = ct.prepareStatement(
                            "select a.图书编号,图书名称,借书时间,还书时间 from 借阅情况 a join 图书 b on a.图书编号=b.图书编号 where 借阅账号 = " + id
                                    + " and 还书时间 regexp '^[0-9]{8}$'");
                    rs = ps.executeQuery();
                    mm2.setRowCount(0);
                    while (rs.next()) {
                        Vector hang2 = new Vector();
                        hang2.add(rs.getString(1));
                        hang2.add(rs.getString(2));
                        hang2.add(rs.getString(3));
                        hang2.add(rs.getString(4));
                        mm2.addRow(hang2);
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
                table2.updateUI();

                prompt frame = new prompt("还书成功！", 1, id);
                frame.setVisible(true);
            }
        });
        btReturn.setForeground(Color.WHITE);
        btReturn.setFont(new Font("宋体", Font.BOLD, 15));
        btReturn.setBackground(Color.ORANGE);
        btReturn.setBounds(425, 10, 150, 40);
        pB1.add(btReturn);

        // 已还图书
        p2 = new JPanel();
        tp.addTab("已还图书", null, p2, null);
        p2.setLayout(null);

        sp2 = new JScrollPane(table2);
        sp2.setBounds(10, 10, 641, 251);
        p2.add(sp2);

        pB2 = new JPanel();
        pB2.setLayout(null);
        pB2.setBackground(Color.GRAY);
        pB2.setBounds(0, 270, 661, 70);
        p2.add(pB2);

        lbBID_1 = new JLabel("编 号：");
        lbBID_1.setForeground(Color.WHITE);
        lbBID_1.setFont(new Font("宋体", Font.ITALIC, 12));
        lbBID_1.setBounds(5, 10, 45, 20);
        pB2.add(lbBID_1);

        lbBName_1 = new JLabel("名 称：");
        lbBName_1.setForeground(Color.WHITE);
        lbBName_1.setFont(new Font("宋体", Font.ITALIC, 12));
        lbBName_1.setBounds(205, 10, 45, 20);
        pB2.add(lbBName_1);

        lbAuthor_1 = new JLabel("作 者：");
        lbAuthor_1.setForeground(Color.WHITE);
        lbAuthor_1.setFont(new Font("宋体", Font.ITALIC, 12));
        lbAuthor_1.setBounds(5, 40, 45, 20);
        pB2.add(lbAuthor_1);

        lbType_1 = new JLabel("类 型：");
        lbType_1.setForeground(Color.WHITE);
        lbType_1.setFont(new Font("宋体", Font.ITALIC, 12));
        lbType_1.setBounds(205, 40, 45, 20);
        pB2.add(lbType_1);

        lbB1_1 = new JLabel("- -");
        lbB1_1.setForeground(Color.WHITE);
        lbB1_1.setFont(new Font("宋体", Font.ITALIC, 12));
        lbB1_1.setBounds(55, 10, 80, 20);
        pB2.add(lbB1_1);

        lbB2_1 = new JLabel("- -");
        lbB2_1.setForeground(Color.WHITE);
        lbB2_1.setFont(new Font("宋体", Font.ITALIC, 12));
        lbB2_1.setBounds(255, 10, 140, 20);
        pB2.add(lbB2_1);

        lbB3_1 = new JLabel("- -");
        lbB3_1.setForeground(Color.WHITE);
        lbB3_1.setFont(new Font("宋体", Font.ITALIC, 12));
        lbB3_1.setBounds(55, 40, 140, 20);
        pB2.add(lbB3_1);

        lbB4_1 = new JLabel("- -");
        lbB4_1.setForeground(Color.WHITE);
        lbB4_1.setFont(new Font("宋体", Font.ITALIC, 12));
        lbB4_1.setBounds(255, 40, 80, 20);
        pB2.add(lbB4_1);

        btContinue = new JButton("继 续 借 书");
        btContinue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                library frame = new library(id);
                frame.setVisible(true);
                dispose();
            }
        });
        btContinue.setForeground(Color.WHITE);
        btContinue.setFont(new Font("宋体", Font.BOLD, 15));
        btContinue.setBackground(Color.ORANGE);
        btContinue.setBounds(425, 10, 150, 40);
        pB2.add(btContinue);

    }
}
