package com.team2.dicegame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;

import com.team2.dicegame.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private int left_dice_res, right_dice_res;
    public static final Random RANDOM = new Random();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        getParentFragmentManager().setFragmentResultListener("computer_roll",
                this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                String result = bundle.getString(requestKey);
                String[] results = result.split(" ");
                String left_dice = results[0];
                String right_dice = results[1];

                left_dice_res = getResources().getIdentifier(left_dice, "drawable", "com.team2.dicegame");
                right_dice_res = getResources().getIdentifier(right_dice, "drawable", "com.team2.dicegame");
            }
        });

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        binding.fragmentSecondLeftDice.setImageResource(left_dice_res);
        binding.fragmentSecondRightDice.setImageResource(right_dice_res);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // add code for rolling dice randomly
            }
        });
    }

    public static int randomDiceValue() {
        return RANDOM.nextInt(6) + 1;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}