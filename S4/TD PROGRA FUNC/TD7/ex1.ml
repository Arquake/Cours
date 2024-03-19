exception NoKeyFound                                                                 (* exception NoKeyFound  *)
type ('a,'b) entry = { key: 'a; value: 'b }                                          (* type ('a,'b) entry = { key: 'a; value: 'b }   *)
let digits = [ { key = 0; value = "zero" }; { key = 1; value = "one" };              (* var digits : (int,string) entry list = [ { key = 0; value = "zero" }; { key = 1; value = "one" };     *)
               { key = 2; value = "two" };  { key = 3; value = "three" };            (*                { key = 2; value = "two" };  { key = 3; value = "three" };   *)
               { key = 4; value = "four" }; { key = 5; value = "five" };             (*                { key = 4; value = "four" }; { key = 5; value = "five" };    *)
               { key = 6; value = "six" };  { key = 7; value = "seven" };            (*                { key = 6; value = "six" };  { key = 7; value = "seven" };   *)
               { key = 8; value = "eight" };{ key = 9; value = "nine" } ]            (*                { key = 8; value = "eight" };{ key = 9; value = "nine" } ]   *)
let rec find = fun key list ->                                                       (* val find : 'a -> ('a,'b) entry list -> 'b = <fun> *)
  match list with                                                                    
  | [] -> raise NoKeyFound                                                           
  | head::tail when head.key = key -> head.value                                     
  | _::tail -> find key tail                                                         
let word_of_digit = fun digit ->                                                     (* val word_of_digit : int -> string = <fun> *)
  try                                                                                
    find digit digits                                                                
  with NoKeyFound -> "not a digit"                                                   