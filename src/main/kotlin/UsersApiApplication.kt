import configuration.server.ktorServer

fun main() {
    ktorServer().start(wait = true)
}