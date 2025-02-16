package com.thomasd.database

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