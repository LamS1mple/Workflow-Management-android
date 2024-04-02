package com.example.workflowmanagementandroid.Model;

import java.util.List;

public class User {



        private long id;


        private String name;

        private String userName;

        private String passWord;

        private byte[] img;

        private List<GroupMember> listGroupJoin;


        private List<Group> listGroupHost;

        private List<TaskMember> listTaskMembers;

        public long getId() {
                return id;
        }

        public void setId(long id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getUserName() {
                return userName;
        }

        public void setUserName(String userName) {
                this.userName = userName;
        }

        public String getPassWord() {
                return passWord;
        }

        public void setPassWord(String passWord) {
                this.passWord = passWord;
        }

        public byte[] getImg() {
                return img;
        }

        public void setImg(byte[] img) {
                this.img = img;
        }

        public List<GroupMember> getListGroupJoin() {
                return listGroupJoin;
        }

        public void setListGroupJoin(List<GroupMember> listGroupJoin) {
                this.listGroupJoin = listGroupJoin;
        }

        public List<Group> getListGroupHost() {
                return listGroupHost;
        }

        public void setListGroupHost(List<Group> listGroupHost) {
                this.listGroupHost = listGroupHost;
        }

        public List<TaskMember> getListTaskMembers() {
                return listTaskMembers;
        }

        public void setListTaskMembers(List<TaskMember> listTaskMembers) {
                this.listTaskMembers = listTaskMembers;
        }
}
