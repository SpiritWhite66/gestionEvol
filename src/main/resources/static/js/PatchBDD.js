$(document).ready()
{	
	loadPatchTable();
}	

function loadPatchTable()
	 {
		$('.tableData').find("#bodyTable").empty();
			$.ajax({
				method : "GET",
				url : "GetPatch",
			}).done(function(data) {
				jQuery.each(data, function(i, index) {
					addLinePatch(index.name, index.application, index.date,index.auteur);
				});
			});
	 }

function addLinePatch(name, application, date, auteur) {
	line = $("<tr>").attr("data-name",name).append($("<td>").attr("data-name",name)
			.append(application))
			.append($("<td>").append(name))
			.append($("<td>").attr("id","date").append(date))
			.append($("<td>").append(auteur));
	$('.tableData').find("#bodyTable").append(line);

}
