package pl.goldy.danowski.fiszki.categories;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import pl.goldy.danowski.fiszki.R;
import pl.goldy.danowski.fiszki.db.entity.CategoryEntity;

public class EditCategoryDialog extends DialogFragment {

    private int position;

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View mainView = inflater.inflate(R.layout.add_cat_dialog, null);
        builder.setView(mainView);
        CategoryEntity cat = CategoriesUtility.getCategory(position);

        EditText etName = mainView.findViewById(R.id.name);
        assert cat != null : "Category is null!";
        etName.setText(cat.getName());

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        return builder.create();
    }

    @Override
    public void onResume() {
        super.onResume();
        final AlertDialog d = (AlertDialog)getDialog();
        if(d != null) {
            Button positiveButton = d.getButton(Dialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText mName = getDialog().findViewById(R.id.name);

                    if(TextUtils.isEmpty(mName.getText())){
                        mName.setError("Pole jest wymagane!");
                    } else {
                        String name = mName.getText().toString();
                        CategoriesUtility.editCategory(position, name);
                        d.dismiss();
                    }
                }
            });
        }
    }
}
