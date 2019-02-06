package shostak.tictactoe

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var player1Turn = true

    private var buttons = ArrayList<Button>()
    private var player1 = ArrayList<Int>()
    private var player2 = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getButtons()
        clearButtons()
    }

    private fun getButtons() {
        buttons.add(findViewById(R.id.but1))
        buttons.add(findViewById(R.id.but2))
        buttons.add(findViewById(R.id.but3))
        buttons.add(findViewById(R.id.but4))
        buttons.add(findViewById(R.id.but5))
        buttons.add(findViewById(R.id.but6))
        buttons.add(findViewById(R.id.but7))
        buttons.add(findViewById(R.id.but8))
        buttons.add(findViewById(R.id.but9))
    }

    private fun clearButtons() {
        for (i in buttons) {
            i.setBackgroundColor(Color.parseColor("#FFFFFF"))
            i.text = ""
            i.isEnabled = true
        }
    }

    fun butClick (view: View) {
        val butSelected = view as Button
        var cellId = 0
        when (butSelected.id) {
            R.id.but1 -> cellId = 1
            R.id.but2 -> cellId = 2
            R.id.but3 -> cellId = 3
            R.id.but4 -> cellId = 4
            R.id.but5 -> cellId = 5
            R.id.but6 -> cellId = 6
            R.id.but7 -> cellId = 7
            R.id.but8 -> cellId = 8
            R.id.but9 -> cellId = 9
        }
        play(cellId, butSelected)
        val textView: TextView = findViewById(R.id.text_view)
        if (player1Turn) textView.text = "Step X"
        else textView.text = "Step O"
    }

    private fun play(cellId: Int, butSelected: Button) {
        if (player1Turn) {
            butSelected.text = "X"
            butSelected.setBackgroundColor(Color.parseColor("#009193"))
            player1.add(cellId)
            player1Turn = false
        } else {
            butSelected.text = "O"
            butSelected.setBackgroundColor(Color.parseColor("#FF9300"))
            player2.add(cellId)
            player1Turn = true
        }
        butSelected.isEnabled = false
        checkWinner()
    }

    private fun checkWinner() {
        var winner = -1

        //rows
        if (player1.contains(1) && player1.contains(2) && player1.contains(3) ||
            player1.contains(4) && player1.contains(5) && player1.contains(6) ||
            player1.contains(7) && player1.contains(8) && player1.contains(9) ){
            winner=1
        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3) ||
            player2.contains(4) && player2.contains(5) && player2.contains(6) ||
            player2.contains(7) && player2.contains(8) && player2.contains(9)  ){
            winner=2
        }


        // columns
        if (player1.contains(1) && player1.contains(4) && player1.contains(7) ||
            player1.contains(2) && player1.contains(5) && player1.contains(8) ||
            player1.contains(3) && player1.contains(6) && player1.contains(9)   ){
            winner=1
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7) ||
            player2.contains(2) && player2.contains(5) && player2.contains(8) ||
            player2.contains(3) && player2.contains(6) && player2.contains(9)   ){
            winner=2
        }

        //diagonal
        if (player1.contains(1) && player1.contains(5) && player1.contains(9) ||
            player1.contains(3) && player1.contains(5) && player1.contains(7)){
            winner=1
        }
        if(player2.contains(1) && player2.contains(5) && player2.contains(9) ||
            player2.contains(3) && player2.contains(5) && player2.contains(7)){
            winner=2
        }

        var nums = ArrayList<Int>()
        for (i in 1..9)
            nums.add(i)

        var allSteps = player1 + player2

        if (allSteps.containsAll(nums))
            winner = 0

        if( winner != -1){
            blockButtons()
            when (winner) {
                1 -> Toast.makeText(this," Player 1  win the game", Toast.LENGTH_LONG).show()
                2 -> Toast.makeText(this," Player 2  win the game", Toast.LENGTH_LONG).show()
                0 -> Toast.makeText(this," Friendship win the game", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun reset (view: View) {
        player1.clear()
        player2.clear()
        player1Turn = true
        clearButtons()
    }

    fun blockButtons() {
        for (i in buttons)
            i.isEnabled = false
    }
}
