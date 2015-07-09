package fpinscala.datastructures

import scala.annotation.tailrec

sealed trait List[+A] // `List` data type, parameterized on a type, `A`
case object Nil extends List[Nothing] // A `List` data constructor representing the empty list
/* Another data constructor, representing nonempty lists. Note that `tail` is another `List[A]`,
which may be `Nil` or another `Cons`.
 */
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {
  // `List` companion object. Contains functions for creating and working with lists.
  def sum(ints: List[Int]): Int = ints match {
    // A function that uses pattern matching to add up a list of integers
    case Nil => 0 // The sum of the empty list is 0.
    case Cons(x, xs) => x + sum(xs) // The sum of a list starting with `x` is `x` plus the sum of the rest of the list.
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x, xs) => x * product(xs)
  }

  def apply[A](as: A*): List[A] = // Variadic function syntax
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  val x = List(1, 2, 3, 4, 5) match {
    case Cons(x, Cons(2, Cons(4, _))) => x
    case Nil => 42
    case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
    case Cons(h, t) => h + sum(t)
    case _ => 101
  }

  def tail[A](as: List[A]): List[A] = as match {
    case Nil => Nil
    case Cons(h, t) => t
  }

  def setHead[A](as: List[A], a: A): List[A] = as match {
    case Nil => List(a)//Cons(a, Nil)
    case Cons(h, t) => Cons(a, t)
  }

  @tailrec
  def drop[A](l: List[A], n: Int): List[A] = l match {
    case Nil => Nil
    case _ if (n == 0) => l
    case Cons(h, t) => drop(t, n - 1)
  }

  @tailrec
  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = l match {
    case Cons(h, t) if (f(h)) => dropWhile(t, f)
    case _ => l
  }

  def append[A](a1: List[A], a2: List[A]): List[A] = a1 match {
      case Nil => a2
      case Cons(h, t) => Cons(h, append(t, a2))
  }

  def init[A](l: List[A]): List[A] = l match {
    case Nil => Nil
    case Cons(h, Nil) => Nil
    case Cons(h, t) => Cons(h, init(t))
  }

  def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B = // Utility functions
    as match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
  }

  def sumFR(ns: List[Int]) = foldRight(ns, 0)((x, y) => x + y)

  def productFR(ns: List[Double]) = foldRight(ns, 1.0)(_ * _) // `_ * _` is more concise notation for `(x,y) => x * y`; see sidebar

  def lengthFR[A](l: List[A]): Int = foldRight(l, 0)((_, acc) => acc + 1)

  @tailrec
  def foldLeft[A, B](as: List[A], z: B)(f: (B, A) => B): B =
    as match {
      case Nil => z
      case Cons(h, t) => foldLeft(t, (f(z, h)))(f)
  }

  def sumFL(l: List[Int]) = foldLeft(l, 0)((x, y) => x + y)

  def productFL(l: List[Double]) = foldLeft(l,1.0)((x,y)=> x * y)

  def lengthFL[A](l: List[A]): Int = foldLeft(l, 0)((acc, _) => acc + 1)

  def reverse[A](l: List[A]): List[A] = foldLeft(l,Nil:List[A])((b,a) => Cons(a,b))

  def appendFR[A](l1: List[A], l2: List[A]): List[A] = foldRight(l1,l2)((a,b) => Cons(a,b))

  def appendFL[A](l1: List[A], l2: List[A]): List[A] = foldLeft(l2, l1)((b, a) => setTail(b,a))

  def setTail[A](l: List[A],a:A): List[A] = foldRight(l,Cons(a,Nil))((a,b) => Cons(a,b))

  def concat[A](l1: List[List[A]], l2: List[List[A]]): List[A] = {
    append(foldLeft(l1, List[A]())(append),foldLeft(l2, List[A]())(append))
  }

  def addOneFL(l:List[Int]): List[Int] = foldLeft(l,List[Int]())((b,a)=> setTail(b,a+1))

  def addOneFR(l: List[Int]): List[Int] = foldRight(l, List[Int]())((a,b) => Cons(a+1,b))

  def doubleToString(l: List[Double]): List[String] = foldRight(l, List[String]())((a,b) => Cons(a.toString,b))

  def map[A, B](l: List[A])(f: A => B): List[B] = foldRight(l, List[B]())((a,b) => Cons(f(a),b))

  def filter[A](as: List[A])(f:A => Boolean): List[A] = foldRight(as,List[A]())((a,b) => if (f(a)) Cons(a,b) else b)

  def flatMap[A,B](as: List[A])(f: A => List[B]): List[B] = foldLeft(as,List[B]())((b,a) => append(b,f(a)))

  def filterFlatMap[A](as: List[A])(f:A => Boolean): List[A]= flatMap(as)(a => if (f(a)) Cons(a,Nil) else Nil)

  def addListItems(l1: List[Int], l2:List[Int]): List[Int] = {
    @tailrec
    def loop (l1:List[Int],l2:List[Int],n2:Int,acc: List[Int],pos: Int): List[Int] = l1 match {
      case Nil => acc
      case Cons(h,t) if (n2 == -1) => append(acc,l1)
      case Cons(h,t) => loop(t,l2,getItemAtPos(l2,pos+1),setTail(acc,h+n2),pos+1)
    }

    if (lengthFL(l1)> lengthFL(l2)) loop(l1,l2,getItemAtPos(l2,0),List[Int](),0)
    else loop(l2,l1,getItemAtPos(l1,0),List[Int](),0)
  }

  @tailrec
  def getItemAtPos(as:List[Int],pos: Int): Int = as match {
    case _ if (pos > lengthFL(as)-1) => -1
    case Cons(h,_) if (pos == 0) => h
    case Cons(h,t) => getItemAtPos(t,pos-1)
  }

}
