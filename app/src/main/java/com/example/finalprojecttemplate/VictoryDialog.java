package com.example.finalprojecttemplate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class VictoryDialog extends DialogFragment implements View.OnClickListener {

    private final String player;

    public VictoryDialog(String player) {
        this.player = player;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_victory, container, false);
        TextView msg = rootView.findViewById(R.id.victoryMessage);
        msg.setText(this.player.equals("tie") ? getResources().getString(R.string.tie)
                : this.player + " won!");
        rootView.findViewById(R.id.dialog_home_button).setOnClickListener(
                view -> startActivity(new Intent(getContext(), MainActivity.class)));
        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onClick(View view) {

    }
}
