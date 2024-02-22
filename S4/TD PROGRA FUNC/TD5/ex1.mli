(* Q1 *)

(* type quantite = int *)
(* var quantite_topinambour : int = 12*)
(* var quantite_patates : quantite = 9 *)
(* type tuberrcule = Patate | Topinambour *)
(* type stock = { aliment : tubercule ; qtte : quantite;} *)
(* -: quantite = 1 *)
(* val est_bon : tubercule -> bool = <fun> *)
(* type inventaire = stock list *)
(* val bon_stock : stock list -> stock list = <fun> *)
(* val mon_stock :stock list = [
                                { aliment = Patate ; qtte = 3};
                                { aliment = Topinambour ; qtte = 1};
                                { aliment = Patate ; qtte = 1};
                                { aliment = Topinambour ; qtte = 19}
                               ] *)
(* val nb_bons_aliments : stock list -> int = <fun> *)
(* -: int = 4 *)


type quantite = int;;
let quantite_topinambour = 12;;
let quantite_patates : quantite = 9;;
type tubercule = Patate | Topinambour;;
type stock = {aliment : tubercule ; qtte : quantite};;
let s = {aliment = Patate ; qtte = 1} in s.qtte;;
let est_bon = fun t ->
  match t with
  | Patate -> true
  | Topinambour -> false;;
type inventaire = stock list;;
let bon_stock = List.filter (fun s -> (est_bon s.aliment));;
let mon_stock = [
  { aliment = Patate ; qtte = 3};
  { aliment = Topinambour ; qtte = 1};
  { aliment = Patate ; qtte = 1};
  { aliment = Topinambour ; qtte = 19}
];;

let rec nb_bons_aliments = fun s ->
  match (bon_stock s) with
  | [] -> 0
  | h::t -> (h.qtte) + nb_bons_aliments t;;
nb_bons_aliments mon_stock;;