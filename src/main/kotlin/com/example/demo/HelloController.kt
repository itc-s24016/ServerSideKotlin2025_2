package com.example.demo
//生成したSpring Boot アプリケーションを起動する
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller  //@ = アノテーション：注釈、ラベルの意味
@RequestMapping("/hello")
class HelloController {
    @GetMapping("/world") //http://localhost:8080/hello/world で表示される
    fun index(model: Model): String{
        model.addAttribute("message", "Hello World!")
        return "index"
    }
    //URLを変えると Hello Kotlin! と表示されるようにする
    @GetMapping("/kotlin") //http://localhost:8080/hello/kotlin で表示される
    fun index2(model: Model): String{
        model.addAttribute("message", "Hello Kotlin!")
        return "index"
    }
}