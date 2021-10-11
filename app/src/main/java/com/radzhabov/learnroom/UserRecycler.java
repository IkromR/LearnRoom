package com.radzhabov.learnroom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.radzhabov.learnroom.DataModel.DateConverter;
import com.radzhabov.learnroom.DataModel.User;

import java.util.List;

public class UserRecycler extends RecyclerView.Adapter<UserViewHolder> {

    List<User> data;

    public UserRecycler (List<User> users){
        data = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.user_item_layout,
                viewGroup,
                false
        );
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int i) {
        User user = data.get(i);
        holder.imageView.setImageBitmap(DateConverter.convertByteArray2Image(user.getImage()));
        holder.name.setText(user.getFullName());
        holder.dob.setText(String.valueOf(user.getDob()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
