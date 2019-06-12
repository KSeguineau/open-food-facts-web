<%@page import="model.Produit"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="utf8"%>
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
<link rel='stylesheet'
	href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css' />
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

	<table class="table">
		<thead class="thead">
			<tr>
				<th scope="col">nom</th>
				<th scope="col">catégorie</th>
				<th scope="col">marque</th>
				<th scope="col">grade nutritionnel</th>
				<th scope="col">energie</th>
				<th scope="col">graisse</th>
			</tr>
		</thead>
		<tbody>
			<%
				List<Produit> listeProduit = (List<Produit>) request.getAttribute("listeProduit");
			%>
			<%
				for (Produit produit : listeProduit) {
			%>
			<tr>
				<td><%=produit.getNom()%><button
						class="btn noHover btn-info px-1 py-0 ml-3">
						<i data-toggle="modal" data-target="#exampleModal"
							data-id="<%=produit.getId()%>"
							class="boutonIngredient fas fa-carrot"></i>
					</button></td>
				<td><%=produit.getCategorie()%></td>
				<td><%=produit.getMarque()%></td>
				<td><%=produit.getGrade()%></td>
				<td><%=produit.getEnergie()%></td>
				<td><%=produit.getGraisse()%></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
	</main>



<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Liste d'ingrédients</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div id="contenuModal"></div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>


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
		let boutonIngredients = document.getElementsByClassName("boutonIngredient");
		for(let bouton of boutonIngredients){
		bouton.addEventListener("click", (event) => {
			const produit = event.target.getAttribute('data-id');
			$.ajax({
		        url: "/open/ingredients?idProduit="+produit,
		        dataType: "json",
		        success: (result) => {
		           let html = '';
		            
		            result.forEach(ingredient => {
		            	html +="<p>"+ingredient+"</p>";   
		            	
		            });
		       
		            $("#contenuModal").html(html);
		        }
		    });
			
			
			});
		}
	</script>
</body>

</html>