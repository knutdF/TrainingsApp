package backend.repository

import backend.model.User
import java.util.*
import java.util.concurrent.ConcurrentHashMap

interface UserRepository {
    fun findById(id: String): User?
    fun findByUsername(username: String): User?
    fun save(user: User): User
    fun deleteById(id: String): Boolean
    fun findAll(): List<User>
}

class InMemoryUserRepository : UserRepository {

    private val users: MutableMap<UUID, User> = ConcurrentHashMap()

    override fun findById(id: String): User? {
        val uuid = UUID.fromString(id)
        return users[uuid]
    }

    override fun findAll(): List<User> {
        return users.values.toList()
    }

    override fun findByUsername(username: String): User? {
        return users.values.find { it.username == username }
    }

    override fun save(user: User): User {
        val uuid = UUID.fromString(user.id)
        users[uuid] = user
        return user
    }

    override fun deleteById(id: String): Boolean {
        val uuid = UUID.fromString(id)
        return users.remove(uuid) != null
    }
}
