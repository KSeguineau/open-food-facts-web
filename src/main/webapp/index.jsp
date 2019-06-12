<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="utf8" %>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Open Food Fact Search</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">


</head>

<body class="text-info">

	<header>
		<nav class="navbar navbar-expand-lg navbar-dark bg-info">
			<a class="navbar-brand" href="/open/recherche">Open Food Fact Search</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item active"><a class="nav-link"
						href="/open/recherche">Recherche de produit <span
							class="sr-only">(current)</span></a></li>
					<li class="nav-item"><a class="nav-link" href="#">Ajout de
							produit</a></li>
			</div>
		</nav>
	</header>

	<main class="text-center container w-50">
	<div class="row-5">
		<h1 class=" py-4">Recherche de produit</h1>
	</div>

	<form method="POST" action="<%=request.getContextPath()%>/recherche">

		<div class="form-group">
			<label>Catégorie:</label> <select name="categorie" class="form-control" id="selectCategorie">
				<option></option>
				<%
					List<String> listeCategorie =(List<String>) request.getAttribute("listeCategorie");
					for (String categorie : listeCategorie) {
				%>
				<option><%=categorie%></option>
				<%
					}
				%>
			</select>
		</div>
		
		<div class="form-group">
			<label>Marque:</label> 
			<select name="marque" id="selectMarque" class="form-control">
				
				
			</select>
		</div>
		
	
		<div class="row-8">
			<div class="form-group">
				<p class="text-left">Nom de produit:</p>
				<input type="text" class="form-control" name="nom"
					placeholder="Entrez un nom de produit">

			</div>
		</div>
		<div class="row-8">
			<div class="form-group">
				<p class="text-left">Grade nutritionnel:</p>
				<input type="text" class="form-control" name="grade"
					placeholder="Entrez un grade nutritionnel">

			</div>
		</div>

		<div class="row-8">
			<div class="form-group">
				<p class="text-left row-8">Energie:</p>
				<div class="row">
					<div class="col">
						<label>min: </label> <input class="form-control" type="number"
							name="minEnergie" placeholder="min">

					</div>

					<div class="col">
						<label>max: </label> <input class="form-control" type="number"
							name="maxEnergie" placeholder="max">


					</div>
				</div>

				<div class="row-8">
					<div class="form-group">
						<p class="text-left row-8 mt-3">Graisse:</p>
						<div class="row">
							<div class="col">
								<label>min: </label> <input class="form-control" type="number"
									name="minGraisse" placeholder="min">

							</div>

							<div class="col">
								<label>max: </label> <input class="form-control" type="number"
									name="maxGraisse" placeholder="max">


							</div>
						</div>



					</div>
					<div class="row-8">
						<button type="submit" class="btn btn-primary">Rechercher</button>
					</div>
				</div>
	</form>


	</main>




	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js">
		
	</script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous">
		
	</script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous">
		
	</script>
	<script>
	$("#selectCategorie").change(function(){
		
		    $.ajax({
		        url: "/open/refresh?nomCategorie="+document.getElementById('selectCategorie').value,
		        dataType: "json",
		        success: (result) => {
		            let html = '<select name="marque" id="selectMarque" class="form-control">';
		            html+="<option></otpion>"
		            result.forEach(marque => {
		            	html +="<option>"+marque+"</option>";      
		            });
		            html+="</select>";
		            $("#selectMarque").html(html);
		        }
		    });
		
		
	})
	</script>

</body>

</html>