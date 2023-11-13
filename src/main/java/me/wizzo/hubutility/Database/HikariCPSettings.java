package me.wizzo.hubutility.Database;

import com.zaxxer.hikari.HikariDataSource;
import me.wizzo.hubutility.Files.databaseSystem;
import me.wizzo.hubutility.Utils.utilsFile;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class HikariCPSettings {

    public String className, url, host, port, database, username, password, stackerTable, pHideTable;

    HikariDataSource dataSource;

    public HikariDataSource initSource() throws SQLException {
        this.className = databaseSystem.get().getString("ClassName");
        this.host = databaseSystem.get().getString("Host");
        this.port = databaseSystem.get().getString("Port");
        this.database = databaseSystem.get().getString("Database");
        this.username = databaseSystem.get().getString("Username");
        this.password = databaseSystem.get().getString("Password");
        this.stackerTable = databaseSystem.get().getString("Tables.Stacker");
        this.pHideTable = databaseSystem.get().getString("Tables.HidePlayer");
        this.url = databaseSystem.get().getString("Url")
                .replace("{ip}", host)
                .replace("{port}", port)
                .replace("{database}", database);

        dataSource = new HikariDataSource();
        dataSource.setMaximumPoolSize(databaseSystem.get().getInt("Max-pool-size"));
        dataSource.setDriverClassName(className);
        dataSource.setJdbcUrl(url);
        dataSource.addDataSourceProperty("user", username);
        dataSource.addDataSourceProperty("password", password);
        dataSource.addDataSourceProperty("database", database);

        testDataSource(dataSource);
        return dataSource;
    }

    public void close(HikariDataSource source) {
        source.close();
    }

    public HikariDataSource getSource() {
        return dataSource;
    }

    private void testDataSource(DataSource source) throws SQLException {
        try (Connection connection = source.getConnection()) {
            if (!connection.isValid(1000)) {
                throw new SQLException("Impossibile eseguire la connessione al database");
            }
        }
    }
}
