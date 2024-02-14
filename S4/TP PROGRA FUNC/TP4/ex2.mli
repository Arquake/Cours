(* Q1 *)
let insert_increasing = fun element ->
    fun l ->
        let rec recursion = fun (ele,start_list,end_list) ->
        match start_list with

        (* si la liste est vide on renvoit end_list concaténé avec l'element *)
        | [] -> end_list @ [ele]

        (* si il n'y a que un element dans la liste on vérifie si il est plus ou moins grand et on lisère avant ou après *)
        | f::[] -> if (ele < f) 
            then 
                end_list @ [ele;f]
            else 
                end_list @ [f;ele]

        | f::s::rest -> if (f <= ele && ele <= s)
            then 
                end_list @ [f;ele;s] @ rest 
            else
                if ele < f 
                then
                     end_list @ [ele;f;s] @ rest 
                else
                    recursion (ele, s::rest, end_list @ [f]  )
        in
        recursion (element,l,[]);;


(* Q2 *)

let sort_increasing = fun l ->
    let rec recur = fun (li,result) ->
        match li with
        | [] -> []
        | h::[] -> insert_increasing h result
        | h::rest -> recur(rest, insert_increasing h result)
    in
    recur(l,[]);;


(* Q3 *)

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