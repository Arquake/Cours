let solve_quadratic = fun (a,b,c) ->
    let det = b *. b -. 4. *. a *. c in
    if det < 0. 
    then "Pas de solution réelle"
    else
    if det > 0.
    then "Deux solutions réelles : " ^ string_of_float ( ( b -. det ) /. ( 2. *. a ) ) ^ " et " ^
         string_of_float ( ( b +. det ) /. ( 2. *. a ) )
    else "Une solution réelle : "^ string_of_float ( ( b -. det ) /. ( 2. *. a ) );;