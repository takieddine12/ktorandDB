package ktor.api.com

import org.jetbrains.exposed.sql.Table

object userSubModel  : Table(){
    var id = integer("id").autoIncrement()
    var status = varchar("status",255)
    var date = varchar("date",255)
    override val primaryKey = PrimaryKey(id)
}