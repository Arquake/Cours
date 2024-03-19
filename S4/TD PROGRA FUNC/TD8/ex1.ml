module Choice =
struct
type choice = | Yes | No
let of_bool =
function
| true -> Yes
| false -> No
let to_bool =
function
| Yes -> true
| No -> false
let to_string =
function
| Yes -> "yes"
| No -> "no"
let select =
fun choice f1 f2 value ->
match choice with
| Yes -> f1 value
| No -> f2 value
end

(*
   
module Choice
  sig
    type choice = Yes | No

    val of_bool : bool -> choice

    val to_bool : choice -> bool

    val to_string : choice -> string

    val select : choice -> ('a -> 'b) -> ('a -> 'b) -> 'a -> 'b

  end

*)


module Color =
struct
type color = | Red | Blue | Green
let to_int =
function
| Red -> 0
| Blue -> 1
| Green -> 2
exception Not_a_color_index
let of_int =
function
| 0 -> Red
| 1 -> Blue
| 2 -> Green
| _ -> raise Not_a_color_index
end

(*
   
module Color
  sig
    type color = Red | Blue | Green

    val to_int : color -> int

    exception Not_a_color_index
    
    val of_int : int -> color

  end

*)