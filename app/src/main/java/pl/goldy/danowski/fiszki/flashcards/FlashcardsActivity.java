package pl.goldy.danowski.fiszki.flashcards;

import android.app.DialogFragment;
import android.content.res.Configuration;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import pl.goldy.danowski.fiszki.R;

public class FlashcardsActivity extends AppCompatActivity {

    private View headView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcards);

        FlashcardUtility.initialize();

        FloatingActionButton fab = findViewById(R.id.addFlashcard);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewFlashcard();
            }
        });

        GridView gridView = findViewById(R.id.flashcardsGrid);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                flashcardClicked(position, v);
            }
        });

        headView = findViewById(R.id.flashcards_list_layout);
        FlashcardUtility.printCards(headView);
    }

    private void flashcardClicked(int position, View view) {
        if(FlashcardUtility.flashcardClicked(position, view)) {
            Toast.makeText(this, "Show details about: " + position, Toast.LENGTH_SHORT).show();

            ShowCardDialog dialog = new ShowCardDialog();
            dialog.setPosition(position);
            dialog.show(getFragmentManager(), "ShowCardDialog");
        }
    }

    private void addNewFlashcard() {
        DialogFragment dialog = new AddFlashcardDialog();
        dialog.show(getFragmentManager(), "AddFlashcardDialog");

        FlashcardUtility.printCards(headView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.flashcards_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.shuffle:
                shuffleCards();
                return true;
            case R.id.changeLanguage:
                changeLanguage();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void changeLanguage() {
        FlashcardUtility.changeLanguage(headView);
    }

    private void shuffleCards() {
        FlashcardUtility.shuffleCards(headView);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

}
