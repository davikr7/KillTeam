package com.example.killteam.Model

import com.example.killteam.*

data class Unit (
    val name: String,
    val description: String,
    val movement: Int,
    val actionPointLimit: Int,
    val groupActivation: Int,
    val defense: Int,
    val save: Int,
    val wounds: Int,
//    val selectedRangeWeapon: List<Weapon>,
//    val keyWords: List<String>,
//    val abilities: List<Ability>,
//    val uniqueActions: List<Action>,
//    val id: String,
//    val currentWounds: Int,
//    val unitType: List<String>,
//    val unitImageName: String,
//    val selectedMeleeWeapon: List<Weapon>,
//    val equipments: List<Equipment>,
//    val availableWeapons: List<Weapon>
)