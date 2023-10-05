package backend.service

import backend.model.Training
import backend.model.User
import backend.repository.UserRepository
import java.util.*

class UserService(private val userRepository: UserRepository) {

    fun getUserById(id: String): User? {
        return userRepository.findById(id)
    }

    fun getAllUsers(): List<User> {
        return userRepository.findAll()
    }

    fun createUser(user: User): User {
        return userRepository.save(user)
    }

    fun updateUser(id: String, user: User): User? {
        if (userRepository.findById(id) != null) {
            return userRepository.save(user.copy(id = id))
        }
        return null
    }

    fun deleteUserById(id: String): Boolean {
        return userRepository.deleteById(id)
    }
}
