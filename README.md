# Library-Management-System-Java
ððA library management system is used as a test of the basic operations of the databaseð¨âð»
---
title: å¾ä¹¦ç®¡çç³»ç» â 
date: 2021-06-21 11:06:41

---

## ç»éç»åæ¨¡å

### ç»éé¡µé¢

#### è®¾è®¡æè·¯

1. æä¾è´¦å·åå¯ç çè¾å¥æ¡ï¼ä»¥åæä¾ç»éèº«ä»½çéæ©ï¼æ¹ä¾¿ä¸åèº«ä»½çç¨æ·ä½¿ç¨è¯¥ç³»ç»ã

#### ç»ééªè¯

1. æè·¯ï¼ç¨æ·è¾å¥è´¦å·åå¯ç ï¼ä»¥åéæ©äºèªå·±çèº«ä»½æ¶ï¼ç¨åºä¼è·åè¾å¥æ¡çè´¦å·è·å¯ç ï¼åæ ¹æ®éæ©çèº«ä»½åå¾æ°æ®åºä¸­å¯¹åºçè¡¨çæ°æ®è¿è¡å¹éã

2. å·ä½å®ç°ï¼é¨å`java`ä»£ç ï¼

   å®æ´ä»£ç å¨

   `src/GUI/Login/LoginNav.java`

   `src/GUI/Login/LoginPage.java`

```java
//éªè¯
boolean check = false;
for (Account_pwd account_pwd : account_pwdList) {
    if(account.equals(account_pwd.getAccount())) {
        if (pwd.equals(account_pwd.getPwd())) {
            System.out.println("ç»éæå");
            check = true;
            break;
        }
    }
}
```

æ ¹æ®éªè¯ç»æåè¿ä¸æ­¥å¤æ­ã

```java
if (check) {
    JOptionPane.showMessageDialog(null, "ç¨æ·èº«ä»½ï¼"+identity+", ç»éæå" , "æ­å",JOptionPane.WARNING_MESSAGE);
    if (identity.equals("å¾ä¹¦ç®¡çå")){
       
    }else if(identity.equals("ç¨æ·")){
        
    }else if (identity.equals("ç¨æ·ä¿¡æ¯ç®¡çå")){

    }
}else {
    if (account.equals("")||pwd.equals("")) {
        JOptionPane.showMessageDialog(null, "è¯·è¾å¥è´¦å·æå¯ç ", "æ±æ­",JOptionPane.WARNING_MESSAGE);
        return;
    }
    JOptionPane.showMessageDialog(null, "è´¦å·æå¯ç éè¯¯", "æ±æ­",JOptionPane.WARNING_MESSAGE);
}
```

æ ¹æ®ç¨æ·èº«ä»½è·³è½¬å°ä¸åæ¨¡åçå·ä½å®ç°ï¼å¨ä¸é¢ä»£ç ç 3 ï½ 7è¡ï¼å¦ä¸

```java
if (identity.equals("å¾ä¹¦ç®¡çå")){

}else if(identity.equals("ç¨æ·")){

}else if (identity.equals("ç¨æ·ä¿¡æ¯ç®¡çå")){

}
```

#### ç»æå±ç¤º

1. æ®éç¨æ·ç»é

   - <img src="https://i.loli.net/2021/06/23/EgOCUlTpDuQMcek.png" alt="image-20210622153222960.png" style="zoom:50%;" />

2. ç¨æ·ä¿¡æ¯ç®¡çåç»é

   1. <img src="https://i.loli.net/2021/06/23/5Xa6iTvUucwNjJD.png" alt="image-20210622153306503.png" style="zoom:50%;" />

