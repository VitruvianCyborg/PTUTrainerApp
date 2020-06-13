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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Vitruvian on 14/02/2018.
 */

public class BaseStatsTrainer extends Fragment implements View.OnClickListener{
    public static final String ARG_PAGE = "ARG_PAGE";

    public static BaseStatsTrainer newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        BaseStatsTrainer fragment = new BaseStatsTrainer();
        fragment.setArguments(args);
        return fragment;
    }
    
    private EditText Trainer_Name;
    //Fields are names of EditText components in xml
    private EditText Lvl_Number, HP_Number, Atk_Number, Def_Number, SpAtk_Number, SpDef_Number, Speed_Number, Feet_Number, Inches_Number, Weight_Number;
    private EditText Acro_Number, Ath_Number, Comb_Number, Intim_Number, Stlth_Number, Surv_Number, GenEd_Number, MedEd_Number, OccEd_Number;
    private EditText PokeEd_Number, TechEd_Number, Guile_Number, Perc_Number,Charm_Number, Comm_Number, Foc_Number, Intuit_Number;

    public Button saveButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base_stats_trainer, container, false);
        return view;
    }
    
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        Trainer_Name = (EditText) view.findViewById(R.id.Pokemon_Name);
        Lvl_Number = (EditText) view.findViewById(R.id.Lvl_Number);
        Feet_Number = (EditText) view.findViewById(R.id.Overland_Number);
        Inches_Number = (EditText) view.findViewById(R.id.Swim_Number);
        Weight_Number = (EditText) view.findViewById(R.id.Weight_Class_Number);
        HP_Number = (EditText) view.findViewById(R.id.HP_Number);
        Atk_Number = (EditText) view.findViewById(R.id.Atk_Number);
        Def_Number = (EditText) view.findViewById(R.id.Def_Number);
        SpAtk_Number = (EditText) view.findViewById(R.id.SpAtk_Number);
        SpDef_Number = (EditText) view.findViewById(R.id.SpDef_Number);
        Speed_Number = (EditText) view.findViewById(R.id.Spd_Number);
        Acro_Number = (EditText) view.findViewById(R.id.Acro_Number);
        Ath_Number = (EditText) view.findViewById(R.id.Ath_Number);
        Comb_Number = (EditText) view.findViewById(R.id.Comb_Number);
        Intim_Number = (EditText) view.findViewById(R.id.Intim_Number);
        Stlth_Number = (EditText) view.findViewById(R.id.Stlth_Number);
        Surv_Number = (EditText) view.findViewById(R.id.Surv_Number);
        GenEd_Number = (EditText) view.findViewById(R.id.GenEd_Number);
        MedEd_Number = (EditText) view.findViewById(R.id.MedEd_Number);
        OccEd_Number= (EditText) view.findViewById(R.id.OccEd_Number);
        PokeEd_Number = (EditText) view.findViewById(R.id.PokeEd_Number);
        TechEd_Number = (EditText) view.findViewById(R.id.TechEd_Number);
        Guile_Number = (EditText) view.findViewById(R.id.Guile_Number);
        Perc_Number = (EditText) view.findViewById(R.id.Perc_Number);
        Charm_Number = (EditText) view.findViewById(R.id.Charm_Number);
        Comm_Number = (EditText) view.findViewById(R.id.Comm_Number);
        Foc_Number = (EditText) view.findViewById(R.id.Foc_Number);
        Intuit_Number = (EditText) view.findViewById(R.id.Intuit_Number);

        saveButton = (Button) view.findViewById(R.id.save);

        saveButton.setOnClickListener(this);

        SharedPreferences savedTrainerStats = this.getActivity().getSharedPreferences("savedTrainerStats", Context.MODE_PRIVATE);
        String characterName = savedTrainerStats.getString(getString(R.string.trainerName), "Trainer Name");

        if (characterName.equals("Trainer Name")){
        } else {
            saveButton.setEnabled(false);
        }
    }
    
    @Override
    public void onClick(View view) throws NumberFormatException{
        try {
            SharedPreferences savedTrainerStats = this.getActivity().getSharedPreferences("savedTrainerStats", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = savedTrainerStats.edit();

            String name = Trainer_Name.getText().toString();
            int bLvl = Integer.parseInt(Lvl_Number.getText().toString());
            String bHeightFeet = Feet_Number.getText().toString();
            String bHeightInches = Inches_Number.getText().toString();
            int bWeight = Integer.parseInt(Weight_Number.getText().toString());
            int bHP = Integer.parseInt(HP_Number.getText().toString());
            int bAtk = Integer.parseInt(Atk_Number.getText().toString());
            int bDef = Integer.parseInt(Def_Number.getText().toString());
            int bSpAtk = Integer.parseInt(SpAtk_Number.getText().toString());
            int bSpDef = Integer.parseInt(SpDef_Number.getText().toString());
            int bSpeed = Integer.parseInt(Speed_Number.getText().toString());
            int bAcro = Integer.parseInt(Acro_Number.getText().toString());
            int bAth = Integer.parseInt(Ath_Number.getText().toString());
            int bComb = Integer.parseInt(Comb_Number.getText().toString());
            int bIntim = Integer.parseInt(Intim_Number.getText().toString());
            int bStlth = Integer.parseInt(Stlth_Number.getText().toString());
            int bSurv = Integer.parseInt(Surv_Number.getText().toString());
            int bGenEd = Integer.parseInt(GenEd_Number.getText().toString());
            int bMedEd = Integer.parseInt(MedEd_Number.getText().toString());
            int bOccEd = Integer.parseInt(OccEd_Number.getText().toString());
            int bPokeEd = Integer.parseInt(PokeEd_Number.getText().toString());
            int bTechEd = Integer.parseInt(TechEd_Number.getText().toString());
            int bGuile = Integer.parseInt(Guile_Number.getText().toString());
            int bPerc = Integer.parseInt(Perc_Number.getText().toString());
            int bCharm = Integer.parseInt(Charm_Number.getText().toString());
            int bComm = Integer.parseInt(Comm_Number.getText().toString());
            int bFoc = Integer.parseInt(Foc_Number.getText().toString());
            int bIntuit = Integer.parseInt(Intuit_Number.getText().toString());

            editor.putInt("baseLevel", bLvl);
            editor.putString(getString(R.string.baseHeightFeet), bHeightFeet);
            editor.putString(getString(R.string.baseHeightInches), bHeightInches);
            editor.putInt(getString(R.string.baseWeight), bWeight);
            editor.putInt(getString(R.string.baseHP), bHP);
            editor.putInt(getString(R.string.baseAtk), bAtk);
            editor.putInt(getString(R.string.baseDef), bDef);
            editor.putInt(getString(R.string.baseSpAtk), bSpAtk);
            editor.putInt(getString(R.string.baseSpDef), bSpDef);
            editor.putInt(getString(R.string.baseSpeed), bSpeed);
            editor.putInt(getString(R.string.baseAcro), bAcro);
            editor.putInt(getString(R.string.baseAth), bAth);
            editor.putInt(getString(R.string.baseComb), bComb);
            editor.putInt(getString(R.string.baseIntim), bIntim);
            editor.putInt(getString(R.string.baseStlth), bStlth);
            editor.putInt(getString(R.string.baseSurv), bSurv);
            editor.putInt(getString(R.string.baseGenEd), bGenEd);
            editor.putInt(getString(R.string.baseMedEd), bMedEd);
            editor.putInt(getString(R.string.baseOccEd), bOccEd);
            editor.putInt(getString(R.string.basePokeEd), bPokeEd);
            editor.putInt(getString(R.string.baseTechEd), bTechEd);
            editor.putInt(getString(R.string.baseGuile), bGuile);
            editor.putInt(getString(R.string.basePerc), bPerc);
            editor.putInt(getString(R.string.baseCharm), bCharm);
            editor.putInt(getString(R.string.baseComm), bComm);
            editor.putInt(getString(R.string.baseFoc), bFoc);
            editor.putInt(getString(R.string.baseIntuit), bIntuit);
            editor.putString(getString(R.string.trainerName), name);
            editor.apply();

            if (name.equals("Kohana Rogu")){
                Toast.makeText(getContext(),"Hi Sam! <3", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Saved!", Toast.LENGTH_SHORT).show();
            }

            EventBus.getDefault().post(new SendBaseStatsTrainer());

            getActivity().finish();
            startActivity(getActivity().getIntent());

        } catch (NumberFormatException e){

            Context context = getContext();
            LayoutInflater inflater = LayoutInflater.from(getActivity());

            View customToastRoot = inflater.inflate(R.layout.toast_stats_error,null);
            Toast customToast = new Toast(context);

            customToast.setView(customToastRoot);
            customToast.setGravity(Gravity.CENTER,0,0);
            customToast.setDuration(Toast.LENGTH_SHORT);
            customToast.show();
        }
    }
}
