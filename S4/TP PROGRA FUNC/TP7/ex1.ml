(* Q1 *)

type unary_operation = Abs | Oppose | Square;;

type binary_operation = Max | Mod | Plus | Multiply | Divide | Minus;;

type 'a expression = 
| Int of int
| Variable of 'a
| Unary of unary_operation * 'a expression
| Binary of binary_operation * 'a expression * 'a expression;;

let premier =
  Binary(
    Plus,
    Binary(
        Multiply,
        Variable "x",
        Int 2
      ),
    Int(1)
  );;

let second = 
  Binary(
    Plus,
    Binary(
    Mod,
    Int(42),
    Int(7)
    ),
    Binary(
      Multiply,
      Int(12),
      Int(2)
    )
  );;
  
let troisieme =
  Binary(
    Divide,
    Binary(Plus,Variable "a",Variable "b"),
    Binary(Minus,Variable "a",Variable "b")
  );;

let quatrieme =
  Binary(
    Plus,
    Int(1),
    Binary(
      Max,
      Variable "x",
      Int 0
    )
  );;

let cinquieme =
  Unary(
    Square,
    Unary(
      Abs,
      Binary(
        Minus,
        Variable "x",
        Variable "y"
      )
    )
  );;

let six =Binary(Plus,Unary(Oppose,Variable "x"),Int(2));;

(* Q2 *)

let closed = fun expr ->
  let rec check = fun exp ->
    match exp with
    | Unary(_,var) -> check var
    | Binary(_,var1,var2) -> (check var1) && (check var2)
    | Variable(_) -> false
    | Int(_) -> true
  in
  check expr;;


let all_variables = fun expr ->
  let rec check = fun exp->
    match exp with
    | Unary(_,var) -> check var
    | Binary(_,var1,var2) -> (check var1) @ (check var2)
    | Variable(var) -> [var]
    | Int(_) -> []
  in
  check expr;;

let variables = fun expr ->
  let rec check = fun exp l ->
    match exp with
    | Unary(_,var) -> check var l
    | Binary(_,var1,var2) -> check var2 (check var1 l)
    | Variable(var) -> if List.mem var l then l else var :: l
    | Int(_) -> l
  in
  check expr [];;


(* Q3 *)

let get_unary = fun ope number ->
  match ope with
  | Abs -> if number < 0 then -number else number
  | Oppose -> -number
  | Square -> number * number;;

let get_binary = fun ope num1 num2 ->
  match ope with
  | Max -> if num1 > num2 then num1 else num2
  | Mod -> num1 - num1/num2
  | Plus -> num1 + num2
  | Multiply -> num1 * num2
  | Divide -> num1 / num2
  | Minus -> num1 - num2;;


exception Invalid_Input of string;;

let evaluate_closed = fun expr ->
  let rec checker = fun exp ->
  match exp with
  | Unary(operation,v1) -> get_unary operation (checker(v1))
  | Binary(operation,v1,v2) -> get_binary operation (checker(v1)) (checker(v2))
  | Int(v1) -> v1
  | Variable (_) -> raise ("A variable was found in the expression")
  in
  checker expr;;