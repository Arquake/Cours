(* Q1 *)

let gcd = fun (a,b) -> let (x,y) = iterate_until (
                                                    (fun (a,b) -> if a = b || a <= 1 || b <= 1 then true else false),
                                                    (fun (a,b) -> if a<b then (a,b-a) else (a-b,b)),
                                                    (a,b)
                                                  )
                        in if x = y then x else 1;;


(* Q2 *)

(* Q3 *)

let max = fun (a,b) -> if a > b then a else b;;

let gcd = fun (a,b) -> let (x,y) = iterate_until (
                                                    (fun (a,b) -> if a = b || a <= 1 || b <= 1 then true else false),
                                                    (fun (a,b) -> if a<b then (a,b-a) else (a-b,b)),
                                                    (a,b)
                                                  )
                        in if x = y then x else 
                            if x = 0 || y = 0 max (x,y)
                            else 1;;