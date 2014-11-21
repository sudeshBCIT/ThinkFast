package com.example.a00807017.thinkfast;

/**
 * Created by Sudesh on 30/10/2014.
 */
public class Score
    {
        int _id;
        String _score;
        String _username;
        String _password;

        public Score(){}

        // constructor
        public Score( String score, String username){
        // this._id = id;
        this._score = score;
        this._username = username;
        // this._password = password;
    }
    //getting id
    public int getID(){
        return this._id;
    }
    // setting id
    public void setID(int id){
        this._id = id;
    }
    //getting the score
    public String getScore()
    {
        return this._score;
    }
    //setting the score
    public void setScore(String score)
    {
        this._score =   score;
    }
    //getting the username
    public String getUsername()
    {
        return this._username;
    }
    //setting the username
    public void setUsername(String username)
    {
        this._username = username;
    }
    //getting the password
    public String getPassword()
        {
            return this._password;
        }
    //setting the password
    public void setPassword(String password)
        {
            this._password = password;
        }
}
