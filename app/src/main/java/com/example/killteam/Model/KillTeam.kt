package com.example.killteam.Model

import com.example.killteam.Model.FireTeam

data class KillTeam (
    var killTeamName: String,
    val factionName: String,
    val fireTeams: List<FireTeam>
)