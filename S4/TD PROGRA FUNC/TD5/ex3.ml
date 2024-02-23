(* Q1 *)

type son = Silence | Note of int;;
type ligne = {instrument : string; sons : son list};;
type piste = {titre:string; auteur:string; ligne_musicales:ligne list};;


(* Q2 *)

let transpose = fun musique -> fun n ->
  let rec modif = fun (liste,nth) ->
    match liste with
    | [] -> []
    | [ele] -> [ele]
    | h::first_tail::second_tail::[] ->
