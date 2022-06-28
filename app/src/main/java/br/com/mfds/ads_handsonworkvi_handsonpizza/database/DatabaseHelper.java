package br.com.mfds.ads_handsonworkvi_handsonpizza.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import br.com.mfds.ads_handsonworkvi_handsonpizza.R;
import br.com.mfds.ads_handsonworkvi_handsonpizza.cliente.Cliente;
import br.com.mfds.ads_handsonworkvi_handsonpizza.pizza.Pizza;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "handsonpiza";
    private static final String TABLE_PIZZA = "pizza";
    private static final String TABLE_CLIENTE = "cliente";

    private static final String CREATE_TABLE_PIZZA = "CREATE TABLE " + TABLE_PIZZA + "(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome VARCHAR(100), " +
            "ingredientes VARCHAR(100), " +
            "tempo_preparo VARCHAR(15));";

    private static final String CREATE_TABLE_CLIENTE = "CREATE TABLE " + TABLE_CLIENTE + "(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome VARCHAR(100), " +
            "endereco VARCHAR(100), " +
            "cpf VARCHAR(15), " +
            "telefone VARCHAR(15));";

    private static final String DROP_TABLE_PIZZA = "DROP TABLE IF EXISTS " + TABLE_PIZZA;
    private static final String DROP_TABLE_CLIENTE = "DROP TABLE IF EXISTS " + TABLE_CLIENTE;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PIZZA);
        db.execSQL(CREATE_TABLE_CLIENTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_PIZZA);
        db.execSQL(DROP_TABLE_CLIENTE);
        onCreate(db);
    }

    /* Início CRUD PIZZA */
    /* CREATE */
    public long createPizza (Pizza p) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", p.getNome());
        cv.put("ingredientes", p.getIngredientes());
        cv.put("tempo_preparo", p.getTempo_preparo());
        long id = db.insert(TABLE_PIZZA, null, cv);
        db.close();
        return id;
    }

    /* UPDATE */
    public long updatePizza (Pizza p) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", p.getNome());
        cv.put("ingredientes", p.getIngredientes());
        cv.put("tempo_preparo", p.getTempo_preparo());
        long id = db.update(TABLE_PIZZA, cv,"_id = ?", new String[]{String.valueOf(p.getId())});
        db.close();
        return id;
    }

    /* DELETE */
    public long deletePizza (Pizza p) {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.delete(TABLE_PIZZA, "_id = ?", new String[]{String.valueOf(p.getId())});
        db.close();
        return id;
    }

    public void getAllPizza (Context context, ListView lv) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "ingredientes", "tempo_preparo"};
        Cursor data = db.query(TABLE_PIZZA, columns, null, null, null, null, "nome");
        int[] to = {R.id.textViewIdListarPizza, R.id.textViewNomeListarPizza, R.id.textViewIngredientesListarPizza, R.id.textViewTempoPreparoListarPizza};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context, R.layout.pizza_item_list_view, data, columns, to, 0);
        lv.setAdapter((simpleCursorAdapter));
        db.close();
    }

    public Pizza getByIdPizza (int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "ingredientes", "tempo_preparo"};
        String[] args = {String.valueOf(id)};
        Cursor data = db.query(TABLE_PIZZA, columns, "_id = ?", args, null, null, null);
        data.moveToFirst();
        Pizza p = new Pizza();
        p.setId(data.getInt(0));
        p.setNome(data.getString(1));
        p.setIngredientes((data.getString(2)));
        p.setTempo_preparo(data.getString(3));
        data.close();
        db.close();
        return p;
    }
    /* Fim CRUD PIZZA */

    /* Início CRUD CLIENTE */
    public long createCliente(Cliente c) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", c.getNome());
        cv.put("endereco", c.getEndereco());
        cv.put("cpf", c.getCpf());
        cv.put("telefone", c.getTelefone());
        long id = db.insert(TABLE_CLIENTE, null, cv);
        db.close();
        return id;
    }

    public long updateCliente(Cliente c) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", c.getNome());
        cv.put("endereco", c.getEndereco());
        cv.put("cpf", c.getCpf());
        cv.put("telefone", c.getTelefone());
        long id = db.update(TABLE_CLIENTE, cv, "_id = ?", new String[]{String.valueOf(c.getId())});
        db.close();
        return id;
    }

    public long deleteCliente(Cliente c) {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.delete(TABLE_CLIENTE, "_id = ?", new String[]{String.valueOf(c.getId())});
        db.close();
        return id;
    }

    public void getAllCliente(Context context, ListView lv) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "endereco", "cpf", "telefone"};
        Cursor data = db.query(TABLE_CLIENTE, columns, null, null, null, null, "nome");
        int[] to = {R.id.textViewIdListarCliente, R.id.textViewNomeListarCliente, R.id.textViewEnderecoListarCliente, R.id.textViewCpfListarCliente, R.id.textViewTelefoneListarCliente};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context, R.layout.cliente_item_list_view, data, columns, to, 0);
        lv.setAdapter(simpleCursorAdapter);
        db.close();
    }

    public Cliente getByIdCliente(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "endereco", "cpf", "telefone"};
        String[] args = {String.valueOf(id)};
        Cursor data = db.query(TABLE_CLIENTE, columns, "_id ?", args, null, null, null);
        data.moveToFirst();
        Cliente c = new Cliente();
        c.setId(data.getInt(0));
        c.setNome(data.getString(1));
        c.setEndereco(data.getString(2));
        c.setCpf(data.getString(3));
        c.setTelefone(data.getString(4));
        data.close();
        db.close();
        return c;
    }


    /* Fim CRUD CLIENTE */


}
