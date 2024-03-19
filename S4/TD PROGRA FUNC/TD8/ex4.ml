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