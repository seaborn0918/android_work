package com.busanit.androidlab

// 1. 람다함수 {매개변수 -> 함수 본문} 함수 본문의 마지막 표현식(결과 값) 리턴
//fun sum(no1: Int, no2: Int): Int {
//  return no1 + no2
//}
//val sum = {no1: Int, no2: Int -> no1 + no2}

// 1-1. 람다 함수 선언과 동시에 호출
//fun main() {
//  println("${{no1: kotlin.Int, no2: kotlin.Int -> no1 + no2}(10, 20)}")
//}

// 1-2. 매개변수 없는 람다함수(화살표 생략 가능)
//fun main() {
//  run { -> println("function call") }
//}

//fun main() {
//  run { println("function call") }
//}

// 1-3. 매개변수 한개인 경우 it 키워드 사용 가능
//fun main() {
//  val some = { no: Int -> println(no) }
//  some(10)
//}

//fun main() {
//  val some: (Int) -> Unit = { println(it) }
//  some(10)
//}

// 1-4. 람다함수의 리턴(return문 사용 안됨)
//fun main() {
//  val some = { no1: Int, no2: Int ->
//    println("in lambda function")
//    no1 * no2 } // 마지막 표현식이 return
//  println("result : ${some(10, 20)}")
//}

// 2. 고차 함수
// 2-1. 함수타입 이용
//fun some(no1: Int, no2: Int): Int {
//  return no1 + no2
//}
//fun main() {
//  val some: (Int, Int) -> Int = { no1: Int, no2: Int -> no1 + no2 }
//  println("${some(10, 20)}")
//}
// 2-2. 타입 별칭으로 함수타입 선언
//typealias MyFunType = (Int, Int) -> Boolean
//
//fun main() {
//  val someFun: MyFunType = { no1: Int, no2: Int -> no1 > no2 }
//  println("${someFun(10,20)}")
//  println("${someFun(20,10)}")
//}

// 2-3. 매개변수 타입 생략가능(타입 유추 가능할 때)
//typealias MyFunType = (Int, Int) -> Boolean
//
//fun main() {
//  val someFun: MyFunType = { no1, no2 -> no1 > no2 }
//  println("${someFun(10,20)}")
//  println("${someFun(20,10)}")
//}

// 2-4. 고차함수(매개변수, 리턴값에 함수를 이용
//fun hofFun(arg: (Int) -> Boolean):()-> String {
//  val result = if (arg(10)) {
//    "valid"
//  } else {
//    "invalid"
//  }
//  return {"hofFun result: $result"}
//}
//
//fun main() {
//  val result = hofFun { no -> no > 0 }
//  println(result())
//}

// 3. null 안전성
//fun main() {
//  var data: String? = null
//  val length = if(data == null){
//    0
//  } else {
//    data.length
//  }
//  println("data length : $length")
//  println("data length: ${data?.length ?:0}")
//}

// 3-1. null 안전성 연산자
// null 허용 연산자 ?
//var data : String? = "kkang"
//fun main(){
//  data = null
//}

// 3-2. 엘비스 연산자 ? (변수가 null일 때 실행해야 하는 구문)
//fun main(){
//  var data : String? = "kkang"
//  println("data = ${data?.length ?:-1}")
//  data = null
//  println("data = ${data?.length ?:-1}")
//}

