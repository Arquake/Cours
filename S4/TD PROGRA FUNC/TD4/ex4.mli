(* Q1 *)

let rec last = fun l ->
    match l with
    | h::[] -> h
    | h::t -> last t;;


(* Q2 *)

let rec without_last = fun l ->
    match l with
    | [] -> []
    | h::[] -> []
    | h::t -> h :: without_last t;;


(* Q3 *)

let cut_last = fun l ->
    let rec cutting = fun (to_cut,in_cut) ->
        match to_cut with
        | h::[] -> (h,in_cut)
        | h::t -> cutting(t,in_cut @ [h])
    in cutting(l,[]);;


(* Q4 *)

let shift_right = fun l ->
    let rec cutting = fun (to_cut,in_cut) ->
        match to_cut with
        | h::[] -> [h] @ in_cut
        | h::t -> cutting(t,in_cut @ [h])
    in cutting(l,[]);;