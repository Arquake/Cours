(* Q1 *)

type son = Silence | Note of int;;
type ligne = {instrument : string; sons : son list};;
type piste = {titre:string; auteur:string; ligne_musicales:ligne list};;


(* Q2 *)

let transpose = fun musique -> fun n ->

  let rec modif = fun (liste,nth) ->
    match liste with
    | [] -> []
    | [ele] -> [ele]
    | h::t -> if nth > 0 then modif(t @ [h], nth-1) else liste
  in 
  
  let rec iterate = fun (mus,did) ->
    match mus with
    | [] -> List.rev did
    | h::t -> iterate (t, {
        instrument = h.instrument;
        sons =
          (modif (h.sons, (List.length h.sons) - n)) 
      } 
        ::did)
  
  in { 
    titre= musique.titre; 
    auteur = musique.auteur;
    ligne_musicales = iterate (musique.ligne_musicales,[])
  };;


(* Q3 *)

let who_plays = fun musique n ->
  let rec checking = fun (p,current) ->
    match p with
    | [] -> false
    | h::t -> if current = 1 then if h = Silence then false else true else checking(t,current-1)
          
  in 
  let rec playing = fun (pistes,res) ->
    match pistes with
    | [] -> res
    | h::t -> if checking(h.sons,n) then playing(t,h.instrument::res) else playing (t,res) 
  in List.rev (playing(musique.ligne_musicales,[]));;


(* Q4 *)

type son = Silence | Note of int | Syllable of string;;




(* val test *)


let r = {
  titre="t";
  auteur="t";
  ligne_musicales=[
    {instrument="a" ; sons = [
         Silence;Note(1);Silence;Note(2);Silence;Note(3);Silence;Note(4)
       ]};
    {instrument="b" ; sons = [
         Silence;Note(1);Silence;Silence;Silence;Note(3);Silence;Note(4)
       ]};
    {instrument="c" ; sons = [
         Silence;Silence;Silence;Note(2);Silence;Silence;Silence;Note(4)
       ]};
    {instrument="d" ; sons = [
         Silence;Note(1);Silence;Note(2);Silence;Note(3);Silence;Silence
       ]};
  ]
};;

who_plays r 2;;