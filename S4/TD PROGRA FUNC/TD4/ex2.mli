let (answer_num, answer_string) = (42, "Forty-Two");;               (* val answer_num : int = 42 \n val answer_string : string = "Forty-Two"*)
let (answer_num’, _) = (42, "Forty-Two");;                          (* error " ’ " *)
let (x, (y, z)) = (1, (2, 3));;                                     (* val x : int = 1 ; val y : int = 2 ; val z : int = 3 *)
let middle = match [1; 2; 3] with                                   (* val middle : int list = [2] *)
            | [] -> []                                              
            | [_ ; n; _] -> [ n ]                                   
            | _ -> [];;                                             
let rec combine = fun (l1,l2) ->                                    (* val combine : 'a list * 'b list -> 'a * 'b list = <fun>*)
    match (l1, l2) with                                             
    | ([], _) -> []                                                 
    | (_, []) -> []                                                 
    | (h1::t1, h2::t2) -> (h1, h2)::(combine(t1, t2));;             
combine([1;2;3], [1.; 2.; 3.]);;                                    (* -: (int * float) list = [(1,1.);(2,2.);(3,3.)]  *)
combine([], ["Bonjour";"tout";"le";"monde"]);;                      (* -: ('a * string) list = [] *)
let f = fun n -> match n with                                       (* val f : int -> string = <fun> *)
                | 1 -> "Red"                                        
                | 2 -> "Green"                                      
                | 3 -> "Blue"                                       
                | _ -> "Not a color";;                              
f answer_num;;                                                      (* -: string = "Not a color" *)
f x;;                                                               (* -: string = "Red" *)                                                               (*  *)
f z;;                                                               (* -: string = "Blue" *)
f y;;                                                               (* -: string = "Green" *)
f(-1);;                                                             (* -: string = "Not a color" *)