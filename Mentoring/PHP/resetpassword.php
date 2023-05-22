<?php
if(isset($_POST['email'])) {
    $to = $_POST['email'];
    $subject = "Password Reset";
    $message = "Please click the link below to reset your password:\n\n";
    $message .= "https://yourwebsite.com/resetpassword.php?email=" . urlencode($to);
    $headers = 'From: noreply@yourwebsite.com' . "\r\n";
    mail($to, $subject, $message, $headers);
}
?>    