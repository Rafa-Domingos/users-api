package repository

import arrow.data.Reader
import arrow.data.ReaderApi.ask
import arrow.data.map
import com.mongodb.client.model.Filters
import configuration.server.RepositoryContext
import kotlinx.coroutines.runBlocking
import model.User
import model.UserRole

fun save(user: User): Reader<RepositoryContext, User> =
    ask<RepositoryContext>().map { ctx ->
        runBlocking {
            user.also {
                ctx.coroutineCollection.insertOne(user)
            }
        }
    }

fun getByIdAndRole(id: String, userRole: UserRole) =
    ask<RepositoryContext>().map { ctx ->
        runBlocking {
            ctx.coroutineCollection.findOne(
                Filters.and(
                    listOf(Filters.eq("_id", id), Filters.eq("userRole", userRole))))
        }
    }

fun getByEmail(email: String) =
    ask<RepositoryContext>().map { ctx ->
        runBlocking {
            ctx.coroutineCollection.findOne(Filters.eq("email", email))
        }
    }

fun delete(id: String) =
    ask<RepositoryContext>().map { ctx ->
        runBlocking {
            ctx.coroutineCollection.deleteOneById(id)
        }
    }

fun replace(id: String, user: User) =
    ask<RepositoryContext>().map { ctx ->
        runBlocking {
            ctx.coroutineCollection.replaceOneById(id, user)
        }
    }