3. å¾ä¹¦ç®¡çåç»é

   1. ![image-20210622153354872.png](https://i.loli.net/2021/06/23/F6q2QAeM8lxpkhU.png)ç¨æ·åå¥½æç¤ºä»¥åé»è¾åçæ§

   2. ä¸è½è·¨èº«ä»½ä½¿ç¨ï¼å¦æè´¦å·å¯ç ä¸æ¯æéèº«ä»½çè´¦å·ï¼åä¸è½ç»éã

      <img src="https://i.loli.net/2021/06/23/GpywK6SQ5AR8M9T.png" alt="image-20210622153732070.png" style="zoom:50%;" />

   3. å¯ç éè¯¯ä¸è½ç»é

   4. è¾å¥æ¡åå¥½æç¤º

      <img src="https://i.loli.net/2021/06/23/GpywK6SQ5AR8M9T.png" alt="image-20210622153732070.png" style="zoom:50%;" />

      <img src="https://i.loli.net/2021/06/23/VYg2Fa3s7pcDIPd.png" alt="image-20210622153815193.png" style="zoom:50%;" />

### æ³¨åé¡µé¢

#### è®¾è®¡æè·¯

åºäºå®å¨æ§åæ¹ä¾¿ç®¡ççèèï¼æ°æ®åºè®¾è®¡å°æ³¨åæ¨¡åçæééå¶ä¸ºæ®éç¨æ·ï¼å æ­¤ï¼æ¬æ³¨åæ¨¡åä»ç¨äºæ³¨ååéå¾ä¹¦çç¨æ·ãæ³¨åéè¦ç¨æ·æå®ç¨æ·éè¦çè´¦å·ï¼é¿åº¦éå®å¨3ä½ï¼æ³¨åçè´¦å·ä¸è½å«æç©ºæ ¼ï¼å¡«ååå§å¯ç æ¶ï¼éè¦äºæ¬¡éªè¯ï¼é²æ­¢è¯¯è¾å¥å¯ç å¯¼è´æ æ³ç»éãæ³¨åæ¶éè¦éæ©ä¸ä¸ªå¯ä¿é®é¢å¹¶è®¾ç½®ä¸ªäººç­æ¡ï¼å¯ä¿é®é¢å¨å¿è®°å¯ç æ¶ä½¿ç¨ä»¥ä¾ç¨æ·éæ°è®¾ç½®å¯ç ã

<img src="https://i.loli.net/2021/06/23/CfDQubjc3P184MY.png" alt="image-20210622160808578.png" style="zoom:50%;" />

#### ä»£ç å±ç¤º

```java
if (verification(account, pwd, confirm_pwd,answer)) {
    //éªè¯æå
    //æ·»å è´¦å·ä¿¡æ¯å°ç¨æ·è¡¨åå¯ä¿è¡¨
    if (create_account(account,pwd,question,answer)) {
        JOptionPane.showMessageDialog(null, "æ³¨åæå", "æ­å",JOptionPane.PLAIN_MESSAGE);
    }
}
```

```java
private boolean verification(String account, String pwd, String confirm_pwd,String answer) {
    //æ³¨åéªè¯

    //        éªè¯ç¨æ·è¡¨ä¸­æ¯å¦å·²å­å¨è¯¥è´¦å·ï¼å­å¨ä¸è½æ³¨å
    if (isExisted(account)) {
        JOptionPane.showMessageDialog(null, "è´¦å·å·²å­å¨", "æç¤º",JOptionPane.WARNING_MESSAGE);
        return false;
    }
    if (account.contains(" ")) {
        //è´¦å·ä¸è½å«æç©ºæ ¼
        JOptionPane.showMessageDialog(null, "è´¦å·ä¸åè®¸ç©ºæ ¼", "æç¤º",JOptionPane.WARNING_MESSAGE);
        return false;
    } else if (account.length() < 3) {
        JOptionPane.showMessageDialog(null, "è´¦å·é¿åº¦æå°ä¸º3ä½", "æç¤º",JOptionPane.WARNING_MESSAGE);
        return false;
    } else if (!pwd.equals(confirm_pwd)) {
        JOptionPane.showMessageDialog(null, "ä¸¤æ¬¡è¾å¥çå¯ç ä¸ä¸è´", "æç¤º",JOptionPane.WARNING_MESSAGE);
        return false;
    }else if(answer.equals("")) {
        JOptionPane.showMessageDialog(null, "å¯ä¿ä¸è½ä¸ºç©º", "æç¤º",JOptionPane.WARNING_MESSAGE);
        return false;
    }
    return true;
}
```

#### ç»æå±ç¤º

1. <img src="https://i.loli.net/2021/06/23/YSrj5Dql6weG8u3.png" alt="image-20210622161056621.png" style="zoom:50%;" />
2. <img src="https://i.loli.net/2021/06/23/RHL3vgumO9sotG4.png" alt="image-20210622161117869.png" style="zoom:50%;" />
3. <img src="https://i.loli.net/2021/06/23/HBxePw2RSgir6AN.png" alt="image-20210622161215201.png" style="zoom:50%;" />
4. <img src="https://i.loli.net/2021/06/23/2VfNepXqUjLkmAW.png" alt="image-20210622161242881.png" style="zoom:50%;" />
5. <img src="https://i.loli.net/2021/06/23/A1zYUZyKPJOmFTu.png" alt="image-20210622161303767.png" style="zoom:50%;" />
6. <img src="https://i.loli.net/2021/06/23/g651uMrkoiGDLIQ.png" alt="image-20210622161329703.png" style="zoom:50%;" />

### å¿è®°å¯ç 

#### è®¾è®¡æè·¯

å¿è®°å¯ç æ¶ï¼åªéå¡«åéè¦æ¾åå¯ç çè´¦å·ï¼ä»¥ååç­æ³¨åæ¶è®¾ç½®çå¯ä¿é®é¢ï¼å³å¯ä¿®æ¹æ°å¯ç ãéè¿æ¥è¯¢æ°æ®åºä¸­å¯ä¿è¡¨çæ°æ®ï¼æ¾åºå¯¹åºçè´¦å·åå¯¹åºçå¯ä¿é®é¢ç­æ¡ï¼å°è¾å¥çé®é¢ç­æ¡ä¸ä¹å¯¹ç§ï¼è¥ç¸åå³å¯è®¾ç½®æ°å¯ç ã

#### ä»£ç å±ç¤º

```java
for (Secret secret : list) {
    if (current.equals(secret.getAccount()) && secret.getStatus().equals(table)) {
        //éªè¯å¯ä¿
        while (!JOptionPane.showInputDialog(null, "å¯ä¿é®é¢ï¼" + secret.getQuestion(), "éªè¯å¯ä¿", JOptionPane.PLAIN_MESSAGE).equals(secret.getAnswer())) {
            //è¿å¥éç½®å¯ç ç¨åº
            JOptionPane.showMessageDialog(null, "å¯ä¿éè¯¯", "å¾æ±æ­", JOptionPane.ERROR_MESSAGE);
        }
        newPwd = JOptionPane.showInputDialog(null, "æ°çå¯ç ï¼", "éç½®å¯ç ", JOptionPane.PLAIN_MESSAGE);
        check = true;
    }
}
if (check) {
    changePwd(newPwd, table, current);
    JOptionPane.showMessageDialog(null, "æ¹å¯æåï¼è¯·ç¢è®°æ¨çå¯ç ï¼");
} else {
    JOptionPane.showMessageDialog(null, "è´¦å·ä¸å­å¨", "å¾æ±æ­", JOptionPane.ERROR_MESSAGE);
}

}
```

#### ç»æå±ç¤º

<img src="https://i.loli.net/2021/06/23/zqNRBQfkoZ8H1yP.png" alt="image-20210623104339516.png" style="zoom:50%;" />

<img src="https://i.loli.net/2021/06/23/ONU9q2ASn4skF1t.png" alt="image-20210623104359730.png" style="zoom:50%;" />

