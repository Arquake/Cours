<!DOCTYPE html>
<html lang="fr">
 		<head>
   			 <meta charset="utf-8">
   			 <link rel="stylesheet" href="style.css">
   			 <title>Index</title>
        </head>
 	    <body>
			<form method="post">
				<fieldset>
					<legend>Déjà Membre</legend>
					<p>Login <input type="text" id="Login" name="Login" maxlength="24" size="24" placeholder="Votre Login"/> </p>
					<p>Mot de passe <input type="password" id="password" name="password" maxlength="64" size="32" placeholder="Votre Mot de passe"/> </p>
				</fieldset>

				<fieldset>
					<legend>Nouveau Membre</legend>
					<p>Nom <input type="text" id="Nom" name="Nom" maxlength="32" size="32" /> </p>
					<p>Prénom <input type="text" id="Prénom" name="Prénom" maxlength="64" size="32" /> </p>
					<p>
						<input type="radio" id="Sexe" name="Sexe" value="Homme" checked="checked/>
						<label for="Homme">Homme</label>
						<input type="radio" id="Sexe" name="Sexe" value="Femme" />
						<label for="Femme">Femme</label>
					</p>
					<p>Code Postal <input id="zip" name="zip" type="number" min="0" step="10"></p>
					<p>Profession <input type="text" id="Profession" name="Profession" maxlength="40" size="32" /> </p>

					<label for="dropDown">Comment avez vous connu notre site </label>
					<select name="dropDown" id="dropDown">
						<option value="net">par le net</option>
						<option value="friend">par un ami</option>
						<option value="article">par courrier</option>
					</select>

					<p>Information Complémentaire <textarea id="infoComp" name="infoComp" rows="3" cols="30"> </textarea> </p>

				</fieldset>

				<input type="Submit" value="Envoyer">
				<input type="Submit" value="Test">
				<input type="reset" value="Effacer">
			</form>
			<?php
				if(strlen($_POST['Login'])>0 && strlen($_POST['password'])>0){
					echo "formulaire Membre :<br>Login : ";
					echo $_POST['Login'] ;
					echo "<br>Mot de passe : ";
					echo $_POST['password'] ;
				} else {

				echo "formulaire Non-membre :<br>Nom : ";
				echo $_POST['Nom'] ;
				echo "<br>Prénom : ";
				echo $_POST['Prénom'] ;
				echo "<br>Sexe : ";
				echo $_POST['Sexe'] ;
				echo "<br>zip : ";
				echo $_POST['zip'] ;
				echo "<br>Profession : ";
				echo $_POST['Profession'] ;
				echo "<br>Decouverte Site : ";
				echo $_POST['dropDown'] ;
				echo "<br>info Complémentaire : ";
				echo $_POST['infoComp'] ;
				}
			?>

  	    </body>	
</html>