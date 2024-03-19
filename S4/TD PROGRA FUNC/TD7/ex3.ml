(* Q1 *)
type 'a nelist =
| END
| Element of 'a * 'a nelist;;

(* Q2 *)

exception ListIsEmpty;;

let of_list = fun l ->
  let rec aux = fun li ->
    match li with
    | h::[] -> Element(h,END) 
    | h::t -> Element(h,aux t)
    | [] -> raise ListIsEmpty
  in
  aux l

(* Q3 *)

exception NoHead;;
exception NoTail
let hd = fun l ->
  match l with
  | Element(h,_) -> h
  | END -> raise NoHead;;

let tl = fun l ->
  match l with
  | Element(_,t) -> t
  | END -> raise NoTail;;


(* Q4 *)

exception EmptyList;;
let maximum = fun l ->
  let rec aux = fun li res ->
    match li with
    | Element(v,t) when v > res -> aux t v
    | Element(_,t) -> aux t res
    | END -> res
  in
  match l with
  | Element(v,t) -> aux t v
  | END -> raise EmptyList;;

exception EmptyList;;
let minimum = fun l ->
  let rec aux = fun li res ->
    match li with
    | Element(v,t) when v < res -> aux t v
    | Element(_,t) -> aux t res
    | END -> res
  in
  match l with
  | Element(v,t) -> aux t v
  | END -> raise EmptyList;;