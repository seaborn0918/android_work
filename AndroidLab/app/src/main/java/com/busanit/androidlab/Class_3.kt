package com.busanit.androidlab

// 1. 클래스 선언
//class User {
//  var name = "kkang" // 멤버 변수
//
//  constructor(name: String) { // 생성자
//    this.name = name
//  }
//
//  fun someFun() {
//    println("name : $name")
//  }
//
//  class SomeClass {
//  }
//}
//
//fun main() {
//  val user = User("kim")
//  user.someFun()
//}

// 1-1. 주 생성자(클래스 생성자는 주 생성자, 보조 생성자로 구분)
// class User constructor(){ // 클래스 선언부에 constructor 키워드로 주 생성자 선언
// }
//
// class User(){ // constructor 키워드 생략 가능
// }
//
// class User{ // 주 생성자 선언하지 않으면 매개변수없는 주 생성자 자동 추가
// }

// 1-2. 매개변수 있는 주 생성자
//class User(name: String, count: Int) {
//  init {
//    println("i am init...") // init 영역은 객체 생성시 자동 실행
//  }
//}
//fun main(){
//  val user = User("kkang", 10)
//}

// 1-3. 매개변수를 멤버변수로 사용하는 방법
//class User(name : String, count: Int){
//  lateinit var name: String
//  var count : Int = 0 // 멤버 변수 선언
//  init {
//    this.name = name
//    this.count = count // init 영역에서 매개변수 값 대입
//  }
//  fun someFun(){
//    println("name : $name, count : $count")
//  }
//}
//
//fun main() {
//  val user = User("kkang", 10)
//  user.someFun()
//}

// 1-4. 매개변수를 val, var로 선언 -> 바로 매개변수로 사용가능
//class User(val name: String,val count: Int){
//  // 멤버변수 선언, 초기화, 값 대입... 생략
//  fun someFun(){
//    println("name : $name, count : $count")
//  }
//}
//
//fun main() {
//  val user = User("kkang", 10)
//  user.someFun()
//}

// 2. 보조 생성자. 클래스의 본문안에 constructor 키워드로 선언, 여러 개 추가 가능
//class User{
//  constructor(name : String){
//    println("constructor(name : String) call...")
//  }
//  constructor(name:String, count: Int){
//    println("constructor(name : String, count: Int) call...")
//  }
//}
//
//fun main() {
//  val user1 = User("kkang")
//  val user2 = User("kkang", 10)
//}

// 2-1. 주 생성자, 보조 생성자 함께 사용
//class User(name: String) {
//  constructor(name: String, count: Int): this(name) { // this(name) 주생성자 호출
//  }
//}

// 2-2. 보조 생성자 여러개 사용
//class User(val name: String) {
//  var name: String
//  init {
//    this.name = name
//  }

//  var count: Int = 0
//  lateinit var email: String
//
//  constructor(name: String, count: Int) : this(name) {
//    this.count = count
//  }
//
//  constructor(name: String, count: Int, email: String) : this(name, count) {
//    this.email = email
//  }
//
//  fun someFun() {
//    println("name : $name, count: $count, email : $email")
//  }
//}
//
//fun main() {
//  val user = User("kkang", 20, "a@a.com")
//  user.someFun()
//}

// 3. 상속
// open class Super{} // open 키워드를 써서 선언해야 다른 클래스에서 상속 가능
// class Sub: Super(){} // 클래스 선언부에서 콜론 뒤에 상속받을 클래스명 입력. Super() 상속 받으면서, Super 클래스의 매개변수 없는 생성자 호출

// 3-1. 매개변수 있는 생성자
// open class Super(name: String) {}
// class Sub(name: String):Super(name){} //매개변수 구성에 맞게 전달

// 3-2. 부모클래스 함수 사용
//open class Super {
//  var superData = 10
//  fun superFun() {
//    println("i am superFun : $superData")
//  }
//}
//class Sub:Super()
//fun main() {
//  val obj = Sub() // Sub 클래스 객체 생성
//  obj.superData = 20 // Sub 클래스 객체를 이용해서 Super 클래스 변수, 함수 사용
//  obj.superFun()
//}

