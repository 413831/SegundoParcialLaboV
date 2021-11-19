package utn.sistema.segundo_parcial.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import utn.sistema.segundo_parcial.POJOs.User;
import utn.sistema.segundo_parcial.R;
import utn.sistema.segundo_parcial.listeners.ClickSave;

/**
 * Clase para agregar Usuario
 */
public class DialogAdd extends AppCompatDialogFragment implements AdapterView.OnItemSelectedListener {
    private List<User> users;
    private String[] roles;

    public DialogAdd(List<User> users)
    {
        this.users = users;
    }

    /**
     * Método para construir el Dialogo para Nuevo Usuario
     * @param savedInstanceState
     * @return
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // View
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.dialog_add, null);
        // Spinner
        Spinner spinner = view.findViewById(R.id.spinnerRol);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_item, this.getRoles(this.users));
        spinner.setAdapter(adapter);
        // Listener
        DialogInterface.OnClickListener onClickListener = new ClickSave(view, this.users, getContext().getSharedPreferences("config", Context.MODE_PRIVATE));
        // Toggle
        ToggleButton toggleAdmin = (ToggleButton) view.findViewById(R.id.toggleAdmin);
        toggleAdmin.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) onClickListener);

        builder.setTitle("Crear Usuario");
        builder.setPositiveButton("GUARDAR", onClickListener);
        builder.setNeutralButton("CERRAR", null);
        builder.setView(view);

        return builder.create();
    }

    /**
     * Método para manipular evento Dismiss
     * @param dialog Dialog
     */
    @Override
    public void onDismiss(@NonNull DialogInterface dialog)
    {
        super.onDismiss(dialog);
        final Activity activity = getActivity();

        if (activity instanceof DialogInterface.OnDismissListener)
        {
            ((DialogInterface.OnDismissListener) activity).onDismiss(dialog);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /**
     * Método para obtener valores de roles de Usuario
     * @param list Listado de User
     * @return Devuelve array de String de Roles
     */
    private String[] getRoles(List<User> list)
    {
        Set<String> roles = new HashSet<>();
        String[] arrayRoles;
        int index = 0;

        for (User user: list)
        {
            roles.add(user.getRol());
        }
        Log.d("ROLES", roles.toString());

        arrayRoles = new String[roles.size()];

        for (String rol: roles)
        {
            arrayRoles[index] = rol;
            index++;
        }

        return arrayRoles;
    }

}
