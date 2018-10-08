package pl.goldy.danowski.fiszki.flashcards;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pl.goldy.danowski.fiszki.R;

public class AddFlashcardDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.add_flashcard_dialog, null))
                .setPositiveButton(R.string.addFlashcardOK, new DialogInterface.OnClickListener() {
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
                    TextView tvForeignWord = getDialog().findViewById(R.id.foreignWord);
                    TextView tvForeignUseCase = getDialog().findViewById(R.id.foreignUseCase);
                    TextView tvPolishWord = getDialog().findViewById(R.id.polishWord);
                    TextView tvPolishUseCase = getDialog().findViewById(R.id.polishUseCase);
                    String foreignWord, foreignUseCase, polishWord, polishUseCase;

                    boolean exit = true;

                    if(TextUtils.isEmpty(tvForeignWord.getText())){
                        tvForeignWord.setError("Pole jest wymagane!");
                        exit = false;
                    }
                    if(TextUtils.isEmpty(tvForeignUseCase.getText())){
                        tvForeignUseCase.setError("Pole jest wymagane!");
                        exit = false;
                    }
                    if(TextUtils.isEmpty(tvPolishWord.getText())){
                        tvPolishWord.setError("Pole jest wymagane!");
                        exit = false;
                    }
                    if(TextUtils.isEmpty(tvPolishUseCase.getText())){
                        tvPolishUseCase.setError("Pole jest wymagane!");
                        exit = false;
                    }
                    if(exit) {
                        foreignWord = tvForeignWord.getText().toString();
                        foreignUseCase = tvForeignUseCase.getText().toString();
                        polishWord = tvPolishWord.getText().toString();
                        polishUseCase = tvPolishUseCase.getText().toString();
                        FlashcardUtility.addNewFlashCard(foreignWord, foreignUseCase, polishWord, polishUseCase);
                        d.dismiss();
                    }
                }
            });
        }
    }
}