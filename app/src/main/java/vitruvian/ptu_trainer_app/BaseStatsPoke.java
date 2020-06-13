package vitruvian.ptu_trainer_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Vitruvian on 02/03/2018.
 */

public class BaseStatsPoke extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int uniqueID = 0;
    private String spinnerItem;

    public static BaseStatsPoke newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        BaseStatsPoke fragment = new BaseStatsPoke();
        fragment.setArguments(args);
        return fragment;
    }

    private EditText Pokemon_Name;
    //Fields are names of EditText components in xml
    private EditText Lvl_Number, HP_Number, Atk_Number, Def_Number, SpAtk_Number, SpDef_Number, Speed_Number, Weight_Class_Number;
    private EditText Overland_Number, Swim_Number;
    private String type;

    public Button saveButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base_stats_poke, container, false);
        return view;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        SharedPreferences activityID = this.getActivity().getSharedPreferences(getString(R.string.activityID), Context.MODE_PRIVATE);
        uniqueID = activityID.getInt(getString(R.string.unqiueID), 0);

        Pokemon_Name = (EditText) view.findViewById(R.id.Pokemon_Name);
        Lvl_Number = (EditText) view.findViewById(R.id.Lvl_Number);
        Weight_Class_Number = (EditText) view.findViewById(R.id.Weight_Class_Number);
        HP_Number = (EditText) view.findViewById(R.id.HP_Number);
        Atk_Number = (EditText) view.findViewById(R.id.Atk_Number);
        Def_Number = (EditText) view.findViewById(R.id.Def_Number);
        SpAtk_Number = (EditText) view.findViewById(R.id.SpAtk_Number);
        SpDef_Number = (EditText) view.findViewById(R.id.SpDef_Number);
        Speed_Number = (EditText) view.findViewById(R.id.Spd_Number);
        Overland_Number = (EditText) view.findViewById(R.id.Overland_Number);
        Swim_Number = (EditText) view.findViewById(R.id.Swim_Number);

        Spinner spinner = (Spinner) view.findViewById(R.id.Type_Spinner);
        //Creates ArrayAdapter using array defined in strings.xml with default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.pokeType_array, android.R.layout.simple_spinner_item);
        //Set layout for list of choices to default layout
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Apply adapter to spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        saveButton = (Button) view.findViewById(R.id.save);
        saveButton.setOnClickListener(this);

        switch (uniqueID) {
            case 1:
                SharedPreferences savedPoke1Stats = this.getActivity().getSharedPreferences("savedPoke1Stats", Context.MODE_PRIVATE);
                String characterName1 = savedPoke1Stats.getString(getString(R.string.poke1Name), "Pokémon 1");

                if (characterName1.equals("Pokémon 1")) {
                    break;
                } else {
                    saveButton.setEnabled(false);
                }
                break;
            case 2:
                SharedPreferences savedPoke2Stats = this.getActivity().getSharedPreferences("savedPoke2Stats", Context.MODE_PRIVATE);
                String characterName2 = savedPoke2Stats.getString(getString(R.string.poke2Name), "Pokémon 2");

                if (characterName2.equals("Pokémon 2")) {
                    break;
                } else {
                    saveButton.setEnabled(false);
                }
                break;
            case 3:
                SharedPreferences savedPoke3Stats = this.getActivity().getSharedPreferences("savedPoke3Stats", Context.MODE_PRIVATE);
                String characterName3 = savedPoke3Stats.getString(getString(R.string.poke3Name), "Pokémon 3");

                if (characterName3.equals("Pokémon 3")) {
                    break;
                } else {
                    saveButton.setEnabled(false);
                }
                break;
            case 4:
                SharedPreferences savedPoke4Stats = this.getActivity().getSharedPreferences("savedPoke4Stats", Context.MODE_PRIVATE);
                String characterName4 = savedPoke4Stats.getString(getString(R.string.poke4Name), "Pokémon 4");

                if (characterName4.equals("Pokémon 4")) {
                    break;
                } else {
                    saveButton.setEnabled(false);
                }
                break;
            case 5:
                SharedPreferences savedPoke5Stats = this.getActivity().getSharedPreferences("savedPoke5Stats", Context.MODE_PRIVATE);
                String characterName5 = savedPoke5Stats.getString(getString(R.string.poke5Name), "Pokémon 5");

                if (characterName5.equals("Pokémon 5")) {
                    break;
                } else {
                    saveButton.setEnabled(false);
                }
                break;
        }
    }

    @Override
    public void onClick(View view) throws NumberFormatException {
        try {
            String sharedPrefFile = "savedPoke" + uniqueID + "Stats";
            SharedPreferences savedStats = this.getActivity().getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = savedStats.edit();

            String name = Pokemon_Name.getText().toString();
            int bLvl = Integer.parseInt(Lvl_Number.getText().toString());
            int bWeightClass = Integer.parseInt(Weight_Class_Number.getText().toString());
            int bHP = Integer.parseInt(HP_Number.getText().toString());
            int bAtk = Integer.parseInt(Atk_Number.getText().toString());
            int bDef = Integer.parseInt(Def_Number.getText().toString());
            int bSpAtk = Integer.parseInt(SpAtk_Number.getText().toString());
            int bSpDef = Integer.parseInt(SpDef_Number.getText().toString());
            int bSpeed = Integer.parseInt(Speed_Number.getText().toString());
            int bOverland = Integer.parseInt(Overland_Number.getText().toString());
            int bSwim = Integer.parseInt(Swim_Number.getText().toString());

            editor.putInt("baseLevel", bLvl);
            editor.putInt("baseOverland", bOverland);
            editor.putInt("baseSwim", bSwim);
            editor.putInt(getString(R.string.baseWeightClass), bWeightClass);
            editor.putInt(getString(R.string.baseHP), bHP);
            editor.putInt(getString(R.string.baseAtk), bAtk);
            editor.putInt(getString(R.string.baseDef), bDef);
            editor.putInt(getString(R.string.baseSpAtk), bSpAtk);
            editor.putInt(getString(R.string.baseSpDef), bSpDef);
            editor.putInt(getString(R.string.baseSpeed), bSpeed);

            String pokeNameID = "poke" + uniqueID + "Name";
            editor.putString(pokeNameID, name);

            editor.putString("Type", spinnerItem);

            editor.apply();

            Toast.makeText(getContext(), "Saved!", Toast.LENGTH_SHORT).show();

            EventBus.getDefault().post(new SendBaseStatsPoke());

            getActivity().finish();
            startActivity(getActivity().getIntent());

        } catch (NumberFormatException e) {

            Context context = getContext();
            LayoutInflater inflater = LayoutInflater.from(getActivity());

            View customToastRoot = inflater.inflate(R.layout.toast_stats_error, null);
            Toast customToast = new Toast(context);

            customToast.setView(customToastRoot);
            customToast.setGravity(Gravity.CENTER, 0, 0);
            customToast.setDuration(Toast.LENGTH_SHORT);
            customToast.show();
        }
    }

    //For Spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerItem = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {    }
}