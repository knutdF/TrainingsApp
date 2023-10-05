package backend.service

import backend.model.Test
import backend.repository.TestRepository
import java.util.*

class TestService(private val testRepository: TestRepository) {

    fun getTestById(id: String): Test? {
        val uuid = UUID.fromString(id)
        return testRepository.findById(id)
    }

    fun getAllTests(): List<Test> {
        return testRepository.findAll()
    }

    fun createTest(test: Test): Test {
        return testRepository.save(test)
    }

    fun updateTest(id: String, test: Test): Test? {
        if (testRepository.findById(id) != null) {
            return testRepository.save(test.copy(id = id))
        }
        return null
    }

    fun deleteTestById(id: String): Boolean {
        val uuid = UUID.fromString(id)
        return testRepository.deleteById(id)
    }
}
