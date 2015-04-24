$( document ).ready(function() {
	    var cansancioRegular = $("#labelcansancioRegular").text();
		var resfrioRegular = $("#labelresfrioRegular").text();
		var dolorCabezaRegular = $("#labeldolorCabezaRegular").text();
		var gastricoRegular = $("#labelgastricoRegular").text();
		var operacion = $("#labeloperacion").text();
		var cirugia = $("#labelcirugia").text();
		var ejercicios = $("#labelejercicios").text();
		var alcohol = $("#labelalcohol").text();
		var medicamento = $("#labelmedicamento").text();
		//------------Editar Usuario
	    var usuActivo = $("#labelusuActivo").text();
		
	    if (cansancioRegular == 'true') {
			$(PrimeFaces.escapeClientId("datosClinicos:j_idt9:cansancioRegular")).
			removeClass("ui-flipswitch ui-shadow-inset ui-bar-inherit ui-corner-all" ).addClass("ui-flipswitch ui-shadow-inset ui-bar-inherit ui-corner-all ui-flipswitch-active" );
		}
	    if (resfrioRegular == 'true') {
			$(PrimeFaces.escapeClientId("datosClinicos:j_idt9:resfrioRegular")).
			removeClass("ui-flipswitch ui-shadow-inset ui-bar-inherit ui-corner-all" ).addClass("ui-flipswitch ui-shadow-inset ui-bar-inherit ui-corner-all ui-flipswitch-active" );
		}
		if (dolorCabezaRegular == 'true') {
			$(PrimeFaces.escapeClientId("datosClinicos:j_idt9:dolorCabezaRegular")).
			removeClass("ui-flipswitch ui-shadow-inset ui-bar-inherit ui-corner-all" ).addClass("ui-flipswitch ui-shadow-inset ui-bar-inherit ui-corner-all ui-flipswitch-active" );
		}
		if (gastricoRegular == 'true') {
			$(PrimeFaces.escapeClientId("datosClinicos:j_idt9:gastricoRegular")).
			removeClass("ui-flipswitch ui-shadow-inset ui-bar-inherit ui-corner-all" ).addClass("ui-flipswitch ui-shadow-inset ui-bar-inherit ui-corner-all ui-flipswitch-active" );
		}
		if (gastricoRegular == 'true') {
			$(PrimeFaces.escapeClientId("datosClinicos:j_idt9:gastricoRegular")).
			removeClass("ui-flipswitch ui-shadow-inset ui-bar-inherit ui-corner-all" ).addClass("ui-flipswitch ui-shadow-inset ui-bar-inherit ui-corner-all ui-flipswitch-active" );
		}
		if (operacion == 'true') {
			$(PrimeFaces.escapeClientId("datosClinicos:j_idt9:operacion")).
			removeClass("ui-flipswitch ui-shadow-inset ui-bar-inherit ui-corner-all" ).addClass("ui-flipswitch ui-shadow-inset ui-bar-inherit ui-corner-all ui-flipswitch-active" );
		}
		if (cirugia == 'true') {
			$(PrimeFaces.escapeClientId("datosClinicos:j_idt9:cirugia")).
			removeClass("ui-flipswitch ui-shadow-inset ui-bar-inherit ui-corner-all" ).addClass("ui-flipswitch ui-shadow-inset ui-bar-inherit ui-corner-all ui-flipswitch-active" );
		}
		if (ejercicios == 'true') {
			$(PrimeFaces.escapeClientId("datosClinicos:j_idt9:ejercicios")).
			removeClass("ui-flipswitch ui-shadow-inset ui-bar-inherit ui-corner-all" ).addClass("ui-flipswitch ui-shadow-inset ui-bar-inherit ui-corner-all ui-flipswitch-active" );
		}
		if (alcohol == 'true') {
			$(PrimeFaces.escapeClientId("datosClinicos:j_idt9:alcohol")).
			removeClass("ui-flipswitch ui-shadow-inset ui-bar-inherit ui-corner-all" ).addClass("ui-flipswitch ui-shadow-inset ui-bar-inherit ui-corner-all ui-flipswitch-active" );
		}
		if (medicamento == 'true') {
			$(PrimeFaces.escapeClientId("datosClinicos:j_idt9:medicamento")).
			removeClass("ui-flipswitch ui-shadow-inset ui-bar-inherit ui-corner-all" ).addClass("ui-flipswitch ui-shadow-inset ui-bar-inherit ui-corner-all ui-flipswitch-active" );
		}
		
		//----------------Editar Usuario 
	    if (usuActivo == 'true') {
			$(PrimeFaces.escapeClientId("editarUsuario:formEditarUsuario:usuActivo")).
			removeClass("ui-flipswitch ui-shadow-inset ui-bar-inherit ui-corner-all" ).addClass("ui-flipswitch ui-shadow-inset ui-bar-inherit ui-corner-all ui-flipswitch-active" );
		}
	});