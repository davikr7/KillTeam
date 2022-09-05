package com.example.killteam.TableKillTeam

import com.example.killteam.DataKillTeam
import com.example.killteam.Model.Faction
import com.google.gson.Gson

class TableKillTeamPresenter {

    var model = TableKillTeamModel()

    fun parseJson() : Array<Faction> {
        val gson = Gson()
        val jsonFile = DataKillTeam.dataJson()
        val factions = gson.fromJson(jsonFile, Array<Faction>::class.java)
        return factions
    }
}