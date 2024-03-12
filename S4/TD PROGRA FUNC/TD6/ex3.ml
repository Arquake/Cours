type ('a,'b) assoc_list =                                     
  | Empty                                                     
  | Pair of 'a * 'b * ('a,'b) assoc_list;;

(* Q1 *)

let rec add = fun k v dict ->
  match dict with
  | Empty -> Pair(k,v,Empty)
  | Pair (k',v',t) -> Pair (k',v',(add k v t));;

let v1 = add 5 "five" Empty;;
let v2 = add 6 "six" v1;;
let v3 = add 7 "seven" v2;;
let v4 = add 8 "eight" v3;;


(* Q2 *)

let check = fun dict ->
  let rec inside_check = fun dico res ->
    match dico with
    | Empty -> true
    | Pair(k,_,t) when (List.mem k res) = true -> false
    | Pair(k,_,t) -> inside_check t (k::res)
  in
  match dict with
  | Empty -> false
  | Pair (k,v,t) -> inside_check dict [];;