(* Q1 👍 *)

let rec power = fun (x,n) -> 
    if n = 0 then 1 else
    if n = 1 then x else power (x,n-1) * x;;
    

(* Q2 😀 *)

let rec power = fun (x,n) -> 
    if n = 0 then 1 else
    if n = 2 then x * x else
    if n mod 2 = 0 then
      power(power(x,n/2),2)
    else
      x * power(power(x,(n-1)/2),2);;