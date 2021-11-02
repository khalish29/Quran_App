package learn.idn.quranapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import learn.idn.quranapp.help.InputFilterMinMax
import learn.idn.quranapp.network.AlquranModel
import learn.idn.quranapp.network.Config
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuranActivity : AppCompatActivity() {

    val dataText = arrayListOf<String>()
    val dataAyat = arrayListOf<String>()
    lateinit var rvQuran : RecyclerView
    lateinit var progressBar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quran)

        rvQuran = findViewById(R.id.rv_quran)
        val edtSurah : EditText = findViewById(R.id.edt_text)
        val btnOk : Button = findViewById(R.id.btn_ok)
        progressBar = findViewById(R.id.progress_bar)

        edtSurah.filters = arrayOf(InputFilterMinMax(1, 114))
        btnOk.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            dataText.clear()
            dataAyat.clear()
            requestData(edtSurah.text.toString())
        }




    }

    private fun requestData(nomor: String){
        Config().service().listRepos(nomor).enqueue(object : Callback<AlquranModel> {
            override fun onResponse(call: Call<AlquranModel>, response: Response<AlquranModel>) {
                val respon = response.body()!!
                respon.data?.ayahs?.forEach { data ->
                    dataAyat.add(data?.numberInSurah.toString())
                    dataText.add(data?.text.toString())
                    rvQuran.adapter = AdapterQuran(dataAyat, dataText)
                    rvQuran.layoutManager = LinearLayoutManager(this@QuranActivity)
                    rvQuran.setHasFixedSize(true)
                    progressBar.visibility = View.GONE


                }
            }

            override fun onFailure(call: Call<AlquranModel>, t: Throwable) {
                Toast.makeText(this@QuranActivity, "$t", Toast.LENGTH_LONG).show()
                progressBar.visibility = View.GONE
                Log.d("pengetesan", "$t")
            }

        })
    }

}