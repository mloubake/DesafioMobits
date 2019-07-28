package br.com.mloubake.desafiomobits.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.com.mloubake.desafiomobits.model.Conta;
import br.com.mloubake.desafiomobits.model.Usuario;

public class BancoDados {
    private SQLiteDatabase db;
    private BancoDadosNucleo banco;

    ContentValues valores;
    long resultado;

    public BancoDados(Context context) {
        banco = new BancoDadosNucleo(context);
    }

    public Usuario pegarUsuarioConta(int conta, int senha) {
        db = banco.getReadableDatabase();

        Usuario usuario = new Usuario();

        String query = "SELECT * FROM " + banco.TABELA_USUARIO;

        Cursor cursor = db.rawQuery(query, null);
        if(cursor != null) {
            cursor.moveToFirst();
        }

        usuario.setContaCorrente(cursor.getInt(cursor.getColumnIndex(banco.KEY_USUARIO_CONTA_CORRENTE)));
        usuario.setContaSenha(cursor.getInt(cursor.getColumnIndex(banco.KEY_USUARIO_CONTA_SENHA)));
        usuario.setTipoCliente(cursor.getInt(cursor.getColumnIndex(banco.KEY_USUARIO_TIPO_CLIENTE)));

        return usuario;
    }

    //Saldo
    public Float recuperarSaldoUsuario(/*numero da conta*/) {
        //Pega a referência para a leitura do DB
        db = banco.getReadableDatabase();

        //Executando a query
        Cursor cursor = db.query(banco.TABELA_CONTA,
                new String[]{banco.KEY_SALDO},
                null /*where com o num conta*/,
                null,
                null,
                null,
                null,
                null);
        //Se achar resultados, move para o primeiro item
        if(cursor != null) {
            cursor.moveToFirst();
        }
        float saldo = cursor.getFloat(cursor.getColumnIndex(banco.KEY_SALDO));

        db.close();

        return saldo;
    }


    //Activity Extrato

    //Activity Saque

    //Activity Depósito
    public void depositarValor(Conta conta) {
        valores = new ContentValues();
        valores.put(BancoDadosNucleo.KEY_SALDO,conta.getSaldo());

//        db.update(banco.TABELA_CONTA,
//                valores,
//                ,
//                new String[]{"" + conta.getSaldo()});
    }

//    public void testeSaldo(float saldo) {
//        db = banco.getWritableDatabase();
//        valores = new ContentValues();
//        valores.put(banco.KEY_SALDO, saldo);
////        db.insert(banco.TABELA_CONTA, null, valores);
//        db.update(banco.TABELA_CONTA, valores, null, null);
//        db.close();
//    }

    //TODO fazer lista de tarefas do que falta

    public void testeSaldo(Conta conta) {
        //Pega a referência para escrever no banco
        db = banco.getWritableDatabase();
        //Instância ContentValues para adicionar chave Coluna/Valor
        valores = new ContentValues();
        //Inserece as infos de Coluna/Valor
        valores.put(banco.KEY_CONTA_CORRENTE, conta.getNumeroConta());
        valores.put(banco.KEY_SALDO, conta.getSaldo());
        //Executa a alteração
        db.insert(banco.TABELA_CONTA,
                null,
                valores);
        //Fecha o banco após operação
        db.close();
    }

    public float testeUpdateSaldo(int conta, float valor) {
        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(banco.KEY_SALDO, valor);

        float saldo = db.update(banco.TABELA_CONTA,
                valores,
                banco.KEY_SALDO + "= ?",
                new String[]{String.valueOf(conta)});

        return saldo;
    }

    //Activity Transferência

    //Activity VisitaGerente
    public String inserirDadoUsuario(int contaCorrente, int contaSenha, int tipoCliente) {
        db = banco.getWritableDatabase();
        valores = new ContentValues();

        valores.put(BancoDadosNucleo.KEY_CONTA_CORRENTE, contaCorrente);
        valores.put(BancoDadosNucleo.KEY_CONTA_SENHA, contaSenha);
        valores.put(BancoDadosNucleo.KEY_TIPO_CONTA, tipoCliente);

        resultado = db.insert(BancoDadosNucleo.TABELA_USUARIO, null, valores);
        db.close();

        if(resultado == -1) {
            return "Erro ao inserir";
        }
        else
            return "Inserção com sucesso";
    }
}

