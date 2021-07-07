package GUI.Information;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class library extends JFrame {

    JPanel contentPane, panel;
    JTextField tfSearch;
    JLabel lbTitle, lbUser, lbDetail, lbID, lbName, lbType, lbNum, lbAuthor, lbReserve, lbTime, lb1, lb2, lb3, lb4, lb5,
            lb6, lb7;
    JButton btPerson, btSearch, btBorrow;
    JRadioButton rbt1, rbt2, rbt3, rbt4;
    ButtonGroup group;// 单选按钮组
    JCheckBox cbx;
    //	int basis = 0;//默认按名称搜索
    String basis = "图书名称";// 搜索依据，默认按名称搜索
    //	String prompt = "请输入" + basis;// 搜索提示
    Vector row, col;// row用来存放行数据，col存放列名
    DefaultTableModel mm;
    JTable table;
    JScrollPane sp;// 存放JTable的Pane

    // 定义数据库需要的全局变量
    PreparedStatement ps = null;
    Connection ct = null;
    ResultSet rs = null;
    private JLabel lblNewLabel;

    /**
     * Launch the application.
     */


    /**
     * Create the frame.
     */
    public library(String id) {
        // 设置列名字
        col = new Vector();
        col.add("编号");
        col.add("名称");
        col.add("作者");
        col.add("类型");
        col.add("库存");
//	       columnNames.add("被借次数");
//	       columnNames.add("借阅时间");

        // 连接数据库并取数据
        row = new Vector();// row可以存放多行,开始从数据库里取
        String driver = "com.mysql.jdbc.Driver"; // 数据库连接描述符。
        String sourceURL = "jdbc:mysql://localhost/mydb?serverTimezone=UTC&useSSL=false&characterEncoding=utf-8&user=root&password=root";

        Connection conn = null;

        try {
            Class.forName(driver);// 加载驱动
            ct = DriverManager.getConnection(sourceURL, "root", "root");// 得到连接

            ps = ct.prepareStatement("select * from 图书");
            rs = ps.executeQuery();

            while (rs.next()) {
                Vector hang = new Vector();
                hang.add(rs.getString(1));
                hang.add(rs.getString(2));
                hang.add(rs.getString(3));
                hang.add(rs.getString(5));
                hang.add(rs.getString(4));
//	               hang.add(rs.getString(6));
//	               hang.add(rs.getString(7));
                row.add(hang);// 加入到row
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

        mm = new DefaultTableModel(row, col) {
            private static final long serialVersionUID = 1L;

            // 设置单元格不可编辑
            public boolean isCellEditable(int rowIndex, int ColIndex) {
                return false;
            }
        };
        table = new JTable(mm);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(mm);
        table.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter("^[1-9]\\d*$", 4));
        table.getTableHeader().setReorderingAllowed(false);// 不可拖动

        // 设置列的宽度
        TableColumn column = null;
        for (int i = 0; i < 5; i++) {
            column = table.getColumnModel().getColumn(i);
            if (i == 1 || i == 2) {
                column.setPreferredWidth(100);// 第二列和第三列更宽一些
            } else {
                column.setPreferredWidth(50);
            }
        }

        // 添加table中选中某行事件监听，在下面显示其详情信息
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = table.getSelectedRow();
                String driver = "com.mysql.jdbc.Driver"; // 数据库连接描述符。
                String sourceURL = "jdbc:mysql://localhost/mydb?serverTimezone=UTC&useSSL=false&characterEncoding=utf-8&user=root&password=root";

                Connection conn = null;

                try {
                    Class.forName(driver);
                    ct = DriverManager.getConnection(sourceURL, "root", "root");

                    ps = ct.prepareStatement("select * from 图书 where 图书编号=?");
                    ps.setString(1, (String) table.getValueAt(row, 0));
                    rs = ps.executeQuery();

                    while (rs.next()) {
                        lb1.setText(rs.getString(1));
                        lb2.setText(rs.getString(2));
                        lb3.setText(rs.getString(3));
                        lb4.setText(rs.getString(5));
                        lb5.setText(rs.getString(6));
                        lb6.setText(rs.getString(4));
                        lb7.setText(rs.getString(7));
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

        // 绝对位置布局
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 700, 650);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // 标题
        lbTitle = new JLabel("图     书     展     示");
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setFont(new Font("宋体", Font.BOLD, 25));
        lbTitle.setBounds(10, 10, 530, 50);
        contentPane.add(lbTitle);

        // 个人信息按钮
        btPerson = new JButton("个 人 信 息");
        btPerson.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                information frame = new information(lbUser.getText());
                frame.setVisible(true);
                dispose();
            }
        });
        btPerson.setBounds(560, 20, 110, 25);
        contentPane.add(btPerson);

        // 账号
        lbUser = new JLabel(id);
        lbUser.setFont(new Font("宋体", Font.BOLD, 12));
        lbUser.setBackground(Color.GRAY);
        lbUser.setForeground(Color.DARK_GRAY);
        lbUser.setBounds(580, 50, 80, 15);
        contentPane.add(lbUser);

        // 放JTable的滚动面板
        sp = new JScrollPane(table);
        sp.setBounds(10, 100, 500, 350);
        contentPane.add(sp);

        // 搜索框
        tfSearch = new JTextField("请输入" + basis);
        tfSearch.setForeground(Color.GRAY);
        tfSearch.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfSearch.getText().equals("请输入" + basis)) {
                    tfSearch.setText("");
                    tfSearch.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfSearch.getText().equals("")) {
                    tfSearch.setForeground(Color.GRAY);
                    tfSearch.setText("请输入" + basis);
                }

            }
        });
        tfSearch.setBounds(515, 100, 100, 25);
        contentPane.add(tfSearch);
        tfSearch.setColumns(10);

        // 搜索依据
        rbt1 = new JRadioButton("按图书编号");
        rbt1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
