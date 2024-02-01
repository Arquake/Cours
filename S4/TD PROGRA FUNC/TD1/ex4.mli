(* Q1 *)

let incr1 = fun n -> n +. 1. ;;
let incr2 = fun n -> n +. 2. ;;

let decr1 = fun n -> n -. 1. ;;
let decr2 = fun n -> n -. 2. ;;


(* Q2 *)

let operation = (incr1, incr2, decr1, decr2);;


(* Q3 *)


let choose : ((float->float * float->float * float->float * float->float) * int)
-> (float -> float)
= fun ((w,x,y,z),num) -> 
    if 0 = num
        then w
    else if 1 = num
        then x
    else if 2 = num
        then y
    else if 3 = num
        then z
    else (fun n -> n);;