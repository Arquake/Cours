#!bin/bash

output.html << "
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>";

while IFS="|" read -r rec_column1 rec_column2 rec_column3 rec_column4 rec_column5 rec_column6
do
  auteur = "$rec_column1"
  titre = "$rec_column2"
  maison = "$rec_column3"
  publi = "$rec_column4"
  isbn = "$rec_column5"
  quatre = "$rec_column6"
  output.html < "
  <div>
    <ul class="space-y-4">
        <li>Titre: $auteur</li>
        <li>Autheur: $titre</li>
        <li>Maison d'édition: $maison</li>
        <li>Année de parution: $publi</li>
        <li>IBSN: $isbn</li>
        <li>quartrième: $quatre</li>
    </ul>
</div>
  "
done < livres.csv

output.html < "</body>
</html>"

pause >nul