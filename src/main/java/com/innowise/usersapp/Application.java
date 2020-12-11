package com.innowise.usersapp;

import com.innowise.usersapp.client.console.MainWindow;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException {
        MainWindow mainWindow = new MainWindow();
        mainWindow.start();
    }
}
