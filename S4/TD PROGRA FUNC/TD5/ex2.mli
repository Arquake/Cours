type media_type =  Livre | Bd | Jeu;;
type media = { category : media_type ; titre : string ; auteur : string };;
type 'a mediatheque = Empty | Media of media * 'a mediatheque;;

let rec auteurs = fun l ->
  match l with
  | Empty -> [] 
  | Media (x,y) -> x.auteur :: auteurs y;;

let li = Media({category = Livre ; titre = "osef" ; auteur = "a"},
            Media({category = Bd ; titre = "osef" ; auteur = "b"},
                Media({category = Jeu ; titre = "osef3" ; auteur = "a"},Empty)));;

let rec nb_categorie = fun l ->
    fun cat -> 
        match l with
        | Empty -> 0
        | Media (x,y) -> if x.category = cat then 1 + nb_categorie y cat else 0 + nb_categorie y cat;;