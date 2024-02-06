(* Q1 *)

let tester = fun 'a list * 'b list -> 'c list list (functions, iteratable) -> 
    let rec apply = fun (func,lis) -> 
      if UOList.tl func = [] 
      then 
        [UOList.map UOList.hd func,lis]
      else
        [UOList.map UOList.hd func,lis] @ apply (UOList.tl func, lis)
    in 
    if functions != []
    then 
      apply functions,iteratable;;

(* Correct *)

let tester = fun(lf,l) -> UOList.map ( fun f -> UOList.map(f,l),lf);;

(* Q2 *)

let tester = fun(lf,l) -> let lis = tester lf,l in UOList.flatten lis;;

(* Q3 *)

let run_all_test = fun (predi,values) -> 
    if ( 
        UOList.length (
            UOList.filter (
                fun x -> x=false,tester (predi,values)
            )
        ) = 0 
    )
    then "OK" else "KO" ;;