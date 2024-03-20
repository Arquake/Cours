module type DICTIONARY =
  sig
    type ('a, 'b) t

    val empty: ('a, 'b) t

    val is_empty: ('a, 'b) t -> bool

    val mem: 'a -> ('a, 'b) t -> bool

    val find_opt: 'a -> ('a, 'b) t -> 'b option

    val find: 'a -> ('a, 'b) t -> 'b

    val remove: 'a -> ('a, 'b) t -> ('a, 'b) t

    val add: 'a -> 'b -> ('a, 'b) t -> ('a, 'b) t
    
    end


module Evaluation (Env : DICTIONARY) =
struct
  type unary_operation = | Opp | Abs | Sqr
  type binary_operation = | Add | Sub | Mul | Div | Mod | Max
  type 'a expression =
  | Var of 'a
  | Int of int
  | Unary of unary_operation * 'a expression
  | Binary of binary_operation * 'a expression * 'a expression

  let evaluate: 'a expr -> ('a, int) Env.t -> int = fun exp ele ->
    try exp ele with raise "test"
  
end