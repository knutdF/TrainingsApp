package backend.service

import backend.model.Training
import backend.repository.TrainingRepository

class TrainingService(private val trainingRepository: TrainingRepository) {

    fun getTrainingById(id: String): Training? {
        return trainingRepository.findById(id)
    }

    fun getAllTrainings(): List<Training> {
        return trainingRepository.findAll()
    }

    fun createTraining(training: Training): Training {
        return trainingRepository.save(training)
    }

    fun updateTraining(id: String, training: Training): Training? {
        if (trainingRepository.findById(id) != null) {
            return trainingRepository.save(training.copy(id = id))
        }
        return null
    }

    fun deleteTrainingById(id: String): Boolean {
        return trainingRepository.deleteById(id)
    }
}
