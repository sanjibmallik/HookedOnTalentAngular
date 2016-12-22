hotMainModule.controller('loginCtrl',function($scope,loginServices){
	
	$scope.projectTitle="Login Page";
	$scope.userLoginFormSubmit = function(){


	if("admin"==$scope.loginUser.userName && "admin"==$scope.loginUser.password){
		alert("login");

	}else{
		alert("failed");

	}

}
}
);
