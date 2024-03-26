module type PAIR =
sig
type ('a, 'b) pair
val create: 'a -> 'b -> ('a, 'b) pair
val fst: ('a, 'b) pair -> 'a
val snd: ('a, 'b) pair -> 'b
end

module A:PAIR = 
struct
  type ('a, 'b) pair = 'a * 'b
  let create = fun a b -> (a,b)
  let fst = fst

  let snd = snd
end


module B: PAIR =
struct
  type ('a, 'b) pair = {f:'a;s:'b}
  let create = fun a b -> {f=a;s=b}
  let fst = fun x -> x.f

  let snd = fun x -> x.s
end


module C: PAIR =
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