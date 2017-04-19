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
	}
	
	function changeFait(objet, action)
	{
		id=objet.parent().parent().attr("data-id");
		$.ajax({
			method : "POST",
			url : "ChangeEtatFait/"+action+"",
			data: {id: id}
		}).done(function(data) {
			objetbutton = objet.parent();
			if(objet.hasClass("btn-danger"))
				{
				objet.removeClass("btn-danger");
				objet.addClass("btn-success");
				objet.find(".glyphicon").addClass("glyphicon-ok");
				objetbutton.parent().attr("class", "success");
				if(action=="merge" && getMode()=="")
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
	
    $("#popupaddinformation").dialog({
        autoOpen: false,
        width: 400,
        buttons: [
            {
                text: "Enregistrer",
                click: function() {
                    $( this ).dialog( "close" );
                }
            }, 	           
			{
                text: "Annuler",
                click: function() {
                    $( this ).dialog( "close" );
                }
            }
        ]
    });
 
    // comportement du bouton devant ouvrir la boîte de dialogue
    $( ".addInfoLine" ).click(function( event ) {
        // cette ligne est très importante pour empêcher les liens ou les boutons de rediriger
        // vers une autre page avant que l'usager ait cliqué dans le popup
        event.preventDefault();
        // affichage du popup
        $( "#popupaddinformation" ).dialog( "open" );
    });
    
     
    // comportement du bouton devant ouvrir la boîte de dialogue
    $( "#bodyMerge" ).on("click", ".infoLine", function( event ) {
        // cette ligne est très importante pour empêcher les liens ou les boutons de rediriger
        // vers une autre page avant que l'usager ait cliqué dans le popup
        event.preventDefault();
        // affichage du popup
        id = $(this).parent().parent().attr("data-id");
        $('.popupinformation[data-id='+id+']').dialog("open");
    });
    
	function typedialog(data)
	{
		data.dialog({ 
			autoOpen: false,
    		width : 400,
    		buttons: [{ 
    			text: "Ok", 
                "class": 'btn',
    			click: function() { 
    	        $(this).dialog( "close" ); 
    	      }
    		}]
    	   });
	}
    
    $(document).ready()
    {	
	   		$('.ListFile').empty();
	   			$.ajax({
	   				method : "POST",
	   				url : "getAllFile"
	   			}).done(function(data) {
	   				jQuery.each(data, function(i, index) {
	   					$('.ListFile').append($("<option>").append(index));
	   				});
	   		});
	   			
	   		$(".ListFile").change(function() {
	   			nameFile = $( ".ListFile option:selected" ).text();
	   			$.ajax({  
	   				type: "POST",
					url : "setFile",
	   				data : {nameFile: nameFile }
	   			   }).done(function(){
	   				     document.location.href="/";
	   			   })
	   		    });   			
    }

