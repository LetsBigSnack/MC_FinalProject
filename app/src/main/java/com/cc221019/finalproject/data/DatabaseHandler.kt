package com.cc221019.finalproject.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.cc221019.finalproject.data.models.entities.Player
import com.cc221019.finalproject.data.models.entities.playerClasses.Bodybuilder
import com.cc221019.finalproject.data.models.entities.playerClasses.CrossFitAthlete
import com.cc221019.finalproject.data.models.entities.playerClasses.EnduranceRunner
import com.cc221019.finalproject.data.models.entities.playerClasses.MartialArtist
import com.cc221019.finalproject.data.models.entities.playerClasses.PersonalTrainer
import java.lang.Exception

class DatabaseHandler(context : Context) : SQLiteOpenHelper(context, dbName, null, dbVersion) {

    companion object DatabaseConfig {
        private const val dbName : String = "GameDatabase"
        private const val dbVersion : Int = 5

        private const val playerTableName = "Player"
        private const val playerId = "_id"
        private const val playerName = "playerName"
        private const val playerClass = "playerClass"
        private const val playerLevel = "playerLevel"
        private const val playerStrength = "playerStrength"
        private const val playerStamina = "playerStamina"
        private const val playerDexterity = "playerDexterity"
        private const val playerConstitution = "playerConstitution"
        private const val playerMotivation = "playerMotivation"
        private const val playerAttributePoints = "playerAttributePoints"
        private const val lastPlayed = "lastPlayed"
        private const val currentXP = "currentXP"

    }

