(* Q1 *)

let from_to = fun (a,b) ->
    if b<a then []
    else
      let (x,y,z) = (
        iterate_until   ( (fun (a,b,l) -> if a = b then true else false), (fun (a,b,l) -> (a,b-1,b::l)), (a-1,b,[]) )
      ) in
      if true then z else [0];;


(* Q2 *)

let to_list = fun s -> let (x,y,z) = (
                                        iterate_until   ( (fun (c,b,l) -> if List.length c = b then true else false), (fun (c,b,l) -> (c,b+1,UOSTRING.get (c,b))), (ch,0,[]) )
                                    ) in
                                    if true then z else [0];;