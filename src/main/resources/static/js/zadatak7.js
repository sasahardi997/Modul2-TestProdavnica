var baseURL = ""

function popuniBaseURL() {
	$.get("baseURL", function(odgovor) {
		console.log(odgovor)

		if (odgovor.status == "ok") {
			baseURL = odgovor.baseURL 
			$("base").attr("href", baseURL) 
		}
	})
	console.log("GET: " + "baseURL")
}

function popuniProizvodjace(){
	$.get("Proizvodjaci", function(odgovor){
		console.log(odgovor)
		
		if(odgovor.status == "ok"){
			var proizvodjaci = odgovor.proizvodjaci
			var option = ''
			
			var tabela = $("table.tabela")
			tabela.find("tr:gt(1)").remove()
			for(var it in proizvodjaci){
				tabela.append(
					'<tr>' +
						'<td>'+ (parseInt(it) + 1) +'</td>' +
						'<td>'+ proizvodjaci[it].naziv +'</td>' +
						'<td>'+ proizvodjaci[it].sediste +'</td>' +
					'</tr>'
				)
				option += '<option value="' + (parseInt(it) + 1) + '">' + proizvodjaci[it].naziv + '</option>';
			}
			$("#select").append(option)
			console.log(option)
		}
	})
	console.log("GET: Proizvodjaci")
}

function izmeniProizvodjaca(){
	var id = $("#select").find(":selected").val()
	var naziv = $("input[name=naziv]").val()
	var sediste = $("input[name=sediste]").val()
	
	var params = {
		id : parseInt(id),
		naziv : naziv,
		sediste : sediste
	}
	console.log(params)
	
	$.post("Proizvodjaci/Edit",params, function(odgovor){
		if(odgovor.status == "greska"){
			window.location.replace("zadatak7.html")
		} else if(odgovor.status == "ok"){
			$.get("Proizvodjaci", function(odgovor){
				var proizvodjaci = odgovor.proizvodjaci
				var option = ''
				$("#select").find('option').remove()
				console.log(proizvodjaci)
				
				var tabela = $("table.tabela")
				tabela.find("tr:gt(0)").remove()
				for(var it in proizvodjaci){
					tabela.append(
						'<tr>' +
							'<td>'+ (parseInt(it) + 1) +'</td>' +
							'<td>'+ proizvodjaci[it].naziv +'</td>' +
							'<td>'+ proizvodjaci[it].sediste +'</td>' +
						'</tr>'
					)
					option += '<option value="' + (parseInt(it) + 1) + '">' + proizvodjaci[it].naziv + '</option>';
				}
				$("#select").append(option)
				console.log(option)
			})
		}
	})
}

$(document).ready(function(){
	popuniBaseURL()
	popuniProizvodjace()
	
	$("form").submit(function(){
		izmeniProizvodjaca()
		return false
	})
})