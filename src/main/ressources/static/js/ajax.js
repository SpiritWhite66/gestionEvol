			$( ".test" ).on('click',function() {
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
						$("#infoevol").append('<a class="list-group-item application" id="'+name+'">'+index.name+'</a>');
					});
			    });
			});

			$('#infoevol').on('click','.application', function(){
				appli= $(this).attr('id');
				evol = $("#titlebranche").text();
				classAppli = $('#'+appli);
				if (classAppli.children().length>0) 
				{
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
						classAppli.children().remove();
						classAppli.append('<br /><span class="blocInfo"></span>').hide;
						jQuery.each(data, function(i, index) {
							$('#'+appli+' .blocInfo').append('<a class="list-group-item"> <b>Nom :</b> '+index.name+'<br /><b>Version :</b> '+index.version+'</a>').hide();
						});
						$('#'+appli+' .blocInfo').show(200);
					});
				}
				
			});