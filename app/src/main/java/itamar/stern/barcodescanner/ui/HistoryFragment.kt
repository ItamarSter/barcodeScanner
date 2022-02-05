package itamar.stern.barcodescanner.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import itamar.stern.barcodescanner.R
import itamar.stern.barcodescanner.ScannerApplication
import itamar.stern.barcodescanner.adapters.HistoryAdapter
import itamar.stern.barcodescanner.databinding.FragmentHistoryBinding
import itamar.stern.barcodescanner.models.HistoryItem

class HistoryFragment : Fragment() {
    private lateinit var historyList:LiveData<List<HistoryItem>>
    private lateinit var binding:FragmentHistoryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        historyList = ScannerApplication.roomDB.historyDao().getHistory()
        binding.recyclerViewHistory.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        historyList.observe(viewLifecycleOwner){
            binding.recyclerViewHistory.adapter = HistoryAdapter(it)
        }

        binding.buttonClearHistory.setOnClickListener {
            ScannerApplication.roomDB.historyDao().clearHistory()
        }
    }

}