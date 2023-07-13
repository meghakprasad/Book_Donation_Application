<?php
session_start();
require "DataBase.php";
$db = new DataBase();
$user=$_POST['username'];

if (isset($_POST['username']) && isset($_POST['password'])) {
    if ($db->dbConnect()) {
        if ($db->logIn("users", $_POST['username'], $_POST['password'])) {
            $_SESSION["username"] = $user;           // Set the "user" key in the session  
            echo "Login Success";
        } else {
            echo "Username or Password wrong";
        }
    } else {
        echo "Error: Database connection";
    }
} else {
    echo "All fields are required";
}

?>