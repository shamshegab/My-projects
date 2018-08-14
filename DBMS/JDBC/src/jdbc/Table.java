/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

/**
 *
 * @author ahmed
 */
public class Table {

    private static Table instance;

    Table() {

    }

    public static Table getInstance() {
        if (instance == null) {
            instance = new Table();
        }
        return instance;
    }

    String[][] table;
    String tableName;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Object[][] getTable() {
        return table;
    }

    public void setTable(String[][] table) {
        this.table = table;
    }

}
