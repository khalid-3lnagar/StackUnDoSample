package khalid.elnagar.stackundosample

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import khalid.elnagar.stackundosample.domain.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val viewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.apply {

            cardColor2.observe(this@MainActivity, Observer { crd_2.setCardBackgroundColor(it!!) })
            cardColor3.observe(this@MainActivity, Observer { crd_3.setCardBackgroundColor(it!!) })
            cardColor1.observe(this@MainActivity, Observer { crd_1.setCardBackgroundColor(it!!) })

            frame_1.setOnClickListener { onCardClick(Cards.CARD_1) }
            frame_2.setOnClickListener { onCardClick(Cards.CARD_2) }
            frame_3.setOnClickListener { onCardClick(Cards.CARD_3) }

            f_undo.setOnClickListener { onUndoBtnClicked() }
        }

    }
}

class MainViewModel(
    val generateRandomColor: GenerateRandomColor = GenerateRandomColor()
) : ViewModel() {
    val cardColor1: LiveData<Int> = RetrieveCardColor1()()
    val cardColor2: LiveData<Int> = RetrieveCardColor2()()
    val cardColor3: LiveData<Int> = RetrieveCardColor3()()

    fun onCardClick(card: Cards) = generateRandomColor(card)

    fun onUndoBtnClicked() = Undo()()
}

