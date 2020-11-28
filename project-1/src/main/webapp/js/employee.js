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