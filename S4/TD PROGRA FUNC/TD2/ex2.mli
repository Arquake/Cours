fun (x,y) -> float_of_int x *. y;;

fun x -> (int_of_float x, x - float_of_int ( int_of_float x ) );;

fun x -> (string_of_float x, int_of_float x);;

fun x -> char_of_string ( get (x,1) ) ;;

fun (f,x,y) -> (f y, x) ;;

xxxxxxxx

fun l -> (UOList.length l, l) ;;

fun (x, y) -> (

    UOList.map (
        fun -> (a,b) -> a, l 
        ), 

    UOList.map( 
        func(a,b) -> b, l
        ) 
        
    );;