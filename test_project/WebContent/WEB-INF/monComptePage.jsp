<%@ include file="header.jsp"%>
<div class="page">
	<%@ include file="menu.jsp"%>

	<div class="corpsMinHeight" align="center">
		<h1 class="titrePage">Mon Compte</h1>

		<c:if test="${!empty sessionScope.sessionUtilisateur }">

			<p style="color: green;">${sessionScope.messageOkSession}</p>
			<p>Vous êtes connecté avec l'adresse :
				${sessionScope.sessionUtilisateur.email}</p>
			<p>Votre Pseudo est: ${sessionScope.sessionUtilisateur.pseudo}</p>
			<p>
				Votre inscription a été enregistré le:
				<fmt:formatDate type="date"
					value="${sessionScope.sessionUtilisateur.dateInscription}" />
				à
				<fmt:formatDate type="time"
					value="${sessionScope.sessionUtilisateur.dateInscription}" />
			</p>


			<p>Vous vous êtes connecté:
				${sessionScope.sessionUtilisateur.nbConnexion} fois</p>
			<p>
				derniere connexion le:
				<fmt:formatDate type="date"
					value="${sessionScope.sessionUtilisateur.dateConnexion}" />
				à
				<fmt:formatDate type="time"
					value="${sessionScope.sessionUtilisateur.dateConnexion}" />
			</p>
			<br>
			<div class="pt-5">
				<ul>
					<li class=""><a class="logo neonRouge" href="./logout">DECONNEXION</a></li>
				</ul>
			</div>

			<br>
			<h1>https://www.youtube.com/watch?v=Td5VHM5a0Ws</h1>
			<h1>verif ajax user in db</h1>

			<h1>Show menu and submenu on homepage from database using
				servlet and jsp</h1>
			<h1>https://coderanch.com/t/582837/java/Show-menu-submenu-homepage-database</h1>
			<h1>
				https://www.javaguides.net/2019/03/jsp-servlet-jdbc-mysql-crud-example-tutorial.html
			</h1>




		</c:if>
	</div>


	<%@ include file="footer.jsp"%>

</div>