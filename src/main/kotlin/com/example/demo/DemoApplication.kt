package com.example.demo
//アプリケーション起動クラス
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

//このアノテーションがついたクラスがアプリケーションを起動するクラスになり、ここのmainを実行する
@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}
