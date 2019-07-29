package br.com.mloubake.desafiomobits.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BancoDados extends SQLiteOpenHelper {

    //todo NA VERSÃO FINAL, TIRAR TODOS OS COMENTÁRIOS E TODOs QUE NÃO AJUDAM EM NADA DE TODAS AS TELAS

    //BD Nome e Versão
    private static final String NOME_BD = "bd.desafio";
    private static final int VERSAO_BD = 2;
    //Tabelas
    protected static final String TABELA_USUARIO = "usuario";
    protected static final String TABELA_CONTA = "conta";
    protected static final String TABELA_MOVIMENTACAO = "movimentacao";
    //Usuário
    protected static final String KEY_USUARIO_ID = "_id";
    protected static final String KEY_USUARIO_CONTA = "conta";
    protected static final String KEY_USUARIO_SENHA = "senha";
    protected static final String KEY_USUARIO_TIPO = "tipo";
    //Conta
    protected static final String KEY_CONTA_ID = "_contaId";
    protected static final String KEY_CONTA_CORRENTE = "conta";
    protected static final String KEY_CONTA_SALDO = "saldo";
    //Movimentação
    protected static final String KEY_MOV_ID = "_id";
    protected static final String KEY_MOV_DATA = "data";
    protected static final String KEY_MOV_HORARIO = "horario";
    protected static final String KEY_MOV_TIPO = "tipo";
    protected static final String KEY_MOV_CONTA_ORIGEM = "contaOrigem";
    protected static final String KEY_MOV_CONTA_DESTINO = "contaDestino";
    protected static final String KEY_MOV_VALOR = "valor";

    public BancoDados(Context context) {
        super(context, NOME_BD,null, VERSAO_BD);
    }

    //Cria Tabelas
    @Override
    public void onCreate(SQLiteDatabase bd) {
        //Exemplo sql na mão
//        bd.execSQL("create table  usuario " +
//                "(_id integer primary key autoincrement, " +
//                "nome text not null, " +
//                "email text not null, " +
//                "senha text not null);");

        //Tabela Usuario
        String sqlUsuario = "CREATE TABLE " + TABELA_USUARIO + " ("
                + KEY_USUARIO_ID + " integer primary key autoincrement,"
                + KEY_USUARIO_CONTA + " integer,"
                + KEY_USUARIO_SENHA + " integer,"
                + KEY_USUARIO_TIPO + " varchar(6)"
                + ")";

        //Tabela Conta
        String sqlConta = "CREATE TABLE " + TABELA_CONTA + " ("
                + KEY_CONTA_ID + " integer primary key autoincrement,"
                + KEY_CONTA_CORRENTE + " integer,"
                + KEY_CONTA_SALDO + " integer"
                + ")";

        //Tabela Movimentação
        String sqlMovimentacao = "CREATE TABLE " + TABELA_MOVIMENTACAO + " ("
                + KEY_MOV_ID + " integer primary key autoincrement,"
                + KEY_MOV_DATA + " string,"
                + KEY_MOV_HORARIO + " string,"
                + KEY_MOV_CONTA_ORIGEM + " integer,"
                + KEY_MOV_CONTA_DESTINO + " integer,"
                + KEY_MOV_TIPO + " string,"
                + KEY_MOV_VALOR + " float"
                + ")";

        //Cria as tabelas no BD
        bd.execSQL(sqlUsuario);
        bd.execSQL(sqlConta);
        bd.execSQL(sqlMovimentacao);
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
        Log.d("", "novaVersão: " + bd.getVersion());
    }
}
