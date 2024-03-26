module type PERSONNE =
  sig
    (** Le type t est un type pour repr´esenter une personne :
    son nom, son pr´enom, et son ^age (en ann´ees). *)
    type t
    (** (creer nom prenom age) cr´eer une personne dont
    le nom est nom, le pr´enom prenom, et l’^age age.
    L’^age doit ^etre positif, sinon une exception
    Invalid_argument "creer: age invalide" est lev´ee. *)
    val creer: string -> string -> int -> t
    (** (nom p) retourne le nom de la personne p. *)
    val nom: t -> string
    (** (prenom p) retourne le pr´enom de la personne p. *)
    val prenom: t -> string
    (** (age p) retourne l’^age de la personne p. *)
    val age: t -> int
    (** (meme_identite p1 p2) renvoie true si les deux
    personnes p1 et p2 ont le m^eme nom et pr´enom,
    3
    faux sinon. *)
    val meme_identite: t -> t -> bool
    (** (plus_jeune p1 p2) retourne true si la personne p1
    est strictement moins ^ag´ee que la personne p2. *)
    val plus_jeune: t -> t -> bool
  end

module Personne_E =
  struct
    type t = {nom:string;prenom:string;age:int}
    (** (creer nom prenom age) cr´eer une personne dont
    le nom est nom, le pr´enom prenom, et l’^age age.
    L’^age doit ^etre positif, sinon une exception
    Invalid_argument "creer: age invalide" est lev´ee. *)
    let creer = fun nom_p prenom_p age_p ->
      if age_p < 0 then raise (Invalid_argument "creer: age invalide") else {nom=nom_p;prenom=prenom_p;age=age_p}
    (** (nom p) retourne le nom de la personne p. *)
    let nom = fun ele -> ele.nom
    (** (prenom p) retourne le pr´enom de la personne p. *)
    let prenom = fun ele -> ele.prenom
    (** (age p) retourne l’^age de la personne p. *)
    let age = fun ele -> ele.age
    (** (meme_identite p1 p2) renvoie true si les deux
    personnes p1 et p2 ont le m^eme nom et pr´enom,
    3
    faux sinon. *)
    let meme_identite = fun p1 p2 -> if p1.nom = p2.nom && p1.prenom = p2.prenom && p1.age = p2.age then true else false
    (** (plus_jeune p1 p2) retourne true si la personne p1
    est strictement moins ^ag´ee que la personne p2. *)
    let plus_jeune = fun p1 p2 -> if p1.age < p2.age then p1 else p2
  end


module Personne_L =
  struct
    type t = {nom:string list;prenom:string list;age:int list}
      

    let creer = fun nom_p prenom_p age_p ->
      if age_p < 0 then raise(Invalid_argument "creer: age invalide") else {nom=[nom_p];prenom=[prenom_p];age=[age_p]}
        
    
    let nom = fun ele ->
      let rec aux = fun e rest ->
        match e with
        | [] -> rest
        | h::t -> aux e (h::rest)
      in aux ele.nom []
        
          
    let prenom = fun ele ->
      let rec aux = fun e rest ->
        match e with
        | [] -> rest
        | h::t -> aux e (h::rest)
      in aux ele.prenom []
        
          
    let age = fun ele ->
      let rec aux = fun e rest ->
        match e with
        | [] -> rest
        | h::t -> aux e (h::rest)
      in aux ele.age []
        
    let meme_identite = fun p1 p2 ->
      let rec aux = fun p1aux p2aux ->
        match p1aux with
        | [] when p2aux = [] -> true
        | h::t when List.hd p2aux = h -> aux t (List.tl p2aux)
        | _ -> false
      in (aux p1.nom p2.nom) && (aux p1.prenom p2.prenom) && (aux p1.age p2.age)
                                                            
    let plus_jeune = fun p1 p2 ->
      let rec aux = fun ele sum ->
        match ele with
        | [] -> sum
        | h::t -> aux t (h+sum)
      in (aux p1.age 0) < (aux p2.age 0)
  end