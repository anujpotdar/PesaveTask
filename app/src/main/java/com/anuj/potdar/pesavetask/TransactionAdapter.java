package com.anuj.potdar.pesavetask;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anuj.potdar.pesavetask.transaction.Message;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by potda on 5/23/2018.
 */

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MessageViewHolder> {

    private ArrayList<Message> messages;
    private LayoutInflater inflater;
    private Context context;

    public TransactionAdapter(ArrayList<Message> messages, Context context) {
        this.messages = messages;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public TransactionAdapter.MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_tasks, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TransactionAdapter.MessageViewHolder holder, int position) {
        holder.bindItem(messages.get(position));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {

        private TextView transactionName;
        private TextView transactionTime;
        private TextView chillr;
        private TextView transactionAmount;
        private HashMap<String,String> monthHashmap = new HashMap<>();

        public MessageViewHolder(View itemView) {
            super(itemView);
        }

        public void bindItem(Message message) {
            transactionName = itemView.findViewById(R.id.transactionName);
            transactionTime = itemView.findViewById(R.id.transactionTime);
            chillr = itemView.findViewById(R.id.chillrAmount);
            transactionAmount = itemView.findViewById(R.id.transactionAmount);

            transactionName.setText(message.getTransactionName());
            transactionTime.setText(constructDate(message.getTransactionTime()));
            chillr.setText("+"+context.getString(R.string.Rs,message.getChillrAmount()));
            transactionAmount.setText(context.getString(R.string.Rs,message.getTransactionAmount()));
        }

        private String constructDate(String dateTime){

            monthHashmap.put("01","Jan");
            monthHashmap.put("02","Feb");
            monthHashmap.put("03","Mar");
            monthHashmap.put("04","Apr");
            monthHashmap.put("05","May");
            monthHashmap.put("06","Jun");
            monthHashmap.put("07","Jul");
            monthHashmap.put("08","Aug");
            monthHashmap.put("09","Sep");
            monthHashmap.put("10","Oct");
            monthHashmap.put("11","Nov");
            monthHashmap.put("12","Dec");

            String year = dateTime.substring(0,4);
            String day = dateTime.substring(8,10);
            String month = dateTime.substring(5,7);


            return (day+" "+monthHashmap.get(month)+", "+year);
        }
    }
}
