(* Q1 *)

type ('a) unary_operation = 'a -> 'a;;

type ('a,'b) binary_operation = 'a -> 'b -> 'a;;

type ('a) expression = Unary of unary_operation | Binary of binary_operation;;