(* Q1 *)

let in_list = fun element ->
  fun l ->
    let rec recur = fun (li,ele) ->
      match li with
      | [] -> false
      | h::t -> if h = ele then true else recur (t,ele)
    in recur (l,element);;

let rec sans_doublons = fun l -> 
  match l with
  | [] -> true
  | h::t -> if in_list h t then false else sans_doublons t;;


(* Q2 *)

mem


