package pl.goldy.danowski.fiszki.flashcards;

import android.app.DialogFragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import pl.goldy.danowski.fiszki.R;

public class FlashcardsActivity extends AppCompatActivity {

    private View headView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcards);

        Intent intent = getIntent();
        String title = intent.getStringExtra("catName");
        if(title != null && !title.equals("")) {
            FlashcardUtility.setCurrentTitle(title);
        }
        setTitle(FlashcardUtility.getCurrentTitle());
        Toast.makeText(this, intent.getStringExtra("catId"), Toast.LENGTH_SHORT).show();

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        assert actionBar != null : "ActionBar is null!";
        actionBar.setDisplayHomeAsUpEnabled(true);

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
        registerForContextMenu(gridView);

        headView = findViewById(R.id.flashcards_list_layout);
        FlashcardUtility.printCards(headView);
    }

    private void flashcardClicked(int position, View view) {
        if(FlashcardUtility.flashcardClicked(position, view)) {
            showInfo(position);
        }
    }

    private void addNewFlashcard() {
        DialogFragment dialog = new AddFlashcardDialog();
        dialog.show(getFragmentManager(), "AddFlashcardDialog");

        FlashcardUtility.printCards(headView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.flashcard_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.edit:
                editCard((int) info.id);
                return true;
            case R.id.showInfo:
                showInfo((int) info.id);
                return true;
            case R.id.delete:
                deleteCard((int) info.id);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void editCard(int id) {
        EditFlashcardDialog dialog = new EditFlashcardDialog();
        dialog.setPosition(id);
        dialog.show(getFragmentManager(), "EditFlashcardDialog");

        FlashcardUtility.printCards(headView);
    }

    private void showInfo(int id) {
        ShowCardDialog dialog = new ShowCardDialog();
        dialog.setPosition(id);
        dialog.show(getFragmentManager(), "ShowCardDialog");
    }

    private void deleteCard(int id) {
        FlashcardUtility.deleteCard(id, headView);
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
