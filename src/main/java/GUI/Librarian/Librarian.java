package GUI.Librarian;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class Librarian extends JFrame{
    JTextField t = null;
    JTextField t1 = null;
    JTextField t2 = null;
    JTextField t3 = null;
    JTextField t4 = null;
    JTextField t5 = null;
    JTextField t6 = null;
    JTextField t7 = null;
    JTextField t8 = null;
    JTextField t9 = null;
    JLabel k=null;
    JLabel k1=null;
    JLabel k2=null;
    JLabel k3=null;
    JLabel k4=null;
    JLabel k5=null;
    JLabel k6=null;
    JLabel k7=null;
    JLabel k8=null;
    JLabel k9=null;
    JLabel k10=null;
    JLabel k11=null;
    JTable jt=null;
    JScrollPane jsp=null;
    JTable jt1=null;
    JScrollPane jsp1=null;
    JTable jt2=null;
    JTable jt3=null;
    JScrollPane jsp2=null;
    JScrollPane jsp3=null;
    JPanel jpanel = new JPanel();
    JPanel jpanel2 = new JPanel();
    JPanel jpanel3 = new JPanel();

    public Librarian() {
        JTabbedPane jTabbedPane = new JTabbedPane();

        jTabbedPane.add("主页", jpanel);
        jTabbedPane.add("查询图书", jpanel2);
        jTabbedPane.add("查看借阅情况", jpanel3);
        this.add(jTabbedPane);
        this.setTitle("图书管理员");
        jpanel.setLayout(null);
        jpanel2.setLayout(null);
        jpanel3.setLayout(null);
        this.setSize(1000, 800);
        this.setLocationRelativeTo(null);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        t=new JTextField(10);
        t1=new JTextField(30);
        t2=new JTextField(30);
        t3=new JTextField(30);
        t4=new JTextField(30);
        t5=new JTextField(30);
        t6=new JTextField(30);
        t7=new JTextField(30);
        t8=new JTextField(30);
        t9=new JTextField(30);
        k=new JLabel();
        k1=new JLabel();
        k2=new JLabel();
        k3=new JLabel();
        k4=new JLabel();
        k5=new JLabel();
        k6=new JLabel();
        k7=new JLabel();
        k8=new JLabel();
        k9=new JLabel();
        k10=new JLabel();
        k11=new JLabel();
    }

    public void gui() {
        final String URL = "jdbc:mysql://localhost/mydb?serverTimezone=UTC&useSSL=false&characterEncoding=utf-8&user=root&password=root";
        try {
            PreparedStatement ps=null;

            ResultSet rs=null;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(URL);
            System.out.println("成功加载MYSQL驱动");


            //数据库查询语句
            ps=con.prepareStatement("select * from 图书");


            /*在询数据表时，需要用到ResultSet接口，它类似于一个数据表，通过该接口的实例可以获得检索结果集，以及对应数据表的接口信息。*/
            rs=ps.executeQuery();
            Vector row,col;
            col=new Vector();
            col.add("图书编号");
            col.add("图书名称");
            col.add("图书作者");
            col.add("图书库存量");
            col.add("图书类型");
            col.add("被借阅次数");
            col.add("允许最长借阅时间/天");
            row =new Vector();

            while(rs.next()){
                //rowData可以存放多行
                Vector hang=new Vector();
                hang.add(rs.getInt(1));
                hang.add(rs.getString(2));
                hang.add(rs.getString(3));
                hang.add(rs.getString(4));
                hang.add(rs.getString(5));
                hang.add(rs.getString(6));
                hang.add(rs.getString(7));
                //加入到rowData
                row.add(hang);
            }
            jt = new JTable(row,col);
            jsp = new JScrollPane(jt);
            jpanel.add(jsp);
            jsp.setBounds(10,20,900,200);
            jpanel.add(k7);
            k7.setBounds(10,230,200,20);
            k7.setText("请选择你想进行的操作");
            String[] c = new String[]{"添加图书","修改图书"};
            JComboBox d = new JComboBox(c);
            jpanel.add(d);
            d.setBounds(10, 250, 80, 30);
            JButton b = new JButton("执行");
            jpanel.add(b);//将按钮加在容器上
            b.setBounds(150, 600, 200, 30);//设置按钮在容器中的位置

            jpanel.add(k);
            k.setBounds(10,280,100,20);
            k.setText("图书编号");
            jpanel.add(t);
            t.setBounds(10,300,100,20);
            jpanel.add(k1);
            k1.setBounds(10,330,100,20);
            k1.setText("图书名称");
            jpanel.add(t1);
            t1.setBounds(10,350,100,20);
            jpanel.add(k2);
            k2.setBounds(10,380,100,20);
            k2.setText("图书名作者");
            jpanel.add(t2);
            t2.setBounds(10,400,100,20);
            jpanel.add(k3);
            k3.setBounds(10,420,100,20);
            k3.setText("图书库存量");
            jpanel.add(t3);
            t3.setBounds(10,450,100,20);
            jpanel.add(k4);
            k4.setBounds(10,480,100,20);
            k4.setText("图书类型");
            jpanel.add(t4);
            t4.setBounds(10,500,100,20);
            jpanel.add(k5);
            k5.setBounds(10,530,100,20);
            k5.setText("被借阅次数");
            jpanel.add(t5);
            t5.setBounds(10,550,100,20);
            jpanel.add(k6);
            k6.setBounds(10,580,100,20);
            k6.setText("最长借阅时间");
            jpanel.add(t6);
            t6.setBounds(10,600,100,20);
            jpanel.add(k8);
            k8.setText("删除图书");
            k8.setBounds(500,250,100,100);
            Font font = new Font("宋体", Font.PLAIN, 25);

            k8.setFont(font);

            k8.setForeground(new Color(1,200,255));
            jpanel.add(t7);
            t7.setBounds(500,350,100,20);
            jpanel.add(k9);
            k9.setBounds(500,310,100,50);
            k9.setText("图书名称");
            JButton h = new JButton("删除一条记录");
            jpanel.add(h);//将按钮加在容器上
            h.setBounds(500, 400, 200, 30);//设置按钮在容器中的位置
            h.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e5) {
                    String o;
                    o=t7.getText();
                    try {
                        PreparedStatement ps3=null;
                        ps3=con.prepareStatement("DELETE FROM 图书 WHERE 图书名称=?");
                        ps3.setString(1,o);
                        ps3.executeUpdate();
                        row.clear();
                        ResultSet rs3=null;
                        PreparedStatement ps12=null;
                        ps12=con.prepareStatement("select * from 图书");
                        rs3=ps12.executeQuery();
                        while(rs3.next()){
                            //rowData可以存放多行
                            Vector hang=new Vector();
                            hang.add(rs3.getInt(1));
                            hang.add(rs3.getString(2));
                            hang.add(rs3.getString(3));
                            hang.add(rs3.getString(4));
                            hang.add(rs3.getString(5));
                            hang.add(rs3.getString(6));
                            hang.add(rs3.getString(7));
                            //加入到rowData
                            row.add(hang);
                        }
                    }catch (SQLException e51) {
                        // TODO Auto-generated catch block
                        e51.printStackTrace();
                    }
                }
            });
            b.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    String s=(String) d.getSelectedItem();
                    System.out.print(""+s);
                    String m[];
                    m=new String[8];
                    if(s.equals("添加图书")) {
                        m[1]=t.getText();
                        m[2]=t1.getText();
                        m[3]=t2.getText();
                        m[4]=t3.getText();
                        m[5]=t4.getText();
                        m[6]=t5.getText();
                        m[7]=t6.getText();
                        PreparedStatement ps1=null;
                        try {
                            ps1=con.prepareStatement("insert into 图书 values(?,?,?,?,?,?,?)");
                            ps1.setString(1, m[1]);
                            ps1.setString(2,m[2]);
                            ps1.setString(3,m[3]);
                            ps1.setString(4,m[4]);
                            ps1.setString(5,m[5]);
                            ps1.setString(6,m[6]);
                            ps1.setString(7,m[7]);
                            ps1.executeUpdate();


                            ResultSet rs1=null;
                            PreparedStatement ps10=null;
                            ps10=con.prepareStatement("select * from 图书");
                            rs1=ps10.executeQuery();
                            while(rs1.next()){
                                //rowData可以存放多行
                                Vector hang=new Vector();
                                hang.add(rs1.getInt(1));
                                hang.add(rs1.getString(2));
                                hang.add(rs1.getString(3));
                                hang.add(rs1.getString(4));
                                hang.add(rs1.getString(5));
                                hang.add(rs1.getString(6));
                                hang.add(rs1.getString(7));
                                //加入到rowData
                                row.add(hang);
                            }


                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                    else if(s.equals("修改图书")) {
                        m[1]=t.getText();
                        m[2]=t1.getText();
                        m[3]=t2.getText();
                        m[4]=t3.getText();
                        m[5]=t4.getText();
                        m[6]=t5.getText();
                        m[7]=t6.getText();
                        PreparedStatement ps2=null;
                        try {
                            ps2=con.prepareStatement("UPDATE 图书 SET 图书名称=? WHERE 图书编号=?");
                            ps2.setString(1,m[2]);
                            ps2.setString(2, m[1]);
                            ps2.executeUpdate();
                            ps2=con.prepareStatement("UPDATE 图书 SET 图书作者=? WHERE 图书编号=?");

                            ps2.setString(1,m[3]);
                            ps2.setString(2, m[1]);
                            ps2.executeUpdate();
                            ps2=con.prepareStatement("UPDATE 图书 SET 图书库存量=? WHERE 图书编号=?");

                            ps2.setString(1,m[4]);
                            ps2.setString(2, m[1]);
                            ps2.executeUpdate();
                            ps2=con.prepareStatement("UPDATE 图书 SET 图书类型=? WHERE 图书编号=?");

                            ps2.setString(1,m[5]);
                            ps2.setString(2, m[1]);
                            ps2.executeUpdate();
                            ps2=con.prepareStatement("UPDATE 图书 SET 借阅次数=? WHERE 图书编号=?");

                            ps2.setString(1,m[6]);
                            ps2.setString(2, m[1]);
                            ps2.executeUpdate();
                            ps2=con.prepareStatement("UPDATE 图书 SET 允许最长借阅时间=? WHERE 图书编号=?");
                            ps2.setString(1,m[7]);
                            ps2.setString(2, m[1]);
                            ps2.executeUpdate();
                            row.clear();
                            ResultSet rs2=null;
                            PreparedStatement ps11=null;
                            ps11=con.prepareStatement("select * from 图书");
                            rs2=ps11.executeQuery();
                            while(rs2.next()){
                                //rowData可以存放多行
                                Vector hang=new Vector();
                                hang.add(rs2.getInt(1));
                                hang.add(rs2.getString(2));
                                hang.add(rs2.getString(3));
                                hang.add(rs2.getString(4));
                                hang.add(rs2.getString(5));
                                hang.add(rs2.getString(6));
                                hang.add(rs2.getString(7));
                                //加入到rowData
                                row.add(hang);
                            }
                        }catch (SQLException e2) {
                            // TODO Auto-generated catch block
                            e2.printStackTrace();
                        }
                    }
                }
            });

            Vector row1,col1;
            col1=new Vector();
            col1.add("图书编号");
            col1.add("图书名称");
            col1.add("图书作者");
            col1.add("图书库存量");
            col1.add("图书类型");
            col1.add("被借阅次数");
            col1.add("允许最长借阅时间/天");
            row1 =new Vector();
            ResultSet rs55=null;
            PreparedStatement ps115=null;
            ps115=con.prepareStatement("select * from 图书");
            rs55=ps115.executeQuery();
            while(rs55.next()){
                //rowData可以存放多行
                Vector hang=new Vector();
                hang.add(rs55.getInt(1));
                hang.add(rs55.getString(2));
                hang.add(rs55.getString(3));
                hang.add(rs55.getString(4));
                hang.add(rs55.getString(5));
                hang.add(rs55.getString(6));
                hang.add(rs55.getString(7));
                //加入到rowData
                row1.add(hang);
            }
            jt1 = new JTable(row1,col1);
            jsp1 = new JScrollPane(jt1);
            jpanel2.add(jsp1);
            jsp1.setBounds(10,20,900,200);
            String[] f = new String[]{"按图书名称查找","按图书类型查找","按图书作者查找"};
            JComboBox d1 = new JComboBox(f);
            jpanel2.add(d1);
            d1.setBounds(10, 250, 150, 30);
            jpanel2.add(k10);
            k10.setBounds(10,300,100,20);
            k10.setText("请输入查找信息");
            jpanel2.add(t8);
            t8.setBounds(10,320,200,20);
            JButton b2 = new JButton("查找");
            jpanel2.add(b2);//将按钮加在容器上
            b2.setBounds(10, 350, 200, 30);//设置按钮在容器中的位置
            b2.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    try {
                        String s1;
                        s1=t8.getText();
                        String s2;
                        s2=(String) d1.getSelectedItem();

                        if(s2.equals("按图书名称查找")) {
                            PreparedStatement ps15=null;
                            ResultSet rs13=null;
                            ps15=con.prepareStatement("select * from 图书 WHERE 图书名称=?");
                            ps15.setString(1,s1);
                            rs13=ps15.executeQuery();
                            row1.clear();
                            while(rs13.next()){
                                //rowData可以存放多行
                                Vector hang1=new Vector();
                                hang1.add(rs13.getInt(1));
                                hang1.add(rs13.getString(2));
                                hang1.add(rs13.getString(3));
                                hang1.add(rs13.getString(4));
                                hang1.add(rs13.getString(5));
                                hang1.add(rs13.getString(6));
                                hang1.add(rs13.getString(7));
                                //加入到rowData
                                row1.add(hang1);
                            }
                        }
                        else if(s2.equals("按图书类型查找")) {
                            PreparedStatement ps15=null;
                            ResultSet rs13=null;
                            ps15=con.prepareStatement("select * from 图书 where 图书类型=?");
                            ps15.setString(1,s1);
                            rs13=ps15.executeQuery();
                            row1.clear();
                            while(rs13.next()){
                                //rowData可以存放多行
                                Vector hang2=new Vector();
                                hang2.add(rs13.getInt(1));
                                hang2.add(rs13.getString(2));
                                hang2.add(rs13.getString(3));
                                hang2.add(rs13.getString(4));
                                hang2.add(rs13.getString(5));
                                hang2.add(rs13.getString(6));
                                hang2.add(rs13.getString(7));
                                //加入到rowData
                                row1.add(hang2);
                            }
                        }
                        else if(s2.equals("按图书作者查找")) {
                            PreparedStatement ps15=null;
                            ResultSet rs13=null;
                            ps15=con.prepareStatement("select * from 图书 where 图书作者=?");
                            ps15.setString(1,s1);
                            rs13=ps15.executeQuery();
                            row1.clear();
                            while(rs13.next()){
                                //rowData可以存放多行
                                Vector hang3=new Vector();
                                hang3.add(rs13.getInt(1));
                                hang3.add(rs13.getString(2));
                                hang3.add(rs13.getString(3));
                                hang3.add(rs13.getString(4));
                                hang3.add(rs13.getString(5));
                                hang3.add(rs13.getString(6));
                                hang3.add(rs13.getString(7));
                                //加入到rowData
                                row1.add(hang3);
                            }
                        }
                    }catch (SQLException e3) {
                        // TODO Auto-generated catch block
                        e3.printStackTrace();
                    }
                }});


            Vector row2,col2;
            col2=new Vector();
            col2.add("借阅账号");
            col2.add("图书编号");
            col2.add("借书时间");
            col2.add("还书时间");
            row2 =new Vector();
            ResultSet rs155=null;
            PreparedStatement ps168=null;
            ps168=con.prepareStatement("select * from 借阅情况");
            rs155=ps168.executeQuery();
            while(rs155.next()){
                //rowData可以存放多行
                Vector hang6=new Vector();
                hang6.add(rs155.getString(1));
                hang6.add(rs155.getInt(2));
                hang6.add(rs155.getString(3));
                hang6.add(rs155.getString(4));
                //加入到rowData
                row2.add(hang6);
            }
            jt2 = new JTable(row2,col2);
            jsp2 = new JScrollPane(jt2);
            jpanel3.add(jsp2);
            jsp2.setBounds(10,20,900,200);
            String[] f1 = new String[]{"按账号查找"};
            JComboBox d2 = new JComboBox(f1);
            jpanel3.add(d2);
            d2.setBounds(10, 250, 150, 30);
            jpanel3.add(k11);
            k11.setBounds(10,300,200,20);
            k11.setText("请输入要查找的账号");
            jpanel3.add(t9);
            t9.setBounds(10,320,200,20);
            JButton b3 = new JButton("查找");
            jpanel3.add(b3);//将按钮加在容器上
            b3.setBounds(10, 350, 200, 30);//设置按钮在容器中的位置
            b3.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    try {
                        String s3;
                        s3=t9.getText();
                        PreparedStatement ps165=null;
                        ResultSet rs136=null;
                        ps165=con.prepareStatement("select * from 借阅情况 where 借阅账号=?");
                        ps165.setString(1,s3);
                        rs136=ps165.executeQuery();
                        row2.clear();
                        while(rs136.next()){
                            //rowData可以存放多行
                            Vector hang8=new Vector();
                            hang8.add(rs136.getString(1));
                            hang8.add(rs136.getInt(2));
                            hang8.add(rs136.getString(3));
                            hang8.add(rs136.getString(4));
                            //加入到rowData
                            row2.add(hang8);
                        }
                    }catch (SQLException e6) {
                        e6.printStackTrace();
                    }
                }});
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}