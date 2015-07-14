import fpinscala.datastructures._

val a = List(1,2,3,4)
val s = List("a","b","c")
val d = List(1.0,2.0,3.0)


//Ques 1: why does result gets represented in Cons(..) and not List


// 3.1
println("3.1")
List.x

// 3.2
println("3.2")
List.tail(a)
List.tail(Nil)
List.tail(List.tail(a))

// 3.3
println("3.3")
List.setHead(a,0)
List.setHead(Nil,1)
List.setHead(List(),1)
List.setHead(List(1),0)

// 3.4
println("3.4")
List.drop(a,1)
List.drop(a,3)
List.drop(a,5)
List.drop(a,0)

//3.5
println("3.5")
List.dropWhile(List(),(n: Int) => true)
List.dropWhile(a,(n: Int) => true)
List.dropWhile(a,(n: Int) => false)
List.dropWhile(a,(n: Int) => n < 4)

//3.6
println("3.6")
List.init(a)
List.init(List.init(a))
List.init(List(1))

//3.7
println("3.7")
List.productFR(d)
List.productFR(List())

//3.8
println("3.8")
List.foldRight(a,Nil:List[Int])((a,b)=>Cons(a,b))
List.foldRight(s,"x")((a,b) => a + b)

// 3.9
println("3.9")
List.lengthFR(a)
List.lengthFR(Nil)

//3.10
println("3.10")
List.foldLeft(a,Nil:List[Int])((b,a)=>Cons(a,b))
List.foldLeft(s,"x")((a,b) => a + b)

//3.11
println("3.11")
List.sumFL(a)
List.productFL(d)
List.lengthFL(a)

// 3.12
println("3.12")
List.reverse(a)
List.reverse(List(1))
List.reverse(s)

// 3.13 ???

// 3.14
println("3.14 - Fold Right")
List.appendFR(a,List(5,6))
List.appendFR(a,List(5,6,7,8))
List.appendFR(List(1),List(5,6))
List.appendFR(Nil,a)
List.appendFR(a,Nil)
List.appendFR(a,s) // ?? append int and string

println("3.14 - Fold Left")
List.appendFL(a,List(5,6))
List.appendFL(Nil,a)
List.appendFL(a,Nil)
List.appendFL(a,s)

//List.setTail(a,5)

//3.15
println("3.15")
List.concat(List(a,List(5,6,7)),List(List(8,9),List(10,11,12)))
List.concat(List(a,Nil),List(Nil,a))

//3.16
println("3.16")
List.addOneFL(a)
List.addOneFR(a)

//3.17
println("3.17")
List.doubleToString(d)

//3.18
println("3.18")
List.map(a)(a => a+1)
List.map(a)(a => a*2)
List.map(a)(a => a)

//3.19
println("3.19")
List.filter(List(2,3,5,8,13))(a => a%2==0)
List.filter(a)(a => true)
List.filter(a)(a => false)

//3.20
println("3.20")
List.flatMap(a)(i => List(i,i))
List.flatMap(a)(i => Cons(i,Nil))

//3.21
println("3.21")
List.filterFlatMap(List(2,3,5,8,13))(a => a%2==0)
List.filterFlatMap(a)(a => true)
List.filterFlatMap(a)(a => false)

//3.22
println("3.22")
List.addListItems(a,a)
List.addListItems(a,List(1))
List.addListItems(List(1),a)


//3.22
println("3.23")

println("hasSubSequence")
List.hasSubSequence(List(1,2), List(1,1,1))
