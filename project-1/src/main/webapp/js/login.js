
function Login()
{
	 
   
    let uName = document.getElementById("username").value;
    let pWord = document.getElementById("password").value;
    
    let loginTemplate = {
        userName: uName,
        passWord: pWord
    }
   
    let xhr = new XMLHttpRequest()
    xhr.onreadystatechange = function(){
    	
    	if(this.readyState <= 3){
    		
    	}
        if(this.readyState === 4 && this.status === 201)
        {
            
           
            
            window.location = "http://localhost:8080/project-1/Employee.html"
           
        }

		if(this.readyState === 4 && this.status === 202)
        {
            
           
           
            window.location = "http://localhost:8080/project-1/Manager.html"
           
        }
        if(this.readyState ===4 && this.status ===204)
        {
           document.getElementById('gif').style.visibility = 'visible';
		var div = document.getElementById('div3');
		div.innerHTML = "User not found. Check login credentials"
        }
        
        
    }
    xhr.open("POST","http://localhost:8080/project-1/login")
    xhr.send(JSON.stringify(loginTemplate))
}