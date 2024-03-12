(* Q1 *)

type 'a cell = { head : 'a; tail : 'a list' }
and  'a list' = 'a cell option;;

let empty : 'a list' =  None;;
let (+:) : 'a -> 'a list' -> 'a list' =
  fun head -> fun tail -> Some { head = head; tail = tail };;

      
1 +: (2 +: (3 +: empty));;
      
let length : 'a list' -> int = fun a ->
  let rec iterate = fun (n,res) ->
    match n with
    | {head=_;tail=Some(l)} -> iterate (l,res+1) 
    | empty -> res
  in
  match a with 
  | Some(i) -> iterate(i,1)
  | _ -> 0;;

length (1 +: (2 +: (3 +: empty)));;


(* Q2 *)

let convert = fun l ->
  let rec matching = fun li ->
    match li with
    | [] -> empty
    | h::t -> h +: (matching t)
  in
  matching l;;

convert ["1"; "2"; "3"];;


(* Q3 *)

let convert = fun l ->
let rec matching = fun li ->
  match li with
  | [] -> empty
  | h::t -> h +: (matching t)
in
matching l;;

let convert' = fun s ->
  let rec iterate = fun (n,res) ->
    match n with
    | {head=v;tail=Some(l)} -> iterate (l,res @ [v]) 
    | {head=v;tail=empty} -> res @ [v]
  in
  match s with 
  | Some(i) -> iterate(i,[])
  | _ -> [];;

convert' (1 +: (2 +: (3 +: empty)));;