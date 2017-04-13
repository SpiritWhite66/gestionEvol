			$( ".loupe" ).on('click',function() {
				nameAppli = $(this).attr('id');
			  	$.ajax({
					method: "GET",
					url: "getBrancheSVN",
					data: {name: nameAppli}
			    }).then(function(data) {
			    	$('#titlebranche').text(nameAppli);
			    	$("#infoevol").children().remove();
			    	$("#infoevol").append('<a class="list-group-item active">Application</a>');
			    	jQuery.each(data, function(i, index) {
			    		name=index.name.replace("/", "");
						$("#infoevol").append('<li class="list-group-item" id="'+name+'">'+index.name+'</li>');
					});
			    });
			});