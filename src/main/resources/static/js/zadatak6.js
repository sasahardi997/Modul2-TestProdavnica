$(document).ready(function(){
	console.log("povezano")
	
	$("form").find("input[type=checkbox]").change(function(){
		var span=$("span").eq(0)
		$("table.tabela").slideToggle()
			if(span.text() == "Prikazi"){
				span.text("Sakrij")
			} else {
				span.text( "Prikazi")
			}
	})
	
	$("form").submit(function(){
		var forma = $(this)
	
		var proizvodjac = $("#proizvodjac").find(":selected").text()
		var model = forma.find("input[name=model]").val()
		var cena = forma.find("input[name=cena]").val()
		var dostupnost = forma.find("input[type=radio]:checked").val()
		
		if(proizvodjac == "" || model == "" || cena == "" || dostupnost == null){
			alert("Nisu unete sve vrenosti!")
			return false
		}
		if(cena < 1){
			alert("Cena komponente ne sme biti manja od 1RSD")
			return false
		}
		if(model.length > 30 || !(model.match("^[a-zA-Z0-9]+$") != null)){
			alert("Model ne sme da sadrzi vise od 30 karaktera i karaktera koji nisu alfanumericki!");
			return false
		}
		
		return true;
	})
})