package br.com.mfds.ads_handsonworkvi_handsonpizza.cliente;

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
    private EditText etEndereco;
    private EditText etCpf;
    private EditText etTelefone;
    private DatabaseHelper databaseHelper;
    private Cliente c;

    public EditarFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.cliente_fragment_editar, container, false);

        etNome = v.findViewById(R.id.editText_nome_cliente);
        etEndereco = v.findViewById(R.id.editText_endereco_cliente);
        etCpf = v.findViewById(R.id.editText_cpf_cliente);
        etTelefone = v.findViewById(R.id.editText_telefone_cliente);

        Bundle b = getArguments();
        int id_cliente = b.getInt("id");
        databaseHelper = new DatabaseHelper(getActivity());
        c = databaseHelper.getByIdCliente(id_cliente);

        etNome.setText(c.getNome());
        etEndereco.setText(c.getEndereco());
        etCpf.setText(c.getCpf());
        etTelefone.setText(c.getTelefone());

        Button btnVoltar = v.findViewById(R.id.button_voltar_editar_cliente);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_cliente, new ListarFragment()).commit();
            }
        });

        Button btnEditar = v.findViewById(R.id.button_editar_cliente);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editar(id_cliente);
            }
        });

        Button btnExcluir = v.findViewById(R.id.button_excluir_cliente);
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Deseja excluir o Cliente?");
                builder.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        excluir(id_cliente);
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

    /* Função que edita os cliente ao banco de dados */
    private void editar(int id) {
        if(etNome.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Por favor, Informe o nome do Cliente",Toast.LENGTH_LONG).show();
        } else if(etEndereco.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Por favor, Informe o endereço do Cliente",Toast.LENGTH_LONG).show();
        }  else if(etCpf.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Por favor, Informe o CPF do CLiente",Toast.LENGTH_LONG).show();
        }   else if(etTelefone.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Por favor, Informe o telefone do CLiente",Toast.LENGTH_LONG).show();
        } else {
            c = new Cliente();
            c.setId(id);
            c.setNome(etNome.getText().toString());
            c.setEndereco(etEndereco.getText().toString());
            c.setCpf(etCpf.getText().toString());
            c.setTelefone(etTelefone.getText().toString());
            databaseHelper.updateCliente(c);
            Toast.makeText(getActivity(), "Cliente atualizado", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_cliente, new ListarFragment()).commit();
        }
    }

    /* Função que edita os cliente ao banco de dados */
    private void excluir(int id) {
        c = new Cliente();
        c.setId(id);
        databaseHelper.deleteCliente(c);
        Toast.makeText(getActivity(), "Cliente excluído", Toast.LENGTH_LONG).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_cliente, new ListarFragment()).commit();
    }

}