(* Code à utiliser. Voir le texte du cours n°2 pour la description 
   des fonctions du module UOList et le texte du TP n°2 pour la 
   description du module UOString et des fonctions iterate et 
   iterate_until. Le code lui-même sera expliqué essentiellement dans 
les cours n°3 et n°4. *)
module UOList = struct
  let hd = List.hd
  and tl = List.tl
  and length = List.length
  and map = fun(f,l) -> List.map f l
  and filter = fun(p,l) -> List.filter p l
end

module UOString =
struct
  (** get(s, i) renvoie le caractère se trouvant à la position 
      i dans la chaîne de caractères s si i est compris entre 
0 et (String.length s)-1 (inclus). *)
  let get : (string*int) -> char =
    fun(s,i) -> String.get s i
end

(*  iterate(n, f, initial_value) applique n fois la fonction f 
    en partant de la valeur initial value. 
    
    iterate(3, f, x) = f(f(f x)).
    
Si n vaut 0 ou moins, la valeur retournée est initial_value.*)
let rec iterate : (int * ('a->'a) * 'a) -> 'a =
  fun (count, f, initial_value) ->
    if count <= 0
    then initial_value
    else iterate (count-1, f, f initial_value)
;;

(*  iterate_until(test, f, initial_value) applique la fonction f autant
    de fois qu'il faut pour que la valeur obtenu satisfasse le prédicat 
test. *)
let rec iterate_until : ( ('a -> bool) * ('a->'a) * 'a) -> 'a =
  fun (predicate, f, initial_value) ->
    if predicate initial_value
    then initial_value
    else iterate_until (predicate, f, f initial_value)
;;


(** EXEMPLES **)

let incr = fun x -> x + 1;;

UOList.map(incr, [1; 4; -1]);;                                      (* il faut mettre des ";" entre les int pour que ce soit du bon type *)

let f = fun n -> (let f = fun n -> 2*n+1 in f 2);;                  (* il faut mettre des parenthèse à l'expression sinon il calcul pour f 2 ce qui donne un int de 5 et 
                                                                    provoque une erreur si on essai de l'utiliser comme fonction*)

f 3;;                                                               (* -: int 5 si l'on change la ligne précédente *)

UOList.map((fun (x,y) -> if y then x + 1 else x, [(1, true); (2, false); (3, true); (8, false)]));;  

                                                                    (* incr est défini à l'aide de 1 paramètre hors on donne des tuples de int * bool 
                                                                    donc on créé une nouvelle fonction pour gérer si la valeur est true ou non) *)
                                                                    

UOList.filter((fun x-> float_of_int x < Float.pi), [1; 2; 3; 4; 42]);;       (* on convertit les éléments en float *)

UOList.map(( fun f -> f Float.pi), [cos; sin; tan]);;

UOList.map(( fun f -> f Float.pi), [cos; sin; tan; (fun x ->  if x < 0. then x *. -1. else x); sqrt]);; (* on créé la fonction abs pour les flotant *)