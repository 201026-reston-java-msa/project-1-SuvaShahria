function getR(){
    let xhr = new XMLHttpRequest()
    xhr.onreadystatechange = function(){
         if(this.readyState ===4 && this.status ===200){
			var ans = JSON.parse(this.responseText);
			document.getElementById("viewDiv").innerHTML = JSON.stringify(ans);
		}else{
			document.getElementById("viewDiv").innerHTML = "Unexpected Error"
		}
    }
		xhr.open("GET","http://localhost:8080/project-1/byauthor")
        xhr.send()
}
function updateInfo(){
    let userN = document.getElementById("updateUsername").value;
    let email = document.getElementById("updateEmail").value;
    let template = {
        userName: userN,
        email: email
    }
    let xhr = new XMLHttpRequest()
    xhr.onreadystatechange = function(){
        if(this.readyState ===4 && this.status ===200){
			var div = document.getElementById('div2');
			div.innerHTML = "User Updated";
        }else{
			var div = document.getElementById('div2');
			div.innerHTML = "Error";
	
		}
    }

    xhr.open("POST","http://localhost:8080/project-1/user")
    xhr.send(JSON.stringify(template))
}
function viewInfo(){
    let xhr = new XMLHttpRequest()
    xhr.onreadystatechange = function(){
        if(this.readyState ===4 && this.status ===200){
            
            var myArr = JSON.parse(this.responseText);
			var out = "Username: "+ myArr.userName+ '<br>'
			+ "First Name: "+myArr.firstName+ '<br>'
            + "Last Name: "+myArr.lastName+ '<br>'
			+ "email: "+myArr.email+ '<br>'
    document.getElementById("viewDiv").innerHTML = out;
        }
        
    }
    xhr.open("GET","http://localhost:8080/project-1/user")
	xhr.send()

}
function logout(){
    let xhr = new XMLHttpRequest()
	let loginTemplate = {
	
}
    xhr.onreadystatechange = function(){
        if(this.readyState ===4 && this.status ===200){
            window.location = "http://localhost:8080/project-1/index.html"
        }
        
    }

    xhr.open("POST","http://localhost:8080/project-1/logout")
	xhr.send()
}
function submitR(){
    let amount = document.getElementById("amount").value;
    let desc = document.getElementById("desc").value;
    const rTypes = document.querySelectorAll('input[name="gridRadios"]');
    let rTypeAns;
    for (const rType of rTypes) {
        if (rType.checked) {
            rTypeAns = rType.value;
            break;
        }
    }
    let loginTemplate = {
        amount: amount,
        description: desc,
		rType: rTypeAns
    }
   
    let xhr = new XMLHttpRequest()
    xhr.onreadystatechange = function(){
		if(this.readyState <= 3){
    		
    	}
        if(this.readyState === 4 && this.status === 200)
        {
            var div = document.getElementById('div2');
			div.innerHTML = "Reimbursement Created";
            

           
        }else{
		
			 var div = document.getElementById('div2');
			div.innerHTML = "Unexpected Error - Check inputs";
		
		}
	

    }

    xhr.open("POST","http://localhost:8080/project-1/reimbursements")
    xhr.send(JSON.stringify(loginTemplate))
   

}