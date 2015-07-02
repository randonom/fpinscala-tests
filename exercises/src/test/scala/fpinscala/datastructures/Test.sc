import fpinscala.datastructures._

val l = List(1,2,3,4)
val c = Cons(1,l)

List.drop(c,0)
List.dropWhile(List(7,1,2,10),(x:Int)=> x < 6)

List.init(List(1,2,3,4,5))

List.sum(Nil)
val add = (x:Int, y:Int) => x+ y
val product = (x:Int, y:Int) => x * y
List.foldRight(Cons(1,Nil),0)(add)
List.foldLeft(List(1,2,3,4),0)(add)
List.foldLeft(List(1,2,3,4),1)(product)

List.sum3(l)
List.product3(List())
List.length2(l)
List.setTail(5,List(1,2,3,4))

List.reverseList(List(1,2,3,4))

//List.product2(List(1.0,2.0,3.0))
//List.product(List(1.0,2.0,3.0))




