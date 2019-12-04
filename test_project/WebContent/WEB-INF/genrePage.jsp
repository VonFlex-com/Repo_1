<%@ include file="header.jsp"%>

<div class="page">

	<%@ include file="menu.jsp"%>

	<div class="corpsMinHeight" align="center">

		<h1 class="titrePage">Genres</h1>

		<ul class="textCenter">

			<c:forEach var="genre" items="${listGenre}">

				<li><a class="neonRouge" href="${genre.nom}">${genre.nom}</a></li>

			</c:forEach>

		</ul>

	</div>

	<%@ include file="footer.jsp"%>

</div>
