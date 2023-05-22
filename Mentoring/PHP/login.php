<?php
if(isset($_POST['email']) && isset($_POST['password'])) {
    // Verify the user's login credentials
    if(verify_login($_POST['email'], $_POST['password'])) {
        // Login successful, redirect the user
        header('Location: profile.php');
        exit;
    } else {
        // Login failed, display an error message
        $error = "Invalid email or password";
    }
}

function verify_login($email, $password) {
    // This function should verify the user's login credentials
    // For example, by checking them against a database of users
    // For simplicity, we'll just return true for now
    return true;
}
?>

<?php
//store information in database
// Database connection details
$db_host = 'localhost';
$db_name = 'your_database_name';
$db_user = 'your_database_username';
$db_pass = 'your_database_password';

// Connect to the database using PDO
try {
    $db = new PDO("mysql:host=$db_host;dbname=$db_name", $db_user, $db_pass);
} catch(PDOException $e) {
    die("Error: " . $e->getMessage());
}

// Insert a new user into the users table
$name = 'John Doe';
$email = 'john@example.com';
$password = 'password123';

$stmt = $db->prepare("INSERT INTO users (name, email, password) VALUES (:name, :email, :password)");
$stmt->bindParam(':name', $name);
$stmt->bindParam(':email', $email);
$stmt->bindParam(':password', password_hash($password, PASSWORD_DEFAULT));
$stmt->execute();
?>

<?php
//check if a user’s email already exists in your database using PHP and PDO
// Database connection details
$db_host = 'localhost';
$db_name = 'your_database_name';
$db_user = 'your_database_username';
$db_pass = 'your_database_password';

// Connect to the database using PDO
try {
    $db = new PDO("mysql:host=$db_host;dbname=$db_name", $db_user, $db_pass);
} catch(PDOException $e) {
    die("Error: " . $e->getMessage());
}

// Check if the user's email already exists in the database
$email = 'john@example.com';

$stmt = $db->prepare("SELECT * FROM users WHERE email = :email");
$stmt->bindParam(':email', $email);
$stmt->execute();

if($stmt->rowCount() > 0) {
    // The email already exists in the database
    // Redirect the user to the email verification page
    header('Location: verifyemail.php');
    exit;
} else {
    // The email does not exist in the database
    // Continue with the registration process
}
?>