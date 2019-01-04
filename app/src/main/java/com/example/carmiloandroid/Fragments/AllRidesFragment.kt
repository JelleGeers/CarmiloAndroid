import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carmiloandroid.R
import com.example.carmiloandroid.ViewModels.RideViewModel
import com.example.carmiloandroid.databinding.FragmentAllRidesBinding
import kotlinx.android.synthetic.main.fragment_all_rides.view.*

class AllRidesFragment : Fragment() {
private lateinit var viewModel: RideViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding:FragmentAllRidesBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_all_rides,container,false)
        val view=binding.root
        viewModel= ViewModelProviders.of(activity!!).get(RideViewModel::class.java)
        viewModel.refresh()
        binding.rideViewModel = viewModel
        binding.setLifecycleOwner(activity)
        view.rvRides.layoutManager = LinearLayoutManager(this.context)
        view.rvRides.adapter = viewModel.getRideAdapter()
        return view
    }

}