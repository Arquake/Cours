(* Q1 *)

val shift_left : a' List -> a' List


(* Q2 *)

fun l -> 
    if l = [] then []
    else UOList.tl l @ [ UOList.hd l ];;