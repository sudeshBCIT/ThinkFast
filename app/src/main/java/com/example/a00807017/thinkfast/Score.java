package com.example.a00807017.thinkfast;

/**
 * Created by Sudesh on 30/10/2014.
 */
public class Score
    {
        int _id;
        String _score;
        String _userid;

        public Score(){}

        // constructor
        public Score( String score, String userid){
        // this._id = id;
        this._score = score;
        this._userid = userid;
    }
        //getting id
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }
    //getting first name
    public String getScore()
    {
        return this._score;
    }
    //setting first name
    public void setScore(String score)
    {
        this._score =   score;
    }
    //getting last name
    public String getUserid()
    {
        return this._userid;
    }
    //setting last name
    public void setUserid(String userid)
    {
        this._userid = userid;
    }
}
