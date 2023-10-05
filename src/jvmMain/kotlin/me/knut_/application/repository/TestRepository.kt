package backend.repository

import backend.model.Test
import backend.model.User
import java.util.*
import java.util.concurrent.ConcurrentHashMap

interface TestRepository {
    fun findById(id: String): Test?
    fun findAll(): List<Test>
    fun save(test: Test): Test
    fun deleteById(id: String): Boolean
}

class InMemoryTestRepository : TestRepository {

    private val tests: MutableMap<UUID, Test> = ConcurrentHashMap()

    override fun findById(id: String): Test? {
        val uuid = UUID.fromString(id)
        return tests[uuid]
    }

    override fun findAll(): List<Test> {
        return tests.values.toList()
    }


    override fun save(test: Test): Test {
        val uuid = UUID.fromString(test.id) // Konvertieren der test.id in ein UUID Objekt
        tests[uuid] = test // Verwenden des UUID-Objekts als Schlüssel für die Map
        return test
    }

    override fun deleteById(id: String): Boolean {
        val uuid = UUID.fromString(id)
        return tests.remove(uuid) != null // Verwenden Sie hier `uuid` anstatt `id`
    }
}

