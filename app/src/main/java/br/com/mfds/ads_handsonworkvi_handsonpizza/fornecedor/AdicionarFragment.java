package br.com.mfds.ads_handsonworkvi_handsonpizza.fornecedor;

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

    private EditText etNomeFornecedor;
    private EditText etEnderecoFornecedor;
    private EditText etCnpj;
    private EditText etTelefoneFornecedor;

    public AdicionarFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Button btnAdicionarClienteMain = getParentFragmentManager().findFragmentById(R.id.frame_fornecedor).getActivity().findViewById(R.id.button_adicionar_fornecedor);
        btnAdicionarClienteMain.setVisibility(View.GONE);

        View v = inflater.inflate(R.layout.fornecedor_fragment_adicionar, container, false);

        etNomeFornecedor = v.findViewById(R.id.editText_nome_fornecedor);
        etEnderecoFornecedor = v.findViewById(R.id.editText_endereco_fornecedor);
        etCnpj = v.findViewById(R.id.editText_cnpj_fornecedor);
        etTelefoneFornecedor = v.findViewById(R.id.editText_telefone_fornecedor);

        Button btnAdicionar = v.findViewById(R.id.button_adicionar_fornecedor);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* Função que adiciona os fornecedor ao banco de dados */
                if(etNomeFornecedor.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Por favor, Informe o nome do Fornecedor", Toast.LENGTH_LONG).show();
                }   else if (etEnderecoFornecedor.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Por favor, Informe o endereço do Fornecedor", Toast.LENGTH_LONG).show();
                }   else if (etCnpj.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Por favor, Informe o CNPJ do Fornecedor", Toast.LENGTH_LONG).show();
                }   else if (etTelefoneFornecedor.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Por favor, Informe o telefone do Fornecedor", Toast.LENGTH_LONG).show();
                }   else {
                    DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
                    Fornecedor f = new Fornecedor();
                    f.setNome(etNomeFornecedor.getText().toString());
                    f.setEndereco(etEnderecoFornecedor.getText().toString());
                    f.setCnpj(etCnpj.getText().toString());
                    f.setTelefone(etTelefoneFornecedor.getText().toString());
                    databaseHelper.createFornecedor(f);
                    Toast.makeText(getActivity(), "Fornecedor salvo", Toast.LENGTH_LONG).show();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_fornecedor, new ListarFragment()).commit();
                    btnAdicionarClienteMain.setVisibility(View.VISIBLE);
                }
            }
        });

        Button btnVoltar = v.findViewById(R.id.button_voltar_adicionar_fornecedor);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_fornecedor, new ListarFragment()).commit();
                btnAdicionarClienteMain.setVisibility(View.VISIBLE);
            }
        });

        // Inflate the layout for this fragment
        return v;
    }
}