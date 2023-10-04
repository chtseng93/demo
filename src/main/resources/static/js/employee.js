function deleteById(id,firstName, lastName) {
	if(window.confirm('Are you sure you want to delete this employee : '+firstName+lastName+'?')){
    	document.employForm.action="/webDemo/deleteEmployee";
    	document.getElementById("empId").value=id
    	console.log('deleteId='+document.getElementById("empId").value);
    	document.employForm.submit();
	}
	
}

function updateById(id) {
    document.employForm.action="/webDemo/updateEmployee";
    document.getElementById("empId").value=id
    console.log('updateId='+document.getElementById("empId").value);
    document.employForm.submit();
	
	
}