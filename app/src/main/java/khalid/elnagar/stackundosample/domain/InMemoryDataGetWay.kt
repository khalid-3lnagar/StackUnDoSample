package khalid.elnagar.stackundosample.domain

import android.arch.lifecycle.MutableLiveData
import android.graphics.Color
import khalid.elnagar.stackundosample.CardBtn
import khalid.elnagar.stackundosample.Cards
import kotlin.random.Random

class InMemoryDataGetWay private constructor() {
    val historyList by lazy { MutableLiveData<MutableList<CardBtn>>() }

    private val cardColor1 by lazy { MutableLiveData<Int>() }
    private val cardColor2 by lazy { MutableLiveData<Int>() }
    private val cardColor3 by lazy { MutableLiveData<Int>() }

    companion object {
        val instance by lazy {
            InMemoryDataGetWay()
                .apply {
                    historyList.postValue(mutableListOf())
                    updateColor(
                        CardBtn(
                            Color.RED,
                            Cards.CARD_1
                        )
                    )
                    updateColor(
                        CardBtn(
                            Color.RED,
                            Cards.CARD_2
                        )
                    )
                    updateColor(
                        CardBtn(
                            Color.RED,
                            Cards.CARD_3
                        )
                    )
                }
        }
    }

    fun retrieveCard(card: Cards) = when (card) {
        Cards.CARD_1 -> cardColor1
        Cards.CARD_2 -> cardColor2
        Cards.CARD_3 -> cardColor3
    }


    private fun updateColor(cardBtn: CardBtn) = with(cardBtn) {
        when (card) {
            Cards.CARD_1 ->
                cardColor1.postValue(color)

            Cards.CARD_2 ->
                cardColor2.postValue(color)

            Cards.CARD_3 ->
                cardColor3.postValue(color)
        }
    }

    fun undo() {
        historyList
            .value
            ?.takeUnless { it.isEmpty() }
            ?.pop()
            ?.also { updateColor(it) }
            ?.also { historyList.postValue(historyList.value) }

    }

    private fun getCardBtn(card: Cards) = when (card) {
        Cards.CARD_1 ->
            cardColor1.value?.let { CardBtn(it, card) }

        Cards.CARD_2 ->
            cardColor2.value?.let { CardBtn(it, card) }

        Cards.CARD_3 ->
            cardColor3.value?.let { CardBtn(it, card) }
    }


    fun generateRandomColor(card: Cards) {
        Random
            .also { historyList.value?.push(getCardBtn(card)!!) }
            .also { historyList.postValue(historyList.value) }
            .let { Color.argb(255, it.nextInt(256), it.nextInt(256), it.nextInt(256)) }
            .let { CardBtn(it, card) }
            .also { updateColor(it) }
    }
}

fun <T> MutableList<T>.push(element: T) = add(count(), element)

fun <T> MutableList<T>.pop(): T? {
    val value = get(count() - 1)
    removeAt(count() - 1)
    return value

}