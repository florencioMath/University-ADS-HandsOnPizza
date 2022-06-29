package br.com.mfds.ads_handsonworkvi_handsonpizza.pizza;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class EditarFragment extends Fragment {

    private EditText etNome;
    private EditText etIngredientes;
    private EditText etTempoPreparo;
    private DatabaseHelper databaseHelper;
    private Pizza p;

    public EditarFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.pizza_fragment_editar, container, false);

        etNome = v.findViewById(R.id.editText_nome_pizza);
        etIngredientes = v.findViewById((R.id.editText_ingredientes_pizza));
        etTempoPreparo = v.findViewById(R.id.editText_tempo_pizza);

        Bundle b = getArguments();
        int id_pizza = b.getInt("id");
        databaseHelper = new DatabaseHelper(getActivity());
        p = databaseHelper.getByIdPizza(id_pizza);

        etNome.setText(p.getNome());
        etIngredientes.setText(p.getIngredientes());
        etTempoPreparo.setText(p.getTempo_preparo());

        Button btnVoltar = v.findViewById(R.id.button_voltar_editar_pizza);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_pizza, new ListarFragment()).commit();
            }
        });

        Button btnEditar = v.findViewById(R.id.button_editar_pizza);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editar(id_pizza);
            }
        });

        Button btnExcluir = v.findViewById(R.id.button_excluir_pizza);
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.deseja_excluir_pizza);
                builder.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        excluir(id_pizza);
                    }
                });
                builder.setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Não faz nada!
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        return v;
    }
    private void editar(int id) {
        if(etNome.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Por favor, Informe o nome da Pizza",Toast.LENGTH_LONG).show();
        } else if(etIngredientes.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Por favor, Informe os ingredientes da Pizza",Toast.LENGTH_LONG).show();
        }  else if(etTempoPreparo.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Por favor, Informe o tempo de preparo da Pizza",Toast.LENGTH_LONG).show();
        } else {
            p = new Pizza();
            p.setId(id);
            p.setNome(etNome.getText().toString());
            p.setIngredientes(etIngredientes.getText().toString());
            p.setTempo_preparo(etTempoPreparo.getText().toString());
            databaseHelper.updatePizza(p);
            Toast.makeText(getActivity(), "Pizza atualizada", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_pizza, new ListarFragment()).commit();
        }
    }

    private void excluir(int id) {
        p = new Pizza();
        p.setId(id);
        databaseHelper.deletePizza(p);
        Toast.makeText(getActivity(), "Pizza excluído", Toast.LENGTH_LONG).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_pizza, new ListarFragment()).commit();
    }

}