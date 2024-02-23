package com.cc221019.finalproject.data.models.entities.playerClasses

import com.cc221019.finalproject.data.models.entities.Player

class EnduranceRunner(
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
        this.abilityOneName = "Runner 1";
        this.abilityTwoName = "Runner 2";
        this.abilityThreeName = "Runner 3";
        this.abilityFourName = "Runner 4";

        this.abilityOneDescription = "Runner Description 1";
        this.abilityTwoDescription = "Runner Description 2";
        this.abilityThreeDescription = "Runner Description 3";
        this.abilityFourDescription = "Runner Description 4";

    }

}