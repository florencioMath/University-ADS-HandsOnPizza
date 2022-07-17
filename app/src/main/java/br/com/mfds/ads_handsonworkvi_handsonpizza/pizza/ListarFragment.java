package br.com.mfds.ads_handsonworkvi_handsonpizza.pizza;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import br.com.mfds.ads_handsonworkvi_handsonpizza.R;
import br.com.mfds.ads_handsonworkvi_handsonpizza.database.DatabaseHelper;

public class ListarFragment extends Fragment {

    public ListarFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        /* Lista todas as pizza */
        View v = inflater.inflate(R.layout.pizza_fragment_listar, container, false);
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        ListView lv = v.findViewById(R.id.list_view_listar_pizza);
        databaseHelper.getAllPizza(getActivity(), lv);

        /* Ao clicar em alguma pizza é redirecionado para a pagina de editar */
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            // Passa por parâmetro o valor do ID do pizza que foi clicado
            TextView tvId = view.findViewById(R.id.textViewIdListarPizza);
            Bundle b = new Bundle();
            b.putInt("id", Integer.parseInt(tvId.getText().toString()));

            // Substitui o valor atual do fragmento FramePizza (ListarFragment) para o novo valor (EditarFragment), passando o ID como parâmetro
            EditarFragment editar = new EditarFragment();
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            editar.setArguments(b);
            ft.replace(R.id.frame_pizza, editar).commit();
        }
        });
        return v;
    }
}