package dk.sdu.contactsexercise;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int PERMISSION_REQUEST_READ_CONTACTS = 100;

    // From exercise 1
//    private ListView contactsList;
//    private SimpleCursorAdapter adapter;

    // For exercise 2
    private RecyclerView contactsRecycler;
    private ContactsTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // From exercise 1
//        setContentView(R.layout.activity_main);
//        contactsList = findViewById(R.id.contactsList);

        // For exercise 2
        setContentView(R.layout.activity_main_recycler);
        contactsRecycler = findViewById(R.id.contactsRecycler);
        contactsRecycler.setHasFixedSize(true);
        contactsRecycler.setLayoutManager(new LinearLayoutManager(this));

        tryLoadContacts();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Since we cancel the task here (and the task supports it), memory leaks should not be a
        //problem
        task.cancel(true);
    }

    private void tryLoadContacts() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSION_REQUEST_READ_CONTACTS);
        } else {
            loadContacts();
        }
    }

    private void loadContacts() {
        // From exercise 1
//        String[] projection = {
//                ContactsContract.Contacts._ID,
//                ContactsContract.Contacts.LOOKUP_KEY,
//                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
//        };
//
//        Cursor cursor = getContentResolver().query(
//                ContactsContract.Contacts.CONTENT_URI,
//                projection,
//                null,
//                new String[0],
//                null);
//
//        adapter = new SimpleCursorAdapter(
//                getApplicationContext(),
//                R.layout.list_item_contact,
//                cursor,
//                new String[] {cursor.getColumnName(2)},
//                new int[] {R.id.contactName},
//                0);
//        contactsList.setAdapter(adapter);

        //Exercise 2
        //We should read the contact information from a background thread
        task = new ContactsTask();
        task.execute();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                tryLoadContacts();
            } else {
                Toast.makeText(this, "Unable to get permissions", Toast.LENGTH_LONG).show();
            }
        }
    }

    private final class ContactsTask extends AsyncTask<Void, Void, List<String>> {

        @Override
        protected List<String> doInBackground(Void... voids) {
            List<String> names = new ArrayList<>();

            String[] projection = {
                    ContactsContract.Contacts._ID,
                    ContactsContract.Contacts.LOOKUP_KEY,
                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
            };

            String selectionCriteria = ContactsContract.Contacts.STARRED + " = '1'";

            //Gracefully handle exceptions and close cursor
            try (Cursor cursor = getContentResolver().query(
                    ContactsContract.Contacts.CONTENT_URI,
                    projection,
                    selectionCriteria,
                    new String[0],
                    null)) {

                //Load data from cursor into name list
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        names.add(cursor.getString(2));

                        //Continuously check if this task has been requested to stop
                        if (isCancelled()) {
                            return null;
                        }
                    }
                }
            }

            return names;
        }

        @Override
        protected void onPostExecute(List<String> names) {
            contactsRecycler.setAdapter(new ContactAdapter(names));
        }
    }
}
