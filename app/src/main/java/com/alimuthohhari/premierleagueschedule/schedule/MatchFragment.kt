package com.alimuthohhari.premierleagueschedule.schedule


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.alimuthohhari.premierleagueschedule.R
import com.alimuthohhari.premierleagueschedule.api.ApiRepo
import com.alimuthohhari.premierleagueschedule.helper.TestContextProvider
import com.alimuthohhari.premierleagueschedule.main.SpinnerAdapter
import com.alimuthohhari.premierleagueschedule.model.League
import com.alimuthohhari.premierleagueschedule.schedule.last_event.LastEventFragment
import com.alimuthohhari.premierleagueschedule.schedule.next_event.NextEventFragment
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_match.*

class MatchFragment : Fragment(),MatchView {

    private lateinit var adapterSpinner: SpinnerAdapter
    private lateinit var adapterPager: PagerMatch
    private lateinit var presenter: MatchPresenter
    private var listLeague: MutableList<League.LeagueList> = mutableListOf()
    private var league = String()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val request = ApiRepo()
        val gson = Gson()

        tabs_event.setupWithViewPager(pager_match)

        presenter = MatchPresenter(this,request,gson,TestContextProvider())
        adapterSpinner = SpinnerAdapter(requireContext(), listLeague)
        spinnerLeague.adapter = adapterSpinner
       // presenter.getLeague()

        spinnerLeague.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                league = adapterSpinner.getItem(position).idLeague
                settupPager(league)
            }

        }
    }

    private fun settupPager(league: String) {
        pager_match.offscreenPageLimit = 2
        adapterPager = PagerMatch(activity?.supportFragmentManager)
        adapterPager.addFragment(NextEventFragment.newInstance(league))
        adapterPager.addFragment(LastEventFragment.newInstance(league))
        pager_match.adapter = adapterPager

    }

    override fun showLeague(data: List<League.LeagueList>) {
        listLeague.clear()
        listLeague.addAll(data)
        adapterSpinner.notifyDataSetChanged()
    }

}
