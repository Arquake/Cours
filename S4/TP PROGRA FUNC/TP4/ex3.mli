(* Q1 *)

let category = fun reference ->
    if String.length reference != 3 then false
    else
        let rec check_letter = fun (chain,n) ->
            if String.length chain <= n then true 
            else
                match String.get chain n with
                | 'A' .. 'Z' -> check_letter (chain, n+1)
                | _ -> false
        in check_letter(reference,0);;



let sous_reference = fun reference ->
    if String.length reference != 10 then false
    else
        let rec check_letter = fun (chain,n) ->
            if String.length chain <= n then true 
            else
                match String.get chain n with
                | '0' .. '9' -> check_letter (chain, n+1)
                | _ -> false
        in check_letter(reference,0);;

let is_valid_reference = fun reference ->
    if String.length reference != 13 then false else
    if (category (String.sub reference 0 3)) && 
        (sous_reference (String.sub reference 3 (String.length reference - 3)))
        then true 
    else false;;


(* Q2 *)

let rec check_database = fun list ->
    match list with
    | [] -> true
    | h::t -> if is_valid_reference h then check_database t else false;;


(* Q3 *)

let get_category = fun reference ->
    String.sub reference 0 3;;

let get_subreference = fun reference ->
    if sous_reference (String.sub reference 3 (String.length reference - 3)) 
    then 
        int_of_string((String.sub reference 3 (String.length reference - 3)))
    else
        (-1);;


(* Q4 *)

let sort_database = fun db ->
    sort (fun (x) (x') -> 
        if (String.sub x 3 (String.length x - 3)) <
            (String.sub x' 3 (String.length x' - 3)) 
        then true 
    else 
        if String.sub x 0 3 < String.sub x' 0 3 
            then true 
else false) 
db;;


let insert = fun operator ->
    fun number -> 
    fun list ->
        let rec recursion = fun (ele,start_list,end_list) ->
        match start_list with
                
                        (* si la liste est vide on renvoit end_list concaténé avec l'element *)
        | [] -> end_list @ [ele]
                
                        (* si il n'y a que un element dans la liste on vérifie si il est plus ou moins grand et on lisère avant ou après *)
        | f::rest -> if (operator ele f) 
            then 
                end_list @ [ele;f] @ rest
            else 
                recursion (ele,rest,end_list @ [f])
        in
        recursion (number,list,[]);;


let sort = fun operator -> fun list ->
    let rec recur = fun (li,result) ->
        match li with
        | [] -> []
        | h::[] -> insert operator h result
        | h::rest -> recur(rest, insert operator h result)
    in
    recur(list,[]);;

let database = ["ABC1234567890";"ACB2234567890";"ACB2134567890"];;
sort_database database;;