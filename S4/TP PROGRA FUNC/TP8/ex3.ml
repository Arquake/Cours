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

  let evaluate: 'a expression -> ('a, int) Env.t -> int = fun exp ele ->
    let binary_opp = fun f v1 v2 ->
      match f with
      | Add -> v1 + v2
      | Sub -> v1 - v2
      | Mul -> v1 * v2
      | Div -> v1 / v2
      | Mod -> v1 - (v1 / v2) * v2
      | Max -> if v1 > v2 then v1 else v2
    in
    let unary_opp = fun f v1 ->
      match f with
      | Opp -> (-v1)
      | Abs -> if v1 < 0 then (-v1) else v1
      | Sqr -> v1 * v1
    in

    let rec aux =
      function
      | Var(x) -> if (Env.mem x ele) then Env.find x ele else raise (Invalid_argument "variable inconnu")
      | Int(x) -> x
      | Unary(express,x) -> unary_opp express (aux x)
      | Binary(express,x,y) -> binary_opp express (aux x) (aux y)
    in
    aux exp
end


(* Q2 *)
module TreeAssoc =
struct
  type 'a binary_tree =
  | Empty
  | Node of 'a * 'a binary_tree * 'a binary_tree

  type ('a, 'b) t = ('a * 'b) binary_tree

  (** Retourne un dictionnaire vide. *)
  let empty: ('a, 'b) t = Empty

  (** Teste si le dictionnaire pass´e en param`etre est vide. *)
  let is_empty: ('a, 'b) t -> bool = function | Node(_,_,_) -> true | _ -> false

  (** (mem key dictionary) renvoie true si le dictionnaire contient
  la cl´e key, et false sinon. *)
  let mem: 'a -> ('a, 'b) t -> bool = fun ele l ->
    let rec check =
      function
      | Node((k,v),_,_) when k = ele -> true
      | Node((k,v),l,_) when k > ele -> check l
      | Node((k,v),_,r) when k < ele -> check r
      | Empty -> false
    in
    check l

  (** (find_opt key dictionary) renvoie (Some value) si la valeur
  value est associ´ee `a la cl´e key dans le dictionnaire, et
  renvoie None sinon. *)
  let find_opt: 'a -> ('a, 'b) t -> 'b option = fun ele l ->
    let rec check =
      function
      | Node((k,v),_,_) when k = ele -> Some(v)
      | Node((k,v),l,_) when k > ele -> check l
      | Node((k,v),_,r) when k < ele -> check r
      | Empty -> None
    in
    check l

  (** (find key dictionary) renvoie une valeur si cette valeur
  est associ´ee `a la cl´e key dans le dictionnaire, et l`eve
  l'exception Not_found sinon. *)
  let find: 'a -> ('a, 'b) t -> 'b = fun ele l ->
    let rec check =
      function
      | Node((k,v),_,_) when k = ele -> v
      | Node((k,v),l,_) when k > ele -> check l
      | Node((k,v),_,r) when k < ele -> check r
      | Empty -> raise Not_found
    in
    check l

  (** (remove key dictionary) renvoie un dictionnaire dans
  lequel la cl´e key n'est plus associ´e `a aucune valeur. *)
  let remove: 'a -> ('a, 'b) t -> ('a, 'b) t = fun ele li ->
    let rec get_min =
      function
      | Node((k,v),l,_) when l <> Empty -> get_min l
      | Node((k,v),Empty,_) -> (k,v)
    in
    
    let rec check = 
      function
      | Node((k,v),l,r) when k = ele -> if r <> Empty then
            let min = get_min r in
            let rest = Node(min,l,r) in check rest 
          else l
      | Node((k,v),l,r) when k > ele -> Node((k,v),check l,r)
      | Node((k,v),l,r) when k < ele -> Node((k,v),l,check r)
      | Empty -> Empty
    in
    check li

  (** (add key value dictionary) ajoute une association entre
  key et value dans le dictionary. Si une association
  existait d´ej`a avec la m^eme cl´e, cette pr´ec´edente association
  est supprim´ee. *)
  let add: 'a -> 'b -> ('a, 'b) t -> ('a, 'b) t = fun key value tree ->
    let rec adding =
      function
      | Node((k,v),l,r) when k = key -> Node((k,v),l,r)
      | Node((k,v),l,r) when k > key -> Node((k,v),adding l,r)
      | Node((k,v),l,r) when k < key -> Node((k,v),l,adding r)
      | Empty -> Node((key,value),Empty,Empty)
    in
    adding tree

end

module ListAssoc = 
  struct
  type ('a, 'b) t = | None | LIST_ASSOC of ('a*'b) list

    let empty: ('a, 'b) t = None

    let is_empty: ('a, 'b) t -> bool = function | None -> true | _ -> false
                                           
    let mem: 'a -> ('a, 'b) t -> bool = fun ele li ->
      let rec aux = fun l ->
        match l with
        | (k,v)::_ when k = ele -> true
        | (k,v)::t -> aux t
        | _ -> false
      in 
      match li with
      | None -> false
      | LIST_ASSOC(list) -> aux list

    let find_opt: 'a -> ('a, 'b) t -> 'b option = fun ele li ->
      let rec aux = fun l ->
        match l with
        | (k,v)::_ when k = ele -> Some(v)
        | (k,v)::t -> aux t
        | _ -> None
      in 
      match li with
      | None -> None
      | LIST_ASSOC(list) -> aux list


    let find: 'a -> ('a, 'b) t -> 'b = fun ele li ->
    let rec aux = fun l ->
      match l with
      | (k,v)::_ when k = ele -> v
      | (k,v)::t -> aux t
      | _ -> raise Not_found
    in 
    match li with
    | LIST_ASSOC(list) -> aux list
    | None -> raise Not_found

    let remove: 'a -> ('a, 'b) t -> ('a, 'b) t = fun ele li ->
      let rec aux = fun l list_rest->
        match l with
        | (k,v)::t when k = ele -> list_rest @ t
        | (k,v)::t -> aux t (list_rest @ [(k,v)])
        | _ -> list_rest
      in 
      match li with
      | LIST_ASSOC(list) -> LIST_ASSOC(aux list [])
      | None -> None

    let add: 'a -> 'b -> ('a, 'b) t -> ('a, 'b) t = fun k v list ->
      match list with
      | LIST_ASSOC(list) -> LIST_ASSOC((k,v) :: list)
      | None -> LIST_ASSOC([(k,v)])

end

let arbre = TreeAssoc.empty;;
let arbre = TreeAssoc.add "x" 3 arbre;;
let arbre = TreeAssoc.add "y" 7 arbre;;
let arbre = TreeAssoc.add "a" 1 arbre;;
let arbre = TreeAssoc.add "b" 10 arbre;;

let list_assoc = ListAssoc.empty;;
let list_assoc = ListAssoc.add "x" 3 list_assoc;;
let list_assoc = ListAssoc.add "y" 7 list_assoc;;
let list_assoc = ListAssoc.add "a" 1 list_assoc;;
let list_assoc = ListAssoc.add "b" 10 list_assoc;;

module ListEval = Evaluation(ListAssoc);;
module TreeEval = Evaluation(TreeAssoc);;

let premier = ListEval.Binary( Add, Binary( Mul, Var "x", Int 2 ), Int(1) );;

let second = ListEval.Binary( Add, Binary( Mod, Int(42), Int(7) ), Binary( Mul, Int(12), Int(2) ) );;
  
let troisieme = ListEval.Binary( Div, Binary(Add,Var "a",Var "b"), Binary(Sub,Var "a",Var "b") );;

let quatrieme = ListEval.Binary( Add, Int(1), Binary( Max, Var "x", Int 0 ) );;

let cinquieme = ListEval.Unary( Sqr, Unary( Abs, Binary( Sub, Var "x", Var "y" ) ) );;

let six = ListEval.Binary(Add,Unary(Opp,Var "x"),Int(2));;


ListEval.evaluate premier list_assoc;;
ListEval.evaluate second list_assoc;;
ListEval.evaluate troisieme list_assoc;;
ListEval.evaluate quatrieme list_assoc;;
ListEval.evaluate cinquieme list_assoc;;
ListEval.evaluate six list_assoc;;


let premier = TreeEval.Binary( Add, Binary( Mul, Var "x", Int 2 ), Int(1) );;

let second = TreeEval.Binary( Add, Binary( Mod, Int(42), Int(7) ), Binary( Mul, Int(12), Int(2) ) );;
  
let troisieme = TreeEval.Binary( Div, Binary(Add,Var "a",Var "b"), Binary(Sub,Var "a",Var "b") );;

let quatrieme = TreeEval.Binary( Add, Int(1), Binary( Max, Var "x", Int 0 ) );;

let cinquieme = TreeEval.Unary( Sqr, Unary( Abs, Binary( Sub, Var "x", Var "y" ) ) );;

let six = TreeEval.Binary(Add,Unary(Opp,Var "x"),Int(2));;



TreeEval.evaluate premier arbre;;
TreeEval.evaluate second arbre;;
TreeEval.evaluate troisieme arbre;;
TreeEval.evaluate quatrieme arbre;;
TreeEval.evaluate cinquieme arbre;;
TreeEval.evaluate six arbre;;