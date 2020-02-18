package com.kh.jfixture

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>