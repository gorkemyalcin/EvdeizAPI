$(document).ready(function() {
	$('#updateEventButton').click(function () {
    	console.log(eventId);
    	event.id=eventId;
    	event.name=document.getElementById("title").value;
		event.type=document.getElementById("type").value ;
		event.description=document.getElementById("description").value; 
		event.startTime=document.getElementById("startTime").value;
		event.endTime=document.getElementById("endTime").value;
		event.address=document.getElementById("address").value;
		event.place=document.getElementById("place").value;
		event.startDate=document.getElementById("startDate").value; 
		event.endDate=document.getElementById("endDate").value;
        var eventObj = JSON.stringify(event);
        console.log(event);
        $.ajax({
            url: "events/",
            method: "PUT",
            data: eventObj,
            contentType: 'application/json; charset=utf-8',
            success: function () {
                alert('Saved successfully');
            },
            error: function (error) {
                alert(error);
            }
        })
    })
	
});

 
       /*
 
        function deleteBook(id){
            $.ajax({
                url: 'http://localhost:8037/spring-mvc-restfull-crud-example/book/'+id,
                method: 'DELETE',
                success: function () {
                    alert('record has been deleted');
                    getAllBooks();
                },
                error: function (error) {
                    alert(error);
                }
            })
        }
 
        function update(id){
            $.ajax({
                url: 'http://localhost:8037/spring-mvc-restfull-crud-example/book/'+id,
                method: 'GET',
                dataType: 'json',
                success: function (data) {
                    $('#txtTitle').val(data.title);
                    $('#txtAuthor').val(data.author);
                    $('#txtId').val(data.id);
                    getAllBooks();
                },
                error: function (error) {
                    alert(error);
                }
            })
        }
 
        function reset(){
            $('#txtTitle').val('');
            $('#txtAuthor').val('');
            $('#txtId').val('');
        }*/







/*GET: $(document).ready(
		function() {
			// GET REQUEST
			$('#updateEventButton').click(function() {
				// Prevent the form from submitting via the browser.
				//event.preventDefault();
				ajaxPut();
			});
			
			var event={};
			function ajaxPut() {
				console.log("xx");
				event.name=document.getElementById("title").value;
				event.type=document.getElementById("type").value ;
				event.description=document.getElementById("description").value;
			   	event.startTime=document.getElementById("startTime").value;
				event.endTime=document.getElementById("endTime").value;
				event.address=document.getElementById("address").value;
				event.place=document.getElementById("place").value;
				
				$.ajax({
					type : "PUT",
					url : "/events",
					data: event,
					success : function() {
								console.log("success");
					},
					error : function(e) {
						console.log("ERROR: ", e);
					}
				});
			}
		}
)*/