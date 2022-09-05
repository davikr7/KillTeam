package com.example.killteam.HomeActivity

import com.example.killteam.DataKillTeam
import com.example.killteam.Model.Faction
import com.google.gson.Gson

class MainActivityPresenter {
    var model = MainActivityModel()

    fun parseJson() : Array<Faction> {
        val gson = Gson()
        val jsonFile = DataKillTeam.dataJson()
        val factions = gson.fromJson(jsonFile, Array<Faction>::class.java)
        return factions
    }

    fun startGame() {
        if (model.turningPoint == 0) {
            model.isStartGame = true
            model.commandPoint = 3
            model.turningPoint = 1
        }
        else {
            model.commandPoint +=1
            model.turningPoint +=1
        }
    }

    fun endGame() {
        model.turningPoint = 0
        model.commandPoint = 0
        model.victoryPoint = 0
        model.isStartGame = false
    }
}