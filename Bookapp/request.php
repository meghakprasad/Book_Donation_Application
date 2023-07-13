<?php
session_start();
    define('DB_HOST','localhost');
    define('DB_USER','root');
    define('DB_PASS','');
    define('DB_NAME','bookapp');

    $conn=new mysqli(DB_HOST,DB_USER,DB_PASS,DB_NAME);
    if(mysqli_connect_errno()){
        die('Unable to connect to database'.mysqli_connect_error());
    }

    $stmt=$conn->prepare("SELECT username,bookname,subject,semester,booktype,mobile from donate where flag=0");

    $stmt->execute();
    $stmt->bind_result($username,$bookname,$subject,$semester,$booktype,$mobile);

    $donate=array();

    while($stmt->fetch()){
        $temp=array();
        $temp['username']=$username;
        $temp['bookname']=$bookname;
        $temp['subject']=$subject;
        $temp['semester']=$semester;
        $temp['booktype']=$booktype;
        $temp['mobile']=$mobile;
        array_push($donate,$temp);
    }
    echo json_encode($donate);