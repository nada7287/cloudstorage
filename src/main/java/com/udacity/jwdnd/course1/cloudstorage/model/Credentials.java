package com.udacity.jwdnd.course1.cloudstorage.model;


public class Credentials {

    private Integer credentialId;
    private String urlLink;
    private String userName;
    private String keyItem;
    private String passWord;
    private Integer userId;

    public String getKeyItem() {
        return keyItem;
    }

    public void setKeyItem(String keyItem) {
        this.keyItem = keyItem;
    }



    public Integer getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUrlLink() {
        return urlLink;
    }

    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
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


    public Credentials(Integer credentialid,String url,String username,String keyItem, String password,Integer userid){

        this.credentialId=credentialid;
        this.userId=userid;
        this.urlLink=url;
        this.userName=username;
        this.keyItem=keyItem;
        this.passWord=password;


    }


}
