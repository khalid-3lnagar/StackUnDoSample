package khalid.elnagar.stackundosample.domain

import khalid.elnagar.stackundosample.Cards

class CardsRepository private constructor() {
    private val dataGetWay: InMemoryDataGetWay =
        InMemoryDataGetWay.instance

    companion object {
        val instance by lazy { CardsRepository() }
    }

    fun generateRandomColor(card: Cards) = dataGetWay.generateRandomColor(card)

    fun getCard1() = dataGetWay.retrieveCard(Cards.CARD_1)
    fun getCard2() = dataGetWay.retrieveCard(Cards.CARD_2)
    fun getCard3() = dataGetWay.retrieveCard(Cards.CARD_3)

    fun undo() = dataGetWay.undo()

}