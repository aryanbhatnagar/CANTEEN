package com.example.canteen;

public class helperClass {
    String mname, mregno,musername,mpassword, mphone;

    public helperClass() {
    }

    public helperClass(String mname, String mregno, String musername, String mpassword, String mphone) {
        this.mname = mname;
        this.mregno = mregno;
        this.musername = musername;
        this.mpassword = mpassword;
        this.mphone = mphone;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getMregno() {
        return mregno;
    }

    public void setMregno(String mregno) {
        this.mregno = mregno;
    }

    public String getMusername() {
        return musername;
    }

    public void setMusername(String musername) {
        this.musername = musername;
    }

    public String getMpassword() {
        return mpassword;
    }

    public void setMpassword(String mpassword) {
        this.mpassword = mpassword;
    }

    public String getMphone() {
        return mphone;
    }

    public void setMphone(String mphone) {
        this.mphone = mphone;
    }
}


