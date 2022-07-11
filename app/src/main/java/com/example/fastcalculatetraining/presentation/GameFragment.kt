package com.example.fastcalculatetraining.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.*
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fastcalculatetraining.R
import com.example.fastcalculatetraining.databinding.FragmentGameBinding
import com.example.fastcalculatetraining.di.GameApp
import com.example.fastcalculatetraining.domain.models.GameResult
import com.example.fastcalculatetraining.domain.models.GameSettings
import com.example.fastcalculatetraining.domain.models.Level
import com.example.fastcalculatetraining.domain.models.Question
import javax.inject.Inject

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

    private val args by navArgs<GameFragmentArgs>()

    private val options by lazy {
        listOf(
            binding.tvOption1,
            binding.tvOption2,
            binding.tvOption3,
            binding.tvOption4,
            binding.tvOption5,
            binding.tvOption6
        )
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: GameViewModel

    private val component by lazy {
        (requireActivity().application as GameApp).component
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[GameViewModel::class.java]
        viewModel.start(args.level)
        setObservers()
        setClickListenersToOptions()
    }

    private fun setObservers() {
        viewModel.formattedTime.observe(viewLifecycleOwner) {
            binding.tvTimer.text = it
        }
        viewModel.gameResult.observe(viewLifecycleOwner) {
            launchGameFinishedFragment(it)
        }
        viewModel.progressAnswers.observe(viewLifecycleOwner) {
            binding.tvAnswersProgress.text = it
        }
        viewModel.percentOfRightAnswers.observe(viewLifecycleOwner) {
            binding.progressBar.setProgress(it, true)
        }
        viewModel.enoughPercent.observe(viewLifecycleOwner) {
            binding.progressBar.progressTintList = ColorStateList.valueOf(setRightColor(it))
        }
        viewModel.enoughCount.observe(viewLifecycleOwner) {
            binding.tvAnswersProgress.setTextColor(setRightColor(it))
        }
        viewModel.minPercent.observe(viewLifecycleOwner) {
            binding.progressBar.secondaryProgress = it
        }
        viewModel.question.observe(viewLifecycleOwner) {
            setQuestion(it)
        }
    }

    private fun setClickListenersToOptions() {
        for (i in options) {
            i.setOnClickListener {
                viewModel.chooseAnswer(i.text.toString().toInt())
            }
        }
    }

    private fun setQuestion(question: Question) {
        binding.tvLeftNumber.text = question.visibleNumber.toString()
        binding.tvSum.text = question.sum.toString()
        for (i in 0 until question.options.size) {
            options[i].text = question.options[i].toString()
        }
    }

    private fun setRightColor(boolean: Boolean): Int {
        return if (boolean) {
            Color.GREEN
        } else {
            Color.RED
        }
    }

    private fun launchGameFinishedFragment(gameResult: GameResult) {
        findNavController().navigate(
            GameFragmentDirections.actionGameFragmentToGameFinishedFragment(
                gameResult
            )
        )
    }

    companion object {

        private const val KEY_LEVEL = "level"

    }
}