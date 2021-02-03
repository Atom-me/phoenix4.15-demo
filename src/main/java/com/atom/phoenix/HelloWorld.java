package com.atom.phoenix;

import java.sql.*;

/**
 * @author Atom
 */
public class HelloWorld {
    public static void main(String[] args) throws SQLException {
        Statement stmt;
        ResultSet rset;

        Connection con = DriverManager.getConnection("jdbc:phoenix:node1,node2,node3:2181");
        stmt = con.createStatement();

        stmt.executeUpdate("create table test (mykey integer not null primary key, mycolumn varchar)");
        stmt.executeUpdate("upsert into test values (1,'Hello')");
        stmt.executeUpdate("upsert into test values (2,'World!')");
        con.commit();

        PreparedStatement statement = con.prepareStatement("select * from test001");
        rset = statement.executeQuery();
        while (rset.next()) {
            System.err.println(rset.getString("mycolumn"));
        }
        statement.close();
        con.close();
    }
}
