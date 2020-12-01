function deny(){
let id = document.getElementById("did").value;
    
    
    let template = {
        id: id,
        rStatus: "Denied"
    }
    let xhr = new XMLHttpRequest()
    xhr.onreadystatechange = function(){
    	 if(this.readyState ===4 && this.status ===200){
            
			
        document.getElementById("updateView").innerHTML = "Denied";
		
        }else{
        
        document.getElementById("updateView").innerHTML = "Error"
        }
    
    }
    
     xhr.open("POST","http://localhost:8080/project-1/update/2")
    xhr.send(JSON.stringify(template))

}
function approve(){
	let id = document.getElementById("upid").value;
    
    
    let template = {
        id: id,
        rStatus: "Denied"
    }
    let xhr = new XMLHttpRequest()
    xhr.onreadystatechange = function(){
    	 if(this.readyState ===4 && this.status ===200){
            
			
        document.getElementById("updateView").innerHTML = "Approved";
		
        }else{
        
        document.getElementById("updateView").innerHTML = "Error"
        }
    
    }
    
     xhr.open("POST","http://localhost:8080/project-1/update/1")
    xhr.send(JSON.stringify(template))
}
function viewByA(){
    let id = document.getElementById("eid").value; 

    let template = {
        id: id
    }
    let xhr = new XMLHttpRequest()
    xhr.onreadystatechange = function(){
        if(this.readyState ===4 && this.status ===200){
            var myArr = JSON.parse(this.responseText);
			
        document.getElementById("viewDiv").innerHTML = JSON.stringify(myArr);
		document.getElementById("json").textContent = JSON.stringify(myArr, undefined, 2);
        }
    }
    var v = "http://localhost:8080/project-1/byauthor"+id;
    xhr.open("POST","http://localhost:8080/project-1/byauthor")
	xhr.send(JSON.stringify(template))
}
function viewEmp(){
    let xhr = new XMLHttpRequest()
    xhr.onreadystatechange = function(){
        if(this.readyState ===4 && this.status ===200){
            var myArr = JSON.parse(this.responseText);
			
        document.getElementById("viewDiv").innerHTML = JSON.stringify(myArr);
        }
    }
    xhr.open("POST","http://localhost:8080/project-1/viewEmp")
	xhr.send()
}

function viewPend(){
    let xhr = new XMLHttpRequest()
    xhr.onreadystatechange = function(){
        if(this.readyState ===4 && this.status ===200){
            var myArr = JSON.parse(this.responseText);
			
        document.getElementById("viewDiv").innerHTML = JSON.stringify(myArr);
        }
    }
    xhr.open("POST","http://localhost:8080/project-1/viewPend")
	xhr.send()
}

function viewAppr(){
   let xhr = new XMLHttpRequest()
    xhr.onreadystatechange = function(){
        if(this.readyState ===4 && this.status ===200){
            var myArr = JSON.parse(this.responseText);
			
        document.getElementById("viewDiv").innerHTML = JSON.stringify(myArr);
        }
    }
    xhr.open("POST","http://localhost:8080/project-1/viewAppr")
	xhr.send()
}

function logout(){
    let xhr = new XMLHttpRequest()
	
    xhr.onreadystatechange = function(){
        if(this.readyState ===4 && this.status ===200){
            window.location = "http://localhost:8080/project-1/index.html"
        }
        
    }

    xhr.open("POST","http://localhost:8080/project-1/logout")
	xhr.send()

}