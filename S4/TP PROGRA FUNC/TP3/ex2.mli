(* Q1 *)

let rec from_to = fun (i,j) -> 
    if i > j then [] 
    else
      i :: from_to ( i + 1 , j );;


(* Q2 *)

let from_to = fun (i,j) -> 
    let rec inter = fun (tab,i,j)->
      if i > j then tab 
      else
        inter ( tab @ [i], i+1, j)
    in 
    inter([],i,j);;


(* Q3 *)

let product = fun l -> 
    let rec inter = fun (l,res) ->
      if l = [] then res else inter( List.tl l, res * List.hd l )
    in inter (l,1);;


(* Q4 *)

let factorial = fun i -> product (from_to (1,i));;