package com.example.demo;

import com.example.demo.database.User;
import com.example.demo.database.UserMapper;
import com.example.demo.database.insert
import com.example.demo.database.selectByPrimaryKey
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")

class UserController (
    private val userMapper: UserMapper
){
    @GetMapping("/select/{id}")
    fun select(@PathVariable id: Int): User? {
        return userMapper.selectByPrimaryKey(id)
    }

    @PostMapping("/insert")
    fun insert(@RequestBody request: InsertRequest): InsertResponse {
        val record = request.run {
            User(id, name, age, profile)
        }
        val count = userMapper.insert(record)
        return InsertResponse(count)
    }
}

data class InsertRequest(// リクエスト
    val id: Int,
    val name: String,
    val age: Int,
    val profile: String,
)
data class InsertResponse(// レスポンス
    val count: Int,
)