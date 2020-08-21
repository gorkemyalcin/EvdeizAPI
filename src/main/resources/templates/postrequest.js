/*$(document).ready(function() {
	console.log(xxx);*/
	$('#submitForm').click(function() {
		console.log("sss");
		// Prevent the form from submitting via the browser.
		//event.preventDefault();
		ajaxPost();
	});
	function ajaxPost() {
		console.log("ve buraya da");
		// PREPARE FORM DATA
		var formData = {
			"name" : "htrhfgt hytg",//$("#title").val()
			"type" : "GEZİ",//$("#type").val(),
			"place" : "xhdy hryx",//$("#place").val(),
			"address" : "xxhtr hytht",//$("#address").val(),
			"startDate" : "20.05.2020",//$("#startDate").val()
			"startTime" : "10:10",//$("#startTime").val()
			"endDate" :"15.08.2021", //$("#endDate").val()
			"endTime" : "10:10",// $("#endTime").val()
			"description" : "hdrthdth hdyhh",//$("#description").val()
			"longitute" :"44.454",
			"latitute" :"11.54611",
			"companyName" : "municipaliy",//$("#company").val(),
			"festivalName" : "",
			//"image" : "xx.png",//$("#image").val(),
		}

		// DO POST
		$.ajax({
			type : "POST",
			contentType : 'application/json; charset=UTF-8',
			url : "/events",
			data : JSON.stringify(formData),
			dataType : 'json',
			success : function(result) {
				console.log("selamınaleykum");
				if (result.status == "success") {
					$("#page").html("Post Successfully! <br>" + "---> Congrats !!" + "</p>");
					console.log("ass");
				} else {
					$("#page").html("<strong>Error</strong>");
					console.log("susla");
				}
				console.log(result);
			},
			error : function(e) {
				alert("Error!");
				console.log(JSON.stringify(formData));
				console.log("ERROR: ", e);
			}
		});

	}
//});

			/*function ajaxGet() {
				console.log("ve buraya da");
				// PREPARE FORM DATA
				var formData = {
					"name" : "htrhfgt hytg",//$("#title").val()
					"type" : "GEZİ",//$("#type").val(),
					"place" : "xhdy hryx",//$("#place").val(),
					"address" : "xxhtr hytht",//$("#address").val(),
					"startDate" : "20.05.2020",//$("#startDate").val()
					"startTime" : "10:10",//$("#startTime").val()
					"endDate" :"15.08.2021", //$("#endDate").val()
					"endTime" : "10:10",// $("#endTime").val()
					"description" : "hdrthdth hdyhh",//$("#description").val()
					"longitute" :"44.454",
					"latitute" :"11.54611",
					"companyName" : "municipaliy",//$("#company").val(),
					"festivalName" : "",
					//"image" : "xx.png",//$("#image").val(),
				}

				// DO POST
				$.ajax({
					type : "POST",
					contentType : 'application/json; charset=UTF-8',
					url : "/events",
					data : JSON.stringify(formData),
					dataType : 'json',
					success : function(result) {
						console.log("selamınaleykum");
						if (result.status == "success") {
							$("#page").html("Post Successfully! <br>" + "---> Congrats !!" + "</p>");
							console.log("ass");
						} else {
							$("#page").html("<strong>Error</strong>");
							console.log("susla");
						}
						console.log(result);
					},
					error : function(e) {
						alert("Error!");
						console.log(JSON.stringify(formData));
						console.log("ERROR: ", e);
					}
				});

			}*/
	


/*function postInfo(){
	console.log("üzülme ben")
	$(document).ready(
			function() {
				console.log("buraya da");
				// SUBMIT FORM
				
				$('#eventForm').submit(function(event) {
					console.log("geldi");
					// Prevent the form from submitting via the browser.
					event.preventDefault();
					ajaxPost();
					
				});
				function ajaxPost() {
					console.log("ve buraya da");
					// PREPARE FORM DATA
					var formData = {
						"name" : "htrhfgt hytg",//$("#title").val()
						"type" : "GEZİ",//$("#type").val(),
						"place" : "xhdy hryx",//$("#place").val(),
						"address" : "xxhtr hytht",//$("#address").val(),
						"startDate" : "20.05.2020",//$("#startDate").val()
						"startTime" : "10:10",//$("#startTime").val()
						"endDate" :"15.08.2021", //$("#endDate").val()
						"endTime" : "10:10",// $("#endTime").val()
						"description" : "hdrthdth hdyhh",//$("#description").val()
						"longitute" :"44.454",
						"latitute" :"11.54611",
						"companyName" : "xx",//$("#company").val(),
						"festivalName" : "fess",
						//"image" : "xx.png",//$("#image").val(),
					}

					// DO POST
					$.ajax({
						type : "POST",
						contentType : 'application/json; charset=UTF-8',
						url : "/events",
						data : JSON.stringify(formData),
						dataType : 'json',
						success : function(result) {
							console.log("selamınaleykum");
							if (result.status == "success") {
								$("#page").html("Post Successfully! <br>" + "---> Congrats !!" + "</p>");
								console.log("ass");
							} else {
								$("#page").html("<strong>Error</strong>");
								console.log("susla");
							}
							console.log(result);
						},
						error : function(e) {
							alert("Error!");
							console.log(JSON.stringify(formData));
							console.log("ERROR: ", e);
						}
					});

				}

			})

}
/*
$(document).ready(
		function() {

			// SUBMIT FORM
			$("#eventForm").submit(function(event) {
				alert("helloo");
				// Prevent the form from submitting via the browser.
				event.preventDefault();
				ajaxPost();
			});
			function ajaxPost() {

				// PREPARE FORM DATA
				var formData = {
					name : $("#title").val(),
					type : $("#type").val(),
					description : $("#description").val(),
					startDate : $("#startDate").val(),
					startTime : $("#startTime").val(),
					endDate : $("#endDate").val(),
					endTime : $("#endTime").val(),
					place : $("#place").val(),
					address : $("#address").val(),
					company : $("#company").val(),
					image : $("#image").val(),
					latitute:"1111",
					longitute:"444",

				}

				// DO POST
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "saveEvent",
					data : JSON.stringify(formData),
					dataType : 'json',
					success : function(result) {
						if (result.status == "success") {
							$("#page").html("Post Successfully! <br>" + "---> Congrats !!" + "</p>");
						} else {
							$("#page").html("<strong>Error</strong>");
						}
						console.log(result);
					},
					error : function(e) {
						alert("Error!")
						console.log("ERROR: ", e);
					}
				});

			}

		})
*/