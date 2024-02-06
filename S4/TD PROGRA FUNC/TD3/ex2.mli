(* Q1 *)

let rec f = fun n -> 
    if n <= 0 
    then 0
    else f (n-1) + n;;


(* Q2 *)

let rec sum_from_to = fun (n,m) ->
    if m < n then 0 else
    if n = m then m
    else n + sum_from_to(n + 1, m);;
