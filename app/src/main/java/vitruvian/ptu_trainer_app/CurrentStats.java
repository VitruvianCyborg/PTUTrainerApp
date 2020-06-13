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
import android.widget.TextView;
import android.widget.Toast;

public class CurrentStats extends Fragment implements View.OnClickListener{
    public static final String ARG_PAGE = "ARG_PAGE";

    Button physAttButton;
    private String cHeightFeet, cHeightInches;
    private int cWeight, cAcro, cAth, cComb, cIntim, cStlth, cSurv, cGenEd, cMedEd, cOccEd, cPokeEd, cTechEd, cGuile, cPerc, cCharm, cComm, cFoc, cIntuit;
    private TextView Acro_Number, Ath_Number, Comb_Number, Intim_Number, Stlth_Number, Surv_Number, GenEd_Number, MedEd_Number, OccEd_Number;
    private TextView PokeEd_Number, TechEd_Number, Guile_Number, Perc_Number,Charm_Number, Comm_Number, Foc_Number, Intuit_Number;

    public static CurrentStats newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        CurrentStats fragment = new CurrentStats();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_stats, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        physAttButton = (Button) view.findViewById(R.id.button_phys_att);
        physAttButton.setOnClickListener(this);

        /*if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }*/
        
        Acro_Number = (TextView) view.findViewById(R.id.Acro_Number);
        Ath_Number = (TextView) view.findViewById(R.id.Ath_Number);
        Comb_Number = (TextView) view.findViewById(R.id.Comb_Number);
        Intim_Number = (TextView) view.findViewById(R.id.Intim_Number);
        Stlth_Number = (TextView) view.findViewById(R.id.Stlth_Number);
        Surv_Number = (TextView) view.findViewById(R.id.Surv_Number);
        GenEd_Number = (TextView) view.findViewById(R.id.GenEd_Number);
        MedEd_Number = (TextView) view.findViewById(R.id.MedEd_Number);
        OccEd_Number = (TextView) view.findViewById(R.id.OccEd_Number);
        PokeEd_Number = (TextView) view.findViewById(R.id.PokeEd_Number);
        TechEd_Number = (TextView) view.findViewById(R.id.TechEd_Number);
        Guile_Number = (TextView) view.findViewById(R.id.Guile_Number);
        Perc_Number = (TextView) view.findViewById(R.id.Perc_Number);
        Charm_Number = (TextView) view.findViewById(R.id.Charm_Number);
        Comm_Number = (TextView) view.findViewById(R.id.Comm_Number);
        Foc_Number = (TextView) view.findViewById(R.id.Foc_Number);
        Intuit_Number = (TextView) view.findViewById(R.id.Intuit_Number);

        SharedPreferences savedTrainerStats = this.getActivity().getSharedPreferences("savedTrainerStats", Context.MODE_PRIVATE);

        cHeightFeet = savedTrainerStats.getString("baseFeet", "0");
        cHeightInches = savedTrainerStats.getString("baseInches", "0");
        cWeight = savedTrainerStats.getInt("baseWeight", 0);
        cAcro = savedTrainerStats.getInt("baseAcro", 0);
        cAth = savedTrainerStats.getInt("baseAth", 0);
        cComb = savedTrainerStats.getInt("baseComb", 0);
        cIntim = savedTrainerStats.getInt("baseIntim", 0);
        cStlth = savedTrainerStats.getInt("baseStlth", 0);
        cSurv = savedTrainerStats.getInt("baseSurv", 0);
        cGenEd = savedTrainerStats.getInt("baseGenEd", 0);
        cMedEd = savedTrainerStats.getInt("baseMedEd", 0);
        cOccEd = savedTrainerStats.getInt("baseOccEd", 0);
        cPokeEd = savedTrainerStats.getInt("basePokeEd", 0);
        cTechEd = savedTrainerStats.getInt("baseTechEd", 0);
        cGuile = savedTrainerStats.getInt("baseGuile", 0);
        cPerc = savedTrainerStats.getInt("basePerc", 0);
        cCharm = savedTrainerStats.getInt("baseCharm", 0);
        cComm = savedTrainerStats.getInt("baseComm", 0);
        cFoc = savedTrainerStats.getInt("baseFoc", 0);
        cIntuit = savedTrainerStats.getInt("baseIntuit", 0);

