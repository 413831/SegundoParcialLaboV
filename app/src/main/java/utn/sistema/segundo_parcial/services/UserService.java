package utn.sistema.segundo_parcial.services;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class UserService extends  Thread
{
    Handler handler;

    public UserService(Handler handler)
    {
        this.handler = handler;
    }

    @Override
    public void run()
    {
        this.getObjetos();
    }

    private void getObjetos()
    {
        String url = "http://192.168.1.36:3001/usuarios";

        HttpConnection httpConnection = HttpConnection.getInstance();
        String s = httpConnection.obtenerElementos(url);
        Log.d("respuesta",s);
        Message message = new Message();
        message.obj = s;
        this.handler.sendMessage(message);
    }
}
