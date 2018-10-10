package pl.goldy.danowski.fiszki.categories;

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
import android.widget.Toast;

import pl.goldy.danowski.fiszki.R;
import pl.goldy.danowski.fiszki.db.entity.CategoryEntity;
import pl.goldy.danowski.fiszki.flashcards.FlashcardsActivity;

public class CategoriesActivity extends AppCompatActivity {

    private View headView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        Intent intent = getIntent();
        String title = intent.getStringExtra("langName");
        if(title != null && !title.equals("")) {
            CategoriesUtility.setCurrentTitle(title);
        }
        setTitle(CategoriesUtility.getCurrentTitle());
        Toast.makeText(this, intent.getStringExtra("langId"), Toast.LENGTH_SHORT).show();

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        assert actionBar != null : "ActionBar is null!";
        actionBar.setDisplayHomeAsUpEnabled(true);

        CategoriesUtility.initialize();

        FloatingActionButton fab = findViewById(R.id.addCategory);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewCategory();
            }
        });

        GridView gridView = findViewById(R.id.catGrid);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                categoryClicked(position);
            }
        });
        registerForContextMenu(gridView);

        headView = findViewById(R.id.categories_list_layout);
        CategoriesUtility.printCategories(headView);
    }

    private void categoryClicked(int position) {
        Intent intent = new Intent(this, FlashcardsActivity.class);
        CategoryEntity cat = CategoriesUtility.getCategory(position);
        assert cat != null : "Category is null!";
        intent.putExtra("catName", cat.getName());
        intent.putExtra("catId", cat.getId());
        startActivity(intent);
    }

    private void addNewCategory() {
        DialogFragment dialog = new AddCategoryDialog();
        dialog.show(getFragmentManager(), "AddCategoryDialog");

        CategoriesUtility.printCategories(headView);
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
                editCategory((int) info.id);
                return true;
            case R.id.delete:
                deleteCategory((int) info.id);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void deleteCategory(int id) {
        CategoriesUtility.deleteCategory(headView, id);
    }

    private void editCategory(int id) {
        EditCategoryDialog dialog = new EditCategoryDialog();
        dialog.setPosition(id);
        dialog.show(getFragmentManager(), "EditCategoryDialog");

        CategoriesUtility.printCategories(headView);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
