let v1 = None;;                                               val v1 : 'a option = None
let v2 = Some 12 in match v2 with Some x -> x | _ -> 0;;      -: int = 12
type ('a,'b) assoc_list =                                     val v3 : ('a, 'b ) assoc_list = Empty
  | Empty                                                     
  | Pair of 'a * 'b * ('a,'b) assoc_list                      
let v3 = Empty;;                                              
let v4 = Pair(1, "One", Empty);;                              val v4 : (int,string) assoc_list = Pair(1, "One", Empty) 
let v5 = Pair(2, "Two", Empty);;                              val v5 : (int,string) assoc_list = Pair(2, "Two", Empty) 
let rec f = fun key -> fun list ->                            val f : 'a -> ('a, 'b) assoc_list -> 'b option = <fun> -> string 
  match list with                                             
  | Empty -> None                                             
  | Pair(key', value', tail) when key = key' -> Some value'   
  | Pair(_, _, tail)  -> f key tail;;                         
f 3 v4;;                                                      -: string option = None
f 2 v5;;                                                      -: string option = "Two"
type 'a cell = { head : 'a; tail : 'a list' }                  type cell = { head : 'a ; tail : 'a list';}
and  'a list' = 'a cell option;;                              type list' = 'a cell option
let empty : 'a list' =  None;;                                val empty : 'a list' = None
let (+:) : 'a -> 'a list' -> 'a list' =                       val (+:) : 'a -> 'a list' -> 'a list' = <fun>
  fun head -> fun tail -> Some { head = head; tail = tail };; 
1 +: (2 +: (3 +: empty));;                                    -: int list' = Some{head=1;tail=Some{head=2;tail=Some{head=3;tail=None}}}