// 3-3. 오버라이딩 규칭
//open class Super{
//  open var someData = 10 // 오버라이딩 허용할 변수, 함수에 open 키워드 사용
//  open fun someFun(){
//    println("i am super class function : $someData")
//  }
//}
//class Sub:Super() { // 오버라이딩 할 메소드에는 override 키워드 사용
//  override var someData = 20
//  override fun someFun() {
//    println("i am sub class function : $someData")
//  }
//}
//
//fun main() {
//  val obj = Sub()
//  obj.someFun()
//}

//4. 접근제한자 - public(모든 클래스), internal(같은 모듈), protected(상속 관계), private(자기 클래스 내부만)
//open class Super {
//  var publicData = 10 // 생략의 경우 public
//  protected var protectedData = 20
//  private var privateData = 30
//}
//
//class Sub : Super() {
//  fun subFun() {
//    publicData++
//    protectedData++
//    // privateData++
//  }
//}
// fun main() {
//   val obj = Super()
//  obj.publicData++
// obj.protectedData++
// obj.privateData++
// }

// 5. 클래스 종류, 데이터 클래스
//class NonDataClass(val name: String, val email: String, val age: Int) // 일반 클래스
//
//data class DataClass(val name: String, val email: String, val age: Int) // 데이터 클래스 선언(data 키워드, JSP DTO)
//
//fun main() {
//  val non1 = NonDataClass("kkang", "a@a.com", 10)
//  val non2 = NonDataClass("kkang", "a@a.com", 10)
//
//  val data1 = DataClass("kkang", "a@a.com", 10)
//  val data2 = DataClass("kkang", "a@a.com", 10)
//
//  println("non data class equals : ${non1.equals(non2)}") // 일반 클래스는 객체 비교
//  println("data class equals : ${data1.equals(data2)}") // 데이터 클래스는 객체의 데이터 비교
//}

// 5-1. 데이터 클래스 equals() : 주생성자 매개변수 데이터만 비교
//data class DataClass(val name: String, val email: String, val age: Int){
//  lateinit var address:String
//  constructor(name: String, email: String, age: Int, address:String): this(name, email, age){
//    this.address = address
//  }
//}
//
//fun main(){
//  val obj1 = DataClass("kkang", "a@a.com", 10, "seoul")
//  val obj2 = DataClass("kkang", "a@a.com", 10, "busan")
//  println("obj1.equals(obj2) : ${obj1.equals(obj2)}") // true : 보조생성자 매개변수 seoul, busan은 비교하지 않음
//}

// 5-2. 데이터 클래스 toString() 함수
//fun main() {
//  class NonDataClass(val name: String, val email: String, val age: Int)
//  data class DataClass(val name: String, val email: String, val age: Int)
//
//  val non = NonDataClass("kkang", "a@a.com", 10)
//  val data = DataClass("kkang", "a@a.com", 10)
//
//  println("non data class toString : ${non.toString()}") // 객체 주소 출력
//  println("data class toString : ${data.toString()}") // 데이터 출력
//}

// 5-3. object 클래스(익명 클래스 생성 목적)
//open class Super {
//  open var data = 10
//  open fun some() {
//    println("i am super some() : $data")
//  }
//}
//val obj = object : Super() {
//  override var data = 20
//  override fun some() {
//    println("i am object some() : $data")
//  }
//}
//
//fun main() {
//  obj.data = 30
//  obj.some()
//}


// 5-4. 컴패니언 클래스(객체 생성 없이 클래스 이름으로 멤버에 접근. 자바 static 클래스)
//class MyClass{
//  var name: String = "kkang"
//  companion object{
//    var data = 10
//    fun some() {
//      println("$data, name")
//    }
//  }
//}

//fun main() {
//  // val obj = MyClass()
//  // obj.data = 20
//  // obj.some()
//  MyClass.data = 30
//  // MyClass.name = "kim"
//  MyClass.some()
//}



















































