package com.app.bookapp;

public class booksavailable {

    private String username,bookname,subject,semester,booktype,mobile;
    public static final String SHOW_ALL_USER_DATA_URL="http://117.247.182.134:8081/ic/mad/Bookapp/request.php";
    public static final String DELETE_USER_URL="http://117.247.182.134:8081/ic/mad/Bookapp/update.php";


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getBooktype() {
        return booktype;
    }

    public void setBooktype(String booktype) {
        this.booktype = booktype;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
