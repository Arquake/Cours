(* Q1 *)

type nat = Null | Nat of (int * nat);;


(* Q2 *)

let nat_of_int = fun num -> 
  if num >= 0 then Nat (num,Null)
  else Null;;

let int_of_nat = fun natnum ->
  match natnum with
  | Nat(a,_) -> a;;


(* Q3 *)

let nat_of_string = fun num -> 
  if (int_of_string num) >= 0 then Nat ((int_of_string num),Null)
  else Null;;

let string_of_nat = fun natnum ->
  match natnum with
  | Nat(a,_) -> string_of_int a;;


(* Q4 *)

let addition = fun n m ->
  if m = 0 then n else
  ;;

  let multiplication = fun n m ->
    match n with
    | Null -> Null
    | _ -> match m with
      | Null -> Null
      | _ -> Nat(int_of_nat (n) * int_of_nat(m) + int_of_nat(n),Null);;


(* Q5 *)

let fact = fun num ->
  let rec aux= fun n m ->
    if int_of_nat n = 1 then multiplication n m else
    if int_of_nat n < 1 then Nat(0,Null) else
      (aux (nat_of_int ((int_of_nat n) - 1)) (multiplication n m))
  in
  aux num (nat_of_int (int_of_nat(num) - 1));;