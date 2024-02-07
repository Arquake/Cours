(* Q1 *)

let rec for_all = fun (f,l) -> 
    match l with
    | [] -> true
    | head::tail -> f head && for_all(f,tail);;
  
for_all ((fun n -> n >= 0),[1; 2; 3]);;
for_all ((fun n -> n >= 0),[]);;
for_all ((fun n -> n >= 0),[1; -2; 3]);;
for_all ((fun c -> 'a' <= c && c <= 'z'),['a'; 'e'; 'z'; 'o'; 'u']);;
for_all ((fun c -> 'a' <= c && c <= 'z'),['a'; 'e'; '1'; 'o'; 'u']);;
  

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
 