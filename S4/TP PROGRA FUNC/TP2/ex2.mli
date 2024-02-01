(* Q1 *)

let repeat_string = fun (ch, n) -> iterate (n, (fun s -> s ^ ch), "");;


(* Q2 *)

let end_line = fun ch -> ch ^ "\n";;


(* Q3 *)

let stars_enclosing = fun ch -> "* " ^ ch ^ " *";;

(* Q4 *)

let frame_string = fun ch -> let x = repeat_string ("*",(String.length ch) + 4) in x ^ "\n" ^ (stars_enclosing ch) ^ "\n" ^ x;;