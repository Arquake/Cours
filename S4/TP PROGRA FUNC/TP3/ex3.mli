let palindrome = fun s ->
    let rec check = fun (s,r) ->
      if String.length s / 2 < r then true
      else
      if (String.get s r) = (String.get s (String.length s - r - 1) )
      then check (s,r+1)
      else false
    in check (s,0);;