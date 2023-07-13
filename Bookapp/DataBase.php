<?php
include "DataBaseConfig.php";


class DataBase
{
    public $connect;
    public $data;
    private $sql;
    protected $servername;
    protected $username;
    protected $password;
    protected $databasename;
    
    

    public function __construct()
    {
        $this->connect = null;
        $this->data = null;
        $this->sql = null;
        $dbc = new DataBaseConfig();
        $this->servername = $dbc->servername;
        $this->username = $dbc->username;
        $this->password = $dbc->password;
        $this->databasename = $dbc->databasename;
        $this->connect = mysqli_connect($this->servername, $this->username, $this->password, $this->databasename);

    }

    function dbConnect()
    {
        return $this->connect;
    }

    function prepareData($data)
    {
        if ($this->connect !== null) {
            return mysqli_real_escape_string($this->connect, stripslashes(htmlspecialchars($data)));
        }
    }

    function logIn($table, $username, $password)
    {
        $username = $this->prepareData($username);
        $password = $this->prepareData($password);
        $this->sql = "select * from " . $table . " where username = '" . $username . "'";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbusername = $row['username'];
            $dbpassword = $row['password'];
            if ($dbusername == $username && password_verify($password, $dbpassword)) {
                $login = true;
            } else $login = false;
        } else $login = false;

        return $login;
    }

    function signUp($table, $fullname, $email, $username, $password)
    {
        $fullname = $this->prepareData($fullname);
        $username = $this->prepareData($username);
        $password = $this->prepareData($password);
        $email = $this->prepareData($email);
        $password = password_hash($password, PASSWORD_DEFAULT);
        $this->sql =
            "INSERT INTO " . $table . " (fullname, username, password, email) VALUES ('" . $fullname . "','" . $username . "','" . $password . "','" . $email . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }

    function donate($table,$username, $mobile, $bookname, $subject, $semester, $booktype)
{
    $flag=0;
    $bookname = $this->prepareData($bookname);
    $subject = $this->prepareData($subject);
    $semester = $this->prepareData($semester);
    $booktype = $this->prepareData($booktype);
    $mobile = $this->prepareData($mobile);
    $this->sql =
        "INSERT INTO " . $table . " (username,bookname, subject, semester, booktype,mobile,flag) VALUES ('" . $username . "','" . $bookname . "','" . $subject . "','" . $semester . "','" . $booktype . "','" . $mobile . "','" . $flag . "')";
    if (mysqli_query($this->connect, $this->sql)) {
        return true;
    } else return false;
}


}

?>