(* Q1 *)

type 'a cell = { head : 'a; tail : 'a list' }
and  'a list' = 'a cell option;;

let length : 'a list' -> int = fun a ->
  let rec iterate = fun (n,res) ->
    match n with
    | {head=empty;tail=_} -> res
    | {head=v;tail=l} -> 
        match l with
        | empty -> res + 1
        | le -> iterate (le,res+1)
  in
  iterate (a,0);;