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