//				basis=0;
                basis = "图书编号";
//				tfSearch.setForeground(Color.GRAY);
                if (tfSearch.getText().matches("^$|请输入图书(编号|名称|作者|类型)"))
                    tfSearch.setText("请输入" + basis);
            }
        });
        rbt1.setBounds(570, 140, 100, 25);
        contentPane.add(rbt1);

        rbt2 = new JRadioButton("按图书名称");
        rbt2.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
//				basis=1;
                basis = "图书名称";
//				tfSearch.setForeground(Color.GRAY);
                if (tfSearch.getText().matches("^$|请输入图书(编号|名称|作者|类型)"))
                    tfSearch.setText("请输入" + basis);
            }
        });
        rbt2.setBounds(570, 175, 100, 25);
        contentPane.add(rbt2);

        rbt3 = new JRadioButton("按图书作者");
        rbt3.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
//				basis=2;
                basis = "图书作者";
//				tfSearch.setForeground(Color.GRAY);
                if (tfSearch.getText().matches("^$|请输入图书(编号|名称|作者|类型)"))
                    tfSearch.setText("请输入" + basis);
            }
        });
        rbt3.setBounds(570, 210, 100, 23);
        contentPane.add(rbt3);

        rbt4 = new JRadioButton("按图书类型");
        rbt4.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
//				basis=3;
                basis = "图书类型";
