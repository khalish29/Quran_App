package learn.idn.quranapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterQuran(private val dataAyat: ArrayList<String>,
                   private val dataText: ArrayList<String>) :
    RecyclerView.Adapter<AdapterQuran.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ayatQuran: TextView = view.findViewById(R.id.ayat)
        val textQuran: TextView = view.findViewById(R.id.text_quran)

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.rv_quran_model, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.ayatQuran.text = dataAyat[position]
        viewHolder.textQuran.text = dataText[position]
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataAyat.size

}