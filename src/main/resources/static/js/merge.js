$(document).ready()
{	
	loadMergeTable();
}

$('#reloadButton').on('click', function(){
	loadMergeTable(false);
});

$('#bodyMerge').on('click',".changeFait", function(){
	objet = $(this);
	changeFait(objet);
});

$('#allButton').on('click', function(){
	loadMergeTable(true);
});

	function changeFait(objet)
	{
		id=objet.parent().parent().attr("data-id");
		$.ajax({
			method : "POST",
			url : "ChangeEtatFait",
			data: {id: id}
		}).done(function(data) {
			objetbutton = objet.parent();
			if(objet.hasClass("btn-danger"))
				{
				objet.removeClass("btn-danger");
				objet.addClass("btn-success");
				objet.find(".glyphicon").addClass("glyphicon-ok").removeClass("glyphicon-remove");
				objetbutton.parent().attr("class", "success");
				}
			else
				{
				objet.removeClass("btn-success");
				objet.addClass("btn-danger");
				objet.find(".glyphicon").addClass("glyphicon-remove").removeClass("glyphicon-ok");
				objetbutton.parent().attr("class", "");
				}
		});
	}
	
	function loadMergeTable(all)
	 {
		$('.tableMerge').find("#bodyMerge").empty();
			$.ajax({
				method : "POST",
				url : "getAllMerge",
				data : {all : all}
			}).done(function(data) {
				jQuery.each(data, function(i, index) {
					addLineMerge(index.id, index.version1, index.version2,index.fait, index.date, index.personne)
				});
			});
	 }
	
	function addLineMerge(id, version1, version2, fait, date, personne) {
		color ="";
		
		dateMerge = new Date();
		dateMerge = new Date(date);
		
		today = Date.now();
		
		before = new Date(dateMerge);
		before.setDate(dateMerge.getDate() - 1);
		
		after = new Date(dateMerge);
		after.setDate(dateMerge.getDate() + 1);
		
		alert(before.getTime()+"<"+today+"&&"+today+"<"+after.getTime());

		
		if((today>after.getTime()))
			{
				color = "danger";	
			}

		else if((before.getTime()<today)&&(today<after.getTime()))
			{
				color = "warning";
			}
		if(fait)
			{
			color = "success";
			etat = "success";
			glyphicon ="glyphicon-ok";
			}
		else
			{
			glyphicon ="glyphicon glyphicon-remove";
			etat= "danger";
			}
		boutonFait = " <button type=\"button\" class=\"btn btn-"+etat+" changeFait\"><span class=\"glyphicon "+glyphicon+"\"></span></button></td>";
		button = " <button type=\"button\" class=\"btn btn-primary infoLineMerge\"><span class=\"glyphicon glyphicon-info-sign\"></span></button></td>" 
			    +" <button type=\"button\" class=\"btn btn-danger removeLineMerge\"><span class=\"glyphicon glyphicon-trash\"></span></button>";
		line = $("<tr class=\""+color+"\">").attr("data-id",id).append($("<td>").attr("data-id",id).append(version1))
				.append($("<td>").append(version2)).append($("<td>").append(date))
				.append($("<td>").append(personne))
				.append($("<td>").append(boutonFait))
				.append($("<td>").append(button));
		test = $('.tableMerge').find("#bodyMerge").append(line);

	}
	
	
