package com.example.alexpetrov.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.alexpetrov.R
import com.example.alexpetrov.data.model.HeroModel
import com.example.alexpetrov.databinding.FragmentMainBinding
import com.squareup.picasso.Picasso

class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null
    private val bind get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        renderData()
    }

    private fun renderData() {
        val data = arguments?.getParcelable<HeroModel>(KEY)

        with(bind) {
            data?.let {
                Picasso
                    .with(context)
                    .load(data.images.md)
                    .error(R.drawable.ic_baseline_error)
                    .into(bind.image)

                fullName.text = data.name
                intelligence.text = data.powerstats.intelligence.toString()
                strength.text = data.powerstats.strength.toString()
                speed.text = data.powerstats.speed.toString()
                durability.text = data.powerstats.durability.toString()
                power.text = data.powerstats.power.toString()
                combat.text = data.powerstats.combat.toString()

            }
        }
    }

    companion object {
        private const val KEY = "details"

        fun newInstance(data: HeroModel) = MainFragment().apply {
            arguments = Bundle().apply {
                putParcelable(KEY, data)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}