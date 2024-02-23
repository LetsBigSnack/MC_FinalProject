package com.cc221019.finalproject.data.models.entities.playerClasses

import com.cc221019.finalproject.data.models.entities.Player

class PersonalTrainer(
    playerName: String,
    playerClass: String,
    playerLevel: Int,
    playerStrength: Int,
    playerStamina: Int,
    playerDexterity: Int,
    playerConstitution: Int,
    playerMotivation: Int,
    playerAttributePoints: Int,
    lastPlayed: Boolean,
    playerCurrentXP: Int,
    id: Int
) : Player(
    playerName,
    playerClass,
    playerLevel,
    playerStrength,
    playerStamina,
    playerDexterity,
    playerConstitution,
    playerMotivation,
    playerAttributePoints,
    lastPlayed,
    playerCurrentXP,
    id
) {

    init {
        this.abilityOneName = "Trainer 1";
        this.abilityTwoName = "Trainer 2";
        this.abilityThreeName = "Trainer 3";
        this.abilityFourName = "Trainer 4";

        this.abilityOneDescription = "Trainer Description 1";
        this.abilityTwoDescription = "Trainer Description 2";
        this.abilityThreeDescription = "Trainer Description 3";
        this.abilityFourDescription = "Trainer Description 4";

    }

}