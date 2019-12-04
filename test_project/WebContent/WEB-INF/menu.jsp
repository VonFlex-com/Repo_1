<nav>
	<ul class="menuCW">
		<li class="logo"><a class="logo neonRouge" href="./accueil"><strong>COSMICS
					WEB</strong></a></li>
		<li class="item type"><a class= "lienMenu neonRouge" href="./genres">GENRES</a></li>
		<li class="item type"><a class= "lienMenu neonRouge" href="./auteurs">AUTEURS</a></li>
		<li class="item type"><a class= "lienMenu neonRouge" href="#">NOTES</a></li>
		<li class="item recherche"><input class="" type="text"
			placeholder="Recherche"></li>
		<c:if test="${sessionScope.sessionUtilisateur == null}">
			<li class="item button"><a class= "neonRouge" href="./login">CONNEXION</a></li>
		</c:if>
		<c:if test="${sessionScope.sessionUtilisateur != null}">
			<li class="item button "><a class="logged" href="./monCompte">MON
					COMPTE</a></li>
		</c:if>
		<li class="toggle"><span class=" neonRouge bars"></span></li>
	</ul>

</nav>