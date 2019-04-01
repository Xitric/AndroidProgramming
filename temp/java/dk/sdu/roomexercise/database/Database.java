package dk.sdu.roomexercise.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {User.class}, version =  1)
public abstract class Database extends RoomDatabase {
    private static Database instance;

    public abstract UserDao userDao();

    private Database() { }

    public static Database getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), Database.class, "user-database")
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }

    public static void destroyInstance() {
        instance.close();
        instance = null;
    }
}
