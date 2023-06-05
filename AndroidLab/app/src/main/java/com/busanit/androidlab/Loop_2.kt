package com.busanit.androidlab

import kotlin.reflect.typeOf

// 조건문과 반복문
// 1. if ~ else
//fun main() {
//  var data = 10
//  if (data > 0) {
//    println("data > 0")
//  } else {
//    println("data <= 0")
//  }
//}

// 1-1. else if
//fun main() {
//  var data = 10
//  if (data > 10) {
//    println("data > 10")
//  } else if (data > 0 && data <= 10) {
//    println("data > 0 && data <= 10")
//  } else {
//    println("data <= 0 ")
//  }
//}

// 1-2. if else 표현식(값)
//fun main(){
//  var data = 10
//  val result = if (data>0){ // 결과 값 반환(여러 줄일 때 마지막 줄 값 리턴)
//    println("data > 10")
//    true // 리턴값
//  } else {
//    println("data <= 0")
//    false
//  } // 표현식으로 사용할 때는 else 생략할 수 없음
//  println(result)
//}

// 2. when 조건문(switch와 비슷)
//fun main(){
//  var data = 10
//  when(data){
//    10 -> println("data is 10")
//    20 -> println("data is 20")
//    else -> println("data is not valid data")
//  }
//}

// 2-1. when 조건에 String
//fun main(){
//  var data = "hello"
//  when(data){
//    "hello" -> println("data is hello")
//    "world" -> println("data is world")
//    else -> println("data is not valid data")
//  }
//}

// 2-2. when 다양한 조건 사용
//fun main() {
//  var data : Any = "hello"
//  when(data){
//    is String -> println("data is String") // is는 타입을 확인하는 연산자
//    20, 30 -> println("data is 20 or 30")
//    in 1 .. 10 -> println("data is 1 .. 10") // in 범위 지정 연산자
//    else -> println("data is not valid data")
//  }
//}

// 2-3. when 조건 없이 사용(조건을 아래 case에 표현)
//fun main() {
//  var data = 10
//  when{
//    data <= 0 -> println("data is <= 0")
//    data > 100 -> println("data is > 100")
//    else -> println("data is not valid data")
//  }
//}

// 2-4. when 표현식 사용
//fun main() {
//  var data = 10
//  val result = when {
//    data <= 0 -> "data is <= 0"
//    data > 100 -> "data is > 100"
//    else -> "data is not valid data"
//  }
//  println(result)
//}

// 3. for(범위 연산자 in 사용)
//fun main() {
//  var sum: Int = 0
//  for (i in 1..10) {
//    sum += i
//  }

//  for (i in 1 until 10) { // 1 ~ 9
//    sum += i
//  }

//  for (i in 2 .. 10 step 2) { // step : 증가, 감소 폭 지정
//    sum += i
//  }

//  for (i in 10 downTo 1) { // downTo : 감소시키면서 반복
//    sum += i
//  }

//  for (i in 10 downTo 1 step 2) {
//    sum += i
//  }
//  println(sum)
//}

// 3-1. 컬렉션 크기 만큼 반복
//fun main() {
//  var data = arrayOf<Int>(10, 20, 30)
//
//  for (i in data.indices) { // 인덱스 0, 1, 2 순서대로 가져옴
//    print(data[i])
//    if (i != data.size - 1) print(", ")
//  }
//}

// 3-2. 인덱스, 데이터 함께 가져오기 withIndex
//fun main() {
//  var data = arrayOf<Int>(10, 20, 30)
//  for ((index, value) in data.withIndex()) {
//    print(value) // value 출력
//    if (index != data.size-1) print(", ") // index 비교
//  }
//}

// 3-3. while문
//fun main() {
//  var x = 0
//  var sum = 0
//  while (x < 10){
//    sum += ++x
//  }
//  println(sum)
//}