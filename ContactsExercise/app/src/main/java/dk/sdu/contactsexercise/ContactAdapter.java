package dk.sdu.contactsexercise;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<String> contactNames;

    public ContactAdapter(List<String> contactNames) {
        this.contactNames = contactNames;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View cellView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_contact, viewGroup, false);
        return new ContactViewHolder(cellView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder contactViewHolder, int i) {
        contactViewHolder.textView.setText(contactNames.get(i));
    }

    @Override
    public int getItemCount() {
        return contactNames.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public ContactViewHolder(@NonNull View view) {
            super(view);
            this.textView = view.findViewById(R.id.contactName);
        }
    }
}