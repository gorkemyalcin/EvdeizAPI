$(document).ready(
		function() {
			function ajaxGetCompanies(){
				$.ajax({
					type : "GET",
					url : "/companies",
					success : function(result) {
						for (var i = 0; i < result.length; i++) {
							var sel = document.getElementById("company");
							var option = document.createElement("option");
							option.appendChild( document.createTextNode(result[i].name) );
							option.value = result[i].name; 
							sel.appendChild(option);
						}
					},
					error : function(e) {
						console.log("ERROR: ", e);
					}
				});
			}
			function ajaxGet() {
				$.ajax({
					type : "GET",
					url : "/events",
					success : function(result) {
					    var container = document.getElementById("events");
						for (var i = 0; i < result.length; i++) {
							sendEvent=[];
							sendEvent=result;
							eventInfo={};
							eventInfo=result[i];
							classEventName="event".concat("-");
							classEventName=classEventName.concat(eventInfo.id);
					        container.innerHTML += "<div class='event'><div class='eventDate'></div><div class='eventName'></div><div class='bottomDiv'><div class='eventPlace'></div>  <div class='selectionButton'><div class='editEvent'><button id='"+classEventName+"' onclick=\'editSelectedEvent(sendEvent,event.target.id);\' ><i class='fas fa-edit' style='font-size:20px'></i>Düzenle </button></div><div ><button id='"+classEventName+"' onclick=\'deleteSelectedEvent(sendEvent,event.target.id);\' ><i class='fas fa-trash-alt'></i>Delete</button></div></div></div>";
							var divDate = document.getElementsByClassName('eventDate')[i];
					        divDate.innerHTML += result[i].startDate;
					        var divName = document.getElementsByClassName('eventName')[i];
					        divName.innerHTML += result[i].name;
					        var divPlace = document.getElementsByClassName('eventPlace')[i];
					        divPlace.innerHTML += result[i].place;
					    }
					},
					error : function(e) {
						console.log("ERROR: ", e);
					}
				});
			}
			function ajaxPostCompany(){				
				//document.getElementById("logo").value=="" ? null :document.getElementById("logo").value
				var formData={
						"name": document.getElementById("companyName").value,
						"telephone": document.getElementById("companyTelephone").value,
						"email" : document.getElementById("companyEmail").value,
						"description" : document.getElementById("companyDescription").value,
						"logo":document.getElementById("logo").value=="" ? null :document.getElementById("logo").value
				}
				$.ajax({
					url : "/companies",
					type : "POST",
					contentType : 'application/json; charset=UTF-8',
					data : JSON.stringify(formData),
					success : function() {
		                console.log("Posted successfully");	
		                //location.reload();
		                //return false;
					},
					error : function(e) {
						console.log(JSON.stringify(formData));
						console.log("ERROR: ", e);
					}
				});
			}
			function ajaxPostEvent() {
				var companies = document.getElementById("company");
				var companyName;
				if(companies.options[companies.selectedIndex].value=="unselected"){
					companyName=null;
				}
				else if(companies.options[companies.selectedIndex].value=="diger"){
					companyName=document.getElementById("companyName").value;
				}
				else{
					companyName=companies.options[companies.selectedIndex].text;
				}
				console.log(document.getElementById("type").value);
				console.log("burası bak"+convertDate2(document.getElementById("startDate").value));
				var formData = {
					"name" : document.getElementById("title").value,
					"type" : document.getElementById("type").value,
					"place" : document.getElementById("place").value,
					"address" : document.getElementById("address").value,
					"startDate" : convertDate2(document.getElementById("startDate").value), 
					"startTime" : document.getElementById("startTime").value,
					"endDate" : convertDate2(document.getElementById("endDate").value),
					"endTime" : document.getElementById("endTime").value,
					"description" : document.getElementById("description").value,
					"companyName" : companyName,
					"longitude" :latLng.lng,
					"latitude" :latLng.lat,
					"festivalName" : "ff",
					//"image" : "xx.png",//$("#image").val(),
				}
				$.ajax({
					url : "/events",
					type : "POST",
					contentType : 'application/json; charset=UTF-8',
					data : JSON.stringify(formData),
					success : function() {
		                console.log("Posted successfully");	
		                location.reload();
		                //return false;
					},
					error : function(e) {
						console.log(JSON.stringify(formData));
						console.log("ERROR: ", e);
					}
				});
			
			}
			ajaxGet();
			ajaxGetCompanies();
			$('#submitForm').click(function() {
				if (document.getElementById("title").value=="" || document.getElementById("type").value=="" ||
						document.getElementById("description").value=="" ||
						document.getElementById("startDate").value=="" ||
						document.getElementById("startTime").value=="" ||
						document.getElementById("endDate").value=="" ||
						document.getElementById("endTime").value=="" ||
						document.getElementById("place").value=="" ||
						document.getElementById("address").value=="" ){
						alert("Eksik alanları doldurunuz.");
		                location.reload();
		                //return false;
				}
				ajaxPostEvent();
			});
			$('#submitCompanyForm').click(function (){
				console.log("xxxg"); 
				if(document.getElementById("companyName").value ==""){
					alert("Eksik alanları doldurunuz.");
				}
				else{
					ajaxPostCompany();
				}
			});
			$('#updateEventButton').click(function () {
				if (document.getElementById("title").value=="" || document.getElementById("type").value=="" ||
						document.getElementById("description").value=="" ||
						document.getElementById("startDate").value=="" ||
						document.getElementById("startTime").value=="" ||
						document.getElementById("endDate").value=="" ||
						document.getElementById("endTime").value=="" ||
						document.getElementById("place").value=="" ||
						document.getElementById("address").value=="" ){
						alert("Eksik alanları doldurunuz.");
		                location.reload();
		                //return false;
				}
				var companyName;
				var companies = document.getElementById("company");
				if(companies.options[companies.selectedIndex].value=="unselected"){
					companyName=null;
				}
				else if(companies.options[companies.selectedIndex].value=="diger"){
					companyName=document.getElementById("companyName").value;
					
				}
				else{
					companyName=companies.options[companies.selectedIndex].text;
				}
				var event = {
						"id": eventId,
						"name" : document.getElementById("title").value,
						"type" : document.getElementById("type").value,
						"place" : document.getElementById("place").value,
						"address" : document.getElementById("address").value,
						"startDate" : convertDate2(document.getElementById("startDate").value), 
						"startTime" : document.getElementById("startTime").value,
						"endDate" : convertDate2(document.getElementById("endDate").value),
						"endTime" : document.getElementById("endTime").value,
						"description" : document.getElementById("description").value,
						"companyName" : companyName,
						"longitude" :latLng.lng,
						"latitude" :latLng.lat,
						"festivalName" : "ff",
						//"companyName": document.getElementById("company").value,
						//"image" : "xx.png",//$("#image").val(),
				}
		        var eventObj = JSON.stringify(event);
		        $.ajax({
		            url: "events/",
		            method: "PUT",
		            data: eventObj,
		            contentType: 'application/json; charset=utf-8',
		            success: function () {
		                console.log("Edited successfully");
		                location.reload();
		                //return false;
		            },
		            error: function (error) {
		                alert("errorrr:"+error);
		            }
		        })
		    })
			}	
)
function deleteSelectedEvent(allEvents,classEventName){
	selectedEventId=classEventName.split('-')[1];
	for (i = 0; i < allEvents.length; i++) {
		if(parseInt(selectedEventId) == parseInt(allEvents[i].id)){
			$.ajax({
			    type : "DELETE",
			    url : "events/" ,
				contentType : 'application/json; charset=UTF-8',
			    data : selectedEventId.toString(),
			    success: function (result) {       
			           console.log(result);
			           location.reload();
			           //return false;
			    },
			    error: function (e) {
			        console.log(e);
			    }
			});
		}

	}
	/**/

}