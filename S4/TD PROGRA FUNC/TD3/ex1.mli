let f = fun x -> if x <= 0 then 0 else 1 + f(x - 1);;                         (* error non précisé récursif *)
let rec f = fun x -> if x >= 0 then 1 + f(x - 1) else 1 + f(x + 1);;          (* val f : int -> int = <fun> *)
f 5;;                                                                         (* error récursion infini *)
let rec g = fun x -> g (x - 1);;                                              (* val g : int -> 'a *)
g 2;;                                                                         (* error récursion infini *)
let rec h = fun x -> if x <= 0 then 1 else 2 * (h (x-1)) in h 4;;             (* -: int = 16 *)
h 8;;                                                                         (* error h n'existe pas *)
let rec z = fun l -> if List.length l = 1 then List.hd l                      
else z(List.tl l);;                                                           (* val z : 'a list -> 'a = <fun> *)
z [’a’; ’b’; ’c’];;                                                           (* -: char = 'c' *)
z [ [1;2;3]; [4;5; 6]; [7] ];;                                                (* -: int list = [7] *)
z [ cos; sin; tan ];;                                                         (* -: float -> float = <fun>  *)
z [];;                                                                        (* error *)