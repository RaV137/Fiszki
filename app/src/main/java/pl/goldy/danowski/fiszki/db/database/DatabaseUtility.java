package pl.goldy.danowski.fiszki.db.database;

import android.arch.persistence.room.Room;
import android.content.Context;

public class DatabaseUtility {

    private static FlashcardDatabase db;
    private static boolean initialized = false;

    public static void initialize(Context applicationContext) {
        if(initialized)
            return;

        initialized = true;
        db = Room.databaseBuilder(applicationContext,
                FlashcardDatabase.class, "grid_item").build();
    }

    public static FlashcardDatabase getAppDatabase(Context applicationContext) {
        if(!initialized){
            initialize(applicationContext);
        }
        return db;
    }

}
