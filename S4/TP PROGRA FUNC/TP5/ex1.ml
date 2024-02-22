(* Q1 *)

type vertex = Sommet of int;;
type edge = {source : vertex ; cible : vertex};;

(* Q2 *)

type graph = {verticies : vertex list ; edges : edge list};;

(* Q3 *)

type sommet_ou_rien = Rien | Sommet of vertex;;

let sommet_quelconque = fun g -> 
  match g.verticies with
  | [] -> Rien
  |  h::_-> Sommet h;;

let g = {verticies = [Sommet 5; Sommet 9; Sommet 10; Sommet 3] ; edges = []};;
sommet_quelconque g;;