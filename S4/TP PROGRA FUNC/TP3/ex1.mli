(* Q1 *)

let rec sum = fun l -> 
    if l = [] then 0
    else List.hd l + sum (List.tl l);;


(* Q2 *)

let rec sum_float = fun l -> 
    if l = [] then 0.
    else List.hd l +. sum_float (List.tl l);;
  
  
  
  let average = fun l -> 
    if l = [] then "nan" else
      string_of_float ( sum_float( l ) /. float_of_int(List.length l ));;


(* Q3 *)

let rec product = fun l -> 
    if l = [] then 1
    else List.hd l * product (List.tl l);;


(* Q4 *)

let rec product_float = fun l -> 
    if l = [] then 1.
    else List.hd l *. product_float (List.tl l);;