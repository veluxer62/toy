package com.kh.jfixture

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        val name: String,

        val age: Int,

        val gender: Gender
)

enum class Gender(label: String) {
    M("남자"),
    F("여자")
}
