package ktor.api.com.retrofit

import ktor.api.com.DatabaseFactory
import ktor.api.com.subModel
import ktor.api.com.userSubModel
import org.h2.mvstore.db.TransactionStore
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import java.awt.image.DataBuffer
import javax.xml.crypto.Data

class UserService {

    suspend fun getAllUsers() : List<subModel> = newSuspendedTransaction{
       userSubModel.selectAll().map {
            toUsers(it)
        }
    }

    private fun toUsers(row: ResultRow) : subModel {
        return subModel(
            status=  row[userSubModel.status],
            date = row[userSubModel.date]
        )
    }
}