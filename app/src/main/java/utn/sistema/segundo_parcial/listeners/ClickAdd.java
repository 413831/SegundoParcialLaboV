package utn.sistema.segundo_parcial.listeners;

import android.view.View;

import androidx.fragment.app.FragmentManager;

import utn.sistema.segundo_parcial.ui.DialogAdd;

public class ClickAdd implements View.OnClickListener
{
    FragmentManager fragmentManager;
    String tag;
    String[] roles;

    public ClickAdd(FragmentManager fragmentManager, String tag, String[] roles)
    {
        this.fragmentManager = fragmentManager;
        this.tag = tag;
        this.roles = roles;
    }

    @Override
    public void onClick(View view)
    {
        // DialogAdd dialogAdd = new DialogAdd(this.roles);
        // dialogAdd.show(this.fragmentManager,this.tag);

    }
}
