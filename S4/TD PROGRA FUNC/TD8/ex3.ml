module type PAIR =
sig
type ('a, 'b) pair
val create: 'a -> 'b -> ('a, 'b) pair
val fst: ('a, 'b) pair -> 'a
val snd: ('a, 'b) pair -> 'b
end

module A = 
struct
  type ('a, 'b) pair = Pair of 'a * 'b
  let create = fun a b -> Pair(a,b)
  let fst =  
    function
    | Pair(a,_) -> a

  let snd =  
    function
    | Pair(_,b) -> b
end


module B =
struct
  type ('a, 'b) pair = Combine of bool*bool
  let create = fun a b -> Combine(a,b)
  let fst =  
    function
    | Combine(a,_) -> a

  let snd =  
    function
    | Combine(_,b) -> b
end


module C =
  struct
    type ('a, 'b) pair = Both of int*float
    let create = fun a b -> Both(a,b)
    let fst =  
      function
      | Both(a,_) -> a

    let snd =  
      function
      | Both(_,b) -> b
  end