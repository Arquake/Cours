module type DICTIONARY =
  sig
    type ('a, 'b) t

    (** Retourne un dictionnaire vide. *)
    val empty: ('a, 'b) t

    (** Teste si le dictionnaire pass´e en param`etre est vide. *)
    val is_empty: ('a, 'b) t -> bool

    (** (mem key dictionary) renvoie true si le dictionnaire contient
    la cl´e key, et false sinon. *)
    val mem: 'a -> ('a, 'b) t -> bool

    (** (find_opt key dictionary) renvoie (Some value) si la valeur
    value est associ´ee `a la cl´e key dans le dictionnaire, et
    renvoie None sinon. *)
    val find_opt: 'a -> ('a, 'b) t -> 'b option

    (** (find key dictionary) renvoie une valeur si cette valeur
    est associ´ee `a la cl´e key dans le dictionnaire, et l`eve
    l'exception Not_found sinon. *)
    val find: 'a -> ('a, 'b) t -> 'b

    (** (remove key dictionary) renvoie un dictionnaire dans
    lequel la cl´e key n'est plus associ´e `a aucune valeur. *)
    val remove: 'a -> ('a, 'b) t -> ('a, 'b) t

    (** (add key value dictionary) ajoute une association entre
    key et value dans le dictionary. Si une association
    existait d´ej`a avec la m^eme cl´e, cette pr´ec´edente association
    est supprim´ee. *)
    val add: 'a -> 'b -> ('a, 'b) t -> ('a, 'b) t
    
    end

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
    let rec check = fun li ->
      match li with
      | Node(v,_,_) when v = ele -> true
      | Node(v,r,_) when v > ele -> check r
      | Node(v,_,l) when v < ele -> check l
      | Empty -> false
    in
    match l with
    | Node(v,r,l) -> check Node(v,r,l)
    | Empty -> false

  (** (find_opt key dictionary) renvoie (Some value) si la valeur
  value est associ´ee `a la cl´e key dans le dictionnaire, et
  renvoie None sinon. *)
  let find_opt: 'a -> ('a, 'b) t -> 'b option = fun ele l ->
    let rec check = fun li ->
      match li with
      | Node(v,_,_) when v = ele -> Some(v)
      | Node(v,r,_) when v > ele -> check r
      | Node(v,_,l) when v < ele -> check l
      | Empty -> None
    in
    match l with
    | Node(v,r,l) -> check Node(v,r,l)
    | Empty -> None

  (** (find key dictionary) renvoie une valeur si cette valeur
  est associ´ee `a la cl´e key dans le dictionnaire, et l`eve
  l'exception Not_found sinon. *)
  let find: 'a -> ('a, 'b) t -> 'b = fun ele l ->
    let rec check = fun li ->
      match li with
      | Node(v,_,_) when v = ele -> v
      | Node(v,r,_) when v > ele -> check r
      | Node(v,_,l) when v < ele -> check l
      | Empty -> raise Not_found
    in
    match l with
    | Node(v,r,l) -> check Node(v,r,l)
    | Empty -> raise Not_found

  (** (remove key dictionary) renvoie un dictionnaire dans
  lequel la cl´e key n'est plus associ´e `a aucune valeur. *)
  let remove: 'a -> ('a, 'b) t -> ('a, 'b) t = fun ele l ->
    let rec remonter = fun n ->
      | Node(v,r,Node(v1,r1,l1)) -> Node(v1,r,remove l1)
      | Node(v,r,Empty) ->
      | Empty -> Empty

    in
    let rec check = fun li ->
      match li with
      | Node(v,r,l) when v = ele -> v
      | Node(v,r,_) when v > ele -> Node(v,check r,_)
      | Node(v,_,l) when v < ele -> Node(v,_,check l)
      | Empty -> Node(ele,Empty,Empty)
    in
    match l with
    | Node(v,r,l) -> check Node(v,r,l)
    | Empty -> Node(ele,Empty,Empty)

  (** (add key value dictionary) ajoute une association entre
  key et value dans le dictionary. Si une association
  existait d´ej`a avec la m^eme cl´e, cette pr´ec´edente association
  est supprim´ee. *)
  val add: 'a -> 'b -> ('a, 'b) t -> ('a, 'b) t
end