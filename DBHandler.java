package com.example.promanager;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "ProManager";
    private static final int DB_VERSION = 1;

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createUserInforTable(db);
        createActivityTable(db);
        createProjectTable(db);
        createUserConnection(db);
        createUserResponActivity(db);
        createAchieveActivity(db);
        createActivityInProject(db);
        createUserResponProject(db);
    }
//    CREATE TABLE
    public void createUserInforTable(SQLiteDatabase db){
        String query = "CREATE TABLE UserInFor (username TEXT, pass TEXT, fullname TEXT, overview TEXT, totalTasks INT, totalHours INT, currentTask INT, currentFinished INT, PRIMARY KEY(username))";
        db.execSQL(query);
    }

    public void createActivityTable(SQLiteDatabase db){
        String query = "CREATE TABLE Activity (activityID INT, activityName TEXT, activityDescribe TEXT, activityDeadline DATE, activityHost TEXT, activityFile TEXT, activityStatus TEXT, activityAgreement BOOLEAN, PRIMARY KEY(activityID))";
        db.execSQL(query);
    }

    public void createProjectTable(SQLiteDatabase db){
        String query = "CREATE TABLE Project (projectID INT, projectName TEXT, projectOwner TEXT, projectDeadline DATE, projectDescribe DATE, projectPrivacy TEXT, PRIMARY KEY(projectID))";
        db.execSQL(query);
    }

    public void createUserConnection(SQLiteDatabase db){
        String query = "CREATE TABLE UserConnection (usernameA TEXT, usernameB TEXT,  PRIMARY KEY(usernameA,usernameB), FOREIGN KEY(usernameA) references UserInFor(username), FOREIGN KEY(usernameB) references UserInFor(username))";
        db.execSQL(query);
    }

    public void createUserResponActivity(SQLiteDatabase db){
        String query = "CREATE TABLE UserResponActivity (activityID INT, username TEXT, PRIMARY KEY(activityID,username), FOREIGN KEY(activityID) references Activity(activityID), FOREIGN KEY(username) references UserInFor(username))";
        db.execSQL(query);
    }

    public void createAchieveActivity(SQLiteDatabase db){
        String query = "CREATE TABLE AchieveActivity (username TEXT, activityID INT, PRIMARY KEY(username,activityID), FOREIGN KEY(username) references UserInFor(username), FOREIGN KEY(activityID) references Activity(activityID))";
        db.execSQL(query);
    }

    public void createActivityInProject(SQLiteDatabase db){
        String query = "CREATE TABLE ActivityInProject (projectID INT, activityID INT, PRIMARY KEY(projectID,activityID), FOREIGN KEY(projectID) references Project(projectID), FOREIGN KEY(activityID) references Activity(activityID))";
        db.execSQL(query);
    }

    public void createUserResponProject(SQLiteDatabase db){
        String query = "CREATE TABLE UserResponProject (username TEXT, projectID INT, PRIMARY KEY(username,projectID), FOREIGN KEY(username) references UserInFor(username), FOREIGN KEY(projectID) references Project(projectID))";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}