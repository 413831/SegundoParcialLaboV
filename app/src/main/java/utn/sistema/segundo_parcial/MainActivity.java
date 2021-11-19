package utn.sistema.segundo_parcial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import utn.sistema.segundo_parcial.POJOs.User;
import utn.sistema.segundo_parcial.services.UserService;
import utn.sistema.segundo_parcial.ui.DialogAdd;

public class MainActivity extends AppCompatActivity implements Handler.Callback, SearchView.OnQueryTextListener, DialogInterface.OnDismissListener
{
    List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.users = new ArrayList<User>();
        Handler handler = new Handler(this);

        SharedPreferences preferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        String users = preferences.getString("users", "empty");

        if("empty".equals(users))
        {
            UserService userService = new UserService(handler);
            userService.start();

            Log.d("SHARED", "Shared preferences empty");
        }
        else
        {
            Log.d("SHARED", users);
            this.users = this.parseList(users);
        }
        TextView textView = super.findViewById(R.id.txtUsers);
        textView.setText(users);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);

        MenuItem menuItem = menu.findItem(R.id.itSearch);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(item.getItemId() == R.id.itAdd)
        {
            DialogAdd dialogAdd = new DialogAdd(this.users);
            dialogAdd.show(getSupportFragmentManager(), "ADD");
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean handleMessage(@NonNull Message message)
    {
        this.users = this.parseList(message.obj.toString());

        SharedPreferences preferences = super.getSharedPreferences("config", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        Log.d("LIST", message.obj.toString());
        editor.putString("users",message.obj.toString());
        editor.commit();

        return true;
    }

    private List parseList(String jsonString)
    {
        List<User> list = new ArrayList<>();

        try
        {
            JSONArray jsonList = new JSONArray(jsonString);
            for (int i = 0; i < jsonList.length(); i++)
            {
                JSONObject usersJSON = jsonList.getJSONObject(i);
                Integer id = usersJSON.getInt("id");
                String username = usersJSON.getString("username");
                String rol = usersJSON.getString("rol");
                Boolean admin = usersJSON.getBoolean("admin");
                User user = new User(id,username,rol,admin);
                list.add(user);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }



    @Override
    public boolean onQueryTextSubmit(String query)
    {
        SharedPreferences preferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        String users = preferences.getString("users", "empty");


        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        SharedPreferences preferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        String users = preferences.getString("users", "empty");

        TextView textView = super.findViewById(R.id.txtUsers);
        textView.setText(users);
    }
}