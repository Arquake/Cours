(* Q1 *)

let rec repeat = fun (n,chain,sep)->
if n <= 0 then "" else
    if n = 1 then chain else chain ^ sep ^ repeat(n-1,chain,sep);;