package mothersday.lib;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import mothersday.contracts.IDataAccess;

public class Database implements IDataAccess {
    private Connection conn;
    private static Database _Instance;
    public final static File DB_FILE = new File("data.sqlite");
    public final static String DRIVE = "org.sqlite.JDBC";
    public final static String CONN_STRING = "jdbc:sqlite:" + DB_FILE.getAbsolutePath();

    private Database() {
        try {
            Class.forName(DRIVE);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        if (!DB_FILE.exists())
            createDatabase();
    }

    public static Database getDatabaseInstance() {
        if (_Instance == null)
            _Instance = new Database();
        return _Instance;
    }

    private void connect() {
        try {
            conn = DriverManager.getConnection(CONN_STRING);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private void disconnect() {
        try {
            if (conn != null)
                conn.close();
            conn = null;
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private void createDatabase() {
        try {
            connect();
            Statement stmt = conn.createStatement();
            String sql = getCreateSql();
            stmt.executeUpdate(sql);
            String sqlInsert = "INSERT INTO media (title) VALUES ('teste');";
            stmt.executeUpdate(sqlInsert);
            stmt.close();
            disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private String getCreateSql() {
        return "CREATE TABLE\n" + "media (id int PRIMARY KEY, title varchar(255));";
    }

    @Override
    public String getInfo() {
        String info = "";
        try {
            connect();
            String sql = "SELECT title FROM media;";
            Statement stmt;
            stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(sql);
            while(result.next()) {
                info = result.getString("title");
            }
            stmt.close();
            disconnect();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(0);
        }
        return info;
    }
}