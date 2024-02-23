package com.cc221019.finalproject.data.models.entities

import kotlinx.coroutines.flow.update
import java.lang.Math.floor
import kotlin.math.roundToInt
import kotlin.random.Random

open class Player (
    var playerName: String = "Player",
    var playerClass : String,
    var playerLevel : Int,
    var playerStrength: Int,
    var playerStamina: Int,
    var playerDexterity: Int,
    var playerConstitution: Int,
    var playerMotivation: Int,
    var playerAttributePoints: Int,
    var lastPlayed : Boolean,
    var playerCurrentXP : Int = 0,
    var id:Int = 0,
    ) : Entity(playerName){

    var playerXPToNextLevel : Int = 0;

    var savedPlayerStrength: Int = 0;
    var savedPlayerStamina: Int = 0;
    var savedPlayerDexterity: Int = 0;
    var savedPlayerConstitution: Int = 0;
    var savedPlayerMotivation: Int = 0;

    var abilityOneName : String = "ab1";
    var abilityTwoName : String = "ab2";
    var abilityThreeName : String = "ab3";
    var abilityFourName : String = "ab4";

    var abilityOneDescription : String = "ab1";
    var abilityTwoDescription  : String = "ab2";
    var abilityThreeDescription  : String = "ab3";
    var abilityFourDescription  : String = "ab4";

    companion object {
        val CLASS_LIST: List<String> = listOf("Bodybuilder", "Martial Artist", "Endurance Runner", "CrossFit Athlete","Personal Trainer")
        val CLASS_ATTRIBUTES = mapOf(
            "Bodybuilder" to "Strength, Constitution",
            "Martial Artist" to "Dexterity, Stamina",
            "Endurance Runner" to "Stamina, Constitution",
            "CrossFit Athlete" to "Strength, Dexterity, Stamina",
            "Personal Trainer"  to "Motivation, Constitution",
        );
        val CLASS_DESCRIPTION = mapOf(
            "Bodybuilder" to "Bodybuilders prioritize raw physical power and durability. Their training leads to immense strength and a robust constitution, making them formidable in physical challenges and combat.",
            "Martial Artist" to "Martial Artists excel in agility and endurance. Their rigorous training enhances their dexterity, allowing them to execute precise and swift movements, and their stamina enables prolonged combat and physical activity.",
            "Endurance Runner" to "Endurance Runners focus on long-lasting performance and resilience. Their incredible stamina allows them to outlast others in activities requiring sustained effort, and their constitution helps them endure physical stress and fatigue.",
            "CrossFit Athlete" to "CrossFit Athletes are the all-rounders, balancing strength, agility, and endurance. They are capable of handling a diverse set of physical challenges due to their well-rounded training.",
            "Personal Trainer"  to "Personal Trainers are experts in fitness and motivation. They not only possess a good constitution due to their regular training but are also skilled in motivating others, potentially enhancing the performance of their team.",
        )
    }
    init {
        getNewHealth();
        getXPtoNextLevel();
    }
    fun levelUp() {
        getNewHealth();
        getXPtoNextLevel();
        saveStats();
        playerLevel += 1;
        playerAttributePoints += 4
    }

    fun saveStats(){
        savedPlayerStrength = playerStrength;
        savedPlayerStamina = playerStamina
        savedPlayerDexterity = playerDexterity
        savedPlayerConstitution = playerConstitution
        savedPlayerMotivation = playerMotivation

    }

    open fun abilityOne(entity: Entity): String {
        val dmgDealt = Random.nextInt(1, 10);

        entity.takeDmg(dmgDealt);

        return "$entityName: Attacked for $dmgDealt DMG";

    }
    open fun abilityTwo(entity: Entity): String {
        val heal = Random.nextInt(1, 10);

        this.heal(heal);

        return "$entityName: Healed for $heal";

    }
    open fun abilityThree(entity: Entity): String {
        val dmgDealt = Random.nextInt(1, 10);

        entity.takeDmg(dmgDealt);

        return "$entityName: Attacked for $dmgDealt DMG";

    }
    open fun abilityFour(entity: Entity): String {
        val dmgDealt = Random.nextInt(1, 10);

        entity.takeDmg(dmgDealt);

        return "$entityName: Attacked for $dmgDealt DMG";

    }

    fun addStat(statName : String){
        if(this.playerAttributePoints >= 1){
            when(statName){
                "Strength" -> this.playerStrength += 1
                "Stamina" -> this.playerStamina += 1
                "Dexterity" -> this.playerDexterity += 1
                "Constitution" -> this.playerConstitution += 1
                "Motivation" -> this.playerMotivation += 1
            }
            this.spendStatPoints();
        }
    }

    fun subStat(statName : String){
        when(statName){
            "Strength" ->
                if(playerStrength-1 >= savedPlayerStrength){
                    this.playerStrength -= 1
                    refundStatPoints();
                }

            "Stamina" ->
                if(playerStamina-1 >= savedPlayerStamina){
                    this.playerStamina -= 1
                    refundStatPoints();
                }

            "Dexterity" ->
                if(playerDexterity-1 >= savedPlayerDexterity){
                    this.playerDexterity -= 1
                    refundStatPoints();
                }

            "Constitution" ->
                if(playerConstitution-1 >= savedPlayerConstitution){
                    this.playerConstitution -= 1
                    refundStatPoints();
                }

            "Motivation" ->
                if(playerMotivation-1 >= savedPlayerMotivation){
                    this.playerMotivation -= 1
                    refundStatPoints();
                }
        }
    }

    fun getStats(statName: String): Int{
        when(statName){
            "Strength" -> return this.playerStrength
            "Stamina" -> return this.playerStamina
            "Dexterity" -> return this.playerDexterity
            "Constitution" -> return this.playerConstitution
            "Motivation" -> return this.playerMotivation
        }
        return 0;
    }

    fun refundStatPoints(){
        this.playerAttributePoints += 1;
    }

    fun spendStatPoints(){
        this.playerAttributePoints -= 1;
    }

    fun getNewHealth(){
        entityMaxHealth = playerLevel * (playerConstitution) + 10;
        isAlive = true;
        entityCurrentHealth = entityMaxHealth;
    }

    fun getXPtoNextLevel(){
        playerCurrentXP -= playerXPToNextLevel;
        playerXPToNextLevel = (playerLevel*1.6).roundToInt();
    }

    fun earnXP(xp:Int){
        playerCurrentXP += xp;

    }

}