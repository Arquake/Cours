let add = fun (num1, num2) -> num1 + num2;;
et sub = fun (num1, num2) -> num1 - num2;;
dd (1,2);;
ub (1,2);;
et succ = fun num -> add (num, 1);;
ucc;;
ucc 41;;
et pred = fun num -> sub (num, 1);;
red;;
pred (succ 42);;
let choose_operation = fun increment -> if increment then succ else pred;;
choose_operation true;;
(choose_operation false) 1;;
(choose_operation true) 1;;