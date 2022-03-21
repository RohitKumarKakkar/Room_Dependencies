package com.kotlin.roomdependencies

/*
    Make Sure to Use 'Database Inspector' to check demo is working or not
*/

/*
    It is a file based RDBMS [Relational Database Management System] is used to store our data in our device
    in the form of a file. we can also perform Queries on that data file Such as : INSERT/DELETE/UPDATE/SELECT -
    used to avoid BoilerPlate Code , Also Check SQL Query During Compile Time Only , Has Support for Multiple
    Libraries such as LiveData, Coroutines, RXJava etc.
*/

/*
    Mainly 5 Components of Room Database:-

            Entites - Tables of data - 1 Table has 1 Entity - Make Sure to add Primary key in all DB
            DAO (Data Access Object) - To Access Database - Methods to Access and Perform CRUD - Can Be multiple
            Database - Database Itself - Can have multiple Entities (Tables)
            Type Converters
            Migrations - Changes in Database After app is fully Live
*/

/*
    Our SQLite Database only Support NULL,INTEGER,REAL,TEXT,BLOB data types. Thats Where [Type Converters] Comes
    handy. lets say if we want to store Date Object in our DB , We have to use Converter. if type is converted
    of any of the data . Coverter will get called Save Data/ Fetch Data.
*/

/*
    Updating the app with Database Changes in Existing DB can be done by Migration - If Already app installed
    Database Will Edited to Version 2. If Not Installed then Database Will be Created to new with version 2 and
    Migration Code will not be Executed.
*/

/*
    Contact Repository is Created just as a sapertated class that is not related to any Architecture , we just made
    it to call methods directly.This File is made as an API Calling structure for our ViewModel to Handle Data
    This file works as a mediator between our data layer and our viewModel, Our Viewmodel will ask for data from
    this file and this file has repsonsibility to provide data to viewmodel. this is a good practice to use this coz ,
    when we make changes in our database or our API or our interface , this wil handle all the changes and our viewmodel
    will be intact means we dont have to do changes in our view model
 */

/*
    Use case of [Repository] is that it checks weather user is online or not, if user is then it will fetch data
    from API and store in Room Database as a cache and provide to viewmodel - our viewmodel does not care weather
    the data is coming from API or Room DB
 */
