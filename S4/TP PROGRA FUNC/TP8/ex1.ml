module type DICTIONARY =
  sig
    type (’a, ’b) t
    (** Retourne un dictionnaire vide. *)
    val empty: (’a, ’b) t
    (** Teste si le dictionnaire pass´e en param`etre est vide. *)
    val is_empty: (’a, ’b) t -> bool
    (** (mem key dictionary) renvoie true si le dictionnaire contient
    la cl´e key, et false sinon. *)
    val mem: ’a -> (’a, ’b) t -> bool
    (** (find_opt key dictionary) renvoie (Some value) si la valeur
    value est associ´ee `a la cl´e key dans le dictionnaire, et
    renvoie None sinon. *)
    val find_opt: ’a -> (’a, ’b) t -> ’b option
    (** (find key dictionary) renvoie une valeur si cette valeur
    est associ´ee `a la cl´e key dans le dictionnaire, et l`eve
    l’exception Not_found sinon. *)
    val find: ’a -> (’a, ’b) t -> ’b
    (** (remove key dictionary) renvoie un dictionnaire dans
    lequel la cl´e key n’est plus associ´e `a aucune valeur. *)
    val remove: ’a -> (’a, ’b) t -> (’a, ’b) t
    (** (add key value dictionary) ajoute une association entre
    key et value dans le dictionary. Si une association
    existait d´ej`a avec la m^eme cl´e, cette pr´ec´edente association
    est supprim´ee. *)
    val add: ’a -> ’b -> (’a, ’b) t -> (’a, ’b) t
    
    end