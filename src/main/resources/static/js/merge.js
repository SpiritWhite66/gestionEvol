$(document).ready()
{	
	loadMergeTable(false);
}

$('#bodyMerge').on('click',".changeFait", function(){
	objet = $(this);
	changeFait(objet);
});

$('#allButton').on('click', function(){
	changeMode();
	mode=false;
	if(getMode()=="all")
		{
		mode=true;
		}
	loadMergeTable(mode);
});

	function changeMode()
	{
		if($(".tableMerge ").attr("data-mode")=="all")
			{
			$(".tableMerge ").attr("data-mode", "");
			$("#allButton").text("Non Fait").show(300);
			}
		else
			{
			$(".tableMerge ").attr("data-mode","all");
			$("#allButton").text("Tout").show(300);
			}
	}
	function getMode()
	{
		return $(".tableMerge ").attr("data-mode");
	}
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
				objet.find(".glyphicon").addClass("glyphicon-ok");
				objetbutton.parent().attr("class", "success");
				if(getMode()=="")
					{
					objetbutton.parent().hide(1000);
					}
				}
			else
				{
				objet.removeClass("btn-success");
				objet.addClass("btn-danger");
				objet.find(".glyphicon").removeClass("glyphicon-ok");
				objet.find(".glyphicon").removeClass("glyphicon-ok");
				color = chooseColor(objetbutton.parent().find("#date").text());
				objetbutton.parent().attr("class", color );
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
	
	function chooseColor(date)
	{
		color ="";
		
		dateMerge = new Date();
		dateMerge = new Date(date);
		
		today = Date.now();
		
		before = new Date(dateMerge);
		before.setDate(dateMerge.getDate() - 1);
		
		after = new Date(dateMerge);
		after.setDate(dateMerge.getDate() + 1);
		
		if((today>after.getTime()))
			{
				color = "danger";	
			}

		else if((before.getTime()<today)&&(today<after.getTime()))
			{
				color = "warning";
			}
		return color 
	};
	
	function addLineMerge(id, version1, version2, fait, date, personne) {
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
		test = $('.tableMerge').find("#bodyMerge").append(line);

	}
	
	
