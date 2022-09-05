package com.example.killteam.EditKillTeam

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.killteam.*
import com.example.killteam.Model.FireTeam
import com.example.killteam.TableKillTeam.TableKillTeam

class EditKillTeam : AppCompatActivity() {

    lateinit var listViewEditKillTeam:ListView
    lateinit var textViewEditKillTeam: TextView
    var presenter = EditKillTeamPresenter()
//    lateinit var stringIntent: String
//    lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_kill_team)


        presenter.model.killTeam = Storage.killTeam
        presenter.model.killTeam!!.killTeamName = Storage.killTeamName.toString()

        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = presenter.model.killTeam!!.killTeamName

        configUI()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.editkillteam_menu, menu)
        return true
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//
//        val intent = Intent(this, TableKillTeam::class.java)
//        startActivity(intent)
//        return true
//    }

    fun configUI() {
        configListView()
        configTextView()
    }

    fun configTextView() {
//        textViewEditKillTeam = findViewById(R.id.textViewEditKillTeam)
//        textViewEditKillTeam.setTextColor(Color.RED)
//        textViewEditKillTeam.text = presenter.model.killTeam!!.killTeamName

    }

    fun configListView() {
        listViewEditKillTeam = findViewById(R.id.listViewEditKillTeam)
        val fireTeamArray = mutableListOf<FireTeam>()
        val killTeam = presenter.model.killTeam
            if (killTeam != null) {
                for (fireTeam in killTeam.fireTeams) {
                    fireTeamArray.add(fireTeam)
                    println(fireTeam.name)
                }
            } else {
                println("ERROR 404")
            }



        val adapter = MyCustomAdapter(this, fireTeamArray, this)
        listViewEditKillTeam.adapter = adapter
    }

//    fun goToNextActivity() {
//        val intent = Intent(this, Dataslate::class.java)
////        stringIntent = editText.text.toString()
////        intent.putExtra("value", stringIntent)
//        startActivity(intent)
//
//    }

    private class MyCustomAdapter (context: Context, fireTeam: MutableList<FireTeam>, activity: EditKillTeam) : BaseAdapter() {

        private val myContext: Context
        private val myFireTeam: MutableList<FireTeam>
        private val myActivity: EditKillTeam

        init {
            myContext = context
            myFireTeam = fireTeam
            myActivity = activity
        }

        override fun getCount(): Int {
            return myFireTeam.size
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItem(position: Int): Any {
            return "Test String"
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {

            val fireTeam = myFireTeam[position]
            val titleText = TextView(myContext)
            titleText.text = fireTeam.name
            val button = Button(myContext)
            button.text = fireTeam.name

            return button
        }
    }
}