package br.com.mfds.ads_handsonworkvi_handsonpizza.cliente;

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

    private EditText etNomeCliente;
    private EditText etEnderecoCliente;
    private EditText etCpf;
    private EditText etTelefoneCliente;

    public AdicionarFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.cliente_fragment_adicionar, container, false);

        etNomeCliente = v.findViewById(R.id.editText_nome_cliente);
        etEnderecoCliente = v.findViewById(R.id.editText_endereco_cliente);
        etCpf = v.findViewById(R.id.editText_cpf_cliente);
        etTelefoneCliente = v.findViewById(R.id.editText_telefone_cliente);

        Button btnAdicionar = v.findViewById(R.id.button_adicionar_cliente);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionar();

            }
        });

        Button btnVoltar = v.findViewById(R.id.button_voltar_adicionar_cliente);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_cliente, new ListarFragment()).commit();
            }
        });

        // Inflate the layout for this fragment
        return v;
    }

    private void adicionar() {
    if(etNomeCliente.getText().toString().equals("")) {
        Toast.makeText(getActivity(), "Por favor, Informe o nome do Cliente", Toast.LENGTH_LONG).show();
    }   else if (etEnderecoCliente.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, Informe o endereço do Cliente", Toast.LENGTH_LONG).show();
    }   else if (etCpf.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, Informe o CPF do Cliente", Toast.LENGTH_LONG).show();
    }   else if (etTelefoneCliente.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, Informe o telefone do Cliente", Toast.LENGTH_LONG).show();
    }   else {
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        Cliente c = new Cliente();
        c.setNome(etNomeCliente.getText().toString());
        c.setEndereco(etEnderecoCliente.getText().toString());
        c.setCpf(etCpf.getText().toString());
        c.setTelefone(etTelefoneCliente.getText().toString());
        databaseHelper.createCliente(c);
        Toast.makeText(getActivity(), "Cliente salvo", Toast.LENGTH_LONG).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_cliente, new ListarFragment()).commit();
        }
    }
}