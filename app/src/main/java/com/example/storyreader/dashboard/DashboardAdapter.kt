package com.example.storyreader.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.common.BookDetails
import com.example.storyreader.R
import kotlinx.android.synthetic.main.item_words.view.*

class DashboardAdapter(private val context: Context) : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    private var dataHashMap = hashMapOf<String, BookDetails>()
    private var dataList =  arrayListOf<Pair<String, BookDetails>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_words, parent, false))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (dataList.isNotEmpty()) {
            holder.bind(dataList[position].first, dataList[position].second, context)
        }
    }

    fun setData(listOfBooks: HashMap<String, BookDetails>) {
        dataHashMap = listOfBooks
        dataList = ArrayList(dataHashMap.size)
        for ((word, bookDetails) in dataHashMap) {
            dataList.add(Pair(word, bookDetails))
        }
        this.notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(word: String, second: BookDetails, context: Context) {
            itemView.wordLabel.text = word
            itemView.wordCount.text = context.getString(R.string.word_count_description, second.count.toString())
            itemView.wordPrimeNumber.text = context.getString(R.string.word_count_description, second.count.toString())
            itemView.wordPrimeNumber.text = context.getString(R.string.word_count_description, second.isPrimeNumber.toString())
        }
    }
}