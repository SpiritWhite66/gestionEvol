$(document).ready()
{	
	loadMergeTable(false);
	
	$('.addLine').on('click', function() {
		saveLineRenommeBranche();
	});
	
	$('#bodyTable').on('click',".changeFait", function(){
		objet = $(this);
		changeFait(objet, "supprimeBranche");
	});
}	

function loadMergeTable(all)
	 {
		$('.tableData').find("#bodyTable").empty();
			$.ajax({
				method : "POST",
				url : "GetAllSupprimeBranche",
				data : {all : all}
			}).done(function(data) {
				jQuery.each(data, function(i, index) {
					addLineRenomme(index.id, index.src,index.fait, index.date, index.personne)
				});
			});
	 }

function addLineRenomme(id, version1, fait, date, personne) {
	color = chooseColor(date);
	if(fait)
		{
		color = "success";
		etat = "success";
		glyphicon ="glyphicon-ok";
		}
	else
		{
		glyphicon ="";
		etat= "danger";
		}
	boutonFait = " <button type=\"button\" class=\"btn btn-"+etat+" changeFait\"><span class=\"glyphicon "+glyphicon+"\"> </span></button></td>";
	button = " <button type=\"button\" class=\"btn btn-primary infoLineMerge\"><span class=\"glyphicon glyphicon-info-sign\"></span></button></td>" 
	line = $("<tr class=\""+color+"\">").attr("data-id",id).append($("<td>").attr("data-id",id).append(version1))
			.append($("<td>").attr("id","date").append(date))
			.append($("<td>").append(personne))
			.append($("<td>").append(boutonFait))
			.append($("<td>").append(button));
	test = $('.tableData').find("#bodyTable").append(line);

}

function saveLineRenommeBranche() {
	src = $('.tableData').find('#src').val();
	personne = $('.tableData').find('#personne').val();
	date = $('.tableData').find('#date').val();

	$.ajax({
		method : "POST",
		url : "SaveSupprimeBrancheLine",
		data : {
			src : src,
			personne : personne,
			date : date,
		}
	}).done(function(data) {
		alert(data);
		addLineRenomme(data, src, false, date, personne)
	});
}