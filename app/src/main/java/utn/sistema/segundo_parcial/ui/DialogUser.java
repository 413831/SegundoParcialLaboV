package utn.sistema.segundo_parcial.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import utn.sistema.segundo_parcial.POJOs.User;


/**
 * Dialogo para mostrar información de Usuario
 */
public class DialogUser extends AppCompatDialogFragment
{
    User user;
    String query;

    public DialogUser(User user)
    {
        this.user = user;
    }

    public DialogUser(String query)
    {
        this.query = query;
    }

    /**
     * Método para configurar vista de Dialog
     * @param savedInstanceState
     * @return
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        if(this.user != null)
        {
            builder.setTitle("Usuario encontrado");
            builder.setMessage("El rol del usuario es " + this.user.getRol());
        }
        else
        {
            builder.setTitle("Usuario no encontrado");
            builder.setMessage("El usuario " + this.query + " no está dentro de la lista");
        }

        builder.setPositiveButton("CERRAR", null);

        return builder.create();
    }
}
