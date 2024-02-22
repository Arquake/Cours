(* Q1 *)

type vertex = Sommet of int;;
type edge = {source : vertex ; cible : vertex};; 
type graph = {verticies : vertex list ; edges : edge list};; 
type sommet_ou_rien = Rien | Sommet of vertex ;;


let chemin_desordre = fun edli ->

  (** Recherche si un chemin qui suit celui donné dans la liste
      Si oui on retourne le point et la liste avec tout les edge
      Retourne un graph avec des listes vides si rien n'est trouvé
      
      val recherche_next_chemin : vertex * edge list * edge list -> graph = <fun> *)
  let rec recherche_next_chemin = fun (v,edl,edl_passed) ->
    (* on vérifie la liste des sommets non passé *)
    match edl with
    (*si il n'y a aucun sommet*)
    | [] -> {verticies = [] ; edges = []}
(*sinon on prend la source de la tête et on la compare avec le vertex*)
    | h::t -> if h.source = v  
(* si c'est vrai on renvoit un graph avec le vertex cible et le reste des chemins non parcouru*)
        then {verticies = [h.cible] ; edges = t @ edl_passed}
(* on appel pour trouver le prochain chemin car l'acutel ne correspond pas*)
        else recherche_next_chemin (v,t,h::edl_passed)
  in

  (** Appel recherche_next_chemin et si le graph est vide retourner false
      sinon faire un appel récursif sur le vertex renvoyé et
      la liste retourner des arrêtes
      si edl est vide on retourne true car tout les chemin on pu être parcouru
      
      val recherche_chemin : edge list * vertex -> bool = <fun>*)

  let rec recherche_chemin = fun (edl,last_vertex) ->
    match edl with
    | [] -> true
    | h::t -> let ret = recherche_next_chemin (last_vertex,edl,[]) in
        if ret.verticies = [] 
        then false 
        else recherche_chemin(ret.edges, List.hd ret.verticies)

  in

  (** Recherche si un chemin qui précède celui donné dans la liste
      Si oui on retourne le point et la liste avec tout les edge
      Retourne un graph avec des listes vides si rien n'est trouvé
      
      val recherche_prev_chemin : vertex * edge list * edge list -> graph = <fun> *)
  let rec recherche_prev_chemin = fun (v,edl,edl_passed) ->
    match edl with
    | [] -> {verticies = [] ; edges = []}
    | h::t -> if h.cible = v  
        then {verticies = [h.source] ; edges = edl @ edl_passed}
        else recherche_prev_chemin (v,t,h::edl_passed)
  in

  (** parcours la liste si aucun chemin ne la précède on vérifie l'élément
      d'après sinon on renvoit le vertex 
      Si aucun élément n'est trouvé on renvoit Rien
      
      val racine : edge list -> sommet_ou_rien = <fun>*)
  let rec racine = fun (list_rest) ->
    match list_rest with
    | [] -> Rien
    | h::t -> if (recherche_prev_chemin(h.source,t,[])).verticies = [] 
        then Sommet h.source
        else racine(t)
  
  in

  let starting_vertex = racine(edli)
  in
  match starting_vertex with
  | Rien -> false
  | Sommet (a) -> recherche_chemin (edli, a);;
  



let ted = [
  {source = Sommet 5 ; cible = Sommet 9};
  {source = Sommet 9 ; cible = Sommet 10};
  {source = Sommet 10 ; cible = Sommet 3};
  {source = Sommet 3 ; cible = Sommet 5}
];;

chemin_desordre ted;;