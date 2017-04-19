$(document).ready()
{	
	loadMergeTable(false);
}

$('#bodyMerge').on('click',".changeFait", function(){
	objet = $(this);
	changeFait(objet, "merge");
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
	
	function loadMergeTable(all)
	 {
		$('.tableMerge').find("#bodyMerge").empty();
			$.ajax({
				method : "POST",
				url : "getAllMerge",
				data : {all : all}
			}).done(function(data) {
				jQuery.each(data, function(i, index) {
					addLineMerge(index.id, index.version1, index.version2,index.fait, index.date, index.personne, index.commentaire)
				});
			});
	 }
	

	function addLineMerge(id, version1, version2, fait, date, personne, commentaire) {
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
		button = " <button type=\"button\" class=\"btn btn-primary infoLine\"><span class=\"glyphicon glyphicon-info-sign\"></span></button></td>" 
		line = $("<tr class=\""+color+"\">").attr("data-id",id).append($("<td>").attr("data-id",id).append(version1))
				.append($("<td>").append(version2)).append($("<td>").attr("id","date").append(date))
				.append($("<td>").append(personne))
				.append($("<td>").append(boutonFait));
		if(commentaire!=null)
		{
			line.append($("<td>").append(button));
		}
		else
		{
			line.append($("<td>"));
		}
		line.append($("<div>").attr("class", "popupinformation").prop('title', 'Information complémentaire : ').attr("data-id", id).append(commentaire));
		typedialog(line.find(".popupinformation"));
		test = $('.tableMerge').find("#bodyMerge").append(line);

	}
	

