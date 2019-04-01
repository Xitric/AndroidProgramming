package dk.sdu.roomexercise.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class User {
    @NonNull
    @PrimaryKey
    public String name;

    public String address;

    @ColumnInfo(name = "birth_date")
    public long birthDate;
}
