
$(document).ready()
{	
    $( "#infoevol" ).on('click', '.renommer',function() {
        $("#popupaddrenomme .modal-dialog .modal-content .modal-body #src").val($(this).data("branche"));
    });
    $( "#infoevol" ).on('click', '.supprimer',function() {
        $("#popupaddsupprime .modal-dialog .modal-content .modal-body #src").val($(this).data("branche"));
    });
    $( "#saveRenomme" ).on('click',function() {
        src = $("#popupaddrenomme .modal-dialog .modal-content .modal-body #src").val();
    	dest = $("#popupaddrenomme .modal-dialog .modal-content .modal-body #dest").val();
    	date = $("#popupaddrenomme .modal-dialog .modal-content .modal-body #date").val();
    	personne = $("#popupaddrenomme .modal-dialog .modal-content .modal-body #personne").val();
    	saveLineRenomme(src, dest, date, personne)
    	});


}

function saveLineRenomme(src, dest, date, personne) {
	$.ajax({
		method : "POST",
		url : "SaveRenommeBrancheLine",
		data : {
			src : src,
			dest : dest,
			personne : personne,
			date : date,
		}
	});
}
function saveLineSupprime(src, date, personne) {
	$.ajax({
		method : "POST",
		url : "SaveSupprimeBrancheLine",
		data : {
			src : src,
			personne : personne,
			date : date,
		}
	});
}
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
						listActionRenomme = data.listActionRenomme;
						listActionSupprime = data.listActionSupprime;
						
						jQuery.each(data.branches, function(i, index) {
							
							buttonRenommer=$("<button>").addClass("btn btn-primary renommer ").data("branche",index.name ).attr("data-toggle", "modal").attr("data-target", "#popupaddrenomme").append("Renommer");
							buttonSupprimer=$("<button>").addClass("btn btn-danger supprimer ").data("branche",index.name ).attr("data-toggle", "modal").attr("data-target", "#popupaddsupprime").append("Supprimer");
							objetAction = $("<div>").addClass("row").append($("<h5>").append("Action :"));
							jQuery.each(listActionRenomme[i], function(i, action) {
								objetActionTmp=$("<span>").addClass("list-group-item").append("Renommage prévue, le "+action.date);
								objetAction.append(objetActionTmp);
							});
							jQuery.each(listActionSupprime[i], function(i, action) {
								objetActionTmp=$("<span>").addClass("list-group-item").append("Suppression prévue, le "+action.date);
								objetAction.append(objetActionTmp);
							});
							
							$('#'+addslashes(appli)+' .blocInfo')
							.append($("<a>").addClass("list-group-item")
							.append($("<b>").append("Nom :"))
							.append(index.name)
							.append("<br />")
							.append($("<b>").append("Version :"))
							.append(index.version)
							.append("<br/>")
							.append(objetAction)
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

