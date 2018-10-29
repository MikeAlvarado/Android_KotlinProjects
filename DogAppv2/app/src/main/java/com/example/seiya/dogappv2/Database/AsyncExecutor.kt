package com.example.seiya.dogappv2.Database

import java.util.concurrent.Executors

private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

fun ioThread(f: ()-> Unit){
    IO_EXECUTOR.execute(f)
}