package utn.sistema.segundo_parcial.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import org.w3c.dom.Text;

import utn.sistema.segundo_parcial.POJOs.User;
import utn.sistema.segundo_parcial.R;

public class DialogUser extends AppCompatDialogFragment
{
    User user;

    public DialogUser(User user)
    {
        this.user = user;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // View
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.dialog_add, null);
        TextView textTitulo = (TextView) view.findViewById(R.id.txtTitulo);
        TextView textRol = (TextView) view.findViewById(R.id.txtRol);

        if(this.user != null)
        {
            textTitulo.setText("Usuario encontrado");
            textRol.setText("El rol del usuario es " + this.user.getRol());
        }
        else
        {
            textTitulo.setText("Usuario no encontrado");
            textRol.setText("El usuario " + this.user.getUsername() + "no está dentro de la lista");
        }

        builder.setView(view);
        builder.setPositiveButton("CERRAR", null);

        return builder.create();
    }
}
