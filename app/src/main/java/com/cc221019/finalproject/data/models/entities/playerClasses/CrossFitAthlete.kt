package com.cc221019.finalproject.data.models.entities.playerClasses

import com.cc221019.finalproject.data.models.entities.Player

class CrossFitAthlete(
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
        this.abilityOneName = "Cross 1";
        this.abilityTwoName = "Cross 2";
        this.abilityThreeName = "Cross 3";
        this.abilityFourName = "Cross 4";

        this.abilityOneDescription = "Cross Description 1";
        this.abilityTwoDescription = "Cross Description 2";
        this.abilityThreeDescription = "Cross Description 3";
        this.abilityFourDescription = "Cross Description 4";

    }

}