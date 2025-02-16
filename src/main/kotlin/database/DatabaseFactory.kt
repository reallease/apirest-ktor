package com.thomasd.database

import org.jetbrains.exposed.sql.Database

private val dbPassword = System.getenv("PASSWORDSQL")

object DatabaseFactory {
    init {
        Database.connect(
            url = "jdbc:mysql://localhost:3306/ktor_users",
            driver = "com.mysql.cj.jdbc.Driver",
            user = "root",
            password = dbPassword
        )
    }
}