package com.kkang.view;

import com.kkang.mapper.UserMassage;
import com.kkang.pojo.User;
import com.kkang.pojo.UserAllMassage;
import com.kkang.utils.MybatisUtil;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class MyWindows extends JFrame {
    JTabbedPane jTabbedPane = null;
    JPanel jpanel1 = new JPanel();
    JPanel jpanel2 = new JPanel();
    JPanel jpanel3 = new JPanel();

    public MyWindows() {
        jTabbedPane = new JTabbedPane();
        jTabbedPane.add("用户信息管理员", jpanel1);
        jTabbedPane.add("图书管理员", jpanel2);
        jTabbedPane.add("借书未还", jpanel3);
        this.add(jTabbedPane);
        this.setTitle("图书管理员");
        jpanel1.setLayout(null);
        jpanel2.setLayout(null);
        jpanel3.setLayout(null);

        String[] c = {"添加", "删除"};
        getData(MybatisUtil.getSqlSession().getMapper(UserMassage.class).selectAllUserMassage(), jpanel1, c);
        this.setSize(1000, 800);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        jTabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                List<UserAllMassage> list = null;
                if (jTabbedPane.getSelectedIndex() == 0)
                    list = MybatisUtil.getSqlSession().getMapper(UserMassage.class).selectAllUserMassage();
                if (jTabbedPane.getSelectedIndex() == 1)
                   list =  MybatisUtil.getSqlSession().getMapper(UserMassage.class).selectAllLibraryMsg();
                if(jTabbedPane.getSelectedIndex() == 2){
                    List<UserAllMassage> list1 =  MybatisUtil.getSqlSession().getMapper(UserMassage.class).selectAllUser();
                    List<User> list2 = MybatisUtil.getSqlSession().getMapper(UserMassage.class).selectUnReturn();
                    list = new ArrayList<UserAllMassage>();
                    for (UserAllMassage userAllMassage : list1) {
                        for (User user : list2) {
                            if(userAllMassage.getUserId().equals(user.getUserId())){
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                                Date now = new Date();
                                String nowtime = sdf.format(now);
                                if(Integer.parseInt(nowtime)-Integer.parseInt(user.getGetTime())>7){
                                    list.add(userAllMassage);
                                }
                            }
                        }
                    }
                }
                System.out.println(list);
                if(jTabbedPane.getSelectedIndex() == 2){
                    String[] a = {"解冻","冻结"};
                    getUnReturn(list, (JPanel) jTabbedPane.getSelectedComponent(),a);
                }
                if(jTabbedPane.getSelectedIndex() != 2){
                    getData(list, (JPanel) jTabbedPane.getSelectedComponent(),c);
                }
                Component selectedComponent = jTabbedPane.getSelectedComponent();
            }
        });
    }

    public void getData(List<UserAllMassage> list, JPanel jpanel, String[] c) {
        jpanel.removeAll();
        JTextField t = new JTextField(10);
        JTextField t1 = new JTextField(10);
        JTextField t2 = new JTextField(10);
        JTextField t3 = new JTextField(10);
        JLabel k = new JLabel();
        JLabel k1 = new JLabel();
        JLabel k2 = new JLabel();
        JLabel k3 = new JLabel();
        JLabel k7 = new JLabel();
        JButton b = new JButton("执行");
        Vector row, col;
        col = new Vector();
        col.add("账号");
        col.add("密码");
        col.add("姓名");
        col.add("联系电话");
        row = getRow(list);
        JTable jt = new JTable(row, col);
        JScrollPane jsp = new JScrollPane(jt);
        jpanel.add(jsp);
        jsp.setBounds(10, 20, 900, 200);


        JComboBox d = new JComboBox(c);
        jpanel.add(d);
        d.setBounds(10, 250, 80, 30);

        jpanel.add(b);//将按钮加在容器上
        b.setBounds(150, 450, 200, 30);//设置按钮在容器中的位置
        jpanel.add(k);
        k.setBounds(10, 280, 100, 20);
        k.setText("账号");
        jpanel.add(t);
        t.setBounds(10, 300, 100, 20);
        jpanel.add(k1);
        k1.setBounds(10, 330, 100, 20);
        k1.setText("密码");
        jpanel.add(t1);
        t1.setBounds(10, 350, 100, 20);
        jpanel.add(k2);
        k2.setBounds(10, 380, 100, 20);
        k2.setText("姓名");
        jpanel.add(t2);
        t2.setBounds(10, 400, 100, 20);
        jpanel.add(k3);
        k3.setBounds(10, 420, 100, 20);
        k3.setText("联系电话");
        jpanel.add(t3);
        t3.setBounds(10, 450, 100, 20);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = d.getSelectedItem().toString();

                if (s.equals("添加") && t.getText().length() != 0) {
                    UserAllMassage userAllMassage = new UserAllMassage(t.getText(), t1.getText(), t2.getText(), t3.getText());

                    if (jTabbedPane.getSelectedIndex() == 0) {
                        Vector col = new Vector();
                        col.add("账号");
                        col.add("密码");
                        col.add("姓名");
                        col.add("联系电话");
                        MybatisUtil.getSqlSession().getMapper(UserMassage.class).insertDataToUser(userAllMassage);
                        List<UserAllMassage> list = MybatisUtil.getSqlSession().getMapper(UserMassage.class).selectAllUserMassage();
                        jt.setModel(new DefaultTableModel(getRow(list), col));
                        jt.setEnabled(true);
                    }
                    if (jTabbedPane.getSelectedIndex() == 1) {
                        Vector col = new Vector();
                        col.add("账号");
                        col.add("密码");
                        col.add("姓名");
                        col.add("联系电话");
                        MybatisUtil.getSqlSession().getMapper(UserMassage.class).insertDataToLibrary(userAllMassage);
                        jt.removeAll();
                        List<UserAllMassage> list = MybatisUtil.getSqlSession().getMapper(UserMassage.class).selectAllLibraryMsg();
                        jt.setModel(new DefaultTableModel(getRow(list), col));
                        jt.setEnabled(true);
                    }
                }
                if (s.equals("删除") && t.getText().length() != 0) {
                    if (jTabbedPane.getSelectedIndex() == 0) {
                        UserAllMassage userAllMassage = MybatisUtil.getSqlSession().getMapper(UserMassage.class).selectUser(t.getText());
                        if (userAllMassage != null) {
                            MybatisUtil.getSqlSession().getMapper(UserMassage.class).deleteUser(t.getText());
                            Vector col = new Vector();
                            col.add("账号");
                            col.add("密码");
                            col.add("姓名");
                            col.add("联系电话");
                            jt.removeAll();
                            List<UserAllMassage> list = MybatisUtil.getSqlSession().getMapper(UserMassage.class).selectAllUserMassage();
                            jt.setModel(new DefaultTableModel(getRow(list), col));
                            jt.setEnabled(true);
                        }
                    }
                    if (jTabbedPane.getSelectedIndex() == 1) {
                        UserAllMassage userAllMassage = MybatisUtil.getSqlSession().getMapper(UserMassage.class).selectLibrary(t.getText());
                        if (userAllMassage != null) {
                            MybatisUtil.getSqlSession().getMapper(UserMassage.class).deleteLibrary(t.getText());
                            Vector col = new Vector();
                            col.add("账号");
                            col.add("密码");
                            col.add("姓名");
                            col.add("联系电话");
                            jt.removeAll();
                            List<UserAllMassage> list = MybatisUtil.getSqlSession().getMapper(UserMassage.class).selectAllLibraryMsg();
                            jt.setModel(new DefaultTableModel(getRow(list), col));
                            jt.setEnabled(true);
                        }
                    }
                }
                t.setText(null);
                t1.setText(null);
                t2.setText(null);
                t3.setText(null);
            }
        });
        jt.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = jt.getSelectedRow();
                Object valueAt1 = jt.getValueAt(row, 0);
                Object valueAt2= jt.getValueAt(row, 1);
                Object valueAt3= jt.getValueAt(row, 2);
                Object valueAt4= jt.getValueAt(row, 3);
                t.setText(valueAt1.toString());
                t1.setText(valueAt2.toString());
                t2.setText(valueAt3.toString());
                t3.setText(valueAt4.toString());
            }

        });

    }

    public void getUnReturn(List<UserAllMassage> list, JPanel jpanel, String[] c) {
        jpanel.removeAll();
        JTextField t = new JTextField(10);
        JTextField t1 = new JTextField(10);
        JTextField t2 = new JTextField(10);
        JTextField t3 = new JTextField(10);
        JLabel k = new JLabel();
        JLabel k1 = new JLabel();
        JLabel k2 = new JLabel();
        JLabel k3 = new JLabel();
        JLabel k7 = new JLabel();
        JButton b = new JButton("执行");
        Vector row, col;
        col = new Vector();
        col.add("账号");
        col.add("密码");
        col.add("姓名");
        col.add("联系电话");
        row = getRow(list);
        JTable jt = new JTable(row, col);
        JScrollPane jsp = new JScrollPane(jt);
        jpanel.add(jsp);
        jsp.setBounds(10, 20, 900, 200);


        JComboBox d = new JComboBox(c);
        jpanel.add(d);
        d.setBounds(10, 250, 80, 30);

        jpanel.add(b);//将按钮加在容器上
        b.setBounds(150, 450, 200, 30);//设置按钮在容器中的位置
        jpanel.add(k);
        k.setBounds(10, 280, 100, 20);
        k.setText("账号");
        jpanel.add(t);
        t.setBounds(10, 300, 100, 20);
        jpanel.add(k1);
        k1.setBounds(10, 330, 100, 20);
        k1.setText("账号标识");
        jpanel.add(t1);
        t1.setBounds(10, 350, 100, 20);
        jpanel.add(k2);
        k2.setBounds(10, 380, 100, 20);
        k2.setText("姓名");
        jpanel.add(t2);
        t2.setBounds(10, 400, 100, 20);
        jpanel.add(k3);
        k3.setBounds(10, 420, 100, 20);
        k3.setText("联系电话");
        jpanel.add(t3);
        t3.setBounds(10, 450, 100, 20);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = d.getSelectedItem().toString();

                if (s.equals("冻结") && t.getText().length() != 0) {
                    for (UserAllMassage userAllMassage : list) {
                        if(userAllMassage.getUserId().equals(t.getText())){
                            MybatisUtil.getSqlSession().getMapper(UserMassage.class).updateUserFFlag(t.getText());
                        }
                    }

                }
                if (s.equals("解冻") && t.getText().length() != 0) {
                    for (UserAllMassage userAllMassage : list) {
                        if(userAllMassage.getUserId().equals(t.getText())){
                            MybatisUtil.getSqlSession().getMapper(UserMassage.class).updateUserTFlag(t.getText());
                        }
                    }
                }
                t.setText(null);
                t1.setText(null);
                t2.setText(null);
                t3.setText(null);
            }
        });
        jt.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = jt.getSelectedRow();
                Object valueAt1 = jt.getValueAt(row, 0);
                Object valueAt2= jt.getValueAt(row, 1);
                Object valueAt3= jt.getValueAt(row, 2);
                Object valueAt4= jt.getValueAt(row, 3);
                t.setText(valueAt1.toString());
                t1.setText(valueAt2.toString());
                t2.setText(valueAt3.toString());
                t3.setText(valueAt4.toString());
            }

        });


    }



    public Vector getRow(List<UserAllMassage> list) {
        Vector row = new Vector();
        for (UserAllMassage userAllMassage : list) {
            Vector rowData = new Vector();
            rowData.add(userAllMassage.getUserId());
            rowData.add(userAllMassage.getUserPwd());
            rowData.add(userAllMassage.getUserName());
            rowData.add(userAllMassage.getUserTel());
            row.add(rowData);
        }
        return row;
    }
}








