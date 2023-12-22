package com.soberk.xpends.console.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class H2Config extends DBConfig{
    private static final String URL = "jdbc:h2:~/Development/expense-app";
    private static final String USER = "admin";
    private static final String PASSWORD = "1234";
    public H2Config() {
        super(URL,USER,PASSWORD);
//        loadTables();
    }

    private void loadTables() {
        try {
            Connection conn = this.getConnection();
            List<String> sqlList = List.of(
                    "CREATE TABLE IF NOT EXISTS accounts(id UUID DEFAULT RANDOM_UUID() PRIMARY KEY, name VARCHAR(50), description VARCHAR(250))",
                    "CREATE TABLE IF NOT EXISTS categories(id IDENTITY PRIMARY KEY, name VARCHAR(50), description VARCHAR(250))",
                    "CREATE TABLE IF NOT EXISTS spot(id IDENTITY PRIMARY KEY, amount FLOAT, account_id UUID, FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE)",
                    "CREATE TABLE IF NOT EXISTS transactions(id UUID DEFAULT RANDOM_UUID() PRIMARY KEY, amount FLOAT, transaction_type VARCHAR(10),description VARCHAR(100), date_time DATETIME, spot_id INT, FOREIGN KEY (spot_id) REFERENCES spot(id) ON DELETE CASCADE, category_id INT, FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE)"
                    );

            Statement stm = conn.createStatement();
            sqlList.forEach(sql -> {
                try {
                    stm.executeUpdate(sql);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
