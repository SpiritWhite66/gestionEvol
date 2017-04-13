$(document).ready()
{
	arrayTable = new Array();

	$('.drag').draggable({
		snap : '.drop',
		snapMode : "inner",
		cursor : "pointer",
		revert : true,
		zIndex : 100
	}); // ce bloc sera déplaçable

	$('.drop').droppable({
		accept : '.drag',
		drop : function(event, ui) {
			$(this).html(ui.draggable.text());
			alert(ui.draggable.text());
		}
	}); // ce bloc servira de zone de dépôt

	$('.addLineMerge').on('click', function() {

		version1 = $('.tableMerge').find('#v1').text();
		version2 = $('.tableMerge').find('#v2').text();
		date = $('.tableMerge').find('#dtp_input1').attr("value");
		personne = $('.tableMerge').find('#personne').val();
		if (verifLigneData() && verifNoDoublon()) {
			$.ajax({
				method : "POST",
				url : "saveMergeLine",
				data : {
					version1 : version1,
					version2 : version2,
					date : date,
					personne : personne
				}
			}).done(function() {
				addLineMerge(version1, version2, date, personne)
			});
		}

	});

	function addLineMerge(version1, version2, date, personne) {

		button = "<button type=\"button\" class=\"btn btn-danger removeLineMerge\"><span class=\"glyphicon glyphicon-remove\"></span></button></td>"
				+ " <button type=\"button\" class=\"btn btn-primary infoLineMerge\"><span class=\"glyphicon glyphicon-info-sign\"></span></button></td>";
		line = $("<tr class=\"success\">").append($("<td>").append(version1))
				.append($("<td>").append(version2)).append(
						$("<td>").append(date)).append(
						$("<td>").append(personne)).append(
						$("<td>").append(button));
		test = $('.tableMerge').find("#bodyMerge").append(line);
		setTimeout(function() {
			$('.tableMerge').find("#bodyMerge tr:last-child").removeClass(
					"success");
		}, 3000);
		var lineData = {
			v1 : version1,
			v2 : version2,
			date : date
		};
		arrayTable.push(lineData);
		clearInput();
	}

	$('.clearLineMerge').on('click', function() {
		clearInput();
	});

	$('#bodyMerge').on('click', '.removeLineMerge', function() {
		$(this).parent().parent().remove();
	});

	function verifNoDoublon() {
		version1 = $('.tableMerge').find('#v1').text();
		version2 = $('.tableMerge').find('#v2').text();
		date = $('.tableMerge').find('#dtp_input1').attr("value");
		result = arrayTable.filter(function(index) {
			return index.v1 == version1 && index.v2 == version2
					&& index.date == date;
		}).length;
		if (result > 0) {
			alert("Une ligne ayant les mêmes données existe déjà.")
			return false;
		} else {
			return true;
		}

	}
	function verifLigneData() {
		version1 = $('.tableMerge').find('#v1').text();
		version2 = $('.tableMerge').find('#v2').text();
		date = $('.tableMerge').find('#dtp_input1').attr("value");
		if (version1 != "Vide" && version2 != "Vide" && date != "") {
			return true;
		} else {
			return false;
		}
	}
	function clearInput() {
		version1 = $('.tableMerge').find('#v1 .drop').text("Vide");
		version2 = $('.tableMerge').find('#v2 .drop').text("Vide");
		date = $('.tableMerge').find('#dtp_input1').text("");
	}

}
