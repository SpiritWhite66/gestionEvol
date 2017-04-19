$(document).ready()
{	
	loadMergeTable(false);
	$('.addLine').on('click', function() {
		saveLineRenommeBranche();
	});
	
	$('#bodyTable').on('click',".changeFait", function(){
		objet = $(this);
		changeFait(objet, "renommeBranche");
	});
}	

function loadMergeTable(all)
	 {
		$('.tableData').find("#bodyTable").empty();
			$.ajax({
				method : "POST",
				url : "GetAllRenommeBranche",
				data : {all : all}
			}).done(function(data) {
				jQuery.each(data, function(i, index) {
					addLineRenomme(index.id, index.src, index.dest,index.fait, index.date, index.personne)
				});
			});
	 }

function addLineRenomme(id, version1, version2, fait, date, personne) {
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
			.append($("<td>").append(version2)).append($("<td>").attr("id","date").append(date))
			.append($("<td>").append(personne))
			.append($("<td>").append(boutonFait))
			.append($("<td>").append(button));
	test = $('.tableData').find("#bodyTable").append(line);

}

function saveLineRenommeBranche() {
	src = $('.tableData').find('#src').val();
	dest = $('.tableData').find('#dest').val();
	personne = $('.tableData').find('#personne').val();
	date = $('.tableData').find('#date').val();

	$.ajax({
		method : "POST",
		url : "SaveRenommeBrancheLine",
		data : {
			src : src,
			dest : dest,
			personne : personne,
			date : date,
		}
	}).done(function(data) {
		addLineRenomme(data, src, dest, false, date, personne)
	});
}