    override fun onConfigure(db: SQLiteDatabase?) {
        super.onConfigure(db)
        db?.execSQL("PRAGMA foreign_keys = ON")
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS $playerTableName " +
                "($playerId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$playerName VARCHAR(128) NOT NULL, " +
                "$playerClass VARCHAR(128)," +
                "$playerLevel INTEGER," +
                "$playerStrength INTEGER," +
                "$playerStamina INTEGER," +
                "$playerDexterity INTEGER," +
                "$playerConstitution INTEGER," +
                "$playerMotivation INTEGER," +
                "$playerAttributePoints INTEGER," +
                "$lastPlayed BOOLEAN," +
                "$currentXP INTEGER" +
                ");")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $playerTableName")
        onCreate(db)
    }

    fun insertPlayer(player : Player){
        deselectAllPlayers();
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(playerName,player.playerName)
            put(playerClass, player.playerClass)
            put(playerLevel, player.playerLevel)
            put(playerStrength, player.playerStrength)
            put(playerStamina, player.playerStamina)
            put(playerDexterity, player.playerDexterity)
            put(playerConstitution, player.playerConstitution)
            put(playerMotivation, player.playerMotivation)
            put(playerAttributePoints, player.playerAttributePoints)
            put(lastPlayed, true)
            put(currentXP, 0)
        }

        db.insert(playerTableName, null, values)
    }

    fun updatePlayer(player: Player){
        val db = this.writableDatabase
        println(player);
        val values = ContentValues().apply {
            put(playerName,player.playerName)
            put(playerClass, player.playerClass)
            put(playerLevel, player.playerLevel)
            put(playerStrength, player.playerStrength)
            put(playerStamina, player.playerStamina)
            put(playerDexterity, player.playerDexterity)
            put(playerConstitution, player.playerConstitution)
            put(playerMotivation, player.playerMotivation)
            put(playerAttributePoints, player.playerAttributePoints)
            put(lastPlayed, player.lastPlayed)
            put(currentXP, player.playerCurrentXP)
        }
        db.update(playerTableName,values,"${playerId} = ?", arrayOf(player.id.toString()))
    }

    fun deletePlayer(player: Player){
        val db = this.writableDatabase
        db.delete(playerTableName,"$playerId = ?", arrayOf(player.id.toString()))
    }

    fun getPlayers() : List<Player>{
        val db = this.readableDatabase
        val players = mutableListOf<Player>()
        val cursor = db.rawQuery("SELECT * FROM $playerTableName", null)
        while(cursor.moveToNext()){
            val idId = cursor.getColumnIndex(playerId)
            val nameId = cursor.getColumnIndex(playerName)
            val classId = cursor.getColumnIndex(playerClass)
            val levelId = cursor.getColumnIndex(playerLevel)
            val strengthId = cursor.getColumnIndex(playerStrength)
            val dexterityId = cursor.getColumnIndex(playerDexterity)
            val staminaId = cursor.getColumnIndex(playerStamina)
            val constitutionId = cursor.getColumnIndex(playerConstitution)
            val motivationId = cursor.getColumnIndex(playerMotivation)
            val attributesId = cursor.getColumnIndex(playerAttributePoints)
            val lastPlayedId = cursor.getColumnIndex(lastPlayed)
            val currentXPId = cursor.getColumnIndex(currentXP)
            //
            if(nameId >= 0){
                var tempPlayer = Player(
                    cursor.getString(nameId),
                    cursor.getString(classId),
                    cursor.getInt(levelId),
                    cursor.getInt(strengthId),
                    cursor.getInt(dexterityId),
                    cursor.getInt(staminaId),
                    cursor.getInt(constitutionId),
                    cursor.getInt(motivationId),
                    cursor.getInt(attributesId),
                    cursor.getInt(lastPlayedId) > 0,
                    cursor.getInt(currentXPId),
                    cursor.getInt(idId)
                );

                players.add(tempPlayer);
            }

        }
        return players.toList();
    }

    fun getAllSelectedPlayers() : List<Player> {
        val db = this.readableDatabase
        val players = mutableListOf<Player>()
        val cursor = db.rawQuery("SELECT * FROM $playerTableName WHERE $lastPlayed = TRUE;", null)
        while(cursor.moveToNext()){
            val idId = cursor.getColumnIndex(playerId)
            val nameId = cursor.getColumnIndex(playerName)
            val classId = cursor.getColumnIndex(playerClass)
            val levelId = cursor.getColumnIndex(playerLevel)
            val strengthId = cursor.getColumnIndex(playerStrength)
            val dexterityId = cursor.getColumnIndex(playerDexterity)
            val staminaId = cursor.getColumnIndex(playerStamina)
            val constitutionId = cursor.getColumnIndex(playerConstitution)
            val motivationId = cursor.getColumnIndex(playerMotivation)
            val attributesId = cursor.getColumnIndex(playerAttributePoints)
            val lastPlayedId = cursor.getColumnIndex(lastPlayed)
            val currentXPId = cursor.getColumnIndex(currentXP)
            //
            if(nameId >= 0){

                var tempPlayer : Player;


                when(cursor.getString(classId)){
                    "Bodybuilder" -> tempPlayer = Bodybuilder(
                       cursor.getString(nameId),
                       cursor.getString(classId),
                       cursor.getInt(levelId),
                       cursor.getInt(strengthId),
                       cursor.getInt(dexterityId),
                       cursor.getInt(staminaId),
                       cursor.getInt(constitutionId),
                       cursor.getInt(motivationId),
                       cursor.getInt(attributesId),
                       cursor.getInt(lastPlayedId) > 0,
                       cursor.getInt(currentXPId),
                       cursor.getInt(idId)
                   );
                    "Martial Artist" -> tempPlayer = MartialArtist(
                        cursor.getString(nameId),
                        cursor.getString(classId),
                        cursor.getInt(levelId),
                        cursor.getInt(strengthId),
                        cursor.getInt(dexterityId),
                        cursor.getInt(staminaId),
                        cursor.getInt(constitutionId),
                        cursor.getInt(motivationId),
                        cursor.getInt(attributesId),
                        cursor.getInt(lastPlayedId) > 0,
                        cursor.getInt(currentXPId),
                        cursor.getInt(idId)
                    );
                    "Endurance Runner" -> tempPlayer = EnduranceRunner(
                        cursor.getString(nameId),
                        cursor.getString(classId),
                        cursor.getInt(levelId),
                        cursor.getInt(strengthId),
                        cursor.getInt(dexterityId),
                        cursor.getInt(staminaId),
                        cursor.getInt(constitutionId),
                        cursor.getInt(motivationId),
                        cursor.getInt(attributesId),
                        cursor.getInt(lastPlayedId) > 0,
                        cursor.getInt(currentXPId),
                        cursor.getInt(idId)
                    );
                    "CrossFit Athlete" -> tempPlayer = CrossFitAthlete(
                        cursor.getString(nameId),
                        cursor.getString(classId),
                        cursor.getInt(levelId),
                        cursor.getInt(strengthId),
                        cursor.getInt(dexterityId),
                        cursor.getInt(staminaId),
                        cursor.getInt(constitutionId),
                        cursor.getInt(motivationId),
                        cursor.getInt(attributesId),
                        cursor.getInt(lastPlayedId) > 0,
                        cursor.getInt(currentXPId),
                        cursor.getInt(idId)
                    );
                    "Personal Trainer" -> tempPlayer = PersonalTrainer(
                        cursor.getString(nameId),
                        cursor.getString(classId),
                        cursor.getInt(levelId),
                        cursor.getInt(strengthId),
                        cursor.getInt(dexterityId),
                        cursor.getInt(staminaId),
                        cursor.getInt(constitutionId),
                        cursor.getInt(motivationId),
                        cursor.getInt(attributesId),
                        cursor.getInt(lastPlayedId) > 0,
                        cursor.getInt(currentXPId),
                        cursor.getInt(idId)
                    );
                    else -> tempPlayer = Player(
                        cursor.getString(nameId),
                        cursor.getString(classId),
                        cursor.getInt(levelId),
                        cursor.getInt(strengthId),
                        cursor.getInt(dexterityId),
                        cursor.getInt(staminaId),
                        cursor.getInt(constitutionId),
                        cursor.getInt(motivationId),
                        cursor.getInt(attributesId),
                        cursor.getInt(lastPlayedId) > 0,
                        cursor.getInt(currentXPId),
                        cursor.getInt(idId)
                    );

                }

                players.add(tempPlayer);
            }

        }
        return players;
    }

    fun deselectAllPlayers(){

        val writeDB = this.writableDatabase
        val players = getAllSelectedPlayers();

        players.forEach { player ->
            val values = ContentValues().apply {
                put(lastPlayed, false)
            }
            writeDB.update(
                playerTableName,
                values,
                "${playerId} = ?",
                arrayOf(player.id.toString())
            )
        }
    }

    fun selectPlayer(player: Player){
        deselectAllPlayers();
        val db = this.writableDatabase
        println(player);
        val values = ContentValues().apply {
            put(lastPlayed, true)
        }
        db.update(playerTableName,values,"${playerId} = ?", arrayOf(player.id.toString()))
    }

    fun getSelectedPlayer() :Player {
        val players = getAllSelectedPlayers();

        if(players.size > 1){

            throw Exception();
        }else{
            return players.first();
        }
    }
}
