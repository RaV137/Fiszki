package pl.goldy.danowski.fiszki.languages;

import android.app.DialogFragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import pl.goldy.danowski.fiszki.R;
import pl.goldy.danowski.fiszki.categories.CategoriesActivity;
import pl.goldy.danowski.fiszki.db.entity.LanguageEntity;

public class LanguagesActivity extends AppCompatActivity {

    private View headView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languages);

        LanguageUtility.initialize(getApplication());

        FloatingActionButton fab = findViewById(R.id.addLanguage);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewLang();
            }
        });

        GridView gridView = findViewById(R.id.langGrid);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                langClicked(position);
            }
        });
        registerForContextMenu(gridView);

        headView = findViewById(R.id.languages_list_layout);
        LanguageUtility.printLangs(headView);
    }

    private void langClicked(int position) {
        Intent intent = new Intent(this, CategoriesActivity.class);
        LanguageEntity lang = LanguageUtility.getLang(position);
        intent.putExtra("langId", lang.getId());
        startActivity(intent);
    }

    private void addNewLang() {
        DialogFragment dialog = new AddLanguageDialog();
        dialog.show(getFragmentManager(), "AddLanguageDialog");

        LanguageUtility.printLangs(headView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.default_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.edit:
                editLang((int) info.id);
                return true;
            case R.id.delete:
                deleteLang((int) info.id);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void deleteLang(int id) {
        LanguageUtility.deleteLang(headView, id);
    }

    private void editLang(int id) {
        EditLanguageDialog dialog = new EditLanguageDialog();
        dialog.setPosition(id);
        dialog.show(getFragmentManager(), "EditLanguageDialog");

        LanguageUtility.printLangs(headView);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
