package com.example.mvvmsampleapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmsampleapp.data.db.entities.CURRENT_USER_ID
import com.example.mvvmsampleapp.data.db.entities.User

@Dao
interface UserDAO {

    //function for insert or update
    //If the user is successfully inserted or updated,we wll get the inserted row id of Long type
    //Insert annotation ,we are using for insert operation and inside it we are specifying what we need to do
    //if there is some conflict i.e we are inserting the same id or same primary key again
    // We will override the currently saved user with the new one
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(user: User): Long

    //Query
    @Query("SELECT * FROM user WHERE uid=$CURRENT_USER_ID")
    fun getUsers(): LiveData<User>
}