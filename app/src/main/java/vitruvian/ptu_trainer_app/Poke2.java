package vitruvian.ptu_trainer_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;

public class Poke2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private String name;
    TextView Character_Name_View, Character_Name_Menu_View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences activityID = this.getSharedPreferences(getString(R.string.activityID), Context.MODE_PRIVATE);
        SharedPreferences.Editor editorID = activityID.edit();
        editorID.putInt(getString(R.string.unqiueID), 2);
        editorID.apply();

        setContentView(R.layout.activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_app_title);
        setSupportActionBar(toolbar);
        // Remove default title text
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Get access to the custom title view
        SharedPreferences savedPoke2Stats = this.getSharedPreferences("savedPoke2Stats", Context.MODE_PRIVATE);
        String type = savedPoke2Stats.getString("Type", "colorPrimary");
        if (type.equals("Bug")){
            toolbar.setBackgroundColor(getColor(R.color.Bug));
        } else if (type.equals("Dark")){
            toolbar.setBackgroundColor(getColor(R.color.Dark));
        }else if (type.equals("Dragon")){
            toolbar.setBackgroundColor(getColor(R.color.Dragon));
        }else if (type.equals("Electric")){
            toolbar.setBackgroundColor(getColor(R.color.Electric));
        }else if (type.equals("Fairy")){
            toolbar.setBackgroundColor(getColor(R.color.Fairy));
        }else if (type.equals("Fighting")){
            toolbar.setBackgroundColor(getColor(R.color.Fighting));
        }else if (type.equals("Fire")){
            toolbar.setBackgroundColor(getColor(R.color.Fire));
        }else if (type.equals("Flying")){
            toolbar.setBackgroundColor(getColor(R.color.Flying));
        }else if (type.equals("Ghost")){
            toolbar.setBackgroundColor(getColor(R.color.Ghost));
        }else if (type.equals("Grass")){
            toolbar.setBackgroundColor(getColor(R.color.Grass));
        }else if (type.equals("Ground")){
            toolbar.setBackgroundColor(getColor(R.color.Ground));
        }else if (type.equals("Ice")){
            toolbar.setBackgroundColor(getColor(R.color.Ice));
        }else if (type.equals("Normal")){
            toolbar.setBackgroundColor(getColor(R.color.Normal));
        }else if (type.equals("Poison")){
            toolbar.setBackgroundColor(getColor(R.color.Poison));
        }else if (type.equals("Psychic")){
            toolbar.setBackgroundColor(getColor(R.color.Psychic));
        }else if (type.equals("Rock")){
            toolbar.setBackgroundColor(getColor(R.color.Rock));
        }else if (type.equals("Steel")){
            toolbar.setBackgroundColor(getColor(R.color.Steel));
        }else if (type.equals("Water")){
            toolbar.setBackgroundColor(getColor(R.color.Water));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        // get menu from navigationView
        Menu menu = navigationView.getMenu();

        // find MenuItem you want to change
        MenuItem nav_trainer_name = menu.findItem(R.id.Trainer);
        MenuItem nav_poke1_name = menu.findItem(R.id.Poke_1);
        MenuItem nav_poke2_name = menu.findItem(R.id.Poke_2);
        MenuItem nav_poke3_name = menu.findItem(R.id.Poke_3);
        MenuItem nav_poke4_name = menu.findItem(R.id.Poke_4);
        MenuItem nav_poke5_name = menu.findItem(R.id.Poke_5);

        // set new title to the MenuItem
        //Load SharedPref file
        SharedPreferences savedTrainerStats = this.getSharedPreferences("savedTrainerStats", Context.MODE_PRIVATE);
        //Load saved character name, set to Trainer Name if not created yet
        String trainerName = savedTrainerStats.getString(getString(R.string.trainerName), "Trainer Name");
        nav_trainer_name.setTitle(trainerName);

        SharedPreferences savedPoke1Stats = this.getSharedPreferences("savedPoke1Stats", Context.MODE_PRIVATE);
        String poke1Name = savedPoke1Stats.getString(getString(R.string.poke1Name), "Pokémon 1");
        String poke1Type = savedPoke1Stats.getString("Type", "colorPrimary");
        nav_poke1_name.setTitle(poke1Name);
        nav_poke1_name.setIcon(this.getResources().getIdentifier(getMenuIcon(poke1Type), "drawable", this.getPackageName()));

        String poke2Name = savedPoke2Stats.getString(getString(R.string.poke2Name), "Pokémon 2");
        String poke2Type = savedPoke2Stats.getString("Type", "colorPrimary");
        nav_poke2_name.setTitle(poke2Name);
        nav_poke2_name.setIcon(this.getResources().getIdentifier(getMenuIcon(poke2Type), "drawable", this.getPackageName()));

        SharedPreferences savedPoke3Stats = this.getSharedPreferences("savedPoke3Stats", Context.MODE_PRIVATE);
        String poke3Name = savedPoke3Stats.getString(getString(R.string.poke3Name), "Pokémon 3");
        String poke3Type = savedPoke3Stats.getString("Type", "colorPrimary");
        nav_poke3_name.setTitle(poke3Name);
        nav_poke3_name.setIcon(this.getResources().getIdentifier(getMenuIcon(poke3Type), "drawable", this.getPackageName()));

        SharedPreferences savedPoke4Stats = this.getSharedPreferences("savedPoke4Stats", Context.MODE_PRIVATE);
        String poke4Name = savedPoke4Stats.getString(getString(R.string.poke4Name), "Pokémon 4");
        String poke4Type = savedPoke4Stats.getString("Type", "colorPrimary");
        nav_poke4_name.setTitle(poke4Name);
        nav_poke4_name.setIcon(this.getResources().getIdentifier(getMenuIcon(poke4Type), "drawable", this.getPackageName()));

        SharedPreferences savedPoke5Stats = this.getSharedPreferences("savedPoke5Stats", Context.MODE_PRIVATE);
        String poke5Name = savedPoke5Stats.getString(getString(R.string.poke5Name), "Pokémon 5");
        String poke5Type = savedPoke5Stats.getString("Type", "colorPrimary");
        nav_poke5_name.setTitle(poke5Name);
        nav_poke5_name.setIcon(this.getResources().getIdentifier(getMenuIcon(poke5Type), "drawable", this.getPackageName()));

        //Display NavView icons in full colour
        navigationView.setItemIconTintList(null);

        navigationView.setNavigationItemSelectedListener(this);


        // Get the ViewPager and set its PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager()));

        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        //Set tabs to equal width in parent
        tabsStrip.setShouldExpand(true);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);

        //Get id of App Bar title text
        Character_Name_View = (TextView) findViewById(R.id.toolbar_title);
        //Set App Bar title to value of saved character name
        Character_Name_View.setText(poke2Name);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.clear_saved_stats) {
            this.getSharedPreferences("savedPoke2Stats", Context.MODE_PRIVATE).edit().clear().apply();
            Toast.makeText(this, "Data Deleted", Toast.LENGTH_SHORT).show();
            //Following code to reload activity (change intent if not refreshing same activity
            finish();
            startActivity(getIntent());
            //finish click and run code above
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Trainer) {
            Intent intent = new Intent(this, Trainer.class);
            startActivity(intent);
        } else if (id == R.id.Poke_1) {
            Intent intent = new Intent(this, Poke1.class);
            startActivity(intent);
        } else if (id == R.id.Poke_2) {
            Intent intent = new Intent(this, Poke2.class);
            startActivity(intent);
        } else if (id == R.id.Poke_3) {
            Intent intent = new Intent(this, Poke3.class);
            startActivity(intent);
        } else if (id == R.id.Poke_4) {
            Intent intent = new Intent(this, Poke4.class);
            startActivity(intent);
        } else if (id == R.id.Poke_5) {
            Intent intent = new Intent(this, Poke5.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
        private String tabTitles[] = new String[] { "Combat", "Lvl 1 Stats" };

        public SampleFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment =null;
            switch(position){
                case 0:
                    fragment = Fragment.instantiate(Poke2.this, CombatStagesPoke.class.getName());
                    break;
                case 1:
                    fragment = Fragment.instantiate(Poke2.this, BaseStatsPoke.class.getName());
                    break;
            }
            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // Generate title based on item position
            return tabTitles[position];
        }
    }

    private String getMenuIcon(String type){
        String icon = "ic_menu_pokeball";
        switch (type) {
            case "colorPrimary":
                break;
            case "Bug":
                icon = "ic_menu_type_bug";
                break;
            case "Dark":
                icon = "ic_menu_type_dark";
                break;
            case "Dragon":
                icon = "ic_menu_type_dragon";
                break;
            case "Electric":
                icon = "ic_menu_type_electric";
                break;
            case "Fairy":
                icon = "ic_menu_type_fairy";
                break;
            case "Fighting":
                icon = "ic_menu_type_fighting";
                break;
            case "Fire":
                icon = "ic_menu_type_fire";
                break;
            case "Flying":
                icon = "ic_menu_type_flying";
                break;
            case "Ghost":
                icon = "ic_menu_type_ghost";
                break;
            case "Grass":
                icon = "ic_menu_type_grass";
                break;
            case "Ground":
                icon = "ic_menu_type_ground";
                break;
            case "Ice":
                icon = "ic_menu_type_ice";
                break;
            case "Normal":
                icon = "ic_menu_type_normal";
                break;
            case "Poison":
                icon = "ic_menu_type_poison";
                break;
            case "Psychic":
                icon = "ic_menu_type_psychic";
                break;
            case "Rock":
                icon = "ic_menu_type_rock";
                break;
            case "Steel":
                icon = "ic_menu_type_steel";
                break;
            case "Water":
                icon = "ic_menu_type_water";
                break;
        }
        return icon;
    }
}