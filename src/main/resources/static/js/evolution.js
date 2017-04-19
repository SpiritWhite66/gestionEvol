$(document).ready()
{	
	loadEvolutionTable();
	
	$('.addLineEvolution').on('click', function() {
		saveLineEvolution();
	});
	
	$('#bodyEvolution').on('click', ".removeLineEvolution", function(){
		id=$(this).parent().parent().attr("data-id");
		removeLineEvolution(id);
	})
}



	
	function loadEvolutionTable()
	 {
		$('.tableEvolution').find("#bodyEvolution").empty();
			$.ajax({
				method : "POST",
				url : "GetAllEvolution",
			}).done(function(data) {
				jQuery.each(data, function(i, index) {
					addLineEvolution(index.id, index.nom,index.debut, index.fin,index.chef_projet, index.ref_technique);
				});
			});
	 }
	
	function addLineEvolution(id, nom, debut, fin, cp, rt) {

		button = " <button type=\"button\" class=\"btn btn-danger removeLineEvolution\"><span class=\"glyphicon glyphicon-remove\"></span></button></td>" 
		line = $("<tr>").attr("data-id", id).append($("<td>").append(nom))
				.append($("<td>").attr("id","datedebut").append(debut))
				.append($("<td>").attr("id","datefin").append(fin))
				.append($("<td>").append(cp))
				.append($("<td>").append(rt))
				.append($("<td>").append(button))
		test = $('.tableEvolution').find("#bodyEvolution").append(line);

	}
	
	function removeLineEvolution(id)
	{
		$.ajax({
			method : "POST",
			url : "RemoveEvolutionLine",
			data : {
				id : id
			}
		}).done(function(data) {
			hideLineEvolution(data);
		});
	}
	
	function hideLineEvolution(id)
	{
		$("#bodyEvolution").find("[data-id="+id+"]").hide(500);
	}
	
	function saveLineEvolution() {
		nom = $('.tableEvolution').find('#evolution').val();
		debut = $('.tableEvolution').find('#debut').val();
		fin = $('.tableEvolution').find('#fin').val();
		cp = $('.tableEvolution').find('#chefprojet').val();
		rt = $('.tableEvolution').find('#reftechnique').val();

		$.ajax({
			method : "POST",
			url : "SaveEvolutionLine",
			data : {
				nom : nom,
				debut : debut,
				fin : fin,
				chef_projet : cp,
				ref_technique : rt
			}
		}).done(function(data) {
			addLineEvolution(data, nom, debut, fin, cp, rt)
		});
	}
	
	
