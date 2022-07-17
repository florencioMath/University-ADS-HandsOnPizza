package br.com.mfds.ads_handsonworkvi_handsonpizza.fornecedor;

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

    private EditText etNomeFornecedor;
    private EditText etEnderecoFornecedor;
    private EditText etCnpj;
    private EditText etTelefoneFornecedor;
    private DatabaseHelper databaseHelper;
    private Fornecedor f;

    public EditarFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fornecedor_fragment_editar, container, false);
        Button btnAdicionarClienteMain = getParentFragmentManager().findFragmentById(R.id.frame_fornecedor).getActivity().findViewById(R.id.button_adicionar_fornecedor);
        btnAdicionarClienteMain.setVisibility(View.GONE);

        etNomeFornecedor = v.findViewById(R.id.editText_nome_fornecedor);
        etEnderecoFornecedor = v.findViewById(R.id.editText_endereco_fornecedor);
        etCnpj = v.findViewById(R.id.editText_cnpj_fornecedor);
        etTelefoneFornecedor = v.findViewById(R.id.editText_telefone_fornecedor);

        Bundle b = getArguments();
        int id_fornecedor = b.getInt("id");
        databaseHelper = new DatabaseHelper(getActivity());
        f = databaseHelper.getByIdFornecedor(id_fornecedor);

        etNomeFornecedor.setText(f.getNome());
        etEnderecoFornecedor.setText(f.getEndereco());
        etCnpj.setText(f.getCnpj());
        etTelefoneFornecedor.setText(f.getTelefone());

        Button btnVoltar = v.findViewById(R.id.button_voltar_editar_fornecedor);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_fornecedor, new ListarFragment()).commit();
                btnAdicionarClienteMain.setVisibility(View.VISIBLE);
            }
        });

        Button btnEditar = v.findViewById(R.id.button_editar_fornecedor);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editar(id_fornecedor);
                btnAdicionarClienteMain.setVisibility(View.VISIBLE);
            }
        });

        Button btnExcluir = v.findViewById(R.id.button_excluir_fornecedor);
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Deseja excluir o Fornecedor?");
                builder.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        excluir(id_fornecedor);
                        btnAdicionarClienteMain.setVisibility(View.VISIBLE);
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

    /* Função que edita os fornecedores ao banco de dados */
    private void editar(int id) {
        if(etNomeFornecedor.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Por favor, Informe o nome do Fornecedor",Toast.LENGTH_LONG).show();
        } else if(etEnderecoFornecedor.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Por favor, Informe o endereço do Fornecedor",Toast.LENGTH_LONG).show();
        }  else if(etCnpj.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Por favor, Informe o CPF do Fornecedor",Toast.LENGTH_LONG).show();
        }   else if(etTelefoneFornecedor.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Por favor, Informe o telefone do Fornecedor",Toast.LENGTH_LONG).show();
        } else {
            f = new Fornecedor();
            f.setId(id);
            f.setNome(etNomeFornecedor.getText().toString());
            f.setEndereco(etEnderecoFornecedor.getText().toString());
            f.setCnpj(etCnpj.getText().toString());
            f.setTelefone(etTelefoneFornecedor.getText().toString());
            databaseHelper.updateFornecedor(f);
            Toast.makeText(getActivity(), "Fornecedor atualizado", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_fornecedor, new ListarFragment()).commit();
        }
    }

    /* Função que edita os fornecedores ao banco de dados */
    private void excluir(int id) {
        f = new Fornecedor();
        f.setId(id);
        databaseHelper.deleteFornecedor(f);
        Toast.makeText(getActivity(), "Fornecedor excluído", Toast.LENGTH_LONG).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_fornecedor, new ListarFragment()).commit();
    }
}