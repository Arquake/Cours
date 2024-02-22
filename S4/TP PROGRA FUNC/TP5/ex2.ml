let voisins = fun v -> fun g ->
  let rec check_list = fun edli ->
    match edli with
    | [] -> []
    | h::t -> if h.source = v then h.cible::check_list t else check_list t
  in
  check_list g.edges;;

(* Faire une map des sommets vers leurs listes de vosisins *)

(* Q2 *)

let sans_boucle = fun g ->
  let rec check = fun (ed,list_sommet) ->
    match ed with
    | [] -> true
    | h::t -> if List.mem [ h.cible;  h.source] list_sommet
      then false 
      else check(t,[ h.source ;  h.cible] :: list_sommet)
  in
  check (g.edges,[]);;

(*let gr = {verticies = [Sommet 5; Sommet 9; Sommet 10; Sommet 3] ;
        edges = [
          {source = Sommet 5 ; cible = Sommet 9};
          {source = Sommet 10 ; cible = Sommet 9};
          {source = Sommet 3 ; cible = Sommet 9};
          {source = Sommet 9 ; cible = Sommet 5}
        ]};;*)


let simple = fun g ->
  let rec check = fun (ed,list_sommet) ->
    match ed with
    | [] -> true
    | h::t -> if List.mem [ h.source;  h.cible] list_sommet
        then false 
        else check(t,[ h.source ;  h.cible] :: list_sommet)
  in
  check (g.edges,[]);;


let coherent = fun g ->
  let rec check = fun (ed) ->
    match ed with
    | [] -> true
    | h::t -> if not (List.mem h.source g.verticies &&  List.mem h.cible g.verticies) 
        then false 
        else check(t)
  in
  check (g.edges);; 


let correct = fun g ->
  sans_boucle g && simple g && coherent g;;


(* Q3*)

let degre_ent = fun g -> fun v ->
  let rec check = fun (ed,count) ->
    match ed with
    | [] -> count
    | h::t -> if h.cible = v then check (t,count + 1) else check(t,count)
  in check (g.edges,0);;

let degre_sort = fun g -> fun v ->
  let rec check = fun (ed,count) ->
    match ed with
    | [] -> count
    | h::t -> if h.source = v then check (t,count + 1) else check(t,count)
  in check (g.edges,0);;