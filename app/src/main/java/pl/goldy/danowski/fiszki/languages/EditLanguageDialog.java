package pl.goldy.danowski.fiszki.languages;

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
import pl.goldy.danowski.fiszki.db.entity.LanguageEntity;

public class EditLanguageDialog extends DialogFragment {

    private int position;

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View mainView = inflater.inflate(R.layout.add_lang_dialog, null);
        builder.setView(mainView);
        LanguageEntity lang = LanguageUtility.getLang(position);

        EditText etName = mainView.findViewById(R.id.name);
        etName.setText(lang.getName());

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
                        LanguageUtility.editLang(position, name);
                        d.dismiss();
                    }
                }
            });
        }
    }
}