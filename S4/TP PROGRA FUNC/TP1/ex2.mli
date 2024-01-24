(* Question 1 *)

let is_multiple = fun (x,y) -> if (y mod x = 0) then true else false;;
  
is_multiple (3,6);;
is_multiple (3,7);;


(* Question 2 *)

let min : (int * int) -> int =
    fun (num1, num2) ->
      if num1 < num2
      then num1
      else num2;;
  
  let min3 = fun (x,y,z) -> 
    min (x,min(y,z));;
        
      
  min3 (3,2,1)


(* Question 3 *)

let add_f_i = fun (f,i) -> 
    float i +. f;;
  
  add_f_i (2., 1);;


(* Question 4 *)

let sum_apply  = fun (fun1,fun2,i) -> 
    i + fun1 i + fun2 i;;