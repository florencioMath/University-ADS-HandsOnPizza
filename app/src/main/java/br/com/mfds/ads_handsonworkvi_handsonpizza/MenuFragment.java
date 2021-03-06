package br.com.mfds.ads_handsonworkvi_handsonpizza;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import br.com.mfds.ads_handsonworkvi_handsonpizza.pizza.MainFragment;


public class MenuFragment extends Fragment {

    public MenuFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
    }
    public boolean onOptionsItemSelected (MenuItem item) {
        // Verifica qual opção foi selecionada pelo usuário
        switch (item.getItemId()) {
            case R.id.menu_pizza:
                Toast.makeText(getActivity(), "Menu Pizza", Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, new MainFragment()).commit();
                break;
            case R.id.menu_clientes:
                Toast.makeText(getActivity(), "Menu Cliente", Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, new br.com.mfds.ads_handsonworkvi_handsonpizza.cliente.MainFragment()).commit();
                break;
            case R.id.menu_fornecedores:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, new br.com.mfds.ads_handsonworkvi_handsonpizza.fornecedor.MainFragment()).commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}