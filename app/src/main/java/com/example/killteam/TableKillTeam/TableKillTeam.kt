package com.example.killteam.TableKillTeam

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.icu.text.Edits
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.killteam.EditKillTeam.EditKillTeam
import com.example.killteam.Model.KillTeam
import com.example.killteam.R
import com.example.killteam.Storage

class TableKillTeam : AppCompatActivity() {


    lateinit var listViewKillTeam: ListView

    var presenter = TableKillTeamPresenter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_kill_team)
        configUI()

    }


    fun configUI() {
        configListView()
    }

    fun configListView() {
        listViewKillTeam = findViewById(R.id.listViewKillTeam)
        val factions = presenter.parseJson()
        //var arrayPolitics = arrayOf("")
//        var killteamArray = emptyArray<String>()

        val killteamArray = mutableListOf<KillTeam>()



        for (faction in factions) {
            for (killteam in faction.killTeam) {
                killteamArray.add(killteam)
//                killteamArray = arrayOf(killteam.killTeamName)
//                println(killteam.killTeamName)
            }
        }

     //val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, killteamArray)

        // здесь я создал объект типа данных  MyCustomAdapter
        // передаём объект в листвью
        val adapter = MyCustomAdapter(this, killteamArray, this)
        listViewKillTeam.adapter = adapter
        
    }

    fun goToNextActivity() {
        val intent = Intent(this, EditKillTeam::class.java)
        startActivity(intent)
    }

    // адаптер - это конфигурация моего листвью
    private class MyCustomAdapter(context: Context, killTeam: MutableList<KillTeam>, activity: TableKillTeam): BaseAdapter() {

        private val myContext: Context
        private val myKillTeam: MutableList<KillTeam>
        private val myActivity: TableKillTeam


        init {
            myContext = context
            myKillTeam = killTeam
            myActivity = activity
        }

        override fun getCount(): Int {
            return myKillTeam.size
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItem(position: Int): Any {
            return "Test String"
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {

            val killTeam = myKillTeam[position]
            val titleText = TextView(myContext)
            titleText.text = killTeam.killTeamName
            val button = Button(myContext)
            val color = Color.parseColor("#3AB4F2")
            button.setBackgroundColor(color)
            button.text = killTeam.killTeamName




                button.setOnClickListener {
                    myActivity.goToNextActivity()
                    println("У нас в квартире газ, а у вас?")
                    Storage.killTeam = killTeam
                    Storage.killTeamName = killTeam.killTeamName
                }

            return button
        }
    }
}






