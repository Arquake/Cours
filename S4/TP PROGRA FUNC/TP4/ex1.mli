(* Q1 *)

let rec for_all = fun (f,l) -> 
    match l with
    | [] -> true
    | head::tail -> f head && for_all(f,tail);;
  

(* Q2 *)

let rec exists = fun (f,l) ->
    match l with
    | [] -> false
    | head::tail -> f head || exists(f,tail);;


(* Q3 *)

let rec for_all_contiguous = fun (f,l) ->
    match l with
    | [] -> true
    | head::[] -> true
    | fi::se::tail -> f (fi,se) && for_all_contiguous(f,se::tail);;


(* Q4 *)

let is_sorted = fun l ->
    let rec for_all_contiguous = fun (f,l) ->
      match l with
      | [] -> true
      | head::[] -> true
      | fi::se::tail -> f (fi,se) && for_all_contiguous(f,se::tail)
    in 
    for_all_contiguous((fun (x,y) -> x < y), l );;
 