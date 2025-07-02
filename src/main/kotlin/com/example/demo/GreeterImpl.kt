package com.example.demo

import org.springframework.stereotype.Component

//インターフェース Greeter を実装したクラス
@Component
class GreeterImpl: Greeter{
    override fun sayHello(name: String): String = "Hello ${name}"

}