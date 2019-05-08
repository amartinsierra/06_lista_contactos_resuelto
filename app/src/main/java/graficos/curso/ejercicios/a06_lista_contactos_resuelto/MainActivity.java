package graficos.curso.ejercicios.a06_lista_contactos_resuelto;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import graficos.curso.ejercicios.a06_lista_contactos_resuelto.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void mostrar(View v){
        String[] nombreColumnas=new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
        ContentResolver resolver=this.getContentResolver();

        Cursor c=resolver.query(ContactsContract.Data.CONTENT_URI,
                    null,
                    ContactsContract.Data.MIMETYPE+"='"+ ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE+"'",
                    null,
                    null
                );

        //creamos el adaptador
        SimpleCursorAdapter adaptar=new SimpleCursorAdapter(this,
                R.layout.fila,
                c,
                nombreColumnas,
                new int[]{R.id.tvFilaNombre,R.id.tvFilaTelefono},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
                );
        //asignamos adaptador a ListView
        ListView lstContactos=(ListView)this.findViewById(R.id.lstContactos);
        lstContactos.setAdapter(adaptar);

    }
}
