<%@ include file="header.jsp"%>
<div class="page">
	<!--  <%@ include file="menu.jsp"%>-->

	<div class="corpsMinHeight borderRed" align="center">
		<h1 class="titrePage">Lecture</h1>

		<div class="divRetourAccueil">
			<div class=" stick ">
				<p class="barreInfoLecture " style="float: left;">
					Vous lisez:
					<c:forEach var="bdImage" items="${listImage}" begin="0" end="0">
						<c:out value="${bdImage.serie}, ${bdImage.titre}"></c:out>
					</c:forEach>
				<p class="barreInfoLecture ">

					Utiliser Ctrl + molette de la souris pour ajuster la taille de
					l'image.
					<!--
					solution future...
					format: <a class="tailleLecture neonRouge grand"><input
						class="grand" type="hidden">grand</input></a><a
						class="tailleLecture neonRouge petit"><input class="petit"
						type="hidden">petit</input></a>   -->
				</p>
				<p class="barreInfoLecture " style="float: right;">
					Retour à l'accueil <a href="./accueil"
						class="retourAccueil tailleLecture neonRouge"> <input
						type="hidden">X
					</a>
				</p>
			</div>
			<br>

			<c:forEach var="bdImage" items="${listImage}">
				<div>

					<img class="images borderRed"
						src="data:image/jpg;base64, ${bdImage.bdImage}">
				</div>
			</c:forEach>


		</div>

	</div>

</div>
<%@ include file="footer.jsp"%>
