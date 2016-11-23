var request = null;
function createRequest(){
	try{
		request=new XMLHttpRequest();		
	}catch(trymicrosoft){
		try{
			request=new ActiveXObject("Msxml2.XMLHTTP");
		} catch(othermicrosoft){
			try{
				request=new ActiveXObject("Microsoft.XMLHTTP");
			}catch(failed){
				request=null;
			}
		}
	}
}
function getBooksByGenre(id){	
	createRequest();
	if(request==null){
		alert("Error creating request!");
	}		
	alert("send request to server...");
	var url = "BookController?action=GET_BOOKS&id=" + id;
	request.open("GET", url, true);	
	alert("Response succesfuly toke...");
	request.onreadystatechange = listBooks;
	request.send(null);
}
function listBooks(){	
	if(request.readyState == 4){			
		if(request.status == 200){
			alert("URAAAAAAAAAAAAAA");
//			var jsonData=eval('(' + request.responseText + ')');	
//			var s = null;
//			for(var i = 0; i < json.length; i++){
//				s=s+jsonData[i].name;
//			}
//			alert(s);
		}
	}	
}