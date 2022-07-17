package br.com.mfds.ads_handsonworkvi_handsonpizza.pizza;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.mfds.ads_handsonworkvi_handsonpizza.R;
import br.com.mfds.ads_handsonworkvi_handsonpizza.database.DatabaseHelper;

public class AdicionarFragment extends Fragment {

    private EditText etNome;
    private EditText etIngredientes;
    private EditText etTempoPreparo;

    public AdicionarFragment() {}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Button btnAdicionarPizzaMain = getParentFragmentManager().findFragmentById(R.id.frame_pizza).getActivity().findViewById(R.id.button_adicionar_pizza);
        btnAdicionarPizzaMain.setVisibility(View.GONE);

        View v = inflater.inflate(R.layout.pizza_fragment_adicionar, container, false);

        // localiza os elementos da interface pelo ID (definidos no arquivo XML)
        etNome = v.findViewById(R.id.editText_nome_pizza);
        etIngredientes = v.findViewById((R.id.editText_ingredientes_pizza));
        etTempoPreparo = v.findViewById(R.id.editText_tempo_pizza);

        Button btnAdicionar = v.findViewById(R.id.button_adicionar_pizza);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* Função que adiciona as pizza ao banco de dados */
                if(etNome.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Por favor, Informe o nome da Pizza",Toast.LENGTH_LONG).show();
                } else if(etIngredientes.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Por favor, Informe os ingredientes da Pizza",Toast.LENGTH_LONG).show();
                }  else if(etTempoPreparo.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Por favor, Informe o tempo de preparo da Pizza",Toast.LENGTH_LONG).show();
                } else {
                    DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
                    // Setar os valores para a classe Pizza
                    Pizza p = new Pizza();
                    p.setNome(etNome.getText().toString());
                    p.setIngredientes(etIngredientes.getText().toString());
                    p.setTempo_preparo(etTempoPreparo.getText().toString());
                    databaseHelper.createPizza(p);
                    Toast.makeText(getActivity(), "Pizza Salvo", Toast.LENGTH_LONG).show();
                    // Substitui o valor atual do fragmento FramePizza (AdicionarFragment) para o novo valor (ListarFragment)
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_pizza, new ListarFragment()).commit();
                    btnAdicionarPizzaMain.setVisibility(View.VISIBLE);
                }
            }
        });

        Button btnVoltar = v.findViewById(R.id.button_voltar_adicionar_pizza);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_pizza, new ListarFragment()).commit();
                btnAdicionarPizzaMain.setVisibility(View.VISIBLE);
            }
        });


        // Inflate the layout for this fragment
        return v;
    }

}