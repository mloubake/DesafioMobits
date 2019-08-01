package br.com.mloubake.desafiomobits.utils;

import java.util.Date;

import br.com.mloubake.desafiomobits.database.BDFuncoes;

public class JurosUtils {
    private static final float TAXA_COMPOSTA = 0.001f;

    public static float calcularJurosNegativo(float saldo, long horarioInicial) {
        Date horario = new Date();
        long horarioFinal = horario.getTime();
        long horarioDif = ((horarioFinal/1000/60) - (horarioInicial/1000/60));
        float montante = (float) (saldo * (Math.pow((1 + TAXA_COMPOSTA), horarioDif)));

//        Foi utilizada a fórmula de Juros Composto
//        Montante = Principal * (1 + taxa) ^ prazo

        return montante;
    }

    public static void setHoraSaldoNegativo(BDFuncoes bdFuncoes, int numeroConta) {
        Date date = new Date();
        long horarioSaldoNeg = date.getTime();
        bdFuncoes.setHoraSaldoNegativo(numeroConta, horarioSaldoNeg);
    }

    public static void atualizaSaldoComJuros(BDFuncoes bdFuncoes, int numeroConta, float saldo, float valorDepositado) {
        long horarioSaldoNegativo = bdFuncoes.getHoraSaldoNegativo(numeroConta).getDataSaldoNegativo();
        float saldoComJurosAdicionado = JurosUtils.calcularJurosNegativo(saldo, horarioSaldoNegativo);
        float saldoAtualizado = saldoComJurosAdicionado + valorDepositado;
        bdFuncoes.alterarSaldo(numeroConta, saldoAtualizado);

        atualizaHoraSaldoNegativo(bdFuncoes, numeroConta, saldoAtualizado);
    }

    public static void atualizaHoraSaldoNegativo(BDFuncoes bdFuncoes, int numeroConta, float saldo ) {
        if(saldo >= 0) {
            bdFuncoes.setHoraSaldoNegativo(numeroConta, 0);
        } else {
            //Assumindo que o valor da dívida atualiza quando são feitos novos depósitos
            bdFuncoes.setHoraSaldoNegativo(numeroConta, new Date().getTime());
        }
    }
}
