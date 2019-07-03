package khalid.elnagar.stackundosample.domain

import khalid.elnagar.stackundosample.Cards


class GenerateRandomColor(private val repository: CardsRepository = CardsRepository.instance) {
    operator fun invoke(card: Cards) = repository.generateRandomColor(card)

}

class Undo(private val repository: CardsRepository = CardsRepository.instance) {
    operator fun invoke() = repository.undo()
}

class RetrieveCardColor1(private val repository: CardsRepository = CardsRepository.instance) {
    operator fun invoke() = repository.getCard1()
}

class RetrieveCardColor2(private val repository: CardsRepository = CardsRepository.instance) {
    operator fun invoke() = repository.getCard2()
}

class RetrieveCardColor3(private val repository: CardsRepository = CardsRepository.instance) {
    operator fun invoke() = repository.getCard3()
}
