package com.cc221019.finalproject.data.models.entities.playerClasses

import com.cc221019.finalproject.data.models.entities.Player

class MartialArtist(
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
        this.abilityOneName = "Martial 1";
        this.abilityTwoName = "Martial 2";
        this.abilityThreeName = "Martial 3";
        this.abilityFourName = "Martial 4";

        this.abilityOneDescription = "Martial Description 1";
        this.abilityTwoDescription = "Martial Description 1";
        this.abilityThreeDescription = "Martial Description 1";
        this.abilityFourDescription = "Martial Description 1";

    }



}