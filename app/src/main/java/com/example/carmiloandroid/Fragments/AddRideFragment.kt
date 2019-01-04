import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.carmiloandroid.Endpoint
import com.example.carmiloandroid.Models.Ride
import com.example.carmiloandroid.R
import com.example.carmiloandroid.RetrofitClientInstance
import kotlinx.android.synthetic.main.fragment_add_ride.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AddRideFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_add_ride, container, false)

    }

    fun addRide(){
        val service = RetrofitClientInstance().getRetrofitInstance()!!.create(Endpoint::class.java)
        val call=service.addRideUser("5c2a4667a28066218c99160b",txtDeparture.text.toString(),txtDate.text.toString(),txtStreet.text.toString(),txtHouseNr.text.toString(),txtZipcode.text.toString(),txtMaxPassengers.text.toString());
        call.enqueue(object : Callback<Ride> {
            override fun onResponse(call: Call<Ride>, response: Response<Ride>) {

            }

            override fun onFailure(call: Call<Ride>, t: Throwable) {
                Log.d("Error", t.message)
            }
        })
    }


    override fun onResume() {
        super.onResume()
        btnAddRide.setOnClickListener { addRide() }
    }
}