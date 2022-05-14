package com.List.listimplementationsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.miniprojet.R;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.List.listimplementationsqlite.adapter.PersonneRecyclerViewAdapter;
import com.List.listimplementationsqlite.dialog.AddPersonneDialog;
import com.List.listimplementationsqlite.entity.Personne;
import com.List.listimplementationsqlite.repository.provider.PersonneDataProvider;
import com.List.listimplementationsqlite.toutchListener.RecyclerTouchListener;

import java.util.List;
public class ShowList extends AppCompatActivity implements AddPersonneDialog.AddPersonCallBack {

    private RecyclerView mRecyclerView;
    private FloatingActionButton mAddPersonneFAB;
    private List<Personne> personneList;
    private PersonneRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);
        initView();
        initEvent();
        initData();
    }
    public void initView(){

        Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/
        getSupportActionBar().setTitle(getString(R.string.contact));


        mRecyclerView = findViewById(R.id.recycler_view);
        mAddPersonneFAB = findViewById(R.id.addPersonneFAB);
    }


    public void initEvent(){

        mAddPersonneFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddPersonneDialog dialog = new AddPersonneDialog(ShowList.this,ShowList.this);
                dialog.show();
            }
        });
    }

    public void initData(){

      /*  List<Personne> personneList = new ArrayList<>();
        personneList.add(new Personne("ali","ISAMM", "isam@gmail.com", 20, "28307488"));
        personneList.add(new Personne("ali1","ISAMM1", "isam2@gmail.com", 21, "28307488"));
        personneList.add(new Personne("ali2","ISAMM2", "isam3@gmail.com", 22, "28307488"));
        personneList.add(new Personne("ali3","ISAMM3", "isam4@gmail.com", 23, "28307488"));
        personneList.add(new Personne("ali4","ISAMM", "isam@gmail.com", 20, "28307488"));
        personneList.add(new Personne("ali15","ISAMM1", "isam2@gmail.com", 21, "28307488"));
        personneList.add(new Personne("ali26","ISAMM2", "isam3@gmail.com", 22, "28307488"));
        personneList.add(new Personne("ali37","ISAMM3", "isam4@gmail.com", 23, "28307488"));
*/


        mAdapter = new PersonneRecyclerViewAdapter(ShowList.this);
        personneList = PersonneDataProvider.getInstance().getAllPersonnes(this);
        mAdapter.addAll(personneList);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(ShowList.this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Toast.makeText(ShowList.this,"click" + position , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLongClick(View view, int position) {

                Toast.makeText(ShowList.this,"Longclick" + position , Toast.LENGTH_LONG).show();

            }
        }));

    }

    @Override
    public void onPersonAdded(Personne personne) {
        long resultInsert = PersonneDataProvider.getInstance().addPersonne(this, personne);
        if (resultInsert != -1) {
            personneList.add(personne);
            mAdapter.add(personne);
        }
    }


  /*  @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            Toast.makeText(this,"Click back button",Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }*/
}