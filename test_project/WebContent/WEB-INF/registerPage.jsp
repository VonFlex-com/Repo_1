<%@ include file="header.jsp"%>
<div class="page">
	<%@ include file="menu.jsp"%>

	<div class="corpsMinHeight" align="center">
		<h1 class="titrePage">Inscription</h1>
		<br>
		<div>
			Veuillez vous <strong>Inscrire</strong> <br> puis vous <strong>Connecter</strong>
			<br> pour acccéder au contenu privé
		</div>
		<br>
		<form action="<%=request.getContextPath()%>/register" method="post">
			<table style="with: 100%">
				<tr>
					<td>Email</td>
					<td><input type="text" name="email" required minlength="3" /></td>
				</tr>
				<tr>
					<td>Pseudo</td>
					<td><input type="text" name="pseudo" required minlength="3" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password1" required
						minlength="3" /></td>
				</tr>
				<tr>
					<td>Password confirmation</td>
					<td><input type="password" name="password2" required
						minlength="3" /></td>
				</tr>

			</table>
			<br> <input class=" logo ahref" type="submit" value="Submit" />
		</form>


		<c:if test="${ !empty regForm.messageErreur }">
			<p style="color: red;">
				<c:out value="${ regForm.messageErreur }" />
			</p>
		</c:if>
		<br>
		<c:if test="${ !empty regForm.messageOk }">
			<p style="color: green;">
				<c:out value="${ regForm.messageOk }" />
			</p>
			<br>
			<a href="./login" type="button" class="neonRouge logo">Connexion</a>
		</c:if>




	</div>

	<%@ include file="footer.jsp"%>
</div>