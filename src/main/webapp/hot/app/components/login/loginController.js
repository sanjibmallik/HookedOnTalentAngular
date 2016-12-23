hotMainModule.controller('loginCtrl',function($scope,$window,loginServices){
	
	$scope.projectTitle="Login Page";
	$scope.message = loginServices.get;
	console.log($scope.message);

    $scope.errorMessageShow= false;
    $scope.errorMessage="User name or Password is invalid";


    $scope.userLoginFormSubmit = function(){

	if("admin"==$scope.loginUser.userName && "admin"==$scope.loginUser.password){
		alert("login");
        $state.go('dashboard');

	}else{
        $scope.errorMessageShow=false;
		alert("failed");

	}

}
}
);
