package com.example.kotlinstudy.controller

import com.example.kotlinstudy.blog.dto.BlogDto
import com.example.kotlinstudy.blog.service.BlogService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BlogController {
    val blogService: BlogService = BlogService()

    @GetMapping("")
    fun searchBlog(@RequestBody blogDto: BlogDto): String? {
        val result = blogService.searchBlog(blogDto)
        return result
    }
}