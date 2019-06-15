package mothersday;

import mothersday.contracts.IDataAccess;
import mothersday.lib.Database;

public class Main {
    public static void main(String[] args) {
        IDataAccess db = Database.getDatabaseInstance();
        if(Database.DB_FILE.exists()) {
            System.out.println("Arquivo banco OK");
            System.out.println(db.getInfo());
        }
    }
}
