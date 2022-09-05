package com.example.killteam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.killteam.HomeActivity.MainActivityModel
import com.example.killteam.HomeActivity.MainActivityPresenter
import com.example.killteam.TableKillTeam.TableKillTeam
import com.example.killteam.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {



//    lateinit var textViewCommandPoint:TextView
//    lateinit var textViewVictoryPoint:TextView
//    lateinit var textViewTurningPoint: TextView
//    lateinit var buttonStartGame: Button
//    lateinit var buttonEndGame: Button
//
//    lateinit var bottomNavigationMain: BottomNavigationView
//
//    lateinit var buttonRemoveCommandPoint: Button
//    lateinit var buttonAddCommandPoint: Button
//    lateinit var buttonRemoveVictoryPoint: Button
//    lateinit var buttonAddVictoryPoint: Button
//    lateinit var buttonKillTeam: Button

    var presenter = MainActivityPresenter()
    var model = MainActivityModel()

    lateinit var bindingClass: ActivityMainBinding


//refactoring on create

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        configUI()
        newActivity()


//        val factions = presenter.parseJson()
//        for (faction in factions) {
//            for (killteam in faction.killTeam) {
//                println(killteam.killTeamName)
//            }
//        }
    }

    fun newActivity() {
        bindingClass.buttonKillTeam.setOnClickListener {
            val intentKillTeams = Intent(this@MainActivity, TableKillTeam::class.java)
            startActivity(intentKillTeams)
        }
    }

    fun configUI() {

        configTextView()
        configButton()
        configBottom()

    }

    fun configTextView() {

//        textViewCommandPoint = findViewById(R.id.textViewCommandPoint)
//        textViewVictoryPoint = findViewById(R.id.textViewVictoryPoint)
//        textViewTurningPoint = findViewById(R.id.textViewTurningPoint)

        updateTextView()
    }

    fun configButton () {
//        buttonStartGame = findViewById(R.id.buttonStartGame)
//        buttonEndGame = findViewById(R.id.buttonEndGame)
//
//        buttonRemoveCommandPoint = findViewById(R.id.buttonRemoveCommandPoint)
//        buttonAddCommandPoint = findViewById(R.id.buttonAddCommandPoint)
//        buttonRemoveVictoryPoint = findViewById(R.id.buttonRemoveVictoryPoint)
//        buttonAddVictoryPoint = findViewById(R.id.buttonAddVictoryPoint)
//        buttonKillTeam = findViewById(R.id.buttonKillTeam)

        updateButton()
    }

    fun configBottom () {
//        bottomNavigationMain = findViewById<BottomNavigationView>(R.id.bottomNavigationMain)

        bindingClass.bottomNavigationMain.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.bottomCounter -> {
                    Toast.makeText(this, R.string.bottomCounter, Toast.LENGTH_SHORT).show()
                }
                R.id.bottomKillteam -> {
                    Toast.makeText(this, R.string.bottomKillteam, Toast.LENGTH_SHORT).show()
                }
                R.id.bottomPloys -> {
                    Toast.makeText(this, R.string.bottomPloys, Toast.LENGTH_SHORT).show()
                }
                R.id.bottomTacOps -> {
                    Toast.makeText(this, R.string.bottomTacOps, Toast.LENGTH_SHORT).show()
                }
            }

            true
        }
    }

    fun updateTextView () {
        bindingClass.textViewCommandPoint.text = "Command Point = ${presenter.model.commandPoint}"
        bindingClass.textViewVictoryPoint.text = "Victory Point = ${presenter.model.victoryPoint}"
        bindingClass.textViewTurningPoint.text = "Turning Point = ${presenter.model.turningPoint}"
    }

    fun updateButton() {
        bindingClass.buttonRemoveCommandPoint.isEnabled = presenter.model.isStartGame
        bindingClass.buttonAddCommandPoint.isEnabled = presenter.model.isStartGame
        bindingClass.buttonRemoveVictoryPoint.isEnabled = presenter.model.isStartGame
        bindingClass.buttonAddVictoryPoint.isEnabled = presenter.model.isStartGame
    }

    fun onClickRemoveCommandPoint(view: View) {
        if (presenter.model.commandPoint > 0) {
            presenter.model.commandPoint -= 1
            updateTextView()
        } else {
            return
        }

    }
    fun onClickRemoveVictoryPoint(view: View) {
        if (presenter.model.victoryPoint > 0) {
            presenter.model.victoryPoint -= 1
            updateTextView()
        } else {
            return
        }
    }
    fun onClickAddCommandPoint(view: View) {
        presenter.model.commandPoint += 1
        updateTextView()
    }
    fun onClickAddVictoryPoint(view: View) {
        presenter.model.victoryPoint += 1
        updateTextView()
    }

    fun onClickStartGame(view: View) {
        //здесь ты спрашиваешь "Игра началась?"
        // view - то кнопка, на которую я нажимаю и обращаясь к ней, я могу изменять некие параметры
        //view.setBackgroundColor(Color.BLUE)
        presenter.startGame()
        bindingClass.buttonStartGame.text = "Next Turn"

        updateTextView()

        updateButton()
    }

    fun onClickEndGame(view: View) {
        presenter.endGame()

        updateTextView()

        bindingClass.buttonStartGame.text = "Start Game"

        updateButton()

    }
}

//MODEL

//data class Faction (
//    val name: String,
//    val killTeam: List<KillTeam>
//)
//
//data class KillTeam (
//    var killTeamName: String,
//    val factionName: String,
//    val fireTeams: List<FireTeam>
//    )
//
//data class FireTeam (
//    val name: String,
//    val availableDataslates: List<Unit>
//        )
//
//data class Unit (
//    val name: String,
//    val description: String,
//    val movement: Int,
//    val actionPointLimit: Int,
//    val groupActivation: Int,
//    val defense: Int,
//    val save: Int,
//    val wounds: Int,
//    val selectedRangeWeapon: List<Weapon>,
//    val keyWords: List<String>,
//    val abilities: List<Ability>,
//    val uniqueActions: List<UniqueAction>,
//    val id: String,
//    val currentWounds: Int,
//    val unitType: List<String>,
//    val unitImageName: String,
//    val selectedMeleeWeapon: List<Weapon>,
//    val equipments: List<Equipment>,
//    val availableWeapons: List<Weapon>
//        )
//
//data class Weapon (
//    val name: String
//        )

//data class Keyword (
//
//)

//data class Ability (
//    val name: String
//)

//data class UniqueAction (
//    val name: String
//)

//data class UnitType (
//
//)

//data class Equipment (
//    val name: String,
//    val description: String,
//    val cost: Int
//)



//JSON

class DataKillTeam {

        companion object {
            fun dataJson(): String = """
      [
          {
              "name": "Imperium",
              "killTeam": [
                  {
                      "factionName": "Adepta Sororitas",
                      "killTeamName": "Ecclesiarchy",
                      "countOfFireTeam": 2,
                      "factionLogo": "ECCLESIARCHY",
                      "fireTeams": [
                          {
                              "name": "Battle sister",
                              "archetype": [
                                  "security"
                              ],
                              "availableDataslates": [
                                  {
                                      "name": "BATTLE SISTER (WARRIOR)",
                                      "description": "Battle Sisters are the foot soldiers of the God-Emperor. Before their thundering boltguns, countless aliens, traitors, heretics and mutants have met their doom. With voices raised in prayer, their faith forms a shield around their souls.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 3,
                                      "wounds": 8,
                                      "selectedRangeWeapon": {
                                          "name": "Boltgun",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "id": "ac8d2434-7bed-4903-95fe-a802cb288981"
                                      },
                                      "keyWords": [
                                          "ECCLESIARCHY",
                                          "IMPERIUM",
                                          "ADEPTUS MINISTORUM",
                                          "ADEPTA SORORITAS",
                                          "<ORDER>",
                                          "BATTLE SISTER",
                                          "WARRIOR"
                                      ],
                                      "id": "230af2b5-e9ff-414e-83cc-02b16d13da4e",
                                      "currentWounds": 8,
                                      "unitType": [
                                          "staunch",
                                          "marksman"
                                      ],
                                      "unitImageName": "BATTLE_SISTER_WARRIOR",
                                      "selectedMeleeWeapon": {
                                          "name": "Gun butt",
                                          "type": "close",
                                          "attacks": 3,
                                          "ballisticWeaponSkill": 4,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "id": "af1db244-1304-460b-bab7-710cee47984c"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Boltgun",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "id": "ac8d2434-7bed-4903-95fe-a802cb288981"
                                          },
                                          {
                                              "name": "Gun butt",
                                              "type": "close",
                                              "attacks": 3,
                                              "ballisticWeaponSkill": 4,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "id": "af1db244-1304-460b-bab7-710cee47984c"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "BATTLE SISTER (ICON BEARER)",
                                      "description": "These Sisters are thrice blessed to bear the holy icons known as simulacrum imperialis. Each artefact is an object offervent devotion - representations of martyred warriors, recreations of objects and deeds associated with them or repositories of their mortal remains.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 3,
                                      "wounds": 8,
                                      "selectedRangeWeapon": {
                                          "name": "Boltgun",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "id": "3df68dc1-a30c-4c21-ade8-8eaa0d222022"
                                      },
                                      "keyWords": [
                                          "ECCLESIARCHY",
                                          "IMPERIUM",
                                          "ADEPTUS MINISTORUM",
                                          "ADEPTA SORORITAS",
                                          "<ORDER>",
                                          "BATTLE SISTER",
                                          "ICON BEARER"
                                      ],
                                      "abilities": [
                                          {
                                              "name": "Icon Bearer",
                                              "description": "When determining control of an objective marker, treat this operative's APL characteristic as being 1 higher. Note that this is not a modifier. In narrative play, this is cumulative with the Focused Battle Honour.",
                                              "id": "2447855e-b35a-48f2-8ff9-744473e0a656"
                                          }
                                      ],
                                      "uniqueActions": [
                                          {
                                              "name": "Icon of Purity",
                                              "cost": 1,
                                              "description": "Until the end of the Turning Point, while this operative is Visible to and within 3' of a friendly ADEPTA SORORITAS operative, that friendly operative is inspired by purity. While an operative is inspired by purity, each time it fights in combat or makes a shooting attack, in the Roll Attack Dice step of that combat or shooting attack, you can retain one of your attack dice results of 5+ that is a successful hit as a critical hit.",
                                              "id": "eb462b87-ecb5-418f-9058-0f734c868e43"
                                          }
                                      ],
                                      "id": "2dfc0220-14d9-4334-b25b-00fe51cb60e5",
                                      "currentWounds": 8,
                                      "unitType": [
                                          "staunch",
                                          "marksman"
                                      ],
                                      "unitImageName": "BATTLE_SISTER_ICON_BEARER",
                                      "selectedMeleeWeapon": {
                                          "name": "Gun butt",
                                          "type": "close",
                                          "attacks": 3,
                                          "ballisticWeaponSkill": 4,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "id": "d6760afa-b613-4c09-ae40-717a61de6b96"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Boltgun",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "id": "3df68dc1-a30c-4c21-ade8-8eaa0d222022"
                                          },
                                          {
                                              "name": "Gun butt",
                                              "type": "close",
                                              "attacks": 3,
                                              "ballisticWeaponSkill": 4,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "id": "d6760afa-b613-4c09-ae40-717a61de6b96"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "BATTLE SISTER (GUNNER)",
                                      "description": "Some Sisters are blessed to receive training in the use of special-issue, close-assault weapons. These Sisters ritually maintain their storm bolters, flamers and meltaguns, which they use in battle to unleash a blistering repudiation of heresy.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 3,
                                      "wounds": 8,
                                      "selectedRangeWeapon": {
                                          "name": "Ministorum flamer",
                                          "type": "range",
                                          "attacks": 5,
                                          "ballisticWeaponSkill": 2,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "specialRules": [
                                              {
                                                  "name": "Rng 6'",
                                                  "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                              },
                                              {
                                                  "name": "Torrent 2'",
                                                  "description": "Each time a friendly operative performs a Shoot action or Overwatch action and selects this weapon, after making the shooting attack against the target, it can make a shooting attack with this weapon against each other valid target within x of the original target and each other, x is the distance after the weapon's Torrent, e.g. Torrent 2'."
                                              }
                                          ],
                                          "id": "31f93d04-af0d-4a3c-8a4f-862505deff87"
                                      },
                                      "keyWords": [
                                          "ECCLESIARCHY",
                                          "IMPERIUM",
                                          "ADEPTUS MINISTORUM",
                                          "ADEPTA SORORITAS",
                                          "<ORDER>",
                                          "BATTLE SISTER",
                                          "GUNNER"
                                      ],
                                      "id": "210d85f9-a33f-44c3-8879-4da874b87e0a",
                                      "currentWounds": 8,
                                      "unitType": [
                                          "staunch",
                                          "marksman"
                                      ],
                                      "unitImageName": "BATTLE_SISTER_GUNNER",
                                      "selectedMeleeWeapon": {
                                          "name": "Gun butt",
                                          "type": "close",
                                          "attacks": 3,
                                          "ballisticWeaponSkill": 4,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "id": "4f018a10-e58d-468a-b337-1a78c3e6e141"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Meltagun",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 6,
                                              "criticalDamage": 3,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  },
                                                  {
                                                      "name": "AP2",
                                                      "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                  }
                                              ],
                                              "criticalHitSpecialRules": [
                                                  {
                                                      "name": "MW4",
                                                      "description": "Mortal wounds. Each time a friendly operative makes a shooting attack with this weapon, in the roll attack dice step of that shooting attack, for each critical hit retained, inflict x mortal wounds on the target, x is the number after the weapon's MW, e.g. MW3."
                                                  }
                                              ],
                                              "id": "5c2fecca-88ec-4458-b8dc-cab9d25856e4"
                                          },
                                          {
                                              "name": "Ministorum flamer",
                                              "type": "range",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  },
                                                  {
                                                      "name": "Torrent 2'",
                                                      "description": "Each time a friendly operative performs a Shoot action or Overwatch action and selects this weapon, after making the shooting attack against the target, it can make a shooting attack with this weapon against each other valid target within x of the original target and each other, x is the distance after the weapon's Torrent, e.g. Torrent 2'."
                                                  }
                                              ],
                                              "id": "31f93d04-af0d-4a3c-8a4f-862505deff87"
                                          },
                                          {
                                              "name": "Storm bolter",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "specialRules": [
                                                  {
                                                      "name": "Relentless",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, you can re-roll any or all of your attack dice."
                                                  }
                                              ],
                                              "id": "77172029-3707-47ba-9fa6-68f16526894d"
                                          },
                                          {
                                              "name": "Gun butt",
                                              "type": "close",
                                              "attacks": 3,
                                              "ballisticWeaponSkill": 4,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "id": "4f018a10-e58d-468a-b337-1a78c3e6e141"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "BATTLE SISTER (HEAVY GUNNER)",
                                      "description": "Among the highly trained Adepta Sororitas, those Sisters granted the honour to bear the most potent armaments enable their team to engage heavily armoured opposition. Their expert eye for target prioritisation and enemy weak points makes them powerful assets.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 3,
                                      "wounds": 8,
                                      "selectedRangeWeapon": {
                                          "name": "Heavy bolter",
                                          "type": "range",
                                          "attacks": 5,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 4,
                                          "criticalDamage": 5,
                                          "specialRules": [
                                              {
                                                  "name": "Heavy",
                                                  "description": "An operative cannot perform a Charge, Fall Back or Normal Move action in the same activation in which it performs a Shoot action with this ranged weapon."
                                              },
                                              {
                                                  "name": "Fusillade",
                                                  "description": "Each time a friendly operative performs a Shoot action and selects this weapon, after selecting a valid target, you can select any number of other valid targets within  of the original target. Distribute your attack dice between the targets you have selected. Make a shooting attack with this weapon (using the same profile) against each of the targets you have selected using the attack dice you have distributed to each of them."
                                              }
                                          ],
                                          "criticalHitSpecialRules": [
                                              {
                                                  "name": "P1",
                                                  "description": "Piercing. Each time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, if you retain any critical hits, the weapon gains the APx special rule for that shooting attack, x is the number after the weapon's P, e.g. P1."
                                              }
                                          ],
                                          "id": "fcad49f1-fe52-44ff-8ffc-ac1f0d0e7033"
                                      },
                                      "keyWords": [
                                          "ECCLESIARCHY",
                                          "IMPERIUM",
                                          "ADEPTUS MINISTORUM",
                                          "ADEPTA SORORITAS",
                                          "<ORDER>",
                                          "BATTLE SISTER",
                                          "HEAVY GUNNER"
                                      ],
                                      "id": "2af33290-f078-46fb-9828-4b206e416c43",
                                      "currentWounds": 8,
                                      "unitType": [
                                          "staunch",
                                          "marksman"
                                      ],
                                      "unitImageName": "BATTLE_SISTER_HEAVY_GUNNER",
                                      "selectedMeleeWeapon": {
                                          "name": "Gun butt",
                                          "type": "close",
                                          "attacks": 3,
                                          "ballisticWeaponSkill": 4,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "id": "215b434a-5ef7-47b3-a0c5-4054a6548ce7"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Heavy bolter",
                                              "type": "range",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 4,
                                              "criticalDamage": 5,
                                              "specialRules": [
                                                  {
                                                      "name": "Heavy",
                                                      "description": "An operative cannot perform a Charge, Fall Back or Normal Move action in the same activation in which it performs a Shoot action with this ranged weapon."
                                                  },
                                                  {
                                                      "name": "Fusillade",
                                                      "description": "Each time a friendly operative performs a Shoot action and selects this weapon, after selecting a valid target, you can select any number of other valid targets within  of the original target. Distribute your attack dice between the targets you have selected. Make a shooting attack with this weapon (using the same profile) against each of the targets you have selected using the attack dice you have distributed to each of them."
                                                  }
                                              ],
                                              "criticalHitSpecialRules": [
                                                  {
                                                      "name": "P1",
                                                      "description": "Piercing. Each time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, if you retain any critical hits, the weapon gains the APx special rule for that shooting attack, x is the number after the weapon's P, e.g. P1."
                                                  }
                                              ],
                                              "id": "fcad49f1-fe52-44ff-8ffc-ac1f0d0e7033"
                                          },
                                          {
                                              "name": "Ministorum heavy flamer",
                                              "type": "range",
                                              "attacks": 6,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "specialRules": [
                                                  {
                                                      "name": "Heavy",
                                                      "description": "An operative cannot perform a Charge, Fall Back or Normal Move action in the same activation in which it performs a Shoot action with this ranged weapon."
                                                  },
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  },
                                                  {
                                                      "name": "Torrent 2'",
                                                      "description": "Each time a friendly operative performs a Shoot action or Overwatch action and selects this weapon, after making the shooting attack against the target, it can make a shooting attack with this weapon against each other valid target within x of the original target and each other, x is the distance after the weapon's Torrent, e.g. Torrent 2'."
                                                  }
                                              ],
                                              "id": "66175210-aeca-477f-acec-605537bad2b4"
                                          },
                                          {
                                              "name": "Gun butt",
                                              "type": "close",
                                              "attacks": 3,
                                              "ballisticWeaponSkill": 4,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "id": "215b434a-5ef7-47b3-a0c5-4054a6548ce7"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "BATTLE SISTER SUPERIOR",
                                      "description": "The Sisters Superior form the crux of each squad of Battle Sisters. They speak with an authority derived from years of combat and supreme faith in the God-Emperor.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 3,
                                      "wounds": 9,
                                      "selectedRangeWeapon": {
                                          "name": "Bolt pistol",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 2,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "specialRules": [
                                              {
                                                  "name": "Rng 6'",
                                                  "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                              }
                                          ],
                                          "id": "110a1943-d2d7-4aa7-b1d1-1930a60551c2"
                                      },
                                      "keyWords": [
                                          "ECCLESIARCHY",
                                          "IMPERIUM",
                                          "ADEPTUS MINISTORUM",
                                          "ADEPTA SORORITAS",
                                          "<ORDER>",
                                          "LEADER",
                                          "BATTLE SISTER",
                                          "SUPERIOR "
                                      ],
                                      "id": "e1eb9e2d-3d9f-4886-9c1b-d3c95feae0cf",
                                      "currentWounds": 9,
                                      "unitType": [
                                          "combat",
                                          "staunch",
                                          "marksman"
                                      ],
                                      "unitImageName": "BATTLE_SISTER_SUPERIOR",
                                      "selectedMeleeWeapon": {
                                          "name": "Chainsword",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 2,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "id": "adbe169a-fb5d-49a8-a75e-95a0a678144d"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Bolt pistol",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  }
                                              ],
                                              "id": "110a1943-d2d7-4aa7-b1d1-1930a60551c2"
                                          },
                                          {
                                              "name": "Boltgun",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "id": "d5396193-b163-4733-b013-2f8ef85753a9"
                                          },
                                          {
                                              "name": "Combi-melta",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 6,
                                              "criticalDamage": 3,
                                              "specialRules": [
                                                  {
                                                      "name": "Combi",
                                                      "description": "An operative equipped with this weapon is also equipped with a boltgun."
                                                  },
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  },
                                                  {
                                                      "name": "AP2",
                                                      "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                  },
                                                  {
                                                      "name": "Limited",
                                                      "description": "This weapon can only be selected for use once per battle. If the weapon has a special rule that would allow it to make more than one shooting attack for an action (e.g. Blast), make each of those attacks as normal."
                                                  }
                                              ],
                                              "criticalHitSpecialRules": [
                                                  {
                                                      "name": "MW4",
                                                      "description": "Mortal wounds. Each time a friendly operative makes a shooting attack with this weapon, in the roll attack dice step of that shooting attack, for each critical hit retained, inflict x mortal wounds on the target, x is the number after the weapon's MW, e.g. MW3."
                                                  }
                                              ],
                                              "id": "8eb7e663-8a69-4fe3-838b-2b390bd1412c"
                                          },
                                          {
                                              "name": "Combi-plasma",
                                              "type": "range",
                                              "profileName": "Standard",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 5,
                                              "criticalDamage": 6,
                                              "subProfiles": [
                                                  {
                                                      "name": "Combi-plasma",
                                                      "type": "range",
                                                      "profileName": "Supercharge",
                                                      "attacks": 4,
                                                      "ballisticWeaponSkill": 2,
                                                      "damage": 5,
                                                      "criticalDamage": 6,
                                                      "specialRules": [
                                                          {
                                                              "name": "Combi",
                                                              "description": "An operative equipped with this weapon is also equipped with a boltgun."
                                                          },
                                                          {
                                                              "name": "AP2",
                                                              "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                          },
                                                          {
                                                              "name": "Hot",
                                                              "description": "Each time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, for each attack dice result of 1 that is discarded, that operative suffers three mortal wounds."
                                                          },
                                                          {
                                                              "name": "Limited",
                                                              "description": "This weapon can only be selected for use once per battle. If the weapon has a special rule that would allow it to make more than one shooting attack for an action (e.g. Blast), make each of those attacks as normal."
                                                          }
                                                      ],
                                                      "id": "685e9447-a0cc-48bc-aa76-47bf658c8094"
                                                  }
                                              ],
                                              "specialRules": [
                                                  {
                                                      "name": "Combi",
                                                      "description": "An operative equipped with this weapon is also equipped with a boltgun."
                                                  },
                                                  {
                                                      "name": "AP1",
                                                      "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                  },
                                                  {
                                                      "name": "Limited",
                                                      "description": "This weapon can only be selected for use once per battle. If the weapon has a special rule that would allow it to make more than one shooting attack for an action (e.g. Blast), make each of those attacks as normal."
                                                  }
                                              ],
                                              "id": "67806091-f68e-4b27-990e-455241f5db84"
                                          },
                                          {
                                              "name": "Condemnor boltgun",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 3,
                                              "criticalDamage": 3,
                                              "specialRules": [
                                                  {
                                                      "name": "Combi",
                                                      "description": "An operative equipped with this weapon is also equipped with a boltgun."
                                                  },
                                                  {
                                                      "name": "Silent",
                                                      "description": "While an operative has a Conceal order, it can perform Shoot actions if this is the ranged weapon (or weapon profile) that is selected for the shooting attack(s)."
                                                  },
                                                  {
                                                      "name": "Limited",
                                                      "description": "This weapon can only be selected for use once per battle. If the weapon has a special rule that would allow it to make more than one shooting attack for an action (e.g. Blast), make each of those attacks as normal."
                                                  }
                                              ],
                                              "criticalHitSpecialRules": [
                                                  {
                                                      "name": "MW1",
                                                      "description": "Mortal wounds. Each time a friendly operative makes a shooting attack with this weapon, in the roll attack dice step of that shooting attack, for each critical hit retained, inflict x mortal wounds on the target, x is the number after the weapon's MW, e.g. MW3."
                                                  },
                                                  {
                                                      "name": "P1",
                                                      "description": "Piercing. Each time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, if you retain any critical hits, the weapon gains the APx special rule for that shooting attack, x is the number after the weapon's P, e.g. P1."
                                                  }
                                              ],
                                              "id": "088ddbcd-8ed7-4463-89f1-d2a30190ead6"
                                          },
                                          {
                                              "name": "Inferno pistol",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 5,
                                              "criticalDamage": 3,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 3'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  },
                                                  {
                                                      "name": "AP2",
                                                      "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                  }
                                              ],
                                              "criticalHitSpecialRules": [
                                                  {
                                                      "name": "MW3",
                                                      "description": "Mortal wounds. Each time a friendly operative makes a shooting attack with this weapon, in the roll attack dice step of that shooting attack, for each critical hit retained, inflict x mortal wounds on the target, x is the number after the weapon's MW, e.g. MW3."
                                                  }
                                              ],
                                              "id": "d6168ff6-feaa-4767-b515-03f47b92e1a2"
                                          },
                                          {
                                              "name": "Ministorum combi-flamer",
                                              "type": "range",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "specialRules": [
                                                  {
                                                      "name": "Combi",
                                                      "description": "An operative equipped with this weapon is also equipped with a boltgun."
                                                  },
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  },
                                                  {
                                                      "name": "Torrent 2'",
                                                      "description": "Each time a friendly operative performs a Shoot action or Overwatch action and selects this weapon, after making the shooting attack against the target, it can make a shooting attack with this weapon against each other valid target within x of the original target and each other, x is the distance after the weapon's Torrent, e.g. Torrent 2'."
                                                  },
                                                  {
                                                      "name": "Limited",
                                                      "description": "This weapon can only be selected for use once per battle. If the weapon has a special rule that would allow it to make more than one shooting attack for an action (e.g. Blast), make each of those attacks as normal."
                                                  }
                                              ],
                                              "id": "13d56f01-8c9a-44e4-b254-8e33529fba39"
                                          },
                                          {
                                              "name": "Ministorum hand flamer",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  },
                                                  {
                                                      "name": "Torrent 1'",
                                                      "description": "Each time a friendly operative performs a Shoot action or Overwatch action and selects this weapon, after making the shooting attack against the target, it can make a shooting attack with this weapon against each other valid target within x of the original target and each other, x is the distance after the weapon's Torrent, e.g. Torrent 2'."
                                                  }
                                              ],
                                              "id": "ac3ee95a-dbbf-4faf-9c69-6e48072928f5"
                                          },
                                          {
                                              "name": "Plasma pistol",
                                              "type": "range",
                                              "profileName": "Standard",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 5,
                                              "criticalDamage": 6,
                                              "subProfiles": [
                                                  {
                                                      "name": "Plasma pistol",
                                                      "type": "range",
                                                      "profileName": "Supercharge",
                                                      "attacks": 4,
                                                      "ballisticWeaponSkill": 2,
                                                      "damage": 5,
                                                      "criticalDamage": 6,
                                                      "specialRules": [
                                                          {
                                                              "name": "Rng 6'",
                                                              "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                          },
                                                          {
                                                              "name": "AP2",
                                                              "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                          },
                                                          {
                                                              "name": "Hot",
                                                              "description": "Each time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, for each attack dice result of 1 that is discarded, that operative suffers three mortal wounds."
                                                          }
                                                      ],
                                                      "id": "39fbf007-775f-40b2-96d2-214899f1fc07"
                                                  }
                                              ],
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  },
                                                  {
                                                      "name": "AP1",
                                                      "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                  }
                                              ],
                                              "id": "f9f4cda0-e116-44dc-b0e2-96074b6dd724"
                                          },
                                          {
                                              "name": "Chainsword",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "id": "adbe169a-fb5d-49a8-a75e-95a0a678144d"
                                          },
                                          {
                                              "name": "Gun butt",
                                              "type": "close",
                                              "attacks": 3,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "id": "e99ed64f-e82f-4284-95ed-b74bedfe1b90"
                                          },
                                          {
                                              "name": "Power maul",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 4,
                                              "criticalDamage": 5,
                                              "criticalHitSpecialRules": [
                                                  {
                                                      "name": "Stun",
                                                      "description": "Each time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, if you retain any critical hits, subtract 1 from the target's APL. Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat:",
                                                      "subText": [
                                                          "The first time you strike with a critical hit, select one of your opponent's normal hits from that combat to be discarded",
                                                          "The second time you strike with a critical hit, subtract 1 from the target's APL."
                                                      ]
                                                  }
                                              ],
                                              "id": "ac51345d-844d-475e-98ab-6fb3cc0946ee"
                                          },
                                          {
                                              "name": "Power weapon",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 4,
                                              "criticalDamage": 6,
                                              "specialRules": [
                                                  {
                                                      "name": "Lethal 5+",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, your attack dice results of equal to or greater than x that are successful hits are critical hits, x is the number after the weapon's Lethal, e.g. Lethal 5+."
                                                  }
                                              ],
                                              "id": "70540415-500a-40f5-b338-71a31fec0afe"
                                          }
                                      ]
                                  }
                              ],
                              "currentDataslates": [],
                              "id": "f43ff25b-c500-4a1a-9d45-4979bfef3199",
                              "defaultDataslates": {
                                  "230af2b5-e9ff-414e-83cc-02b16d13da4e": 5
                              }
                          },
                          {
                              "name": "Repentia",
                              "archetype": [
                                  "seekAndDestroy"
                              ],
                              "availableDataslates": [
                                  {
                                      "name": "SISTER REPENTIA",
                                      "description": "It is rare but not unknown for a Sister to fail in her duty, suffering disgrace as a result. Those who do are offered a chance of redemption. Stripped of armour, they are sent to wreak penitent slaughter upon the enemy with cleaving blows from brutal eviscerators.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 6,
                                      "wounds": 7,
                                      "keyWords": [
                                          "ECCLESIARCHY",
                                          "IMPERIUM",
                                          "ADEPTUS MINISTORUM",
                                          "ADEPTA SORORITAS",
                                          "<ORDER>",
                                          "SISTER",
                                          "REPENTIA"
                                      ],
                                      "abilities": [
                                          {
                                              "name": "Solace in Anguish",
                                              "description": "Each time this operative would lose a wound, roll one D6: on a 5+, that wound is not lost.",
                                              "id": "ffa03b6b-4238-423b-90a6-ac126bc1a23d"
                                          }
                                      ],
                                      "id": "d0c97d1c-2f55-44bd-9092-125e45513127",
                                      "currentWounds": 7,
                                      "unitType": [
                                          "combat",
                                          "scout"
                                      ],
                                      "unitImageName": "SISTER_REPENTIA",
                                      "selectedMeleeWeapon": {
                                          "name": "Penitent eviscerator",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 4,
                                          "damage": 5,
                                          "criticalDamage": 6,
                                          "specialRules": [
                                              {
                                                  "name": "Brutal",
                                                  "description": "Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat, your opponent can only parry with critical hits."
                                              }
                                          ],
                                          "criticalHitSpecialRules": [
                                              {
                                                  "name": "Reap 2",
                                                  "description": "Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat, if you strike with a critical hit, inflict x mortal wounds on each other enemy operative Visible to the friendly operative and within 1' of it or the target operative, x is the number after the weapon's Reap (e.g. Reap 1)."
                                              }
                                          ],
                                          "id": "c07b3fe9-4c68-44b1-be5c-5648f503819d"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Penitent eviscerator",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 4,
                                              "damage": 5,
                                              "criticalDamage": 6,
                                              "specialRules": [
                                                  {
                                                      "name": "Brutal",
                                                      "description": "Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat, your opponent can only parry with critical hits."
                                                  }
                                              ],
                                              "criticalHitSpecialRules": [
                                                  {
                                                      "name": "Reap 2",
                                                      "description": "Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat, if you strike with a critical hit, inflict x mortal wounds on each other enemy operative Visible to the friendly operative and within 1' of it or the target operative, x is the number after the weapon's Reap (e.g. Reap 1)."
                                                  }
                                              ],
                                              "id": "c07b3fe9-4c68-44b1-be5c-5648f503819d"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "REPENTIA SUPERIOR",
                                      "description": "The solemn task of guiding wayward Sisters in their atonement falls to Repentia Superior. These veterans are stern taskmasters who drive their charges forward with bellowed prayers and lashes from their neural whips, watching vigilantly for any remnant signs of sinfulness.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 3,
                                      "wounds": 9,
                                      "selectedRangeWeapon": {
                                          "name": "Neural whips",
                                          "type": "range",
                                          "attacks": 5,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "specialRules": [
                                              {
                                                  "name": "Lethal 5+",
                                                  "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, your attack dice results of equal to or greater than x that are successful hits are critical hits, x is the number after the weapon's Lethal, e.g. Lethal 5+."
                                              },
                                              {
                                                  "name": "Rng 3'",
                                                  "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                              }
                                          ],
                                          "criticalHitSpecialRules": [
                                              {
                                                  "name": "Stun",
                                                  "description": "ach time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, if you retain any critical hits, subtract 1 from the target's APL. Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat:",
                                                  "subText": [
                                                      "The first time you strike with a critical hit, select one of your opponent's normal hits from that combat to be discarded",
                                                      "The second time you strike with a critical hit, subtract 1 from the target's APL."
                                                  ]
                                              }
                                          ],
                                          "id": "f78167d8-bc64-4388-bf6f-5e62be184492"
                                      },
                                      "keyWords": [
                                          "ECCLESIARCHY",
                                          "IMPERIUM",
                                          "ADEPTUS MINISTORUM",
                                          "ADEPTA SORORITAS",
                                          "<ORDER>",
                                          "LEADER",
                                          "REPENTIA",
                                          "SUPERIOR "
                                      ],
                                      "uniqueActions": [
                                          {
                                              "name": "Whip Into Fury",
                                              "cost": 1,
                                              "description": "Select one friendly SISTER REPENTIA operative within 3' of and Visible to this operative. Add 1 to that friendly operative's APL and, until the end of that operative's next activation, add 2' to that friendly operative's Movement characteristic.",
                                              "id": "331a9fb3-5e04-402d-9a9d-0083e8934462"
                                          }
                                      ],
                                      "id": "98b2efba-b7a8-4122-989a-1efad22f140d",
                                      "currentWounds": 9,
                                      "unitType": [
                                          "combat",
                                          "staunch"
                                      ],
                                      "unitImageName": "REPENTIA_SUPERIOR",
                                      "selectedMeleeWeapon": {
                                          "name": "Neural whips",
                                          "type": "close",
                                          "attacks": 5,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "specialRules": [
                                              {
                                                  "name": "Lethal 5+",
                                                  "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, your attack dice results of equal to or greater than x that are successful hits are critical hits, x is the number after the weapon's Lethal, e.g. Lethal 5+."
                                              }
                                          ],
                                          "criticalHitSpecialRules": [
                                              {
                                                  "name": "Stun",
                                                  "description": "ach time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, if you retain any critical hits, subtract 1 from the target's APL. Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat:",
                                                  "subText": [
                                                      "The first time you strike with a critical hit, select one of your opponent's normal hits from that combat to be discarded",
                                                      "The second time you strike with a critical hit, subtract 1 from the target's APL."
                                                  ]
                                              }
                                          ],
                                          "id": "4affee3b-6c67-40ce-83ef-12fe2228c7a5"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Neural whips",
                                              "type": "range",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "specialRules": [
                                                  {
                                                      "name": "Lethal 5+",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, your attack dice results of equal to or greater than x that are successful hits are critical hits, x is the number after the weapon's Lethal, e.g. Lethal 5+."
                                                  },
                                                  {
                                                      "name": "Rng 3'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  }
                                              ],
                                              "criticalHitSpecialRules": [
                                                  {
                                                      "name": "Stun",
                                                      "description": "ach time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, if you retain any critical hits, subtract 1 from the target's APL. Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat:",
                                                      "subText": [
                                                          "The first time you strike with a critical hit, select one of your opponent's normal hits from that combat to be discarded",
                                                          "The second time you strike with a critical hit, subtract 1 from the target's APL."
                                                      ]
                                                  }
                                              ],
                                              "id": "f78167d8-bc64-4388-bf6f-5e62be184492"
                                          },
                                          {
                                              "name": "Neural whips",
                                              "type": "close",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "specialRules": [
                                                  {
                                                      "name": "Lethal 5+",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, your attack dice results of equal to or greater than x that are successful hits are critical hits, x is the number after the weapon's Lethal, e.g. Lethal 5+."
                                                  }
                                              ],
                                              "criticalHitSpecialRules": [
                                                  {
                                                      "name": "Stun",
                                                      "description": "ach time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, if you retain any critical hits, subtract 1 from the target's APL. Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat:",
                                                      "subText": [
                                                          "The first time you strike with a critical hit, select one of your opponent's normal hits from that combat to be discarded",
                                                          "The second time you strike with a critical hit, subtract 1 from the target's APL."
                                                      ]
                                                  }
                                              ],
                                              "id": "4affee3b-6c67-40ce-83ef-12fe2228c7a5"
                                          }
                                      ]
                                  }
                              ],
                              "currentDataslates": [],
                              "id": "b1c376f2-62df-4688-ba5b-a320355d81ae",
                              "defaultDataslates": {
                                  "d0c97d1c-2f55-44bd-9092-125e45513127": 5
                              }
                          },
                          {
                              "name": "Arco-Flagellant",
                              "archetype": [
                                  "seekAndDestroy"
                              ],
                              "availableDataslates": [
                                  {
                                      "name": "ARCO-FLAGELLANT",
                                      "description": "The Ecclesiarchy punishes those guilty of heresy in a number of ways. Those subjected to arco-flagellation are painfully remade - fitted with cybernetic weapons and sensory suppressors - then driven into a frenzy and unleashed as near mindless killing machines.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 6,
                                      "wounds": 10,
                                      "keyWords": [
                                          "ECCLESIARCHY",
                                          "IMPERIUM",
                                          "ADEPTUS MINISTORUM",
                                          "ARCO-FLAGELLANT"
                                      ],
                                      "abilities": [
                                          {
                                              "name": "Berserk Killing Machine",
                                              "description": "Each time this operative would lose a wound, roll one D6: on a 5+, that wound is not lost. This operative cannot perform mission actions or Pick Up actions. Unless otherwise specified, this operative cannot be equipped with equipment.",
                                              "id": "fb291490-2373-4b8a-a35a-26aec075d016"
                                          }
                                      ],
                                      "id": "bd74cb21-321b-486f-bcf3-62934e2de695",
                                      "currentWounds": 10,
                                      "unitType": [
                                          "combat",
                                          "scout"
                                      ],
                                      "unitImageName": "ARCOFLAGELLANT",
                                      "selectedMeleeWeapon": {
                                          "name": "Arco-flails",
                                          "type": "close",
                                          "attacks": 5,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "specialRules": [
                                              {
                                                  "name": "Ceaseless",
                                                  "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, you can re-roll any or all of your attack dice results of 1."
                                              }
                                          ],
                                          "criticalHitSpecialRules": [
                                              {
                                                  "name": "Reap 1",
                                                  "description": "Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat, if you strike with a critical hit, inflict x mortal wounds on each other enemy operative Visible to the friendly operative and within 1' of it or the target operative, x is the number after the weapon's Reap (e.g. Reap 1)."
                                              }
                                          ],
                                          "id": "1837a9fe-da3b-45e3-ba47-83296031f0dd"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Arco-flails",
                                              "type": "close",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "specialRules": [
                                                  {
                                                      "name": "Ceaseless",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, you can re-roll any or all of your attack dice results of 1."
                                                  }
                                              ],
                                              "criticalHitSpecialRules": [
                                                  {
                                                      "name": "Reap 1",
                                                      "description": "Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat, if you strike with a critical hit, inflict x mortal wounds on each other enemy operative Visible to the friendly operative and within 1' of it or the target operative, x is the number after the weapon's Reap (e.g. Reap 1)."
                                                  }
                                              ],
                                              "id": "1837a9fe-da3b-45e3-ba47-83296031f0dd"
                                          }
                                      ]
                                  }
                              ],
                              "currentDataslates": [],
                              "id": "617a44fa-2b9d-45ce-9a41-2a5d1c75b840",
                              "defaultDataslates": {
                                  "bd74cb21-321b-486f-bcf3-62934e2de695": 5
                              }
                          }
                      ],
                      "chosenFireTeams": [],
                      "ploys": [
                          {
                              "name": "EMPEROR'S GUIDANCE",
                              "description": "Until the end of the Turning Point, each time a friendly ADEPTA SORORITAS operative fights in combat or makes a shooting attack, in the Roll Attack Dice step of that combat or shooting attack, if you retain any critical hits, you can re-roll one of your attack dice.",
                              "cost": 1,
                              "type": "strategic",
                              "id": "0b71086a-559d-443a-8539-69f92c9aff0a"
                          },
                          {
                              "name": "DIVINE SHIELD",
                              "description": "Until the end of the Turning Point, each time a shooting attack is made against a friendly ADEPTA SORORITAS operative, in the Roll Defence Dice step of that shooting attack, if you retain any critical saves, you can re-roll one of your failed saves.",
                              "cost": 1,
                              "type": "strategic",
                              "id": "61b64840-7000-4f09-9b91-96c5b0742812"
                          },
                          {
                              "name": "EXTREMIS TRIGGER WORD",
                              "description": "Until the end of the Turning Point:",
                              "cost": 1,
                              "type": "strategic",
                              "id": "8af11b3e-0146-4eb1-935c-2fd5776e25d2",
                              "subTexts": [
                                  "Each time a friendly ARCO-FLAGELLANT operative performs a Dash or Charge action, it can move an additional 1' for that action.",
                                  "Arco-flaiIs that friendly ARCO-FLAGELLANT operatives are equipped with gain the Lethal 5+ special rule.",
                                  "You can only use this Strategic Ploy once."
                              ]
                          },
                          {
                              "name": "PENANCE THROUGH DEATH",
                              "description": "Use this Tactical Ploy when a friendly SISTER REPENTIA or ARCO-FLAGELLANT operative is incapacitated in combat by an enemy operative. Before that friendly operative is removed from the killzone, select one melee weapon it is equipped with and roll one attack dice as if it is fighting in combat. If the result is a successful hit, you can immediately strike an enemy operative within Engagement Range of it. Then remove that friendly operative from the killzone as normal.",
                              "cost": 1,
                              "type": "tactical",
                              "id": "6057ce76-d72e-4d36-856b-5bf2bc004e70"
                          },
                          {
                              "name": "STORM OF RETRIBUTION",
                              "description": "Use this Tactical Ploy when a friendly BATTLE SISTER GUNNER or friendly BATTLE SISTER HEAVY GUNNER operative is activated. Until the end of that operative's activation, ranged weapons it is equipped with lose the Heavy special rule (if they have it) and gain the No Cover special rule.",
                              "cost": 1,
                              "type": "tactical",
                              "id": "d652f126-549b-4e65-8dab-a0abf5594df9"
                          },
                          {
                              "name": "DIVINE INTERVENTION",
                              "description": "Use this Tactical Ploy in the Resolve Successful Hits step of a combat or shooting attack, when an attack dice would inflict damage on a friendly ADEPTA SORORITAS operative. Ignore the damage inflicted from that attack dice.",
                              "cost": 1,
                              "type": "tactical",
                              "id": "81c45aee-70e8-458e-997a-340d6c8fa0af"
                          }
                      ],
                      "equipments": [
                          {
                              "name": "ROSARIUS",
                              "description": "LEADER operative only. The operative gains the following ability for the battle:",
                              "cost": 2,
                              "maxNumberPerKillTeam": 1,
                              "id": "9878e4c7-4359-4b55-92cd-8b81ba713a47",
                              "ability": {
                                  "name": "Rosarius",
                                  "description": "This operative has a 4+ invulnerable save.",
                                  "id": "e2f3afb3-5aa7-4fab-b21b-4da34601fe94"
                              }
                          },
                          {
                              "name": "PHIAL OF RESTORATION",
                              "description": "The operative gains the following ability for the battle:",
                              "cost": 2,
                              "maxNumberPerKillTeam": 1,
                              "id": "42b38edb-e4af-4eb5-8638-ba1a3c2ed272",
                              "ability": {
                                  "name": "Phial of Restoration",
                                  "description": "Once per battle, when this operative is activated, it can use this ability. If it does so, it regains up to 2D3 lost wounds.",
                                  "id": "7f32c227-584d-4a5c-bd44-490985d71411"
                              }
                          },
                          {
                              "name": "FRAG GRENADE",
                              "description": "The operative is equipped with the following ranged weapon for the battle:",
                              "cost": 2,
                              "id": "3a59afbe-1f83-42a4-92fb-f0c653b22ba1",
                              "weapon": {
                                  "name": "Frag grenade",
                                  "type": "range",
                                  "attacks": 4,
                                  "ballisticWeaponSkill": 3,
                                  "damage": 2,
                                  "criticalDamage": 3,
                                  "specialRules": [
                                      {
                                          "name": "Rng 6'",
                                          "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                      },
                                      {
                                          "name": "Limited",
                                          "description": "This weapon can only be selected for use once per battle. If the weapon has a special rule that would allow it to make more than one shooting attack for an action (e.g. Blast), make each of those attacks as normal."
                                      },
                                      {
                                          "name": "Blast 2'",
                                          "description": "Each time a friendly operative performs a Shoot action and selects this weapon (or, in the case of profiles, this weapon's profile), after making the shooting attack against the target, make a shooting attack with this weapon (using the same profile) against each other operative Visible to and within X of the original target - each of them is a valid target and cannot be in Cover. X is the distance after the weapon's Blast, e.g. Blast 2'. An operative cannot make a shooting attack with this weapon by performing an Overwatch action."
                                      },
                                      {
                                          "name": "Indirect",
                                          "description": "Each time a friendly operative makes a shooting attack with this weapon, in the select valid target step of that shooting attack, enemy operatives are not in Cover."
                                      }
                                  ],
                                  "id": "90d8fc1f-172f-403f-ac12-8e620583cd59"
                              }
                          },
                          {
                              "name": "KRAK GRENADE",
                              "description": "The operative is equipped with the following ranged weapon for the battle. It cannot make a shooting attack with this weapon by performing an Overwatch action:",
                              "cost": 3,
                              "id": "dbc61584-20be-43b6-84bc-7e70cb578a4a",
                              "weapon": {
                                  "name": "Krak grenade",
                                  "type": "range",
                                  "attacks": 4,
                                  "ballisticWeaponSkill": 3,
                                  "damage": 4,
                                  "criticalDamage": 5,
                                  "specialRules": [
                                      {
                                          "name": "Rng 6'",
                                          "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                      },
                                      {
                                          "name": "Limited",
                                          "description": "This weapon can only be selected for use once per battle. If the weapon has a special rule that would allow it to make more than one shooting attack for an action (e.g. Blast), make each of those attacks as normal."
                                      },
                                      {
                                          "name": "AP1",
                                          "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                      },
                                      {
                                          "name": "Indirect",
                                          "description": "Each time a friendly operative makes a shooting attack with this weapon, in the select valid target step of that shooting attack, enemy operatives are not in Cover."
                                      }
                                  ],
                                  "id": "70deff3b-1ed8-44c0-bb04-6044b448b3ce"
                              }
                          },
                          {
                              "name": "PURITY SEAL",
                              "description": "The operative gains the following ability for the battle:",
                              "cost": 3,
                              "id": "885abf84-732c-4d3b-8925-8408936883d5",
                              "ability": {
                                  "name": "Purity Seal",
                                  "description": "Once per battle, when this operative is fighting in combat, making a shooting attack or a shooting attack is being made against it, you can use the Command Re-roll Tactical Ploy without spending any CP.",
                                  "id": "7013151d-0796-4f1a-9d94-779843088d3d"
                              }
                          },
                          {
                              "name": "SCOURGING BARBS",
                              "description": "SISTER REPENTIA operative only. The operative gains the following ability for the battle:",
                              "cost": 2,
                              "id": "cb0d3118-a601-4cf7-8336-bc959257e0ed",
                              "ability": {
                                  "name": "Scourging Barbs",
                                  "description": "Each time this operative fights in combat, in the Roll Attack Dice step of that combat, you can re-roll one of your attack dice. If the result of that re-roll is a 1 or 2, this operative suffers 1 mortal wound.",
                                  "id": "93723c10-8cd4-4d53-b1b1-bce01d925d67"
                              }
                          }
                      ],
                      "countEquipmentPoint": 10,
                      "id": "945f1852-6fd3-4d61-a43c-05eec9fccf75"
                  },
                  {
                      "factionName": "Adepta Sororitas",
                      "killTeamName": "Novitiate",
                      "countOfFireTeam": 1,
                      "factionLogo": "NOVITIATE",
                      "fireTeams": [
                          {
                              "name": "Novitiate",
                              "archetype": [
                                  "security",
                                  "recon"
                              ],
                              "availableDataslates": [
                                  {
                                      "name": "NOVITIATE MILITANT",
                                      "description": "Novitiates Militant are Battle Sisters in training. They have yet to earn the power armour and boltguns of fully fledged Battle Sisters, but are zealous warriors of the Emperor nonetheless, eager to join the ranks of their Order Militant.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 4,
                                      "wounds": 7,
                                      "selectedRangeWeapon": {
                                          "name": "Autogun",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "id": "02c02b08-0a6f-419b-8377-54efbfc15a9b"
                                      },
                                      "keyWords": [
                                          "NOVITIATE",
                                          "IMPERIUM",
                                          "ADEPTA SORORITAS",
                                          "<ORDER>",
                                          "NOVITIATE MILITANT"
                                      ],
                                      "id": "94292b9e-cea2-4e59-a55b-4cbceff2dc63",
                                      "currentWounds": 7,
                                      "unitType": [
                                          "combat",
                                          "marksman",
                                          "scout"
                                      ],
                                      "unitImageName": "NOVITIATE_MILITANT",
                                      "selectedMeleeWeapon": {
                                          "name": "Gun butt",
                                          "type": "close",
                                          "attacks": 3,
                                          "ballisticWeaponSkill": 4,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "id": "429ce909-c054-425b-b153-0ec95fbab975"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Autogun",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "id": "02c02b08-0a6f-419b-8377-54efbfc15a9b"
                                          },
                                          {
                                              "name": "Autopistol",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  }
                                              ],
                                              "id": "7c3e62a1-4b13-4728-aea6-7cde829b00da"
                                          },
                                          {
                                              "name": "Gun butt",
                                              "type": "close",
                                              "attacks": 3,
                                              "ballisticWeaponSkill": 4,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "id": "429ce909-c054-425b-b153-0ec95fbab975"
                                          },
                                          {
                                              "name": "Novitiate blade",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 4,
                                              "criticalDamage": 5,
                                              "specialRules": [
                                                  {
                                                      "name": "Balanced",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, you can re-roll one of your attack dice."
                                                  }
                                              ],
                                              "id": "84cdde49-a67f-4b30-bae3-414c93f56f99"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "NOVITIATE SUPERIOR",
                                      "description": "A fully fledged Battle Sister of an Order Militant, a Superior has the task of leading Novitiates in battle. They make command decisions, steer the faith of their charges and serve as inspirational examples of how a warrior of the Adepta Sororitas acts at all times.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 3,
                                      "wounds": 8,
                                      "selectedRangeWeapon": {
                                          "name": "Bolt pistol",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 2,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "specialRules": [
                                              {
                                                  "name": "Rng 6'",
                                                  "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                              }
                                          ],
                                          "id": "57a0f952-24d4-4b6d-93ea-e32042dfc2a1"
                                      },
                                      "keyWords": [
                                          "NOVITIATE",
                                          "IMPERIUM",
                                          "ADEPTA SORORITAS",
                                          "<ORDER>",
                                          "LEADER",
                                          "NOVITIATE SUPERIOR"
                                      ],
                                      "abilities": [
                                          {
                                              "name": "Lead by Example",
                                              "description": "Each time this operative is activated, if it performs any mission actions or if any enemy operatives are incapacitated as a result of any actions it performs during that activation, you can select one Ready friendly NOVITIATE operative Visible to and within 6' of it. After this operative's activation ends, you can activate that operative.",
                                              "id": "68af9abd-af3d-482a-acbd-6bd09d2bf8ea"
                                          }
                                      ],
                                      "id": "74f0264f-62d5-46d8-8dde-338beb18ab65",
                                      "currentWounds": 8,
                                      "unitType": [
                                          "combat",
                                          "staunch",
                                          "marksman"
                                      ],
                                      "unitImageName": "NOVITIATE_SUPERIOR",
                                      "selectedMeleeWeapon": {
                                          "name": "Power weapon",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 2,
                                          "damage": 4,
                                          "criticalDamage": 6,
                                          "specialRules": [
                                              {
                                                  "name": "Lethal 5+",
                                                  "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, your attack dice results of equal to or greater than x that are successful hits are critical hits, x is the number after the weapon's Lethal, e.g. Lethal 5+."
                                              }
                                          ],
                                          "id": "5f79cc14-8205-4f14-af61-548e36a96f3d"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Bolt pistol",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  }
                                              ],
                                              "id": "57a0f952-24d4-4b6d-93ea-e32042dfc2a1"
                                          },
                                          {
                                              "name": "Boltgun",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "id": "c505fd70-59c7-4d4e-80f4-5932e2322ed4"
                                          },
                                          {
                                              "name": "Plasma pistol",
                                              "type": "range",
                                              "profileName": "Standard",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 5,
                                              "criticalDamage": 6,
                                              "subProfiles": [
                                                  {
                                                      "name": "Plasma pistol",
                                                      "type": "range",
                                                      "profileName": "Supercharge",
                                                      "attacks": 4,
                                                      "ballisticWeaponSkill": 2,
                                                      "damage": 5,
                                                      "criticalDamage": 6,
                                                      "specialRules": [
                                                          {
                                                              "name": "Rng 6'",
                                                              "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                          },
                                                          {
                                                              "name": "AP2",
                                                              "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                          },
                                                          {
                                                              "name": "Hot",
                                                              "description": "Each time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, for each attack dice result of 1 that is discarded, that operative suffers three mortal wounds."
                                                          }
                                                      ],
                                                      "id": "96554a56-1080-49b1-9250-2c58ba00c311"
                                                  }
                                              ],
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  },
                                                  {
                                                      "name": "AP1",
                                                      "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                  }
                                              ],
                                              "id": "bca48540-3b54-49ec-bd9f-ff7c2a90f395"
                                          },
                                          {
                                              "name": "Gun butt",
                                              "type": "close",
                                              "attacks": 3,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "id": "8148786b-8812-4473-8619-7a75f52be5d6"
                                          },
                                          {
                                              "name": "Power weapon",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 4,
                                              "criticalDamage": 6,
                                              "specialRules": [
                                                  {
                                                      "name": "Lethal 5+",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, your attack dice results of equal to or greater than x that are successful hits are critical hits, x is the number after the weapon's Lethal, e.g. Lethal 5+."
                                                  }
                                              ],
                                              "id": "5f79cc14-8205-4f14-af61-548e36a96f3d"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "NOVITIATE PENITENT",
                                      "description": "Some Novitiates are ordered to wield brutal eviscerators as a mark of shame for minor transgressions. Urged to earn their redemption face-to-face with the enemy, such Sisters will fight with unrestrained fury to earn hack their honour.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 4,
                                      "wounds": 7,
                                      "selectedRangeWeapon": {
                                          "name": "Autopistol",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "specialRules": [
                                              {
                                                  "name": "Rng 6'",
                                                  "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                              }
                                          ],
                                          "id": "75d0116a-0c8b-435d-8b61-e9a2ef6184fe"
                                      },
                                      "keyWords": [
                                          "NOVITIATE",
                                          "IMPERIUM",
                                          "ADEPTA SORORITAS",
                                          "<ORDER>",
                                          "NOVITIATE PENITENT"
                                      ],
                                      "abilities": [
                                          {
                                              "name": "Zealous Rage",
                                              "description": "The first time this operative performs a Fight action in each of its activations, in the Roll Attack Dice step of that combat, you can re-roll any or all of your attack dice.",
                                              "id": "2230dedd-afaf-4374-ab94-a301df090601"
                                          }
                                      ],
                                      "uniqueActions": [
                                          {
                                              "name": "Absolution Through Destruction",
                                              "cost": 1,
                                              "description": "Perform a free Fight action with this operative. After completing that action's fight sequence, if this operative is still within Engagement Range of an enemy operative, you can immediately fight in combat with this operative again (for the second combat, you do not have to select the same target and the Zealous Rage ability has no effect).",
                                              "id": "b4ea6dfc-863a-4679-b249-a7d7d6de0a99"
                                          }
                                      ],
                                      "id": "831d1d33-e2ca-479d-a113-81a7a2986864",
                                      "currentWounds": 7,
                                      "unitType": [
                                          "combat",
                                          "staunch"
                                      ],
                                      "unitImageName": "NOVITIATE_PENITENT",
                                      "selectedMeleeWeapon": {
                                          "name": "Penitent eviscerator",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 4,
                                          "damage": 5,
                                          "criticalDamage": 6,
                                          "specialRules": [
                                              {
                                                  "name": "Brutal",
                                                  "description": "Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat, your opponent can only parry with critical hits."
                                              }
                                          ],
                                          "criticalHitSpecialRules": [
                                              {
                                                  "name": "Reap 2",
                                                  "description": "Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat, if you strike with a critical hit, inflict x mortal wounds on each other enemy operative Visible to the friendly operative and within 1' of it or the target operative, x is the number after the weapon's Reap (e.g. Reap 1)."
                                              }
                                          ],
                                          "id": "571b1a77-9ed4-4c6f-89a6-d2150f7d67d2"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Autopistol",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  }
                                              ],
                                              "id": "75d0116a-0c8b-435d-8b61-e9a2ef6184fe"
                                          },
                                          {
                                              "name": "Penitent eviscerator",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 4,
                                              "damage": 5,
                                              "criticalDamage": 6,
                                              "specialRules": [
                                                  {
                                                      "name": "Brutal",
                                                      "description": "Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat, your opponent can only parry with critical hits."
                                                  }
                                              ],
                                              "criticalHitSpecialRules": [
                                                  {
                                                      "name": "Reap 2",
                                                      "description": "Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat, if you strike with a critical hit, inflict x mortal wounds on each other enemy operative Visible to the friendly operative and within 1' of it or the target operative, x is the number after the weapon's Reap (e.g. Reap 1)."
                                                  }
                                              ],
                                              "id": "571b1a77-9ed4-4c6f-89a6-d2150f7d67d2"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "NOVITIATE PURGATUS",
                                      "description": "Novitiates Purgatus have the holy duty of bringing cleansing flame to the enemies of Mankind. When they unleash their Ministorum flamers at the enemy, they leave little more than ash behind.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 4,
                                      "wounds": 7,
                                      "selectedRangeWeapon": {
                                          "name": "Ministorum flamer",
                                          "type": "range",
                                          "attacks": 5,
                                          "ballisticWeaponSkill": 2,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "specialRules": [
                                              {
                                                  "name": "Rng 6'",
                                                  "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                              },
                                              {
                                                  "name": "Torrent 2'",
                                                  "description": "Each time a friendly operative performs a Shoot action or Overwatch action and selects this weapon, after making the shooting attack against the target, it can make a shooting attack with this weapon against each other valid target within x of the original target and each other, x is the distance after the weapon's Torrent, e.g. Torrent 2'."
                                              }
                                          ],
                                          "criticalHitSpecialRules": [
                                              {
                                                  "name": "Inferno 1",
                                                  "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, if you retain any critical hits, the target gains x Inferno tokens. X is the number after the weapon's Inferno (e.g Inferno 1) At the end of each Turning Point, roll one D6 for each Inferno token an enemy operative has: on a 4+, that enemy operative suffers 1 mortal wound. After rolling, remove all Inferno tokens that operative has."
                                              }
                                          ],
                                          "id": "6a6ac246-34e8-43b8-8a08-e16c41f8b89f"
                                      },
                                      "keyWords": [
                                          "NOVITIATE",
                                          "IMPERIUM",
                                          "ADEPTA SORORITAS",
                                          "<ORDER>",
                                          "NOVITIATE",
                                          "PURGATUS"
                                      ],
                                      "uniqueActions": [
                                          {
                                              "name": "Burning Advance",
                                              "cost": 1,
                                              "description": "Perform a free Dash action with this operative, then perform a free Shoot action with it. You can only select a ministorum flamer for this action's shooting attack.",
                                              "id": "cf2caa27-1ada-4047-a83f-1a82b5f50fa8"
                                          }
                                      ],
                                      "id": "df2e2de8-79e8-4ddf-a72c-61085393f29f",
                                      "currentWounds": 7,
                                      "unitType": [
                                          "staunch",
                                          "marksman"
                                      ],
                                      "unitImageName": "NOVITIATE_PURGATUS",
                                      "selectedMeleeWeapon": {
                                          "name": "Gun butt",
                                          "type": "close",
                                          "attacks": 3,
                                          "ballisticWeaponSkill": 4,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "id": "fe597b03-6534-4919-b4d6-eca80d2b0120"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Ministorum flamer",
                                              "type": "range",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  },
                                                  {
                                                      "name": "Torrent 2'",
                                                      "description": "Each time a friendly operative performs a Shoot action or Overwatch action and selects this weapon, after making the shooting attack against the target, it can make a shooting attack with this weapon against each other valid target within x of the original target and each other, x is the distance after the weapon's Torrent, e.g. Torrent 2'."
                                                  }
                                              ],
                                              "criticalHitSpecialRules": [
                                                  {
                                                      "name": "Inferno 1",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, if you retain any critical hits, the target gains x Inferno tokens. X is the number after the weapon's Inferno (e.g Inferno 1) At the end of each Turning Point, roll one D6 for each Inferno token an enemy operative has: on a 4+, that enemy operative suffers 1 mortal wound. After rolling, remove all Inferno tokens that operative has."
                                                  }
                                              ],
                                              "id": "6a6ac246-34e8-43b8-8a08-e16c41f8b89f"
                                          },
                                          {
                                              "name": "Gun butt",
                                              "type": "close",
                                              "attacks": 3,
                                              "ballisticWeaponSkill": 4,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "id": "fe597b03-6534-4919-b4d6-eca80d2b0120"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "NOVITIATE PRONATUS",
                                      "description": "To serve in the Orders Pronatus a candidate must learn everything there is to know of countless kinds of relics, as well being highly skilled warriors. They are expected to retrieve and guard highly precious - and sometimes extremely dangerous - artefacts.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 4,
                                      "wounds": 7,
                                      "selectedRangeWeapon": {
                                          "name": "Autopistol",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "specialRules": [
                                              {
                                                  "name": "Rng 6'",
                                                  "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                              }
                                          ],
                                          "id": "81c636f1-3fba-4cb1-9c2a-fb938ac40f5c"
                                      },
                                      "keyWords": [
                                          "NOVITIATE",
                                          "IMPERIUM",
                                          "ADEPTA SORORITAS",
                                          "<ORDER>",
                                          "NOVITIATE PRONATUS"
                                      ],
                                      "abilities": [
                                          {
                                              "name": "Relic Seeker",
                                              "description": "Each time this operative is activated, it can perform one mission action at any point during its activation without subtracting any action points.",
                                              "id": "2de9703a-f67e-40e4-aa25-95a7cf362e79"
                                          }
                                      ],
                                      "uniqueActions": [
                                          {
                                              "name": "Raise Relic",
                                              "cost": 1,
                                              "description": "You gain 2 Faith points. If this operative performs this action while within 2' of the centre of an objective marker, or while within 6' of the enemy drop zone, you gain 3 Faith points instead.",
                                              "id": "1d1158bd-a155-44df-b8c1-4a51aa1ffe30"
                                          }
                                      ],
                                      "id": "29d825d3-ed3a-4a93-bad0-748b2a761cc8",
                                      "currentWounds": 7,
                                      "unitType": [
                                          "staunch",
                                          "scout"
                                      ],
                                      "unitImageName": "NOVITIATE_PRONATUS",
                                      "selectedMeleeWeapon": {
                                          "name": "Gun butt",
                                          "type": "close",
                                          "attacks": 3,
                                          "ballisticWeaponSkill": 4,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "id": "c34cb7e0-b9cd-4508-8899-2b7898d0ecce"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Autopistol",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  }
                                              ],
                                              "id": "81c636f1-3fba-4cb1-9c2a-fb938ac40f5c"
                                          },
                                          {
                                              "name": "Gun butt",
                                              "type": "close",
                                              "attacks": 3,
                                              "ballisticWeaponSkill": 4,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "id": "c34cb7e0-b9cd-4508-8899-2b7898d0ecce"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "NOVITIATE EXACTOR",
                                      "description": "Some zealous Novitiates are given neural whips with which to inflict physical and mental anguish on the foe. On occasion, the bearer will turn these weapons on fellow Novitiates they feel are lacking in commitment and need to be dissuaded from lapses in bravery and faith.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 4,
                                      "wounds": 7,
                                      "selectedRangeWeapon": {
                                          "name": "Neural whips",
                                          "type": "range",
                                          "attacks": 5,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "specialRules": [
                                              {
                                                  "name": "Rng 3'",
                                                  "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                              },
                                              {
                                                  "name": "Lethal 5+",
                                                  "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, your attack dice results of equal to or greater than x that are successful hits are critical hits, x is the number after the weapon's Lethal, e.g. Lethal 5+."
                                              }
                                          ],
                                          "criticalHitSpecialRules": [
                                              {
                                                  "name": "Stun",
                                                  "description": "ach time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, if you retain any critical hits, subtract 1 from the target's APL. Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat:",
                                                  "subText": [
                                                      "The first time you strike with a critical hit, select one of your opponent's normal hits from that combat to be discarded",
                                                      "The second time you strike with a critical hit, subtract 1 from the target's APL."
                                                  ]
                                              }
                                          ],
                                          "id": "3f2d4450-46b2-49dd-ab1e-82dd6d2a4060"
                                      },
                                      "keyWords": [
                                          "NOVITIATE",
                                          "IMPERIUM",
                                          "ADEPTA SORORITAS",
                                          "<ORDER>",
                                          "NOVITIATE EXACTOR"
                                      ],
                                      "uniqueActions": [
                                          {
                                              "name": "Whip into Frenzy",
                                              "cost": 1,
                                              "description": "Select one friendly NOVITIATE operative (excluding a NOVITIATE SUPERIOR operative) within 3' of and Visible to this operative. Add 1 to that operative's APL. In addition, if that operative is a NOVITIATE PENITENT operative, until the end of that operative's next activation, add 2' to its Movement characteristic.",
                                              "id": "a0d5f136-cff6-4717-9828-b3d99f30accc"
                                          }
                                      ],
                                      "id": "842d8120-6686-4580-bff7-b2ff7dfc21d8",
                                      "currentWounds": 7,
                                      "unitType": [
                                          "staunch",
                                          "scout"
                                      ],
                                      "unitImageName": "NOVITIATE_EXACTOR",
                                      "selectedMeleeWeapon": {
                                          "name": "Neural whips",
                                          "type": "close",
                                          "attacks": 5,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "specialRules": [
                                              {
                                                  "name": "Lethal 5+",
                                                  "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, your attack dice results of equal to or greater than x that are successful hits are critical hits, x is the number after the weapon's Lethal, e.g. Lethal 5+."
                                              }
                                          ],
                                          "criticalHitSpecialRules": [
                                              {
                                                  "name": "Stun",
                                                  "description": "ach time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, if you retain any critical hits, subtract 1 from the target's APL. Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat:",
                                                  "subText": [
                                                      "The first time you strike with a critical hit, select one of your opponent's normal hits from that combat to be discarded",
                                                      "The second time you strike with a critical hit, subtract 1 from the target's APL."
                                                  ]
                                              }
                                          ],
                                          "id": "87bf43e7-a544-4036-9675-3489c94b7318"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Neural whips",
                                              "type": "range",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 3'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  },
                                                  {
                                                      "name": "Lethal 5+",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, your attack dice results of equal to or greater than x that are successful hits are critical hits, x is the number after the weapon's Lethal, e.g. Lethal 5+."
                                                  }
                                              ],
                                              "criticalHitSpecialRules": [
                                                  {
                                                      "name": "Stun",
                                                      "description": "ach time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, if you retain any critical hits, subtract 1 from the target's APL. Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat:",
                                                      "subText": [
                                                          "The first time you strike with a critical hit, select one of your opponent's normal hits from that combat to be discarded",
                                                          "The second time you strike with a critical hit, subtract 1 from the target's APL."
                                                      ]
                                                  }
                                              ],
                                              "id": "3f2d4450-46b2-49dd-ab1e-82dd6d2a4060"
                                          },
                                          {
                                              "name": "Neural whips",
                                              "type": "close",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "specialRules": [
                                                  {
                                                      "name": "Lethal 5+",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, your attack dice results of equal to or greater than x that are successful hits are critical hits, x is the number after the weapon's Lethal, e.g. Lethal 5+."
                                                  }
                                              ],
                                              "criticalHitSpecialRules": [
                                                  {
                                                      "name": "Stun",
                                                      "description": "ach time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, if you retain any critical hits, subtract 1 from the target's APL. Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat:",
                                                      "subText": [
                                                          "The first time you strike with a critical hit, select one of your opponent's normal hits from that combat to be discarded",
                                                          "The second time you strike with a critical hit, subtract 1 from the target's APL."
                                                      ]
                                                  }
                                              ],
                                              "id": "87bf43e7-a544-4036-9675-3489c94b7318"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "NOVITIATE RELIQUARIUS",
                                      "description": "Some Novitiates bear inspiring icons, such as an ornate tapestry, a reliquary containing a saint's mortal remains or a simulacrum of a famous artefact. Novitiates who look upon such relics know the holy power of the God-Emperor is with them, and fight all the harder for it.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 4,
                                      "wounds": 8,
                                      "selectedRangeWeapon": {
                                          "name": "Autopistol",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "specialRules": [
                                              {
                                                  "name": "Rng 6'",
                                                  "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                              }
                                          ],
                                          "id": "e4d6a4a5-728f-4862-9149-fc3f6df15669"
                                      },
                                      "keyWords": [
                                          "NOVITIATE",
                                          "IMPERIUM",
                                          "ADEPTA SORORITAS",
                                          "<ORDER>",
                                          "NOVITIATE RELIQUARIUS"
                                      ],
                                      "abilities": [
                                          {
                                              "name": "Icon of Purity",
                                              "description": "Each time a friendly NOVITIATE operative is incapacitated, if this operative is within 6' of and Visible to it, roll one D6: on a 4+, that operative can immediately perform a free Shoot action.",
                                              "id": "136d31fe-6568-42d4-b605-7b00c3caa90d"
                                          }
                                      ],
                                      "uniqueActions": [
                                          {
                                              "name": "Plant the Icon",
                                              "cost": 1,
                                              "description": "Perform this action while within 2' of the centre of an objective marker. Until this operative is next activated, if it is within 2' of the centre of that objective marker, halve the APL of enemy operatives (rounding up) when determining who controls that objective marker.",
                                              "id": "268faed7-6dba-4e5a-ad9b-af354e5f4ff2"
                                          }
                                      ],
                                      "id": "2cbca318-0757-4697-85d2-b0751132d989",
                                      "currentWounds": 8,
                                      "unitType": [
                                          "staunch",
                                          "scout"
                                      ],
                                      "unitImageName": "NOVITIATE_RELIQUARIUS",
                                      "selectedMeleeWeapon": {
                                          "name": "Gun butt",
                                          "type": "close",
                                          "attacks": 3,
                                          "ballisticWeaponSkill": 4,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "id": "bc689e80-dc9c-4b4b-a269-9d9242385273"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Autopistol",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  }
                                              ],
                                              "id": "e4d6a4a5-728f-4862-9149-fc3f6df15669"
                                          },
                                          {
                                              "name": "Gun butt",
                                              "type": "close",
                                              "attacks": 3,
                                              "ballisticWeaponSkill": 4,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "id": "bc689e80-dc9c-4b4b-a269-9d9242385273"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "NOVITIATE HOSPITALLER",
                                      "description": "Curing the physical and spiritual ills of the Imperium's battlefield wounded is an onerous task, one that requires countless hours of practice. Novitiates seeking admission into the Orders Hospitaller hone their skills in the field, aiding their fellow Novitiates In whichever way they can.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 4,
                                      "wounds": 7,
                                      "selectedRangeWeapon": {
                                          "name": "Autopistol",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "specialRules": [
                                              {
                                                  "name": "Rng 6'",
                                                  "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                              }
                                          ],
                                          "id": "a60439a7-297e-4783-8e8d-a37ab8e60184"
                                      },
                                      "keyWords": [
                                          "NOVITIATE",
                                          "IMPERIUM",
                                          "ADEPTA SORORITAS",
                                          "<ORDER>",
                                          "MEDIC",
                                          "NOVITIATE HOSPITALLER"
                                      ],
                                      "abilities": [
                                          {
                                              "name": "Mantra of Restoration",
                                              "description": "Once per Turning Point, the first time another friendly NOVITIATE operative would be incapacitated while Visible to and within 3' of this operative and not within Engagement Range of an enemy operative, if this operative is not within Engagement Range of an enemy operative, this operative can revive it. That operative is not incapacitated, has 1 wound remaining, and if it would have been incapacitated by a shooting attack, any remaining attack dice are discarded. That other friendly operative can then perform a free Dash action, but must finish that move within 1' of this operative. Subtract 1 from both operatives' APL.",
                                              "id": "59b8ca3f-864d-401e-a6c0-3cfab41ad4b6"
                                          }
                                      ],
                                      "uniqueActions": [
                                          {
                                              "name": "Chirurgeon's Tools",
                                              "cost": 1,
                                              "description": "Select one friendly NOVITIATE operative within 1' of and Visible to this operative. That operative regains 2D3 lost wounds. An operative cannot be selected for this if it was revived using the Mantra of Restoration ability during the same Turning Point. This operative cannot perform this action if it is within Engagement Range of an enemy operative.",
                                              "id": "eab3a555-3eec-454a-a674-7f458de86c7a"
                                          }
                                      ],
                                      "id": "275533f3-16ce-40e1-acf0-69026d6bf943",
                                      "currentWounds": 7,
                                      "unitType": [
                                          "staunch",
                                          "scout"
                                      ],
                                      "unitImageName": "NOVITIATE_HOSPITALLER",
                                      "selectedMeleeWeapon": {
                                          "name": "Surgical saw",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 4,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "specialRules": [
                                              {
                                                  "name": "Lethal 5+",
                                                  "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, your attack dice results of equal to or greater than x that are successful hits are critical hits, x is the number after the weapon's Lethal, e.g. Lethal 5+."
                                              }
                                          ],
                                          "id": "280f11bc-f723-45f7-9edf-7375d4515e35"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Autopistol",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  }
                                              ],
                                              "id": "a60439a7-297e-4783-8e8d-a37ab8e60184"
                                          },
                                          {
                                              "name": "Surgical saw",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 4,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "specialRules": [
                                                  {
                                                      "name": "Lethal 5+",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, your attack dice results of equal to or greater than x that are successful hits are critical hits, x is the number after the weapon's Lethal, e.g. Lethal 5+."
                                                  }
                                              ],
                                              "id": "280f11bc-f723-45f7-9edf-7375d4515e35"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "NOVITIATE PRECEPTOR",
                                      "description": "These Novitiates are expected to rigidly enforce their Order's precepts, ensuring their fellows faithfully adhere to the prescribed standards. They are often chosen as their Superior's second in command, and serve as exemplars by eagerly striding into the fray to smite the Emperor's foes.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 4,
                                      "wounds": 7,
                                      "keyWords": [
                                          "NOVITIATE",
                                          "IMPERIUM",
                                          "ADEPTA SORORITAS",
                                          "<ORDER>",
                                          "NOVITIATE PRECEPTOR"
                                      ],
                                      "abilities": [
                                          {
                                              "name": "Unflinching Determination",
                                              "description": "Each time a shooting attack is made against a friendly NOVITIATE operative, in the Roll Defence Dice step of that shooting attack, if this operative is Visible to and within 3' of it, you can re-roll one of your defence dice.",
                                              "id": "1ac3d89a-9dc0-4959-b723-9167f8639914"
                                          }
                                      ],
                                      "uniqueActions": [
                                          {
                                              "name": "Glorious Hymnal",
                                              "cost": 1,
                                              "description": "Until the end of the Turning Point, each time a friendly NOVITIATE operative fights in combat, in the Roll Attack Dice step of that combat, if it is within 6' of this operative, you can re-roll any or all of your attack dice results of 1 or 2.",
                                              "id": "1a72a9fa-d791-462c-97f0-4580cbfcdc63"
                                          }
                                      ],
                                      "id": "9dbe8b50-b407-44a1-b6bf-1dbc2a4ddbb8",
                                      "currentWounds": 7,
                                      "unitType": [
                                          "combat",
                                          "staunch"
                                      ],
                                      "unitImageName": "NOVITIATE_PRECEPTOR",
                                      "selectedMeleeWeapon": {
                                          "name": "Mace of the Righteous",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 5,
                                          "criticalDamage": 5,
                                          "criticalHitSpecialRules": [
                                              {
                                                  "name": "Stun",
                                                  "description": "Each time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, if you retain any critical hits, subtract 1 from the target's APL. Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat:",
                                                  "subText": [
                                                      "The first time you strike with a critical hit, select one of your opponent's normal hits from that combat to be discarded",
                                                      "The second time you strike with a critical hit, subtract 1 from the target's APL."
                                                  ]
                                              },
                                              {
                                                  "name": "Inferno 2",
                                                  "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, if you retain any critical hits, the target gains x Inferno tokens. X is the number after the weapon's Inferno (e.g Inferno 1) At the end of each Turning Point, roll one D6 for each Inferno token an enemy operative has: on a 4+, that enemy operative suffers 1 mortal wound. After rolling, remove all Inferno tokens that operative has."
                                              }
                                          ],
                                          "id": "59aad2e7-ea9f-448c-8fbd-695a04ecb5bf"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Mace of the Righteous",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 5,
                                              "criticalDamage": 5,
                                              "criticalHitSpecialRules": [
                                                  {
                                                      "name": "Stun",
                                                      "description": "Each time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, if you retain any critical hits, subtract 1 from the target's APL. Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat:",
                                                      "subText": [
                                                          "The first time you strike with a critical hit, select one of your opponent's normal hits from that combat to be discarded",
                                                          "The second time you strike with a critical hit, subtract 1 from the target's APL."
                                                      ]
                                                  },
                                                  {
                                                      "name": "Inferno 2",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, if you retain any critical hits, the target gains x Inferno tokens. X is the number after the weapon's Inferno (e.g Inferno 1) At the end of each Turning Point, roll one D6 for each Inferno token an enemy operative has: on a 4+, that enemy operative suffers 1 mortal wound. After rolling, remove all Inferno tokens that operative has."
                                                  }
                                              ],
                                              "id": "59aad2e7-ea9f-448c-8fbd-695a04ecb5bf"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "NOVITIATE DIALOGUS",
                                      "description": "These Novitiates are in training to join the Orders Dialogus, while learning warfare under the guidance of experienced Battle Sisters. Their training in cryptography makes them of great value to kill teams - they can unlock obscure ciphers and find hidden patterns in communiques.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 4,
                                      "wounds": 7,
                                      "selectedRangeWeapon": {
                                          "name": "Autopistol",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "specialRules": [
                                              {
                                                  "name": "Rng 6'",
                                                  "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                              }
                                          ],
                                          "id": "6fb1276e-e17e-4184-aa38-9f73e33a5db3"
                                      },
                                      "keyWords": [
                                          "NOVITIATE",
                                          "IMPERIUM",
                                          "ADEPTA SORORITAS",
                                          "<ORDER>",
                                          "NOVITIATE DIALOGUS"
                                      ],
                                      "uniqueActions": [
                                          {
                                              "name": "Stirring Rhetoric",
                                              "cost": 1,
                                              "description": "Select one friendly NOVITIATE operative within 6' of and Visible to this operative. Add 1 to its APL.",
                                              "id": "82c40760-aff4-4d25-940d-ed5c62ac3323"
                                          },
                                          {
                                              "name": "Auto-Broadcaster",
                                              "cost": 1,
                                              "description": "Place a Condemning Sermon token within 6' of this operative. While an enemy operative is within  of the centre of your Condemning Sermon token, worsen the Ballistic Skill characteristic of ranged weapons it is equipped with by 1. In the Ready Operatives step of each Turning Point, remove your Condemning Sermon token.",
                                              "id": "f9ca9382-619a-469b-93c1-2616b33254bd"
                                          }
                                      ],
                                      "id": "361b2b1b-9620-436f-94cb-595f6710823c",
                                      "currentWounds": 7,
                                      "unitType": [
                                          "scout"
                                      ],
                                      "unitImageName": "NOVITIATE_DIALOGUS",
                                      "selectedMeleeWeapon": {
                                          "name": "Dialogus stave",
                                          "type": "close",
                                          "attacks": 3,
                                          "ballisticWeaponSkill": 4,
                                          "damage": 3,
                                          "criticalDamage": 3,
                                          "id": "e01c6cd3-1a5b-445b-b9c8-64c00f3e2164"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Autopistol",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  }
                                              ],
                                              "id": "6fb1276e-e17e-4184-aa38-9f73e33a5db3"
                                          },
                                          {
                                              "name": "Dialogus stave",
                                              "type": "close",
                                              "attacks": 3,
                                              "ballisticWeaponSkill": 4,
                                              "damage": 3,
                                              "criticalDamage": 3,
                                              "id": "e01c6cd3-1a5b-445b-b9c8-64c00f3e2164"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "NOVITIATE DUELLIST",
                                      "description": "Some Novitiates have a natural affinity for bladework, their reaction times and dexterity greater than that of their fellow warriors. They take to the field eager to prove their skill, wishing to claim the heads of the most capable enemies and emulate the warrior-saints of legend.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 4,
                                      "wounds": 7,
                                      "selectedRangeWeapon": {
                                          "name": "Autopistol",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "specialRules": [
                                              {
                                                  "name": "Rng 6'",
                                                  "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                              }
                                          ],
                                          "id": "494ea392-833f-4957-84d0-4d2e5d5c55d1"
                                      },
                                      "keyWords": [
                                          "NOVITIATE",
                                          "IMPERIUM",
                                          "ADEPTA SORORITAS",
                                          "<ORDER>",
                                          "NOVITIATE DUELLIST"
                                      ],
                                      "id": "cb7ada1f-1a49-4a69-bfe9-2cea996ec900",
                                      "currentWounds": 7,
                                      "unitType": [
                                          "combat",
                                          "scout"
                                      ],
                                      "unitImageName": "NOVITIATE_DUELLIST",
                                      "selectedMeleeWeapon": {
                                          "name": "Duelling blades",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 2,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "specialRules": [
                                              {
                                                  "name": "Lethal 5+",
                                                  "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, your attack dice results of equal to or greater than x that are successful hits are critical hits, x is the number after the weapon's Lethal, e.g. Lethal 5+."
                                              }
                                          ],
                                          "criticalHitSpecialRules": [
                                              {
                                                  "name": "Expert Riposte",
                                                  "description": "Each time this operative fights in combat using its duelling blades, in the Resolve Successful Hits step of that combat, each time you parry with a critical hit, also inflict damage equal to the weapon's Critical Damage characteristic"
                                              }
                                          ],
                                          "id": "f57f8e53-c3b9-428b-8b01-e18da50ed55f"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Autopistol",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  }
                                              ],
                                              "id": "494ea392-833f-4957-84d0-4d2e5d5c55d1"
                                          },
                                          {
                                              "name": "Duelling blades",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "specialRules": [
                                                  {
                                                      "name": "Lethal 5+",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, your attack dice results of equal to or greater than x that are successful hits are critical hits, x is the number after the weapon's Lethal, e.g. Lethal 5+."
                                                  }
                                              ],
                                              "criticalHitSpecialRules": [
                                                  {
                                                      "name": "Expert Riposte",
                                                      "description": "Each time this operative fights in combat using its duelling blades, in the Resolve Successful Hits step of that combat, each time you parry with a critical hit, also inflict damage equal to the weapon's Critical Damage characteristic"
                                                  }
                                              ],
                                              "id": "f57f8e53-c3b9-428b-8b01-e18da50ed55f"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "NOVITIATE CONDEMNOR",
                                      "description": "Sorcerers and witches are among the Adepta Sororitas' most hated foes. Such psychic abominations are terrible threats to the Emperor's realm, and cutting them down is a key priority. Novitiates Condemnor are especially equipped to deal with this kind of enemy.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 4,
                                      "wounds": 7,
                                      "selectedRangeWeapon": {
                                          "name": "Condemnor stakethrower",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "specialRules": [
                                              {
                                                  "name": "Silent",
                                                  "description": "While an operative has a Conceal order, it can perform Shoot actions if this is the ranged weapon (or weapon profile) that is selected for the shooting attack(s)."
                                              }
                                          ],
                                          "criticalHitSpecialRules": [
                                              {
                                                  "name": "P1",
                                                  "description": "Piercing. Each time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, if you retain any critical hits, the weapon gains the APx special rule for that shooting attack, x is the number after the weapon's P, e.g. P1."
                                              },
                                              {
                                                  "name": "MW2",
                                                  "description": "Mortal wounds. Each time a friendly operative makes a shooting attack with this weapon, in the roll attack dice step of that shooting attack, for each critical hit retained, inflict x mortal wounds on the target, x is the number after the weapon's MW, e.g. MW3."
                                              }
                                          ],
                                          "id": "6c020f39-003c-4809-b00b-c464c6138d4a"
                                      },
                                      "keyWords": [
                                          "NOVITIATE",
                                          "IMPERIUM",
                                          "ADEPTA SORORITAS",
                                          "<ORDER>",
                                          "NOVITIATE CONDEMNOR"
                                      ],
                                      "abilities": [
                                          {
                                              "name": "Null Rod",
                                              "description": "Each time an enemy operative is activated, if it is within 6' of this operative for that activation, its APL characteristic cannot be higher than 2. Each time an enemy operative would perform a psychic action, if it is within 6' of this operative, 1 additional AP must be subtracted to perform that action.",
                                              "id": "f8adbac4-01b5-49e9-9e1c-d9a892eaacf2"
                                          }
                                      ],
                                      "id": "b01acc14-6353-41e8-9272-82919685ffc7",
                                      "currentWounds": 7,
                                      "unitType": [
                                          "combat",
                                          "marksman"
                                      ],
                                      "unitImageName": "NOVITIATE_CONDEMNOR",
                                      "selectedMeleeWeapon": {
                                          "name": "Null rod",
                                          "type": "close",
                                          "attacks": 3,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 3,
                                          "criticalHitSpecialRules": [
                                              {
                                                  "name": "Stun",
                                                  "description": "Each time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, if you retain any critical hits, subtract 1 from the target's APL. Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat:",
                                                  "subText": [
                                                      "The first time you strike with a critical hit, select one of your opponent's normal hits from that combat to be discarded",
                                                      "The second time you strike with a critical hit, subtract 1 from the target's APL."
                                                  ]
                                              }
                                          ],
                                          "id": "15f9a795-aad8-4326-a4d9-057383a4a0f5"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Condemnor stakethrower",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "specialRules": [
                                                  {
                                                      "name": "Silent",
                                                      "description": "While an operative has a Conceal order, it can perform Shoot actions if this is the ranged weapon (or weapon profile) that is selected for the shooting attack(s)."
                                                  }
                                              ],
                                              "criticalHitSpecialRules": [
                                                  {
                                                      "name": "P1",
                                                      "description": "Piercing. Each time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, if you retain any critical hits, the weapon gains the APx special rule for that shooting attack, x is the number after the weapon's P, e.g. P1."
                                                  },
                                                  {
                                                      "name": "MW2",
                                                      "description": "Mortal wounds. Each time a friendly operative makes a shooting attack with this weapon, in the roll attack dice step of that shooting attack, for each critical hit retained, inflict x mortal wounds on the target, x is the number after the weapon's MW, e.g. MW3."
                                                  }
                                              ],
                                              "id": "6c020f39-003c-4809-b00b-c464c6138d4a"
                                          },
                                          {
                                              "name": "Null rod",
                                              "type": "close",
                                              "attacks": 3,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 3,
                                              "criticalHitSpecialRules": [
                                                  {
                                                      "name": "Stun",
                                                      "description": "Each time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, if you retain any critical hits, subtract 1 from the target's APL. Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat:",
                                                      "subText": [
                                                          "The first time you strike with a critical hit, select one of your opponent's normal hits from that combat to be discarded",
                                                          "The second time you strike with a critical hit, subtract 1 from the target's APL."
                                                      ]
                                                  }
                                              ],
                                              "id": "15f9a795-aad8-4326-a4d9-057383a4a0f5"
                                          }
                                      ]
                                  }
                              ],
                              "currentDataslates": [],
                              "id": "b89a6691-4a11-4b1a-9a30-ca8d9e922c5c",
                              "defaultDataslates": {
                                  "74f0264f-62d5-46d8-8dde-338beb18ab65": 1,
                                  "94292b9e-cea2-4e59-a55b-4cbceff2dc63": 9
                              }
                          }
                      ],
                      "chosenFireTeams": [],
                      "ploys": [
                          {
                              "name": "EYES OF THE EMPEROR",
                              "description": "Until the end of the Turning Point, remove the Range special rule from autopistols, bolt pistols and plasma pistols that friendly NOVITIATE operatives are equipped with.",
                              "cost": 1,
                              "type": "strategic",
                              "id": "d3ddd4f6-3056-4de0-a7b9-684134031ac6"
                          },
                          {
                              "name": "SANCTIFIED ROUNDS",
                              "description": "Until the end of the Turning Point, add 1 to both Damage characteristics of autoguns and autopistols that friendly NOVITIATE operatives are equipped with.",
                              "cost": 1,
                              "type": "strategic",
                              "id": "54f98f94-39a2-4739-b02a-46b2dbad39ca"
                          },
                          {
                              "name": "AEGIS OF THE EMPEROR",
                              "description": "Until the end of the Turning Point, each time an enemy operative within 6' of a friendly NOVITIATE operative performs a psychic action, that enemy operative suffers 1 mortal wound.",
                              "cost": 1,
                              "type": "strategic",
                              "id": "5fee5fd8-76b1-4ae1-87fe-4bb2d4e25f34"
                          },
                          {
                              "name": "DEFENDERS OF THE FAITH",
                              "description": "At the start of the Firefight phase, one friendly NOVITIATE operative that is within 2' of the centre of each objective marker can:",
                              "cost": 1,
                              "type": "strategic",
                              "id": "4c4dec0b-b86d-4141-8ea3-bb897ac9fea6",
                              "subTexts": [
                                  "Perform a free Shoot action if it has an Engage order.",
                                  "Perform a free Fight action if it has an Engage order."
                              ]
                          },
                          {
                              "name": "GLORIOUS MARTYRDOM",
                              "description": "Use this Tactical Ploy when a friendly NOVITIATE operative is incapacitated. Each enemy operative within 2' of and Visible to that operative suffer 1 mortal wound, and you gain D3 Faith points.",
                              "cost": 1,
                              "type": "tactical",
                              "id": "f4110f96-5816-4cc9-994d-42123a7a2332"
                          },
                          {
                              "name": "BURNING WRATH",
                              "description": "Select one friendly NOVITIATE PURGATUS operative. Until the end of the turning point, that operative's Ministorum flamer has the following profile:",
                              "cost": 1,
                              "type": "tactical",
                              "id": "2f4ac113-b680-4835-a8a4-ed9d7ff5534d",
                              "weapon": {
                                  "name": "Ministorum flamer",
                                  "type": "range",
                                  "attacks": 5,
                                  "ballisticWeaponSkill": 2,
                                  "damage": 3,
                                  "criticalDamage": 4,
                                  "specialRules": [
                                      {
                                          "name": "Rng 6'",
                                          "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                      },
                                      {
                                          "name": "Torrent 2'",
                                          "description": "Each time a friendly operative performs a Shoot action or Overwatch action and selects this weapon, after making the shooting attack against the target, it can make a shooting attack with this weapon against each other valid target within x of the original target and each other, x is the distance after the weapon's Torrent, e.g. Torrent 2'."
                                      }
                                  ],
                                  "criticalHitSpecialRules": [
                                      {
                                          "name": "Inferno 2",
                                          "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, if you retain any critical hits, the target gains x Inferno tokens. X is the number after the weapon's Inferno (e.g Inferno 1) At the end of each Turning Point, roll one D6 for each Inferno token an enemy operative has: on a 4+, that enemy operative suffers 1 mortal wound. After rolling, remove all Inferno tokens that operative has."
                                      }
                                  ],
                                  "id": "353147de-84fc-4f2c-bbb2-ebebef467fbe"
                              }
                          },
                          {
                              "name": "RIGHTEOUS CONDEMNATION",
                              "description": "Use this Tactical Ploy after rolling your attack dice for a shooting attack made by a friendly NOVITIATE CONDEMNOR operative. You can re-roll any or all of your attack dice for that shooting attack.",
                              "cost": 1,
                              "type": "tactical",
                              "id": "d0c839fa-742f-4aad-97e6-a46801a2afa8"
                          }
                      ],
                      "equipments": [
                          {
                              "name": "ICON OF FAITH",
                              "description": "The operative gains the following ability for the battle:",
                              "cost": 2,
                              "maxNumberPerKillTeam": 1,
                              "id": "20fc2c39-7dcf-4f51-8d85-a7e5ef274ffd",
                              "ability": {
                                  "name": "Icon of Faith",
                                  "description": "Once per battle, this operative can perform one Act of Faith without you needing to subtract Faith points.",
                                  "id": "1ebffab9-60f7-4b68-ab6c-f13995e9c122"
                              }
                          },
                          {
                              "name": "CHAPLET ECCLESIATICUS",
                              "description": "This operative gains the following ability for the battle:",
                              "cost": 2,
                              "id": "9ad7a48c-d23c-4b55-8865-0b989e6fd593",
                              "ability": {
                                  "name": "Chaplet Ecclesiaticus",
                                  "description": "Each time this operative is activated, roll one D6: on a 6, add 1 to its APL.",
                                  "id": "eebc5116-553a-4e79-8134-ec8d9b1eb901"
                              }
                          },
                          {
                              "name": "ADAMANTIUM-WEAVE SURPLICE",
                              "description": "This operative gains the following ability for the battle:",
                              "cost": 2,
                              "id": "1b349819-96c3-4005-a1d2-f8677a35d7a0",
                              "ability": {
                                  "name": "Adamantium-weave Surplice",
                                  "description": "This model has a Save characteristic of 3+ instead of 4+.",
                                  "id": "0a67c038-39df-4ac5-815d-28885aec8cfc"
                              }
                          },
                          {
                              "name": "FRAG GRENADE",
                              "description": "The operative is equipped with the following ranged weapon for the battle:",
                              "cost": 2,
                              "id": "1fcbef23-47ba-4865-9023-d8c8fac260b5",
                              "weapon": {
                                  "name": "Frag grenade",
                                  "type": "range",
                                  "attacks": 4,
                                  "ballisticWeaponSkill": 3,
                                  "damage": 2,
                                  "criticalDamage": 3,
                                  "specialRules": [
                                      {
                                          "name": "Rng 6'",
                                          "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                      },
                                      {
                                          "name": "Limited",
                                          "description": "This weapon can only be selected for use once per battle. If the weapon has a special rule that would allow it to make more than one shooting attack for an action (e.g. Blast), make each of those attacks as normal."
                                      },
                                      {
                                          "name": "Blast 2'",
                                          "description": "Each time a friendly operative performs a Shoot action and selects this weapon (or, in the case of profiles, this weapon's profile), after making the shooting attack against the target, make a shooting attack with this weapon (using the same profile) against each other operative Visible to and within X of the original target - each of them is a valid target and cannot be in Cover. X is the distance after the weapon's Blast, e.g. Blast 2'. An operative cannot make a shooting attack with this weapon by performing an Overwatch action."
                                      },
                                      {
                                          "name": "Indirect",
                                          "description": "Each time a friendly operative makes a shooting attack with this weapon, in the select valid target step of that shooting attack, enemy operatives are not in Cover."
                                      }
                                  ],
                                  "id": "84ab0a9d-e7f3-45af-9f33-81723660224a"
                              }
                          },
                          {
                              "name": "KRAK GRENADE",
                              "description": "The operative is equipped with the following ranged weapon for the battle. It cannot make a shooting attack with this weapon by performing an Overwatch action:",
                              "cost": 3,
                              "id": "92d2efde-46ae-4ab1-bb6d-eee31cf9afc9",
                              "weapon": {
                                  "name": "Krak grenade",
                                  "type": "range",
                                  "attacks": 4,
                                  "ballisticWeaponSkill": 3,
                                  "damage": 4,
                                  "criticalDamage": 5,
                                  "specialRules": [
                                      {
                                          "name": "Rng 6'",
                                          "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                      },
                                      {
                                          "name": "Limited",
                                          "description": "This weapon can only be selected for use once per battle. If the weapon has a special rule that would allow it to make more than one shooting attack for an action (e.g. Blast), make each of those attacks as normal."
                                      },
                                      {
                                          "name": "AP1",
                                          "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                      },
                                      {
                                          "name": "Indirect",
                                          "description": "Each time a friendly operative makes a shooting attack with this weapon, in the select valid target step of that shooting attack, enemy operatives are not in Cover."
                                      }
                                  ],
                                  "id": "50cfff12-dd9c-4d37-a5b8-90d8eb0deeb4"
                              }
                          },
                          {
                              "name": "AUTO-CHASTISER",
                              "description": "This operative gains the following ability for the battle:",
                              "cost": 1,
                              "id": "94caf93a-f897-4c0b-921c-30ef7a18e7e6",
                              "ability": {
                                  "name": "Auto-chastiser",
                                  "description": "Each time this operative fights in combat or makes a shooting attack, in the Roll Attack Dice step of that combat or shooting attack, you can re-roll any or all of your attack dice results of 1. If any of your re-rolled dice also result in 1, that operative suffers 1 mortal wound.",
                                  "id": "b8b99e80-a00a-42cc-870a-add1f25190cb"
                              }
                          },
                          {
                              "name": "SAINTED RELIQUAE",
                              "description": "This operative gains the following ability for the battle:",
                              "cost": 2,
                              "id": "64830d1f-0fa7-47d9-aaa0-9ff85eb2e891",
                              "ability": {
                                  "name": "Sainted Reliquae",
                                  "description": "Each time this operative strikes with a critical hit, you gain 1 Faith point.",
                                  "id": "36715245-34c9-4a58-b719-fbb55a4162e8"
                              }
                          }
                      ],
                      "countEquipmentPoint": 10,
                      "tacOps": [
                          {
                              "name": "PURGE WITH FLAME",
                              "type": "factionTacOp",
                              "description": "You can reveal this Tac Op in the Target Reveal step of any Turning Point.",
                              "firstCondition": "If two or more enemy operatives are incapacitated by attacks made by weapons with the Inferno x critical hit rule, you score 1VP.",
                              "secondCondition": "If an enemy LEADER operative is incapacitated by an attack made by a weapon with the Inferno x critical hit rule, you score 1VP.",
                              "victoryPoint": [
                                  1,
                                  1
                              ],
                              "id": "c9bf6d82-98ed-4554-bb44-e95245cb9490",
                              "isCompletedConditions": []
                          },
                          {
                              "name": "RECONSECRATE GROUND",
                              "type": "factionTacOp",
                              "description": "Reveal this Tac Op in the Target Reveal step of the first Turning Point. Select one objective marker that is not within 6' of your drop zone:",
                              "firstCondition": "At the end of any Turning Point, if friendly operatives control that objective marker, you score 1VP.",
                              "secondCondition": "At the end of the battle, if friendly operatives control that objective marker, you score 1VP.",
                              "victoryPoint": [
                                  1,
                                  1
                              ],
                              "id": "2cac965e-5c19-4d12-b12b-3429313daad3",
                              "isCompletedConditions": []
                          },
                          {
                              "name": "GLORY TO THE MARTYRS",
                              "type": "factionTacOp",
                              "description": "Reveal this Tac Op when a friendly operative is incapacitated while within 2' of the centre of an objective marker.",
                              "firstCondition": "If another friendly operative is incapacitated while within 2' of the centre of that objective marker, you score 1VP.",
                              "secondCondition": "If you achieve the first condition in any subsequent Turning Points, you score 1VP.",
                              "victoryPoint": [
                                  1,
                                  1
                              ],
                              "id": "4f79665a-3138-4681-9e0e-5d6115e10385",
                              "isCompletedConditions": []
                          }
                      ],
                      "abilityOfKillTeam": {
                          "name": "ACTS OF FAITH",
                          "description": "Keep a pool of Faith points. At the start of each Turning Point, if there are any friendly NOVITIATE operatives in the kill zone, you gain 3 Faith points. In addition, you gain one Faith point at the end of an activation, if any of the following apply:",
                          "subText": [
                              "During that activation, a friendly NOVITIATE operative with the Combat specialism incapacitated an enemy operative in a combat.",
                              "During that activation, a friendly NOVITIATE operative with the Marksman specialism incapacitated an enemy operative with a shooting attack.",
                              "During that activation, a friendly NOVITIATE operative with the Staunch specialism performed a mission action.",
                              "A friendly NOVITIATE operative with the Scout specialism was activated and finished that activation within 6' of the enemy drop zone."
                          ],
                          "postText": [
                              "Faith points can be subtracted so that friendly NOVITIATE operatives can perform Acts of Faith listed below. Each Act of Faith will specify when it can be used, its effect and how many Faith points you must subtract from your total to use it. If you cannot subtract the required Faith points from your total, you cannot use that Act of Faith.",
                              "Unless otherwise specified, only one Act of Faith can be used during each activation (friendly or enemy). For example, a shooting attack is made against a friendly NOVITIATE operative. In the Roll Defence Dice step of that shooting attack, that operative's controlling player decides to subtract 2 Faith points to use Divine Shield to retain one failed save as a successful normal save. No other Acts of Faith can then be performed during that activation (other than Faithful Blessing)."
                          ],
                          "actsOfFaith": [
                              {
                                  "name": "Faithful Blessing",
                                  "description": "When a friendly NOVITIATE operative is fighting in combat or making a shooting attack, or a shooting attack is being made against it, in the Roll Attack Dice step (for the former) or the Roll Defence Dice step (for the latter) of that combat or shooting attack, re-roll one of your attack or defence dice. This Act of Faith can be used more than once in each activation, and can be used with other Acts of Faith.",
                                  "cost": 1,
                                  "id": "3e42af60-1f41-4871-a49c-c3a99383f199"
                              },
                              {
                                  "name": "Guiding Light",
                                  "description": "When a friendly NOVITIATE operative fights in combat or makes a shooting attack, in the Roll Attack Dice step of that combat or shooting attack, retain one of your failed hits as a successful normal hit.",
                                  "cost": 2,
                                  "id": "6fe2b540-508f-4368-8adc-6da7d64f827f"
                              },
                              {
                                  "name": "Vengeful Strike",
                                  "description": "When a friendly NOVITIATE operative fights in combat or makes a shooting attack, in the Roll Attack Dice step of that combat or shooting attack, retain one of your successful normal hits as a critical hit instead.",
                                  "cost": 3,
                                  "id": "2797a40d-ed2a-4389-91d6-eb944285edc6"
                              },
                              {
                                  "name": "Divine Shield",
                                  "description": "When a shooting attack is made against a friendly NOVITIATE operative, in the Roll Defence Dice step of that shooting attack, retain one of your failed saves as a successful normal save.",
                                  "cost": 2,
                                  "id": "2bed6427-7262-4908-890e-ce324f8702de"
                              },
                              {
                                  "name": "Armour of Contempt",
                                  "description": "When a shooting attack is made against a friendly NOVITIATE operative, in the Roll Defence Dice step of that shooting attack, retain one of your successful normal saves as a critical save instead.",
                                  "cost": 2,
                                  "id": "255f443f-ac99-4d5a-8023-5041aef72c94"
                              },
                              {
                                  "name": "Emperor's Protection",
                                  "description": "When a friendly NOVITIATE operative suffers a mortal wound, ignore that mortal wound. This Act of Faith can be used more than once in each activation.",
                                  "cost": 1,
                                  "id": "70aa3fca-0526-48cf-a2a9-9287f17cc801"
                              },
                              {
                                  "name": "Blessed Rejuvenation",
                                  "description": "When a friendly NOVITIATE operative is activated, it regains D3 lost wounds. This Act of Faith can be used a maximum of two times in each activation.",
                                  "cost": 2,
                                  "id": "3f267b2d-00df-414a-9b0e-154f8c367c2c"
                              },
                              {
                                  "name": "Blinding Aura",
                                  "description": "When an enemy operative performs a shooting attack, select one friendly NOVITIATE operative. Until the end of that activation, while that friendly operative is more than 2' from that enemy operative:",
                                  "subText": [
                                      "That friendly operative is treated as being in Cover.",
                                      "While that friendly operative has a Conceal order, it is always treated as having a Conceal order, regardless of any other rules (e.g. Vantage Point)."
                                  ],
                                  "cost": 2,
                                  "id": "b61c5e63-6845-4d93-bbd1-12908974b4a6"
                              }
                          ]
                      },
                      "id": "9fdfdb08-79e2-4976-9a7a-1f5781025ee7"
                  }
                  
              ]
          },
          {
              "name": "Chaos",
              "killTeam": [
                  {
                      "factionName": "Chaos Daemons",
                      "killTeamName": "Chaos Daemons",
                      "countOfFireTeam": 2,
                      "factionLogo": "CHAOS_DAEMON",
                      "fireTeams": [
                          {
                              "name": "Bloodletter",
                              "archetype": [
                                  "seekAndDestroy"
                              ],
                              "availableDataslates": [
                                  {
                                      "name": "BLOODLETTER (FIGHTER)",
                                      "description": "Bloodletters are Khorne's most numerous warriors, the foot soldiers of the Blood Legions. Their skin is the colour of spilt gore, and their muscles bulge in response to their rage. They carry jagged hellblades in their taloned hands that glow with the energies of the warp.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 6,
                                      "wounds": 9,
                                      "keyWords": [
                                          "CHAOS DAEMON",
                                          "CHAOS",
                                          "DAEMON",
                                          "KHORNE",
                                          "BLOODLETTER",
                                          "FIGHTER"
                                      ],
                                      "abilities": [
                                          {
                                              "name": "Daemon",
                                              "description": "This operative has a 5+ invulnerable save.",
                                              "id": "9f1ef4b3-0c30-49a4-ad74-a6fc266ef04a"
                                          }
                                      ],
                                      "id": "448def93-f1dc-424e-92d9-765e62805c91",
                                      "currentWounds": 9,
                                      "unitType": [
                                          "combat"
                                      ],
                                      "unitImageName": "BLOODLETTER_FIGHTER",
                                      "selectedMeleeWeapon": {
                                          "name": "Hellblade",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 4,
                                          "criticalDamage": 6,
                                          "specialRules": [
                                              {
                                                  "name": "Lethal 5+",
                                                  "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, your attack dice results of equal to or greater than x that are successful hits are critical hits, x is the number after the weapon's Lethal, e.g. Lethal 5+."
                                              }
                                          ],
                                          "id": "a8219dc6-ef64-46d9-a0a9-691ff3e711e5"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Hellblade",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 4,
                                              "criticalDamage": 6,
                                              "specialRules": [
                                                  {
                                                      "name": "Lethal 5+",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, your attack dice results of equal to or greater than x that are successful hits are critical hits, x is the number after the weapon's Lethal, e.g. Lethal 5+."
                                                  }
                                              ],
                                              "id": "a8219dc6-ef64-46d9-a0a9-691ff3e711e5"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "BLOODLETTER (ICON BEARER)",
                                      "description": "Blood letters go to war under a semblance of martial affectation. Favoured daemons of their packs display blood-soaked banners, under which their howling kin rend and butcher Khorne's enemies with greater ferocity.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 6,
                                      "wounds": 9,
                                      "keyWords": [
                                          "CHAOS DAEMON",
                                          "CHAOS",
                                          "DAEMON",
                                          "KHORNE",
                                          "BLOODLETTER",
                                          "ICON BEARER"
                                      ],
                                      "abilities": [
                                          {
                                              "name": "Daemon",
                                              "description": "This operative has a 5+ invulnerable save.",
                                              "id": "2d8a788e-8cf3-4c83-98c6-a0adfcf4a559"
                                          },
                                          {
                                              "name": "Icon Bearer",
                                              "description": "When determining control of an objective marker, treat this operative's APL characteristic as being 1 higher. Note that this is not a modifier.",
                                              "id": "9637be41-1b13-4018-b8c6-c8783b8af5e7"
                                          }
                                      ],
                                      "uniqueActions": [
                                          {
                                              "name": "Daemonic Icon",
                                              "cost": 1,
                                              "description": "Until the end of the Turning Point, while this operative is within 3' of a friendly BLOODLETTER operative, that friendly operative is invigorated by Chaos. While an operative is invigorated by Chaos, improve its invulnerable save by 1.",
                                              "id": "331ca9e2-6feb-4077-9441-ae95fba7463a"
                                          }
                                      ],
                                      "id": "a448f1f6-4b3b-4e02-b10e-33ee41288ccc",
                                      "currentWounds": 9,
                                      "unitType": [
                                          "combat"
                                      ],
                                      "unitImageName": "BLOODLETTER_ICON_BEARER",
                                      "selectedMeleeWeapon": {
                                          "name": "Hellblade",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 4,
                                          "criticalDamage": 6,
                                          "specialRules": [
                                              {
                                                  "name": "Lethal 5+",
                                                  "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, your attack dice results of equal to or greater than x that are successful hits are critical hits, x is the number after the weapon's Lethal, e.g. Lethal 5+."
                                              }
                                          ],
                                          "id": "3a1c8c28-6aa1-44ae-8d5f-08ef67d5cf91"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Hellblade",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 4,
                                              "criticalDamage": 6,
                                              "specialRules": [
                                                  {
                                                      "name": "Lethal 5+",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, your attack dice results of equal to or greater than x that are successful hits are critical hits, x is the number after the weapon's Lethal, e.g. Lethal 5+."
                                                  }
                                              ],
                                              "id": "3a1c8c28-6aa1-44ae-8d5f-08ef67d5cf91"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "BLOODLETTER (HORN BEARER)",
                                      "description": "Accompanying the Blood Legions to war, some daemons bear immense brass war horns. They use these to sound the charge, spurring their diabolical brethren on in a red tide that sweeps away the living in a sea of blood.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 6,
                                      "wounds": 9,
                                      "keyWords": [
                                          "CHAOS DAEMON",
                                          "CHAOS",
                                          "DAEMON",
                                          "KHORNE",
                                          "BLOODLETTER",
                                          "ICON BEARER"
                                      ],
                                      "abilities": [
                                          {
                                              "name": "Daemon",
                                              "description": "This operative has a 5+ invulnerable save.",
                                              "id": "e15204d8-b81c-4d9b-85f2-51c008ea3ee2"
                                          }
                                      ],
                                      "uniqueActions": [
                                          {
                                              "name": "Instrument of Chaos",
                                              "cost": 1,
                                              "description": "Until the end of the Turning Point, each time a friendly BLOODLETTER operative performs a Normal Move or Charge action, it can move an additional 1'.",
                                              "id": "7680a23b-7d3f-476a-93e1-bf77fa324929"
                                          }
                                      ],
                                      "id": "c35ec180-f93c-487a-98ad-4f9d986329ae",
                                      "currentWounds": 9,
                                      "unitType": [
                                          "combat"
                                      ],
                                      "unitImageName": "BLOODLETTER_HORN_BEARER",
                                      "selectedMeleeWeapon": {
                                          "name": "Hellblade",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 4,
                                          "criticalDamage": 6,
                                          "specialRules": [
                                              {
                                                  "name": "Lethal 5+",
                                                  "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, your attack dice results of equal to or greater than x that are successful hits are critical hits, x is the number after the weapon's Lethal, e.g. Lethal 5+."
                                              }
                                          ],
                                          "id": "cd3cc136-365d-49da-aed6-374314b0d58d"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Hellblade",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 4,
                                              "criticalDamage": 6,
                                              "specialRules": [
                                                  {
                                                      "name": "Lethal 5+",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, your attack dice results of equal to or greater than x that are successful hits are critical hits, x is the number after the weapon's Lethal, e.g. Lethal 5+."
                                                  }
                                              ],
                                              "id": "cd3cc136-365d-49da-aed6-374314b0d58d"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "BLOODREAPER",
                                      "description": "Bloodreapers marshal Khorne s frenzied hordes in battle. They are among the deadliest warriors of their kind, each having offered up countless skulls to their lord. They are not blinded by rage, and despatch their lessers with martial precision to ensure no foe escapes.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 6,
                                      "wounds": 10,
                                      "keyWords": [
                                          "CHAOS DAEMON",
                                          "CHAOS",
                                          "DAEMON",
                                          "KHORNE",
                                          "LEADER",
                                          "BLOODLETTER",
                                          "BLOODREAPER"
                                      ],
                                      "abilities": [
                                          {
                                              "name": "Daemon",
                                              "description": "This operative has a 5+ invulnerable save.",
                                              "id": "469ef7d7-b3e3-4397-8145-d98f3b346f69"
                                          }
                                      ],
                                      "id": "195f4a74-8d2c-4fb2-b277-02e42a5fc2ab",
                                      "currentWounds": 10,
                                      "unitType": [
                                          "combat"
                                      ],
                                      "unitImageName": "BLOODREAPER",
                                      "selectedMeleeWeapon": {
                                          "name": "Hellblade",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 2,
                                          "damage": 4,
                                          "criticalDamage": 6,
                                          "specialRules": [
                                              {
                                                  "name": "Lethal 5+",
                                                  "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, your attack dice results of equal to or greater than x that are successful hits are critical hits, x is the number after the weapon's Lethal, e.g. Lethal 5+."
                                              }
                                          ],
                                          "id": "2837a001-afe3-4b28-95b7-d937aa62f014"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Hellblade",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 4,
                                              "criticalDamage": 6,
                                              "specialRules": [
                                                  {
                                                      "name": "Lethal 5+",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, your attack dice results of equal to or greater than x that are successful hits are critical hits, x is the number after the weapon's Lethal, e.g. Lethal 5+."
                                                  }
                                              ],
                                              "id": "2837a001-afe3-4b28-95b7-d937aa62f014"
                                          }
                                      ]
                                  }
                              ],
                              "currentDataslates": [],
                              "id": "8af4dd84-a7d4-4d5e-8fed-3b4c820ad455",
                              "defaultDataslates": {
                                  "448def93-f1dc-424e-92d9-765e62805c91": 6
                              }
                          },
                          {
                              "name": "Daemonette",
                              "archetype": [
                                  "seekAndDestroy",
                                  "recon"
                              ],
                              "availableDataslates": [
                                  {
                                      "name": "DAEMONETTE (FIGHTER)",
                                      "description": "Daemonettes are seductive harbingers of torment. They advance in a swift surge, dancing with impossible agility to pounce upon their victims with keening screams of horrific desire. They indulge in inflicting wounds with their razor-sharp claws.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 6,
                                      "wounds": 8,
                                      "keyWords": [
                                          "CHAOS DAEMON",
                                          "CHAOS",
                                          "DAEMON",
                                          "SLAANESH",
                                          "DAEMONETTE",
                                          "FIGHTER"
                                      ],
                                      "abilities": [
                                          {
                                              "name": "Daemon",
                                              "description": "This operative has a 5+ invulnerable save.",
                                              "id": "ff969dd0-2bcf-4a7c-85cf-da99a99c32a2"
                                          }
                                      ],
                                      "id": "6f890b26-e8f8-4c3c-96cf-f7f65182a1f5",
                                      "currentWounds": 8,
                                      "unitType": [
                                          "combat",
                                          "scout"
                                      ],
                                      "unitImageName": "DAEMONETTE_FIGHTER",
                                      "selectedMeleeWeapon": {
                                          "name": "Claws",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 4,
                                          "criticalDamage": 5,
                                          "specialRules": [
                                              {
                                                  "name": "Relentless",
                                                  "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, you can re-roll any or all of your attack dice."
                                              }
                                          ],
                                          "id": "51f74886-2ad6-4d68-89e5-a09784e80b8b"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Claws",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 4,
                                              "criticalDamage": 5,
                                              "specialRules": [
                                                  {
                                                      "name": "Relentless",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, you can re-roll any or all of your attack dice."
                                                  }
                                              ],
                                              "id": "51f74886-2ad6-4d68-89e5-a09784e80b8b"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "DAEMONETTE (ICON BEARER)",
                                      "description": "The icons borne in the clawed grasp of some Daemonettes display the repulsive beauty embodied by Slaanesh. Under their baleful influence, the hedonistic daemons reach new heights of murderous excess.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 6,
                                      "wounds": 8,
                                      "keyWords": [
                                          "CHAOS DAEMON",
                                          "CHAOS",
                                          "DAEMON",
                                          "SLAANESH",
                                          "DAEMONETTE",
                                          "ICON BEARER"
                                      ],
                                      "abilities": [
                                          {
                                              "name": "Daemon",
                                              "description": "This operative has a 5+ invulnerable save.",
                                              "id": "f22a8b48-dfd6-4fec-80ab-54e3f6643e8e"
                                          },
                                          {
                                              "name": "Icon Bearer",
                                              "description": "When determining control of an objective marker, treat this operative's APL characteristic as being 1 higher. Note that this is not a modifier.",
                                              "id": "9b75393a-503e-4128-a235-7f9db33effbb"
                                          }
                                      ],
                                      "uniqueActions": [
                                          {
                                              "name": "Daemonic Icon",
                                              "cost": 1,
                                              "description": "Until the end of the Turning Point, while this operative is within 3' of a friendly DAEMONETTE operative, that friendly operative is invigorated by Chaos. While an operative is invigorated by Chaos, improve its invulnerable save by 1.",
                                              "id": "56fa189a-774a-4dc3-8521-0addf5210bf0"
                                          }
                                      ],
                                      "id": "48d912a5-4f79-4a8b-be9f-0cb60fccf772",
                                      "currentWounds": 8,
                                      "unitType": [
                                          "combat",
                                          "scout"
                                      ],
                                      "unitImageName": "DAEMONETTE_ICON_BEARER",
                                      "selectedMeleeWeapon": {
                                          "name": "Claws",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 4,
                                          "criticalDamage": 5,
                                          "specialRules": [
                                              {
                                                  "name": "Relentless",
                                                  "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, you can re-roll any or all of your attack dice."
                                              }
                                          ],
                                          "id": "cff889bc-e470-408a-94c8-54bc3140ec6c"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Claws",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 4,
                                              "criticalDamage": 5,
                                              "specialRules": [
                                                  {
                                                      "name": "Relentless",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, you can re-roll any or all of your attack dice."
                                                  }
                                              ],
                                              "id": "cff889bc-e470-408a-94c8-54bc3140ec6c"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "DAEMONETTE (HORN BEARER)",
                                      "description": "Some Daemonettes brandish sinuous instruments that they play with lascivious exuberance. Emitting a supernatural resonance, they inflame the passions of other Daemonettes, the unnatural music goading them to more inventive debaucheries.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 6,
                                      "wounds": 8,
                                      "keyWords": [
                                          "CHAOS DAEMON",
                                          "CHAOS",
                                          "DAEMON",
                                          "SLAANESH",
                                          "DAEMONETTE",
                                          "HORN BEARER"
                                      ],
                                      "abilities": [
                                          {
                                              "name": "Daemon",
                                              "description": "This operative has a 5+ invulnerable save.",
                                              "id": "9f856660-de6a-47f4-b68d-b7b767e883b7"
                                          }
                                      ],
                                      "uniqueActions": [
                                          {
                                              "name": "Instrument of Chaos",
                                              "cost": 1,
                                              "description": "Until the end of the Turning Point, each time a friendly DAEMONETTE operative performs a Normal Move or Charge action, it can move an additional 1'.",
                                              "id": "167d06ad-d67e-4319-b62f-631066bf07b2"
                                          }
                                      ],
                                      "id": "2b93ae81-80cf-4c6c-a05e-7bc2e3d49227",
                                      "currentWounds": 8,
                                      "unitType": [
                                          "combat",
                                          "scout"
                                      ],
                                      "unitImageName": "DAEMONETTE_HORN_BEARER",
                                      "selectedMeleeWeapon": {
                                          "name": "Claws",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 4,
                                          "criticalDamage": 5,
                                          "specialRules": [
                                              {
                                                  "name": "Relentless",
                                                  "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, you can re-roll any or all of your attack dice."
                                              }
                                          ],
                                          "id": "42c58ac0-c10d-4cd4-949d-a5394b0a4690"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Claws",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 4,
                                              "criticalDamage": 5,
                                              "specialRules": [
                                                  {
                                                      "name": "Relentless",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, you can re-roll any or all of your attack dice."
                                                  }
                                              ],
                                              "id": "42c58ac0-c10d-4cd4-949d-a5394b0a4690"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "ALLURESS",
                                      "description": "More beauteous and yet more repulsive than most Daemonettes, Alluresses orchestrate their kin's slaughter with trilling songs of praise to Slaanesh. Their hypnotic glamour causes foes to falter in their presence, helpless as barbed claws and needle-like teeth close in.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 6,
                                      "wounds": 9,
                                      "keyWords": [
                                          "CHAOS DAEMON",
                                          "CHAOS",
                                          "DAEMON",
                                          "SLAANESH",
                                          "LEADER",
                                          "DAEMONETTE",
                                          "ALLURESS"
                                      ],
                                      "abilities": [
                                          {
                                              "name": "Daemon",
                                              "description": "This operative has a 5+ invulnerable save.",
                                              "id": "108ba86c-dcb8-4511-a26a-c48acc3c8a37"
                                          }
                                      ],
                                      "id": "a28c0fba-6409-488a-9ef5-dbd0f75524ec",
                                      "currentWounds": 9,
                                      "unitType": [
                                          "combat",
                                          "scout"
                                      ],
                                      "unitImageName": "ALLURESS",
                                      "selectedMeleeWeapon": {
                                          "name": "Claws",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 2,
                                          "damage": 4,
                                          "criticalDamage": 5,
                                          "specialRules": [
                                              {
                                                  "name": "Relentless",
                                                  "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, you can re-roll any or all of your attack dice."
                                              }
                                          ],
                                          "id": "6517d924-908c-4938-9905-7dcb70e836ef"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Claws",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 4,
                                              "criticalDamage": 5,
                                              "specialRules": [
                                                  {
                                                      "name": "Relentless",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, you can re-roll any or all of your attack dice."
                                                  }
                                              ],
                                              "id": "6517d924-908c-4938-9905-7dcb70e836ef"
                                          }
                                      ]
                                  }
                              ],
                              "currentDataslates": [],
                              "id": "0b05581a-8b79-4ef0-8bdc-737fd4034bc5",
                              "defaultDataslates": {
                                  "6f890b26-e8f8-4c3c-96cf-f7f65182a1f5": 6
                              }
                          },
                          {
                              "name": "Plaguebearer",
                              "archetype": [
                                  "security"
                              ],
                              "availableDataslates": [
                                  {
                                      "name": "PLAGUEBEARER (FIGHTER)",
                                      "description": "A Plaguebearer's body is swollen and bursting with contagion. They shamble purposefully forward with dour inevitability, bringing the promise of corruption with them. Despite their appearance, they swing their disease-laden plagueswords with great strength.",
                                      "movement": 2,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 5,
                                      "wounds": 8,
                                      "keyWords": [
                                          "CHAOS DAEMON",
                                          "CHAOS",
                                          "DAEMON",
                                          "NURGLE",
                                          "PLAGUEBEARER",
                                          "FIGHTER"
                                      ],
                                      "abilities": [
                                          {
                                              "name": "Daemon",
                                              "description": "This operative has a 5+ invulnerable save.",
                                              "id": "636d2140-ec2c-409f-8a23-280b14687e9c"
                                          },
                                          {
                                              "name": "Disgustingly Resilient",
                                              "description": "Each time this operative would lose a wound, roll one D6: on a 5+, that wound is not lost. Other than the effects of Battle Scars, this operative cannot be injured.",
                                              "id": "92efc653-3457-43ef-98a0-c861bce75165"
                                          }
                                      ],
                                      "id": "18f99bdf-6436-4dd4-8190-8f76fbc2336c",
                                      "currentWounds": 8,
                                      "unitType": [
                                          "staunch"
                                      ],
                                      "unitImageName": "PLAGUEBEARER_FIGHTER",
                                      "selectedMeleeWeapon": {
                                          "name": "Plaguesword",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 4,
                                          "criticalDamage": 6,
                                          "id": "294e38b3-e2e4-4b7b-9206-64a8eb14b0e6"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Plaguesword",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 4,
                                              "criticalDamage": 6,
                                              "id": "294e38b3-e2e4-4b7b-9206-64a8eb14b0e6"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "PLAGUEBEARER (ICON BEARER)",
                                      "description": "These daemons carry stinking icons of Nurgle into the realm of realspace, the poisonous totems dripping with pus and surrounded by clouds of fat flies. Doleful bells hang from these grim icons, tolling out the death knells of the daemon's foes.",
                                      "movement": 2,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 5,
                                      "wounds": 8,
                                      "keyWords": [
                                          "CHAOS DAEMON",
                                          "CHAOS",
                                          "DAEMON",
                                          "NURGLE",
                                          "PLAGUEBEARER",
                                          "ICON BEARER"
                                      ],
                                      "abilities": [
                                          {
                                              "name": "Daemon",
                                              "description": "This operative has a 5+ invulnerable save.",
                                              "id": "80e4372e-c70f-4cf7-bc5e-ab4e6d2189d3"
                                          },
                                          {
                                              "name": "Disgustingly Resilient",
                                              "description": "Each time this operative would lose a wound, roll one D6: on a 5+, that wound is not lost. Other than the effects of Battle Scars, this operative cannot be injured.",
                                              "id": "2a07bf27-e16e-4d1f-9775-13d03f805d8e"
                                          },
                                          {
                                              "name": "Icon Bearer",
                                              "description": "When determining control of an objective marker, treat this operative's APL characteristic as being 1 higher. Note that this is not a modifier.",
                                              "id": "66895bb6-92c1-4a05-a701-8c0cc3b163f7"
                                          }
                                      ],
                                      "uniqueActions": [
                                          {
                                              "name": "Daemonic Icon",
                                              "cost": 1,
                                              "description": "Until the end of the Turning Point, while this operative is within 3' of a friendly PLAGUEBEARER operative, that friendly operative is invigorated by Chaos. While an operative is invigorated by Chaos, improve its invulnerable save by 1.",
                                              "id": "c144fedc-051b-40b6-88ac-7d8a98190ffc"
                                          }
                                      ],
                                      "id": "6e3963a5-a6e5-4fd5-9ec7-b07db6f49596",
                                      "currentWounds": 8,
                                      "unitType": [
                                          "staunch"
                                      ],
                                      "unitImageName": "PLAGUEBEARER_ICON_BEARER",
                                      "selectedMeleeWeapon": {
                                          "name": "Plaguesword",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 4,
                                          "criticalDamage": 6,
                                          "id": "20a7fd47-86f3-40ad-822a-014858bb1503"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Plaguesword",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 4,
                                              "criticalDamage": 6,
                                              "id": "20a7fd47-86f3-40ad-822a-014858bb1503"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "PLAGUEBEARER (HORN BEARER)",
                                      "description": "Plaguebearers utter a constant drone as they interminably tally all of Nurgle's endless diseases. Some of these fell daemons carry horns or pipes carved from rotten remains. From these, they elicit a dirge that stirs their kind into a rapid advance.",
                                      "movement": 2,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 5,
                                      "wounds": 8,
                                      "keyWords": [
                                          "CHAOS DAEMON",
                                          "CHAOS",
                                          "DAEMON",
                                          "NURGLE",
                                          "PLAGUEBEARER",
                                          "HORN BEARER"
                                      ],
                                      "abilities": [
                                          {
                                              "name": "Daemon",
                                              "description": "This operative has a 5+ invulnerable save.",
                                              "id": "94e1acc2-a7cb-42ff-be16-1de26de405bd"
                                          },
                                          {
                                              "name": "Disgustingly Resilient",
                                              "description": "Each time this operative would lose a wound, roll one D6: on a 5+, that wound is not lost. Other than the effects of Battle Scars, this operative cannot be injured.",
                                              "id": "e8d47531-68b7-4480-9eb1-c61d46f8dff0"
                                          }
                                      ],
                                      "uniqueActions": [
                                          {
                                              "name": "Instrument of Chaos",
                                              "cost": 1,
                                              "description": "Until the end of the Turning Point, each time a friendly PLAGUEBEARER operative performs a Normal Move or Charge action, it can move an additional 1'.",
                                              "id": "4a0b3317-3d89-4947-972e-910f739ff9e5"
                                          }
                                      ],
                                      "id": "1f71b113-bd64-46e4-a0da-914b4def8953",
                                      "currentWounds": 8,
                                      "unitType": [
                                          "staunch"
                                      ],
                                      "unitImageName": "PLAGUEBEARER_HORN_BEARER",
                                      "selectedMeleeWeapon": {
                                          "name": "Plaguesword",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 4,
                                          "criticalDamage": 6,
                                          "id": "5c446762-2abc-45ed-8bd7-4470d3b2c77c"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Plaguesword",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 4,
                                              "criticalDamage": 6,
                                              "id": "5c446762-2abc-45ed-8bd7-4470d3b2c77c"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "PLAGUERIDDEN",
                                      "description": "Despite their death's head rictus grins, Plagueridden are devoted to the serious business of spreading Nurgle's bounteous plagues across reality. They often bear signs of Nurgle's favour - such as more elaborate horns - and direct other Plaguebearers in his grand plans.",
                                      "movement": 2,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 6,
                                      "wounds": 9,
                                      "keyWords": [
                                          "CHAOS DAEMON",
                                          "CHAOS",
                                          "DAEMON",
                                          "NURGLE",
                                          "LEADER",
                                          "PLAGUEBEARER",
                                          "PLAGUERIDDEN"
                                      ],
                                      "abilities": [
                                          {
                                              "name": "Daemon",
                                              "description": "This operative has a 5+ invulnerable save.",
                                              "id": "cb90a616-5333-4586-b8b9-d34daab1407c"
                                          },
                                          {
                                              "name": "Disgustingly Resilient",
                                              "description": "Each time this operative would lose a wound, roll one D6: on a 5+, that wound is not lost. Other than the effects of Battle Scars, this operative cannot be injured.",
                                              "id": "d6f9d361-f234-4854-96c9-31503eaf0c96"
                                          }
                                      ],
                                      "id": "874552fd-d2e7-4a65-bb1e-3a14908b70f6",
                                      "currentWounds": 9,
                                      "unitType": [
                                          "staunch"
                                      ],
                                      "unitImageName": "PLAGUERIDDEN",
                                      "selectedMeleeWeapon": {
                                          "name": "Plaguesword",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 2,
                                          "damage": 4,
                                          "criticalDamage": 6,
                                          "id": "8c0a301e-9049-4d66-9a05-85e4fef1674b"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Plaguesword",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 4,
                                              "criticalDamage": 6,
                                              "id": "8c0a301e-9049-4d66-9a05-85e4fef1674b"
                                          }
                                      ]
                                  }
                              ],
                              "currentDataslates": [],
                              "id": "24a8ab0d-7dc0-4452-a3d9-4420c63cfb1e",
                              "defaultDataslates": {
                                  "18f99bdf-6436-4dd4-8190-8f76fbc2336c": 6
                              }
                          },
                          {
                              "name": "Pink Horror",
                              "archetype": [
                                  "security"
                              ],
                              "availableDataslates": [
                                  {
                                      "name": "PINK HORROR (FIGHTER)",
                                      "description": "Pink Horrors are magic made manifest. They caper and whirl, cackling as bolts of raw sorcery leap from their clawed fingertips. These coruscating streams of multicoloured flame do not merely burn, they turn their victims into hideous or nonsensical forms.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 6,
                                      "wounds": 8,
                                      "selectedRangeWeapon": {
                                          "name": "Coruscating flames",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "id": "7da34627-995d-431d-9756-3eabff6259c3"
                                      },
                                      "keyWords": [
                                          "CHAOS DAEMON",
                                          "CHAOS",
                                          "DAEMON",
                                          "TZEENTCH",
                                          "PINK HORROR",
                                          "FIGHTER"
                                      ],
                                      "abilities": [
                                          {
                                              "name": "Daemon",
                                              "description": "This operative has a 5+ invulnerable save.",
                                              "id": "06341037-b5cb-463b-98e7-e7508c27da2d"
                                          }
                                      ],
                                      "id": "aa2bba2e-b7da-494a-94e6-4e9d04ea9822",
                                      "currentWounds": 8,
                                      "unitType": [
                                          "staunch"
                                      ],
                                      "unitImageName": "PINK_HORROR_FIGHTER",
                                      "selectedMeleeWeapon": {
                                          "name": "Fists",
                                          "type": "close",
                                          "attacks": 3,
                                          "ballisticWeaponSkill": 4,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "id": "f83c98c2-b6cf-4ade-a2c3-a0684ea5b054"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Coruscating flames",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "id": "7da34627-995d-431d-9756-3eabff6259c3"
                                          },
                                          {
                                              "name": "Fists",
                                              "type": "close",
                                              "attacks": 3,
                                              "ballisticWeaponSkill": 4,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "id": "f83c98c2-b6cf-4ade-a2c3-a0684ea5b054"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "PINK HORROR (ICON BEARER)",
                                      "description": "Either held at the end of one gangling limb, or sprouting from the daemon's own flesh, favoured Pink Horrors carry a sorcerous icon ofTzeentch. Some of these devices burn with multi-hued fire, or twist into new shapes as their bearers gibber arcane litanies.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 6,
                                      "wounds": 8,
                                      "selectedRangeWeapon": {
                                          "name": "Coruscating flames",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "id": "bfc47b3f-6a28-40f2-9ccc-bdc9f9ba1494"
                                      },
                                      "keyWords": [
                                          "CHAOS DAEMON",
                                          "CHAOS",
                                          "DAEMON",
                                          "TZEENTCH",
                                          "PINK HORROR",
                                          "ICON BEARER"
                                      ],
                                      "abilities": [
                                          {
                                              "name": "Daemon",
                                              "description": "This operative has a 5+ invulnerable save.",
                                              "id": "b681f177-2e00-4da0-92f5-6e3631cb55b4"
                                          },
                                          {
                                              "name": "Icon Bearer",
                                              "description": "When determining control of an objective marker, treat this operative's APL characteristic as being 1 higher. Note that this is not a modifier.",
                                              "id": "433b6bf4-f77e-4dec-9b32-91ad36d8e146"
                                          }
                                      ],
                                      "uniqueActions": [
                                          {
                                              "name": "Daemonic Icon",
                                              "cost": 1,
                                              "description": "Until the end of the Turning Point, while this operative is within 3' of a friendly PINK HORROR operative, that friendly operative is invigorated by Chaos. While an operative is invigorated by Chaos, improve its invulnerable save by 1.",
                                              "id": "2381dcdc-e5cd-4328-84f3-00d9e0a344c2"
                                          }
                                      ],
                                      "id": "58e620b1-6f9e-4720-8277-cc0e75a77776",
                                      "currentWounds": 8,
                                      "unitType": [
                                          "marksman"
                                      ],
                                      "unitImageName": "PINK_HORROR_ICON_BEARER",
                                      "selectedMeleeWeapon": {
                                          "name": "Fists",
                                          "type": "close",
                                          "attacks": 3,
                                          "ballisticWeaponSkill": 4,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "id": "40a28e98-7071-4efd-adb6-14e44c12801f"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Coruscating flames",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "id": "bfc47b3f-6a28-40f2-9ccc-bdc9f9ba1494"
                                          },
                                          {
                                              "name": "Fists",
                                              "type": "close",
                                              "attacks": 3,
                                              "ballisticWeaponSkill": 4,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "id": "40a28e98-7071-4efd-adb6-14e44c12801f"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "PINK HORROR (HORN BEARER)",
                                      "description": "Some Pink Horrors carry shimmering horns, often in the serpentine form of Tzeentch's own sigil. The daemons sound them at random moments, emitting mutating rhythms, streams of nebulous fire or other strange discharges that send their kind wild with delight.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 6,
                                      "wounds": 8,
                                      "selectedRangeWeapon": {
                                          "name": "Coruscating flames",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "id": "6826189f-d90a-4761-b11b-c68d4a5bba40"
                                      },
                                      "keyWords": [
                                          "CHAOS DAEMON",
                                          "CHAOS",
                                          "DAEMON",
                                          "TZEENTCH",
                                          "PINK HORROR",
                                          "HORN BEARER"
                                      ],
                                      "abilities": [
                                          {
                                              "name": "Daemon",
                                              "description": "This operative has a 5+ invulnerable save.",
                                              "id": "80517c99-eb4e-400c-9492-8c51d921cc72"
                                          }
                                      ],
                                      "uniqueActions": [
                                          {
                                              "name": "Instrument of Chaos",
                                              "cost": 1,
                                              "description": "Until the end of the Turning Point, each time a friendly PINK HORROR operative performs a Normal Move or Charge action, it can move an additional 1'.",
                                              "id": "5e0e592a-db86-47b2-a0e9-9fd7ed494ac4"
                                          }
                                      ],
                                      "id": "de74e384-851e-487e-9e83-b65e88222b48",
                                      "currentWounds": 8,
                                      "unitType": [
                                          "marksman"
                                      ],
                                      "unitImageName": "PINK_HORROR_HORN_BEARER",
                                      "selectedMeleeWeapon": {
                                          "name": "Fists",
                                          "type": "close",
                                          "attacks": 3,
                                          "ballisticWeaponSkill": 4,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "id": "8d0b3664-d60b-4b13-bdef-b8c4a16e66d6"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Coruscating flames",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "id": "6826189f-d90a-4761-b11b-c68d4a5bba40"
                                          },
                                          {
                                              "name": "Fists",
                                              "type": "close",
                                              "attacks": 3,
                                              "ballisticWeaponSkill": 4,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "id": "8d0b3664-d60b-4b13-bdef-b8c4a16e66d6"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "PINK HORROR IRIDESCENT",
                                      "description": "Iridescent Horrors are imbued with a sliver of Tzeentch's immortal knowledge. They revel in leading their capering daemons in enacting Tzeentch's schemes.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 6,
                                      "wounds": 9,
                                      "selectedRangeWeapon": {
                                          "name": "Coruscating flames",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 2,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "id": "ef557c58-4a30-4d8b-bfa4-0e1174dd780c"
                                      },
                                      "keyWords": [
                                          "CHAOS DAEMON",
                                          "CHAOS",
                                          "DAEMON",
                                          "TZEENTCH",
                                          "LEADER",
                                          "PINK HORROR",
                                          "IRIDESCENT"
                                      ],
                                      "abilities": [
                                          {
                                              "name": "Daemon",
                                              "description": "This operative has a 5+ invulnerable save.",
                                              "id": "d8861e2e-7f2b-4a97-aee6-d5a73627382f"
                                          }
                                      ],
                                      "id": "f99c3d65-c70f-4cf7-99b2-facb280ac0c7",
                                      "currentWounds": 9,
                                      "unitType": [
                                          "marksman"
                                      ],
                                      "unitImageName": "PINK_HORROR_IRIDESCENT",
                                      "selectedMeleeWeapon": {
                                          "name": "Fists",
                                          "type": "close",
                                          "attacks": 3,
                                          "ballisticWeaponSkill": 4,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "id": "4926b9ba-2bf1-4a30-9efd-a78f3650aee1"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Coruscating flames",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "id": "ef557c58-4a30-4d8b-bfa4-0e1174dd780c"
                                          },
                                          {
                                              "name": "Fists",
                                              "type": "close",
                                              "attacks": 3,
                                              "ballisticWeaponSkill": 4,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "id": "4926b9ba-2bf1-4a30-9efd-a78f3650aee1"
                                          }
                                      ]
                                  }
                              ],
                              "currentDataslates": [],
                              "id": "a2de1e82-27ad-41f9-8080-7c1acb1c8232",
                              "defaultDataslates": {
                                  "aa2bba2e-b7da-494a-94e6-4e9d04ea9822": 6
                              }
                          },
                          {
                              "name": "Blue Horror",
                              "archetype": [
                                  "security",
                                  "recon"
                              ],
                              "availableDataslates": [
                                  {
                                      "name": "BLUE HORROR",
                                      "description": "Should a Pink Horror be cut down, it may split, with the two halves reforming as smaller daemons. These Blue Horrors are morose and spiteful creatures, aggressively calling on their warp-spawned powers to destroy those who dared lay their original form low.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 2,
                                      "defense": 3,
                                      "save": 6,
                                      "wounds": 6,
                                      "selectedRangeWeapon": {
                                          "name": "Fizzing flames",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 4,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "specialRules": [
                                              {
                                                  "name": "Rng 6'",
                                                  "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                              }
                                          ],
                                          "id": "1d1c6ea9-57ff-42f8-a4fb-5a854a3a8b7f"
                                      },
                                      "keyWords": [
                                          "CHAOS DAEMON",
                                          "CHAOS",
                                          "DAEMON",
                                          "TZEENTCH",
                                          "BLUE HORROR"
                                      ],
                                      "abilities": [
                                          {
                                              "name": "Ephemeral Daemon",
                                              "description": "This operative has a 6+ invulnerable save.",
                                              "id": "c405ff58-6e67-4c8a-9e42-2c2048d1806c"
                                          }
                                      ],
                                      "id": "db7c90c7-4916-46aa-80ec-c292c3f6a29f",
                                      "currentWounds": 6,
                                      "unitType": [
                                          "marksman",
                                          "scout"
                                      ],
                                      "unitImageName": "BLUE_HORROR",
                                      "selectedMeleeWeapon": {
                                          "name": "Fists",
                                          "type": "close",
                                          "attacks": 3,
                                          "ballisticWeaponSkill": 5,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "id": "d31aec71-9ef5-44a5-b85d-c64df8deb2c8"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Fizzing flames",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 4,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  }
                                              ],
                                              "id": "1d1c6ea9-57ff-42f8-a4fb-5a854a3a8b7f"
                                          },
                                          {
                                              "name": "Fists",
                                              "type": "close",
                                              "attacks": 3,
                                              "ballisticWeaponSkill": 5,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "id": "d31aec71-9ef5-44a5-b85d-c64df8deb2c8"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "BRIMSTONE HORRORS",
                                      "description": "Blue Horrors that are destroyed may vanish in a cloud of smoke, from which emerge two stunted Brimstone Horrors. These diminutive daemons are manifestations of pure bitterness, seeking to incinerate their enemies with burning talons and fangs.",
                                      "movement": 2,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 2,
                                      "save": 6,
                                      "wounds": 5,
                                      "keyWords": [
                                          "CHAOS DAEMON",
                                          "CHAOS",
                                          "DAEMON",
                                          "TZEENTCH",
                                          "BRIMSTONE HORRORS"
                                      ],
                                      "abilities": [
                                          {
                                              "name": "Ephemeral Daemon",
                                              "description": "This operative has a 6+ invulnerable save.",
                                              "id": "7cc1fb86-e013-49d2-a048-5334ae9c3f2c"
                                          },
                                          {
                                              "name": "Insignificant",
                                              "description": "Each time this operative would perform a mission action or the Pick Up action, you must subtract one additional AP to do so. This operative cannot be equipped with equipment. Note that in narrative play, this operative does not have a narrative datacard and is no longer part of your kill team after the game.",
                                              "id": "84ca8523-36dc-4114-9dec-2487595db9bc"
                                          }
                                      ],
                                      "id": "2efc07de-d175-4d57-8923-f1a026a9c8fa",
                                      "currentWounds": 5,
                                      "unitType": [
                                          "marksman"
                                      ],
                                      "unitImageName": "BRIMSTONE HORRORS",
                                      "selectedMeleeWeapon": {
                                          "name": "Fists",
                                          "type": "close",
                                          "attacks": 2,
                                          "ballisticWeaponSkill": 5,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "id": "9f41252f-e212-4dd3-9d60-ac83ff217765"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Fists",
                                              "type": "close",
                                              "attacks": 2,
                                              "ballisticWeaponSkill": 5,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "id": "9f41252f-e212-4dd3-9d60-ac83ff217765"
                                          }
                                      ]
                                  }
                              ],
                              "currentDataslates": [],
                              "id": "b21c75b6-59ee-4c7c-8e1b-8c8dc76c856e",
                              "defaultDataslates": {
                                  "db7c90c7-4916-46aa-80ec-c292c3f6a29f": 8
                              }
                          }
                      ],
                      "chosenFireTeams": [],
                      "ploys": [
                          {
                              "name": "UNSTOPPABLE FEROCITY",
                              "description": "Until the end of the Turning Point, add 1 to the Attacks characteristic of melee weapons friendly KHORNE operatives are equipped with.",
                              "cost": 1,
                              "type": "strategic",
                              "id": "ad8b051b-2d7f-4d3e-be5a-1fe2b50945ab"
                          },
                          {
                              "name": "QUICKSILVER SWIFTNESS",
                              "description": "Until the end of the Turning Point, each time a friendly SLAANESH operative is activated, it can perform a free Dash action during that activation.",
                              "cost": 1,
                              "type": "strategic",
                              "id": "3f68dc75-2a78-433b-b5c0-51579c97e45a"
                          },
                          {
                              "name": "GLISTENING BARRAGE",
                              "description": "Until the end of the Turning Point, ranged weapons friendly TZEENTCH operatives are equipped with gain the Ceaseless special rule.",
                              "cost": 1,
                              "type": "strategic",
                              "id": "9ac94db4-cf28-4d00-a5b0-beac066e8d1b"
                          },
                          {
                              "name": "CONTAGION",
                              "description": "Until the end of the Turning Point, while an enemy operative is Visible to and within 2' of a friendly NURGLE operative, that enemy operative is treated as being injured (only subtract 2' from its Movement characteristic as a result of being injured if it is activated within 2' of a friendly NURGLE operative).",
                              "cost": 1,
                              "type": "strategic",
                              "id": "e5764063-71c7-441a-acdc-06113b0bdcef"
                          },
                          {
                              "name": "WARP SURGE",
                              "description": "Use this Tactical Ploy when a friendly CHAOS DAEMON operative is selected as the target of a ranged attack. Until the end of the Turning Point, each time a shooting attack is made against that friendly operative, in the Roll Defence Dice step of that shooting attack, you can re-roil any or all of your defence dice.",
                              "cost": 1,
                              "type": "tactical",
                              "id": "8232e277-cfa1-4bad-91b3-1803682909b9"
                          },
                          {
                              "name": "EPHEMERAL REGENERATION",
                              "description": "Use this Tactical Ploy when a friendly CHAOS DAEMON operative is activated. That friendly operative regains 2D3 lost wounds.",
                              "cost": 1,
                              "type": "tactical",
                              "id": "08df8906-07bc-4de5-88e9-fa1ba6728064"
                          },
                          {
                              "name": "SPLIT",
                              "description": "Use this Tactical Ploy when a friendly PINK HORROR operative is incapacitated for 2CP, or when a friendly BLUE HORROR operative is incapacitated for 1CP.",
                              "cost": 1,
                              "type": "tactical",
                              "id": "de85b3e7-e2b8-41f9-965c-523d8fe697e9",
                              "subTexts": [
                                  "Before that PINK HORROR operative is removed from the killzone, set up two BLUE HORROR operatives as close as possible to that operative and not within Engagement Range of enemy operatives.",
                                  "Before that BLUE HORROR operative is removed from the killzone, set up one BRIMSTONE HORRORS operative as close as possible to that operative and not within Engagement Range of enemy operatives.",
                                  "In either case, set up those operatives with the same order as the previous operative (including if it was ready or activated). In narrative play, any operatives set up as a result of this Tactical Ploy are no longer part of your kill team after the game."
                              ]
                          }
                      ],
                      "equipments": [
                          {
                              "name": "BRASS HORNS",
                              "description": "KHORNE operative only. The operative gains the following ability for the battle:",
                              "cost": 1,
                              "id": "203e2f55-8908-4ad0-b417-e52a6e9af992",
                              "ability": {
                                  "name": "Brass Horns",
                                  "description": "Each time this operative fights in combat, in the Roll Attack Dice step of that combat, if it performed a Charge action during that activation, you can re-roll one of your attack dice.",
                                  "id": "e57a1275-61f1-4166-b431-4a0131824524"
                              }
                          },
                          {
                              "name": "SCORCHED SKULL",
                              "description": "KHORNE operative only. The operative is equipped with the following ranged weapon for the battle:",
                              "cost": 3,
                              "id": "56ec5a73-e847-4f38-a0fa-859a626a2165",
                              "weapon": {
                                  "name": "Scorched skull",
                                  "type": "range",
                                  "attacks": 4,
                                  "ballisticWeaponSkill": 4,
                                  "damage": 4,
                                  "criticalDamage": 5,
                                  "specialRules": [
                                      {
                                          "name": "AP1",
                                          "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                      }
                                  ],
                                  "id": "d2d95364-9df2-4c86-94cf-e7420ab26d60"
                              }
                          },
                          {
                              "name": "RANCID VOMIT",
                              "description": "NURGLE operative only. The operative is equipped with the following ranged weapon for the battle:",
                              "cost": 3,
                              "id": "5ba6991a-2ef5-42cd-b1e7-9e05db77c09b",
                              "weapon": {
                                  "name": "Rancid vomit",
                                  "type": "range",
                                  "attacks": 5,
                                  "ballisticWeaponSkill": 2,
                                  "damage": 2,
                                  "criticalDamage": 3,
                                  "specialRules": [
                                      {
                                          "name": "Rng 6'",
                                          "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                      },
                                      {
                                          "name": "Torrent 1'",
                                          "description": "Each time a friendly operative performs a Shoot action or Overwatch action and selects this weapon, after making the shooting attack against the target, it can make a shooting attack with this weapon against each other valid target within x of the original target and each other, x is the distance after the weapon's Torrent, e.g. Torrent 2'."
                                      }
                                  ],
                                  "id": "7bf2f38e-3933-4116-b590-1753e26d6294"
                              }
                          },
                          {
                              "name": "DEATH'S HEADS",
                              "description": "NURGLE operative only. The operative is equipped with the following ranged weapon for the battle:",
                              "cost": 1,
                              "id": "1690a2be-8bc2-4c24-9dcb-50389422a0bb",
                              "weapon": {
                                  "name": "Death's heads",
                                  "type": "range",
                                  "attacks": 4,
                                  "ballisticWeaponSkill": 3,
                                  "damage": 3,
                                  "criticalDamage": 5,
                                  "specialRules": [
                                      {
                                          "name": "Rng 6'",
                                          "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                      }
                                  ],
                                  "id": "3ebeed2c-07ff-4e09-9dde-2fe195487af2"
                              }
                          },
                          {
                              "name": "ALLURING MUSK",
                              "description": "SLAANESH operative only. The operative is equipped with the following ranged weapon for the battle:",
                              "cost": 3,
                              "id": "cf7dd23f-aa6a-452d-a0e7-9c1d19e3cb30",
                              "weapon": {
                                  "name": "Alluring musk",
                                  "type": "range",
                                  "attacks": 5,
                                  "ballisticWeaponSkill": 2,
                                  "damage": 1,
                                  "criticalDamage": 1,
                                  "specialRules": [
                                      {
                                          "name": "Rng 6'",
                                          "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                      }
                                  ],
                                  "criticalHitSpecialRules": [
                                      {
                                          "name": "Stun",
                                          "description": "Each time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, if you retain any critical hits, subtract 1 from the target's APL. Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat:",
                                          "subText": [
                                              "The first time you strike with a critical hit, select one of your opponent's normal hits from that combat to be discarded",
                                              "The second time you strike with a critical hit, subtract 1 from the target's APL."
                                          ]
                                      }
                                  ],
                                  "id": "8852313f-946e-4198-a61c-98576c6d8ee5"
                              }
                          },
                          {
                              "name": "PIERCING CLAWS",
                              "description": "SLAANESH operative only. Select one claws the operative is equipped with. Add 1 to that weapon's Critical Damage characteristic and it gains the Rending critical hit rule for the battle.",
                              "cost": 2,
                              "id": "e5897220-c291-447b-ad99-88917d912c93"
                          },
                          {
                              "name": "RITUAL DAGGER",
                              "description": "PINK HORROR or BLUE HORROR operative only. The operative is equipped with the following melee weapon for the battle:",
                              "cost": 1,
                              "id": "0241e2ef-09f8-4d43-8bdd-fb06724558af",
                              "weapon": {
                                  "name": "Ritual dagger",
                                  "type": "range",
                                  "attacks": 3,
                                  "ballisticWeaponSkill": 4,
                                  "damage": 3,
                                  "criticalDamage": 6,
                                  "specialRules": [
                                      {
                                          "name": "Balanced",
                                          "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, you can re-roll one of your attack dice."
                                      }
                                  ],
                                  "id": "42cc2df8-665e-4de1-a631-4e1be93910b8"
                              }
                          },
                          {
                              "name": "TRINKET OF FLUX",
                              "description": "PINK HORROR or BLUE HORROR operative only. Select one coruscating flames or fizzing flames the operative is equipped with. Add 1 to that weapon's Damage characteristics for the battle.",
                              "cost": 2,
                              "id": "247685d2-2aa2-4bc3-8c91-9e945277d78d"
                          }
                      ],
                      "countEquipmentPoint": 10,
                      "id": "a40939ce-265d-4e61-820f-7abcc8613b76"
                  },
                  {
                      "factionName": "Chaos Space Marines",
                      "killTeamName": "Legionary",
                      "countOfFireTeam": 1,
                      "factionLogo": "LEGIONARY",
                      "fireTeams": [
                          {
                              "name": "Legionary",
                              "archetype": [
                                  "seekAndDestroy",
                                  "security"
                              ],
                              "availableDataslates": [
                                  {
                                      "name": "LEGIONARY WARRIOR",
                                      "description": "Heretic Astartes are post-human warriors with the natural strength, speed, resilience and mental acuity of such beings. Now they have turned against the Imperium.",
                                      "movement": 3,
                                      "actionPointLimit": 3,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 3,
                                      "wounds": 12,
                                      "selectedRangeWeapon": {
                                          "name": "Boltgun",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "id": "08b53513-55df-4025-99b0-33973bb118e3"
                                      },
                                      "keyWords": [
                                          "LEGIONARY",
                                          "CHAOS",
                                          "HERETIC ASTARTES",
                                          "<LEGION>",
                                          "<MARK OF CHAOS>",
                                          "LEGIONARY WARRIOR"
                                      ],
                                      "id": "c0ed15b9-5c1f-462f-a792-05b91435dd9d",
                                      "currentWounds": 12,
                                      "unitType": [
                                          "combat",
                                          "staunch",
                                          "marksman"
                                      ],
                                      "unitImageName": "LEGIONARY_WARRIOR",
                                      "selectedMeleeWeapon": {
                                          "name": "Fists",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "id": "8af6000f-82c0-4995-8092-9cd30aa08dad"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Bolt pistol",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  }
                                              ],
                                              "id": "009f2917-ffb4-413c-8584-fb33cf87ad1e"
                                          },
                                          {
                                              "name": "Boltgun",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "id": "08b53513-55df-4025-99b0-33973bb118e3"
                                          },
                                          {
                                              "name": "Chainsword",
                                              "type": "close",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 4,
                                              "criticalDamage": 5,
                                              "id": "eb5c2b45-37f7-4760-a26d-f1442e9d3b8f"
                                          },
                                          {
                                              "name": "Fists",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "id": "8af6000f-82c0-4995-8092-9cd30aa08dad"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "LEGIONARY GUNNER",
                                      "description": "Armed with flamers, Heretic Astartes burn through swathes of light enemy infantry. With meltaguns they destroy armoured bunkers and with plasma guns they pose a threat to the heaviest enemy troops.",
                                      "movement": 3,
                                      "actionPointLimit": 3,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 3,
                                      "wounds": 12,
                                      "selectedRangeWeapon": {
                                          "name": "Flamer",
                                          "type": "range",
                                          "attacks": 5,
                                          "ballisticWeaponSkill": 2,
                                          "damage": 2,
                                          "criticalDamage": 2,
                                          "specialRules": [
                                              {
                                                  "name": "Rng 6'",
                                                  "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                              },
                                              {
                                                  "name": "Torrent 2'",
                                                  "description": "Each time a friendly operative performs a Shoot action or Overwatch action and selects this weapon, after making the shooting attack against the target, it can make a shooting attack with this weapon against each other valid target within x of the original target and each other, x is the distance after the weapon's Torrent, e.g. Torrent 2'."
                                              }
                                          ],
                                          "id": "d1f5a857-5e58-4de1-9d72-37161de885e6"
                                      },
                                      "keyWords": [
                                          "LEGIONARY",
                                          "CHAOS",
                                          "HERETIC ASTARTES",
                                          "<LEGION>",
                                          "<MARK OF CHAOS>",
                                          "LEGIONARY GUNNER"
                                      ],
                                      "id": "72befaee-7074-4983-9d44-c00b4d6adc2c",
                                      "currentWounds": 12,
                                      "unitType": [
                                          "staunch",
                                          "marksman"
                                      ],
                                      "unitImageName": "LEGIONARY_GUNNER",
                                      "selectedMeleeWeapon": {
                                          "name": "Fists",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "id": "b0268e0b-a123-4164-baa4-49fd56e4c1b6"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Flamer",
                                              "type": "range",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 2,
                                              "criticalDamage": 2,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  },
                                                  {
                                                      "name": "Torrent 2'",
                                                      "description": "Each time a friendly operative performs a Shoot action or Overwatch action and selects this weapon, after making the shooting attack against the target, it can make a shooting attack with this weapon against each other valid target within x of the original target and each other, x is the distance after the weapon's Torrent, e.g. Torrent 2'."
                                                  }
                                              ],
                                              "id": "d1f5a857-5e58-4de1-9d72-37161de885e6"
                                          },
                                          {
                                              "name": "Meltagun",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 6,
                                              "criticalDamage": 3,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  },
                                                  {
                                                      "name": "AP2",
                                                      "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                  }
                                              ],
                                              "criticalHitSpecialRules": [
                                                  {
                                                      "name": "MW4",
                                                      "description": "Mortal wounds. Each time a friendly operative makes a shooting attack with this weapon, in the roll attack dice step of that shooting attack, for each critical hit retained, inflict x mortal wounds on the target, x is the number after the weapon's MW, e.g. MW3."
                                                  }
                                              ],
                                              "id": "beb8eadc-d803-46f3-9425-57185e12653c"
                                          },
                                          {
                                              "name": "Plasma gun",
                                              "profileName": "Standard",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 5,
                                              "criticalDamage": 6,
                                              "subProfiles": [
                                                  {
                                                      "name": "Plasma gun",
                                                      "profileName": "Supercharge",
                                                      "type": "range",
                                                      "attacks": 4,
                                                      "ballisticWeaponSkill": 3,
                                                      "damage": 5,
                                                      "criticalDamage": 6,
                                                      "specialRules": [
                                                          {
                                                              "name": "AP2",
                                                              "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                          },
                                                          {
                                                              "name": "Hot",
                                                              "description": "Each time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, for each attack dice result of 1 that is discarded, that operative suffers three mortal wounds."
                                                          }
                                                      ],
                                                      "id": "06d8dc86-01ea-41b0-8549-403581d2e81d"
                                                  }
                                              ],
                                              "specialRules": [
                                                  {
                                                      "name": "AP1",
                                                      "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                  }
                                              ],
                                              "id": "e4249c35-ad13-4237-b7bf-99ce0b3a62a1"
                                          },
                                          {
                                              "name": "Fists",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "id": "b0268e0b-a123-4164-baa4-49fd56e4c1b6"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "LEGIONARY HEAVY GUNNER",
                                      "description": "Heretic Astartes bearing heavy weapons provide devastating anti-infantry and anti-armour firepower, dominating large swathes of any killzone.",
                                      "movement": 3,
                                      "actionPointLimit": 3,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 3,
                                      "wounds": 12,
                                      "selectedRangeWeapon": {
                                          "name": "Heavy bolter",
                                          "type": "range",
                                          "attacks": 5,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 4,
                                          "criticalDamage": 5,
                                          "specialRules": [
                                              {
                                                  "name": "Heavy",
                                                  "description": "An operative cannot perform a Charge, Fall Back or Normal Move action in the same activation in which it performs a Shoot action with this ranged weapon."
                                              },
                                              {
                                                  "name": "Fusillade",
                                                  "description": "Each time a friendly operative performs a Shoot action and selects this weapon, after selecting a valid target, you can select any number of other valid targets within 2' of the original target. Distribute your attack dice between the targets you have selected. Make a shooting attack with this weapon (using the same profile) against each of the targets you have selected using the attack dice you have distributed to each of them."
                                              }
                                          ],
                                          "criticalHitSpecialRules": [
                                              {
                                                  "name": "P1",
                                                  "description": "Piercing. Each time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, if you retain any critical hits, the weapon gains the APx special rule for that shooting attack, x is the number after the weapon's P, e.g. P1."
                                              }
                                          ],
                                          "id": "d846634a-6521-43d8-8044-fdf0f9aa3394"
                                      },
                                      "keyWords": [
                                          "LEGIONARY",
                                          "CHAOS",
                                          "HERETIC ASTARTES",
                                          "<LEGION>",
                                          "<MARK OF CHAOS>",
                                          "LEGIONARY HEAVY GUNNER"
                                      ],
                                      "id": "779dd1bc-2ebc-4dfa-9ab2-8a691313ec12",
                                      "currentWounds": 12,
                                      "unitType": [
                                          "staunch",
                                          "marksman"
                                      ],
                                      "unitImageName": "LEGIONARY_HEAVY_GUNNER",
                                      "selectedMeleeWeapon": {
                                          "name": "Fists",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "id": "0e9bd20b-1332-490d-9c24-c24c84c5b9f7"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Heavy bolter",
                                              "type": "range",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 4,
                                              "criticalDamage": 5,
                                              "specialRules": [
                                                  {
                                                      "name": "Heavy",
                                                      "description": "An operative cannot perform a Charge, Fall Back or Normal Move action in the same activation in which it performs a Shoot action with this ranged weapon."
                                                  },
                                                  {
                                                      "name": "Fusillade",
                                                      "description": "Each time a friendly operative performs a Shoot action and selects this weapon, after selecting a valid target, you can select any number of other valid targets within 2' of the original target. Distribute your attack dice between the targets you have selected. Make a shooting attack with this weapon (using the same profile) against each of the targets you have selected using the attack dice you have distributed to each of them."
                                                  }
                                              ],
                                              "criticalHitSpecialRules": [
                                                  {
                                                      "name": "P1",
                                                      "description": "Piercing. Each time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, if you retain any critical hits, the weapon gains the APx special rule for that shooting attack, x is the number after the weapon's P, e.g. P1."
                                                  }
                                              ],
                                              "id": "d846634a-6521-43d8-8044-fdf0f9aa3394"
                                          },
                                          {
                                              "name": "Missile launcher",
                                              "profileName": "Frag",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 5,
                                              "subProfiles": [
                                                  {
                                                      "name": "Missile launcher",
                                                      "profileName": "Krak",
                                                      "type": "range",
                                                      "attacks": 4,
                                                      "ballisticWeaponSkill": 3,
                                                      "damage": 5,
                                                      "criticalDamage": 7,
                                                      "specialRules": [
                                                          {
                                                              "name": "AP1",
                                                              "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                          },
                                                          {
                                                              "name": "Heavy",
                                                              "description": "An operative cannot perform a Charge, Fall Back or Normal Move action in the same activation in which it performs a Shoot action with this ranged weapon."
                                                          }
                                                      ],
                                                      "id": "8b00233e-ae3a-461e-9e31-beadf3289e50"
                                                  }
                                              ],
                                              "specialRules": [
                                                  {
                                                      "name": "Blast 2'",
                                                      "description": "Each time a friendly operative performs a Shoot action and selects this weapon (or, in the case of profiles, this weapon's profile), after making the shooting attack against the target, make a shooting attack with this weapon (using the same profile) against each other operative Visible to and within X of the original target - each of them is a valid target and cannot be in Cover. X is the distance after the weapon's Blast, e.g. Blast . An operative cannot make a shooting attack with this weapon by performing an Overwatch action."
                                                  },
                                                  {
                                                      "name": "Heavy",
                                                      "description": "An operative cannot perform a Charge, Fall Back or Normal Move action in the same activation in which it performs a Shoot action with this ranged weapon."
                                                  }
                                              ],
                                              "id": "8a42a972-e8a0-4b09-875a-6d1b076c69c8"
                                          },
                                          {
                                              "name": "Reaper chaincannon",
                                              "type": "range",
                                              "attacks": 6,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 5,
                                              "specialRules": [
                                                  {
                                                      "name": "Ceaseless",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, you can re-roll any or all of your attack dice results of 1."
                                                  },
                                                  {
                                                      "name": "Fusillade",
                                                      "description": "Each time a friendly operative performs a Shoot action and selects this weapon, after selecting a valid target, you can select any number of other valid targets within 2' of the original target. Distribute your attack dice between the targets you have selected. Make a shooting attack with this weapon (using the same profile) against each of the targets you have selected using the attack dice you have distributed to each of them."
                                                  },
                                                  {
                                                      "name": "Heavy",
                                                      "description": "An operative cannot perform a Charge, Fall Back or Normal Move action in the same activation in which it performs a Shoot action with this ranged weapon."
                                                  }
                                              ],
                                              "id": "0e22d389-a0f5-4d37-ac2c-77b31d274860"
                                          },
                                          {
                                              "name": "Fists",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "id": "0e9bd20b-1332-490d-9c24-c24c84c5b9f7"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "LEGIONARY ANOINTED",
                                      "description": "Some Heretic Astartes thirst for power at any cost, and offer themselves wholly to Chaos. They become willing hosts to the immaterial creatures of the warp. This is a slow and painful process, and those in the early stages are known as Anointed due to their mutations.",
                                      "movement": 3,
                                      "actionPointLimit": 3,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 3,
                                      "wounds": 12,
                                      "selectedRangeWeapon": {
                                          "name": "Bolt pistol",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "specialRules": [
                                              {
                                                  "name": "Rng 6'",
                                                  "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                              }
                                          ],
                                          "id": "4208fd91-2d70-4a71-a13d-fdcaf93ebaf8"
                                      },
                                      "abilities": [
                                          {
                                              "name": "Unleash Daemon",
                                              "description": "Once per battle, when this operative is activated, the daemon can take control. If it does so, until the end of the battle:",
                                              "subText": [
                                                  "This operative cannot perform Overwatch, Pick Up, Shoot or mission actions.",
                                                  "Each time this operative would lose a wound, roll one D6: on a 5+, that wound is not lost.",
                                                  "Its daemonic claw gains the Ceaseless and Lethal 5+ special rules.",
                                                  "Each time this operative is activated, it can perform two Fight actions during that activation."
                                              ],
                                              "id": "0264fe3e-8bb6-4bf9-b85c-d9b4442f693b"
                                          }
                                      ],
                                      "keyWords": [
                                          "LEGIONARY",
                                          "CHAOS",
                                          "HERETIC ASTARTES",
                                          "<LEGION>",
                                          "<MARK OF CHAOS>",
                                          "LEGIONARY ANOINTED"
                                      ],
                                      "id": "2de63eb5-92c0-47f9-9667-52c48cfbe013",
                                      "currentWounds": 12,
                                      "unitType": [
                                          "combat",
                                          "staunch",
                                          "scout"
                                      ],
                                      "unitImageName": "LEGIONARY_ANOINTED",
                                      "selectedMeleeWeapon": {
                                          "name": "Daemonic claw",
                                          "type": "close",
                                          "attacks": 5,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 4,
                                          "criticalDamage": 5,
                                          "criticalHitSpecialRules": [
                                              {
                                                  "name": "Rending",
                                                  "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, if you retain any critical hits you can retain one normal hit as a critical hit."
                                              }
                                          ],
                                          "id": "b1f06dc4-309d-4a60-84f3-bc8f1c0b413c"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Bolt pistol",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  }
                                              ],
                                              "id": "4208fd91-2d70-4a71-a13d-fdcaf93ebaf8"
                                          },
                                          {
                                              "name": "Daemonic claw",
                                              "type": "close",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 4,
                                              "criticalDamage": 5,
                                              "criticalHitSpecialRules": [
                                                  {
                                                      "name": "Rending",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, if you retain any critical hits you can retain one normal hit as a critical hit."
                                                  }
                                              ],
                                              "id": "b1f06dc4-309d-4a60-84f3-bc8f1c0b413c"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "LEGIONARY BUTCHER",
                                      "description": "Bloodthirsty madmen, Butchers are Heretic Astartes that fight without subtlety of any kind in combat. They deal furious strikes with their enormous chain axes, which can carve enemies to pieces.",
                                      "movement": 3,
                                      "actionPointLimit": 3,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 3,
                                      "wounds": 12,
                                      "selectedRangeWeapon": {
                                          "name": "Bolt pistol",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "specialRules": [
                                              {
                                                  "name": "Rng 6'",
                                                  "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                              }
                                          ],
                                          "id": "d5d57eb2-307a-4884-a5ba-e05a83f3d2bb"
                                      },
                                      "abilities": [
                                          {
                                              "name": "Devastating Onslaught",
                                              "description": "",
                                              "subText": [
                                                  "Each time this operative fights in combat, enemy operatives within Engagement Range of it cannot provide combat support for that combat.",
                                                  "For the purposes of Dash, Fall Back and Normal Move actions enemy operatives would perform, treat the distance of this operative's Engagement Range as 2' for that action (instead of 1')."
                                              ],
                                              "id": "ee2fd0d5-28d7-4cf4-b48f-6675e7b962ef"
                                          }
                                      ],
                                      "keyWords": [
                                          "LEGIONARY",
                                          "CHAOS",
                                          "HERETIC ASTARTES",
                                          "<LEGION>",
                                          "<MARK OF CHAOS>",
                                          "LEGIONARY BUTCHER"
                                      ],
                                      "id": "a486dcbb-5646-48e5-afa9-2228d674e58d",
                                      "currentWounds": 12,
                                      "unitType": [
                                          "combat",
                                          "staunch"
                                      ],
                                      "unitImageName": "LEGIONARY_BUTCHER",
                                      "selectedMeleeWeapon": {
                                          "name": "Double-handed chain axe",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 4,
                                          "damage": 5,
                                          "criticalDamage": 7,
                                          "specialRules": [
                                              {
                                                  "name": "Vicious Blows",
                                                  "description": "Each time this operative fights in combat:",
                                                  "subText": [
                                                      "If this operative is the Attacker, this weapon gains the Ceaseless special rule for that combat.",
                                                      "If this operative performed a Charge action during this activation, this weapon gains the Relentless special rule for that combat."
                                                  ]
                                              }
                                          ],
                                          "criticalHitSpecialRules": [
                                              {
                                                  "name": "Reap 2",
                                                  "description": "Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat, if you strike with a critical hit, inflict x mortal wounds on each other enemy operative Visible to the friendly operative and within 1' of it or the target operative, x is the number after the weapon's Reap (e.g. Reap 1)."
                                              }
                                          ],
                                          "id": "7896b1a0-8b05-4def-bf34-045d779f866e"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Bolt pistol",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  }
                                              ],
                                              "id": "d5d57eb2-307a-4884-a5ba-e05a83f3d2bb"
                                          },
                                          {
                                              "name": "Double-handed chain axe",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 4,
                                              "damage": 5,
                                              "criticalDamage": 7,
                                              "specialRules": [
                                                  {
                                                      "name": "Vicious Blows",
                                                      "description": "Each time this operative fights in combat:",
                                                      "subText": [
                                                          "If this operative is the Attacker, this weapon gains the Ceaseless special rule for that combat.",
                                                          "If this operative performed a Charge action during this activation, this weapon gains the Relentless special rule for that combat."
                                                      ]
                                                  }
                                              ],
                                              "criticalHitSpecialRules": [
                                                  {
                                                      "name": "Reap 2",
                                                      "description": "Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat, if you strike with a critical hit, inflict x mortal wounds on each other enemy operative Visible to the friendly operative and within 1' of it or the target operative, x is the number after the weapon's Reap (e.g. Reap 1)."
                                                  }
                                              ],
                                              "id": "7896b1a0-8b05-4def-bf34-045d779f866e"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "LEGIONARY SHRIVETALON",
                                      "description": "Inflicting pain and torture becomes a near addiction for some Heretic Astartes. Those who embrace this propensity become known as Shrivetalons, and they stalk the battlefield looking for enemies to torment.",
                                      "movement": 3,
                                      "actionPointLimit": 3,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 3,
                                      "wounds": 12,
                                      "selectedRangeWeapon": {
                                          "name": "Bolt pistol",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "specialRules": [
                                              {
                                                  "name": "Rng 6'",
                                                  "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                              }
                                          ],
                                          "id": "872dc163-cd45-4ae0-8a6c-827f8f01d530"
                                      },
                                      "abilities": [
                                          {
                                              "name": "Vicious Reflexes:",
                                              "description": "Each time this operative fights in combat, in the Resolve Successful Hits step of that combat, if you are the Defender, then you start instead of the Attacker when resolving successful hits.",
                                              "id": "58720ba0-5495-4c43-8136-b7be89429a09"
                                          },
                                          {
                                              "name": "Horrifying Dismemberment:",
                                              "description": "Each time this operative fights in combat, if it incapacitates an enemy operative, select one enemy operative within 3' of it. Subtract 1 from that enemy operative's APL.",
                                              "id": "2f28d96b-e3ff-4ab0-b0f5-4e3c2492599c"
                                          }
                                      ],
                                      "uniqueActions": [
                                          {
                                              "name": "Grisly Mark",
                                              "cost": 2,
                                              "description": "Place a Grisly Mark token within 1' of this operative. This operative can only perform this action once, and cannot perform this action if it is within Engagement Range of an enemy operative.",
                                              "subText": [
                                                  "Each time an enemy operative would perform a mission action or the Pick Up action, if that enemy operative is within 3' of your Grisly Mark token, one additional action point must be subtracted to perform that action.",
                                                  "When determining control of an objective marker that Grisly Mark token is within 3' of, treat enemy operatives' total APL as being 1 less. Note that this is not a modifier."
                                              ],
                                              "id": "0e8094f9-120c-43b1-88db-238880fcba87"
                                          }
                                      ],
                                      "keyWords": [
                                          "LEGIONARY",
                                          "CHAOS",
                                          "HERETIC ASTARTES",
                                          "<LEGION>",
                                          "<MARK OF CHAOS>",
                                          "LEGIONARY SHRIVETALON"
                                      ],
                                      "id": "0168b362-0b60-4392-9b80-fc5132e74adc",
                                      "currentWounds": 12,
                                      "unitType": [
                                          "combat",
                                          "scout"
                                      ],
                                      "unitImageName": "LEGIONARY_SHRIVETALON",
                                      "selectedMeleeWeapon": {
                                          "name": "Flensing blades",
                                          "type": "close",
                                          "attacks": 5,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 5,
                                          "specialRules": [
                                              {
                                                  "name": "Lethal 5+",
                                                  "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, your attack dice results of equal to or greater than x that are successful hits are critical hits, x is the number after the weapon's Lethal, e.g. Lethal 5+."
                                              }
                                          ],
                                          "id": "3af9f54c-11bd-4149-8f8a-88c940db855e"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Bolt pistol",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  }
                                              ],
                                              "id": "872dc163-cd45-4ae0-8a6c-827f8f01d530"
                                          },
                                          {
                                              "name": "Flensing blades",
                                              "type": "close",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 5,
                                              "specialRules": [
                                                  {
                                                      "name": "Lethal 5+",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, your attack dice results of equal to or greater than x that are successful hits are critical hits, x is the number after the weapon's Lethal, e.g. Lethal 5+."
                                                  }
                                              ],
                                              "id": "3af9f54c-11bd-4149-8f8a-88c940db855e"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "LEGIONARY ICON BEARER",
                                      "description": "Many Heretic Astartes kill teams include Icon Bearers - warriors bearing totems, banners or standards dedicated to the glory of the Dark Gods.",
                                      "movement": 3,
                                      "actionPointLimit": 3,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 3,
                                      "wounds": 12,
                                      "selectedRangeWeapon": {
                                          "name": "Boltgun",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "id": "b7115821-794d-40d1-b5bf-675fb52cdf66"
                                      },
                                      "abilities": [
                                          {
                                              "name": "Icon Bearer",
                                              "description": "When determining control of an objective marker, treat this operative's APL characteristic as being 1 higher. Note that this is not a modifier. In narrative play, this is cumulative with the Focused Battle Honour (see the Kill Team Core Book).",
                                              "id": "6733adad-8432-4e17-b12d-fd901ab4d102"
                                          },
                                          {
                                              "name": "Favoured of the Dark Gods",
                                              "description": "Once per Turning Point, when it is your turn to use a Strategic Ploy, if any friendly operatives with this ability are in the killzone, you can use a Strategic Ploy without spending any CPs; that Strategic Ploy must have the same <MARK OF CHAOS> selectable keyword as one friendly operative with this ability. For example, if an operative with this ability has the KHORNE keyword, you could use the Blood for the Blood God Strategic Ploy. If an operative with this ability has the UNDIVIDED keyword, you can use the following Strategic Ploys for this ability instead: Hateful Assault, Malicious Volleys.",
                                              "id": "aa116747-0a24-4d64-9a17-ea8f7ad91da6"
                                          }
                                      ],
                                      "keyWords": [
                                          "LEGIONARY",
                                          "CHAOS",
                                          "HERETIC ASTARTES",
                                          "<LEGION>",
                                          "<MARK OF CHAOS>",
                                          "LEGIONARY ICON BEARER"
                                      ],
                                      "id": "9627a8b0-7b0d-4cf6-a65a-ff022db47913",
                                      "currentWounds": 12,
                                      "unitType": [
                                          "combat",
                                          "staunch",
                                          "marksman"
                                      ],
                                      "unitImageName": "LEGIONARY_ICON_BEARER",
                                      "selectedMeleeWeapon": {
                                          "name": "Fists",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "id": "7123d727-d8ba-4b23-bc9a-48df5eba8148"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Bolt pistol",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  }
                                              ],
                                              "id": "7e27068d-0110-4867-87e2-fc4e15e5bf43"
                                          },
                                          {
                                              "name": "Boltgun",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "id": "b7115821-794d-40d1-b5bf-675fb52cdf66"
                                          },
                                          {
                                              "name": "Chainsword",
                                              "type": "close",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 4,
                                              "criticalDamage": 5,
                                              "id": "3a8c6be1-37b5-4831-b847-13183c8642da"
                                          },
                                          {
                                              "name": "Fists",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "id": "7123d727-d8ba-4b23-bc9a-48df5eba8148"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "LEGIONARY BALEFIRE ACOLYTE",
                                      "description": "Balefire Acolytes are psykers blessed by the Chaos Gods with the dark power of the empyrean, which they turn indiscriminately upon the foe. Many also carry blades made even deadlier thanks to the power of the warp they are infused with.",
                                      "movement": 3,
                                      "actionPointLimit": 3,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 3,
                                      "wounds": 12,
                                      "selectedRangeWeapon": {
                                          "name": "Tainted bolt pistol",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "specialRules": [
                                              {
                                                  "name": "Rng 6'",
                                                  "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                              },
                                              {
                                                  "name": "Balanced",
                                                  "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, you can re-roll one of your attack dice."
                                              }
                                          ],
                                          "id": "d79c7e82-39da-4237-8af2-e40a1ca9384a"
                                      },
                                      "abilities": [
                                          {
                                              "name": "Hatred of Sorcery",
                                              "description": "This operative's <MARK OF CHAOS> keyword cannot be replaced with KHORNE.",
                                              "id": "c0debccf-8e60-43be-adec-00a23384ee62"
                                          }
                                      ],
                                      "keyWords": [
                                          "LEGIONARY",
                                          "CHAOS",
                                          "HERETIC ASTARTES",
                                          "<LEGION>",
                                          "<MARK OF CHAOS>",
                                          "PSYKER",
                                          "LEGIONARY BALEFIRE ACOLYTE"
                                      ],
                                      "id": "ca9b3328-742a-429a-8228-e295900219e1",
                                      "currentWounds": 12,
                                      "unitType": [
                                          "combat",
                                          "staunch",
                                          "marksman"
                                      ],
                                      "unitImageName": "LEGIONARY_BALEFIRE_ACOLYTE",
                                      "selectedMeleeWeapon": {
                                          "name": "Fell dagger",
                                          "type": "close",
                                          "attacks": 5,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "specialRules": [
                                              {
                                                  "name": "Daemonic Energies",
                                                  "description": "Each time this operative fights in combat, in the Roll Attack Dice step of that combat, each time you retain a critical hit, the target suffers 2 mortal wounds."
                                              }
                                          ],
                                          "id": "e97adb8e-31fc-4416-8140-7cb6f878e721"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Tainted bolt pistol",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  },
                                                  {
                                                      "name": "Balanced",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, you can re-roll one of your attack dice."
                                                  }
                                              ],
                                              "id": "d79c7e82-39da-4237-8af2-e40a1ca9384a"
                                          },
                                          {
                                              "name": "Fell dagger",
                                              "type": "close",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "specialRules": [
                                                  {
                                                      "name": "Daemonic Energies",
                                                      "description": "Each time this operative fights in combat, in the Roll Attack Dice step of that combat, each time you retain a critical hit, the target suffers 2 mortal wounds."
                                                  }
                                              ],
                                              "id": "e97adb8e-31fc-4416-8140-7cb6f878e721"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "LEGIONARY CHOSEN",
                                      "description": "Chosen are amongst the most experienced and dedicated Heretic Astartes. They are favoured within their bitter brotherhoods, wearing baroque armour and equipped with the finest wargear. They are more hard-bitten and callous than even others of their kind.",
                                      "movement": 3,
                                      "actionPointLimit": 3,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 3,
                                      "wounds": 13,
                                      "selectedRangeWeapon": {
                                          "name": "Tainted bolt pistol",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "specialRules": [
                                              {
                                                  "name": "Rng 6'",
                                                  "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                              },
                                              {
                                                  "name": "Balanced",
                                                  "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, you can re-roll one of your attack dice."
                                              }
                                          ],
                                          "id": "ada3784a-8410-431a-8eba-4ddcddd8279a"
                                      },
                                      "abilities": [
                                          {
                                              "name": "Favoured of the Dark Gods",
                                              "description": "Once per Turning Point, when it is your turn to use a Strategic Ploy, if any friendly operatives with this ability are in the killzone, you can use a Strategic Ploy without spending any CPs; that Strategic Ploy must have the same <MARK OF CHAOS> selectable keyword as one friendly operative with this ability. For example, if an operative with this ability has the KHORNE keyword, you could use the Blood for the Blood God Strategic Ploy. If an operative with this ability has the UNDIVIDED keyword, you can use the following Strategic Ploys for this ability instead: Hateful Assault, Malicious Volleys.",
                                              "id": "2933ba26-9bfd-4ad9-a91c-312a02c86b5b"
                                          },
                                          {
                                              "name": "Daemonic Aura",
                                              "description": "Each time an enemy operative starts a Fall Back action while within Engagement Range of this operative, reduce the distance that enemy operative can move by 4' (to a minimum of 2').",
                                              "id": "4079296d-9fcc-407f-a3b9-288e1fbf66b3"
                                          },
                                          {
                                              "name": "Soul Feast",
                                              "description": "Each time this operative fights in combat, in the Resolve Successful Hits step of that combat, if any of their strikes inflict Critical Damage, this operative regains 2 lost wounds.",
                                              "id": "df941761-5c4c-4563-9745-8e3e17a169ff"
                                          }
                                      ],
                                      "keyWords": [
                                          "LEGIONARY",
                                          "CHAOS",
                                          "HERETIC ASTARTES",
                                          "<LEGION>",
                                          "<MARK OF CHAOS>",
                                          "LEADER",
                                          "LEGIONARY CHOSEN"
                                      ],
                                      "id": "3af703de-7e7d-4dca-971b-a70ee3a3076b",
                                      "currentWounds": 13,
                                      "unitType": [
                                          "combat",
                                          "staunch",
                                          "marksman"
                                      ],
                                      "unitImageName": "LEGIONARY_CHOSEN",
                                      "selectedMeleeWeapon": {
                                          "name": "Daemon blade",
                                          "type": "close",
                                          "attacks": 5,
                                          "ballisticWeaponSkill": 2,
                                          "damage": 4,
                                          "criticalDamage": 7,
                                          "specialRules": [
                                              {
                                                  "name": "Lethal 5+",
                                                  "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, your attack dice results of equal to or greater than x that are successful hits are critical hits, x is the number after the weapon's Lethal, e.g. Lethal 5+."
                                              }
                                          ],
                                          "id": "7adbaf19-f737-465c-87ad-72024ed8bf18"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Plasma pistol",
                                              "type": "range",
                                              "profileName": "Standard",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 5,
                                              "criticalDamage": 6,
                                              "subProfiles": [
                                                  {
                                                      "name": "Plasma pistol",
                                                      "type": "range",
                                                      "profileName": "Supercharge",
                                                      "attacks": 4,
                                                      "ballisticWeaponSkill": 3,
                                                      "damage": 5,
                                                      "criticalDamage": 6,
                                                      "specialRules": [
                                                          {
                                                              "name": "Rng 6'",
                                                              "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                          },
                                                          {
                                                              "name": "AP2",
                                                              "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                          },
                                                          {
                                                              "name": "Hot",
                                                              "description": "Each time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, for each attack dice result of 1 that is discarded, that operative suffers three mortal wounds."
                                                          }
                                                      ],
                                                      "id": "7256da8c-360a-428e-bb2e-886fd6cb531c"
                                                  }
                                              ],
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  },
                                                  {
                                                      "name": "AP1",
                                                      "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                  }
                                              ],
                                              "id": "5521ec2e-66f5-4564-8488-b6c331724428"
                                          },
                                          {
                                              "name": "Tainted bolt pistol",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  },
                                                  {
                                                      "name": "Balanced",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, you can re-roll one of your attack dice."
                                                  }
                                              ],
                                              "id": "ada3784a-8410-431a-8eba-4ddcddd8279a"
                                          },
                                          {
                                              "name": "Daemon blade",
                                              "type": "close",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 4,
                                              "criticalDamage": 7,
                                              "specialRules": [
                                                  {
                                                      "name": "Lethal 5+",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, your attack dice results of equal to or greater than x that are successful hits are critical hits, x is the number after the weapon's Lethal, e.g. Lethal 5+."
                                                  }
                                              ],
                                              "id": "7adbaf19-f737-465c-87ad-72024ed8bf18"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "LEGIONARY ASPIRING CHAMPION",
                                      "description": "Aspiring Champions are the strongest and most merciless of their brothers. These blood-soaked warriors enforce their will through brutal acts of might, seeking to become favoured of the gods.",
                                      "movement": 3,
                                      "actionPointLimit": 3,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 3,
                                      "wounds": 13,
                                      "selectedRangeWeapon": {
                                          "name": "Tainted bolt pistol",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 2,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "specialRules": [
                                              {
                                                  "name": "Rng 6'",
                                                  "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                              },
                                              {
                                                  "name": "Balanced",
                                                  "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, you can re-roll one of your attack dice."
                                              }
                                          ],
                                          "id": "c099ecf1-2cde-4d31-9d9e-a6aafe50c47c"
                                      },
                                      "abilities": [
                                          {
                                              "name": "Favoured of the Dark Gods",
                                              "description": "Once per Turning Point, when it is your turn to use a Strategic Ploy, if any friendly operatives with this ability are in the killzone, you can use a Strategic Ploy without spending any CPs; that Strategic Ploy must have the same <MARK OF CHAOS> selectable keyword as one friendly operative with this ability. For example, if an operative with this ability has the KHORNE keyword, you could use the Blood for the Blood God Strategic Ploy. If an operative with this ability has the UNDIVIDED keyword, you can use the following Strategic Ploys for this ability instead: Hateful Assault, Malicious Volleys.",
                                              "id": "a0c09df5-0b0a-4b98-8842-277d26707f7e"
                                          },
                                          {
                                              "name": "In the Eyes of the Gods",
                                              "description": "Once per Turning Point, when this operative incapacitates an enemy operative, you can add 1 to this operative's APL.",
                                              "id": "c4f9f768-9041-47e0-91d8-8ae9f6ef51c7"
                                          }
                                      ],
                                      "keyWords": [
                                          "LEGIONARY",
                                          "CHAOS",
                                          "HERETIC ASTARTES",
                                          "<LEGION>",
                                          "<MARK OF CHAOS>",
                                          "LEADER",
                                          "LEGIONARY ASPIRING CHAMPION"
                                      ],
                                      "id": "d7f15a1c-8650-4afe-af63-97881634b86d",
                                      "currentWounds": 13,
                                      "unitType": [
                                          "combat",
                                          "staunch",
                                          "marksman"
                                      ],
                                      "unitImageName": "LEGIONARY_ASPIRING_CHAMPION",
                                      "selectedMeleeWeapon": {
                                          "name": "Tainted chainsword",
                                          "type": "close",
                                          "attacks": 5,
                                          "ballisticWeaponSkill": 2,
                                          "damage": 4,
                                          "criticalDamage": 5,
                                          "specialRules": [
                                              {
                                                  "name": "Parry Hook",
                                                  "description": "Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat, each time you parry with a normal hit, you can select one of your opponent's critical hits to be discarded instead."
                                              }
                                          ],
                                          "id": "8eca42d2-c9f3-4a44-8816-b75c6b7650f2"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Plasma pistol",
                                              "type": "range",
                                              "profileName": "Standard",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 5,
                                              "criticalDamage": 6,
                                              "subProfiles": [
                                                  {
                                                      "name": "Plasma pistol",
                                                      "type": "range",
                                                      "profileName": "Supercharge",
                                                      "attacks": 4,
                                                      "ballisticWeaponSkill": 2,
                                                      "damage": 5,
                                                      "criticalDamage": 6,
                                                      "specialRules": [
                                                          {
                                                              "name": "Rng 6'",
                                                              "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                          },
                                                          {
                                                              "name": "AP2",
                                                              "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                          },
                                                          {
                                                              "name": "Hot",
                                                              "description": "Each time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, for each attack dice result of 1 that is discarded, that operative suffers three mortal wounds."
                                                          }
                                                      ],
                                                      "id": "c4b90853-f5de-448d-b067-331573d5ffa9"
                                                  }
                                              ],
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  },
                                                  {
                                                      "name": "AP1",
                                                      "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                  }
                                              ],
                                              "id": "ede79e9a-b49e-4ae6-b108-8fce4d4f4a3a"
                                          },
                                          {
                                              "name": "Tainted bolt pistol",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  },
                                                  {
                                                      "name": "Balanced",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, you can re-roll one of your attack dice."
                                                  }
                                              ],
                                              "id": "c099ecf1-2cde-4d31-9d9e-a6aafe50c47c"
                                          },
                                          {
                                              "name": "Power fist",
                                              "type": "close",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 5,
                                              "criticalDamage": 7,
                                              "specialRules": [
                                                  {
                                                      "name": "Brutal",
                                                      "description": "Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat, your opponent can only parry with critical hits."
                                                  }
                                              ],
                                              "id": "2f09d552-e5f9-48f3-bee9-d9953977fb42"
                                          },
                                          {
                                              "name": "Power maul",
                                              "type": "close",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 4,
                                              "criticalDamage": 6,
                                              "criticalHitSpecialRules": [
                                                  {
                                                      "name": "Stun",
                                                      "description": "Each time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, if you retain any critical hits, subtract 1 from the target's APL. Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat:",
                                                      "subText": [
                                                          "The first time you strike with a critical hit, select one of your opponent's normal hits from that combat to be discarded",
                                                          "The second time you strike with a critical hit, subtract 1 from the target's APL."
                                                      ]
                                                  }
                                              ],
                                              "id": "9f52563a-102a-4109-ab65-685729b0a68a"
                                          },
                                          {
                                              "name": "Power weapon",
                                              "type": "close",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 4,
                                              "criticalDamage": 6,
                                              "specialRules": [
                                                  {
                                                      "name": "Lethal 5+",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, your attack dice results of equal to or greater than x that are successful hits are critical hits, x is the number after the weapon's Lethal, e.g. Lethal 5+."
                                                  }
                                              ],
                                              "id": "aa0b9e37-b462-45bd-8d67-56547edb5d39"
                                          },
                                          {
                                              "name": "Tainted chainsword",
                                              "type": "close",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 4,
                                              "criticalDamage": 5,
                                              "specialRules": [
                                                  {
                                                      "name": "Parry Hook",
                                                      "description": "Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat, each time you parry with a normal hit, you can select one of your opponent's critical hits to be discarded instead."
                                                  }
                                              ],
                                              "id": "8eca42d2-c9f3-4a44-8816-b75c6b7650f2"
                                          }
                                      ]
                                  }
                              ],
                              "currentDataslates": [],
                              "id": "a4c0a641-8a7e-443e-a611-0fddd84ec07a",
                              "defaultDataslates": {
                                  "3af703de-7e7d-4dca-971b-a70ee3a3076b": 1,
                                  "c0ed15b9-5c1f-462f-a792-05b91435dd9d": 5
                              }
                          }
                      ],
                      "chosenFireTeams": [],
                      "ploys": [
                          {
                              "name": "HATEFUL ASSAULT",
                              "description": "Until the end of the Turning Point, each time a friendly LEGIONARY operative is activated, if it does not perform a Shoot action during that activation, it can perform two Fight actions during that activation.",
                              "cost": 1,
                              "type": "strategic",
                              "id": "a5d60617-e533-4874-807a-f99089dd549c"
                          },
                          {
                              "name": "MALICIOUS VOLLEYS",
                              "description": "Until the end of the Turning Point, each time a friendly LEGIONARY operative is activated, if it does not perform a Fight action during that activation, it can perform two Shoot actions during that activation if a bolt weapon is selected for each of the shooting attacks. A bolt weapon is a ranged weapon that includes the word 'bolt' in its name e.g. boltgun, heavy bolter etc.",
                              "cost": 1,
                              "type": "strategic",
                              "id": "54d598d2-ccef-427c-800b-faa61cc1dbc0"
                          },
                          {
                              "name": "BLOOD FOR THE BLOOD GOD",
                              "description": "Until the end of the Turning Point, each time a friendly KHORNE operative fights in combat, in the Resolve Successful Hits step of that combat, if it performed a Charge action during that activation, the first time it strikes, inflict one additional damage.",
                              "cost": 1,
                              "type": "strategic",
                              "id": "6fbd0c3f-48be-4dfa-bdc7-a14b6084b0bd"
                          },
                          {
                              "name": "PERPETUAL AGGRESSION",
                              "description": "Until the end of the Turning Point, each time after a friendly KHORNE operative fights in combat, if it is not within Engagement Range of an enemy operative, it can make a move following the same rules as a Normal Move action, with the following exceptions:",
                              "cost": 1,
                              "type": "strategic",
                              "id": "8db52bc8-c8f7-4d8b-b5c4-f032139cf019",
                              "subTexts": [
                                  "It can only move up to 3'.",
                                  "It can move within Engagement Range of enemy operatives.",
                                  "If it can, it must finish the move within Engagement Range of the closest Visible enemy operative."
                              ]
                          },
                          {
                              "name": "MUTAGENIC FLESH",
                              "description": "Until the end of the Turning Point, each time Normal Damage would be inflicted upon a friendly NURGLE operative from an attack dice, subtract 1 from the damage inflicted from that attack dice (to a minimum of 2).",
                              "cost": 1,
                              "type": "strategic",
                              "id": "6cd28de0-e2f7-4ad3-aee1-7430405fd580"
                          },
                          {
                              "name": "IMPLACABLE",
                              "description": "Until the end of the Turning Point, friendly NURGLE operatives:",
                              "cost": 1,
                              "type": "strategic",
                              "id": "a6b03095-d2b8-495f-819e-ac72d963e697",
                              "subTexts": [
                                  "Are not treated as being injured.",
                                  "Ignore all negative modifiers to their APL.",
                                  "Ignore the worsening of their Ballistic Skill when performing an Overwatch action."
                              ]
                          },
                          {
                              "name": "GRACEFUL KILLER",
                              "description": "Until the end of the Turning Point, add 1 to the Critical Damage characteristic of friendly SLAANESH operatives' melee weapons.",
                              "cost": 1,
                              "type": "strategic",
                              "id": "a415f2c4-a7f6-4f90-b66d-d604bef79d26"
                          },
                          {
                              "name": "DELICIOUS AGONY",
                              "description": "Until the end of the Turning Point, each time a friendly SLAANESH operative fights in combat, in the Resolve Successful Hits step of that combat, when you would resolve your first successful hit, if the target is injured, you can resolve two of your successful hits (instead of one).",
                              "cost": 1,
                              "type": "strategic",
                              "id": "eb6d895a-5e14-4a93-9b75-41ac7e87f1f6"
                          },
                          {
                              "name": "PROTECTED BY FATE",
                              "description": "Until the end of the Turning Point, each time a shooting attack is made against a friendly TZEENTCH operative, in the Roll Defence Dice step of that shooting attack, if you retain any critical saves, you can select one of your failed saves to be retained as a successful normal save.",
                              "cost": 1,
                              "type": "strategic",
                              "id": "0b0849f1-d396-4407-9b6d-f74143d0249f"
                          },
                          {
                              "name": "AETHERIC WARD",
                              "description": "Until the end of the Turning Point, friendly TZEENTCH operatives have a 4+ invulnerable save.",
                              "cost": 1,
                              "type": "strategic",
                              "id": "21c796d8-ccb4-43e6-b531-3569e3040e69"
                          },
                          {
                              "name": "VETERAN OF THE LONG WAR",
                              "description": "Use this Tactical Ploy during a friendly LEGIONARY operative's activation, after it fights in combat or makes a shooting attack. If the target did not lose any wounds as a result of that combat or shooting attack, repeat that combat or shooting attack.",
                              "cost": 1,
                              "type": "tactical",
                              "id": "f5ef0494-38ec-4ca2-a765-645250d65397"
                          },
                          {
                              "name": "UNENDING BLOODSHED",
                              "description": "Use this Tactical Ploy when a ready friendly KHORNE operative is incapacitated in combat by an enemy operative. Before that friendly operative is removed from the killzone, select one melee weapon it is equipped with and roll one attack dice as if it is the attacker fighting in combat. If the result is a successful hit, you can immediately strike an enemy operative within Engagement Range of it. Then remove that friendly operative from the killzone as normal.",
                              "cost": 1,
                              "type": "tactical",
                              "id": "8ecbc655-bfe5-4374-a2f6-b48f72a5fedf"
                          },
                          {
                              "name": "MUTABILITY AND CHANGE",
                              "description": "Use this Tactical Ploy during a friendly TZEENTCH operative's activation. Add 1 to that operative's APL.",
                              "cost": 1,
                              "type": "tactical",
                              "id": "92658dc5-c134-4809-9404-16aff799848a"
                          },
                          {
                              "name": "MALIGNANT AURA",
                              "description": "Use this Tactical Ploy during a friendly NURGLE operative's activation. Until the end of the Turning Point, while an enemy operative is within 3' of that NURGLE operative, subtract 1 from the Defence characteristic of that enemy operative.",
                              "cost": 1,
                              "type": "tactical",
                              "id": "43a6b4f3-51f7-44ef-9d14-8faeaa031eb2"
                          },
                          {
                              "name": "SICKENING CAPTIVATION",
                              "description": "Use this Tactical Ploy during a friendly SLAANESH operative's activation. Select one enemy operative Visible to and within 3' of that SLAANESH operative. Subtract 1 from that enemy operative's APL.",
                              "cost": 1,
                              "type": "tactical",
                              "id": "cc7081c9-481b-48b1-8065-7d202ff4b6c1"
                          }
                      ],
                      "equipments": [
                          {
                              "name": "AGGRESSION STIMULANTS",
                              "description": "The operative gains the following ability for the battle:",
                              "cost": 3,
                              "maxNumberPerKillTeam": 1,
                              "id": "88089383-ad7d-457d-87c0-2faf964eed88",
                              "ability": {
                                  "name": "Aggression Stimulants",
                                  "description": "Each time this operative fights in combat, in the Roll Attack Dice step of that combat, if this operative performed a Charge action during this Turning Point, you can re-roll one of your attack dice.",
                                  "id": "43383f72-dff9-4b0d-be9a-fd32304767fc"
                              }
                          },
                          {
                              "name": "FRAG GRENADE",
                              "description": "The operative is equipped with the following ranged weapon for the battle:",
                              "cost": 2,
                              "id": "ec85acc8-7ea9-48e5-8cd8-2d947e2f8b87",
                              "weapon": {
                                  "name": "Frag grenade",
                                  "type": "range",
                                  "attacks": 4,
                                  "ballisticWeaponSkill": 3,
                                  "damage": 2,
                                  "criticalDamage": 3,
                                  "specialRules": [
                                      {
                                          "name": "Rng 6'",
                                          "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                      },
                                      {
                                          "name": "Blast 2'",
                                          "description": "Each time a friendly operative performs a Shoot action and selects this weapon (or, in the case of profiles, this weapon's profile), after making the shooting attack against the target, make a shooting attack with this weapon (using the same profile) against each other operative Visible to and within X of the original target - each of them is a valid target and cannot be in Cover. X is the distance after the weapon's Blast, e.g. Blast 2'. An operative cannot make a shooting attack with this weapon by performing an Overwatch action."
                                      },
                                      {
                                          "name": "Indirect",
                                          "description": "Each time a friendly operative makes a shooting attack with this weapon, in the select valid target step of that shooting attack, enemy operatives are not in Cover."
                                      },
                                      {
                                          "name": "Limited",
                                          "description": "This weapon can only be selected for use once per battle. If the weapon has a special rule that would allow it to make more than one shooting attack for an action (e.g. Blast), make each of those attacks as normal."
                                      }
                                  ],
                                  "id": "3cfa87ed-b583-45d9-8a1f-837a5e85e873"
                              }
                          },
                          {
                              "name": "KRAK GRENADE",
                              "description": "The operative is equipped with the following ranged weapon for the battle. It cannot make a shooting attack with this weapon by performing an Overwatch action:",
                              "cost": 3,
                              "id": "bcfaf19f-86ea-4db8-a120-8d1ef7a3138b",
                              "weapon": {
                                  "name": "Krak grenade",
                                  "type": "range",
                                  "attacks": 4,
                                  "ballisticWeaponSkill": 3,
                                  "damage": 4,
                                  "criticalDamage": 5,
                                  "specialRules": [
                                      {
                                          "name": "Rng 6'",
                                          "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                      },
                                      {
                                          "name": "AP1",
                                          "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                      },
                                      {
                                          "name": "Indirect",
                                          "description": "Each time a friendly operative makes a shooting attack with this weapon, in the select valid target step of that shooting attack, enemy operatives are not in Cover."
                                      },
                                      {
                                          "name": "Limited",
                                          "description": "This weapon can only be selected for use once per battle. If the weapon has a special rule that would allow it to make more than one shooting attack for an action (e.g. Blast), make each of those attacks as normal."
                                      }
                                  ],
                                  "id": "2c1da5a7-7512-4993-aa95-5f2c7255e10a"
                              }
                          },
                          {
                              "name": "WARDED ARMOUR",
                              "description": "Change the operative's Save characteristic to 2+ for the battle. In the Resolve Successful Hits step of a combat or shooting attack, if an attack dice inflicts damage on this operative, its Save characteristic is changed back to its normal characteristic for the rest of the battle.",
                              "cost": 3,
                              "maxNumberPerKillTeam": 1,
                              "id": "e54e4bb5-e3da-4dd6-8350-511782df10af"
                          },
                          {
                              "name": "SUSPENSOR SYSTEM",
                              "description": "The operative gains the following ability for the battle:",
                              "cost": 3,
                              "id": "85b33900-b49f-47cb-b62b-0ffdee20fcdc",
                              "ability": {
                                  "name": "Suspensor System",
                                  "description": "The Heavy special rule of ranged weapons the operative is equipped with is treated differently. Instead, an operative cannot move more than 6' in the same activation in which it performs a Shoot action with any of those ranged weapons.",
                                  "id": "fd4c2612-259a-440c-9de2-fcca33769e75"
                              }
                          },
                          {
                              "name": "MALIGN SCRIPTURE",
                              "description": "LEGIONARY BALEFIRE ACOLYTE operative only. The operative gains the following ability for the battle:",
                              "cost": 2,
                              "id": "6817cae4-3f2d-4053-ab21-b6b290f84309",
                              "ability": {
                                  "name": "Malign Scripture",
                                  "description": "Once per battle, this operative can perform two Manifest Psychic Power actions during its activation.",
                                  "id": "2da096af-aca7-4692-8b74-379a5e88be94"
                              }
                          },
                          {
                              "name": "TAINTED ROUNDS",
                              "description": "Select one boltgun, bolt pistol or tainted bolt pistol the operative is equipped with. Add 1 to both Damage characteristics of that weapon.",
                              "cost": 3,
                              "maxNumberPerKillTeam": 1,
                              "id": "954e9d68-9fcf-439d-9cc4-6c67bbfd7f86"
                          },
                          {
                              "name": "GRISLY TROPHY",
                              "description": "The operative gains the following ability for the battle:",
                              "cost": 3,
                              "maxNumberPerKillTeam": 1,
                              "id": "859091db-5e7b-47fe-96e6-acc0b483518d",
                              "ability": {
                                  "name": "Grisly Trophy",
                                  "description": "While this operative is Visible to and within 3' of an enemy operative, subtract 1 from the Attacks characteristic of ranged and melee weapons that enemy operative is equipped with.",
                                  "id": "91d2fe2b-1548-43d3-b4a5-5bd24a0e3f35"
                              }
                          },
                          {
                              "name": "MALEFIC BLADE",
                              "description": "The operative is equipped with the following melee weapon for the battle:",
                              "cost": 2,
                              "id": "6175ad73-0d66-4085-94c4-4a7cc399f4ad",
                              "weapon": {
                                  "name": "Malefic Blade",
                                  "type": "close",
                                  "attacks": 5,
                                  "ballisticWeaponSkill": 3,
                                  "damage": 3,
                                  "criticalDamage": 5,
                                  "id": "c69e5849-627b-4b9a-a84e-8dd5d945b54f"
                              }
                          }
                      ],
                      "countEquipmentPoint": 10,
                      "tacOps": [
                          {
                              "name": "SACRILEGIOUS MUTILATION",
                              "type": "factionTacOp",
                              "description": "Reveal this Tac Op the first time an enemy operative is incapacitated. Each time an enemy operative is incapacitated, before it is removed from the killzone, place one of your Enemy Corpse tokens underneath the operative as close as possible to the centre of its base.",
                              "subText": "Friendly operatives can perform the following mission action:",
                              "firstCondition": "The second time a friendly operative performs the Defiled For The Dark Gods action, you score 1VP.",
                              "secondCondition": "The fourth time a friendly operative performs the Defiled For The Dark Gods action, you score 1VP.",
                              "victoryPoint": [
                                  1,
                                  1
                              ],
                              "id": "a177558e-f9c3-436f-ba52-0d82d8aae88c",
                              "uniqueAction": {
                                  "name": "DEFILED FOR THE DARK GODS",
                                  "cost": 1,
                                  "description": "An operative can perform this action while within 1' of one of your Enemy Corpse tokens. An operative cannot perform this action while within 2' of an enemy operative. Remove that Enemy Corpse token from the killzone.",
                                  "id": "b79e3957-9c9c-4747-94f8-2936a1ca1a59"
                              },
                              "isCompletedConditions": []
                          },
                          {
                              "name": "DARK DESECRATION",
                              "type": "factionTacOp",
                              "description": "Reveal this Tac Op in the Target Reveal step of the first Turning Point. Select one terrain feature that includes any parts with the Heavy trait.",
                              "firstCondition": "If two or more enemy operatives are incapacitated while within 1' of that terrain feature, you score 1 VP.",
                              "secondCondition": "If you achieve the first condition and at the end of the battle the total APL of friendly operatives within 1' of that terrain feature is greater than that of enemy operatives, you score 1VP.",
                              "victoryPoint": [
                                  1,
                                  1
                              ],
                              "id": "6e1b6a9d-415a-4d8f-ad79-666a93051d0b",
                              "isCompletedConditions": []
                          },
                          {
                              "name": "SAVAGE BUTCHER",
                              "type": "factionTacOp",
                              "description": "You can reveal this Tac Op in the Target Reveal step of any Turning Point. Select one friendly operative.",
                              "firstCondition": "If two or more enemy operatives are incapacitated in combat by that friendly operative, you score 1VP.",
                              "secondCondition": "If three or more enemy operatives are incapacitated in combat by that friendly operative, you score 1VP.",
                              "victoryPoint": [
                                  1,
                                  1
                              ],
                              "id": "ec9e15ca-9a28-43fa-94ee-fca335347a76",
                              "isCompletedConditions": []
                          }
                      ],
                      "psychicPowerDescriptions": [
                          "The first time a friendly LEGIONARY PSYKER operative performs the Manifest Psychic Power action in each of its activations, select one psychic power from the list below to be resolved.",
                          "If a friendly LEGIONARY PSYKER operative can perform the Manifest Psychic Power action twice in its activation (e.g. it is equipped with the Pages of Scabrius), when it does so, roll one D6: on a 1-2, that friendly operative suffers 3 mortal wounds; on a 3+, select one psychic power from the list below to be resolved. You cannot select a psychic power that you have already resolved during this Turning Point."
                      ],
                      "psychicPowers": [
                          {
                              "name": "FIREBLAST",
                              "description": "Perform a free Shoot action using the following ranged weapon:",
                              "weapon": {
                                  "name": "Fireblast",
                                  "type": "range",
                                  "attacks": 4,
                                  "ballisticWeaponSkill": 3,
                                  "damage": 3,
                                  "criticalDamage": 4,
                                  "specialRules": [
                                      {
                                          "name": "No Cover",
                                          "description": "Each time a friendly operative makes a shooting attack with this weapon, for that shooting attack, defence dice cannot be automatically retained as a result of Cover (they must be rolled instead)."
                                      },
                                      {
                                          "name": "Blast 2'",
                                          "description": "Each time a friendly operative performs a Shoot action and selects this weapon (or, in the case of profiles, this weapon's profile), after making the shooting attack against the target, make a shooting attack with this weapon (using the same profile) against each other operative Visible to and within X of the original target - each of them is a valid target and cannot be in Cover. X is the distance after the weapon's Blast, e.g. Blast 2'. An operative cannot make a shooting attack with this weapon by performing an Overwatch action."
                                      }
                                  ],
                                  "criticalHitSpecialRules": [
                                      {
                                          "name": "Splash 1",
                                          "description": "Each time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, for each critical hit retained, inflict x mortal wounds on the target and each other operative Visible to and within 2' of it. x is the number after the weapon's Splash, e.g. Splash 1."
                                      }
                                  ],
                                  "id": "99853737-470e-481b-8281-e8c09c400ddf"
                              },
                              "id": "753a56e3-1c1f-42b4-9017-517f7d3a9f97"
                          },
                          {
                              "name": "MALIGN INFLUENCE",
                              "description": "Select one friendly LEGIONARY operative Visible to this operative. Until the end of the Turning Point, weapons that operative is equipped with gain the Lethal 5+, No Cover and Brutal special rules.",
                              "id": "9fb62d9b-8aef-475f-a9a2-573587f45881"
                          },
                          {
                              "name": "LIFE SIPHON",
                              "description": "Perform a free Shoot action using the following ranged weapon:",
                              "weapon": {
                                  "name": "Life siphon",
                                  "type": "range",
                                  "attacks": 5,
                                  "ballisticWeaponSkill": 3,
                                  "damage": 3,
                                  "criticalDamage": 3,
                                  "specialRules": [
                                      {
                                          "name": "Siphon Life Force",
                                          "description": "Each time a friendly operative makes a shooting attack with this weapon, in the Resolve Successful Hits step of that shooting attack, if you resolve two or more attack dice, you can select one friendly LEGIONARY operative within 6' of the target to regain D3 lost wounds."
                                      }
                                  ],
                                  "id": "71801c72-444b-4157-a71d-6ff630199484"
                              },
                              "id": "2515949b-d2e1-4440-9fdf-969b8d2e4404"
                          }
                      ],
                      "abilityOfKillTeam": {
                          "chaosBlessing": {
                              "name": "CHAOS BLESSING",
                              "description": "This operative gains one ability from those listed below. The ability it gains depends on its <MARK OF CHAOS> keyword.",
                              "id": "fb8f514e-8cf7-46cd-afcb-11e8a629f928"
                          },
                          "favouredOfTheDarkGods": {
                              "name": "FAVOURED OF THE DARK GODS",
                              "description": "Once per Turning Point, when it is your turn to use a Strategic Ploy, if any friendly operatives with this ability are in the killzone, you can use a Strategic Ploy without spending any CPs; that Strategic Ploy must have the same <MARK OF CHAOS> selectable keyword as one friendly operative with this ability. For example, if an operative with this ability has the KHORNE keyword, you could use the Blood for the Blood God Strategic Ploy. If an operative with this ability has the UNDIVIDED keyword, you can use the following Strategic Ploys for this ability instead: Hateful Assault, Malicious Volleys.",
                              "id": "e941b4d2-12a5-4143-aaa9-5cb7644684ca"
                          },
                          "chaosBlessings": [
                              {
                                  "name": "Khorne - Wrathful Onslaught",
                                  "description": "Each time this operative fights in combat, in the Resolve Successful Hits step of that combat, if you did not retain any critical hits, you can strike with one normal hit as if it were a critical hit.",
                                  "id": "445e2894-4442-4e25-a371-bba58540539d"
                              },
                              {
                                  "name": "Nurgle - Disgusting Vigour",
                                  "description": "Each time a shooting attack is made against this operative, in the Roll Defence Dice step of that shooting attack, you can retain one normal save as a critical save.",
                                  "id": "09152828-16dc-4b25-ba8a-a108d0c3d5fa"
                              },
                              {
                                  "name": "Slaanesh - Unnatural Agility",
                                  "description": "Add 1' to this operative's Movement characteristic.",
                                  "id": "d7daeca8-60bf-4a70-95e6-e0ee4c6ff4d1"
                              },
                              {
                                  "name": "Tzeentch - Empyreal Guidance",
                                  "description": "Each time this operative makes a shooting attack, in the Roll Attack Dice step of that shooting attack, you can retain one of your attack dice results of 5+ that is a successful hit as a critical hit.",
                                  "id": "acfe25b5-28f2-476a-9338-8e70860c54fb"
                              },
                              {
                                  "name": "Undivided - Vicious Reavers",
                                  "description": "Each time this operative fights in combat or makes a shooting attack, in the Roll Attack Dice step of that combat or shooting attack, if the target is within 6' of it, you can re-roll one of your attack dice.",
                                  "id": "39a67a2d-6101-4e48-a7b6-87705d20b5a1"
                              }
                          ]
                      },
                      "id": "b6fdaab8-77a0-4051-afaa-8943f06156d7"
                  },
                  {
                      "factionName": "Chaos Space Marines",
                      "killTeamName": "Traitor Space Marine",
                      "countOfFireTeam": 2,
                      "factionLogo": "CHAOS_SPACE_MARINE",
                      "fireTeams": [
                          {
                              "name": "Chaos Space Marine",
                              "archetype": [
                                  "seekAndDestroy",
                                  "security",
                                  "infiltration",
                                  "recon"
                              ],
                              "availableDataslates": [
                                  {
                                      "name": "CHAOS SPACE MARINE (WARRIOR)",
                                      "description": "Chaos Space Marines combine the advantages of their loyalist foes with the power of Chaos, wielding debased marks of boltguns and pistols, and rune-etched blades.",
                                      "movement": 3,
                                      "actionPointLimit": 3,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 3,
                                      "wounds": 12,
                                      "selectedRangeWeapon": {
                                          "name": "Boltgun",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "id": "08551d19-dcc2-4d33-a730-9393ca707c54"
                                      },
                                      "keyWords": [
                                          "TRAITOR SPACE MARINE",
                                          "CHAOS",
                                          "HERETIC ASTARTES",
                                          "<LEGION>",
                                          "CHAOS SPACE MARINE",
                                          "WARRIOR"
                                      ],
                                      "id": "24057e4e-2451-4cf3-9020-4acdfe5164ec",
                                      "currentWounds": 12,
                                      "unitType": [
                                          "combat",
                                          "staunch",
                                          "marksman"
                                      ],
                                      "unitImageName": "CHAOS_SPACE_MARINE_WARRIOR",
                                      "selectedMeleeWeapon": {
                                          "name": "Fists",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "id": "f2d8a4e8-8b68-4b24-b590-49b7bb84a9c7"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Boltgun",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "id": "08551d19-dcc2-4d33-a730-9393ca707c54"
                                          },
                                          {
                                              "name": "Chainsword",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 4,
                                              "criticalDamage": 5,
                                              "id": "1ddf4bc1-190f-42a0-8182-9bf4e53367b5"
                                          },
                                          {
                                              "name": "Bolt pistol",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  }
                                              ],
                                              "id": "778d2aec-a88d-4911-b478-987e741daad2"
                                          },
                                          {
                                              "name": "Fists",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "id": "f2d8a4e8-8b68-4b24-b590-49b7bb84a9c7"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "CHAOS SPACE MARINE (GUNNER)",
                                      "description": "While some traitors prefer gory close assaults, others glorify in the slaughter of their foes at range. They go to war bearing ancient patterns of plasma guns and meltaguns to tear through heavy opposition, or bathe their enemies in gouts of fire from flamers.",
                                      "movement": 3,
                                      "actionPointLimit": 3,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 3,
                                      "wounds": 12,
                                      "selectedRangeWeapon": {
                                          "name": "Flamer",
                                          "type": "range",
                                          "attacks": 5,
                                          "ballisticWeaponSkill": 2,
                                          "damage": 2,
                                          "criticalDamage": 2,
                                          "specialRules": [
                                              {
                                                  "name": "Rng 6'",
                                                  "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                              },
                                              {
                                                  "name": "Torrent 2'",
                                                  "description": "Each time a friendly operative performs a Shoot action or Overwatch action and selects this weapon, after making the shooting attack against the target, it can make a shooting attack with this weapon against each other valid target within x of the original target and each other, x is the distance after the weapon's Torrent, e.g. Torrent 2'."
                                              }
                                          ],
                                          "id": "9c8fd953-2545-4092-ac97-e575b24dfada"
                                      },
                                      "keyWords": [
                                          "TRAITOR SPACE MARINE",
                                          "CHAOS",
                                          "HERETIC ASTARTES",
                                          "<LEGION>",
                                          "CHAOS SPACE MARINE",
                                          "GUNNER"
                                      ],
                                      "id": "80591210-7be2-4595-b2cc-cb7b392de461",
                                      "currentWounds": 12,
                                      "unitType": [
                                          "staunch",
                                          "marksman"
                                      ],
                                      "unitImageName": "CHAOS_SPACE_MARINE_GUNNER",
                                      "selectedMeleeWeapon": {
                                          "name": "Fists",
                                          "type": "close",
                                          "attacks": 3,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "id": "63e24b19-317f-4e5b-9565-59a679e01f23"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Flamer",
                                              "type": "range",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 2,
                                              "criticalDamage": 2,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  },
                                                  {
                                                      "name": "Torrent 2'",
                                                      "description": "Each time a friendly operative performs a Shoot action or Overwatch action and selects this weapon, after making the shooting attack against the target, it can make a shooting attack with this weapon against each other valid target within x of the original target and each other, x is the distance after the weapon's Torrent, e.g. Torrent 2'."
                                                  }
                                              ],
                                              "id": "9c8fd953-2545-4092-ac97-e575b24dfada"
                                          },
                                          {
                                              "name": "Meltagun",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 6,
                                              "criticalDamage": 3,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  },
                                                  {
                                                      "name": "AP2",
                                                      "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                  }
                                              ],
                                              "criticalHitSpecialRules": [
                                                  {
                                                      "name": "MW4",
                                                      "description": "Mortal wounds. Each time a friendly operative makes a shooting attack with this weapon, in the roll attack dice step of that shooting attack, for each critical hit retained, inflict x mortal wounds on the target, x is the number after the weapon's MW, e.g. MW3."
                                                  }
                                              ],
                                              "id": "49fa3da5-69f8-4ef0-a779-930523f5a54a"
                                          },
                                          {
                                              "name": "Plasma gun",
                                              "profileName": "Standard",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 5,
                                              "criticalDamage": 6,
                                              "subProfiles": [
                                                  {
                                                      "name": "Plasma gun",
                                                      "profileName": "Supercharge",
                                                      "type": "range",
                                                      "attacks": 4,
                                                      "ballisticWeaponSkill": 3,
                                                      "damage": 5,
                                                      "criticalDamage": 6,
                                                      "specialRules": [
                                                          {
                                                              "name": "AP2",
                                                              "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                          },
                                                          {
                                                              "name": "Hot",
                                                              "description": "Each time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, for each attack dice result of 1 that is discarded, that operative suffers three mortal wounds."
                                                          }
                                                      ],
                                                      "id": "05a01ca5-2fa6-43c2-bbc1-d4f64a0f7c3f"
                                                  }
                                              ],
                                              "specialRules": [
                                                  {
                                                      "name": "AP1",
                                                      "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                  }
                                              ],
                                              "id": "66485e03-85f8-427f-8854-53e7327d0b19"
                                          },
                                          {
                                              "name": "Fists",
                                              "type": "close",
                                              "attacks": 3,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "id": "63e24b19-317f-4e5b-9565-59a679e01f23"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "CHAOS SPACE MARINE (HEAVY GUNNER)",
                                      "description": "These operatives brazenly carry the heaviest weapons to eviscerate the enemy, adorned with foul symbols and bladed combat attachments.",
                                      "movement": 3,
                                      "actionPointLimit": 3,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 3,
                                      "wounds": 12,
                                      "selectedRangeWeapon": {
                                          "name": "Heavy bolter",
                                          "type": "range",
                                          "attacks": 5,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 4,
                                          "criticalDamage": 5,
                                          "specialRules": [
                                              {
                                                  "name": "Heavy",
                                                  "description": "An operative cannot perform a Charge, Fall Back or Normal Move action in the same activation in which it performs a Shoot action with this ranged weapon."
                                              },
                                              {
                                                  "name": "Fusillade",
                                                  "description": "Each time a friendly operative performs a Shoot action and selects this weapon, after selecting a valid target, you can select any number of other valid targets within 2' of the original target. Distribute your attack dice between the targets you have selected. Make a shooting attack with this weapon (using the same profile) against each of the targets you have selected using the attack dice you have distributed to each of them."
                                              }
                                          ],
                                          "criticalHitSpecialRules": [
                                              {
                                                  "name": "P1",
                                                  "description": "Piercing. Each time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, if you retain any critical hits, the weapon gains the APx special rule for that shooting attack, x is the number after the weapon's P, e.g. P1."
                                              }
                                          ],
                                          "id": "ebb240c0-dfc8-412c-af0f-dbe2f87ec2cf"
                                      },
                                      "keyWords": [
                                          "TRAITOR SPACE MARINE",
                                          "CHAOS",
                                          "HERETIC ASTARTES",
                                          "<LEGION>",
                                          "CHAOS SPACE MARINE",
                                          "HEAVY GUNNER"
                                      ],
                                      "id": "1392d0e1-74a9-4e63-a3c8-30a76546bbeb",
                                      "currentWounds": 12,
                                      "unitType": [
                                          "staunch",
                                          "marksman"
                                      ],
                                      "unitImageName": "CHAOS_SPACE_MARINE_HEAVY_GUNNER",
                                      "selectedMeleeWeapon": {
                                          "name": "Fists",
                                          "type": "close",
                                          "attacks": 3,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "id": "31509802-cb20-447e-9738-6bedc3a0abfb"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Heavy bolter",
                                              "type": "range",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 4,
                                              "criticalDamage": 5,
                                              "specialRules": [
                                                  {
                                                      "name": "Heavy",
                                                      "description": "An operative cannot perform a Charge, Fall Back or Normal Move action in the same activation in which it performs a Shoot action with this ranged weapon."
                                                  },
                                                  {
                                                      "name": "Fusillade",
                                                      "description": "Each time a friendly operative performs a Shoot action and selects this weapon, after selecting a valid target, you can select any number of other valid targets within 2' of the original target. Distribute your attack dice between the targets you have selected. Make a shooting attack with this weapon (using the same profile) against each of the targets you have selected using the attack dice you have distributed to each of them."
                                                  }
                                              ],
                                              "criticalHitSpecialRules": [
                                                  {
                                                      "name": "P1",
                                                      "description": "Piercing. Each time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, if you retain any critical hits, the weapon gains the APx special rule for that shooting attack, x is the number after the weapon's P, e.g. P1."
                                                  }
                                              ],
                                              "id": "ebb240c0-dfc8-412c-af0f-dbe2f87ec2cf"
                                          },
                                          {
                                              "name": "Missile launcher",
                                              "profileName": "Frag",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 5,
                                              "subProfiles": [
                                                  {
                                                      "name": "Missile launcher",
                                                      "profileName": "Krak",
                                                      "type": "range",
                                                      "attacks": 4,
                                                      "ballisticWeaponSkill": 3,
                                                      "damage": 5,
                                                      "criticalDamage": 7,
                                                      "specialRules": [
                                                          {
                                                              "name": "Heavy",
                                                              "description": "An operative cannot perform a Charge, Fall Back or Normal Move action in the same activation in which it performs a Shoot action with this ranged weapon."
                                                          },
                                                          {
                                                              "name": "AP1",
                                                              "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                          }
                                                      ],
                                                      "id": "ed36d24e-eb01-4ddb-b2eb-ed8b2cc43847"
                                                  }
                                              ],
                                              "specialRules": [
                                                  {
                                                      "name": "Heavy",
                                                      "description": "An operative cannot perform a Charge, Fall Back or Normal Move action in the same activation in which it performs a Shoot action with this ranged weapon."
                                                  },
                                                  {
                                                      "name": "Blast 2'",
                                                      "description": "Each time a friendly operative performs a Shoot action and selects this weapon (or, in the case of profiles, this weapon's profile), after making the shooting attack against the target, make a shooting attack with this weapon (using the same profile) against each other operative Visible to and within X of the original target - each of them is a valid target and cannot be in Cover. X is the distance after the weapon's Blast, e.g. Blast . An operative cannot make a shooting attack with this weapon by performing an Overwatch action."
                                                  }
                                              ],
                                              "id": "0d769356-e348-4545-94b7-c273d16f0db0"
                                          },
                                          {
                                              "name": "Fists",
                                              "type": "close",
                                              "attacks": 3,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "id": "31509802-cb20-447e-9738-6bedc3a0abfb"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "CHAOS SPACE MARINE (ICON BEARER)",
                                      "description": "Some Chaos Space Marines proclaim their devotion to the Dark Gods by carrying twisted icons or banners to battle, strung with offerings of defeated foes.",
                                      "movement": 3,
                                      "actionPointLimit": 3,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 3,
                                      "wounds": 12,
                                      "selectedRangeWeapon": {
                                          "name": "Boltgun",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "id": "027f1309-835f-43f9-8dd0-fc1eb1e3ba4c"
                                      },
                                      "abilities": [
                                          {
                                              "name": "Icon Bearer",
                                              "description": "When determining control of an objective marker, treat this operative's APL characteristic as being 1 higher. Note that this is not a modifier. In narrative play, this is cumulative with the Focused Battle Honour.",
                                              "id": "35001b45-f357-41d6-96fb-0294f0d2718f"
                                          }
                                      ],
                                      "uniqueActions": [
                                          {
                                              "name": "Icon of Vengeance",
                                              "cost": 1,
                                              "description": "Until the end of the Turning Point, while this operative is Visible to and within 3' of a friendly HERETIC ASTARTES operative, that friendly operative is inspired by vengeance. While an operative is inspired by vengeance, each time it fights in combat or makes a shooting attack, in the Roll Attack Dice step of that combat or shooting attack, if you retain any critical hits, you can select one of your failed hits to be retained as a successful normal hit.",
                                              "id": "b72dfaa4-246b-4ab6-b7e1-ca0efae9e504"
                                          }
                                      ],
                                      "keyWords": [
                                          "TRAITOR SPACE MARINE",
                                          "CHAOS",
                                          "HERETIC ASTARTES",
                                          "<LEGION>",
                                          "CHAOS SPACE MARINE",
                                          "ICON BEARER"
                                      ],
                                      "id": "ca6651b0-5521-4420-8b69-cb91d8546be7",
                                      "currentWounds": 12,
                                      "unitType": [
                                          "combat",
                                          "staunch",
                                          "marksman"
                                      ],
                                      "unitImageName": "CHAOS_SPACE_MARINE_ICON_BEARER",
                                      "selectedMeleeWeapon": {
                                          "name": "Fists",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "id": "9bc15e35-0bb8-4220-80dc-958f0cc0523d"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Boltgun",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "id": "027f1309-835f-43f9-8dd0-fc1eb1e3ba4c"
                                          },
                                          {
                                              "name": "Chainsword",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 4,
                                              "criticalDamage": 5,
                                              "id": "501b6c38-c284-430f-bbb9-87eb7a188521"
                                          },
                                          {
                                              "name": "Bolt pistol",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  }
                                              ],
                                              "id": "a0901ad0-e1f8-43c8-a951-33c3da80b8b6"
                                          },
                                          {
                                              "name": "Fists",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "id": "9bc15e35-0bb8-4220-80dc-958f0cc0523d"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "CHAOS SPACE MARINE ASPIRING CHAMPION",
                                      "description": "Chaos Space Marines follow the strongest and most merciless of their kind to battle. These blood-soaked warriors seek to become the favoured of the gods.",
                                      "movement": 3,
                                      "actionPointLimit": 3,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 3,
                                      "wounds": 13,
                                      "selectedRangeWeapon": {
                                          "name": "Bolt pistol",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 2,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "specialRules": [
                                              {
                                                  "name": "Rng 6'",
                                                  "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                              }
                                          ],
                                          "id": "f1206247-e69c-41ac-b2e7-532e11e293cf"
                                      },
                                      "keyWords": [
                                          "TRAITOR SPACE MARINE",
                                          "CHAOS",
                                          "HERETIC ASTARTES",
                                          "<LEGION>",
                                          "LEADER",
                                          "CHAOS SPACE MARINE",
                                          "ASPIRING CHAMPION"
                                      ],
                                      "id": "87a1c0e2-fe42-47d1-a613-6588e6739938",
                                      "currentWounds": 13,
                                      "unitType": [
                                          "combat",
                                          "staunch",
                                          "marksman"
                                      ],
                                      "unitImageName": "CHAOS_SPACE_MARINE_ASPIRING_CHAMPION",
                                      "selectedMeleeWeapon": {
                                          "name": "Chainsword",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 2,
                                          "damage": 4,
                                          "criticalDamage": 5,
                                          "id": "aa0c5287-cbd7-4dd1-9cc1-4b7ecd1edaf7"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Boltgun",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "id": "0a2273b8-41c9-4118-adb8-c748bfcd7842"
                                          },
                                          {
                                              "name": "Bolt pistol",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  }
                                              ],
                                              "id": "f1206247-e69c-41ac-b2e7-532e11e293cf"
                                          },
                                          {
                                              "name": "Plasma pistol",
                                              "type": "range",
                                              "profileName": "Standard",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 5,
                                              "criticalDamage": 6,
                                              "subProfiles": [
                                                  {
                                                      "name": "Plasma pistol",
                                                      "type": "range",
                                                      "profileName": "Supercharge",
                                                      "attacks": 4,
                                                      "ballisticWeaponSkill": 2,
                                                      "damage": 5,
                                                      "criticalDamage": 6,
                                                      "specialRules": [
                                                          {
                                                              "name": "Rng 6'",
                                                              "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                          },
                                                          {
                                                              "name": "AP2",
                                                              "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                          },
                                                          {
                                                              "name": "Hot",
                                                              "description": "Each time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, for each attack dice result of 1 that is discarded, that operative suffers three mortal wounds."
                                                          }
                                                      ],
                                                      "id": "64e16028-5fed-4f9c-974c-13a3baa90582"
                                                  }
                                              ],
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  },
                                                  {
                                                      "name": "AP1",
                                                      "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                  }
                                              ],
                                              "id": "0f3fea72-039a-435b-ad86-bfc253d0f27c"
                                          },
                                          {
                                              "name": "Chainsword",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 4,
                                              "criticalDamage": 5,
                                              "id": "aa0c5287-cbd7-4dd1-9cc1-4b7ecd1edaf7"
                                          },
                                          {
                                              "name": "Fists",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "id": "f6f2b96a-0924-41f0-9867-d4de3637a974"
                                          },
                                          {
                                              "name": "Power fist",
                                              "type": "close",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 5,
                                              "criticalDamage": 7,
                                              "specialRules": [
                                                  {
                                                      "name": "Brutal",
                                                      "description": "Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat, your opponent can only parry with critical hits."
                                                  }
                                              ],
                                              "id": "1e406dbd-b33a-46fa-92bf-dc0ef04c49fa"
                                          },
                                          {
                                              "name": "Power weapon",
                                              "type": "close",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 4,
                                              "criticalDamage": 6,
                                              "specialRules": [
                                                  {
                                                      "name": "Lethal 5+",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, your attack dice results of equal to or greater than x that are successful hits are critical hits, x is the number after the weapon's Lethal, e.g. Lethal 5+."
                                                  }
                                              ],
                                              "id": "718e83bc-bda8-4bcf-885e-8ab76dc1b4d5"
                                          }
                                      ]
                                  }
                              ],
                              "currentDataslates": [],
                              "id": "1df9b75e-33f8-47b3-be20-158f707ecfa1",
                              "defaultDataslates": {
                                  "24057e4e-2451-4cf3-9020-4acdfe5164ec": 3
                              }
                          },
                          {
                              "name": "Chaos Cultist",
                              "archetype": [
                                  "infiltration",
                                  "recon"
                              ],
                              "availableDataslates": [
                                  {
                                      "name": "CHAOS CULTIST (FIGHTER)",
                                      "description": "Chaos cults foment rebellion and betrayal on thousands of worlds. Cultists arm themselves with an assortment of stolen rifles and improvised clubs and blades, gladly fighting in service of their post-Human superiors while seeking the downfall of the Imperium.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 2,
                                      "defense": 3,
                                      "save": 5,
                                      "wounds": 7,
                                      "selectedRangeWeapon": {
                                          "name": "Autopistol",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 4,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "specialRules": [
                                              {
                                                  "name": "Rng 6'",
                                                  "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                              }
                                          ],
                                          "id": "b05a0040-2e93-4c71-8458-8ae7840ff08b"
                                      },
                                      "keyWords": [
                                          "TRAITOR SPACE MARINE",
                                          "CHAOS",
                                          "CULTIST",
                                          "FIGHTER"
                                      ],
                                      "id": "54bd59de-ae18-433d-9b56-7ce229b4ca4b",
                                      "currentWounds": 7,
                                      "unitType": [
                                          "combat",
                                          "marksman",
                                          "scout"
                                      ],
                                      "unitImageName": "CHAOS_CULTIST_FIGHTER",
                                      "selectedMeleeWeapon": {
                                          "name": "Brutal assault weapon",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 4,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "id": "511ad6fb-ad76-439b-a3fb-42221068b182"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Autogun",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 4,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "id": "4a484aaa-7c22-4fcf-abf1-c64497cbcd86"
                                          },
                                          {
                                              "name": "Autopistol",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 4,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  }
                                              ],
                                              "id": "b05a0040-2e93-4c71-8458-8ae7840ff08b"
                                          },
                                          {
                                              "name": "Brutal assault weapon",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 4,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "id": "511ad6fb-ad76-439b-a3fb-42221068b182"
                                          },
                                          {
                                              "name": "Gun butt",
                                              "type": "close",
                                              "attacks": 3,
                                              "ballisticWeaponSkill": 4,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "id": "0296075d-390e-418e-89a5-b65a35c667dd"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "CHAOS CULTIST (GUNNER)",
                                      "description": "Long-hidden heavier weapons are broken out by the cult's leaders and assigned to favoured warriors when the time comes. Heavy stub be rs are devastating against lightly armoured foes, while flamers douse enemy positions in burning promethium.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 5,
                                      "wounds": 7,
                                      "selectedRangeWeapon": {
                                          "name": "Heavy stubber",
                                          "type": "range",
                                          "attacks": 5,
                                          "ballisticWeaponSkill": 4,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "specialRules": [
                                              {
                                                  "name": "Heavy",
                                                  "description": "An operative cannot perform a Charge, Fall Back or Normal Move action in the same activation in which it performs a Shoot action with this ranged weapon."
                                              },
                                              {
                                                  "name": "Ceaseless",
                                                  "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, you can re-roll any or all of your attack dice results of 1."
                                              },
                                              {
                                                  "name": "Fusillade",
                                                  "description": "Each time a friendly operative performs a Shoot action and selects this weapon, after selecting a valid target, you can select any number of other valid targets within 2' of the original target. Distribute your attack dice between the targets you have selected. Make a shooting attack with this weapon (using the same profile) against each of the targets you have selected using the attack dice you have distributed to each of them."
                                              }
                                          ],
                                          "id": "0c1a62fb-48ea-45bc-b06e-0fcf8cb0a553"
                                      },
                                      "keyWords": [
                                          "TRAITOR SPACE MARINE",
                                          "CHAOS",
                                          "CULTIST",
                                          "GUNNER"
                                      ],
                                      "id": "ea2dcb4d-e70f-4532-bf83-522964ad92e0",
                                      "currentWounds": 7,
                                      "unitType": [
                                          "staunch",
                                          "marksman"
                                      ],
                                      "unitImageName": "CHAOS_CULTIST_GUNNER",
                                      "selectedMeleeWeapon": {
                                          "name": "Gun butt",
                                          "type": "close",
                                          "attacks": 3,
                                          "ballisticWeaponSkill": 4,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "id": "672ad9a4-4299-46a8-a222-85baba35231c"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Flamer",
                                              "type": "range",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 2,
                                              "criticalDamage": 2,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  },
                                                  {
                                                      "name": "Torrent 2'",
                                                      "description": "Each time a friendly operative performs a Shoot action or Overwatch action and selects this weapon, after making the shooting attack against the target, it can make a shooting attack with this weapon against each other valid target within x of the original target and each other, x is the distance after the weapon's Torrent, e.g. Torrent 2'."
                                                  }
                                              ],
                                              "id": "ed724a98-5f66-4779-ac1b-a47cd66c7662"
                                          },
                                          {
                                              "name": "Heavy stubber",
                                              "type": "range",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 4,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "specialRules": [
                                                  {
                                                      "name": "Heavy",
                                                      "description": "An operative cannot perform a Charge, Fall Back or Normal Move action in the same activation in which it performs a Shoot action with this ranged weapon."
                                                  },
                                                  {
                                                      "name": "Ceaseless",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, you can re-roll any or all of your attack dice results of 1."
                                                  },
                                                  {
                                                      "name": "Fusillade",
                                                      "description": "Each time a friendly operative performs a Shoot action and selects this weapon, after selecting a valid target, you can select any number of other valid targets within 2' of the original target. Distribute your attack dice between the targets you have selected. Make a shooting attack with this weapon (using the same profile) against each of the targets you have selected using the attack dice you have distributed to each of them."
                                                  }
                                              ],
                                              "id": "0c1a62fb-48ea-45bc-b06e-0fcf8cb0a553"
                                          },
                                          {
                                              "name": "Gun butt",
                                              "type": "close",
                                              "attacks": 3,
                                              "ballisticWeaponSkill": 4,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "id": "672ad9a4-4299-46a8-a222-85baba35231c"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "CHAOS CULTIST CHAMPION",
                                      "description": "Those who rise to prominence in a cult may be former officers, gang lords or underground preachers, praising the Dark Gods with their varied displays of violence.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 5,
                                      "wounds": 8,
                                      "selectedRangeWeapon": {
                                          "name": "Autopistol",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "specialRules": [
                                              {
                                                  "name": "Rng 6'",
                                                  "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                              }
                                          ],
                                          "id": "01a0a6d3-c843-4f7d-be34-4176868f73f2"
                                      },
                                      "keyWords": [
                                          "TRAITOR SPACE MARINE",
                                          "CHAOS",
                                          "LEADER",
                                          "CULTIST",
                                          "CHAMPION"
                                      ],
                                      "id": "3e04c8ce-7d9a-4ec4-b377-13cedde6affd",
                                      "currentWounds": 8,
                                      "unitType": [
                                          "combat",
                                          "marksman",
                                          "scout"
                                      ],
                                      "unitImageName": "CHAOS_CULTIST_CHAMPION",
                                      "selectedMeleeWeapon": {
                                          "name": "Brutal assault weapon",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "id": "69fc1e9c-f461-49b3-bd14-dec51ff9ae60"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Autogun",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "id": "55092917-595d-4b0c-b1b3-42bcda3df524"
                                          },
                                          {
                                              "name": "Autopistol",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  }
                                              ],
                                              "id": "01a0a6d3-c843-4f7d-be34-4176868f73f2"
                                          },
                                          {
                                              "name": "Shotgun",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 3,
                                              "criticalDamage": 3,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  }
                                              ],
                                              "id": "7de3f71c-2a1e-41ad-a9ad-6533782ceecd"
                                          },
                                          {
                                              "name": "Brutal assault weapon",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "id": "69fc1e9c-f461-49b3-bd14-dec51ff9ae60"
                                          },
                                          {
                                              "name": "Gun butt",
                                              "type": "close",
                                              "attacks": 3,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "id": "f9c1099e-a466-47df-9661-77ef9dad9997"
                                          }
                                      ]
                                  }
                              ],
                              "currentDataslates": [],
                              "id": "b1cd9115-4f7e-43e3-86a3-17b76b19748e",
                              "defaultDataslates": {
                                  "54bd59de-ae18-433d-9b56-7ce229b4ca4b": 8
                              }
                          }
                      ],
                      "chosenFireTeams": [],
                      "ploys": [
                          {
                              "name": "MALICIOUS VOLLEYS",
                              "description": "Until the end of the Turning Point, each time a friendly HERETIC ASTARTES operative is activated, if it does not perform a Fight action during that activation, it can perform two Shoot actions during that activation if a bolt weapon is selected for each of those shooting attacks. A bolt weapon is a ranged weapon that includes 'bolt' in its name, e.g. boltgun, heavy bolter etc.",
                              "cost": 1,
                              "type": "strategic",
                              "id": "c80cf9a6-1501-4bc5-a408-ce2927cb945b"
                          },
                          {
                              "name": "HATEFUL ASSAULT",
                              "description": "Until the end of the Turning Point, each time a friendly HERETIC ASTARTES operative is activated, if it does not perform a Shoot action during that activation, it can perform two Fight actions during that activation",
                              "cost": 1,
                              "type": "strategic",
                              "id": "acfdc2c7-d37c-4449-bad6-c2fc275dfb9e"
                          },
                          {
                              "name": "LET THE GALAXY BURN!",
                              "description": "Until the end of the Turning Point, each time a friendly HERETIC ASTARTES operative is activated, once during that activation, in the Roll Attack Dice step of a combat or shooting attack, if it is targeting the closest valid target, you can retain one of your attack dice results of 5+ that is a successful hit as a critical hit (if you could already retain one of your results of 5+, you can retain one of your results of 4+ instead).",
                              "cost": 1,
                              "type": "strategic",
                              "id": "3896b8df-901d-405d-85fb-4789c1e0490b"
                          },
                          {
                              "name": "VETERAN OF THE LONG WAR",
                              "description": "Use this Tactical Ploy in a friendly HERETIC ASTARTES operative's activation, after it fights in combat or makes a shooting attack. If the target did not lose any wounds as a result of that combat or shooting attack, repeat that combat or shooting attack.",
                              "cost": 1,
                              "type": "tactical",
                              "id": "d18a5c3f-f9cd-465e-99bd-de00cfd05d29"
                          },
                          {
                              "name": "STRIKE FROM WITHIN",
                              "description": "Use this Tactical Ploy in the Set Up Operatives step of the mission sequence. Select one friendly CHAOS CULTIST operative. That operative can be set up with a Conceal order anywhere in the killzone that is within 1' of Heavy terrain and more than 6' from enemy operatives and the enemy drop zone.",
                              "cost": 1,
                              "type": "tactical",
                              "id": "d809c726-882d-44c2-aa43-4679e0043ca0"
                          },
                          {
                              "name": "WARP INFUSED",
                              "description": "Use this Tactical Ploy when a friendly HERETIC ASTARTES operative is activated. Until the end of that operative's activation, you can ignore any or all modifiers to its APL characteristic and it is not injured.",
                              "cost": 1,
                              "type": "tactical",
                              "id": "ac2cef08-44c4-45ad-92c8-95aa161dd938"
                          }
                      ],
                      "equipments": [
                          {
                              "name": "BELT FEED",
                              "description": "Select one boltgun, bolt pistol or heavy bolter the operative is equipped with. That weapon gains the Ceaseless special rule for the battle. If you selected a heavy bolter, this equipment costs 3EP; otherwise, it costs 2EP.",
                              "cost": 2,
                              "id": "45c9a7a3-f0cc-404c-ad4b-0fcfd42e6795"
                          },
                          {
                              "name": "MALEFIC ROUNDS",
                              "description": "Select one boltgun, bolt pistol or heavy bolter the operative is equipped with. That weapon gains the P1 critical hit rule for the battle. If it already has the P1 critical hit rule, it gains the AP1 special rule instead.",
                              "cost": 2,
                              "id": "8921b18a-e97a-4877-a5c7-3313721589c8"
                          },
                          {
                              "name": "DARK BLESSING",
                              "description": "Select one melee weapon the operative is equipped with. Add 1 to that weapon's Attacks characteristic for the battle.",
                              "cost": 3,
                              "id": "06650cd7-6bb8-4fb4-8d94-39cf67467c2b"
                          },
                          {
                              "name": "FRAG GRENADE",
                              "description": "The operative is equipped with the following ranged weapon for the battle:",
                              "cost": 2,
                              "id": "cf180333-a67b-4075-b9d1-1688da65c692",
                              "weapon": {
                                  "name": "Frag grenade",
                                  "type": "range",
                                  "attacks": 4,
                                  "ballisticWeaponSkill": 3,
                                  "damage": 2,
                                  "criticalDamage": 3,
                                  "specialRules": [
                                      {
                                          "name": "Rng 6'",
                                          "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                      },
                                      {
                                          "name": "Limited",
                                          "description": "This weapon can only be selected for use once per battle. If the weapon has a special rule that would allow it to make more than one shooting attack for an action (e.g. Blast), make each of those attacks as normal."
                                      },
                                      {
                                          "name": "Blast 2'",
                                          "description": "Each time a friendly operative performs a Shoot action and selects this weapon (or, in the case of profiles, this weapon's profile), after making the shooting attack against the target, make a shooting attack with this weapon (using the same profile) against each other operative Visible to and within X of the original target - each of them is a valid target and cannot be in Cover. X is the distance after the weapon's Blast, e.g. Blast 2'. An operative cannot make a shooting attack with this weapon by performing an Overwatch action."
                                      },
                                      {
                                          "name": "Indirect",
                                          "description": "Each time a friendly operative makes a shooting attack with this weapon, in the select valid target step of that shooting attack, enemy operatives are not in Cover."
                                      }
                                  ],
                                  "id": "63b85ecc-4625-4a12-8d9a-ffb1c0e5743b"
                              }
                          },
                          {
                              "name": "KRAK GRENADE",
                              "description": "The operative is equipped with the following ranged weapon for the battle. It cannot make a shooting attack with this weapon by performing an Overwatch action:",
                              "cost": 3,
                              "id": "2d29abcb-50ea-4be2-ab35-e8a569696931",
                              "weapon": {
                                  "name": "Krak grenade",
                                  "type": "range",
                                  "attacks": 4,
                                  "ballisticWeaponSkill": 3,
                                  "damage": 4,
                                  "criticalDamage": 5,
                                  "specialRules": [
                                      {
                                          "name": "Rng 6'",
                                          "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                      },
                                      {
                                          "name": "Limited",
                                          "description": "This weapon can only be selected for use once per battle. If the weapon has a special rule that would allow it to make more than one shooting attack for an action (e.g. Blast), make each of those attacks as normal."
                                      },
                                      {
                                          "name": "AP1",
                                          "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                      },
                                      {
                                          "name": "Indirect",
                                          "description": "Each time a friendly operative makes a shooting attack with this weapon, in the select valid target step of that shooting attack, enemy operatives are not in Cover."
                                      }
                                  ],
                                  "id": "b7a576e5-eb1f-4359-aac5-a9f780c9a8e1"
                              }
                          },
                          {
                              "name": "GRISLY TROPHY",
                              "description": "The operative gains the following ability for the battle:",
                              "cost": 3,
                              "maxNumberPerKillTeam": 1,
                              "id": "82922559-8755-4a71-be31-71c1179568b1",
                              "ability": {
                                  "name": "Grisly Trophy",
                                  "description": "While this operative is Visible to and within 3' of an enemy operative, subtract 1 from the Attacks characteristic of ranged and melee weapons that enemy operative is equipped with.",
                                  "id": "2c895e9d-022d-4404-92cc-493e7581a843"
                              }
                          },
                          {
                              "name": "SUSPENSOR SYSTEM",
                              "description": "The operative gains the following ability for the battle:",
                              "cost": 3,
                              "id": "532c74bb-9731-4752-aa59-938cbbcc7369",
                              "ability": {
                                  "name": "Suspensor System",
                                  "description": "The Heavy special rule of ranged weapons the operative is equipped with is treated differently. Instead, an operative cannot move more than 6' in the same activation in which it performs a Shoot action with any of those ranged weapons.",
                                  "id": "102fe6d5-415a-43af-815f-f26b5ebca071"
                              }
                          },
                          {
                              "name": "SACRIFICIAL DAGGER",
                              "description": "The operative gains the following ability for the battle:",
                              "cost": 3,
                              "maxNumberPerKillTeam": 1,
                              "id": "1940e363-8dd5-4639-b4bd-01b4cf9ecc42",
                              "ability": {
                                  "name": "Sacrificial Offering",
                                  "description": "Once per Turning Point, when this operative incapacitates an enemy operative in combat, this operative can regain up to 4 lost wounds.",
                                  "id": "0d9299f1-84f3-4df3-bd76-99e1e1abfa79"
                              }
                          }
                      ],
                      "countEquipmentPoint": 10,
                      "id": "36cc6ae9-91d4-4127-8b97-1685d9ce92b2"
                  },
                  {
                      "factionName": "Death Guard",
                      "killTeamName": "Death Guard",
                      "countOfFireTeam": 2,
                      "factionLogo": "DEATH_GUARD",
                      "fireTeams": [
                          {
                              "name": "Plague Marine",
                              "archetype": [
                                  "seekAndDestroy",
                                  "security"
                              ],
                              "availableDataslates": [
                                  {
                                      "name": "PLAGUE MARINE (WARRIOR)",
                                      "description": "Bloated with corruption, the Plague Marines' rusted power armour is cracked, but thanks to Nurgle's blessings they are unnaturally resilient and inured to pain. They advance inexorably upon their foes, ignoring horrendous wounds and unleashing hails of boltfire.",
                                      "movement": 2,
                                      "actionPointLimit": 3,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 3,
                                      "wounds": 12,
                                      "selectedRangeWeapon": {
                                          "name": "Boltgun",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "id": "010d480e-7703-405f-8a9e-81767182bfd6"
                                      },
                                      "abilities": [
                                          {
                                              "name": "Disgustingly Resilient",
                                              "description": "Each time this operative would lose a wound, roll one D6: on a 5+, that wound is not lost. Other than the effects of Battle Scars, this operative cannot be injured.",
                                              "id": "75aaa2d6-5ab1-403a-bc21-cc53b9a6ec67"
                                          }
                                      ],
                                      "keyWords": [
                                          "DEATH GUARD",
                                          "CHAOS",
                                          "BUBONIC ASTARTES",
                                          "<PLAGUE COMPANY>",
                                          "PLAGUE MARINE",
                                          "WARRIOR"
                                      ],
                                      "id": "b0b8cc9f-2fad-45c0-a22f-d679c71a55e1",
                                      "currentWounds": 12,
                                      "unitType": [
                                          "staunch",
                                          "marksman"
                                      ],
                                      "unitImageName": "PLAGUE_MARINE_WARRIOR",
                                      "selectedMeleeWeapon": {
                                          "name": "Plague knife",
                                          "type": "close",
                                          "attacks": 3,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 5,
                                          "id": "40d8d05f-4ce5-4ba6-9e66-775175e3c865"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Boltgun",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "id": "010d480e-7703-405f-8a9e-81767182bfd6"
                                          },
                                          {
                                              "name": "Plague knife",
                                              "type": "close",
                                              "attacks": 3,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 5,
                                              "id": "40d8d05f-4ce5-4ba6-9e66-775175e3c865"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "PLAGUE MARINE (GUNNER)",
                                      "description": "For all their mutations, Plague Marines remain the perfect infantrymen. Tactically astute and well-equipped, Plague Marines units typically boast specialists capable of responding to any threat with a variety of ancient or warp-tainted weapons.",
                                      "movement": 2,
                                      "actionPointLimit": 3,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 3,
                                      "wounds": 12,
                                      "selectedRangeWeapon": {
                                          "name": "Meltagun",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 6,
                                          "criticalDamage": 3,
                                          "specialRules": [
                                              {
                                                  "name": "Rng 6'",
                                                  "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                              },
                                              {
                                                  "name": "AP2",
                                                  "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                              }
                                          ],
                                          "criticalHitSpecialRules": [
                                              {
                                                  "name": "MW4",
                                                  "description": "Mortal wounds. Each time a friendly operative makes a shooting attack with this weapon, in the roll attack dice step of that shooting attack, for each critical hit retained, inflict x mortal wounds on the target, x is the number after the weapon's MW, e.g. MW3."
                                              }
                                          ],
                                          "id": "56fb8f9f-d2c1-4d41-bb55-47f238cee1d7"
                                      },
                                      "abilities": [
                                          {
                                              "name": "Disgustingly Resilient",
                                              "description": "Each time this operative would lose a wound, roll one D6: on a 5+, that wound is not lost. Other than the effects of Battle Scars, this operative cannot be injured.",
                                              "id": "178df8fb-37c1-4c5b-8a28-c73af732f58c"
                                          }
                                      ],
                                      "keyWords": [
                                          "DEATH GUARD",
                                          "CHAOS",
                                          "BUBONIC ASTARTES",
                                          "<PLAGUE COMPANY>",
                                          "PLAGUE MARINE",
                                          "GUNNER"
                                      ],
                                      "id": "3e77cf18-42f4-45c5-b296-81757f9eed62",
                                      "currentWounds": 12,
                                      "unitType": [
                                          "staunch",
                                          "marksman"
                                      ],
                                      "unitImageName": "PLAGUE_MARINE_GUNNER",
                                      "selectedMeleeWeapon": {
                                          "name": "Plague knife",
                                          "type": "close",
                                          "attacks": 3,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 5,
                                          "id": "4e7eb02c-e958-46a6-8c35-ddf26bce9d1a"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Meltagun",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 6,
                                              "criticalDamage": 3,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  },
                                                  {
                                                      "name": "AP2",
                                                      "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                  }
                                              ],
                                              "criticalHitSpecialRules": [
                                                  {
                                                      "name": "MW4",
                                                      "description": "Mortal wounds. Each time a friendly operative makes a shooting attack with this weapon, in the roll attack dice step of that shooting attack, for each critical hit retained, inflict x mortal wounds on the target, x is the number after the weapon's MW, e.g. MW3."
                                                  }
                                              ],
                                              "id": "56fb8f9f-d2c1-4d41-bb55-47f238cee1d7"
                                          },
                                          {
                                              "name": "Plague belcher",
                                              "type": "range",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng . All other rules for selecting a valid target still apply."
                                                  },
                                                  {
                                                      "name": "Torrent 2'",
                                                      "description": "Each time a friendly operative performs a Shoot action or Overwatch action and selects this weapon, after making the shooting attack against the target, it can make a shooting attack with this weapon against each other valid target within x of the original target and each other, x is the distance after the weapon's Torrent, e.g. Torrent ."
                                                  }
                                              ],
                                              "id": "f9d856a5-2dd9-41fe-8be3-4ba675c908b4"
                                          },
                                          {
                                              "name": "Plasma gun",
                                              "profileName": "Standard",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 5,
                                              "criticalDamage": 6,
                                              "subProfiles": [
                                                  {
                                                      "name": "Plasma gun",
                                                      "profileName": "Supercharge",
                                                      "type": "range",
                                                      "attacks": 4,
                                                      "ballisticWeaponSkill": 3,
                                                      "damage": 5,
                                                      "criticalDamage": 6,
                                                      "specialRules": [
                                                          {
                                                              "name": "AP2",
                                                              "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                          },
                                                          {
                                                              "name": "Hot",
                                                              "description": "Each time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, for each attack dice result of 1 that is discarded, that operative suffers three mortal wounds."
                                                          }
                                                      ],
                                                      "id": "202cbf62-d0d9-4a6a-af1f-2a3becad9c4c"
                                                  }
                                              ],
                                              "specialRules": [
                                                  {
                                                      "name": "AP1",
                                                      "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                  }
                                              ],
                                              "id": "bdd51242-f32a-433a-b85a-5a4e36ea2658"
                                          },
                                          {
                                              "name": "Plague knife",
                                              "type": "close",
                                              "attacks": 3,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 5,
                                              "id": "4e7eb02c-e958-46a6-8c35-ddf26bce9d1a"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "PLAGUE MARINE (HEAVY GUNNER)",
                                      "description": "Against more numerous opposition, heavily armed Plague Marines unleash gouts of foulness from plague spewers and armour-piercing shells from blight launchers.",
                                      "movement": 2,
                                      "actionPointLimit": 3,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 3,
                                      "wounds": 12,
                                      "selectedRangeWeapon": {
                                          "name": "Blight launcher",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 4,
                                          "criticalDamage": 6,
                                          "specialRules": [
                                              {
                                                  "name": "AP1",
                                                  "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                              }
                                          ],
                                          "id": "5d92ef15-b672-4801-bac2-c67e1346b473"
                                      },
                                      "abilities": [
                                          {
                                              "name": "Disgustingly Resilient",
                                              "description": "Each time this operative would lose a wound, roll one D6: on a 5+, that wound is not lost. Other than the effects of Battle Scars, this operative cannot be injured.",
                                              "id": "a0553a7a-65e7-45a1-b1b9-3deb690cacb0"
                                          }
                                      ],
                                      "keyWords": [
                                          "DEATH GUARD",
                                          "CHAOS",
                                          "BUBONIC ASTARTES",
                                          "<PLAGUE COMPANY>",
                                          "PLAGUE MARINE",
                                          "HEAVY GUNNER"
                                      ],
                                      "id": "7ec8211f-eab1-4d3c-901e-0300e30b8cca",
                                      "currentWounds": 12,
                                      "unitType": [
                                          "staunch",
                                          "marksman"
                                      ],
                                      "unitImageName": "PLAGUE_MARINE_HEAVY_GUNNER",
                                      "selectedMeleeWeapon": {
                                          "name": "Plague knife",
                                          "type": "close",
                                          "attacks": 3,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 5,
                                          "id": "92f83fa8-728a-4a55-8d76-8987905fb823"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Blight launcher",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 4,
                                              "criticalDamage": 6,
                                              "specialRules": [
                                                  {
                                                      "name": "AP1",
                                                      "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                  }
                                              ],
                                              "id": "5d92ef15-b672-4801-bac2-c67e1346b473"
                                          },
                                          {
                                              "name": "Plague spewer",
                                              "type": "range",
                                              "attacks": 6,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng . All other rules for selecting a valid target still apply."
                                                  },
                                                  {
                                                      "name": "Torrent 2'",
                                                      "description": "Each time a friendly operative performs a Shoot action or Overwatch action and selects this weapon, after making the shooting attack against the target, it can make a shooting attack with this weapon against each other valid target within x of the original target and each other, x is the distance after the weapon's Torrent, e.g. Torrent ."
                                                  }
                                              ],
                                              "id": "74b1815a-b221-427d-89df-57a668e48f11"
                                          },
                                          {
                                              "name": "Plague knife",
                                              "type": "close",
                                              "attacks": 3,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 5,
                                              "id": "92f83fa8-728a-4a55-8d76-8987905fb823"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "PLAGUE MARINE (FIGHTER)",
                                      "description": "Even before their fall to Chaos, the Death Cuard were feared as terrifying trench fighters. Now, more dangerous than ever, their diseased muscles are swollen with power and their rotting senses ignore flesh wounds.",
                                      "movement": 2,
                                      "actionPointLimit": 3,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 3,
                                      "wounds": 12,
                                      "selectedRangeWeapon": {
                                          "name": "Bolt pistol",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "specialRules": [
                                              {
                                                  "name": "Rng 6'",
                                                  "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                              }
                                          ],
                                          "id": "8197818a-1265-4b0c-8562-3e3dee967a93"
                                      },
                                      "abilities": [
                                          {
                                              "name": "Disgustingly Resilient",
                                              "description": "Each time this operative would lose a wound, roll one D6: on a 5+, that wound is not lost. Other than the effects of Battle Scars, this operative cannot be injured.",
                                              "id": "3fc0fbd8-fb22-4059-9cf1-1041c184e058"
                                          }
                                      ],
                                      "keyWords": [
                                          "DEATH GUARD",
                                          "CHAOS",
                                          "BUBONIC ASTARTES",
                                          "<PLAGUE COMPANY>",
                                          "PLAGUE MARINE",
                                          "FIGHTER"
                                      ],
                                      "id": "7922a01f-c99a-41b3-930e-2131615700b9",
                                      "currentWounds": 12,
                                      "unitType": [
                                          "combat",
                                          "staunch"
                                      ],
                                      "unitImageName": "PLAGUE_MARINE_FIGHTER",
                                      "selectedMeleeWeapon": {
                                          "name": "Bubotic axe",
                                          "type": "close",
                                          "attacks": 5,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 4,
                                          "criticalDamage": 6,
                                          "criticalHitSpecialRules": [
                                              {
                                                  "name": "Rending",
                                                  "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, if you retain any critical hits you can retain one normal hit as a critical hit."
                                              }
                                          ],
                                          "id": "42c82efe-5ab8-4747-a011-1993770e71be"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Bolt pistol",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  }
                                              ],
                                              "id": "8197818a-1265-4b0c-8562-3e3dee967a93"
                                          },
                                          {
                                              "name": "Bubotic axe",
                                              "type": "close",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 4,
                                              "criticalDamage": 6,
                                              "criticalHitSpecialRules": [
                                                  {
                                                      "name": "Rending",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, if you retain any critical hits you can retain one normal hit as a critical hit."
                                                  }
                                              ],
                                              "id": "42c82efe-5ab8-4747-a011-1993770e71be"
                                          },
                                          {
                                              "name": "Flail of corruption",
                                              "type": "close",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 4,
                                              "criticalDamage": 5,
                                              "criticalHitSpecialRules": [
                                                  {
                                                      "name": "Reap 2",
                                                      "description": "Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat, if you strike with a critical hit, inflict x mortal wounds on each other enemy operative Visible to the friendly operative and within 1' of it or the target operative, x is the number after the weapon's Reap (e.g. Reap 1)."
                                                  }
                                              ],
                                              "id": "0b67f11c-273d-4e16-898f-5dab989974ee"
                                          },
                                          {
                                              "name": "Great plague cleaver",
                                              "type": "close",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 4,
                                              "damage": 5,
                                              "criticalDamage": 7,
                                              "criticalHitSpecialRules": [
                                                  {
                                                      "name": "Rending",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, if you retain any critical hits you can retain one normal hit as a critical hit."
                                                  }
                                              ],
                                              "id": "0fd96eac-32d0-4200-8013-39c0e88bdadc"
                                          },
                                          {
                                              "name": "Mace of contagion",
                                              "type": "close",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 4,
                                              "criticalDamage": 5,
                                              "criticalHitSpecialRules": [
                                                  {
                                                      "name": "Stun",
                                                      "description": "Each time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, if you retain any critical hits, subtract 1 from the target's APL. Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat:",
                                                      "subText": [
                                                          "The first time you strike with a critical hit, select one of your opponent's normal hits from that combat to be discarded",
                                                          "The second time you strike with a critical hit, subtract 1 from the target's APL."
                                                      ]
                                                  }
                                              ],
                                              "id": "6c3181c8-b17a-44d4-912c-7339cc53e937"
                                          },
                                          {
                                              "name": "Plague knives",
                                              "type": "close",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 5,
                                              "specialRules": [
                                                  {
                                                      "name": "Relentless",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, you can re-roll any or all of your attack dice."
                                                  }
                                              ],
                                              "id": "ae7cc7f3-3c26-4412-a586-fe1760606777"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "PLAGUE MARINE (ICON BEARER)",
                                      "description": "Bloated with corruption, the Plague Marines' rusted power armour is cracked, but thanks to Nurgle's blessings they are unnaturally resilient and inured to pain. They advance inexorably upon their foes, ignoring horrendous wounds and unleashing hails of boltfire.",
                                      "movement": 2,
                                      "actionPointLimit": 3,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 3,
                                      "wounds": 12,
                                      "selectedRangeWeapon": {
                                          "name": "Boltgun",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "id": "daef3f93-1028-4208-877a-2b05ea4ea126"
                                      },
                                      "abilities": [
                                          {
                                              "name": "Disgustingly Resilient",
                                              "description": "Each time this operative would lose a wound, roll one D6: on a 5+, that wound is not lost. Other than the effects of Battle Scars, this operative cannot be injured.",
                                              "id": "658ece5c-ca7a-4e1f-bb65-72dd795ff90f"
                                          },
                                          {
                                              "name": "Icon Bearer",
                                              "description": "When determining control of an objective marker, treat this operative's APL as being 1 higher. Note that this is not a modifier. In narrative play, this is cumulative with the Focused Battle Honour.",
                                              "id": "13195c00-cf0e-4016-a513-eb6b7e429aca"
                                          }
                                      ],
                                      "uniqueActions": [
                                          {
                                              "name": "Icon of Decay",
                                              "cost": 1,
                                              "description": "Until the end of the Turning Point, while this operative is Visible to and within 3' of a friendly BUBONIC ASTARTES operative, that friendly operative is invigorated by decay. While an operative is invigorated by decay, when rolling for its Disgustingly Resilient ability, you can re-roll results of 1 and 2.",
                                              "id": "f9a43530-fabe-42a4-9482-9640ed83431d"
                                          }
                                      ],
                                      "keyWords": [
                                          "DEATH GUARD",
                                          "CHAOS",
                                          "BUBONIC ASTARTES",
                                          "<PLAGUE COMPANY>",
                                          "PLAGUE MARINE",
                                          "ICON BEARER"
                                      ],
                                      "id": "94b3a792-4372-48e8-875b-a7ee7641a2d6",
                                      "currentWounds": 12,
                                      "unitType": [
                                          "staunch"
                                      ],
                                      "unitImageName": "PLAGUE_MARINE_ICON_BEARER",
                                      "selectedMeleeWeapon": {
                                          "name": "Plague knife",
                                          "type": "close",
                                          "attacks": 3,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 5,
                                          "id": "607e64e9-3d0c-4438-bde7-1c65ac698b30"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Boltgun",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "id": "daef3f93-1028-4208-877a-2b05ea4ea126"
                                          },
                                          {
                                              "name": "Plague knife",
                                              "type": "close",
                                              "attacks": 3,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 5,
                                              "id": "607e64e9-3d0c-4438-bde7-1c65ac698b30"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "PLAGUE MARINE CHAMPION",
                                      "description": "Riddled with stinking buboes and melded with their armour, these veterans grind their opponents down with expert strikes from their daemon-tainted blades.",
                                      "movement": 2,
                                      "actionPointLimit": 3,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 3,
                                      "wounds": 13,
                                      "selectedRangeWeapon": {
                                          "name": "Bolt pistol",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 2,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "specialRules": [
                                              {
                                                  "name": "Rng 6'",
                                                  "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                              }
                                          ],
                                          "id": "abd821f6-7da1-4bb6-a451-0c9a8809c887"
                                      },
                                      "abilities": [
                                          {
                                              "name": "Disgustingly Resilient",
                                              "description": "Each time this operative would lose a wound, roll one D6: on a 5+, that wound is not lost. Other than the effects of Battle Scars, this operative cannot be injured.",
                                              "id": "32e0b812-3156-4c9b-a49e-669e1d98b8f3"
                                          }
                                      ],
                                      "keyWords": [
                                          "DEATH GUARD",
                                          "CHAOS",
                                          "BUBONIC ASTARTES",
                                          "<PLAGUE COMPANY>",
                                          "LEADER",
                                          "PLAGUE MARINE",
                                          "CHAMPION"
                                      ],
                                      "id": "83ec2e37-051b-4025-8773-ff9a1b3716c4",
                                      "currentWounds": 13,
                                      "unitType": [
                                          "combat",
                                          "staunch",
                                          "marksman"
                                      ],
                                      "unitImageName": "PLAGUE_MARINE_CHAMPION",
                                      "selectedMeleeWeapon": {
                                          "name": "Plague sword",
                                          "type": "close",
                                          "attacks": 5,
                                          "ballisticWeaponSkill": 2,
                                          "damage": 4,
                                          "criticalDamage": 6,
                                          "id": "3000de53-22e9-40d0-87a4-a0738dc10ca6"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Boltgun",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "id": "e978698b-cda9-4b8c-b3b8-66e072a73f61"
                                          },
                                          {
                                              "name": "Bolt pistol",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  }
                                              ],
                                              "id": "abd821f6-7da1-4bb6-a451-0c9a8809c887"
                                          },
                                          {
                                              "name": "Plasma pistol",
                                              "type": "range",
                                              "profileName": "Standard",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 5,
                                              "criticalDamage": 6,
                                              "subProfiles": [
                                                  {
                                                      "name": "Plasma pistol",
                                                      "type": "range",
                                                      "profileName": "Supercharge",
                                                      "attacks": 4,
                                                      "ballisticWeaponSkill": 2,
                                                      "damage": 5,
                                                      "criticalDamage": 6,
                                                      "specialRules": [
                                                          {
                                                              "name": "Rng 6'",
                                                              "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                          },
                                                          {
                                                              "name": "AP2",
                                                              "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                          },
                                                          {
                                                              "name": "Hot",
                                                              "description": "Each time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, for each attack dice result of 1 that is discarded, that operative suffers three mortal wounds."
                                                          }
                                                      ],
                                                      "id": "4042b57d-300d-4491-9c8f-488fc89486f7"
                                                  }
                                              ],
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  },
                                                  {
                                                      "name": "AP1",
                                                      "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                  }
                                              ],
                                              "id": "a518415e-ff1e-465c-8d0d-66e771d2ebed"
                                          },
                                          {
                                              "name": "Plague knife",
                                              "type": "close",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 3,
                                              "criticalDamage": 5,
                                              "id": "56081d95-f59d-4c3f-b4b6-19184c389703"
                                          },
                                          {
                                              "name": "Plague sword",
                                              "type": "close",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 4,
                                              "criticalDamage": 6,
                                              "id": "3000de53-22e9-40d0-87a4-a0738dc10ca6"
                                          },
                                          {
                                              "name": "Power fist",
                                              "type": "close",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 5,
                                              "criticalDamage": 7,
                                              "specialRules": [
                                                  {
                                                      "name": "Brutal",
                                                      "description": "Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat, your opponent can only parry with critical hits."
                                                  }
                                              ],
                                              "id": "0aec75c8-d00c-45f1-b652-06a62748b68a"
                                          }
                                      ]
                                  }
                              ],
                              "currentDataslates": [],
                              "id": "08e2138a-0a40-428b-b98f-bb10416ff9a6",
                              "defaultDataslates": {
                                  "b0b8cc9f-2fad-45c0-a22f-d679c71a55e1": 3
                              }
                          },
                          {
                              "name": "Poxwalker",
                              "archetype": [
                                  "security",
                                  "infiltration"
                              ],
                              "availableDataslates": [
                                  {
                                      "name": "POXWALKER",
                                      "description": "Poxwalkers are all that remain of the victims of an arcane plague. Given animus through the disease's corrupting power, these moaning cadavers are slow and clumsy, but they will never stop, ignoring even mortal wounds.",
                                      "movement": 2,
                                      "actionPointLimit": 2,
                                      "groupActivation": 2,
                                      "defense": 3,
                                      "save": 6,
                                      "wounds": 7,
                                      "keyWords": [
                                          "DEATH GUARD",
                                          "CHAOS",
                                          "POXWALKER"
                                      ],
                                      "abilities": [
                                          {
                                              "name": "Mindless",
                                              "description": "Each time this operative would perform a mission action or the Pick Up action, you must subtract one additional AP to do so. This operative cannot be equipped with equipment. In narrative play, it cannot gain (or lose) experience and automatically passes Casualty tests.",
                                              "id": "8d084558-aac9-4ceb-824d-b30f76bb9e38"
                                          },
                                          {
                                              "name": "Disgustingly Resilient",
                                              "description": "Each time this operative would lose a wound, roll one D6: on a 5+, that wound is not lost. This operative cannot be injured.",
                                              "id": "8167beca-83dc-46d4-92b9-abff1592813d"
                                          }
                                      ],
                                      "id": "c9e6fcb4-683f-4308-92d8-e3ba96f53c3a",
                                      "currentWounds": 7,
                                      "unitType": [],
                                      "unitImageName": "POXWALKER",
                                      "availableWeapons": [
                                          {
                                              "name": "Improvised weapon",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 4,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "id": "32fb7166-d2b3-42a2-aa7d-dfced3052891"
                                          }
                                      ],
                                      "selectedMeleeWeapon": {
                                          "name": "Improvised weapon",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 4,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "id": "32fb7166-d2b3-42a2-aa7d-dfced3052891"
                                      },
                                      "equipments": []
                                  }
                              ],
                              "currentDataslates": [],
                              "id": "97480297-a342-49f2-9c3a-095e19d51411",
                              "defaultDataslates": {
                                  "c9e6fcb4-683f-4308-92d8-e3ba96f53c3a": 8
                              }
                          }
                      ],
                      "chosenFireTeams": [],
                      "ploys": [
                          {
                              "name": "MALICIOUS VOLLEYS",
                              "description": "Until the end of the Turning Point, each time a friendly BUBONIC ASTARTES operative is activated, if it does not perform a Fight action during that activation, it can perform two Shoot actions during that activation if a bolt weapon is selected for each of those shooting attacks. A bolt weapon is a ranged weapon that includes 'bolt' in its name, e.g. boltgun, bolt pistol etc.",
                              "cost": 1,
                              "type": "strategic",
                              "id": "aa301e8d-a560-48a0-a14c-3e72c9372afd"
                          },
                          {
                              "name": "HATEFUL ASSAULT",
                              "description": "Until the end of the Turning Point, each time a friendly BUBONIC ASTARTES operative is activated, if it does not perform a Shoot action during that activation, it can perform two Fight actions during that activation.",
                              "cost": 1,
                              "type": "strategic",
                              "id": "c542f8b2-5f2c-4326-9ea6-ac56cc2fa2a5"
                          },
                          {
                              "name": "CONTAGION",
                              "description": "Until the end of the Turning Point, while an enemy operative is Visible to and within 2' of a friendly DEATH GUARD operative, that enemy operative is treated as being injured (only subtract 2' from its Movement characteristic as a result of being injured if it is activated within 2' of a friendly DEATH GUARD operative).",
                              "cost": 1,
                              "type": "strategic",
                              "id": "c2b9d8cf-398e-44d3-9846-78746dc32cd8"
                          },
                          {
                              "name": "EFFLUENT DEMISE",
                              "description": "Use this Tactical Ploy when a friendly BUBONIC ASTARTES operative is incapacitated. Inflict D3 mortal wounds on each enemy operative Visible to and within 2' of that operative.",
                              "cost": 1,
                              "type": "tactical",
                              "id": "ef57b31c-2392-44d4-a34a-0f515682695f"
                          },
                          {
                              "name": "REVOLTING DURABILITY",
                              "description": "Use this Tactical Ploy in either the Resolve Successful Hits or Resolve Successful Saves step of a combat or shooting attack made against a friendly BUBONIC ASTARTES operative. You can change one of your opponent's critical hits into a normal hit.",
                              "cost": 1,
                              "type": "tactical",
                              "id": "f63f114b-f45a-404c-8fb0-a812daf5d3fc"
                          },
                          {
                              "name": "DIG IN",
                              "description": "Use this Tactical Ploy when a friendly BUBONIC ASTARTES operative is activated. Until the end of that operative's activation, it can perform the following action:",
                              "cost": 1,
                              "type": "tactical",
                              "id": "6a2ea3a8-b5f5-4956-bbe2-fdcdf537152c",
                              "uniqueAction": {
                                  "name": "DIG IN",
                                  "cost": 1,
                                  "description": "Until the end of the Turning Point, add 1 to this operative's Defence characteristic.",
                                  "id": "bbe14af7-e3d1-4070-9e5e-b3e73a780cc8"
                              }
                          }
                      ],
                      "equipments": [
                          {
                              "name": "NURGLING",
                              "description": "The operative gains the following ability for the battle:",
                              "cost": 3,
                              "maxNumberPerKillTeam": 1,
                              "id": "d987c52e-d7aa-4917-ad80-21d6728d0a55",
                              "ability": {
                                  "name": "Nurgling Mischief",
                                  "description": "Once per battle, when an enemy operative Visible to and within 3' of this operative is activated, you can use this ability. If you do so, subtract 1 from that enemy operative's APL.",
                                  "id": "621d3225-ff5f-4550-8bad-89e07ebcfecf"
                              }
                          },
                          {
                              "name": "PLAGUE BELL",
                              "description": "The operative can perform the following action during the battle:",
                              "cost": 3,
                              "maxNumberPerKillTeam": 1,
                              "id": "13af0df7-bba8-4c85-89c2-4c6c47e4be36",
                              "uniqueAction": {
                                  "name": "PLAGUE BELL",
                                  "cost": 1,
                                  "description": "Until the end of the Turning Point, each time a friendly BUBONIC ASTARTES operative is activated within 6' of this operative, until the end of that activation, add 2' to that friendly operative's movement characteristic.",
                                  "id": "6fd47aa2-77be-44a5-b70c-3147b9385ce3"
                              }
                          },
                          {
                              "name": "BLIGHT GRENADE",
                              "description": "The operative is equipped with the following ranged weapon for the battle:",
                              "cost": 2,
                              "id": "e40045f5-4d15-4841-8ee7-7af20907d9c9",
                              "weapon": {
                                  "name": "Blight grenade",
                                  "type": "range",
                                  "attacks": 4,
                                  "ballisticWeaponSkill": 3,
                                  "damage": 2,
                                  "criticalDamage": 3,
                                  "specialRules": [
                                      {
                                          "name": "Rng 6'",
                                          "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                      },
                                      {
                                          "name": "Blast 2'",
                                          "description": "Each time a friendly operative performs a Shoot action and selects this weapon (or, in the case of profiles, this weapon's profile), after making the shooting attack against the target, make a shooting attack with this weapon (using the same profile) against each other operative Visible to and within X of the original target - each of them is a valid target and cannot be in Cover. X is the distance after the weapon's Blast, e.g. Blast 2'. An operative cannot make a shooting attack with this weapon by performing an Overwatch action."
                                      },
                                      {
                                          "name": "Limited",
                                          "description": "This weapon can only be selected for use once per battle. If the weapon has a special rule that would allow it to make more than one shooting attack for an action (e.g. Blast), make each of those attacks as normal."
                                      },
                                      {
                                          "name": "Indirect",
                                          "description": "Each time a friendly operative makes a shooting attack with this weapon, in the select valid target step of that shooting attack, enemy operatives are not in Cover."
                                      },
                                      {
                                          "name": "Ceaseless",
                                          "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, you can re-roll any or all of your attack dice results of 1."
                                      }
                                  ],
                                  "id": "67c47c99-e3e3-4aef-bee6-278ea4a48872"
                              }
                          },
                          {
                              "name": "KRAK GRENADE",
                              "description": "The operative is equipped with the following ranged weapon for the battle. It cannot make a shooting attack with this weapon by performing an Overwatch action:",
                              "cost": 3,
                              "id": "ab260610-0f72-4be6-a092-cee84708b2d9",
                              "weapon": {
                                  "name": "Krak grenade",
                                  "type": "range",
                                  "attacks": 4,
                                  "ballisticWeaponSkill": 3,
                                  "damage": 4,
                                  "criticalDamage": 5,
                                  "specialRules": [
                                      {
                                          "name": "Rng 6'",
                                          "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                      },
                                      {
                                          "name": "Limited",
                                          "description": "This weapon can only be selected for use once per battle. If the weapon has a special rule that would allow it to make more than one shooting attack for an action (e.g. Blast), make each of those attacks as normal."
                                      },
                                      {
                                          "name": "AP1",
                                          "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                      },
                                      {
                                          "name": "Indirect",
                                          "description": "Each time a friendly operative makes a shooting attack with this weapon, in the select valid target step of that shooting attack, enemy operatives are not in Cover."
                                      }
                                  ],
                                  "id": "01008da8-9abc-48d5-83fc-8dfe701d591a"
                              }
                          },
                          {
                              "name": "FILTH CENSER",
                              "description": "The operative gains the following ability for the battle:",
                              "cost": 3,
                              "maxNumberPerKillTeam": 1,
                              "id": "ced39e10-76f9-42bc-831a-993efbf835f9",
                              "ability": {
                                  "name": "Filth Censer",
                                  "description": "Each time you use the Contagion Strategic Ploy, enemy operatives are treated as being injured while this operative is Visible to and within 4' of them (instead of 2').",
                                  "id": "fa9e7085-334e-48b4-ad61-1072faa21821"
                              }
                          },
                          {
                              "name": "VIRULENT ROUNDS",
                              "description": "Select one boltgun or bolt pistol the operative is equipped with. Add 1 to that weapon's Critical Damage characteristic for the battle.",
                              "cost": 1,
                              "id": "0dc01d99-0220-4df1-8c0c-b1fcebd51612"
                          },
                          {
                              "name": "MEPHITIC TOXIN",
                              "description": "Select one plague knife or plague knives the operative is equipped with. Add 1 to that weapon's Normal Damage characteristic for the battle.",
                              "cost": 2,
                              "id": "3219b270-b3b9-4830-9d78-7616c94b22ec"
                          }
                      ],
                      "countEquipmentPoint": 10,
                      "id": "ebc89dc5-c383-462b-b2fb-afcbc21b989e"
                  },
                  {
                      "factionName": "Thousand Sons",
                      "killTeamName": "Thousand Sons [outdated]",
                      "countOfFireTeam": 2,
                      "factionLogo": "THOUSAND_SONS",
                      "fireTeams": [
                          {
                              "name": "Thousand Sons",
                              "archetype": [
                                  "seekAndDestroy",
                                  "security"
                              ],
                              "availableDataslates": [
                                  {
                                      "name": "RUBRIC MARINE (WARRIOR)",
                                      "description": "Rubric Mannes are animated suits of power armour inhabited by the bound souls of their former occupants. With ruinous proficiency, they steadily fire bursts of eldritch shells from their inferno boltguns, advancing upon their psychic master s enemies.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 3,
                                      "wounds": 12,
                                      "selectedRangeWeapon": {
                                          "name": "Inferno boltgun",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "specialRules": [
                                              {
                                                  "name": "AP1",
                                                  "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                              }
                                          ],
                                          "id": "681aef46-1054-471a-9751-d75943bbfe26"
                                      },
                                      "abilities": [
                                          {
                                              "name": "Favoured by Change",
                                              "description": "This operative has a 5+ invulnerable save.",
                                              "id": "2dfb5cb8-79e2-4708-80b6-6ebfdbd36df8"
                                          },
                                          {
                                              "name": "All Is Dust",
                                              "description": "Each time a shooting attack is made against this operative, if the ranged weapon has a Normal Damage characteristic of 3 or less, this operative is treated as having a Save characteristic of 2+ for that shooting attack.",
                                              "id": "c067fc38-c6be-4b31-ae62-70d3b49d2716"
                                          }
                                      ],
                                      "keyWords": [
                                          "THOUSAND SONS",
                                          "CHAOS",
                                          "ARCANA ASTARTES",
                                          "<GREAT CULT>",
                                          "RUBRIC MARINE",
                                          "WARRIOR"
                                      ],
                                      "id": "3bd7aa10-8667-4451-9012-0f2bc0c31557",
                                      "currentWounds": 12,
                                      "unitType": [
                                          "staunch",
                                          "marksman"
                                      ],
                                      "unitImageName": "RUBRIC_MARINE_WARRIOR",
                                      "selectedMeleeWeapon": {
                                          "name": "Fists",
                                          "type": "close",
                                          "attacks": 3,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "id": "8b261451-d194-48ab-97ce-9463abefa1f8"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Inferno boltgun",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "specialRules": [
                                                  {
                                                      "name": "AP1",
                                                      "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                  }
                                              ],
                                              "id": "681aef46-1054-471a-9751-d75943bbfe26"
                                          },
                                          {
                                              "name": "Fists",
                                              "type": "close",
                                              "attacks": 3,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "id": "8b261451-d194-48ab-97ce-9463abefa1f8"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "RUBRIC MARINE (GUNNER)",
                                      "description": "With their armour's servos infused with sorcery, the spirit within a Rubric Marine's shell easily hefts the heaviest of arcane weapons. Their sou I reaper cannons shoot streams of shells alight with magical power, while their warpflamers unleash gouts of iridescent fire.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 3,
                                      "wounds": 12,
                                      "selectedRangeWeapon": {
                                          "name": "Soulreaper cannon",
                                          "type": "range",
                                          "attacks": 6,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "specialRules": [
                                              {
                                                  "name": "AP1",
                                                  "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                              },
                                              {
                                                  "name": "Fusillade",
                                                  "description": "Each time a friendly operative performs a Shoot action and selects this weapon, after selecting a valid target, you can select any number of other valid targets within 2' of the original target. Distribute your attack dice between the targets you have selected. Make a shooting attack with this weapon (using the same profile) against each of the targets you have selected using the attack dice you have distributed to each of them."
                                              }
                                          ],
                                          "id": "8ac4678b-9913-453a-a8aa-d8c7802349bb"
                                      },
                                      "abilities": [
                                          {
                                              "name": "Favoured by Change",
                                              "description": "This operative has a 5+ invulnerable save.",
                                              "id": "b0b27d10-89c3-4f47-8ddf-06f657bc5c44"
                                          },
                                          {
                                              "name": "All Is Dust",
                                              "description": "Each time a shooting attack is made against this operative, if the ranged weapon has a Normal Damage characteristic of 3 or less, this operative is treated as having a Save characteristic of 2+ for that shooting attack.",
                                              "id": "9973f3de-16cd-4801-9227-329c256f62eb"
                                          }
                                      ],
                                      "keyWords": [
                                          "THOUSAND SONS",
                                          "CHAOS",
                                          "ARCANA ASTARTES",
                                          "<GREAT CULT>",
                                          "RUBRIC MARINE",
                                          "GUNNER"
                                      ],
                                      "id": "e5952b5c-e23e-4941-8799-03f94bb1935c",
                                      "currentWounds": 12,
                                      "unitType": [
                                          "staunch",
                                          "marksman"
                                      ],
                                      "unitImageName": "RUBRIC_MARINE_GUNNER",
                                      "selectedMeleeWeapon": {
                                          "name": "Fists",
                                          "type": "close",
                                          "attacks": 3,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "id": "8c35b43d-b6df-41bc-8914-98fdaad785cf"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Soulreaper cannon",
                                              "type": "range",
                                              "attacks": 6,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "specialRules": [
                                                  {
                                                      "name": "AP1",
                                                      "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                  },
                                                  {
                                                      "name": "Fusillade",
                                                      "description": "Each time a friendly operative performs a Shoot action and selects this weapon, after selecting a valid target, you can select any number of other valid targets within 2' of the original target. Distribute your attack dice between the targets you have selected. Make a shooting attack with this weapon (using the same profile) against each of the targets you have selected using the attack dice you have distributed to each of them."
                                                  }
                                              ],
                                              "id": "8ac4678b-9913-453a-a8aa-d8c7802349bb"
                                          },
                                          {
                                              "name": "Warpflamer",
                                              "type": "range",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  },
                                                  {
                                                      "name": "Torrent 2'",
                                                      "description": "Each time a friendly operative performs a Shoot action or Overwatch action and selects this weapon, after making the shooting attack against the target, it can make a shooting attack with this weapon against each other valid target within x of the original target and each other, x is the distance after the weapon's Torrent, e.g. Torrent 2'."
                                                  },
                                                  {
                                                      "name": "AP1",
                                                      "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                  }
                                              ],
                                              "id": "f1492f18-fa6c-4a0b-8c26-421d233c6d80"
                                          },
                                          {
                                              "name": "Fists",
                                              "type": "close",
                                              "attacks": 3,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "id": "8c35b43d-b6df-41bc-8914-98fdaad785cf"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "RUBRIC MARINE (ICON BEARER)",
                                      "description": "Rubric Marines and their masters ultimately serve the schemes of the Chaos God Tzeentch. Some of these arcane warriors bear sorcerous icons of the Changer of the Ways that crawl with coruscating energy, searing the unenlightened with mutating flames.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 3,
                                      "wounds": 12,
                                      "selectedRangeWeapon": {
                                          "name": "Inferno boltgun",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "specialRules": [
                                              {
                                                  "name": "AP1",
                                                  "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                              }
                                          ],
                                          "id": "a4694aa5-64fe-4221-afbb-b324f301445b"
                                      },
                                      "abilities": [
                                          {
                                              "name": "Favoured by Change",
                                              "description": "This operative has a 5+ invulnerable save.",
                                              "id": "cb20e138-df7b-4f23-af4f-ff2e4802c5be"
                                          },
                                          {
                                              "name": "All Is Dust",
                                              "description": "Each time a shooting attack is made against this operative, if the ranged weapon has a Normal Damage characteristic of 3 or less, this operative is treated as having a Save characteristic of 2+ for that shooting attack.",
                                              "id": "41e1e18f-2829-442c-8b61-ceab83a1dd83"
                                          },
                                          {
                                              "name": "Icon Bearer",
                                              "description": "When determining control of an objective marker, treat this operative's APL characteristic as being 1 higher. Note that this is not a modifier. In narrative play, this is cumulative with the Focused Battle Honour.",
                                              "id": "7e669406-bb9b-4749-8e92-310a26d92a57"
                                          },
                                          {
                                              "name": "Icon of Flame",
                                              "description": "While a friendly THOUSAND SONS ASPIRING SORCERER operative is within 3' of this operative, it can resolve a second psychic power in its activation as a result of the Manifest Psychic Power action without needing to roll one D6.",
                                              "id": "59f78157-acf2-4e66-8519-06ada470ed99"
                                          }
                                      ],
                                      "keyWords": [
                                          "THOUSAND SONS",
                                          "CHAOS",
                                          "ARCANA ASTARTES",
                                          "<GREAT CULT>",
                                          "RUBRIC MARINE",
                                          "ICON BEARER"
                                      ],
                                      "id": "f92cdf71-d8cb-4c1b-96b4-03e959635d7a",
                                      "currentWounds": 12,
                                      "unitType": [
                                          "staunch",
                                          "marksman"
                                      ],
                                      "unitImageName": "RUBRIC_MARINE_ICON_BEARER",
                                      "selectedMeleeWeapon": {
                                          "name": "Fists",
                                          "type": "close",
                                          "attacks": 3,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "id": "8e711905-c316-48c8-8369-acf9f492ae21"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Inferno boltgun",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "specialRules": [
                                                  {
                                                      "name": "AP1",
                                                      "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                  }
                                              ],
                                              "id": "a4694aa5-64fe-4221-afbb-b324f301445b"
                                          },
                                          {
                                              "name": "Fists",
                                              "type": "close",
                                              "attacks": 3,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "id": "8e711905-c316-48c8-8369-acf9f492ae21"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "ASPIRING SORCERER",
                                      "description": "An Aspiring Sorcerer who has gained control over a unit of Rubric Marines yokes them to his will. He directs them like a puppeteer, employing them as bulwarks against his foes while channelling his dark magics of manipulation through a psychically attuned force staff.",
                                      "movement": 3,
                                      "actionPointLimit": 3,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 3,
                                      "wounds": 13,
                                      "selectedRangeWeapon": {
                                          "name": "Inferno bolt pistol",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 2,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "specialRules": [
                                              {
                                                  "name": "Rng 6'",
                                                  "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                              },
                                              {
                                                  "name": "AP1",
                                                  "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                              }
                                          ],
                                          "id": "1c26ce93-3d17-4d83-ae25-b801e09d8e6c"
                                      },
                                      "abilities": [
                                          {
                                              "name": "Favoured by Change",
                                              "description": "This operative has a 5+ invulnerable save.",
                                              "id": "ba353cc4-9378-47b6-80b1-bc6b514d8b5a"
                                          }
                                      ],
                                      "uniqueActions": [
                                          {
                                              "name": "Manifest Psychic Power",
                                              "cost": 1,
                                              "description": "Psychic action. Resolve a THOUSAND SONS psychic power. An operative can perform this action twice during its activation.",
                                              "id": "f4184217-d450-43df-85f7-4ddedf4bd9c0"
                                          }
                                      ],
                                      "keyWords": [
                                          "THOUSAND SONS",
                                          "CHAOS",
                                          "ARCANA ASTARTES",
                                          "<GREAT CULT>",
                                          "PSYKER",
                                          "LEADER",
                                          "RUBRIC MARINE",
                                          "ASPIRING SORCERER"
                                      ],
                                      "id": "f0978718-fbe3-4ea3-b834-b4bc40911966",
                                      "currentWounds": 13,
                                      "unitType": [
                                          "staunch",
                                          "marksman"
                                      ],
                                      "unitImageName": "ASPIRING_SORCERER",
                                      "selectedMeleeWeapon": {
                                          "name": "Force stave",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 4,
                                          "criticalDamage": 5,
                                          "criticalHitSpecialRules": [
                                              {
                                                  "name": "Stun",
                                                  "description": "Each time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, if you retain any critical hits, subtract 1 from the target's APL. Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat:",
                                                  "subText": [
                                                      "The first time you strike with a critical hit, select one of your opponent's normal hits from that combat to be discarded",
                                                      "The second time you strike with a critical hit, subtract 1 from the target's APL."
                                                  ]
                                              }
                                          ],
                                          "id": "cd457354-1dba-48f6-9462-f0deb72cfb3c"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Inferno bolt pistol",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  },
                                                  {
                                                      "name": "AP1",
                                                      "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                  }
                                              ],
                                              "id": "1c26ce93-3d17-4d83-ae25-b801e09d8e6c"
                                          },
                                          {
                                              "name": "Plasma pistol",
                                              "type": "range",
                                              "profileName": "Standard",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 5,
                                              "criticalDamage": 6,
                                              "subProfiles": [
                                                  {
                                                      "name": "Plasma pistol",
                                                      "type": "range",
                                                      "profileName": "Supercharge",
                                                      "attacks": 4,
                                                      "ballisticWeaponSkill": 2,
                                                      "damage": 5,
                                                      "criticalDamage": 6,
                                                      "specialRules": [
                                                          {
                                                              "name": "Rng 6'",
                                                              "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                          },
                                                          {
                                                              "name": "AP2",
                                                              "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                          },
                                                          {
                                                              "name": "Hot",
                                                              "description": "Each time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, for each attack dice result of 1 that is discarded, that operative suffers three mortal wounds."
                                                          }
                                                      ],
                                                      "id": "7ce65d51-d617-4e2c-a731-e8806276a386"
                                                  }
                                              ],
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  },
                                                  {
                                                      "name": "AP1",
                                                      "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                  }
                                              ],
                                              "id": "8acec16c-34f5-40b8-a946-4b13ddb28e44"
                                          },
                                          {
                                              "name": "Warpflame pistol",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 2,
                                              "criticalDamage": 2,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  },
                                                  {
                                                      "name": "Torrent 1'",
                                                      "description": "Each time a friendly operative performs a Shoot action or Overwatch action and selects this weapon, after making the shooting attack against the target, it can make a shooting attack with this weapon against each other valid target within x of the original target and each other, x is the distance after the weapon's Torrent, e.g. Torrent 2'."
                                                  },
                                                  {
                                                      "name": "AP1",
                                                      "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                  }
                                              ],
                                              "id": "b0888bb1-98de-4d88-b879-fa830d2e0851"
                                          },
                                          {
                                              "name": "Force stave",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 4,
                                              "criticalDamage": 5,
                                              "criticalHitSpecialRules": [
                                                  {
                                                      "name": "Stun",
                                                      "description": "Each time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, if you retain any critical hits, subtract 1 from the target's APL. Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat:",
                                                      "subText": [
                                                          "The first time you strike with a critical hit, select one of your opponent's normal hits from that combat to be discarded",
                                                          "The second time you strike with a critical hit, subtract 1 from the target's APL."
                                                      ]
                                                  }
                                              ],
                                              "id": "cd457354-1dba-48f6-9462-f0deb72cfb3c"
                                          }
                                      ]
                                  }
                              ],
                              "currentDataslates": [],
                              "id": "e6d0594a-ede8-4d76-a63b-e0f85c67379d",
                              "defaultDataslates": {
                                  "3bd7aa10-8667-4451-9012-0f2bc0c31557": 2
                              }
                          },
                          {
                              "name": "Tzaangor",
                              "archetype": [
                                  "seekAndDestroy",
                                  "recon"
                              ],
                              "availableDataslates": [
                                  {
                                      "name": "TZAANGOR (FIGHTER)",
                                      "description": "Tzaangors are mutated fusions of avian beasts and corrupted Humans. In thrall to Tzeentch, they serve the Thousand Sons as bestial shock troops, attacking with savage blows from ritual blades and tearing flesh with their jagged beaks.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 6,
                                      "wounds": 8,
                                      "selectedRangeWeapon": {
                                          "name": "Autopistol",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 4,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "specialRules": [
                                              {
                                                  "name": "Rng 6'",
                                                  "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                              }
                                          ],
                                          "id": "28625008-a2ac-4c9a-aeba-87ce0514015e"
                                      },
                                      "keyWords": [
                                          "THOUSAND SONS",
                                          "CHAOS",
                                          "TZAANGOR",
                                          "FIGHTER"
                                      ],
                                      "abilities": [
                                          {
                                              "name": "Favoured by Change",
                                              "description": "This operative has a 5+ invulnerable save.",
                                              "id": "d0fd59be-04b2-47d8-ab04-5804173fc80a"
                                          }
                                      ],
                                      "id": "066f1487-434c-4b04-b69a-47b8922b28ac",
                                      "currentWounds": 8,
                                      "unitType": [
                                          "combat",
                                          "scout"
                                      ],
                                      "unitImageName": "TZAANGOR_FIGHTER",
                                      "selectedMeleeWeapon": {
                                          "name": "Chainsword",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 4,
                                          "damage": 4,
                                          "criticalDamage": 5,
                                          "id": "f806e9fe-3ae8-4db5-97a3-a202fe0c4999"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Autopistol",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 4,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  }
                                              ],
                                              "id": "28625008-a2ac-4c9a-aeba-87ce0514015e"
                                          },
                                          {
                                              "name": "Chainsword",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 4,
                                              "damage": 4,
                                              "criticalDamage": 5,
                                              "id": "f806e9fe-3ae8-4db5-97a3-a202fe0c4999"
                                          },
                                          {
                                              "name": "Tzaangor blades",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 4,
                                              "damage": 4,
                                              "criticalDamage": 5,
                                              "specialRules": [
                                                  {
                                                      "name": "Relentless",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, you can re-roll any or all of your attack dice."
                                                  }
                                              ],
                                              "id": "626f9278-79a3-484b-88d8-27a62fda9d08"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "TZAANGOR (ICON BEARER)",
                                      "description": "Driven by a desire to accumulate arcane knowledge, Tzaangor hope to use such a prize to gain even more favour with Tzeentch. Particularly fervent Tzaangor carry sinuous icons and banners they believe draw their deity's gaze and mutative blessings.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 6,
                                      "wounds": 8,
                                      "keyWords": [
                                          "THOUSAND SONS",
                                          "CHAOS",
                                          "TZAANGOR",
                                          "ICON BEARER"
                                      ],
                                      "abilities": [
                                          {
                                              "name": "Favoured by Change",
                                              "description": "This operative has a 5+ invulnerable save.",
                                              "id": "a12c2fdc-d192-4081-a077-a10d00f027cf"
                                          },
                                          {
                                              "name": "Icon Bearer",
                                              "description": "When determining control of an objective marker, treat this operative's APL characteristic as being 1 higher. Note that this is not a modifier.",
                                              "id": "07848b91-e123-4c8c-bb13-25878293be2e"
                                          }
                                      ],
                                      "uniqueActions": [
                                          {
                                              "name": "Herd Banner",
                                              "cost": 1,
                                              "description": " Until the end of the Turning Point, while this operative is within 3' of a friendly TZAANGOR operative, that operative is invigorated by chaos. While an operative is invigorated by chaos, improve its invulnerable save by 1.",
                                              "id": "7f67ff6d-8031-4dd5-9219-b8c598942d8f"
                                          }
                                      ],
                                      "id": "7cf3a62c-bbe5-488d-bfe7-a55514baf565",
                                      "currentWounds": 8,
                                      "unitType": [
                                          "combat",
                                          "scout"
                                      ],
                                      "unitImageName": "TZAANGOR_ICON_BEARER",
                                      "selectedMeleeWeapon": {
                                          "name": "Dagger",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 4,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "id": "0ff39b39-b334-4ad1-810d-4e3b5a5bb94e"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Dagger",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 4,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "id": "0ff39b39-b334-4ad1-810d-4e3b5a5bb94e"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "TZAANGOR (HORN BEARER)",
                                      "description": "Often, a member of a Tzaangor flock will carry a daemonically mawed instrument, the piercing blasts of which stir its fellow creatures into a bestial frenzy Their shrill cries echo and their horns clatter together as they vie to be first into the fray to hack their victims apart.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 6,
                                      "wounds": 8,
                                      "keyWords": [
                                          "THOUSAND SONS",
                                          "CHAOS",
                                          "TZAANGOR",
                                          "HORN BEARER"
                                      ],
                                      "abilities": [
                                          {
                                              "name": "Favoured by Change",
                                              "description": "This operative has a 5+ invulnerable save.",
                                              "id": "196701e0-e98b-469d-982d-199def49cb16"
                                          }
                                      ],
                                      "uniqueActions": [
                                          {
                                              "name": "Brayhorn",
                                              "cost": 1,
                                              "description": "Until the end of the Turning Point, each time a friendly TZAANGOR operative performs a Normal Move or Charge action, it can move an additional 1'.",
                                              "id": "6670ccb0-0780-4d43-a5ba-77d36f42ad06"
                                          }
                                      ],
                                      "id": "9fffe7a9-1dbf-4c79-9ffc-5dab110d4d81",
                                      "currentWounds": 8,
                                      "unitType": [
                                          "combat",
                                          "scout"
                                      ],
                                      "unitImageName": "TZAANGOR_HORN_BEARER",
                                      "selectedMeleeWeapon": {
                                          "name": "Dagger",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 4,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "id": "735e6242-68b5-4d77-91b5-20b0f4de1f40"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Dagger",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 4,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "id": "735e6242-68b5-4d77-91b5-20b0f4de1f40"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "TWISTBRAY",
                                      "description": "The most savage member of a flock is a Twistbray, usually bearing the most extreme mutations of their creator-god. Raised by Tzeentch above the predatory instincts of their fellow mutants, they exhibit great cunning, formulating complex ploys to outwit their prey.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 6,
                                      "wounds": 9,
                                      "selectedRangeWeapon": {
                                          "name": "Autopistol",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 4,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "specialRules": [
                                              {
                                                  "name": "Rng 6'",
                                                  "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                              }
                                          ],
                                          "id": "78b69582-618d-4ee9-9970-49f970a804e9"
                                      },
                                      "keyWords": [
                                          "THOUSAND SONS",
                                          "CHAOS",
                                          "LEADER",
                                          "TZAANGOR",
                                          "TWISTBRAY"
                                      ],
                                      "abilities": [
                                          {
                                              "name": "Favoured by Change",
                                              "description": "This operative has a 5+ invulnerable save.",
                                              "id": "7f944b86-1af7-413f-9504-89c62586a8df"
                                          }
                                      ],
                                      "id": "4ceb3377-4133-4032-9b8f-91731f695c7d",
                                      "currentWounds": 9,
                                      "unitType": [
                                          "combat",
                                          "scout"
                                      ],
                                      "unitImageName": "TWISTBRAY",
                                      "selectedMeleeWeapon": {
                                          "name": "Chainsword",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 4,
                                          "criticalDamage": 5,
                                          "id": "d2c312b0-2ada-4f88-8d0f-a1e0fffc04ef"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Autopistol",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 4,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  }
                                              ],
                                              "id": "78b69582-618d-4ee9-9970-49f970a804e9"
                                          },
                                          {
                                              "name": "Chainsword",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 4,
                                              "criticalDamage": 5,
                                              "id": "d2c312b0-2ada-4f88-8d0f-a1e0fffc04ef"
                                          },
                                          {
                                              "name": "Tzaangor blades",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 4,
                                              "criticalDamage": 5,
                                              "specialRules": [
                                                  {
                                                      "name": "Relentless",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, you can re-roll any or all of your attack dice."
                                                  }
                                              ],
                                              "id": "37ac1bea-bbf3-4c88-bf70-b7ea198593d5"
                                          }
                                      ]
                                  }
                              ],
                              "currentDataslates": [],
                              "id": "d219795a-4221-4f25-8667-375327c29fb7",
                              "defaultDataslates": {
                                  "066f1487-434c-4b04-b69a-47b8922b28ac": 6
                              }
                          }
                      ],
                      "chosenFireTeams": [],
                      "ploys": [
                          {
                              "name": "MALICIOUS VOLLEYS",
                              "description": "Until the end of the Turning Point, each time a friendly ARCANA ASTARTES operative is activated, if it does not perform a Fight action during that activation, it can perform two Shoot actions during that activation if a bolt weapon is selected for each of those shooting attacks. A bolt weapon is a ranged weapon that includes 'bolt' in its name (excluding Doombolt), e.g. inferno boltgun.",
                              "cost": 1,
                              "type": "strategic",
                              "id": "48794e12-22dc-46e7-ba85-630326f3a36c"
                          },
                          {
                              "name": "SORCEROUS AUTOMATA",
                              "description": "Until the end of the Turning Point, each time a friendly ARCANA ASTARTES operative (excluding an ASPIRING SORCERER operative) is activated while Visible to and within 3' of a ready friendly ARCANA ASTARTES ASPIRING SORCERER operative, add 1 to its APL.",
                              "cost": 1,
                              "type": "strategic",
                              "id": "0e2cc9ce-9d92-46d6-af08-0c0751b241e1"
                          },
                          {
                              "name": "INHUMAN SAVAGERY",
                              "description": "Until the end of the Turning Point, each time a friendly TZAANGOR operative fights in combat, if it performed a Charge action during that activation, in the Roll Attack Dice step of that combat, if you retain two or more successful hits, you can retain one failed hit as a successful normal hit.",
                              "cost": 1,
                              "type": "strategic",
                              "id": "2708c0d5-0277-4c8d-a6a2-7df64390a722"
                          },
                          {
                              "name": "TREASURE HUNTER",
                              "description": "Use this Tactical Ploy when a friendly TZAANGOR operative is activated. During that activation, that operative can perform a mission action or the Pick Up action for one less AP (to a minimum of 0AP).",
                              "cost": 1,
                              "type": "tactical",
                              "id": "1eb29dc1-f20d-436a-aa4e-0f4e88fd3abb"
                          },
                          {
                              "name": "SORCEROUS FOCUS",
                              "description": "Use this Tactical Ploy after rolling one D6 to resolve a second psychic power with a friendly ASPIRING SORCERER operative that is not within Engagement Range of enemy operatives. Treat the result of that D6 as a 6.",
                              "cost": 1,
                              "type": "tactical",
                              "id": "a8a08aab-c7eb-40fe-aa05-a3dd5ba574ef"
                          },
                          {
                              "name": "INFERNAL FUSILLADE",
                              "description": "Use this Tactical Ploy after making a shooting attack with a friendly ARCANA ASTARTES operative (excluding an ASPIRING SORCERER operative) in which the target did not lose any wounds. Repeat that shooting attack.",
                              "cost": 1,
                              "type": "tactical",
                              "id": "2444e789-c0c0-4b1b-8c65-351103da69d5"
                          }
                      ],
                      "equipments": [
                          {
                              "name": "ALL-SEEING EYE",
                              "description": "The operative gains the following ability for the battle:",
                              "cost": 2,
                              "maxNumberPerKillTeam": 1,
                              "id": "b7e47d75-63e5-471c-a9d6-69f4e54b6772",
                              "ability": {
                                  "name": "All-seeing Eye",
                                  "description": "Once per battle, you can do one of the following:",
                                  "subText": [
                                      "If this operative is ready, you can activate it after activating a friendly operative.",
                                      "You can activate a ready friendly operative after activating this operative."
                                  ],
                                  "id": "1a65e985-0f69-4d09-80f7-6f21b835b8f5"
                              }
                          },
                          {
                              "name": "RUNIC WARD",
                              "description": "The operative gains the following ability for the battle:",
                              "cost": 3,
                              "maxNumberPerKillTeam": 1,
                              "id": "8cb0143c-ec69-4eea-9266-79b4427daaf7",
                              "ability": {
                                  "name": "Runic Ward",
                                  "description": "Once per battle, in the Roll Defence Dice step of a shooting attack made against this operative, you can use this ability. If you do so, you can retain one of your failed saves as a successful normal save, or one of your successful normal saves as a successful critical save.",
                                  "id": "cd87350a-1bf6-4c24-b364-28d237b9deb4"
                              }
                          },
                          {
                              "name": "GARGOYLE BAYONET",
                              "description": "The operative is equipped with the following melee weapon for the battle:",
                              "cost": 2,
                              "id": "58d2a0b8-08c2-4635-af58-556e44265016",
                              "weapon": {
                                  "name": "Gargoyle Bayonet",
                                  "type": "close",
                                  "attacks": 3,
                                  "ballisticWeaponSkill": 3,
                                  "damage": 4,
                                  "criticalDamage": 4,
                                  "id": "d9a93c53-fda1-4d65-a460-d1fe94931a60"
                              }
                          },
                          {
                              "name": "MYTHIC SCROLL",
                              "description": "ASPIRING SORCERER operative only. The operative gains the following ability for the battle:",
                              "cost": 2,
                              "maxNumberPerKillTeam": 1,
                              "id": "9a773497-f2ad-443d-9d16-ba47c9839023",
                              "ability": {
                                  "name": "Mythic Scroll",
                                  "description": "Once per battle, during this operative's activation, it can perform a free Manifest Psychic Power action.",
                                  "id": "1fcb459a-9a9d-4f0d-bd03-340dec4534ea"
                              }
                          },
                          {
                              "name": "TREASURE TRINKET",
                              "description": "TZAANGOR operative only. Add 1CP to your pool for the battle.",
                              "cost": 3,
                              "maxNumberPerKillTeam": 1,
                              "id": "616273d9-c129-4a40-8457-07e858782eb7"
                          },
                          {
                              "name": "GILDED HORNS",
                              "description": "TZAANGOR operative only. The operative gains the following ability for the battle:",
                              "cost": 1,
                              "id": "b5348ba2-8a18-4187-8dee-11e4c6f6037b",
                              "ability": {
                                  "name": "Gilded Horns",
                                  "description": "Each time this operative fights in combat, if it performed a Charge action during that activation, in the Roll Attack Dice step of that combat, you can retain one of your attack dice results of 5+ that is a successful hit as a critical hit.",
                                  "id": "8b816f64-5369-4918-a960-8a1876bdfce6"
                              }
                          },
                          {
                              "name": "ENSORCELLED ROUNDS",
                              "description": "TZAANGOR operative only. Select one autopistol the operative is equipped with. Add 1 to that weapon's Damage characteristics for the battle.",
                              "cost": 2,
                              "id": "a38c3846-952f-4311-9b7f-1c77740f4cc3"
                          }
                      ],
                      "countEquipmentPoint": 10,
                      "psychicPowerDescriptions": [
                          "The first time a friendly THOUSAND SONS ASPIRING SORCERER operative performs the Manifest Psychic Power action (see Aspiring Sorcerer datacard) in each of its activations, select one psychic power from the list below to be resolved.",
                          "The second time a friendly THOUSAND SONS ASPIRING SORCERER operative performs the Manifest Psychic Power action in each of its activations, roll one D6: on a 1-2, that operative suffers 3 mortal wounds. On a 3+, select one psychic power from the list below to be resolved. You cannot select one that you have resolved during this Turning Point."
                      ],
                      "psychicPowers": [
                          {
                              "name": "DOOMBOLT",
                              "description": "Perform a free Shoot action using the following ranged weapon.",
                              "weapon": {
                                  "name": "Doombolt",
                                  "type": "range",
                                  "attacks": 4,
                                  "ballisticWeaponSkill": 3,
                                  "damage": 3,
                                  "criticalDamage": 3,
                                  "specialRules": [
                                      {
                                          "name": "Lethal 5+",
                                          "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, your attack dice results of equal to or greater than x that are successful hits are critical hits, x is the number after the weapon's Lethal, e.g. Lethal 5+."
                                      }
                                  ],
                                  "criticalHitSpecialRules": [
                                      {
                                          "name": "MW2",
                                          "description": "Mortal wounds. Each time a friendly operative makes a shooting attack with this weapon, in the roll attack dice step of that shooting attack, for each critical hit retained, inflict x mortal wounds on the target, x is the number after the weapon's MW, e.g. MW3."
                                      }
                                  ],
                                  "id": "8109dc7c-7b2d-4d30-b2c2-eaf857a7fb50"
                              },
                              "id": "492e28c6-e01a-4fee-95d1-7092da5356c1"
                          },
                          {
                              "name": "FIRESTORM",
                              "description": "Perform a free Shoot action using the following ranged weapon.",
                              "weapon": {
                                  "name": "Firestorm",
                                  "type": "range",
                                  "attacks": 5,
                                  "ballisticWeaponSkill": 4,
                                  "damage": 2,
                                  "criticalDamage": 2,
                                  "specialRules": [
                                      {
                                          "name": "Indirect",
                                          "description": "Each time a friendly operative makes a shooting attack with this weapon, in the select valid target step of that shooting attack, enemy operatives are not in Cover."
                                      },
                                      {
                                          "name": "Barrage",
                                          "description": "Each time a friendly operative makes a shooting attack with this weapon, for that shooting attack, the automatic retention of defence dice as a result of cover is determined differently. Instead, if any part of the operative's base is hidden by a terrain feature from directly above, the defender can retain defence dice as if they were in cover."
                                      },
                                      {
                                          "name": "Blast 1'",
                                          "description": "Each time a friendly operative performs a Shoot action and selects this weapon (or, in the case of profiles, this weapon's profile), after making the shooting attack against the target, make a shooting attack with this weapon (using the same profile) against each other operative Visible to and within X of the original target - each of them is a valid target and cannot be in Cover. X is the distance after the weapon's Blast, e.g. Blast 2'. An operative cannot make a shooting attack with this weapon by performing an Overwatch action."
                                      }
                                  ],
                                  "id": "2693e1ee-128a-4d4a-b313-b7a96028ab0e"
                              },
                              "id": "2ba68760-73bb-4ada-b8c0-c04aee2d8811"
                          },
                          {
                              "name": "IMMATERIAL SURGE",
                              "description": "Select one other friendly THOUSAND SONS operative Visible to this operative. Add 1 to its APL.",
                              "id": "5b4e61f5-8d8d-43b3-a236-224ed6dd6be3"
                          },
                          {
                              "name": "TWIST OF FATE",
                              "description": "Select one enemy operative Visible to this operative. Until the end of the Turning Point, each time a friendly THOUSAND SONS operative fights in combat or makes a shooting attack against that enemy operative, in the Roll Attack Dice step of that combat or shooting attack, you can re-roll one of your attack dice.",
                              "id": "5c9a7bf8-950c-4cbc-a566-871d41d1ad4c"
                          }
                      ],
                      "id": "c8e1729d-4340-4d90-bb8d-fd73829c362a"
                  },
                  {
                      "factionName": "Thousand Sons",
                      "killTeamName": "Warpcoven",
                      "countOfFireTeam": 1,
                      "factionLogo": "WARPCOVEN",
                      "fireTeams": [
                          {
                              "name": "Warpcoven",
                              "archetype": [
                                  "security",
                                  "recon"
                              ],
                              "availableDataslates": [
                                  {
                                      "name": "SORCERER",
                                      "description": "Sorcerers are the leaders of the Thousand Sons Legion. They control forces of Rubric Marines, having yoked them to their will. Sorcerers direct them like puppeteers, employing Rubric Marines as bulwarks against their foes as they channel dark magics of manipulation through their psychically attuned weapons.",
                                      "movement": 3,
                                      "actionPointLimit": 3,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 3,
                                      "wounds": 13,
                                      "selectedRangeWeapon": {
                                          "name": "Inferno bolt pistol",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "specialRules": [
                                              {
                                                  "name": "Rng 6'",
                                                  "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                              },
                                              {
                                                  "name": "AP1",
                                                  "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                              }
                                          ],
                                          "id": "99938c08-9892-4db4-b507-614001db654e"
                                      },
                                      "abilities": [
                                          {
                                              "name": "Favoured by Change",
                                              "description": "This operative has a 5+ invulnerable save.",
                                              "id": "c2ef00d7-cfc5-40e2-9ec5-6695d50eb439"
                                          },
                                          {
                                              "name": "Rubric Command",
                                              "description": "Once per Turning Point, when a friendly RUBRIC MARINE operative Visible to and within 3' of this operative is activated, you can add 1 to that friendly operative's APL.",
                                              "id": "7e7c152d-38a0-4326-ba7a-e7ac7efde8bf"
                                          },
                                          {
                                              "name": "Relentless",
                                              "description": "If this operative is equipped with a force stave and a Prosperine khopesh, those melee weapons gain the Relentless special rule for this operative.",
                                              "id": "c4d157c2-6f76-4e5c-8c04-34214a5373ba"
                                          }
                                      ],
                                      "uniqueActions": [
                                          {
                                              "name": "Manifest Psychic Power",
                                              "cost": 1,
                                              "description": "Psychic action. Resolve a WARPCOVEN psychic power. This operative cannot perform this action while within Engagement Range of an enemy operative.",
                                              "id": "ed8376b9-4e1c-452b-aa7d-7cff07658526"
                                          }
                                      ],
                                      "keyWords": [
                                          "WARPCOVEN",
                                          "CHAOS",
                                          "ARCANA ASTARTES",
                                          "<GREAT CULT>",
                                          "PSYKER",
                                          "SORCERER"
                                      ],
                                      "id": "5d4d75dd-2597-425c-9e17-d6432987d688",
                                      "currentWounds": 13,
                                      "unitType": [
                                          "combat",
                                          "staunch",
                                          "marksman"
                                      ],
                                      "unitImageName": "SORCERER",
                                      "selectedMeleeWeapon": {
                                          "name": "Force stave",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 4,
                                          "criticalDamage": 6,
                                          "criticalHitSpecialRules": [
                                              {
                                                  "name": "Stun",
                                                  "description": "Each time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, if you retain any critical hits, subtract 1 from the target's APL. Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat:",
                                                  "subText": [
                                                      "The first time you strike with a critical hit, select one of your opponent's normal hits from that combat to be discarded",
                                                      "The second time you strike with a critical hit, subtract 1 from the target's APL."
                                                  ]
                                              }
                                          ],
                                          "id": "b077b764-48ac-4278-9df7-c8372e025eda"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Inferno bolt pistol",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  },
                                                  {
                                                      "name": "AP1",
                                                      "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                  }
                                              ],
                                              "id": "99938c08-9892-4db4-b507-614001db654e"
                                          },
                                          {
                                              "name": "Warpflame pistol",
                                              "type": "range",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 2,
                                              "criticalDamage": 4,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  },
                                                  {
                                                      "name": "Torrent 1'",
                                                      "description": "Each time a friendly operative performs a Shoot action or Overwatch action and selects this weapon, after making the shooting attack against the target, it can make a shooting attack with this weapon against each other valid target within x of the original target and each other, x is the distance after the weapon's Torrent, e.g. Torrent 2'."
                                                  },
                                                  {
                                                      "name": "AP1",
                                                      "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                  }
                                              ],
                                              "id": "a314805c-8e68-47c4-bc41-ec7631b74395"
                                          },
                                          {
                                              "name": "Force stave",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 4,
                                              "criticalDamage": 6,
                                              "criticalHitSpecialRules": [
                                                  {
                                                      "name": "Stun",
                                                      "description": "Each time a friendly operative makes a shooting attack with this weapon, in the Roll Attack Dice step of that shooting attack, if you retain any critical hits, subtract 1 from the target's APL. Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat:",
                                                      "subText": [
                                                          "The first time you strike with a critical hit, select one of your opponent's normal hits from that combat to be discarded",
                                                          "The second time you strike with a critical hit, subtract 1 from the target's APL."
                                                      ]
                                                  }
                                              ],
                                              "id": "b077b764-48ac-4278-9df7-c8372e025eda"
                                          },
                                          {
                                              "name": "Prosperine khopesh",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 4,
                                              "criticalDamage": 6,
                                              "specialRules": [
                                                  {
                                                      "name": "Lethal 5+",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, your attack dice results of equal to or greater than x that are successful hits are critical hits, x is the number after the weapon's Lethal, e.g. Lethal 5+."
                                                  }
                                              ],
                                              "id": "23ae2a4e-6b0e-44c9-bc91-290d4c24073d"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "RUBRIC MARINE GUNNER",
                                      "description": "With their armour s servos infused with sorcery, the spirit within a Rubric Marine s shell easily hefts the heaviest of arcane weapons. Their soulreaper cannons shoot streams of shells alight with magical power, while their warpflamers unleash gouts of iridescent fire.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 3,
                                      "wounds": 12,
                                      "selectedRangeWeapon": {
                                          "name": "Soulreaper cannon",
                                          "type": "range",
                                          "attacks": 6,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "specialRules": [
                                              {
                                                  "name": "AP1",
                                                  "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                              },
                                              {
                                                  "name": "Fusillade",
                                                  "description": "Each time a friendly operative performs a Shoot action and selects this weapon, after selecting a valid target, you can select any number of other valid targets within 2' of the original target. Distribute your attack dice between the targets you have selected. Make a shooting attack with this weapon (using the same profile) against each of the targets you have selected using the attack dice you have distributed to each of them."
                                              }
                                          ],
                                          "id": "6902f9a8-caf9-4b91-b247-818211ce4d6b"
                                      },
                                      "abilities": [
                                          {
                                              "name": "Favoured by Change",
                                              "description": "This operative has a 5+ invulnerable save.",
                                              "id": "c262565b-6c05-4a2f-a23e-36942226bc1f"
                                          },
                                          {
                                              "name": "All Is Dust",
                                              "description": "",
                                              "subText": [
                                                  "Each time a shooting attack is made against this operative, if the ranged weapon has a Normal Damage characteristic of 3 or less, this operative is treated as having a Save characteristic of 2+ for that shooting attack.",
                                                  "This operative cannot move more than 6' during its activation."
                                              ],
                                              "id": "4a1fe57c-1b05-488d-8df4-9e783322c3fa"
                                          }
                                      ],
                                      "keyWords": [
                                          "WARPCOVEN",
                                          "CHAOS",
                                          "ARCANA ASTARTES",
                                          "<GREAT CULT>",
                                          "RUBRIC MARINE",
                                          "GUNNER"
                                      ],
                                      "id": "6d26f1f7-0e3d-4b60-b73e-31073faad4de",
                                      "currentWounds": 12,
                                      "unitType": [
                                          "staunch",
                                          "marksman"
                                      ],
                                      "unitImageName": "RUBRIC_MARINE_GUNNER",
                                      "selectedMeleeWeapon": {
                                          "name": "Fists",
                                          "type": "close",
                                          "attacks": 3,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "id": "6b09a5c5-4d92-404c-8808-4d3f287244b1"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Soulreaper cannon",
                                              "type": "range",
                                              "attacks": 6,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "specialRules": [
                                                  {
                                                      "name": "AP1",
                                                      "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                  },
                                                  {
                                                      "name": "Fusillade",
                                                      "description": "Each time a friendly operative performs a Shoot action and selects this weapon, after selecting a valid target, you can select any number of other valid targets within 2' of the original target. Distribute your attack dice between the targets you have selected. Make a shooting attack with this weapon (using the same profile) against each of the targets you have selected using the attack dice you have distributed to each of them."
                                                  }
                                              ],
                                              "id": "6902f9a8-caf9-4b91-b247-818211ce4d6b"
                                          },
                                          {
                                              "name": "Warpflamer",
                                              "type": "range",
                                              "attacks": 5,
                                              "ballisticWeaponSkill": 2,
                                              "damage": 2,
                                              "criticalDamage": 4,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  },
                                                  {
                                                      "name": "Torrent 2'",
                                                      "description": "Each time a friendly operative performs a Shoot action or Overwatch action and selects this weapon, after making the shooting attack against the target, it can make a shooting attack with this weapon against each other valid target within x of the original target and each other, x is the distance after the weapon's Torrent, e.g. Torrent 2'."
                                                  },
                                                  {
                                                      "name": "AP1",
                                                      "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                  }
                                              ],
                                              "id": "0d01955f-e82e-4f6c-9eda-d00633282145"
                                          },
                                          {
                                              "name": "Fists",
                                              "type": "close",
                                              "attacks": 3,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "id": "6b09a5c5-4d92-404c-8808-4d3f287244b1"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "RUBRIC MARINE ICON BEARER",
                                      "description": "Rubric Marines and their masters ultimately serve the schemes of the Chaos God Tzeentch. Some of these arcane warriors bear sorcerous icons of the Changer of the Ways that crawl with coruscating energy, which the sorcerers can draw upon to enhance their power.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 3,
                                      "wounds": 12,
                                      "selectedRangeWeapon": {
                                          "name": "Inferno boltgun",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "specialRules": [
                                              {
                                                  "name": "AP1",
                                                  "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                              }
                                          ],
                                          "id": "92ad01a0-2f91-4f37-8ec0-617bc0fb282a"
                                      },
                                      "abilities": [
                                          {
                                              "name": "Favoured by Change",
                                              "description": "This operative has a 5+ invulnerable save.",
                                              "id": "b3168c17-708f-4936-9fc7-bd0552cb6e8c"
                                          },
                                          {
                                              "name": "All Is Dust",
                                              "description": "",
                                              "subText": [
                                                  "Each time a shooting attack is made against this operative, if the ranged weapon has a Normal Damage characteristic of 3 or less, this operative is treated as having a Save characteristic of 2+ for that shooting attack.",
                                                  "This operative cannot move more than 6' during its activation."
                                              ],
                                              "id": "6dc6b53c-48ab-43e4-bf1f-c8524e545f12"
                                          },
                                          {
                                              "name": "Icon Bearer",
                                              "description": "When determining control of an objective marker, treat this operative's APL characteristic as being 1 higher. Note that this is not a modifier. In narrative play, this is cumulative with the Focused Battle Honour.",
                                              "id": "7d72e75e-70a0-44f2-8ba0-1f358c17d86d"
                                          },
                                          {
                                              "name": "Icon of Flame",
                                              "description": " Once per Turning Point, a friendly SORCERER operative within 6' of this operative can perform a free Manifest Psychic Power action during that SORCERER operative's activation.",
                                              "id": "749ef583-4f17-4b27-ab3f-3a9481d44a0d"
                                          }
                                      ],
                                      "keyWords": [
                                          "WARPCOVEN",
                                          "CHAOS",
                                          "ARCANA ASTARTES",
                                          "<GREAT CULT>",
                                          "RUBRIC MARINE",
                                          "ICON BEARER"
                                      ],
                                      "id": "1eb1aab5-5f8a-40c4-90a7-f104da2b5d79",
                                      "currentWounds": 12,
                                      "unitType": [
                                          "staunch"
                                      ],
                                      "unitImageName": "RUBRIC_MARINE_ICON_BEARER",
                                      "selectedMeleeWeapon": {
                                          "name": "Fists",
                                          "type": "close",
                                          "attacks": 3,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "id": "23493af6-20ae-4d32-aab7-befe0d1daff9"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Inferno boltgun",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "specialRules": [
                                                  {
                                                      "name": "AP1",
                                                      "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                  }
                                              ],
                                              "id": "92ad01a0-2f91-4f37-8ec0-617bc0fb282a"
                                          },
                                          {
                                              "name": "Fists",
                                              "type": "close",
                                              "attacks": 3,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "id": "23493af6-20ae-4d32-aab7-befe0d1daff9"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "RUBRIC MARINE WARRIOR",
                                      "description": "Rubric Marine Warriors are animated suits of power armour inhabited by the bound souls of their former occupants. With ruinous proficiency, they steadily fire bursts of eldritch shells from their inferno boltguns, advancing upon their psychic masters' enemies.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 3,
                                      "wounds": 12,
                                      "selectedRangeWeapon": {
                                          "name": "Inferno boltgun",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "specialRules": [
                                              {
                                                  "name": "AP1",
                                                  "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                              }
                                          ],
                                          "id": "972577b7-3c02-490f-b461-19dfabffd77b"
                                      },
                                      "abilities": [
                                          {
                                              "name": "Favoured by Change",
                                              "description": "This operative has a 5+ invulnerable save.",
                                              "id": "7dbaaef9-a937-4e33-8238-17ed9237a4ab"
                                          },
                                          {
                                              "name": "All Is Dust",
                                              "description": "",
                                              "subText": [
                                                  "Each time a shooting attack is made against this operative, if the ranged weapon has a Normal Damage characteristic of 3 or less, this operative is treated as having a Save characteristic of 2+ for that shooting attack.",
                                                  "This operative cannot move more than 6' during its activation."
                                              ],
                                              "id": "e5586929-1bd0-4cdd-bb21-92ef2f840e77"
                                          }
                                      ],
                                      "keyWords": [
                                          "WARPCOVEN",
                                          "CHAOS",
                                          "ARCANA ASTARTES",
                                          "<GREAT CULT>",
                                          "RUBRIC MARINE",
                                          "WARRIOR"
                                      ],
                                      "id": "3022f57f-9aa5-46cd-9dcc-0caa5340a6a8",
                                      "currentWounds": 12,
                                      "unitType": [
                                          "staunch",
                                          "marksman"
                                      ],
                                      "unitImageName": "RUBRIC_MARINE_WARRIOR",
                                      "selectedMeleeWeapon": {
                                          "name": "Fists",
                                          "type": "close",
                                          "attacks": 3,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "id": "94a1025b-f745-4881-b0b0-d036fcf30c11"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Inferno boltgun",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "specialRules": [
                                                  {
                                                      "name": "AP1",
                                                      "description": "Armour Penetration. Each time a friendly operative makes a shooting attack with this weapon, subtract x from the Defence of the target for that shooting attack, x is the number after the weapon's AP, e.g. AP1. If two different APx special rules would be in effect for a shooting attack, they are not cumulative - the attacker selects which one to use."
                                                  }
                                              ],
                                              "id": "972577b7-3c02-490f-b461-19dfabffd77b"
                                          },
                                          {
                                              "name": "Fists",
                                              "type": "close",
                                              "attacks": 3,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "id": "94a1025b-f745-4881-b0b0-d036fcf30c11"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "TZAANGOR CHAMPION",
                                      "description": "These ferocious Tzaangor wield enormous two-handed blades or axes that can cleave enemy warriors in two. It takes fighters with great skill as well as aggression to carry such formidable weapons, and they cause as much terror as they do casualties.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 6,
                                      "wounds": 8,
                                      "keyWords": [
                                          "WARPCOVEN",
                                          "CHAOS",
                                          "TZAANGOR",
                                          "CHAMPION"
                                      ],
                                      "abilities": [
                                          {
                                              "name": "Favoured by Change",
                                              "description": "This operative has a 5+ invulnerable save.",
                                              "id": "dcfbb9d6-a80d-4ae0-8161-5584160a3517"
                                          }
                                      ],
                                      "uniqueActions": [
                                          {
                                              "name": "Savage Assault",
                                              "cost": 1,
                                              "description": "Perform a free Fight action with this operative. After completing that action's fight sequence, if this operative is still within Engagement Range of an enemy operative, you can immediately fight in combat with this operative again (you do not have to select the same target).",
                                              "id": "c4fbe682-7b00-4447-8530-a159001dd2f5"
                                          }
                                      ],
                                      "id": "a9b24550-73fc-4ae9-af19-55d5c1822c64",
                                      "currentWounds": 8,
                                      "unitType": [
                                          "combat"
                                      ],
                                      "unitImageName": "TZAANGOR_CHAMPION",
                                      "selectedMeleeWeapon": {
                                          "name": "Tzaangor greataxe",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 3,
                                          "damage": 4,
                                          "criticalDamage": 5,
                                          "specialRules": [
                                              {
                                                  "name": "Brutal",
                                                  "description": "Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat, your opponent can only parry with critical hits."
                                              },
                                              {
                                                  "name": "Lethal 5+",
                                                  "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, your attack dice results of equal to or greater than x that are successful hits are critical hits, x is the number after the weapon's Lethal, e.g. Lethal 5+."
                                              }
                                          ],
                                          "id": "384f3c55-50f1-4c54-874c-8a6f676925bf"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Tzaangor greataxe",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 4,
                                              "criticalDamage": 5,
                                              "specialRules": [
                                                  {
                                                      "name": "Brutal",
                                                      "description": "Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat, your opponent can only parry with critical hits."
                                                  },
                                                  {
                                                      "name": "Lethal 5+",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, your attack dice results of equal to or greater than x that are successful hits are critical hits, x is the number after the weapon's Lethal, e.g. Lethal 5+."
                                                  }
                                              ],
                                              "id": "384f3c55-50f1-4c54-874c-8a6f676925bf"
                                          },
                                          {
                                              "name": "Tzaangor greatblade",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 3,
                                              "damage": 4,
                                              "criticalDamage": 5,
                                              "specialRules": [
                                                  {
                                                      "name": "Lethal 5+",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, your attack dice results of equal to or greater than x that are successful hits are critical hits, x is the number after the weapon's Lethal, e.g. Lethal 5+."
                                                  }
                                              ],
                                              "criticalHitSpecialRules": [
                                                  {
                                                      "name": "Reap 1",
                                                      "description": "Each time a friendly operative fights in combat with this weapon, in the Resolve Successful Hits step of that combat, if you strike with a critical hit, inflict x mortal wounds on each other enemy operative Visible to the friendly operative and within 1' of it or the target operative, x is the number after the weapon's Reap (e.g. Reap 1)."
                                                  }
                                              ],
                                              "id": "10a6c3d9-1ef1-4e2b-9178-0181b72e34ca"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "TZAANGOR FIGHTER",
                                      "description": "Tzaangor fighters are mutated fusions of avian beasts and corrupted Humans. In thrall to Tzeentch, they serve the Thousand Sons as bestial shock troops, attacking with savage blows from ritual blades and tearing flesh with their jagged beaks.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 6,
                                      "wounds": 8,
                                      "selectedRangeWeapon": {
                                          "name": "Autopistol",
                                          "type": "range",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 4,
                                          "damage": 2,
                                          "criticalDamage": 3,
                                          "specialRules": [
                                              {
                                                  "name": "Rng 6'",
                                                  "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                              }
                                          ],
                                          "id": "d23701b2-b76a-4cfb-aa51-89486e10826f"
                                      },
                                      "keyWords": [
                                          "WARPCOVEN",
                                          "CHAOS",
                                          "TZAANGOR",
                                          "FIGHTER"
                                      ],
                                      "abilities": [
                                          {
                                              "name": "Favoured by Change",
                                              "description": "This operative has a 5+ invulnerable save.",
                                              "id": "34843286-1a5e-4a14-80b2-9d4534afb6ea"
                                          }
                                      ],
                                      "id": "f703de47-ce01-47bf-888c-17784d05b61d",
                                      "currentWounds": 8,
                                      "unitType": [
                                          "combat",
                                          "scout"
                                      ],
                                      "unitImageName": "TZAANGOR_FIGHTER",
                                      "selectedMeleeWeapon": {
                                          "name": "Chainsword",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 4,
                                          "damage": 4,
                                          "criticalDamage": 5,
                                          "id": "7e70b89d-7f24-425f-9f6f-1894a9be2e1c"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Autopistol",
                                              "type": "range",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 4,
                                              "damage": 2,
                                              "criticalDamage": 3,
                                              "specialRules": [
                                                  {
                                                      "name": "Rng 6'",
                                                      "description": "Range. Each time a friendly operative makes a shooting attack with this weapon, only operatives within x are a valid target, x is the distance after the weapon's Rng, e.g. Rng 6'. All other rules for selecting a valid target still apply."
                                                  }
                                              ],
                                              "id": "d23701b2-b76a-4cfb-aa51-89486e10826f"
                                          },
                                          {
                                              "name": "Chainsword",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 4,
                                              "damage": 4,
                                              "criticalDamage": 5,
                                              "id": "7e70b89d-7f24-425f-9f6f-1894a9be2e1c"
                                          },
                                          {
                                              "name": "Tzaangor blades",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 4,
                                              "damage": 4,
                                              "criticalDamage": 5,
                                              "specialRules": [
                                                  {
                                                      "name": "Relentless",
                                                      "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, you can re-roll any or all of your attack dice."
                                                  }
                                              ],
                                              "id": "39770db1-c770-40a2-8c9b-3ed30e8d3a40"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "TZAANGOR HORN BEARER",
                                      "description": "Often, a member of a Tzaangor flock will carry a daemonically mawed instrument, the piercing blasts of which stir its fellow creatures into a bestial frenzy. Their shrill cries echo and their horns clatter together as they vie to be first into the fray to hack their victims apart.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 6,
                                      "wounds": 8,
                                      "keyWords": [
                                          "WARPCOVEN",
                                          "CHAOS",
                                          "TZAANGOR",
                                          "HORN BEARER"
                                      ],
                                      "abilities": [
                                          {
                                              "name": "Favoured by Change",
                                              "description": "This operative has a 5+ invulnerable save.",
                                              "id": "cef90a05-45ab-4f8c-89fe-f8034d2ce0b7"
                                          }
                                      ],
                                      "uniqueActions": [
                                          {
                                              "name": "Brayhorn",
                                              "cost": 1,
                                              "description": "Until the end of the Turning Point, add 1' to the Movement characteristic of friendly TZAANGOR operatives.",
                                              "id": "41244015-c94d-4c9b-a815-af6301443df0"
                                          }
                                      ],
                                      "id": "1bb75e08-8203-4fc8-a58f-41826b768779",
                                      "currentWounds": 8,
                                      "unitType": [
                                          "combat",
                                          "scout"
                                      ],
                                      "unitImageName": "TZAANGOR_HORN_BEARER",
                                      "selectedMeleeWeapon": {
                                          "name": "Dagger",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 4,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "id": "c03a819e-deec-4dc7-9af9-a0457c7be5b4"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Dagger",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 4,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "id": "c03a819e-deec-4dc7-9af9-a0457c7be5b4"
                                          }
                                      ]
                                  },
                                  {
                                      "name": "TZAANGOR ICON BEARER",
                                      "description": "Driven by a desire to accumulate arcane knowledge, Tzaangor hope to use such a prize to gain even more favour with Tzeentch. Particularly fervent Tzaangor carry sinuous icons and banners they believe draw their deity's gaze and mutative blessings.",
                                      "movement": 3,
                                      "actionPointLimit": 2,
                                      "groupActivation": 1,
                                      "defense": 3,
                                      "save": 6,
                                      "wounds": 8,
                                      "keyWords": [
                                          "WARPCOVEN",
                                          "CHAOS",
                                          "TZAANGOR",
                                          "ICON BEARER"
                                      ],
                                      "abilities": [
                                          {
                                              "name": "Favoured by Change",
                                              "description": "This operative has a 5+ invulnerable save.",
                                              "id": "13188ca4-9fa0-44d6-88e9-2eba8a12e171"
                                          },
                                          {
                                              "name": "Icon Bearer",
                                              "description": "When determining control of an objective marker, treat this operative's APL characteristic as being 1 higher. Note that this is not a modifier.",
                                              "id": "9a443127-d327-4c67-b398-729ada0df126"
                                          }
                                      ],
                                      "uniqueActions": [
                                          {
                                              "name": "Herd Banner",
                                              "cost": 1,
                                              "description": "Until the end of the Turning Point, while a friendly TZAANGOR operative is within 3' of this operative, that friendly operative is invigorated by the herd banner. While an operative is invigorated by the herd banner, improve its invulnerable save by 1.",
                                              "id": "66c6a0d7-ad70-4178-8d7f-8d55fda656ae"
                                          }
                                      ],
                                      "id": "255271af-4fea-417d-9604-602f83f83de3",
                                      "currentWounds": 8,
                                      "unitType": [
                                          "combat",
                                          "scout"
                                      ],
                                      "unitImageName": "TZAANGOR_ICON_BEARER",
                                      "selectedMeleeWeapon": {
                                          "name": "Dagger",
                                          "type": "close",
                                          "attacks": 4,
                                          "ballisticWeaponSkill": 4,
                                          "damage": 3,
                                          "criticalDamage": 4,
                                          "id": "99acb345-72d4-4434-adf4-a23f43092ffa"
                                      },
                                      "equipments": [],
                                      "availableWeapons": [
                                          {
                                              "name": "Dagger",
                                              "type": "close",
                                              "attacks": 4,
                                              "ballisticWeaponSkill": 4,
                                              "damage": 3,
                                              "criticalDamage": 4,
                                              "id": "99acb345-72d4-4434-adf4-a23f43092ffa"
                                          }
                                      ]
                                  }
                              ],
                              "currentDataslates": [],
                              "id": "31503b7d-12e8-4084-93de-c260ffcf8c86",
                              "defaultDataslates": {
                                  "5d4d75dd-2597-425c-9e17-d6432987d688": 1,
                                  "3022f57f-9aa5-46cd-9dcc-0caa5340a6a8": 5
                              }
                          }
                      ],
                      "chosenFireTeams": [],
                      "ploys": [
                          {
                              "name": "EXALTED ASTARTES",
                              "description": "Until the end of the Turning Point, each time a friendly SORCERER operative is activated:",
                              "cost": 1,
                              "type": "strategic",
                              "id": "e66c0520-ad76-41b3-b8ee-acd67835a948",
                              "subTexts": [
                                  "If it does not perform a Shoot action during that activation, it can perform two Fight actions during that activation.",
                                  "If it does not perform a Fight action during that activation, it can perform two Shoot actions during that activation, but it must select different ranged weapons for those shooting attacks. Ranged weapons from a WARPCOVEN, psychic power are eligible weapons for this."
                              ]
                          },
                          {
                              "name": "PSYCHIC DOMINION",
                              "description": "Until the end of the Turning Point, friendly SORCERER operatives can perform the Manifest Psychic Power action twice during their activations.",
                              "cost": 1,
                              "type": "strategic",
                              "id": "3da85f4f-148a-4438-94bf-65588b813b10"
                          },
                          {
                              "name": "SLOW AND PURPOSEFUL",
                              "description": "Until the end of the Turning Point, each time a friendly RUBRIC MARINE operative makes a shooting attack, if it has not performed a Charge, Fall Back or Normal Move action during this Turning Point, in the Roll Attack Dice step of that shooting attack, you can re-roll any or all of your attack dice results of one result (e.g. results of 2).",
                              "cost": 1,
                              "type": "strategic",
                              "id": "dcdd4934-677f-44e0-9c47-ac3770db5709"
                          },
                          {
                              "name": "SAVAGE HERD",
                              "description": "Until the end of the Turning Point, each time a friendly TZAANGOR operative fights in combat, before rolling your attack dice for that combat, you can retain one as a successful normal hit without rolling it. If another friendly TZAANGOR operative is supporting them in that combat, you can retain one as a successful critical hit instead.",
                              "cost": 1,
                              "type": "strategic",
                              "id": "7a2140b0-e013-41ef-8426-5830d8230db5"
                          },
                          {
                              "name": "CAPRICIOUS PLAN",
                              "description": "Use this Tactical Ploy at the end of a friendly SORCERER operative's activation. You can immediately perform a free Dash action with that operative and change its order.",
                              "cost": 1,
                              "type": "tactical",
                              "id": "44d28bae-9fdb-4fe9-8ecf-eb2f73b7a2b4"
                          },
                          {
                              "name": "PSYCHIC CABAL",
                              "description": "Use this Tactical Ploy when you would select a psychic power to be resolved by a friendly SORCERER operative as a result of performing the Manifest Psychic Power action. You can select a psychic power from a discipline another friendly SORCERER operative within 6' of this operative has studied.",
                              "cost": 1,
                              "type": "tactical",
                              "id": "813f9a2d-33bf-4dbc-afe1-6e9a2b343c0a"
                          },
                          {
                              "name": "MUTANT HERD",
                              "description": "Use this Tactical Ploy at the start of a friendly TZAANGOR operative's activation. Select one other ready friendly TZAANGOR operative Visible to and within 3' of that operative that is eligible to be activated. After that operative's activation, activate that other operative before your opponent activates any operatives or performs an Overwatch action.",
                              "cost": 1,
                              "type": "tactical",
                              "id": "2f274c18-9276-456d-ab11-1ec1607292cf"
                          },
                          {
                              "name": "SCHEMES OF CHANGE",
                              "description": "Use this Tactical Ploy at the start of the Target Reveal step of the Strategy phase, or when you activate a ready friendly WARPCOVEN operative. Discard one of your Tac Ops (any victory points scored from it are lost) and randomly determine a new Tac Op from those remaining in your Tac Ops deck.",
                              "cost": 1,
                              "type": "tactical",
                              "id": "e886a6bd-2811-4982-b1f4-21154520f267"
                          }
                      ],
                      "equipments": [
                          {
                              "name": "GARGOYLE BAYONET",
                              "description": "RUBRIC MARINE operative only. The operative is equipped with the following melee weapon for the battle:",
                              "cost": 2,
                              "id": "202caf1f-d24c-4587-9292-848d0f8b48dd",
                              "weapon": {
                                  "name": "Gargoyle Bayonet",
                                  "type": "close",
                                  "attacks": 3,
                                  "ballisticWeaponSkill": 3,
                                  "damage": 4,
                                  "criticalDamage": 4,
                                  "id": "802f3304-52ef-46a7-b982-438ce0123bc8"
                              }
                          },
                          {
                              "name": "HIGH CAPACITY MAGAZINE",
                              "description": "Select one autopistol, inferno bolt pistol or inferno boltgun the operative is equipped with. That weapon gains the Ceaseless special rule for the battle. If you selected an autopistol, this equipment costs 1 EP; otherwise, it costs 3EP.",
                              "cost": 1,
                              "id": "a57d2c03-d0a8-40ab-9c3f-2e708e91793a"
                          },
                          {
                              "name": "OCCULT TALISMAN",
                              "description": "The operative gains the following ability for the battle:",
                              "cost": 2,
                              "id": "e2c96935-095f-43ea-97e5-7aaaab466b21",
                              "ability": {
                                  "name": "Occult Talisman",
                                  "description": "Each time this operative would lose a wound as a result of a mortal wound or a shooting attack from a psychic power, roll one D6: on a 5+, that wound is not lost.",
                                  "id": "bb96fcd4-bf88-47c3-b8e6-9883169503c2"
                              }
                          },
                          {
                              "name": "SORCEROUS SCROLL",
                              "description": "SORCERER operative only. The operative gains the following ability for the battle:",
                              "cost": 4,
                              "maxNumberPerKillTeam": 1,
                              "id": "62aea3c2-85e4-4843-85f8-0c306723b10c",
                              "ability": {
                                  "name": "Sorcerous Scroll",
                                  "description": "When this operative gains this ability, select one psychic power from a discipline this operative has not studied. Once in the battle, when this operative performs the Manifest Psychic Power action, if it is not within Engagement Range of an enemy operative, you can select that psychic power to be resolved for that action.",
                                  "id": "2fbf139c-9bce-41d3-ae5b-78caa7579d78"
                              }
                          },
                          {
                              "name": "ARCANE ROBES",
                              "description": "SORCERER operative only. The operative gains the following ability for the battle:",
                              "cost": 2,
                              "id": "5e4512b7-4a93-402f-aa73-c8e55b36ffa4",
                              "ability": {
                                  "name": "Arcane Robes",
                                  "description": "Once per battle, when Critical Damage would be inflicted upon this operative, you can use this ability. If you do so, that attack dice inflicts Normal Damage instead.",
                                  "id": "ec110baf-cc31-4034-b9c6-31668e3a3471"
                              }
                          },
                          {
                              "name": "ENSORCELLED ROUNDS",
                              "description": "TZAANGOR FIGHTER operative only. Select one autopistol the operative is equipped with. Add 1 to that weapon's Damage characteristics for the battle.",
                              "cost": 2,
                              "id": "fca7e26d-5bde-4ab3-ba01-62273e040a1f"
                          },
                          {
                              "name": "GILDED HORNS",
                              "description": "TZAANGOR operative only. The operative gams the following ability for the battle:",
                              "cost": 1,
                              "id": "a361dbd0-f669-45d7-bbe3-20fe458530cc",
                              "ability": {
                                  "name": "Gilded Horns",
                                  "description": "Each time this operative fights in combat, if it performed a Charge action during that activation, in the Roll Attack Dice step of that combat, you can retain one of your attack dice results of 5+ that is a successful hit as a critical hit.",
                                  "id": "23a4c5fb-2145-45a3-8141-4e598c9ba9cc"
                              }
                          }
                      ],
                      "countEquipmentPoint": 10,
                      "tacOps": [
                          {
                              "name": "SCRY SECRET",
                              "type": "factionTacOp",
                              "description": "Reveal this Tac Op in the Target Reveal step of the first Turning Point. Your opponent selects one of their operatives to be holding a secret.",
                              "subText": "Friendly SORCERER operatives can perform the following mission action:",
                              "firstCondition": "If a friendly operative performs the Scry Secret action, you score 1VP.",
                              "secondCondition": "At the end of the battle, if a friendly operative has performed the Scry Secret action and has not been incapacitated, you score 1VP.",
                              "victoryPoint": [
                                  1,
                                  1
                              ],
                              "id": "cbf24039-664a-4473-9c28-e7e0b77727aa",
                              "uniqueAction": {
                                  "name": "SCRY SECRET",
                                  "cost": 1,
                                  "description": "Psychic action. An operative can perform this action while within 6' of the enemy operative holding a secret. An operative cannot perform this action while within Engagement Range of an enemy operative. Your kill team can only perform this action once.",
                                  "id": "9b498a09-0c7a-47f7-b322-7fdf41311910"
                              },
                              "isCompletedConditions": []
                          },
                          {
                              "name": "SORCEROUS RITUAL",
                              "type": "factionTacOp",
                              "description": "Reveal this Tac Op when a friendly operative performs the Sorcerous Ritual action.",
                              "subText": "Friendly SORCERER operatives can perform the following mission action:",
                              "firstCondition": "If a friendly operative performs the Sorcerous Ritual action in two or more Turning Points, you score 1VP.",
                              "secondCondition": "If a friendly operative performs the Sorcerous Ritual action in three or more Turning Points, you score 1VP.",
                              "victoryPoint": [
                                  1,
                                  1
                              ],
                              "id": "7b01e343-2658-4d10-bb4f-107cc73b7398",
                              "uniqueAction": {
                                  "name": "SORCEROUS RITUAL",
                                  "cost": 1,
                                  "description": "Psychic action. An operative can perform this action while within 2' of a ritual site. An operative cannot perform this action while within 2' of an enemy operative. A ritual site is the centre of the killzone or an objective marker that is more than 6' from your drop zone. Once a friendly operative performs this action, friendly operatives can only perform this action again at the same ritual site.",
                                  "id": "eb5af7ee-34a0-4a19-9a70-e1498b2f48c5"
                              },
                              "isCompletedConditions": []
                          },
                          {
                              "name": "GRAND PLAN",
                              "type": "factionTacOp",
                              "description": "Reveal this Tac Op in the Target Reveal step of the first Turning Point. Your opponent selects one of their operatives and an objective marker that is more than 6' from their killzone edge (they cannot select an objective marker that can be removed during the battle).",
                              "firstCondition": "If that enemy operative is incapacitated, you score 1VP.",
                              "secondCondition": "If you control that objective marker at the end of any Turning Point, you score 1VP.",
                              "victoryPoint": [
                                  1,
                                  1
                              ],
                              "id": "091da564-928d-4988-8493-b9b000d72296",
                              "isCompletedConditions": []
                          }
                      ],
                      "psychicPowerDescriptions": [
                          "When a WARPCOVEN SORCERER operative is added to your roster or dataslate, you must select one of the following psychic disciplines for it to study: Destiny, Tempyric or Warpfire. That operative knows the psychic powers associated with that discipline.",
                          "Each time a friendly SORCERER operative performs the Manifest Psychic Power action, select one psychic power it knows to be resolved. You can only select each psychic power a maximum of once per Turning Point."
                      ],
                      "psychicPowers": [
                          {
                              "name": "WEAVE FATE",
                              "type": "DESTINY DISCIPLINE",
                              "description": "Select one friendly WARPCOVEN operative Visible to this operative. Until the end of the Turning Point, each time a shooting attack is made against that operative, in the Roll Defence Dice step of that shooting attack, you can re-roll any or all of your defence dice.",
                              "id": "6734a8cf-fbbf-48b0-89f0-b3272a51fa02"
                          },
                          {
                              "name": "TWIST DESTINY",
                              "type": "DESTINY DISCIPLINE",
                              "description": "Select one enemy operative Visible to this operative. Until the end of the Turning Point:",
                              "subText": [
                                  "Each time that enemy operative fights in combat or makes a shooting attack, in the Roll Attack Dice step of that shooting attack, your opponent cannot re-roll their attack dice.",
                                  "That enemy operative ignores all positive modifiers to its APL."
                              ],
                              "id": "16bcdd1a-5a93-4016-8ebc-9c6857af4129"
                          },
                          {
                              "name": "DOOMBOLT",
                              "type": "DESTINY DISCIPLINE",
                              "description": "Perform a free Shoot action using the following ranged weapon:",
                              "weapon": {
                                  "name": "Doombolt",
                                  "type": "range",
                                  "attacks": 4,
                                  "ballisticWeaponSkill": 3,
                                  "damage": 3,
                                  "criticalDamage": 3,
                                  "specialRules": [
                                      {
                                          "name": "Lethal 5+",
                                          "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, your attack dice results of equal to or greater than x that are successful hits are critical hits, x is the number after the weapon's Lethal, e.g. Lethal 5+."
                                      }
                                  ],
                                  "criticalHitSpecialRules": [
                                      {
                                          "name": "MW2",
                                          "description": "Mortal wounds. Each time a friendly operative makes a shooting attack with this weapon, in the roll attack dice step of that shooting attack, for each critical hit retained, inflict x mortal wounds on the target, x is the number after the weapon's MW, e.g. MW3."
                                      }
                                  ],
                                  "id": "2026d37e-8cbb-4b7f-abd2-cfbe3467c849"
                              },
                              "id": "f3ebdf83-cbaa-4141-8e42-7d63c8ffa4d5"
                          },
                          {
                              "name": "EPHEMERAL INSTABILITY",
                              "type": "TEMPYRIC DISCIPLINE",
                              "description": "Until the end of the Turning Point, subtract  from the distance that enemy operatives can move when performing Charge and Dash actions.",
                              "id": "5c3fb5d1-5d97-4a63-a7e4-f1563dd7263c"
                          },
                          {
                              "name": "TEMPORAL MANIPULATION",
                              "type": "TEMPYRIC DISCIPLINE",
                              "description": "Select one friendly WARPCOVEN operative Visible to and within 6' of this operative. That operative regains 2D3 lost wounds.",
                              "id": "8653c7a0-e0a4-4f2f-a6c2-01508dc5d230"
                          },
                          {
                              "name": "FLUXBLAST",
                              "type": "TEMPYRIC DISCIPLINE",
                              "description": "Perform a free Shoot action using the following ranged weapon:",
                              "weapon": {
                                  "name": "Fluxblast",
                                  "type": "range",
                                  "attacks": 4,
                                  "ballisticWeaponSkill": 3,
                                  "damage": 3,
                                  "criticalDamage": 4,
                                  "specialRules": [
                                      {
                                          "name": "Blast 2'",
                                          "description": "Each time a friendly operative performs a Shoot action and selects this weapon (or, in the case of profiles, this weapon's profile), after making the shooting attack against the target, make a shooting attack with this weapon (using the same profile) against each other operative Visible to and within X of the original target - each of them is a valid target and cannot be in Cover. X is the distance after the weapon's Blast, e.g. Blast 2'. An operative cannot make a shooting attack with this weapon by performing an Overwatch action."
                                      }
                                  ],
                                  "criticalHitSpecialRules": [
                                      {
                                          "name": "Rending",
                                          "description": "Each time a friendly operative fights in combat or makes a shooting attack with this weapon, in the Roll Attack Dice step of that combat or shooting attack, if you retain any critical hits you can retain one normal hit as a critical hit."
                                      }
                                  ],
                                  "id": "730640dd-9f1d-437b-ab1f-bdfa63462ec1"
                              },
                              "id": "42439f3a-81cf-41a5-95e7-4d25a2d8f491"
                          },
                          {
                              "name": "WARP PORTAL",
                              "type": "WARPFIRE DISCIPLINE",
                              "description": "Select one friendly WARPCOVEN operative Visible to and within 3' of this operative that has not performed an action in which it moved during this Turning Point. Remove it from the killzone and set it back up again within 6' of this operative and not within Engagement Range of an enemy operative. That friendly operative cannot perform actions in which it moves during this Turning Point.",
                              "id": "6bac06a4-d38d-4a6e-a5c8-5eecb4012383"
                          },
                          {
                              "name": "INFERNAL FIRE",
                              "type": "WARPFIRE DISCIPLINE",
                              "description": "Select one enemy operative Visible to this operative. Until the end of the Turning Point, each time a friendly WARPCOVEN operative fights in combat or makes a shooting attack against that enemy operative, in the Roll Attack Dice step of that combat or shooting attack, you can re-roll any or all of your attack dice.",
                              "id": "6abf9aa4-5ee2-4422-b4d7-d8f4698862ae"
                          },
                          {
                              "name": "FIRESTORM",
                              "type": "WARPFIRE DISCIPLINE",
                              "description": "Perform a free Shoot action using the following ranged weapon:",
                              "weapon": {
                                  "name": "Firestorm",
                                  "type": "range",
                                  "attacks": 5,
                                  "ballisticWeaponSkill": 4,
                                  "damage": 2,
                                  "criticalDamage": 2,
                                  "specialRules": [
                                      {
                                          "name": "Barrage",
                                          "description": "Each time a friendly operative makes a shooting attack with this weapon, for that shooting attack, the automatic retention of defence dice as a result of cover is determined differently. Instead, if any part of the operative's base is hidden by a terrain feature from directly above, the defender can retain defence dice as if they were in cover."
                                      },
                                      {
                                          "name": "Blast 1'",
                                          "description": "Each time a friendly operative performs a Shoot action and selects this weapon (or, in the case of profiles, this weapon's profile), after making the shooting attack against the target, make a shooting attack with this weapon (using the same profile) against each other operative Visible to and within X of the original target - each of them is a valid target and cannot be in Cover. X is the distance after the weapon's Blast, e.g. Blast 2'. An operative cannot make a shooting attack with this weapon by performing an Overwatch action."
                                      },
                                      {
                                          "name": "Indirect",
                                          "description": "Each time a friendly operative makes a shooting attack with this weapon, in the select valid target step of that shooting attack, enemy operatives are not in Cover."
                                      }
                                  ],
                                  "id": "389c8092-173a-425a-a9a7-f0900328ad19"
                              },
                              "id": "49a71772-5ba9-4732-88a0-65457847da6e"
                          }
                      ],
                      "abilityOfKillTeam": {
                          "name": "BOONS OF TZEENTCH",
                          "description": [
                              "Boons of Tzeentch are Chaos blessings available to WARPCOVEN operatives. When a SORCERER operative is added to your roster or dataslate, it gains one Boon of Tzeentch - select or randomly determine one for it to gain, as described below.",
                              "When an operative gains a Boon of Tzeentch by any other means (i.e. WARPCOVEN Spec Ops rules, roll one D6: on a 1-2, randomly determine one for it to gain; on a 3+, select or randomly determine one for it to gain An operative can never have the same boon more than once (if randomly determined and neither order of results would determine a unique boon, roll again). Make a note of each operative's Boons of Tzeentch on your roster or dataslate.",
                              "When you select a boon for a SORCERER operative to gain, you can select one from any of the categories on the right, but you cannot select one a SORCERER operative on your roster or dataslate already has. When you randomly determine a boon for a SORCERER operative to gain, roll two D3, selecting one result to determine the category of boon and the other result to determine the boon",
                              "When you select a boon for a TZAANGOR operative to gain, you must select one from the Mutation category, but you cannot select one a TZAANGOR operative on your roster or dataslate already has. When you randomly determine a boon for a TZAANGOR operative to gain, roll one D3 to determine the boon from the Mutation category."
                          ],
                          "mutation": [
                              {
                                  "name": "Warp Swell",
                                  "type": "mutation",
                                  "description": "Add 1 to the Normal Damage characteristic of melee weapons this operative is equipped with.",
                                  "id": "e4d58ee6-d99c-42e7-9809-dad67d5021f6"
                              },
                              {
                                  "name": "Mutant Appendage",
                                  "type": "mutation",
                                  "description": "Once per Turning Point, during this operative's activation, it can perform a mission action or the Pick Up action for one less AP (to a minimum of 0AP).",
                                  "id": "ef218f27-4e7a-4025-bf61-cab0d06a1b0f"
                              },
                              {
                                  "name": "Avian Talons",
                                  "type": "mutation",
                                  "description": "Each time this operative fights in combat, if it performed a Charge action during this activation, in the Resolve Successful Hits step of that combat, if you did not retain any critical hits, you can strike with one normal hit as if it were a critical hit.",
                                  "id": "efbabd23-b1fa-4762-ac14-7f0cbc5ae18a"
                              },
                              {
                                  "name": "Patron of Destiny",
                                  "type": "fate",
                                  "description": "Once per Turning Point, when this operative is fighting in combat or making a shooting attack or a shooting attack is being made against it, you can use the Command Re-roll Tactical Ploy without spending any CPs. Each time you do so, roll one D6: on a 1-3, this operative loses this ability for the rest of the battle.",
                                  "id": "2153e6ad-0bbf-4b1f-bc84-24869c1b8922"
                              },
                              {
                                  "name": "Incorporeal Sight",
                                  "type": "fate",
                                  "description": "Ranged weapons this operative is equipped with (excluding ranged weapons from a psychic power) gain the Lethal 5+ and No Cover special rules.",
                                  "id": "34d96a6b-f92e-46b6-91fc-75d95283a9e4"
                              },
                              {
                                  "name": "Time-walker",
                                  "type": "fate",
                                  "description": "",
                                  "subText": [
                                      "Add 1 to the Attacks characteristic of melee weapons this operative is equipped with.",
                                      "Add 1' to this operative's Movement characteristic."
                                  ],
                                  "id": "f323047f-ad4e-4723-9cf2-18e19e3960b1"
                              },
                              {
                                  "name": "Immaterial Flight",
                                  "type": "aetheric",
                                  "description": "This operative gains the FLY keyword.",
                                  "id": "f15aaf3b-6aae-4478-b78e-19a117d80b2a"
                              },
                              {
                                  "name": "Crystalline",
                                  "type": "aetheric",
                                  "description": "Improve this operative's Save characteristic by 1.",
                                  "id": "4e933dfc-6448-4a81-9ea4-15f8455ca9d1"
                              },
                              {
                                  "name": "Empyric Ward",
                                  "type": "aetheric",
                                  "description": "This operative has a 4+ invulnerable save.",
                                  "id": "8919e336-fde1-4068-8cc4-fb05c0db8044"
                              }
                          ]
                      },
                      "id": "75b8e3dc-90ad-42e6-91de-266e209ca0c4"
                  }
              ]
          }
      ]
      """
        }
}