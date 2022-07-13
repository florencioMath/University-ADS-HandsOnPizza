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
import br.com.mfds.ads_handsonworkvi_handsonpizza.fornecedor.Fornecedor;
import br.com.mfds.ads_handsonworkvi_handsonpizza.pizza.Pizza;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "handsonpiza";
    private static final String TABLE_PIZZA = "pizza";
    private static final String TABLE_CLIENTE = "cliente";
    private static final String TABLE_FORNECEDOR = "fornecedor";

    private static final String CREATE_TABLE_PIZZA = "CREATE TABLE " + TABLE_PIZZA + "(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome VARCHAR(100), " +
            "ingredientes VARCHAR(100), " +
            "tempo_preparo VARCHAR(15));";

    private static final String CREATE_TABLE_CLIENTE = "CREATE TABLE " + TABLE_CLIENTE + "(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome VARCHAR(100), " +
            "cep VARCHAR(9), " +
            "logradouro VARCHAR(200), " +
            "numero INTEGER, " +
            "bairro VARCHAR(50), " +
            "cidade VARCHAR(100), " +
            "cpf VARCHAR(15), " +
            "telefone VARCHAR(15));";

    private static final String CREATE_TABLE_FORNECEDOR = "CREATE TABLE " + TABLE_FORNECEDOR + "(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome VARCHAR(100), " +
            "endereco VARCHAR(100), " +
            "cnpj VARCHAR(15), " +
            "telefone VARCHAR(15));";

    private static final String DROP_TABLE_PIZZA = "DROP TABLE IF EXISTS " + TABLE_PIZZA;
    private static final String DROP_TABLE_CLIENTE = "DROP TABLE IF EXISTS " + TABLE_CLIENTE;
    private static final String DROP_TABLE_FORNECEDOR = "DROP TABLE IF EXISTS " + TABLE_FORNECEDOR;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PIZZA);
        db.execSQL(CREATE_TABLE_CLIENTE);
        db.execSQL(CREATE_TABLE_FORNECEDOR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_PIZZA);
        db.execSQL(DROP_TABLE_CLIENTE);
        db.execSQL(DROP_TABLE_FORNECEDOR);
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

    /* Traz todas as Pizza */
    public void getAllPizza (Context context, ListView lv) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "ingredientes", "tempo_preparo"};
        Cursor data = db.query(TABLE_PIZZA, columns, null, null, null, null, "nome");
        int[] to = {R.id.textViewIdListarPizza, R.id.textViewNomeListarPizza, R.id.textViewIngredientesListarPizza, R.id.textViewTempoPreparoListarPizza};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context, R.layout.pizza_item_list_view, data, columns, to, 0);
        lv.setAdapter((simpleCursorAdapter));
        db.close();
    }

    /* Pega a Pizza pelo ID */
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
        cv.put("cpf", c.getCpf());
        cv.put("cep", c.getCep());
        cv.put("logradouro", c.getLogradouro());
        cv.put("numero", c.getNumero());
        cv.put("bairro", c.getBairro());
        cv.put("cidade", c.getCidade());
        cv.put("telefone", c.getTelefone());
        long id = db.insert(TABLE_CLIENTE, null, cv);
        db.close();
        return id;
    }

    /* UPDATE */
    public long updateCliente(Cliente c) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", c.getNome());
        cv.put("cpf", c.getCpf());
        cv.put("cep", c.getCep());
        cv.put("logradouro", c.getLogradouro());
        cv.put("numero", c.getNumero());
        cv.put("bairro", c.getBairro());
        cv.put("cidade", c.getCidade());
        cv.put("telefone", c.getTelefone());
        long id = db.update(TABLE_CLIENTE, cv, "_id = ?", new String[]{String.valueOf(c.getId())});
        db.close();
        return id;
    }

    /* DELETE */
    public long deleteCliente(Cliente c) {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.delete(TABLE_CLIENTE, "_id = ?", new String[]{String.valueOf(c.getId())});
        db.close();
        return id;
    }

    /* Traz todos os Cliente*/
    public void getAllCliente(Context context, ListView lv) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id","nome","cpf" ,"cep" ,"logradouro" ,"numero" ,"bairro" ,"cidade", "telefone"};
        Cursor data = db.query(TABLE_CLIENTE, columns, null, null, null, null, "nome");
        int[] to = {R.id.textViewIdListarCliente, R.id.textViewNomeListarCliente, R.id.textViewCpfListarCliente,R.id.textView_cep_cliente,R.id.textView_logradouro_cliente, R.id.textView_numero_cliente, R.id.textView_bairro_cliente, R.id.textView_cidade_cliente, R.id.textViewTelefoneListarCliente};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context, R.layout.cliente_item_list_view, data, columns, to, 0);
        lv.setAdapter(simpleCursorAdapter);
        db.close();
    }

    /* Pega o Cliente pelo ID*/
    public Cliente getByIdCliente(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id","nome","cpf" ,"cep" ,"logradouro" ,"numero" ,"bairro" ,"cidade", "telefone"};
        String[] args = {String.valueOf(id)};
        Cursor data = db.query(TABLE_CLIENTE, columns, "_id = ?", args, null, null, null);
        data.moveToFirst();
        Cliente c = new Cliente();
        c.setId(data.getInt(0));
        c.setNome(data.getString(1));
        c.setCpf(data.getString(3));
        c.setCep(data.getString(2));
        c.setLogradouro(data.getString(2));
        c.setNumero(data.getInt(3));
        c.setBairro(data.getString(4));
        c.setCidade(data.getString(5));
        c.setTelefone(data.getString(4));
        data.close();
        db.close();
        return c;
    }
    /* Fim CRUD CLIENTE */

    /* Início CRUD FORNECEDOR */
    public long createFornecedor(Fornecedor f) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", f.getNome());
        cv.put("endereco", f.getEndereco());
        cv.put("cnpj", f.getCnpj());
        cv.put("telefone", f.getTelefone());
        long id = db.insert(TABLE_FORNECEDOR, null, cv);
        db.close();
        return id;
    }

    /* UPDATE */
    public long updateFornecedor(Fornecedor f) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", f.getNome());
        cv.put("endereco", f.getEndereco());
        cv.put("cnpj", f.getCnpj());
        cv.put("telefone", f.getTelefone());
        long id = db.update(TABLE_FORNECEDOR, cv, "_id = ?", new String[]{String.valueOf(f.getId())});
        db.close();
        return id;
    }

    /* DELETE */
    public long deleteFornecedor(Fornecedor f) {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.delete(TABLE_FORNECEDOR, "_id = ?", new String[]{String.valueOf(f.getId())});
        db.close();
        return id;
    }

    /* Traz todos os Fornecedores*/
    public void getAllFornecedores(Context context, ListView lv) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "endereco", "cnpj", "telefone"};
        Cursor data = db.query(TABLE_FORNECEDOR, columns, null, null, null, null, "nome");
        int[] to = {R.id.textViewIdListarFornecedor, R.id.textViewNomeListarFornecedor, R.id.textViewEnderecoListarFornecedor, R.id.textViewCnpjListarFornecedor, R.id.textViewTelefoneListarFornecedor};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context, R.layout.fornecedor_item_list_view, data, columns, to, 0);
        lv.setAdapter(simpleCursorAdapter);
        db.close();
    }

    /* Pega o Fornecedor pelo ID*/
    public Fornecedor getByIdFornecedor(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "endereco", "cnpj", "telefone"};
        String[] args = {String.valueOf(id)};
        Cursor data = db.query(TABLE_FORNECEDOR, columns, "_id = ?", args, null, null, null);
        data.moveToFirst();
        Fornecedor f = new Fornecedor();
        f.setId(data.getInt(0));
        f.setNome(data.getString(1));
        f.setEndereco(data.getString(2));
        f.setCnpj(data.getString(3));
        f.setTelefone(data.getString(4));
        data.close();
        db.close();
        return f;
    }
    /* Fim CRUD FORNECEDOR */

}
