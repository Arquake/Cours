let chemin = fun edli ->
  let rec check = fun (edl,last_vertex) ->
    match edl with
    | [] -> true
    | h::t -> if h.source = last_vertex then check(t,h.cible) else false
  in 
  match edli with
  | [] -> false
  | h::t -> check (t, h.cible);;


let ed = [
  {source = Sommet 5 ; cible = Sommet 9};
  {source = Sommet 9 ; cible = Sommet 10};
  {source = Sommet 10 ; cible = Sommet 3};
  {source = Sommet 3 ; cible = Sommet 5}
];; 


type vertex = Sommet of int;;
type edge = {source : vertex ; cible : vertex};;

(* Q2 *)

type graph = {verticies : vertex list ; edges : edge list};;

(* Q3 *)

type sommet_ou_rien = Rien | Sommet of vertex;;

let sommet_quelconque = fun g -> 
  match g.verticies with
  | [] -> Rien
  |  h::_-> Sommet h;;

let g = {verticies = [Sommet 5; Sommet 9; Sommet 10; Sommet 3] ; edges = []};;


let chemin_desordre = fun edli ->
  let rec recherche_next_chemin = fun (v,edl,edl_passed) ->
    match edl with
    | [] -> {verticies = [] ; edges = []}
    | h::t -> if h.source = v  
      then {verticies = [h.cible] ; edges = edl @ edl_passed}
      else recherche_next_chemin (v,t,h::edl_passed)
  in

  let rec recherche_chemin = fun (edl,last_vertex) ->
    match edl with
    | [] -> true
    | h::t -> let ret = recherche_next_chemin (last_vertex,edl,[]) in
              if ret.verticies = [] 
                then false 
              else recherche_chemin(ret.edges, Sommet (List.hd ret.verticies))

  in


  let rec recherche_prev_chemin = fun (v,edl,edl_passed) ->
    match edl with
    | [] -> {verticies = [] ; edges = []}
    | h::t -> if h.cible = v  
      then {verticies = [h.source] ; edges = edl @ edl_passed}
      else recherche_prev_chemin (v,t,h::edl_passed)
  in

  let rec racine = fun (list_rest) ->
    match list_rest with
    | [] -> Rien
    | h::t -> if (recherche_prev_chemin(h.source,t,[])).verticies = [] 
        then Sommet h.source
        else racine(t)
  
  in

  let starting_vertex = racine(edli)
  in
  if starting_vertex = Rien then false
  else recherche_chemin (edli, starting_vertex);;