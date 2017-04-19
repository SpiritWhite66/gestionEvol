			$(".test").on('click',function() {
				nameEvol = $(this).attr('id');
			  	$.ajax({
					method: "GET",
					url: "getAppByEvol",
					data: {name: nameEvol}
			    }).then(function(data) {
			    	$('#titlebranche').text(nameEvol);
			    	$("#infoevol").children().remove();
			    	$("#infoevol").append('<a class="list-group-item active">Application</a>');
			    	jQuery.each(data, function(i, index) {
			    		name=index.name.replace("/", "");
						$("#infoevol").append('<li class="list-group-item" id="'+name+'"><span class="glyphicon glyphicon-menu-down application"></span>'+index.name+'<span class="data"></span></li>');
					});
			    });
			});

			$('#infoevol').on('click','.list-group-item .application', function(){
				appli= $(this).parent().attr('id');
				evol = $("#titlebranche").text();
				classAppli = $('#'+addslashes(appli)+" .data");
				if (classAppli.children().length>0) 
				{
					$('#'+addslashes(appli)+" .application").attr("class","glyphicon glyphicon-menu-down application" )
					classAppli.children().hide(200, function(){
						classAppli.children().remove();
					});					
				}
				else
				{
					$.ajax({
						method: "GET",
						url: "getBrancheByAppli",
						data: {nameEvol: evol,
								nameAppli : "/"+appli }
					}).then(function(data) {
						$('#'+addslashes(appli)+" .application").attr("class","glyphicon glyphicon-menu-up application" )
						classAppli.children().remove();
						classAppli.append('<br /><span class="blocInfo"></span>').hide;
						jQuery.each(data, function(i, index) {
							buttonRenommer=$("<button>").addClass("btn btn-primary").append("Renommer");
							buttonSupprimer=$("<button>").addClass("btn btn-primary").append("Supprimer")
							$('#'+addslashes(appli)+' .blocInfo')
							.append($("<a>").addClass("list-group-item")
							.append($("<b>").append("Nom :"))
							.append(index.name)
							.append("<br />")
							.append($("<b>").append("Version :"))
							.append(index.version)
							.append("<br/>")
							.append($("<b>").append("Action :"))
							.append("<br/> ")
							.append(buttonRenommer)
							.append(buttonSupprimer));

						});
						$('#'+addslashes(appli)+' .blocInfo').show(200);
					});
				}
			});
			
			function addslashes(ch) {
				ch = ch.replace(/\./g,"\\.")
				return ch
				}

