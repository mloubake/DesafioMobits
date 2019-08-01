package br.com.mloubake.desafiomobits.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import br.com.mloubake.desafiomobits.R;
import br.com.mloubake.desafiomobits.model.Movimentacao;
import br.com.mloubake.desafiomobits.utils.TextoUtils;

public class MovimentacaoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;
    ArrayList<Movimentacao> mMovList;
    int mConta;

    public MovimentacaoAdapter(Context context, ArrayList<Movimentacao> movimentacaoList, int conta) {
        mContext = context;
        mMovList = movimentacaoList;
        mConta = conta;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.viewholder_movimentacao, viewGroup, false);
        MovimentacaoViewHolder movimentacaoViewHolder = new MovimentacaoViewHolder(view);
        return movimentacaoViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder myViewHolder, int position) {
        ((MovimentacaoViewHolder) myViewHolder).txtData.setText(String.valueOf(mMovList.get(position).getData()));
        ((MovimentacaoViewHolder) myViewHolder).txtHorario.setText(String.valueOf(mMovList.get(position).getHorario()));
        if(mMovList.get(position).getValor() >= 0) {
            ((MovimentacaoViewHolder) myViewHolder).txtValor.setText("R$ " + TextoUtils.formatarDuasCasasDecimais(mMovList.get(position).getValor()));
        } else {
            ((MovimentacaoViewHolder) myViewHolder).txtValor.setText("R$ (" + TextoUtils.formatarDuasCasasDecimais(mMovList.get(position).getValor()) +")");
        }
        ((MovimentacaoViewHolder) myViewHolder).txtContaOrigem.setText(String.valueOf(mMovList.get(position).getContaOrigem()));
        ((MovimentacaoViewHolder) myViewHolder).txtContaDestino.setText(String.valueOf(mMovList.get(position).getContaDestino()));
        ((MovimentacaoViewHolder) myViewHolder).txtTipoMov.setText(String.valueOf(mMovList.get(position).getTipoMov()));

        if(mMovList.get(position).getTipoMov().matches("Saque") ||
                mMovList.get(position).getTipoMov().matches("Depósito") ||
                mMovList.get(position).getTipoMov().matches("Solicitação do Gerente")) {
            ((MovimentacaoViewHolder) myViewHolder).layoutMeioInf.setVisibility(View.GONE);
        }
        if(mMovList.get(position).getContaDestino() == mConta) {
            ((MovimentacaoViewHolder) myViewHolder).txtTipoMov.setText("Transferência");
            ((MovimentacaoViewHolder) myViewHolder).txtContaDestino.setText("Minha Conta");
        }

        if(mMovList.get(position).getContaDestino() != mConta) {
            ((MovimentacaoViewHolder) myViewHolder).txtContaOrigem.setText("Minha Conta");
        }
    }

    @Override
    public int getItemCount() {
        return mMovList.size();
    }

    public class MovimentacaoViewHolder extends RecyclerView.ViewHolder {
        TextView txtData;
        TextView txtHorario;
        TextView txtValor;
        TextView txtContaOrigem;
        TextView txtContaDestino;
        TextView txtTipoMov;

        LinearLayout layoutMeioInf;
        LinearLayout layoutInferior;

        public MovimentacaoViewHolder(View itemView) {
            super(itemView);

            txtData = itemView.findViewById(R.id.txtDataMov);
            txtHorario = itemView.findViewById(R.id.txtHorarioMov);
            txtValor = itemView.findViewById(R.id.txtValorMov);
            txtContaOrigem = itemView.findViewById(R.id.txtContaOrigem);
            txtContaDestino = itemView.findViewById(R.id.txtContaDestino);
            txtTipoMov = itemView.findViewById(R.id.txtTipoMov);

            layoutMeioInf = itemView.findViewById(R.id.layoutMeioInf);
            layoutInferior = itemView.findViewById(R.id.layoutInferior);
        }
    }
}
