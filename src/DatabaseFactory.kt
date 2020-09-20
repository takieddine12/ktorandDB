package ktor.api.com

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import org.omg.CORBA.AnySeqHelper.insert
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.insert

object DatabaseFactory {

    init {
        Database.connect(hikari())
        transaction {
                create(userSubModel)
                userSubModel.insert {
                    it[status] = "Active"
                    it[date] = "2020-09-20"
                }
            userSubModel.insert {
                it[status] = "Not Active"
                it[date] = "2020-09-15"
            }
        }
    }

    fun hikari(): HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = "org.h2.Driver"
        config.jdbcUrl = "jdbc:h2:mem:test"
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
    }
}