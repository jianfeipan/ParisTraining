<?php
	//$name=$_GET["names"];//接收参数
	$hostname_conn = "localhost";
	$database_conn = "user";//数据库名
	$username_conn = "ParisTraining";//用户名
	$password_conn = "xiaozhushou";//自己数据库的密码
	//连接MYSQL数据库
	$conn = mysqli_connect($hostname_conn, $username_conn, $password_conn,$database_conn)or trigger_error(mysqli_error(),E_USER_ERROR);
	if(!$conn){
		
		echo "连接不成功！";	
	}
	else{
		$sql = "SELECT NAME FROM small_class";
		mysqli_query($conn, "set names 'utf8'");
		$result = mysqli_query($conn, $sql);
		class Name{
			public $user_name;
		}
		$names = array();
		if (mysqli_num_rows($result) > 0) {
			while($row = mysqli_fetch_assoc($result)) {
				$user=new Name();
				$user->user_name=$row["NAME"];
				$data[] = $user;
			}
			echo json_encode($data,JSON_UNESCAPED_UNICODE|JSON_PRETTY_PRINT);//将请求结果转换为json格式
		}
	}
?>