package pl.goldy.danowski.fiszki.flashcards;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pl.goldy.danowski.fiszki.R;

public class ShowCardDialog extends DialogFragment {

    private int position;
    private boolean foreign;
    private Flashcard card;
    private View mainView;

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        mainView = inflater.inflate(R.layout.flashcard_details, null);
        builder.setView(mainView);
        card = FlashcardUtility.getCardFromArray(position);
        foreign = FlashcardUtility.getForeign();

        printData();

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) { }
                })
                .setNeutralButton(R.string.changeLanguage, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { }
                });
        return builder.create();
    }

   private void printData() {
       TextView tvWord = mainView.findViewById(R.id.word);
       TextView tvUseCase = mainView.findViewById(R.id.useCase);

       if(foreign) {
           tvWord.setText(card.getForeign());
           tvUseCase.setText(card.getForeignUseCase());
       } else {
           tvWord.setText(card.getPolish());
           tvUseCase.setText(card.getPolishUseCase());
       }
   }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }

    @Override
    public void onResume() {
        super.onResume();
        final AlertDialog d = (AlertDialog) getDialog();
        if (d != null) {
            Button button = d.getButton(Dialog.BUTTON_NEUTRAL);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    foreign = !foreign;
                    printData();
                }
            });
        }
    }
}
