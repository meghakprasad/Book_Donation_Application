<?php
session_start();

define('DB_HOST', 'localhost');
define('DB_USER', 'root');
define('DB_PASS', '');
define('DB_NAME', 'bookapp');

$conn = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME);
if (mysqli_connect_errno()) {
    die('Unable to connect to the database' . mysqli_connect_error());
}

// Check if the 'username' parameter is present in the POST data
if (isset($_POST['username'])) {
    $username = $_POST['username'];

    // Validate the received username (you can add your own validation logic here)
    if (!empty($username)) {

        $sql = "UPDATE donate SET flag='1' WHERE username = '$username'";

        if ($conn->query($sql) === TRUE) {
            echo "Record updated successfully";
        } else {
            echo "Error updating record: " . $conn->error;
        }

    } else {
        echo "Empty username received";
    }
} else {
    echo "No username parameter received";
}

$conn->close();
?>
