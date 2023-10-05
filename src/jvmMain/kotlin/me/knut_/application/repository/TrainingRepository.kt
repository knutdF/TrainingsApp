package backend.repository


import backend.model.Training
import java.util.*
import java.util.concurrent.ConcurrentHashMap

interface TrainingRepository {
    fun findById(id: String): Training?
    fun findAll(): List<Training>
    fun save(training: Training): Training
    fun deleteById(id: String): Boolean
}

class InMemoryTrainingRepository : TrainingRepository {

    private val trainings: MutableMap<UUID, Training> = ConcurrentHashMap()

    override fun findById(id: String): Training? {
        val uuid = UUID.fromString(id)
        return trainings[uuid]
    }



    override fun findAll(): List<Training> {
        return trainings.values.toList()
    }

    override fun save(training: Training): Training {
        val uuid = UUID.fromString(training.id) // Konvertieren der training.id in ein UUID Objekt
        trainings[uuid] = training // Verwenden des UUID-Objekts als Schlüssel für die Map
        return training
    }


    override fun deleteById(id: String): Boolean {
        val uuid = UUID.fromString(id)
        return trainings.remove(uuid) != null
    }
}

