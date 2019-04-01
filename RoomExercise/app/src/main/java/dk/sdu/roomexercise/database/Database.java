package dk.sdu.roomexercise.database;

import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


@android.arch.persistence.room.Database(entities = {User.class}, version =  1)
public abstract class Database extends RoomDatabase {
    public static Database instance;

    public abstract UserDao userDao();

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
