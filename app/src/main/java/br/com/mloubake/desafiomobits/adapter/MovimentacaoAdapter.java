package br.com.mloubake.desafiomobits.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.mloubake.desafiomobits.R;
import br.com.mloubake.desafiomobits.model.Movimentacao;

public class MovimentacaoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;
    ArrayList<Movimentacao> mMovimentacaoList;

    public MovimentacaoAdapter(Context context, ArrayList<Movimentacao> movimentacaoList) {
        mContext = context;
        mMovimentacaoList = movimentacaoList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.viewholder_movimentacao, viewGroup, false);
        MovimentacaoViewHolder movimentacaoViewHolder = new MovimentacaoViewHolder(view);
        return movimentacaoViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder myViewHolder, int position) {
//        ((MovimentacaoViewHolder) myViewHolder).txtData.setText(mMovimentacaoList.get(position).getData());
//        ((MovimentacaoViewHolder) myViewHolder).txtHorario.setText(mMovimentacaoList.get(position).getHorario());
//        ((MovimentacaoViewHolder) myViewHolder).txtMovimentacao.setText(mMovimentacaoList.get(position).getTipoMov());
//        ((MovimentacaoViewHolder) myViewHolder).txtValor.setText(String.valueOf((int) mMovimentacaoList.get(position).getValor()));
    }

    @Override
    public int getItemCount() {
        return mMovimentacaoList.size();
    }

    public class MovimentacaoViewHolder extends RecyclerView.ViewHolder {
        TextView txtData;
        TextView txtHorario;
        TextView txtMovimentacao;
        TextView txtValor;
        TextView txtTransferencia;
        TextView txtConta;

        public MovimentacaoViewHolder(View itemView) {
            super(itemView);

            txtData = itemView.findViewById(R.id.txtDataExtrato);
            txtHorario = itemView.findViewById(R.id.txtHorarioExtrato);
            txtMovimentacao = itemView.findViewById(R.id.txtMovimentacaoExtrato);
            txtValor = itemView.findViewById(R.id.txtValorExtrato);
            txtTransferencia = itemView.findViewById(R.id.txtTransferencia);
            txtConta = itemView.findViewById(R.id.txtConta);
        }
    }
}
