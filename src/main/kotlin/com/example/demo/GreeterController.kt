package com.example.demo
//REST API の実装
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

//RestAPI:jsonでリクエスト、レスポンスをやり取りするためのAPI

//@RestController データクラスで定義されているものであれば、自動的にJsonデータに変換する
//@RequestParam name:String /hello?name=値　の?name=値 の部分

@RestController
@RequestMapping("greeter")
class GreeterController (private val greeter: Greeter){
    //curl http://localhost:8080/greeter/hello?name=s24016
    //{"message":"Hello s24016!"}
    @GetMapping("/hello")
    fun hello(@RequestParam name: String): HelloResponse{
        return HelloResponse("Hello ${name}!")
    }

    //curl http://localhost:8080/greeter/hello/s24016
    //{"message":"Hello s24016!"}
    @GetMapping("/hello/{name}")
    fun helloPathValue(@PathVariable name: String): HelloResponse{
        return HelloResponse("Hello ${name}!")
    }

    //curl -v -H 'Content-Type: application/json' -X POST -d '{"name":"s24016"}' http://localhost:8080/greeter/hello
    /* 【ターミナルでの結果】
    Note: Unnecessary use of -X or --request, POST is already inferred.
    * Host localhost:8080 was resolved.
    * IPv6: ::1
    * IPv4: 127.0.0.1
    *   Trying [::1]:8080...
    * Connected to localhost (::1) port 8080
    > POST /greeter/hello HTTP/1.1
    > Host: localhost:8080
    > User-Agent: curl/8.5.0
    > Accept:
    > Content-Type: application/json
    > Content-Length: 17
    >
    < HTTP/1.1 200
    < Content-Type: application/json
    < Transfer-Encoding: chunked
    < Date: Thu, 26 Jun 2025 06:13:10 GMT
    <
    * Connection #0 to host localhost left intact
    */

    @PostMapping("/hello")
    fun helloByPost(@RequestBody request: HelloRequest): HelloResponse{
        return HelloResponse("Hello ${request.name}")
    }

    @GetMapping("/hello/byservice/{name}")
    fun helloByService(@PathVariable name: String): HelloResponse{
        val message = greeter.sayHello(name)
        return HelloResponse(message)
    }
}
//{"message":"Hello s24016!"}の"message"はこの引数名のこと
data class HelloResponse(val message: String)
data class HelloRequest(val name: String)