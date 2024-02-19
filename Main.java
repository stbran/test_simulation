package ru.sbertroika;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
      DBService.getListTable("/terminal");
        DBService.getListTable("/type");
        DBService.getListTable("/organization");
        String id = DBService.getTerminalIdByName("/terminal/name?", "name=Test%201");
        System.out.println(id);
        System.out.println(DBService.deleteById("/terminal/id?", UUID.fromString(id)));
    }
}
