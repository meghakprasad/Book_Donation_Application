<?php
session_start();
require "DataBase.php";
$db = new DataBase();

//$usn = $_SESSION["username"];

if (isset($_POST['username']) && isset($_POST['bookname']) && isset($_POST['subject']) && isset($_POST['semester']) && isset($_POST['booktype'])&& isset($_POST['mobile'])) {
    if ($db->dbConnect()) {

        if ($db->donate("donate",$_POST['username'],$_POST['mobile'], $_POST['bookname'], $_POST['subject'], $_POST['semester'], $_POST['booktype'])) {
            echo "Submitted Success";
        } else {
            echo "Submit Failed";
        }
    } else {
        echo "Error: Database connection";
    }
} else {
    echo "All fields are required";
}
?>
