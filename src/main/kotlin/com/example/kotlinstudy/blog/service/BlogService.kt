package com.example.kotlinstudy.blog.service

import com.example.kotlinstudy.blog.dto.BlogDto
import com.example.kotlinstudy.blog.entity.Wordcount
import com.example.kotlinstudy.blog.repository.WordRepository
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Service
class BlogService(
    val wordRepository: WordRepository
) {

    fun searchBlog(blogDto: BlogDto): String? {
        val webClient: WebClient = WebClient
            .builder()
            .baseUrl("https://dapi.kakao.com")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build()
        val response = webClient
            .get()
            .uri { it.path("/v2/search/blog")
                .queryParam("query", blogDto.query)
                .queryParam("sort", blogDto.sort)
                .queryParam("page", blogDto.page)
                .queryParam("size", blogDto.size)
                .build() }
            .header("Authorization", "")
            .retrieve()
            .bodyToMono<String>()
        val result = response.block()

        val lowQuery: String = blogDto.query!!.lowercase()
        val word: Wordcount = wordRepository.findById(lowQuery).orElse(Wordcount(lowQuery, 0))
        word.count++
        wordRepository.save(word)

        return result
    }

    fun getTop10(): List<Wordcount> {
        return wordRepository.findTop10ByOrderByCountDesc()
    }
}