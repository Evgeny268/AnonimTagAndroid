package com.tagchat;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends BaseAdapter {
    List<Message> messages = new ArrayList<>();
    Context context;

    public MessageAdapter(Context context) {
        this.context = context;
    }

    public void add(Message message){
        this.messages.add(message);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int i) {
        return messages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MessageViewHolder holder = new MessageViewHolder();
        LayoutInflater messageInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        Message message = messages.get(position);
        if (message.isBelongsToCurrentUser()){
            convertView = messageInflater.inflate(R.layout.my_message, null);
            holder.messageBody = (TextView) convertView.findViewById(R.id.message_body);
            convertView.setTag(holder);
            holder.messageBody.setText(message.getText());
        }else {
            convertView = messageInflater.inflate(R.layout.their_message, null);
            holder.messageBody = (TextView) convertView.findViewById(R.id.message_body);
            convertView.setTag(holder);
            holder.messageBody.setText(message.getText());
        }
        return convertView;
    }

    class MessageViewHolder {
        public TextView messageBody;
    }
}
