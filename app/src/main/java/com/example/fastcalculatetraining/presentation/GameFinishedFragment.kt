package com.example.fastcalculatetraining.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fastcalculatetraining.R
import com.example.fastcalculatetraining.databinding.FragmentGameFinishedBinding
import com.example.fastcalculatetraining.domain.models.GameResult

class GameFinishedFragment : Fragment() {

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val args by navArgs<GameFinishedFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToBackListeners()
        setValues()
    }

    private fun setValues() {
        binding.emojiResult.setImageResource(setImage())
        binding.tvRequiredAnswers.text = String.format(
            context?.resources?.getString(R.string.required_score).toString(),
            args.gameResult.gameSettings.minCountOfRightAnswers
        )
        binding.tvRequiredPercentage.text = String.format(
            context?.resources?.getString(R.string.required_percentage).toString(),
            args.gameResult.gameSettings.minPercentOfRightAnswers
        )
        binding.tvScoreAnswers.text = String.format(
            context?.resources?.getString(R.string.score_answers).toString(),
            args.gameResult.countOfRightAnswers.toString()
        )
        binding.tvScorePercentage.text = String.format(
            context?.resources?.getString(R.string.score_percentage).toString(),
            countPercentage().toString()
        )
    }

    private fun countPercentage(): Int {
        if (args.gameResult.countOfRightAnswers == 0) {
            return 0
        }
        return ((args.gameResult.countOfQuestions / args.gameResult.countOfRightAnswers.toDouble()) * 100).toInt()
    }

    private fun setImage(): Int {
        return if (args.gameResult.winner) {
            R.drawable.ic_happy_smile
        } else {
            R.drawable.ic_sad_smile
        }
    }

    private fun setToBackListeners() {
        binding.buttonRetry.setOnClickListener {
            returnToChooseLevel()
        }
    }

    private fun returnToChooseLevel() {
        findNavController().popBackStack()
    }

    companion object {

        private const val KEY_GAME_RESULT = "game result"

        fun newInstance(gameResult: GameResult): GameFinishedFragment {
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_GAME_RESULT, gameResult)
                }
            }
        }
    }
}