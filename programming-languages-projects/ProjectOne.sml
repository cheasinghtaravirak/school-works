(*	1. Write a function cube of	type int -> int	that returns the cube of its parameter *)
fun	cube x = x*x*x;

(* 2. Write a function sqsum of type int -> int that takes an non-negative integer n 
and returns the sum of the squares of all the integers 0 through n. Your 
function need not behave well on negative input *)

fun	sqsum n = if n = 0 then 0 
else n*n + sqsum(n-1);

(* 3. Write a function max of type int list -> int that returns the largest element of 
a list of integers. Your function need not behave well if the list is empty. *)

(* Create a function to test whether an element is a max. value in a list *)
fun maxhelper(ls, max) 
= if null ls then max 
else if hd(ls) > max then maxhelper(tl ls, hd ls)
else maxhelper(tl ls, max)

fun max ls = maxhelper(ls, hd ls); 


