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
import android.widget.EditText;
import android.widget.TextView;

import pl.goldy.danowski.fiszki.R;
import pl.goldy.danowski.fiszki.db.entity.FlashcardEntity;

public class EditFlashcardDialog extends DialogFragment {

    private int position;

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View mainView = inflater.inflate(R.layout.add_flashcard_dialog, null);
        builder.setView(mainView);
        FlashcardEntity card = FlashcardUtility.getCardFromArray(position);

        EditText tvForeignWord = mainView.findViewById(R.id.foreignWord);
        EditText tvForeignUseCase = mainView.findViewById(R.id.foreignUseCase);
        EditText tvPolishWord = mainView.findViewById(R.id.polishWord);
        EditText tvPolishUseCase = mainView.findViewById(R.id.polishUseCase);

        tvForeignWord.setText(card.getForeignWord());
        tvForeignUseCase.setText(card.getForeignUseCase());
        tvPolishWord.setText(card.getPolishWord());
        tvPolishUseCase.setText(card.getPolishUseCase());

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
                    EditText tvForeignWord = getDialog().findViewById(R.id.foreignWord);
                    EditText tvForeignUseCase = getDialog().findViewById(R.id.foreignUseCase);
                    EditText tvPolishWord = getDialog().findViewById(R.id.polishWord);
                    EditText tvPolishUseCase = getDialog().findViewById(R.id.polishUseCase);
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
                        FlashcardUtility.editFlashcard(foreignWord, foreignUseCase, polishWord, polishUseCase, position);
                        d.dismiss();
                    }
                }
            });
        }
    }
}