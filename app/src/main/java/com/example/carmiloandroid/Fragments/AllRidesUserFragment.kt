
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carmiloandroid.R
import com.example.carmiloandroid.ViewModels.RideUserViewModel
import com.example.carmiloandroid.ViewModels.RideViewModel
import com.example.carmiloandroid.databinding.FragmentAllRidesUserBinding
import kotlinx.android.synthetic.main.design_bottom_sheet_dialog.*
import kotlinx.android.synthetic.main.fragment_all_rides_user.view.*

class AllRidesUserFragment : Fragment() {
    private lateinit var viewModel: RideUserViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentAllRidesUserBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_rides_user,container,false)
        val view=binding.root
        viewModel= ViewModelProviders.of(activity!!).get(RideUserViewModel::class.java)
        binding.rideUserViewModel = viewModel
        binding.setLifecycleOwner(activity)
        view.rvRidesUser.layoutManager = LinearLayoutManager(this.context)
        view.rvRidesUser.adapter = viewModel.getRideUserAdapter()
        return view
    }

}