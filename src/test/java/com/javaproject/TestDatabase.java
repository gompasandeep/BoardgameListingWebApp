package com.javaproject;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.javaproject.database.DatabaseAccess;

@SpringBootTest
@AutoConfigureMockMvc
class TestDatabase {

    private DatabaseAccess da;

    @Autowired
    public void setDatabase(DatabaseAccess da) {
        this.da = da;
    }

    // @Test
    // public void testDatabaseAdd() {
    // Avenger avenger = new Avenger();
    // avenger.setName("Starlight");
    // avenger.setAge(22);

    // int originalSize = da.getAvengers().size();

    // da.addAvenger(avenger);
    // int newSize = da.getAvengers().size();

    // assertEquals(newSize, originalSize + 1);
    // }

    // @Test
    // public void testDatabasedelete() {
    // List<Avenger> avengers = da.getAvengers();
    // Long id = avengers.get(0).getId();
    // int originalSize = da.getAvengers().size();

    // da.deleteAvenger(id);
    // int newSize = da.getAvengers().size();
    // assertEquals(newSize, originalSize - 1);
    // }

}
