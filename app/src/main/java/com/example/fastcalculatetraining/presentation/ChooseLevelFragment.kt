package com.example.fastcalculatetraining.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fastcalculatetraining.R
import com.example.fastcalculatetraining.databinding.FragmentChooseLevelBinding
import com.example.fastcalculatetraining.domain.models.GameResult
import com.example.fastcalculatetraining.domain.models.Level

class ChooseLevelFragment : Fragment() {

    private var _binding: FragmentChooseLevelBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseLevelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLevelsListener()
    }

    private fun setLevelsListener() {
        binding.buttonEasy.setOnClickListener {
            launchGameFragment(Level.EASY)
        }
        binding.buttonNormal.setOnClickListener {
            launchGameFragment(Level.NORMAL)
        }
        binding.buttonHard.setOnClickListener {
            launchGameFragment(Level.HARD)
        }
        binding.buttonTest.setOnClickListener {
            launchGameFragment(Level.TEST)
        }
    }

    private fun launchGameFragment(level: Level) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFragment.newInstance(level))
            .addToBackStack(GameFragment.NAME_FOR_BACKSTACK)
            .commit()
    }

    companion object {

        fun newInstance(): ChooseLevelFragment {
            return ChooseLevelFragment()
        }
    }
}