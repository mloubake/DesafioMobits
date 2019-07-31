package br.com.mloubake.desafiomobits.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BancoDados extends SQLiteOpenHelper {
    //todo NA VERSÃO FINAL, TIRAR TODOS OS COMENTÁRIOS E TODOs QUE NÃO AJUDAM EM NADA DE TODAS AS TELAS

    private static final String TAG = "";
    //BD Nome e Versão
    private static final String NOME_BD = "bd.desafio";
    private static final int VERSAO_BD = 3;
    //Tabelas
    protected static final String TABELA_USUARIO = "usuario";
    protected static final String TABELA_CONTA = "conta";
    protected static final String TABELA_MOVIMENTACAO = "movimentacao";
    //Usuário
    protected static final String KEY_USUARIO_ID = "_idUsuario";
    protected static final String KEY_USUARIO_CONTA = "conta";
    protected static final String KEY_USUARIO_SENHA = "senha";
    protected static final String KEY_USUARIO_TIPO = "tipo";
    //Conta
    protected static final String KEY_CONTA_ID = "_idConta";
    protected static final String KEY_CONTA_NUMERO = "conta";
    protected static final String KEY_CONTA_SALDO = "saldo";
    //Movimentação
    protected static final String KEY_MOV_ID = "_idMovimentacao";
    protected static final String KEY_MOV_DATA = "data";
    protected static final String KEY_MOV_HORARIO = "horario";
    protected static final String KEY_MOV_VALOR = "valor";
    protected static final String KEY_MOV_CONTA_ORIGEM = "contaOrigem";
    protected static final String KEY_MOV_CONTA_DESTINO = "contaDestino";
    protected static final String KEY_MOV_TIPO = "tipo";

    public BancoDados(Context context) {
        super(context, NOME_BD,null, VERSAO_BD);
    }

    //Cria Tabelas
    @Override
    public void onCreate(SQLiteDatabase bd) {
        //Tabela Usuario
        String tabelaUsuario = "CREATE TABLE " + TABELA_USUARIO + " ("
                + KEY_USUARIO_ID + " integer primary key autoincrement,"
                + KEY_USUARIO_CONTA + " integer,"
                + KEY_USUARIO_SENHA + " integer,"
                + KEY_USUARIO_TIPO + " varchar(6)"
                + ")";

        //Tabela Conta
        String tabelaConta = "CREATE TABLE " + TABELA_CONTA + " ("
                + KEY_CONTA_ID + " integer primary key autoincrement,"
                + KEY_CONTA_NUMERO + " integer,"
                + KEY_CONTA_SALDO + " float"
                + ")";

        //Tabela Movimentação
        String tabelaMovimentacao = "CREATE TABLE " + TABELA_MOVIMENTACAO + " ("
                + KEY_MOV_ID + " integer primary key autoincrement,"
                + KEY_MOV_DATA + " string,"
                + KEY_MOV_HORARIO + " string,"
                + KEY_MOV_VALOR + " float,"
                + KEY_MOV_CONTA_ORIGEM + " integer,"
                + KEY_MOV_CONTA_DESTINO + " integer,"
                + KEY_MOV_TIPO + " string"
                + ")";

        bd.execSQL(tabelaUsuario);
        bd.execSQL(tabelaConta);
        bd.execSQL(tabelaMovimentacao);
    }

    //Atualiza BD
    @Override
    public void onUpgrade(SQLiteDatabase bd, int arg1, int arg2) {
        //Dropa as tabelas atuais
        bd.execSQL("DROP TABLE IF EXISTS '" + TABELA_USUARIO + "'");
        bd.execSQL("DROP TABLE IF EXISTS '" + TABELA_CONTA +"'");
        bd.execSQL("DROP TABLE IF EXISTS '" + TABELA_MOVIMENTACAO + "'");
        //Cria BD com nova versão
        onCreate(bd);
        Log.d(TAG, "novaVersão: " + bd.getVersion());
    }
}
