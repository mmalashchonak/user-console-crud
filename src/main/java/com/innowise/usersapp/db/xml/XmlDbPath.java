package com.innowise.usersapp.db.xml;

public enum XmlDbPath {

    USERS_DEFAULT("./database/users.xml");

    private String xmlFilePath;

    XmlDbPath(String xmlFilePath) {
        this.xmlFilePath = xmlFilePath;
    }

    public String getXmlFilePath() {
        return this.xmlFilePath;
    }
}
