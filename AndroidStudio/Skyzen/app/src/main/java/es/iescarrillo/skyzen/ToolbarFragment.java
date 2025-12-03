package es.iescarrillo.skyzen;// ToolbarFragment.java
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import es.iescarrillo.skyzen.R;

public class ToolbarFragment extends Fragment {

    private Toolbar toolbar;
    private BottomNavigationView bottomNav;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_toolbar, container, false);

        toolbar = view.findViewById(R.id.toolbar);
        bottomNav = view.findViewById(R.id.bottom_navigation);

        // Configurar Toolbar
        toolbar.setTitle("SkyZen");
        toolbar.setNavigationOnClickListener(v -> {
            // Acción del botón de retroceso
            onBackButtonPressed();
        });

        // Habilitar menú en la Toolbar
        setHasOptionsMenu(true);

        // Configurar BottomNavigationView
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_vuelo) {
                onVueloSelected();
                return true;
            } else if (id == R.id.nav_menu) {
                onMenuSelected();
                return true;
            } else if (id == R.id.nav_azafata) {
                onAzafataBottomSelected();
                return true;
            }
            return false;
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_carta_vuelo) {
            onCartaVueloSelected();
            return true;
        } else if (item.getItemId() == R.id.action_azafata) {
            onAzafataTopSelected();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // === MÉTODOS QUE PUEDES PERSONALIZAR ===

    protected void onBackButtonPressed() {
        // Por defecto: cierra la actividad o fragment
        if (getActivity() != null) {
            getActivity().onBackPressed();
        }
    }

    protected void onVueloSelected() {
        // Acción para "Vuelo"
    }

    protected void onMenuSelected() {
        // Acción para "Menú"
    }

    protected void onAzafataBottomSelected() {
        // Acción para "Azafata" desde bottom nav
    }

    protected void onCartaVueloSelected() {
        // Acción desde menú superior
    }

    protected void onAzafataTopSelected() {
        // Acción desde menú superior
    }
}