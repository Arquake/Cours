(* Question 1 *)

let at_least_one_true = fun fun1 ->
    if fun1 true || fun1 false
    then true
    else false;;


(* Question 2 *)

let tauto = fun func -> 
    if func(false,false,false)
    then 
      if func(false,true,false)
      then 
        if func(false,false,true)
        then 
          if func(false,true,true)
          then 
            if func(true,false,false)
            then 
              if func(true,true,false)
              then 
                if func(true,false,true)
                then 
                  if func(true,true,true)
                  then true
                  else false 
                else false 
              else false 
            else false 
          else false 
        else false
      else false 
    else false ;;


let tauto = fun func -> 
    if func(false,false,false) &&
        func(false,true,false) &&
        func(false,false,true) &&
        func(false,true,true) &&
        func(true,false,false) && 
        func(true,true,false) && 
        func(true,false,true) && 
        func(true,true,true)
    then true
    else false ;;

tauto (fun (x, y, z) -> (x || (not x)) && (y=true || y=z || y<> z));;
tauto (fun (x,y,z) -> true);;
tauto (fun (x,y,z) -> x || y || z);;
tauto (fun (x,y,z) -> x && (y || z));;