//				tfSearch.setForeground(Color.GRAY);
                if (tfSearch.getText().matches("^$|请输入图书(编号|名称|作者|类型)"))
                    tfSearch.setText("请输入" + basis);
            }
        });
        rbt4.setBounds(570, 245, 100, 25);
        contentPane.add(rbt4);

        // 单选按钮加到一个组件中
        group = new ButtonGroup();
        group.add(rbt1);
        group.add(rbt2);
        group.add(rbt3);
        group.add(rbt4);

        // 搜索按钮
        btSearch = new JButton("搜索");
        btSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String driver = "com.mysql.jdbc.Driver"; // 数据库连接描述符。
                String sourceURL = "jdbc:mysql://localhost/mydb?serverTimezone=UTC&useSSL=false&characterEncoding=utf-8&user=root&password=root";

                Connection conn = null;

                try {
                    Class.forName(driver);
                    ct = DriverManager.getConnection(sourceURL, "root", "root");

                    String tSearch = tfSearch.getText();
                    if(tSearch.matches("^$|请输入图书(编号|名称|作者|类型)"))tSearch="";
                    ps = ct.prepareStatement(
                            "select * from 图书 where " + basis + " like " + "'%" + tSearch + "%'");

                    rs = ps.executeQuery();

//			           row.clear();//rowData的删除和增加不能同时进行，这样一边添加记录，一边删除记录，会出现数组越界的情况
                    mm.setRowCount(0);


                    while (rs.next()) {
                        // rowData可以存放多行
                        Vector hang = new Vector();
                        hang.add(rs.getString(1));
                        hang.add(rs.getString(2));
                        hang.add(rs.getString(3));
                        hang.add(rs.getString(5));
                        hang.add(rs.getString(4));

                        // 加入到rowData
//						row.add(hang);
                        mm.addRow(hang);
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
                table.updateUI();
            }
        });
        btSearch.setBounds(620, 100, 60, 25);
        contentPane.add(btSearch);

        // 过滤库存为0的图书
        cbx = new JCheckBox("显示库存为0的图书");
        cbx.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (cbx.isSelected())
                    sorter.setRowFilter(null);
                else
                    sorter.setRowFilter(RowFilter.regexFilter("^[1-9]\\d*$", 4));
            }
        });
        cbx.setFont(new Font("宋体", Font.BOLD, 15));
        cbx.setBounds(520, 300, 160, 25);
        contentPane.add(cbx);

        // 放详情信息的面板
        panel = new JPanel();
        panel.setBackground(Color.GRAY);
        panel.setBounds(10, 470, 665, 120);
        contentPane.add(panel);
        panel.setLayout(null);

        lbDetail = new JLabel("详 情 信 息");
        lbDetail.setForeground(Color.WHITE);
        lbDetail.setBounds(5, 5, 80, 20);
        panel.add(lbDetail);

        lbID = new JLabel("编 号：");
        lbID.setForeground(Color.WHITE);
        lbID.setFont(new Font("宋体", Font.ITALIC, 12));
        lbID.setBounds(5, 30, 45, 20);
        panel.add(lbID);

        lbName = new JLabel("名 称：");
        lbName.setForeground(Color.WHITE);
        lbName.setFont(new Font("宋体", Font.ITALIC, 12));
        lbName.setBounds(205, 30, 45, 20);
        panel.add(lbName);

        lbAuthor = new JLabel("作 者：");
        lbAuthor.setForeground(Color.WHITE);
        lbAuthor.setFont(new Font("宋体", Font.ITALIC, 12));
        lbAuthor.setBounds(425, 30, 45, 20);
        panel.add(lbAuthor);

        lbType = new JLabel("类 型：");
        lbType.setForeground(Color.WHITE);
        lbType.setFont(new Font("宋体", Font.ITALIC, 12));
        lbType.setBounds(5, 60, 45, 20);
        panel.add(lbType);

        lbNum = new JLabel("被借次数：");
        lbNum.setForeground(Color.WHITE);
        lbNum.setFont(new Font("宋体", Font.ITALIC, 12));
        lbNum.setBounds(205, 60, 60, 20);
        panel.add(lbNum);

        lbReserve = new JLabel("库存量：");
        lbReserve.setForeground(Color.WHITE);
        lbReserve.setFont(new Font("宋体", Font.ITALIC, 12));
        lbReserve.setBounds(5, 90, 50, 20);
        panel.add(lbReserve);

        lbTime = new JLabel("借阅时间：");
        lbTime.setForeground(Color.WHITE);
        lbTime.setFont(new Font("宋体", Font.ITALIC, 12));
        lbTime.setBounds(205, 90, 60, 20);
        panel.add(lbTime);

        lb1 = new JLabel("- -");
        lb1.setForeground(Color.WHITE);
        lb1.setFont(new Font("宋体", Font.ITALIC, 12));
        lb1.setBounds(55, 30, 80, 20);
        panel.add(lb1);

        lb2 = new JLabel("- -");
        lb2.setForeground(Color.WHITE);
        lb2.setFont(new Font("宋体", Font.ITALIC, 12));
        lb2.setBounds(255, 30, 140, 20);
        panel.add(lb2);

        lb3 = new JLabel("- -");
        lb3.setForeground(Color.WHITE);
        lb3.setFont(new Font("宋体", Font.ITALIC, 12));
        lb3.setBounds(475, 30, 140, 20);
        panel.add(lb3);

        lb4 = new JLabel("- -");
        lb4.setForeground(Color.WHITE);
        lb4.setFont(new Font("宋体", Font.ITALIC, 12));
        lb4.setBounds(55, 60, 80, 20);
        panel.add(lb4);

        lb5 = new JLabel("- -");
        lb5.setForeground(Color.WHITE);
        lb5.setFont(new Font("宋体", Font.ITALIC, 12));
        lb5.setBounds(270, 60, 80, 20);
        panel.add(lb5);

        lb6 = new JLabel("- -");
        lb6.setForeground(Color.WHITE);
        lb6.setFont(new Font("宋体", Font.ITALIC, 12));
        lb6.setBounds(60, 90, 80, 20);
        panel.add(lb6);

        lb7 = new JLabel("- -");
        lb7.setForeground(Color.WHITE);
        lb7.setFont(new Font("宋体", Font.ITALIC, 12));
        lb7.setBounds(270, 90, 80, 20);
        panel.add(lb7);

        btBorrow = new JButton("借 阅 此 书");
        btBorrow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Integer.parseInt(lb6.getText()) > 0) {

                    String driver = "com.mysql.jdbc.Driver"; // 数据库连接描述符。
                    String sourceURL = "jdbc:mysql://localhost/mydb?serverTimezone=UTC&useSSL=false&characterEncoding=utf-8&user=root&password=root";

                    Connection conn = null;

                    try {
                        Class.forName(driver);
                        ct = DriverManager.getConnection(sourceURL, "root", "root");

                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                        Date fromDate = new Date();
                        String time = dateFormat.format(fromDate);
                        boolean usable = true;
                        boolean NoRepeat = true;// 标识是否会出先主键重复的情况，true代表不重复

                        ps = ct.prepareStatement("select 账号标识 from 用户 where 账号=?");
                        ps.setString(1, id);
                        rs = ps.executeQuery();
                        while (rs.next()) {
                            if (rs.getString(1).equals("不可用"))
                                usable = false;
                            System.out.println(usable);
                        }
                        ps = ct.prepareStatement("select * from 借阅情况 where 借阅账号=?");
                        ps.setString(1, id);
                        rs = ps.executeQuery();
                        while (rs.next()) {
                            if (rs.getString(2).equals(lb1.getText()) && rs.getString(3).equals(time))
                                NoRepeat = false;
                        }

                        if(!usable){
                            prompt frame = new prompt("该账号已被冻结，不能借书！", 2, id);
                            frame.setVisible(true);
                        }

                        else if (NoRepeat) {
                            ps = ct.prepareStatement("update 图书 set 图书库存量=图书库存量-1,借阅次数=借阅次数+1 where 图书编号=?");
                            ps.setString(1, lb1.getText());
                            ps.execute();

                            ps = ct.prepareStatement("insert into 借阅情况 values(?,?,?,'未还')");
                            ps.setString(1, id);
                            ps.setString(2, lb1.getText());
                            ps.setString(3, time);
                            ps.execute();

                            prompt frame = new prompt("借书成功！", 1, id);
                            frame.setVisible(true);
                        } else {
                            prompt frame = new prompt("你今天已经借过这本书了！", 2, id);
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
                } else {
                    prompt frame = new prompt("书被借完了，换本看看吧！", 2, id);
                    frame.setVisible(true);
                }
            }
        });
        btBorrow.setForeground(Color.WHITE);
        btBorrow.setBackground(Color.ORANGE);
        btBorrow.setFont(new Font("宋体", Font.BOLD, 15));
        btBorrow.setBounds(425, 60, 150, 40);
        panel.add(btBorrow);
    }
}

