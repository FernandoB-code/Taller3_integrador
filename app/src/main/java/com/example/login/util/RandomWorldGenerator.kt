package com.example.login.util


import android.content.Context
import android.util.Log


class RandomWorldGenerator() {

     fun main(context : Context) : MutableList<String> {

          var wordsList : MutableList<String> = mutableListOf()

          try{
               val fileName = "words.txt"
               val bufferReader = context.assets.open(fileName).bufferedReader()
               bufferReader.forEachLine {
                    wordsList.add(it)
               }

          } catch(e : Exception) {
               Log.d("error", e.message.toString())
          }

          return wordsList
     }
}



