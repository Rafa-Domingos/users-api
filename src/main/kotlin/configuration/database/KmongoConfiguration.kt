package configuration.database

import model.User
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun mongoConnection() = KMongo.createClient("mongodb://localhost:27017")
    .coroutine
    .getDatabase("users-api")
    .getCollection<User>("users")