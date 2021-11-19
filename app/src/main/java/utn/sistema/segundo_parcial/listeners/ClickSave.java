package utn.sistema.segundo_parcial.listeners;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ToggleButton;

import androidx.appcompat.app.AlertDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONStringer;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import utn.sistema.segundo_parcial.POJOs.User;
import utn.sistema.segundo_parcial.R;

/**
 * Clase para guardar User en SharedPreferences
 */
public class ClickSave implements AlertDialog.OnClickListener, CompoundButton.OnCheckedChangeListener
{
    View view;
    Boolean admin;
    List<User> users;
    SharedPreferences preferences;

    /**
     * Constructor que recibe Vista, Lista de User y SharedPreferences
     * @param view Vista
     * @param users Lista de User
     * @param preferences SharedPreferences
     */
    public ClickSave(View view, List<User> users, SharedPreferences preferences)
    {
        this.view = view;
        this.users = users;
        this.preferences = preferences;
    }

    /**
     * Método para obtener valores de Nuevo User y guardar en SharedPreferences
     * @param dialogInterface Dialog
     * @param i Botón
     */
    @Override
    public void onClick(DialogInterface dialogInterface, int i)
    {
        EditText editUserName = (EditText) this.view.findViewById(R.id.editUsername);
        Spinner spinnerRol = (Spinner) this.view.findViewById(R.id.spinnerRol);
        ToggleButton toggleAdmin = (ToggleButton) this.view.findViewById(R.id.toggleAdmin);

        String userName = editUserName.getText().toString();
        String rol = spinnerRol.getSelectedItem().toString();

        Log.d("click", "USERNAME: " + editUserName.getText().toString());
        Log.d("click", "ROL: " + spinnerRol.getSelectedItem().toString());
        Log.d("click", "ADMIN: " + this.admin);

        if(this.validate(userName,rol,this.admin))
        {
            int lastIndex = this.users.get(this.users.size() - 1).getId();
            User user = new User(lastIndex++, userName, rol, this.admin);
            this.users.add(user);

            try
            {
                JSONArray jsonArray = new JSONArray(this.users.toString());
                Log.d("JSON ARRAY", jsonArray.toString());

                SharedPreferences.Editor editor = this.preferences.edit();
                editor.putString("users",jsonArray.toString());
                editor.commit();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Método para verifica estado de ToggleButton
     * @param compoundButton ToggleButton
     * @param b valor booleano
     */
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b)
    {
        this.admin = b;
    }

    /**
     * Validacion de datos de User
     * @param userName Nombre de usuario
     * @param rol Rol de Usuario
     * @param admin Condicion de admin del Usuario
     * @return Devuelve true si los datos son validos sino false
     */
    private boolean validate(String userName, String rol, Boolean admin)
    {
        if(userName.isEmpty() || userName == null || rol.isEmpty() || rol == null || admin == null)
        {
            return false;
        }
        return true;
    }
}
