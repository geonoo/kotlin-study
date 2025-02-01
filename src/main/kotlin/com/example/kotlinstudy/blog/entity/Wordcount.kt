package com.example.kotlinstudy.blog.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class Wordcount {
    @Id
    var word: String? = null
    var count: Int = 0
}