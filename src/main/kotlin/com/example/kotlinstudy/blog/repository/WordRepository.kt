package com.example.kotlinstudy.blog.repository

import com.example.kotlinstudy.blog.entity.Wordcount
import org.springframework.data.repository.CrudRepository

interface WordRepository :  CrudRepository<Wordcount, String> {
    fun findTop10ByOrderByCountDesc(): List<Wordcount>

}