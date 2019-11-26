package configuration.server

import configuration.database.mongoConnection
import model.User
import org.litote.kmongo.coroutine.CoroutineCollection

object RepositoryContext {
    val coroutineCollection: CoroutineCollection<User> = mongoConnection()
}