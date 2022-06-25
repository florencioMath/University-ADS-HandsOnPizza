package br.com.mfds.ads_handsonworkvi_handsonpizza.cliente;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.mfds.ads_handsonworkvi_handsonpizza.R;

public class MainFragment extends Fragment {

    public MainFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.cliente_fragment_main, container, false);

        if (savedInstanceState == null) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_cliente, new ListarFragment()).commit();
        }

        // Inflate the layout for this fragment
        return v;
    }
}