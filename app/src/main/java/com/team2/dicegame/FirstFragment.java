package com.team2.dicegame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.team2.dicegame.databinding.FragmentFirstBinding;


public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    // this instance will be used to create pseudo-random numbers
    public static final Random RANDOM = new Random();
    private Button rollButton;
    private ImageView dice1, dice2;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value1 = randomDiceValue();
                int value2 = randomDiceValue();

                String left_dice = "dice_" + value1;
                String right_dice = "dice_" + value2;
                String combined_str = left_dice + " " + right_dice;

                Bundle result = new Bundle();
                result.putString("computer_roll", combined_str);
                getChildFragmentManager().setFragmentResult("computer_roll", result);

                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
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