<?php
    $to = "artemenko_mihail@hotmail.com"; 
    // $to = "vanya_tsybulin@ukr.net";
    $subject = "Новая заявка обратной связи с сайта http://java.digitalsite.com.ua/";

    // $subject = "Заполнена контактная форма с ".$_SERVER['HTTP_REFERER'];
   
    if (isset($_POST["name"])){
        $name = $_POST["name"];
    }
  
    if (isset($_POST['phone'])){
        $phone = $_POST['phone'];
    }
    if (isset($_POST['email'])){
        $email = $_POST['email']; 
        $from = $_POST['email'];
    }
     if (isset($_POST['message'])){
        $message = $_POST['message'];
    }
     if (isset($_POST['courseType'])){
        $courseType = $_POST['courseType'];
    }
    else{
        $from = "info@java.digitalsite.com.ua";
    }

    $message = '
            <html>
                    <head>
                            <title>Заявка с сайта: </title>
                    </head>
                    <body>
                            <p>Имя: '.$name.'</p>
                            <p>Телефон: '.$phone.'</p>
                            <p>Email : '.$email.'</p>
                            <p>Сообщение : '.$message.'</p>
                             <p>Тип курса : '.$courseType.'</p>
                    </body>
            </html>';

$headers =   "From: ". $_POST["name"] ." <". $_POST["email"] .">\r\n"
        ."Reply-To: ". $_POST["email"] ."\r\n"
        ."Content-type: text/html; charset=utf-8 \r\n"
        ."X-Mailer: PHP/" . phpversion();

 

        $send_mail = mail($to, $subject, $message, $headers);

        if (!$send_mail)
  {
    //If mail couldn't be sent output error. Check your PHP email configuration (if it ever happens)
    $output = json_encode(array('type'=>'error', 'text' => 'Could not send mail! Please check your PHP mail configuration.'));
    die($output);
  } else {
    $output = json_encode(array('type'=>'message', 'text' => 'Hi '.$field_name_first.', thank you for your email.'));
    die($output);
  }
?>