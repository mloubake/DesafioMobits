package br.com.mloubake.desafiomobits.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoDados extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "bd.desafio";
    private static final int VERSAO_BD = 1;
    protected static final String TABELA_USUARIO = "usuario";
    private static final String TABELA_CONTA = "conta";
    private static final String TABELA_MOVIMENTACAO = "movimentacao";
    private static final String KEY_ID = "_id";
    protected static final String KEY_CONTA_CORRENTE = "contaCorrente";
    protected static final String KEY_CONTA_SENHA = "contaSenha";
    protected static final String KEY_TIPO_CONTA = "tipoConta";
    private static final String KEY_SALDO = "saldo";
    private static final String KEY_DATA = "data";
    private static final String KEY_HORARIO = "horario";
    private static final String KEY_TIPO_MOVIMENTACAO = "tipoMovimentacao";
    private static final String KEY_VALOR = "valor";

    public BancoDados(Context context) {
        super(context, NOME_BANCO,null, VERSAO_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        String sqlUsuario = "CREATE TABLE" + TABELA_USUARIO + " {"
                + KEY_ID + " integer primary key autoincrement,"
                + KEY_CONTA_CORRENTE + " integer,"
                + KEY_CONTA_SENHA + " integer,"
                + KEY_TIPO_CONTA + " integer"
                + ")";

        String sqlConta = "CREATE TABLE" + TABELA_CONTA + " ("
                + KEY_ID + "integer primary key autoincrement,"
                + KEY_CONTA_CORRENTE + "integer,"
                + KEY_SALDO + "float,"
                + ")";

        String sqlMovimentacao = "CREATE TABLE" + TABELA_MOVIMENTACAO + " ("
                + KEY_ID + "integer,"
                + KEY_DATA + "string,"
                + KEY_HORARIO + "string,"
                + KEY_TIPO_MOVIMENTACAO + "string,"
                + KEY_VALOR + "float"
                + ")";

        bd.execSQL(sqlUsuario);
        bd.execSQL(sqlConta);
        bd.execSQL(sqlMovimentacao);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int arg1, int arg2) {
        bd.execSQL("DROP TABLE IF EXISTS '" + TABELA_USUARIO + "'");
        bd.execSQL("DROP TABLE IF EXISTS '" + TABELA_CONTA +"'");
        bd.execSQL("DROP TABLE IF EXISTS '" + TABELA_MOVIMENTACAO + "'");
        onCreate(bd);
    }
}
