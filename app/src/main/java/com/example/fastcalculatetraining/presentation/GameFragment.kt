package com.example.fastcalculatetraining.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.fastcalculatetraining.R
import com.example.fastcalculatetraining.databinding.FragmentGameBinding
import com.example.fastcalculatetraining.domain.models.GameResult
import com.example.fastcalculatetraining.domain.models.GameSettings
import com.example.fastcalculatetraining.domain.models.Level

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    private lateinit var level: Level

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvLeftNumber.setOnClickListener {
            launchGameFinishedFragment(
                GameResult(
                    true,
                    10,
                    10,
                    GameSettings(10, 10, 10, 10)
                )
            )
        }
    }

    private fun parseArgs() {
        level = requireArguments().get(KEY_LEVEL) as Level
    }

    private fun launchGameFinishedFragment(gameResult: GameResult) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFinishedFragment.newInstance(gameResult))
            .addToBackStack(null)
            .commit()
    }

    companion object {

        private const val KEY_LEVEL = "level"
        const val NAME_FOR_BACKSTACK = "gameFragment"

        fun newInstance(level: Level): GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_LEVEL, level)
                }
            }
        }
    }
}