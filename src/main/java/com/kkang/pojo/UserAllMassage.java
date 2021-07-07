package com.kkang.pojo;




public class UserAllMassage {
        private String userId;
        private String userPwd;
        private String userName;
        private String userTel;
        private String falg;
        public UserAllMassage() {
        }

    public String getFalg() {
        return falg;
    }

    public void setFalg(String falg) {
        this.falg = falg;
    }

    public UserAllMassage(String userId, String userPwd, String userName, String userTel) {
            this.userId = userId;
            this.userPwd = userPwd;
            this.userName = userName;
            this.userTel = userTel;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserPwd() {
            return userPwd;
        }

        public void setUserPwd(String userPwd) {
            this.userPwd = userPwd;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserTel() {
            return userTel;
        }

        public void setUserTel(String userTel) {
            this.userTel = userTel;
        }

        @Override
        public String toString() {
            return "UserAllMassage{" +
                    "userId='" + userId + '\'' +
                    ", userPwd='" + userPwd + '\'' +
                    ", userName='" + userName + '\'' +
                    ", userTel='" + userTel + '\'' +
                    '}';
        }
}
