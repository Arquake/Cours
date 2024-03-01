(* Q1 *)

let reduce = fun list choice ->

  let rec sum = fun l op -> 
    if l = [] then 0
    else op List.hd l (sum (List.tl l) op)
  
  in
  
  let rec sum_float = fun l op -> 
    if l = [] then 0.
    else op (List.hd l) (sum_float (List.tl l) op)
  
  in
  
  let average = fun l op secop-> 
    if l = [] then nan else
      op (sum_float l secop) (float_of_int(List.length l ))

  
  in        
  
  let rec product = fun l op -> 
    if l = [] then 1
    else op (List.hd l) (product (List.tl l) op)
  
  in
  
  let rec product_float = fun l op -> 
    if l = [] then 1.
    else op (List.hd l) (product_float (List.tl l) op)
           
  in
  match choice with
  | 1 -> sum list (+)
  | 2 -> sum_float list (+.)
  | 3 -> average list (/.) (+.)
  | 4 -> product list ( * )
  | 5 -> product_float list ( *. )
  | _ -> nan;;




(* Q2 *)

let sum = fun l -> 
  let rec s = fun (li,res) ->
    match li with
    | [] -> res
    | h::t -> s (t, h + res)
  in 
  s (l,0);;
  


let sum_float = fun l -> 
  let rec s = fun (li,res) ->
    match li with
    | [] -> res
    | h::t -> s (t, h +. res)
  in 
  s (l,0.);;

let average = fun l -> 
  if l = [] then nan else
    sum_float( l ) /. float_of_int(List.length l ) ;;


let rec product = fun l -> 
  let rec s = fun (li,res) ->
    match li with
    | [] -> res
    | h::t -> s (t, h * res)
  in 
  s (l,1);;


let rec product_float = fun l -> 
  let rec s = fun (li,res) ->
    match li with
    | [] -> res
    | h::t -> s (t, h *. res)
  in 
  s (l,1.);;


(* Q3 *)




(* Q4 *)