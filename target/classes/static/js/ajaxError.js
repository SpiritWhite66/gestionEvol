			$(document).ready(getError());
			function getError(errorType)
			{
				$.ajax({
					url: "getError/"+errorType
				}).then(function(data) {
					ErrorDisplay(data);
				});
			}
			
			function updateCountError()
			{
				var total = 0;
				$.ajax({
					method: "GET",
					url: "getCountWarning",
				}).then(function(data) {
					total += data;
					$(".btn-warning .badge").html(data);
					
					$.ajax({
						method: "GET",
						url: "getCountError",
					}).then(function(data) {
						total += data;
						$(".btn-danger .badge").html(data);
						$(".btn-info .badge").html(total);
					});
				});

			}
			
			function ErrorDisplay(data)
			{
				updateCountError();
				$("#containerError").children().remove();
				var classType = "alert-warning";
				jQuery.each(data, function(i, donnee) {
					if(donnee.error_code==1)
					{
						classType = "alert-warning";
					}
					else if(donnee.error_code==2)
					{
						classType = "alert-danger";
					}
					$("#containerError").append('<div class=\"alert '+classType+' col-lg-8\" data-name=\"'+donnee.name+'\">'+
							'<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
							'<p>'+donnee.name+'</p><p>'+donnee.error_message+'</p></div>');
				});
			}
			
			function noErrorDisplay() {
				$(".alert").remove();
				$("#containerError").html("<div class=\"alert alert-success col-lg-8\">Aucune erreur présente.</div>");
			}
			function updateError(baliseThis)
			{
				if(baliseThis.parent().hasClass("alert-warning"))
				{
					nbr_warning = $(".btn-warning .badge").html()-1;
					$(".btn-warning .badge").html(nbr_warning);
				}
				else if(baliseThis.parent().hasClass("alert-danger"))
				{
					nbr_danger = $(".btn-danger .badge").html()-1;
					$(".btn-danger .badge").html(nbr_danger);
				}
				nbr_all = $(".btn-info .badge").html()-1;
				$(".btn-info .badge").html(nbr_all);
			}
			function deleteError(idError, baliseThis) {
				$.ajax({
					url: "deleteError/",
					data: {idError: idError}
				}).success(function(data) {
					if(data && idError!=null)
					{	
						updateError(baliseThis);
					}
					else if(data && idError==null)
					{
						$(".btn-warning .badge").html("0");
						$(".btn-danger .badge").html("0");
						$(".btn-info .badge").html("0");
						noErrorDisplay();
					}
				});
			}
			
			$(".removeAll").on('click',function() {
				deleteError(null)
			});
			
			$("#containerError").on('click',".close",function() {
				deleteError($(this).parent().attr("data-name"), $(this));
			});
			
			$(".btn-command").on('click',function() {
				getError($(this).attr('id'));
			});
			