        Acro_Number.setText(String.valueOf(cAcro));
        Ath_Number.setText(String.valueOf(cAth));
        Comb_Number.setText(String.valueOf(cComb));
        Intim_Number.setText(String.valueOf(cIntim));
        Stlth_Number.setText(String.valueOf(cStlth));
        Surv_Number.setText(String.valueOf(cSurv));
        GenEd_Number.setText(String.valueOf(cGenEd));
        MedEd_Number.setText(String.valueOf(cMedEd));
        OccEd_Number.setText(String.valueOf(cOccEd));
        PokeEd_Number.setText(String.valueOf(cPokeEd));
        TechEd_Number.setText(String.valueOf(cTechEd));
        Guile_Number.setText(String.valueOf(cGuile));
        Perc_Number.setText(String.valueOf(cPerc));
        Charm_Number.setText(String.valueOf(cCharm));
        Comm_Number.setText(String.valueOf(cComm));
        Foc_Number.setText(String.valueOf(cFoc));
        Intuit_Number.setText(String.valueOf(cIntuit));
    }

    @Override
    public void onClick(View view) {
        Context context = getContext();
        LayoutInflater inflater = LayoutInflater.from(getActivity());

        View customToastRoot = inflater.inflate(R.layout.toast_phys_attributes,null);
        Toast customToast = new Toast(context);
        
        int weightClass = 1;
        if (cWeight > 11 && cWeight < 26){
            weightClass = 2;
        }
        else if (cWeight > 25 && cWeight < 51){
            weightClass = 3;
        }
        else if (cWeight > 50 && cWeight < 101){
            weightClass = 4;
        }
        else if (cWeight > 100 && cWeight < 201){
            weightClass = 5;
        }
        else if (cWeight > 200){
            weightClass = 6;
        }
        
        int overland = 3 + ((cAth + cAcro) / 2);
        int swim = overland / 2;
        
        int basePower = 4;
        int modPower = 0;
        if (cAth > 2){modPower += 1;}
        if (cComb > 3){modPower += 1;}
        int power = basePower + modPower;
        
        int throwingRange = 4 + cAth;
        int longJump = cAcro / 2;
        
        int baseHighJump = 0;
        int modHighJump = 0;
        if (cAcro > 3){modHighJump += 1;}
        if (cAcro > 5){modHighJump += 1;}
        int highJump = baseHighJump + modHighJump;
        
        /*TextView Height_Number = customToastRoot.findViewById(R.id.Height_Number);
        TextView Weight_Number = customToastRoot.findViewById(R.id.Weight_Number);*/
        TextView Weight_Class_Number = (TextView) customToastRoot.findViewById(R.id.Weight_Class_Number);
        TextView Overland_Number = (TextView) customToastRoot.findViewById(R.id.Overland_Number);
        TextView Swim_Number = (TextView) customToastRoot.findViewById(R.id.Swim_Number);
        TextView Power_Number = (TextView) customToastRoot.findViewById(R.id.Power_Number);
        TextView Throwing_Range_Number = (TextView) customToastRoot.findViewById(R.id.Throwing_Range_Number);
        TextView Jump_L_Number = (TextView) customToastRoot.findViewById(R.id.Jump_L_Number);
        TextView Jump_H_Number = (TextView) customToastRoot.findViewById(R.id.Jump_H_Number);
        
        /*Height_Number.setText(cHeightFeet + "\' " + cHeightInches + "\"");
        Weight_Number.setText(String.valueOf(cWeight) + " Kg");*/
        Weight_Class_Number.setText(String.valueOf(weightClass));
        Overland_Number.setText(String.valueOf(overland));
        Swim_Number.setText(String.valueOf(swim));
        Power_Number.setText(String.valueOf(power));
        Throwing_Range_Number.setText(String.valueOf(throwingRange));
        Jump_L_Number.setText(String.valueOf(longJump));
        Jump_H_Number.setText(String.valueOf(highJump));

        customToast.setView(customToastRoot);
        customToast.setGravity(Gravity.CENTER,0,0);
        customToast.setDuration(Toast.LENGTH_LONG);
        customToast.show();
    }

    /*@Subscribe
    public void onMessageEvent(SendBaseStatsTrainer event){
    }*/
}
