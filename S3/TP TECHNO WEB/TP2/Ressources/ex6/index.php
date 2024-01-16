<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <header><p>tgtgtgt</p></header>

    <form action="index.php" method="post">

        <fieldset>
            <legend>Connexion</legend>
            <label for=""><input type="text" name="login" id="login">Identifiant</label>
            <label for=""><input type="password" name="password" id="password">Mot de passe</label>
        </fieldset>

        <fieldset>
            <legend>Créer un compte</legend>
            <label for="">Nom</label><input type="text" name="" id="">
            <label for="">Prénom</label><input type="text" name="" id="">
            <input type="radio" id="Homme" name="Sexe" value="Homme"> <label for="Homme">Homme</label> <br>
            <input type="radio" id="Femme" name="Sexe" value="Femme"> <label for="Femme">Femme</label> <br>
            <label for="">Date de naissance</label><input type="date" name="" id="">
            <label for="">Région</label><input type="number" name="" id="" min="0" max="100000">
        </fieldset>
        
    </form>
</body>
</html>