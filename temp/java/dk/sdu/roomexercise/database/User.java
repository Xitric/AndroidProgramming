package dk.sdu.roomexercise.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    public String name;

    public String address;

    @ColumnInfo(name = "birth_date")
    public String birthDate;
}
