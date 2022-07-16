package br.com.mfds.ads_handsonworkvi_handsonpizza.cliente;

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

public class AdicionarFragment extends Fragment {

    private EditText etNomeCliente;
    private EditText etCpf;
    private EditText etCep;
    private EditText etLogradouro;
    private EditText etNumero;
    private EditText etBairro;
    private EditText etCidade;
    private EditText etTelefoneCliente;

    public AdicionarFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Button btnAdicionarClienteMain = getParentFragmentManager().findFragmentById(R.id.frame_cliente).getActivity().findViewById(R.id.button_adicionar_cliente);
        btnAdicionarClienteMain.setVisibility(View.GONE);

        View v = inflater.inflate(R.layout.cliente_fragment_adicionar, container, false);

        etNomeCliente = v.findViewById(R.id.editTextEditarNomeCliente);
        etCpf = v.findViewById(R.id.editTextEditarCpfCliente);
        etCep = v.findViewById(R.id.editTextEditarCepCliente);
        etLogradouro = v.findViewById(R.id.editTextEditarLogradouroCliente);
        etNumero = v.findViewById(R.id.editTextEditarNumeroCliente);
        etBairro = v.findViewById(R.id.editTextEditarBairroCliente);
        etCidade = v.findViewById(R.id.editTextEditarCidadeCliente);
        etTelefoneCliente = v.findViewById(R.id.editTextEditarTelefoneCliente);

        etCep.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
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
        });

        Button btnAdicionar = v.findViewById(R.id.button_adicionar_cliente);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Adiciona os cliente ao banco de dados */

                if(etNomeCliente.getText().toString().equals("")) {
                        Toast.makeText(getActivity(), "Por favor, Informe o nome do Cliente", Toast.LENGTH_LONG).show();
                    } else if (etCpf.getText().toString().equals("")) {
                        Toast.makeText(getActivity(), "Por favor, Informe o CPF do Cliente", Toast.LENGTH_LONG).show();
                    }  else if (etCep.getText().toString().equals("")) {
                        Toast.makeText(getActivity(), "Por favor, Informe o CEP do Cliente", Toast.LENGTH_LONG).show();
                    } else if (etLogradouro.getText().toString().equals("")) {
                        Toast.makeText(getActivity(), "Por favor, Informe o Logradouro do Cliente", Toast.LENGTH_LONG).show();
                    } else if (etNumero.getText().toString().equals("")) {
                        Toast.makeText(getActivity(), "Por favor, Informe o Numero do Cliente", Toast.LENGTH_LONG).show();
                    } else if (etBairro.getText().toString().equals("")) {
                        Toast.makeText(getActivity(), "Por favor, Informe o Bairro do Cliente", Toast.LENGTH_LONG).show();
                    } else if (etCidade.getText().toString().equals("")) {
                        Toast.makeText(getActivity(), "Por favor, Informe a Cidade do Cliente", Toast.LENGTH_LONG).show();
                    }else if (etTelefoneCliente.getText().toString().equals("")) {
                        Toast.makeText(getActivity(), "Por favor, Informe o telefone do Cliente", Toast.LENGTH_LONG).show();
                    } else {
                        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
                        Cliente c = new Cliente();
                        c.setNome(etNomeCliente.getText().toString());
                        c.setCpf(etCpf.getText().toString());
                        c.setCep(etCep.getText().toString());
                        c.setLogradouro(etLogradouro.getText().toString());
                        c.setNumero(Integer.parseInt(etNumero.getText().toString()));
                        c.setBairro(etBairro.getText().toString());
                        c.setCidade(etCidade.getText().toString());
                        c.setTelefone(etTelefoneCliente.getText().toString());
                        databaseHelper.createCliente(c);
                        Toast.makeText(getActivity(), "Cliente salvo", Toast.LENGTH_LONG).show();
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_cliente, new ListarFragment()).commit();
                        btnAdicionarClienteMain.setVisibility(View.VISIBLE);}

            }
        });

        Button btnVoltar = v.findViewById(R.id.button_voltar_adicionar_cliente);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_cliente, new ListarFragment()).commit();
                btnAdicionarClienteMain.setVisibility(View.VISIBLE);
            }
        });

        // Inflate the layout for this fragment
        return v;
    }

}