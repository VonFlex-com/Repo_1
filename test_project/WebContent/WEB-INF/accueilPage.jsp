<%@ include file="header.jsp"%>
<div class="page">
	<%@ include file="menu.jsp"%>

	<div class="corpsMinHeight borderRed" align="center">

		<h1 class="titrePage">Accueil</h1>

		<div class="containerVignette   borderRed">

			<div class="container borderRed">


				<div class="containerVignette   borderRed">

					<div class="container borderRed">

						<c:choose>
							<c:when test="${!empty sessionScope.sessionUtilisateur }">
								<c:forEach var="bdItemAccueil" items="${listBDItemAccueil}">

									<div class="tooltip">


										<form action="./lecture" method="post">

											<input type="hidden" name="param1"
												value="${bdItemAccueil.idBd}">
											<button type="submit" value="hidden" class="boutonVignette">

												<div class=" box  borderRed">

													<img class="imgVignette"
														src="data:image/jpg;base64, ${bdItemAccueil.vignette}">

													<div class="play">
														<i class=" fa fa-play-circle" aria-hidden="true"></i>
													</div>

													<div class="serie borderRed ">
														<div class="textBottom borderRed">
															<c:out value="${bdItemAccueil.serie}" />
														</div>
													</div>
												</div>

												<div class="tooltiptext ">
													<p class="titreTootips">Titre:</p>

													<p class="textTootips">
														<c:out value="${bdItemAccueil.serie}" />
													</p>
													<p class="textTootips">
														<c:out value="${bdItemAccueil.titre}" />
													</p>

													<p class="titreTootips">Résumé:</p>

													<p class="textResumeTootips">
														<c:out value="${bdItemAccueil.resume}" />
													</p>

													<p class="titreTootips">Auteur:</p>

													<p class="textTootips">
														<c:out
															value="${bdItemAccueil.auteurNom} ${bdItemAccueil.auteurPrenom}" />
													</p>

													<p class="titreTootips">Genre:</p>

													<p class="textTootips">
														<c:out value="${bdItemAccueil.genre}" />
													</p>

													<p class="titreTootips">Langue:</p>

													<p class="textTootips">
														<c:out value="${bdItemAccueil.langue}" />
													</p>

													<br> <a class="lienLecture neonRouge"><input
														type="hidden">Lecture</input></a>

												</div>
											</button>

										</form>
									</div>
								</c:forEach>
							</c:when>

							<c:otherwise>
								<c:forEach var="bdItemAccueil" items="${listBDItemAccueil}"
									begin="0" end="6">

									<div class="tooltip">


										<form action="./lecture" method="post">

											<input type="hidden" name="param1"
												value="${bdItemAccueil.idBd}">
											<button type="submit" value="hidden" class="boutonVignette">

												<div class=" box  borderRed">

													<img class="imgVignette"
														src="data:image/jpg;base64, ${bdItemAccueil.vignette}">

													<div class="play">
														<i class=" fa fa-play-circle" aria-hidden="true"></i>
													</div>

													<div class="serie borderRed ">
														<div class="textBottom borderRed">
															<c:out value="${bdItemAccueil.serie}" />
														</div>
													</div>
												</div>

												<div class="tooltiptext ">
													<p class="titreTootips">Titre:</p>

													<p class="textTootips">
														<c:out value="${bdItemAccueil.serie}" />
													</p>
													<p class="textTootips">
														<c:out value="${bdItemAccueil.titre}" />
													</p>

													<p class="titreTootips">Résumé:</p>

													<p class="textResumeTootips">
														<c:out value="${bdItemAccueil.resume}" />
													</p>

													<p class="titreTootips">Auteur:</p>

													<p class="textTootips">
														<c:out
															value="${bdItemAccueil.auteurNom} ${bdItemAccueil.auteurPrenom}" />
													</p>

													<p class="titreTootips">Genre:</p>

													<p class="textTootips">
														<c:out value="${bdItemAccueil.genre}" />
													</p>

													<p class="titreTootips">Langue:</p>

													<p class="textTootips">
														<c:out value="${bdItemAccueil.langue}" />
													</p>

													<br> <a class="lienLecture neonRouge"><input
														type="hidden">Lecture</input></a>

												</div>
											</button>

										</form>
									</div>
								</c:forEach>
							</c:otherwise>
						</c:choose>



					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</div>
