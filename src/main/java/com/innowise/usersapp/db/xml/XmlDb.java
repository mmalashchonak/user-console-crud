package com.innowise.usersapp.db.xml;

import com.innowise.usersapp.db.xml.exceptions.CanNotAccessDatabaseException;
import com.innowise.usersapp.entity.Users;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.Properties;

public class XmlDb {

    private final String USERS_XML = "./database/users.xml";
    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;
    private static final XmlDb db = new XmlDb();

    public static XmlDb getDb() {
        return db;
    }

    private XmlDb() {
        try {
            JAXBContext context = JAXBContext.newInstance(Users.class);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            unmarshaller = context.createUnmarshaller();
        } catch (JAXBException e) {
            throw new CanNotAccessDatabaseException();
        }
    }

    public Users getDbContent(XmlDbPath xmlDbPath) {
        try (FileReader fileReader = new FileReader(xmlDbPath.getXmlFilePath())) {
            return (Users) unmarshaller.unmarshal(fileReader);
        } catch (Exception e) {
            throw new CanNotAccessDatabaseException("Database reading was failed!");
        }
    }

    public int setDbContent(Users users, XmlDbPath xmlDbPath) {
        try {
            File file = new File(xmlDbPath.getXmlFilePath());
            marshaller.marshal(users, file);
            return 1;
        } catch (Exception e) {
            throw new CanNotAccessDatabaseException("Database saving was failed!");
        }
    }

    public Long getLastId() {
        try (InputStream input = new FileInputStream("./database/config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            return Long.parseLong(prop.getProperty("max-id"));
        } catch (IOException ex) {
            throw new RuntimeException("Can't load xml-database properties.");
        }
    }

    public void setLastId(Long id) {
        try (FileOutputStream output = new FileOutputStream("./database/config.properties")) {
            Properties prop = new Properties();
            prop.setProperty("max-id", id.toString());
            prop.store(output, null);
        } catch (IOException ex) {
            throw new RuntimeException("Can't save xml-database properties.");
        }
    }

}
