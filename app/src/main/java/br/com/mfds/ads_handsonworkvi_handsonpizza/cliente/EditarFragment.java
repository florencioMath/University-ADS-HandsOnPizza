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

import java.util.concurrent.ExecutionException;

import br.com.mfds.ads_handsonworkvi_handsonpizza.R;
import br.com.mfds.ads_handsonworkvi_handsonpizza.database.DatabaseHelper;
import br.com.mfds.ads_handsonworkvi_handsonpizza.webservice.DadosEndereco;
import br.com.mfds.ads_handsonworkvi_handsonpizza.webservice.RetornarEnderecoPeloCep;

public class EditarFragment extends Fragment {

    private EditText etNomeCliente;
    private EditText etCpf;
    private EditText etCep;
    private EditText etLogradouro;
    private EditText etNumero;
    private EditText etBairro;
    private EditText etCidade;
    private EditText etTelefoneCliente;
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

        etNomeCliente = v.findViewById(R.id.editText_nome_cliente);
        etCpf = v.findViewById(R.id.editText_cpf_cliente);
        etCep = v.findViewById(R.id.editText_cep_cliente);
        etLogradouro = v.findViewById(R.id.editText_logradouro_cliente);
        etNumero = v.findViewById(R.id.editText_numero_cliente);
        etBairro = v.findViewById(R.id.editText_bairro_cliente);
        etCidade = v.findViewById(R.id.editText_cidade_cliente);
        etTelefoneCliente = v.findViewById(R.id.editText_telefone_cliente);

        etCep.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    if (c.getCep() != etCep.getText().toString()) {
                        try {
                            DadosEndereco dadosEndereco = new RetornarEnderecoPeloCep(etCep.getText().toString()).execute().get();
                            etLogradouro.setText(dadosEndereco.getLogradouro());
                            etBairro.setText(dadosEndereco.getBairro());
                            etCidade.setText(dadosEndereco.getLocalidade());
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Bundle b = getArguments();
        int id_cliente = b.getInt("id");
        databaseHelper = new DatabaseHelper(getActivity());
        c = databaseHelper.getByIdCliente(id_cliente);
        etNomeCliente.setText(c.getNome());
        etCpf.setText(c.getCpf());
        etCep.setText(c.getCep());
        etLogradouro.setText(c.getLogradouro());
        etNumero.setText(c.getNumero());
        etBairro.setText(c.getBairro());
        etCidade.setText(c.getCidade());
        etTelefoneCliente.setText(c.getTelefone());

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
        if(etNomeCliente.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Por favor, Informe o nome do Cliente",Toast.LENGTH_LONG).show();
        } else if(etCpf.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Por favor, Informe o CPF do Cliente",Toast.LENGTH_LONG).show();
        } else if(etCep.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Por favor, Informe o CEP do Cliente",Toast.LENGTH_LONG).show();
        } else if(etLogradouro.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Por favor, Informe o Logradouro do Cliente",Toast.LENGTH_LONG).show();
        } else if(etNumero.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Por favor, Informe o Numero do Cliente",Toast.LENGTH_LONG).show();
        } else if(etBairro.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Por favor, Informe o Bairro do Cliente",Toast.LENGTH_LONG).show();
        } else if(etCidade.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Por favor, Informe a Cidade do Cliente",Toast.LENGTH_LONG).show();
        } else if(etTelefoneCliente.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Por favor, Informe o telefone do Cliente",Toast.LENGTH_LONG).show();
        } else {
            c = new Cliente();
            c.setId(id);
            c.setNome(etNomeCliente.getText().toString());
            c.setCpf(etCpf.getText().toString());
            c.setCpf(etCep.getText().toString());
            c.setCpf(etLogradouro.getText().toString());
            c.setCpf(etNumero.getText().toString());
            c.setCpf(etBairro.getText().toString());
            c.setCpf(etCidade.getText().toString());
            c.setTelefone(etTelefoneCliente.getText().toString());
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