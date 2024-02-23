package com.cc221019.finalproject.data.models.entities.playerClasses

import com.cc221019.finalproject.data.models.entities.Player

class Bodybuilder(
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
        this.abilityOneName = "Body 1";
        this.abilityTwoName = "Body 2";
        this.abilityThreeName = "Body 3";
        this.abilityFourName = "Body 4";

        this.abilityOneDescription = "Body Description 1";
        this.abilityTwoDescription = "Body Description 2";
        this.abilityThreeDescription = "Body Description 3";
        this.abilityFourDescription = "Body Description 4";

    }
}