package com.busanit.ch18_news

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.busanit.ch18_news.databinding.FragmentRetrofitBinding
import com.busanit.ch18_news.model.PageListModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RetrofitFragment : Fragment() {
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val binding = FragmentRetrofitBinding.inflate(inflater, container, false)
    val retrofitRecyclerView = binding.retrofitRecyclerView

    val call: Call<PageListModel>? = MyApplication.networkService.getList(
      MyApplication.Query, MyApplication.API_KEY, 1, 10
    )
    call?.enqueue(object : Callback<PageListModel> {
      override fun onResponse(call: Call<PageListModel>, response: Response<PageListModel>) {
        if (response.isSuccessful) {
          retrofitRecyclerView.layoutManager = LinearLayoutManager(activity)
          retrofitRecyclerView.adapter = MyAdapter(activity as Context, response.body()?.articles)
        }
      }

      override fun onFailure(call: Call<PageListModel>, t: Throwable) {
        Log.d("myLog", "통신 실패 : ${t.message}")
      }

    })
    return binding.root
  }
}