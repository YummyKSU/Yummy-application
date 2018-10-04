package com.example.yummy

class UserInfo(var timeOpen:Int,val username:String,var timeClose:Int, var deliFrom:Int, var deliTo:Int, var cost:Int, val id:String,val logUrl:String){
    constructor():this(0,"",0,0,0,0,"","") {

    }}