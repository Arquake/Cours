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