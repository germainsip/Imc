package com.example.imc;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class IMCActivity extends AppCompatActivity {
   // La chaine de caractères par défaut
    private final String defaut = "Vous devez cliquer sur le bouton calculer l'IMC pour obtenir un résultat";
    //la cahine de la mega fonction
    private final String megaString = "Vous faites un poids parfait!!! trop fort!!!";

    Button envoyer = null;
    Button raz = null;

    TextInputEditText poids = null;
    TextInputEditText taille = null;

    RadioGroup group = null;

    TextView result = null;

    CheckBox mega = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    //Todo: finir le programme
        //on récupère toutes les vues dont on a besoin
        envoyer = (Button)findViewById(R.id.calcul);

        raz = (Button)findViewById(R.id.raz);

        taille = (TextInputEditText)findViewById(R.id.taille);
        poids = (TextInputEditText)findViewById(R.id.poids);

        mega = (CheckBox)findViewById(R.id.mega);

        group = (RadioGroup)findViewById(R.id.group);

        result = (TextView)findViewById(R.id.result);

        //on attribue les listener
        envoyer.setOnClickListener(envoyerListener);
        raz.setOnClickListener(razListener);
        taille.addTextChangedListener(textWatcher);
        poids.addTextChangedListener(textWatcher);

        //mega.setOnClickListener(checkedListener);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            result.setText(defaut);

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private OnClickListener envoyerListener = new OnClickListener(){
        @Override
        public void onClick(View v){
            if(!mega.isChecked()){
                String t = taille.getText().toString();
                String p =poids.getText().toString();

                float tValue = Float.valueOf(t);

                //on vérifie que la valeur t est cohérente
                if(tValue == 0){
                    Toast.makeText(IMCActivity.this, "C'est pas possible de mesurer cette taille !!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    float pValue = Float.valueOf(p);
                    if(group.getCheckedRadioButtonId()==R.id.radio2)
                        tValue = tValue/100;

                    tValue = (float)Math.pow(tValue,2);
                    float imc = pValue /tValue;
                    result.setText("Votre IMC est "+String.valueOf(imc));
                }

            } else
                result.setText(megaString);
        }
    };

    // Listener du bouton raz
    public OnClickListener razListener = new OnClickListener(){
        @Override
        public void onClick(View v){
            poids.getText().clear();
            taille.getText().clear();
            result.setText(defaut);
        }
    };

    // Listener du bouton de la megafonction
    private OnClickListener checkedListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
// on remet le texte par défaut si c'était le texte de la megafonction qui était écrit
            if (!((CheckBox)v).isChecked() && result.getText().equals(megaString))
                result.setText(defaut);
        }
    };
}
