# Library-Management-System-Java
👉👉A library management system is used as a test of the basic operations of the database👨‍💻
---
title: 图书管理系统 — 
date: 2021-06-21 11:06:41

---

## 登陆组册模块

### 登陆页面

#### 设计思路

1. 提供账号和密码的输入框，以及提供登陆身份的选择，方便不同身份的用户使用该系统。

#### 登陆验证

1. 思路：用户输入账号和密码，以及选择了自己的身份时，程序会获取输入框的账号跟密码，再根据选择的身份前往数据库中对应的表的数据进行匹配。

2. 具体实现（部分`java`代码）

   完整代码在

   `src/GUI/Login/LoginNav.java`

   `src/GUI/Login/LoginPage.java`

```java
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
```

根据验证结果做进一步判断。

```java
if (check) {
    JOptionPane.showMessageDialog(null, "用户身份："+identity+", 登陆成功" , "恭喜",JOptionPane.WARNING_MESSAGE);
    if (identity.equals("图书管理员")){
       
    }else if(identity.equals("用户")){
        
    }else if (identity.equals("用户信息管理员")){

    }
}else {
    if (account.equals("")||pwd.equals("")) {
        JOptionPane.showMessageDialog(null, "请输入账号或密码", "抱歉",JOptionPane.WARNING_MESSAGE);
        return;
    }
    JOptionPane.showMessageDialog(null, "账号或密码错误", "抱歉",JOptionPane.WARNING_MESSAGE);
}
```

根据用户身份跳转到不同模块的具体实现，在上面代码的 3 ～ 7行，如下

```java
if (identity.equals("图书管理员")){

}else if(identity.equals("用户")){

}else if (identity.equals("用户信息管理员")){

}
```

#### 结果展示

1. 普通用户登陆

   - <img src="https://i.loli.net/2021/06/23/EgOCUlTpDuQMcek.png" alt="image-20210622153222960.png" style="zoom:50%;" />

2. 用户信息管理员登陆

   1. <img src="https://i.loli.net/2021/06/23/5Xa6iTvUucwNjJD.png" alt="image-20210622153306503.png" style="zoom:50%;" />

3. 图书管理员登陆

   1. ![image-20210622153354872.png](https://i.loli.net/2021/06/23/F6q2QAeM8lxpkhU.png)用户友好提示以及逻辑合理性

   2. 不能跨身份使用，如果账号密码不是所选身份的账号，则不能登陆。

      <img src="https://i.loli.net/2021/06/23/GpywK6SQ5AR8M9T.png" alt="image-20210622153732070.png" style="zoom:50%;" />

   3. 密码错误不能登陆

   4. 输入框友好提示

      <img src="https://i.loli.net/2021/06/23/GpywK6SQ5AR8M9T.png" alt="image-20210622153732070.png" style="zoom:50%;" />

      <img src="https://i.loli.net/2021/06/23/VYg2Fa3s7pcDIPd.png" alt="image-20210622153815193.png" style="zoom:50%;" />

### 注册页面

#### 设计思路

出于安全性和方便管理的考虑，数据库设计将注册模块的权限限制为普通用户，因此，本注册模块仅用于注册借阅图书的用户。注册需要用户指定用户需要的账号，长度限定在3位，注册的账号不能含有空格，填写初始密码时，需要二次验证，防止误输入密码导致无法登陆。注册时需要选择一个密保问题并设置个人答案，密保问题在忘记密码时使用以供用户重新设置密码。

<img src="https://i.loli.net/2021/06/23/CfDQubjc3P184MY.png" alt="image-20210622160808578.png" style="zoom:50%;" />

#### 代码展示

```java
if (verification(account, pwd, confirm_pwd,answer)) {
    //验证成功
    //添加账号信息到用户表和密保表
    if (create_account(account,pwd,question,answer)) {
        JOptionPane.showMessageDialog(null, "注册成功", "恭喜",JOptionPane.PLAIN_MESSAGE);
    }
}
```

```java
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
```

#### 结果展示

1. <img src="https://i.loli.net/2021/06/23/YSrj5Dql6weG8u3.png" alt="image-20210622161056621.png" style="zoom:50%;" />
2. <img src="https://i.loli.net/2021/06/23/RHL3vgumO9sotG4.png" alt="image-20210622161117869.png" style="zoom:50%;" />
3. <img src="https://i.loli.net/2021/06/23/HBxePw2RSgir6AN.png" alt="image-20210622161215201.png" style="zoom:50%;" />
4. <img src="https://i.loli.net/2021/06/23/2VfNepXqUjLkmAW.png" alt="image-20210622161242881.png" style="zoom:50%;" />
5. <img src="https://i.loli.net/2021/06/23/A1zYUZyKPJOmFTu.png" alt="image-20210622161303767.png" style="zoom:50%;" />
6. <img src="https://i.loli.net/2021/06/23/g651uMrkoiGDLIQ.png" alt="image-20210622161329703.png" style="zoom:50%;" />

### 忘记密码

#### 设计思路

忘记密码时，只需填写需要找回密码的账号，以及回答注册时设置的密保问题，即可修改新密码。通过查询数据库中密保表的数据，找出对应的账号和对应的密保问题答案，将输入的问题答案与之对照，若相同即可设置新密码。

#### 代码展示

```java
for (Secret secret : list) {
    if (current.equals(secret.getAccount()) && secret.getStatus().equals(table)) {
        //验证密保
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
```

#### 结果展示

<img src="https://i.loli.net/2021/06/23/zqNRBQfkoZ8H1yP.png" alt="image-20210623104339516.png" style="zoom:50%;" />

<img src="https://i.loli.net/2021/06/23/ONU9q2ASn4skF1t.png" alt="image-20210623104359730.png" style="zoom:50%;" />

