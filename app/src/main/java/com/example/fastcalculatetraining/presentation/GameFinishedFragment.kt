package com.example.fastcalculatetraining.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    private lateinit var gameResult: GameResult

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToBackListeners()
        setValues()
    }

    private fun setValues() {
        binding.emojiResult.setImageResource(setImage())
        binding.tvRequiredAnswers.text = String.format(
            context?.resources?.getString(R.string.required_score).toString(),
            gameResult.gameSettings.minCountOfRightAnswers
        )
        binding.tvRequiredPercentage.text = String.format(
            context?.resources?.getString(R.string.required_percentage).toString(),
            gameResult.gameSettings.minPercentOfRightAnswers
        )
        binding.tvScoreAnswers.text = String.format(
            context?.resources?.getString(R.string.score_answers).toString(),
            gameResult.countOfRightAnswers.toString()
        )
        binding.tvScorePercentage.text = String.format(
            context?.resources?.getString(R.string.score_percentage).toString(),
            countPercentage().toString()
        )
    }

    private fun countPercentage(): Int {
        if (gameResult.countOfRightAnswers == 0) {
            return 0
        }
        return ((gameResult.countOfQuestions / gameResult.countOfRightAnswers.toDouble()) * 100).toInt()
    }

    private fun setImage(): Int {
        return if (gameResult.winner) {
            R.drawable.ic_happy_smile
        } else {
            R.drawable.ic_sad_smile
        }
    }

    private fun setToBackListeners() {
        binding.buttonRetry.setOnClickListener {
            returnToChooseLevel()
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    returnToChooseLevel()
                }
            })
    }

    private fun returnToChooseLevel() {
        requireActivity().supportFragmentManager.popBackStack(
            GameFragment.NAME_FOR_BACKSTACK,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

    private fun parseArgs() {
        gameResult = requireArguments().get(KEY_GAME_RESULT) as GameResult
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