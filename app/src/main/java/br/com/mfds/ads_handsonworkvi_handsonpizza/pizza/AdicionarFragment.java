package br.com.mfds.ads_handsonworkvi_handsonpizza.pizza;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.com.mfds.ads_handsonworkvi_handsonpizza.R;

public class AdicionarFragment extends Fragment {

    public AdicionarFragment() {}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.pizza_fragment_adicionar, container, false);


        Button btnAdicionar = v.findViewById(R.id.button_adicionar_pizza);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_pizza, new ListarFragment()).commit();
            }
        });

        return v;
    }
}