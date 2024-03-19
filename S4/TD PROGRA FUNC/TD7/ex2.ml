(* Q1 *)

type type_enseignement = CM | TD | TP 

type personne = { nom : string ; prenom : string }

type enseignement = { code : string ; titre : string ; semestre : int ; nature : type_enseignement }

type enseignant = { identifiant : int ; service : enseignement ; humain : personne }

