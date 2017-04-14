$(document).ready()
{	
	loadHistoriqueTable();
}

function loadHistoriqueTable()
	 {
		$('.tableHistorique').find("#bodyHistorique").empty();
			$.ajax({
				method : "POST",
				url : "getAllHistorique"
				}).done(function(data) {
					
				jQuery.each(data, function(i, index) {
					addLineHistorique(index.id, index.type.libelle, index.merge.id, index.date, index.qui)
				});
			});
	 }

function addLineHistorique(id, libelle, merge_id, date, personne) {
	
	line = $("<tr>").append($("<td>").append(id))
			.append($("<td>").append(libelle))
			.append($("<td>").append(merge_id))
			.append($("<td>").attr("id","date").append(date))
			.append($("<td>").append(personne))
	test = $('.tableHistorique').find("#bodyHistorique").append(line);

}